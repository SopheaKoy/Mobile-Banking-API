package co.istad.mbanking.api.accounttype.web;

import co.istad.mbanking.api.accounttype.AccountTypeService;
import co.istad.mbanking.base.BaseRest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account-type")
@CrossOrigin("http://localhost:3000")
public class AccountTypeRestController {

    private final AccountTypeService accountTypeService;
    @GetMapping
    public BaseRest<?> selectAll(){
       var accountTypeDtoList = accountTypeService.findAll();
       return BaseRest.builder()
               .status(true)
               .code(HttpStatus.OK.value())
               .message("User Account types have been found")
               .timestamp(LocalDateTime.now())
               .data(accountTypeDtoList)
               .build();
    }

    @GetMapping ("/{id}")
    public BaseRest<?> findAccountById(@PathVariable Integer id){
        AccountTypeDto accountTypeDto = accountTypeService.findAccountTypeById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("User Account types have been found by id.")
                .timestamp(LocalDateTime.now())
                .data(accountTypeDto)
                .build();
    }

    @DeleteMapping("/{id}")
    public BaseRest<?> deleteAll(@PathVariable Integer id){
        Integer deleteById = accountTypeService.deleteAccountTypeById(id);
        return BaseRest.builder()
                .status(true)
                .message("Delete account-type successfully.")
                .code(HttpStatus.OK.value())
                .data(deleteById)
                .timestamp(LocalDateTime.now())
                .build();
    }
    @PostMapping
    public BaseRest<?> createNewAccountType(@RequestBody @Valid CreateAccountTypeDto createAccountTypeDto){
       accountTypeService.insertAccountType(createAccountTypeDto);
        return BaseRest.builder()
                .status(true)
                .message("Inserts accounts successfully.")
                .code(HttpStatus.OK.value())
                .data(createAccountTypeDto)
                .timestamp(LocalDateTime.now())
                .build();
    }
    @PutMapping("/{id}")
    public BaseRest<?> updateAccountById(@PathVariable Integer id , @RequestBody UpdateAccountTypeDto updateAccountTypeDto){
        AccountTypeDto accountTypeDto = accountTypeService.updateAccountTypeById(id,updateAccountTypeDto);
        return BaseRest.builder()
                .status(true)
                .message("Update accounts successfully.")
                .code(HttpStatus.OK.value())
                .data(accountTypeDto)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
