package com.marcandohuellitas.api.services;

import com.marcandohuellitas.api.models.Usuario;
import com.marcandohuellitas.api.repositories.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UsuarioServicesTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioServices usuarioServices;

    @Test
    void login_DebeLanzarExcepcion_CuandoFalla() {
        Usuario mockUser = new Usuario();
        mockUser.setCorreo("test@huellitas.com");
        mockUser.setPassword("12345");
        
        when(usuarioRepository.findByCorreo("test@huellitas.com")).thenReturn(Optional.of(mockUser));
        when(passwordEncoder.matches("malaPass", "12345")).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            usuarioServices.loginUsuario("test@huellitas.com", "malaPass");
        });
        
        assertTrue(exception.getMessage().contains("Credenciales incorrectas"));
    }
}