package co.istad.mbanking.api.account;

import co.istad.mbanking.api.account.web.AccountDto;

import java.util.List;

public interface AccountService {
    List<AccountDto> findAll();
}
