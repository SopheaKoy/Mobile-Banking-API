package co.istad.mbanking.api.account;

import co.istad.mbanking.api.account.web.AccountDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapStruct {
    List<AccountDto> findAll(List<Account> accounts);
}
