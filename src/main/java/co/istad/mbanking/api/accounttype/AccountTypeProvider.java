package co.istad.mbanking.api.accounttype;

import org.apache.ibatis.jdbc.SQL;

public class AccountTypeProvider {
    // Dynamic SQL
    //!Select
    public String buildSelectSql(){
        return new SQL() {{
            //TODO:
            SELECT("*");
            FROM("account_types");
        }}.toString();
    }

    //! Delete
    public String buildDeleteSql(){
        return new SQL(){{
            DELETE_FROM("account_types")
                    .WHERE("id =#{id}");
        }}.toString();
    }
}
