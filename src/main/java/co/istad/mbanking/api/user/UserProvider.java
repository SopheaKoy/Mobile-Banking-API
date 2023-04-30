package co.istad.mbanking.api.user;


import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.jdbc.SQL;

public class UserProvider {
    private static final String tableName = "users";
    @Result(column = "is_StudentJi")
    public String buildInsertSql(){
        return new SQL(){{
            INSERT_INTO(tableName);
            VALUES("name", "#{u.name}");
            VALUES("gender", "#{u.gender}");
            VALUES("one_signal_id", "#{u.oneSignalId}");
            VALUES("student_card_id", "#{u.studentCardId}");
            VALUES("is_student", "#{u.isStudent}");
            VALUES("is_deleted", "FALSE");
        }}.toString();

    }
    public String buildSelectById(){
        return new SQL(){{
            SELECT("*");
            FROM(tableName);
            WHERE("id=#{id}","is_deleted = FALSE");
        }}.toString();
    }

    public String buildDeleteById(){
        return new SQL(){{
            DELETE_FROM(tableName);
            WHERE("id=#{id}");
        }}.toString();
    }

    public String buildUpdateIsDeletedByIdSql(){
        return new SQL(){{
            UPDATE(tableName);
            SET("is_deleted = #{status}");
            WHERE("id=#{id}");
        }}.toString();
    }

    public String buildSelectNameSql(){
        return new SQL(){{
            SELECT("*");
            FROM(tableName);
            WHERE("name ILIKE '%' || #{name} || '%'");
            ORDER_BY("id ASC");
        }}.toString();
    }

    public String buildUpdateByIdSql(){
        return  new SQL(){{
            UPDATE(tableName);
            SET("name = #{u.name}");
            SET("gender = #{u.gender}");
            WHERE("id = #{u.id}");
        }}.toString();
    }
    public String buildFindCardByIdSql(){
        return new  SQL(){{
            SELECT("*");
            FROM(tableName);
            WHERE("student_card_id =#{studentCardId}");
        }}.toString();
    }
}
