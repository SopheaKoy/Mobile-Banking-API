package co.istad.mbanking.api.user;

import co.istad.mbanking.api.auth.web.RegisterDto;
import co.istad.mbanking.api.user.web.CreateUserDto;
import co.istad.mbanking.api.user.web.UpdateUserDto;
import co.istad.mbanking.api.user.web.UserDto;
import com.github.pagehelper.PageInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapStruct {
    // MapStruct it means
    User createUerDtoUser(CreateUserDto createUserDto);

    UserDto userToUserDto (User user);
    User userDtoToUser(UserDto userDto);

    PageInfo<UserDto> userPageInfoToUserDtoPageInfo(PageInfo<User> userPageInfo);

    User updateUserDtoToUser(UpdateUserDto updateUserDto);

    User registerUserDtoUser(RegisterDto registerDto);


}
