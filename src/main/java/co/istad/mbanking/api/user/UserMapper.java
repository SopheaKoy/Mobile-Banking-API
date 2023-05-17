package co.istad.mbanking.api.user;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface UserMapper {
    // Insert
    @InsertProvider(type = UserProvider.class , method = "buildInsertSql")
    @Options(useGeneratedKeys = true , keyColumn = "id", keyProperty = "id")
    void insert(@Param("u") User user);

    @SelectProvider(type = UserProvider.class ,method = "buildSelectById")
    @Results(id ="userResultMap", value = {
            @Result(column="student_card_id", property = "studentCardId"),
            @Result(column="is_student" , property = "isStudent")
    })
    Optional<User> selectById(@Param("id") Integer Id);


    @Select("SELECT EXISTS (SELECT * FROM users WHERE id = #{id})")
    boolean existsById(@Param("id") Integer id);

    @DeleteProvider(type = UserProvider.class, method="buildDeleteById")
    void deleteById(@Param("id") Integer id);

    @UpdateProvider(type = UserProvider.class, method = "buildUpdateIsDeletedByIdSql")
    void updateIsDeletedById(@Param("id") Integer id,@Param("status") boolean status);

    @SelectProvider(type = UserProvider.class, method="buildSelectNameSql")
    @ResultMap("userResultMap")
    List<User> select(String name);

    @UpdateProvider(type = UserProvider.class, method = "buildUpdateByIdSql")
    void updateById(@Param("u") User user);

    @SelectProvider(type = UserProvider.class, method="buildFindCardByIdSql")
    Optional<User> findCardById(String studentCardId);

    @Select("SELECT EXISTS(SELECT * FROM users WHERE email = #{email})")
    boolean existsByEmail(String email);

    @Select("SELECT EXISTS(SELECT * FROM roles WHERE id = #{roleId})")
    boolean checkRoleId(Integer roleId);

}
