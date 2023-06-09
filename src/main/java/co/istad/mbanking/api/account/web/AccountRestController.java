package co.istad.mbanking.api.account.web;

import co.istad.mbanking.api.account.Account;
import co.istad.mbanking.api.account.AccountService;
import co.istad.mbanking.api.accounttype.AccountType;
import co.istad.mbanking.base.BaseRest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountRestController {
    private final AccountService accountService;
    @GetMapping
    public BaseRest<?> findAll(AccountType accountType){
        List<AccountDto> accountsDto = accountService.findAll(accountType);
        return BaseRest.builder()
                .code(HttpStatus.OK.value())
                .status(true)
                .message("User_Account has been found.")
                .data(accountsDto)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
