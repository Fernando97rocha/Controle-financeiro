package com.app.controlefinanceiro.controller.authenticationController;

import com.app.controlefinanceiro.dto.authentication.AuthenticationDto;
import com.app.controlefinanceiro.dto.authentication.RegisterDto;
import com.app.controlefinanceiro.model.user.User;
import com.app.controlefinanceiro.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "auth")
public class AuthenticationController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody @Validated AuthenticationDto dto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/register")
    public ResponseEntity register (@RequestBody @Validated RegisterDto dto) {
        if (repository.findByLogin(dto.login()) != null)
            return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
        User user = new User(dto.userId(), dto.login(), encryptedPassword, dto.role());
        repository.save(user);

        return ResponseEntity.ok().build();
    }
}
