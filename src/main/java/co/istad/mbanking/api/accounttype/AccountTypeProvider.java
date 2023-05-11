package co.istad.mbanking.api.accounttype;

import org.apache.ibatis.jdbc.SQL;

public class AccountTypeProvider {
    // Dynamic SQL
    //!Select
    private final String tableName = "account_types";

    public String buildSelectSql() {
        return new SQL() {{
            SELECT("*");
            FROM(tableName);
        }}.toString();
    }

    //#Insert
    public String buildInsertSql() {
        return new SQL() {{
            INSERT_INTO(tableName);
            VALUES("name", "#{a.name}");
        }}.toString();
    }

    //!Delete AccountType
    public String buildDeleteSql() {
        return new SQL() {{
            DELETE_FROM(tableName);
            WHERE("id =#{id}");
        }}.toString();
    }
    public String buildSelectByIdSql(){
        return new SQL(){{
            SELECT("*");
            FROM(tableName);
            WHERE("id =#{id}");
        }}.toString();
    }
    public String buildUpdateAccountTypeSql(){
        return new SQL(){{
            UPDATE(tableName);
            SET("name=#{a.name}");
            WHERE("id =#{a.id}");
        }}.toString();
    }
    public String buildSelectByNameSql(){
        return new SQL(){{}}.toString();
    }
}