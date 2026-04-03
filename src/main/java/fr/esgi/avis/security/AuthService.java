package fr.esgi.avis.security;

import fr.esgi.avis.domain.dto.LoginRequest;
import fr.esgi.avis.domain.dto.LoginResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtUtil jwtUtil;

    public AuthService(AuthenticationManager authenticationManager,
                       UserDetailsServiceImpl userDetailsService,
                       JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.pseudo(), request.motDePasse()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.pseudo());
        String role = userDetails.getAuthorities().iterator().next().getAuthority();
        String token = jwtUtil.generateToken(request.pseudo(), role);
        return new LoginResponse(token);
    }
}
