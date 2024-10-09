package PRM392.CleaningServices.controller;


import PRM392.CleaningServices.dto.request.LoginRequest;
import PRM392.CleaningServices.model.User;
import PRM392.CleaningServices.repository.UserRepository;
import PRM392.CleaningServices.ultils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(email);

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        response.put("email", user.getEmail());
        response.put("role", user.getRole().toString());

        return ResponseEntity.ok(response);
    }
    @GetMapping("/validate-token")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String token) {
        String email = jwtUtil.extractEmail(token.substring(7));

        if (jwtUtil.validateToken(token, email)) {
            return ResponseEntity.ok("Token is valid and user has the required permissions.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token.");
        }
    }
}