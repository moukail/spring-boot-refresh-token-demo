package nl.moukafih.demo.controller;

import nl.moukafih.demo.dto.LoginDTO;
import nl.moukafih.demo.dto.RefreshTokenDTO;
import nl.moukafih.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        Map<String, String> tokens = authService.login(loginDTO.email(), loginDTO.password());
        if (tokens == null) {
            return ResponseEntity.badRequest().body("Invalid credentials");
        }
        return ResponseEntity.ok(tokens);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenDTO refreshTokenDTO) {
        Map<String, String> tokens = authService.refreshToken(refreshTokenDTO.refreshToken());
        if (tokens == null) {
            return ResponseEntity.badRequest().body("Invalid refresh token");
        }
        return ResponseEntity.ok(tokens);
    }
}
