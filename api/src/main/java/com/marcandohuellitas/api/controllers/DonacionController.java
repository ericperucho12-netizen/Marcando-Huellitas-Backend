package com.marcandohuellitas.api.controllers;

import com.marcandohuellitas.api.models.Donacion;
import com.marcandohuellitas.api.services.DonacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Recibir peticiones HTTP desde frontend o Postman
@RestController

// Ruta principal del módulo de donaciones
@RequestMapping("/api/donaciones")
public class DonacionController {

    @Autowired
    private DonacionService donacionService;

    // CREATE - Registrar una nueva donación
    @PostMapping
    public ResponseEntity<?> registrarDonacion(@RequestBody Donacion donacion) {

        try {
            Donacion nuevaDonacion =
                    donacionService.registrarDonacion(donacion);

            return ResponseEntity.ok(nuevaDonacion);

        } catch (Exception e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    // READ - Obtener todas las donaciones
    @GetMapping
    public ResponseEntity<List<Donacion>> obtenerDonaciones() {

        List<Donacion> donaciones =
                donacionService.obtenerDonaciones();

        return ResponseEntity.ok(donaciones);
    }

    // READ - Obtener una donación por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerDonacionPorId(@PathVariable Long id) {

        try {
            Donacion donacion =
                    donacionService.obtenerDonacionPorId(id);

            return ResponseEntity.ok(donacion);

        } catch (Exception e) {

            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    // UPDATE - Actualizar una donación por ID
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarDonacion(
            @PathVariable Long id,
            @RequestBody Donacion donacion) {

        try {
            Donacion donacionActualizada =
                    donacionService.actualizarDonacion(id, donacion);

            return ResponseEntity.ok(donacionActualizada);

        } catch (Exception e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    // DELETE - Eliminar una donación
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarDonacion(@PathVariable Long id) {

        try {
            donacionService.eliminarDonacion(id);

            return ResponseEntity.ok(
                    "Donación eliminada correctamente"
            );

        } catch (Exception e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
}
