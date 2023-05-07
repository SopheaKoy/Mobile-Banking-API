package co.istad.mbanking.api.account;

import org.apache.ibatis.jdbc.SQL;

public class AccountProvider {
    private final String tableName = "accounts";
    public String buildSelectProvider(){
        return new SQL(){{
            SELECT("*");
            FROM(tableName);
        }}.toString();
    }
}
