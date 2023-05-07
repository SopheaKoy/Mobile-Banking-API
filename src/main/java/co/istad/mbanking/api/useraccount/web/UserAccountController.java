package co.istad.mbanking.api.useraccount.web;

import co.istad.mbanking.api.useraccount.UserAccountService;
import co.istad.mbanking.base.BaseRest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user-account")
@RequiredArgsConstructor
public class UserAccountController {
    private final UserAccountService userAccountService;
    @GetMapping
    public BaseRest<?> findAll(){
       List<UserAccountDto> userAccountDto = userAccountService.findAll();
        System.out.println(userAccountDto);
        return BaseRest.builder()
                .code(HttpStatus.OK.value())
                .status(true)
                .message("User has been found successfully.")
                .data(userAccountDto)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
