package co.istad.mbanking.api.accounttype;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

@Mapper
public interface AccountTypeMapper {
    // Select All Account Types

    //Using annotation it requires two parameters
    @SelectProvider(type = AccountTypeProvider.class, method = "buildSelectSql")
    List<AccountType> selectAll();
}
