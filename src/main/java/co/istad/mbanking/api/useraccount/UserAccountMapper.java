package co.istad.mbanking.api.useraccount;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

@Mapper
public  interface UserAccountMapper {
    @SelectProvider(type = UserAccountProvider.class , method = "buildSelectUserAccount")
    List<UserAccount> selectUserAccount();
}
