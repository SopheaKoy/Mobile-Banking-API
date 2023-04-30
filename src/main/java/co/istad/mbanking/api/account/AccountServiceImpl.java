package co.istad.mbanking.api.account;

import co.istad.mbanking.api.account.web.AccountDto;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{
    private final Account account;
    private final AccountMapper accountMapper;

    @Override
    public List<AccountDto> findAll() {

        return null;
    }
}
