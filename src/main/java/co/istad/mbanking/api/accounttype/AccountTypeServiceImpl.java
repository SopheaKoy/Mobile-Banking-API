package co.istad.mbanking.api.accounttype;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountTypeServiceImpl implements AccountTypeService{
    private final AccountTypeMapper accountTypeMapper;
    private final AccountTypeMapStruct accountTypeMapStruct;

    @Override
    public List<AccountTypeDto> findAll() {
        List<AccountType> accountTypes = accountTypeMapper.selectAll();
//        List<AccountTypeDto> accountTypeDto = accountTypes.stream()
//                .map(accountType -> new AccountTypeDto(accountType.getName())).toList();
        System.out.println(accountTypes.get(0).getName());
        return accountTypeMapStruct.toDtoList(accountTypes);
    }

    @Override
    public List<AccountTypeDto> delete() {
        List<AccountType> accountTypes = accountTypeMapper.deleteById();
        return accountTypeMapStruct.toDtoList(accountTypes);
    }
}
