package co.istad.mbanking.api.accounttype;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AccountTypeMapper {
    // Select All Account Types
    //! Repository  = DAO = Mapper
    //Using annotation it requires two parameters
    @SelectProvider(type = AccountTypeProvider.class, method = "buildSelectSql")
    List<AccountType> selectAll();

    @DeleteProvider(type = AccountTypeProvider.class, method = "buildDeleteSql")
    List<AccountType> deleteById();

}
