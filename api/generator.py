import os
import re

schema_file = 'database/schema.sql'
base_pkg = 'com.marcandohuellitas.api'
base_dir = 'src/main/java/com/marcandohuellitas/api'
test_dir = 'src/test/java/com/marcandohuellitas/api'

type_map = {
    'BIGINT': 'Long',
    'VARCHAR': 'String',
    'TEXT': 'String',
    'INT': 'Integer',
    'DECIMAL': 'Double',
    'TIMESTAMP': 'LocalDateTime'
}

def to_camel_case(snake_str):
    components = snake_str.split('_')
    return components[0] + ''.join(x.title() for x in components[1:])

def to_pascal_case(snake_str):
    components = snake_str.split('_')
    return ''.join(x.title() for x in components)

def parse_schema(file_path):
    tables = []
    with open(file_path, 'r', encoding='utf-8') as f:
        content = f.read()
        
    matches = re.finditer(r'CREATE TABLE IF NOT EXISTS\s+(\w+)\s*\((.*?)\);', content, re.DOTALL | re.IGNORECASE)
    for match in matches:
        table_name = match.group(1).lower()
        if table_name == 'usuarios':
            continue
            
        columns = []
        body = match.group(2)
        lines = body.split('\n')
        for line in lines:
            line = line.strip()
            if not line or line.startswith('--') or line.startswith('PRIMARY KEY') or line.startswith('FOREIGN KEY'):
                continue
                
            col_match = re.match(r'(\w+)\s+(\w+)(?:\(\d+(?:,\s*\d+)?\))?', line)
            if col_match:
                col_name = col_match.group(1).lower()
                sql_type = col_match.group(2).upper()
                java_type = type_map.get(sql_type, 'String')
                
                if col_name == 'id':
                    java_type = 'Long'
                
                columns.append({
                    'name': col_name,
                    'camel': to_camel_case(col_name),
                    'type': java_type
                })
                
        entity_name = to_pascal_case(table_name)
        if entity_name.endswith('s'):
            if entity_name.endswith('es') and not entity_name.endswith('litas'):
                entity_name = entity_name[:-2]
            else:
                entity_name = entity_name[:-1]
                
        if entity_name == 'SolicitudesAdopcion': entity_name = 'SolicitudAdopcion'
        if entity_name == 'DetallesPedido': entity_name = 'DetallePedido'
        if entity_name == 'HistoriasExito': entity_name = 'HistoriaExito'
        if entity_name == 'Donacione': entity_name = 'Donacion'
        
        tables.append({
            'table': table_name,
            'entity': entity_name,
            'camel': entity_name[0].lower() + entity_name[1:],
            'columns': columns
        })
    return tables

def generate_model(table):
    code = f"package {base_pkg}.models;\n\n"
    code += "import jakarta.persistence.*;\nimport lombok.Data;\nimport lombok.NoArgsConstructor;\nimport lombok.AllArgsConstructor;\nimport java.time.LocalDateTime;\n\n"
    code += "/**\n * Modelo Entidad para " + table['entity'] + ".\n"
    code += " * Esta clase representa la tabla " + table['table'] + " en la base de datos.\n */\n"
    code += "@Entity\n@Table(name = \"" + table['table'] + "\")\n"
    code += "@Data // Genera getters y setters\n@NoArgsConstructor\n@AllArgsConstructor\n"
    code += f"public class {table['entity']} {{\n\n"
    
    for col in table['columns']:
        if col['name'] == 'id':
            code += "    @Id\n    @GeneratedValue(strategy = GenerationType.IDENTITY)\n"
        elif col['name'] in ['creado_en', 'actualizado_en']:
            code += f"    @Column(name = \"{col['name']}\", insertable = false, updatable = false)\n"
        elif col['name'] != col['camel']:
            code += f"    @Column(name = \"{col['name']}\")\n"
        
        code += f"    private {col['type']} {col['camel']};\n\n"
        
    code += "}\n"
    
    path = os.path.join(base_dir, 'models', f"{table['entity']}.java")
    os.makedirs(os.path.dirname(path), exist_ok=True)
    with open(path, 'w', encoding='utf-8') as f: f.write(code)

