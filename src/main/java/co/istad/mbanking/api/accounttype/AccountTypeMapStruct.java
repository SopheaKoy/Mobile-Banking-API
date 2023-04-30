package co.istad.mbanking.api.accounttype;

import co.istad.mbanking.api.accounttype.web.AccountTypeDto;
import co.istad.mbanking.api.accounttype.web.CreateAccountTypeDto;
import co.istad.mbanking.api.accounttype.web.UpdateAccountTypeDto;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface AccountTypeMapStruct {
    List<AccountTypeDto> toDtoList(List<AccountType> model);
    AccountTypeDto toDto(AccountType model);
    AccountType createAccountTypeToAccountDto(CreateAccountTypeDto createAccountType);

    AccountType updateAccountTypeToAccountDto(UpdateAccountTypeDto updateAccountType);


}
