package xyz.juniorapeles.catalog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import xyz.juniorapeles.catalog.dto.AuthRequest;
import xyz.juniorapeles.catalog.dto.AuthResponse;
import xyz.juniorapeles.catalog.security.CustomUserDetailsService;
import xyz.juniorapeles.catalog.security.JwtTokenProvider;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );

        UserDetails user = userDetailsService.loadUserByUsername(request.username());
        String token = jwtTokenProvider.generateToken(user.getUsername());

        return ResponseEntity.ok(new AuthResponse(token));
    }
}
