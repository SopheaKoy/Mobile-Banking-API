package co.istad.mbanking.api.useraccount;

import co.istad.mbanking.api.useraccount.web.UserAccountDto;

import java.util.List;

public interface UserAccountService {
    List<UserAccountDto> findAll();
}
