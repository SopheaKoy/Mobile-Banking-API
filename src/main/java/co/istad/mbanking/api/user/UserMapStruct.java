package co.istad.mbanking.api.user;

import co.istad.mbanking.api.user.web.CreateUserDto;
import co.istad.mbanking.api.user.web.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapStruct {
    // MapStruct it means
    User createUerDtoUser(CreateUserDto createUserDto);

    UserDto userToUserDto (User user);
    User userDtoToUser(UserDto userDto);
}
