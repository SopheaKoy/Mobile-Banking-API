package co.istad.mbanking.api.account;

import java.util.List;

public interface AccountService {
    List<AccountDto> findAll();
}
