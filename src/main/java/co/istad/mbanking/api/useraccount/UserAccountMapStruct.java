package co.istad.mbanking.api.useraccount;

import co.istad.mbanking.api.accounttype.AccountType;
import co.istad.mbanking.api.accounttype.web.AccountTypeDto;
import co.istad.mbanking.api.useraccount.web.UserAccountDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserAccountMapStruct {
    UserAccount toDto(UserAccountDto userAccountDto);

    List<UserAccountDto> toDtoList(List<UserAccount> userAccounts);

}
