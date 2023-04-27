package co.istad.mbanking.api.accounttype.web;

import co.istad.mbanking.api.accounttype.AccountTypeService;
import co.istad.mbanking.api.accounttype.web.AccountTypeDto;
import co.istad.mbanking.base.BaseRest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account-type")
public class AccountTypeRestController {
    private final AccountTypeService accountTypeService;
    @GetMapping
    public BaseRest<?> findAll(){
       var accountTypeDtoList = accountTypeService.findAll();
       return BaseRest.builder()
               .status(true)
               .code(HttpStatus.OK.value())
               .message("Account types have been found")
               .timestamp(LocalDateTime.now())
               .data(accountTypeDtoList)
               .build();
    }
    @DeleteMapping("/api/v1/account-type")
    public BaseRest<?> deleteAll(){
        var accountTypeDtoList = accountTypeService.delete();
        return BaseRest.builder()
                .status(true)
                .message("Delete accounts successfully")
                .code(HttpStatus.OK.value())
                .data(accountTypeDtoList)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @PostMapping({"id"})
    public BaseRest<?> createNewAccountType(@RequestBody @Valid AccountTypeDto accountTypeDto){

        return null;
    }
}
