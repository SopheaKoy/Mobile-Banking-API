package co.istad.mbanking.api.account;

import co.istad.mbanking.api.accounttype.AccountType;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AccountMapper {
    @SelectProvider(type = AccountProvider.class, method = "buildSelectProvider")
    @Results(id = "accountResults", value ={
        @Result(column = "account_type", property = "account_type" ,one = @One(select = "selectAccountType"))
    })
    List<Account> findAllAccount();

    @Select("SELECT * FROM account_types WHERE id =#{account_type}")
    AccountType selectAccountType(Integer account_type);
}
