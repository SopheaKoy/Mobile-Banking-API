package co.istad.mbanking.api.accounttype;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AccountTypeMapper {
    // Select All User_Account Types
    //! Repository  = DAO = Mapper
    //Using annotation it requires two parameters
    @SelectProvider(type = AccountTypeProvider.class, method = "buildSelectByNameSql")
    List<AccountType> selectAll();

    @SelectProvider(type = AccountTypeProvider.class, method = "buildSelectSql")
    List<AccountType> select();

    @Select("SELECT EXISTS (SELECT * FROM account_types WHERE id = #{id})")
    boolean existsById(@Param("id") Integer id);

    @InsertProvider(type = AccountTypeProvider.class, method = "buildInsertSql")
    @Options(useGeneratedKeys = true , keyColumn = "id", keyProperty = "id")
    void insert(@Param("a") AccountType accountType);

    @DeleteProvider(type = AccountTypeProvider.class, method = "buildDeleteSql")
    void deleteById(@Param("id") Integer id);

    @SelectProvider(type = AccountTypeProvider.class, method = "buildSelectByIdSql")
    @Result(column="id" , property = "id")
    Optional<AccountType> selectById(@Param("id") Integer id);

    @UpdateProvider(type = AccountTypeProvider.class, method = "buildUpdateAccountTypeSql")
    void updateById(@Param("a") AccountType accountType);

}
