package co.istad.mbanking.api.accounttype;

import co.istad.mbanking.api.accounttype.web.AccountTypeDto;
import co.istad.mbanking.api.accounttype.web.CreateAccountTypeDto;
import co.istad.mbanking.api.accounttype.web.UpdateAccountTypeDto;

import java.util.List;

public interface AccountTypeService {
    List<AccountTypeDto> findAll();

    AccountTypeDto insertAccountType(CreateAccountTypeDto createAccountTypeDto);

    Integer deleteAccountTypeById(Integer id);

    AccountTypeDto findAccountTypeById(Integer id);

    AccountTypeDto updateAccountTypeById(Integer id, UpdateAccountTypeDto updateAccountTypeDto);


}
