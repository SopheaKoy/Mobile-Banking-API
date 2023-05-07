package co.istad.mbanking.api.account;

import co.istad.mbanking.api.account.web.AccountDto;
import co.istad.mbanking.api.accounttype.AccountType;

import java.util.List;

public interface AccountService {
    List<AccountDto> findAll(AccountType accountType);
}
