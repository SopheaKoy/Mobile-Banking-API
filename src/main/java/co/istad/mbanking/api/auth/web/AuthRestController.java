package co.istad.mbanking.api.auth.web;

import co.istad.mbanking.api.auth.AuthService;
import co.istad.mbanking.base.BaseRest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthRestController {

    private final AuthService  authService;
    @PostMapping("/register")
    public BaseRest<?> register(@Valid @RequestBody RegisterDto register){
        authService.register(register);
        return BaseRest.builder()
                .code(HttpStatus.OK.value())
                .message("You have been registered successfully.")
                .timestamp(LocalDateTime.now())
                .status(true)
                .data(register.email())
                .build();
    }

    @PostMapping("/verify")
    public BaseRest<?> verify(@RequestParam String email){
        authService.verify(email);
        return BaseRest.builder()
                .code(HttpStatus.OK.value())
                .message("You have been send successfully.")
                .timestamp(LocalDateTime.now())
                .status(true )
                .data(email)
                .build();
    }
    @GetMapping("/check-verify")
    public BaseRest<?> checkVerify(@RequestParam String email, @RequestParam String verifiedCode){
        authService.checkVerify(email,verifiedCode);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("You have been verified successfully.")
                .timestamp(LocalDateTime.now())
                .data(email)
                .build();
    }

    @PostMapping("/login")
    public BaseRest<?> login(@Valid @RequestBody LogInDto loginDto){
        AuthDto authDto = authService.login(loginDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("You have been login successfully.")
                .timestamp(LocalDateTime.now())
                .data(authDto)
                .build();
    }

}
