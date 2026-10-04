package com.vetturno.vetturno.service;


import com.vetturno.vetturno.dto.AuthResponse;
import com.vetturno.vetturno.dto.LoginRequest;
import com.vetturno.vetturno.dto.RegistroRequest;
import com.vetturno.vetturno.model.Rol;
import com.vetturno.vetturno.model.Usuario;
import com.vetturno.vetturno.repository.UsuarioRepository;
import com.vetturno.vetturno.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(UsuarioRepository usuarioRepository,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager,
                       JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public AuthResponse registrar(RegistroRequest request) {
        Usuario usuario = new Usuario();
        usuario.setEmail(request.getEmail());

        // Aplica BCrypt al la contraseña
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));

        // Guarda el rol del usuario por default en USER
        usuario.setRol(Rol.USER);

        usuarioRepository.save(usuario);

        String token = jwtService.generarToken(usuario.getEmail(), usuario.getRol().name());
        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest request) {

        // Utiliza el mecanismo de autenticación de Spring
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()));


        Usuario usuario = usuarioRepository.findByEmail(request.getEmail()).orElseThrow();

        // Genera un JWT vigente
        String token = jwtService.generarToken(usuario.getEmail(), usuario.getRol().name());
        return new AuthResponse(token);
    }
}