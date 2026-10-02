package com.vetturno.vetturno.repository;

import com.vetturno.vetturno.model.Propietario;
import com.vetturno.vetturno.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Búsqueda por email
    Optional<Usuario> findByEmail(String email);

    // Verificación de existencia
    boolean existsByEmail(String email);

}
