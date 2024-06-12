package nl.moukafih.demo.service;

import nl.moukafih.demo.entity.RefreshToken;
import nl.moukafih.demo.entity.User;
import nl.moukafih.demo.repository.RefreshTokenRepository;
import nl.moukafih.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Map<String, String> login(String username, String password) {

        Optional<User> user = userRepository.findByEmail(username);

        if (user.isEmpty() || !passwordEncoder.matches(password, user.get().getPassword())) {
            return null;
            //throw new UsernameNotFoundException("User not found");
        }

        //UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(username, password);
        //Authentication auth = authenticationManager.authenticate(credentials);

        String accessToken = jwtService.generateAccessToken(username);
        String refreshToken = jwtService.generateRefreshToken(username);

        RefreshToken token = new RefreshToken();
        token.setToken(refreshToken);
        token.setUser(user.get());
        refreshTokenRepository.save(token);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken);
        return tokens;
    }

    public Map<String, String> refreshToken(String refreshToken) {
        RefreshToken token = refreshTokenRepository.findByToken(refreshToken);
        if (token == null || !jwtService.validateToken(refreshToken)) {
            return null;
        }

        String username = jwtService.getUsernameFromToken(refreshToken);
        String newAccessToken = jwtService.generateAccessToken(username);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", newAccessToken);
        return tokens;
    }
}