def generate_repository(table):
    code = f"package {base_pkg}.repositories;\n\n"
    code += f"import {base_pkg}.models.{table['entity']};\n"
    code += "import org.springframework.data.jpa.repository.JpaRepository;\nimport org.springframework.stereotype.Repository;\n\n"
    code += "/**\n * Repositorio para " + table['entity'] + ".\n"
    code += " * Nos permite acceder a la base de datos sin escribir SQL.\n */\n"
    code += "@Repository\n"
    code += f"public interface {table['entity']}Repository extends JpaRepository<{table['entity']}, Long> {{\n"
    code += "}\n"
    
    path = os.path.join(base_dir, 'repositories', f"{table['entity']}Repository.java")
    os.makedirs(os.path.dirname(path), exist_ok=True)
    with open(path, 'w', encoding='utf-8') as f: f.write(code)

def generate_service(table):
    code = f"package {base_pkg}.services;\n\n"
    code += f"import {base_pkg}.models.{table['entity']};\n"
    code += f"import {base_pkg}.repositories.{table['entity']}Repository;\n"
    code += "import org.springframework.beans.factory.annotation.Autowired;\nimport org.springframework.stereotype.Service;\nimport java.util.List;\nimport java.util.Optional;\n\n"
    code += "/**\n * Servicio para " + table['entity'] + ".\n"
    code += " * Aquí va la lógica de negocio.\n */\n"
    code += "@Service\n"
    code += f"public class {table['entity']}Service {{\n\n"
    code += "    @Autowired // Inyectamos el repositorio\n"
    code += f"    private {table['entity']}Repository repository;\n\n"
    code += f"    public List<{table['entity']}> obtenerTodos() {{\n        return repository.findAll();\n    }}\n\n"
    code += f"    public Optional<{table['entity']}> obtenerPorId(Long id) {{\n        return repository.findById(id);\n    }}\n\n"
    code += f"    public {table['entity']} guardar({table['entity']} entidad) {{\n        return repository.save(entidad);\n    }}\n\n"
    code += f"    public void eliminar(Long id) {{\n        repository.deleteById(id);\n    }}\n"
    code += "}\n"
    
    path = os.path.join(base_dir, 'services', f"{table['entity']}Service.java")
    os.makedirs(os.path.dirname(path), exist_ok=True)
    with open(path, 'w', encoding='utf-8') as f: f.write(code)

def generate_controller(table):
    code = f"package {base_pkg}.controllers;\n\n"
    code += f"import {base_pkg}.models.{table['entity']};\n"
    code += f"import {base_pkg}.services.{table['entity']}Service;\n"
    code += "import org.springframework.beans.factory.annotation.Autowired;\nimport org.springframework.http.ResponseEntity;\nimport org.springframework.web.bind.annotation.*;\nimport java.util.List;\n\n"
    code += "/**\n * Controlador REST para " + table['entity'] + ".\n"
    code += " * Recibe las peticiones HTTP del frontend.\n */\n"
    code += "@RestController\n"
    code += f"@RequestMapping(\"/api/{table['table']}\")\n"
    code += f"public class {table['entity']}Controller {{\n\n"
    code += "    @Autowired // Inyectamos el servicio\n"
    code += f"    private {table['entity']}Service service;\n\n"
    code += "    @GetMapping // GET /api/" + table['table'] + "\n"
    code += f"    public List<{table['entity']}> listarTodos() {{\n        return service.obtenerTodos();\n    }}\n\n"
    code += "    @GetMapping(\"/{id}\") // GET /api/" + table['table'] + "/{id}\n"
    code += f"    public ResponseEntity<{table['entity']}> obtenerPorId(@PathVariable Long id) {{\n"
    code += "        return service.obtenerPorId(id)\n                .map(ResponseEntity::ok)\n                .orElse(ResponseEntity.notFound().build());\n    }\n\n"
    code += "    @PostMapping // POST /api/" + table['table'] + "\n"
    code += f"    public {table['entity']} crear(@RequestBody {table['entity']} entidad) {{\n        return service.guardar(entidad);\n    }}\n\n"
    code += "    @DeleteMapping(\"/{id}\") // DELETE /api/" + table['table'] + "/{id}\n"
    code += f"    public ResponseEntity<Void> eliminar(@PathVariable Long id) {{\n        service.eliminar(id);\n        return ResponseEntity.ok().build();\n    }}\n"
    code += "}\n"
    
    path = os.path.join(base_dir, 'controllers', f"{table['entity']}Controller.java")
    os.makedirs(os.path.dirname(path), exist_ok=True)
    with open(path, 'w', encoding='utf-8') as f: f.write(code)

