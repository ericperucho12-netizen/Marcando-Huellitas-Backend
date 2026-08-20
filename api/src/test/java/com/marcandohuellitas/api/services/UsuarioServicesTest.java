package com.marcandohuellitas.api.services;

import com.marcandohuellitas.api.models.Usuario;
import com.marcandohuellitas.api.repositories.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Pruebas Unitarias para UsuarioServices.
 * Usamos Mockito (@Mock) para simular la base de datos y no tocar datos reales.
 */
@ExtendWith(MockitoExtension.class)
public class UsuarioServicesTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioServices usuarioServices;

    private Usuario usuarioMock;

    @BeforeEach
    void setUp() {
        // Preparamos un usuario de prueba antes de cada test
        usuarioMock = new Usuario();
        usuarioMock.setId(1L);
        usuarioMock.setCorreo("test@huellitas.com");
        usuarioMock.setPassword("contrasenaEncriptada");
        usuarioMock.setIntentosFallidos(0);
    }

    @Test
    void registrarUsuario_Exito() {
        // GIVEN: Le decimos al Mock que cuando busque este correo, NO encuentre nada (Optional.empty)
        when(usuarioRepository.findByCorreo(anyString())).thenReturn(Optional.empty());
        // Y que cuando encripte, devuelva una cadena encriptada
        when(passwordEncoder.encode(anyString())).thenReturn("contrasenaEncriptada");
        // Y que cuando guarde, devuelva el usuarioMock
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioMock);

        Usuario nuevoUser = new Usuario();
        nuevoUser.setCorreo("test@huellitas.com");
        nuevoUser.setPassword("12345");

        // WHEN: Ejecutamos el método real
        Usuario resultado = usuarioServices.registrarUsuario(nuevoUser);

        // THEN: Comprobamos que el resultado no sea nulo y que el repositorio se haya llamado
        assertNotNull(resultado);
        assertEquals("contrasenaEncriptada", resultado.getPassword());
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    void loginUsuario_CredencialesIncorrectas() {
        // GIVEN: El usuario existe en la BD
        when(usuarioRepository.findByCorreo("test@huellitas.com")).thenReturn(Optional.of(usuarioMock));
        // Pero la contraseña NO coincide
        when(passwordEncoder.matches("malaPass", "contrasenaEncriptada")).thenReturn(false);

        // WHEN
        String resultado = usuarioServices.loginUsuario("test@huellitas.com", "malaPass");

        // THEN
        assertTrue(resultado.contains("Credenciales incorrectas"));
        assertEquals(1, usuarioMock.getIntentosFallidos()); // Se debió sumar 1 intento fallido
        verify(usuarioRepository, times(1)).save(usuarioMock); // Verificamos que se guardó el intento
    }
}
