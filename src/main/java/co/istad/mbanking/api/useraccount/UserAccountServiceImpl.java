package co.istad.mbanking.api.useraccount;

import co.istad.mbanking.api.useraccount.web.UserAccountDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserAccountServiceImpl implements UserAccountService{
    private final UserAccountMapper userAccountMapper;
    private final UserAccountMapStruct userAccountMapStruct;

    @Override
    public List<UserAccountDto> findAll() {
        List<UserAccount> userAccounts = userAccountMapper.selectUserAccount();
        log.info("UserAccount : " +userAccounts);
        return userAccountMapStruct.toDtoList(userAccounts);
    }
}
