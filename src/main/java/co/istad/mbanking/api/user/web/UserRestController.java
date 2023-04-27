package co.istad.mbanking.api.user.web;

import co.istad.mbanking.api.user.UserService;
import co.istad.mbanking.api.user.web.CreateUserDto;
import co.istad.mbanking.base.BaseRest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/users")
@Slf4j
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;

    @PostMapping
    public BaseRest<?> createNewUser(@RequestBody @Valid CreateUserDto createUserDto){
        UserDto userDto = userService.createNewUser(createUserDto);
        return BaseRest.builder()
                .code(HttpStatus.OK.value())
               .status(true)
               .message("User has been create successfully.")
               .data(userDto).timestamp(LocalDateTime.now())

               .build();
    }

    @GetMapping("/{id}")
    public BaseRest<?> findUserById(@PathVariable Integer id){
        UserDto userDto =userService.findById(id);
        return BaseRest.builder()
                .code(HttpStatus.OK.value())
                .status(true)
                .message("User has been found.")
                .data(userDto)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @DeleteMapping("/{id}")
    public BaseRest<?> deleteUserById(@PathVariable Integer id){
        Integer deletedId= userService.deleteUserById(id);
        return BaseRest.builder()
                .code(HttpStatus.OK.value())
                .status(true)
                .message("User has been delete successfully.")
                .data(deletedId)
                .timestamp(LocalDateTime.now())
                .build();
    }


    @PutMapping("/{id}")
    public BaseRest<?> updateIsDeletedStatusById(@PathVariable Integer id, @RequestBody IsDeletedDto isDeletedDto){
        Integer deletedId= userService.updateIsDeletedUserStatus(id, isDeletedDto.status());
        return BaseRest.builder()
                .code(HttpStatus.OK.value())
                .status(true)
                .message("User has been delete successfully.")
                .data(deletedId)
                .timestamp(LocalDateTime.now())
                .build();
    }

}
