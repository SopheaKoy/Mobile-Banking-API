package co.istad.mbanking.api.accounttype;

import co.istad.mbanking.api.accounttype.web.AccountTypeDto;
import co.istad.mbanking.api.accounttype.web.CreateAccountTypeDto;
import co.istad.mbanking.api.accounttype.web.UpdateAccountTypeDto;
import co.istad.mbanking.api.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountTypeServiceImpl implements AccountTypeService{

    private final AccountTypeMapper accountTypeMapper;
    private final AccountTypeMapStruct accountTypeMapStruct;

    @Override
    public List<AccountTypeDto> findAll() {
        List<AccountType> accountTypes = accountTypeMapper.selectAll();
        return accountTypeMapStruct.toDtoList(accountTypes);
    }


//    @Override
//    public void insertAccountType(@RequestBody CreateAccountTypeDto createAccountTypeDto) {
//       accountTypeMapper.insert(accountTypeMapStruct.createAccountTypeToAccountDto(createAccountTypeDto));
//    }

    @Override
    public Integer deleteAccountTypeById(Integer id) {
        accountTypeMapper.deleteById(id);
        log.info("Delete : "+accountTypeMapper);
        return  id;
    }

    @Override
    public AccountTypeDto findAccountTypeById(Integer id) {
        AccountType accountType = accountTypeMapper.selectById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with %d is not found",id)));
        return accountTypeMapStruct.toDto(accountType);
    }

    @Override
    public AccountTypeDto updateAccountTypeById(Integer id, UpdateAccountTypeDto updateAccountTypeDto) {
//        AccountType accountType = accountTypeMapStruct.updateAccountTypeToAccountDto(updateAccountTypeDto);
//        accountTypeMapper.updateById(accountType);
//        System.out.println(accountType.getName());
//        return null;
        AccountType  accountType;
        if(accountTypeMapper.existsById(id)){
            accountType =accountTypeMapStruct.updateAccountTypeToAccountDto(updateAccountTypeDto);
            accountType.setId(id);
            accountTypeMapper.updateById(accountType);
            return this.findAccountTypeById(id);
        }
        throw  new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User_Account with %d not found..!"));
    }

    @Override
    public AccountTypeDto insertAccountType(CreateAccountTypeDto createAccountTypeDto) {
        AccountType accountType = accountTypeMapStruct.createAccountTypeToAccountDto(createAccountTypeDto);
        accountTypeMapper.insert(accountType);
        log.info("User_Account : " +accountType);
        return this.findAccountTypeById(accountType.getId());
    }

}
