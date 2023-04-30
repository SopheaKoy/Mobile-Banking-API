package co.istad.mbanking.api.user;

import co.istad.mbanking.api.user.web.CreateUserDto;
import co.istad.mbanking.api.user.web.SearchByNameDto;
import co.istad.mbanking.api.user.web.UpdateUserDto;
import co.istad.mbanking.api.user.web.UserDto;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService {
    //!Functionally
    // ! return Dto to
    UserDto createNewUser(CreateUserDto createUserDto);

    UserDto findById(Integer id);

    Integer deleteUserById(Integer id);

    Integer updateIsDeletedUserStatus(Integer id, boolean status);

    PageInfo<UserDto> findAllUser(int page,int limit,String name);

    UserDto updateUserById(Integer id, UpdateUserDto updateUserDto);

    UserDto findStudentCardById(String stuId);

}
