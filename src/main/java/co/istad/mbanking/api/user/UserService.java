package co.istad.mbanking.api.user;

import co.istad.mbanking.api.user.web.CreateUserDto;
import co.istad.mbanking.api.user.web.UserDto;

public interface UserService {
    //!Functionally
    // ! return Dto to
    UserDto createNewUser(CreateUserDto createUserDto);
}