def generate_service_test(table):
    code = f"package {base_pkg}.services;\n\n"
    code += f"import {base_pkg}.models.{table['entity']};\n"
    code += f"import {base_pkg}.repositories.{table['entity']}Repository;\n"
    code += "import org.junit.jupiter.api.Test;\nimport org.junit.jupiter.api.extension.ExtendWith;\nimport org.mockito.InjectMocks;\nimport org.mockito.Mock;\nimport org.mockito.junit.jupiter.MockitoExtension;\nimport java.util.Optional;\n"
    code += "import static org.junit.jupiter.api.Assertions.*;\nimport static org.mockito.ArgumentMatchers.any;\nimport static org.mockito.Mockito.*;\n\n"
    code += "/**\n * Pruebas unitarias para " + table['entity'] + "Service.\n */\n"
    code += f"@ExtendWith(MockitoExtension.class)\n"
    code += f"public class {table['entity']}ServiceTest {{\n\n"
    code += "    @Mock\n"
    code += f"    private {table['entity']}Repository repository;\n\n"
    code += "    @InjectMocks\n"
    code += f"    private {table['entity']}Service service;\n\n"
    code += "    @Test\n"
    code += f"    void guardar_Exito() {{\n        // GIVEN\n        {table['entity']} mockEntidad = new {table['entity']}();\n"
    code += f"        when(repository.save(any({table['entity']}.class))).thenReturn(mockEntidad);\n"
    code += f"        // WHEN\n        {table['entity']} resultado = service.guardar(new {table['entity']}());\n"
    code += f"        // THEN\n        assertNotNull(resultado);\n        verify(repository, times(1)).save(any({table['entity']}.class));\n    }}\n"
    code += "}\n"
    
    path = os.path.join(test_dir, 'services', f"{table['entity']}ServiceTest.java")
    os.makedirs(os.path.dirname(path), exist_ok=True)
    with open(path, 'w', encoding='utf-8') as f: f.write(code)

def generate_controller_test(table):
    code = f"package {base_pkg}.controllers;\n\n"
    code += f"import {base_pkg}.models.{table['entity']};\n"
    code += f"import {base_pkg}.services.{table['entity']}Service;\n"
    code += "import org.junit.jupiter.api.Test;\nimport org.junit.jupiter.api.extension.ExtendWith;\nimport org.mockito.InjectMocks;\nimport org.mockito.Mock;\nimport org.mockito.junit.jupiter.MockitoExtension;\nimport org.springframework.http.ResponseEntity;\n"
    code += "import java.util.Collections;\nimport java.util.List;\n"
    code += "import static org.junit.jupiter.api.Assertions.assertEquals;\nimport static org.mockito.Mockito.when;\n\n"
    code += "/**\n * Pruebas unitarias para " + table['entity'] + "Controller.\n */\n"
    code += f"@ExtendWith(MockitoExtension.class)\n"
    code += f"public class {table['entity']}ControllerTest {{\n\n"
    code += "    @Mock\n"
    code += f"    private {table['entity']}Service service;\n\n"
    code += "    @InjectMocks\n"
    code += f"    private {table['entity']}Controller controller;\n\n"
    code += "    @Test\n"
    code += f"    void listarTodos_DebeRetornarLista() {{\n        // GIVEN\n        when(service.obtenerTodos()).thenReturn(Collections.singletonList(new {table['entity']}()));\n"
    code += f"        // WHEN\n        List<{table['entity']}> resultado = controller.listarTodos();\n"
    code += f"        // THEN\n        assertEquals(1, resultado.size());\n    }}\n"
    code += "}\n"
    
    path = os.path.join(test_dir, 'controllers', f"{table['entity']}ControllerTest.java")
    os.makedirs(os.path.dirname(path), exist_ok=True)
    with open(path, 'w', encoding='utf-8') as f: f.write(code)

if __name__ == '__main__':
    tables = parse_schema(schema_file)
    for t in tables:
        print(f"Generando backend para {t['entity']}...")
        generate_model(t)
        generate_repository(t)
        generate_service(t)
        generate_controller(t)
        generate_service_test(t)
        generate_controller_test(t)
    print("Listo!")
