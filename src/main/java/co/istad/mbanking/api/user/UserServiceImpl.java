package co.istad.mbanking.api.user;

import co.istad.mbanking.api.user.web.CreateUserDto;
import co.istad.mbanking.api.user.web.SearchByNameDto;
import co.istad.mbanking.api.user.web.UpdateUserDto;
import co.istad.mbanking.api.user.web.UserDto;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserMapStruct userMapStruct;

    @Override
    public UserDto createNewUser(CreateUserDto createUserDto) {
        User user = userMapStruct.createUerDtoUser(createUserDto);
        userMapper.insert(user);
        log.info("User : "+user.getId());
        return this.findById(user.getId());
    }

    @Override
    public UserDto findById(Integer id) {
        User user = userMapper.selectById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with %d is not found",id)));
        return userMapStruct.userToUserDto(user);
    }

    @Override
    public Integer deleteUserById(Integer id) {
        boolean isExisted = userMapper.existsById(id);
        if(isExisted){
            userMapper.deleteById(id);
            return id;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User with %d is not found" ,id));
    }

    @Override
    public Integer updateIsDeletedUserStatus(Integer id, boolean status) {
        boolean isExisted = userMapper.existsById(id);
        if (isExisted){
            userMapper.updateIsDeletedById(id,status);
            return  id;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User with %d is not found" ,id));
    }

    @Override
    public PageInfo<UserDto> findAllUser(int page, int limit,String name) {
        //! Use page helper  required 2 parameters (page , limit)
        //! call reponsitory  and follow dependencies our.
        PageInfo<User> userPageInfo = PageHelper.startPage(page, limit)
                .doSelectPageInfo(()->userMapper.select(name));
        return userMapStruct.userPageInfoToUserDtoPageInfo(userPageInfo);
    }

    @Override
    public UserDto updateUserById(Integer id, UpdateUserDto updateUserDto) {
        User user;
        if(userMapper.existsById(id)){
            user = userMapStruct.updateUserDtoToUser(updateUserDto);
            user.setId(id);
            userMapper.updateById(user);
            return this.findById(id);
        }
        throw  new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User with %d not found..!"));
    }

    @Override
    public UserDto findStudentCardById(String stuId) {
        User user = userMapper.findCardById(stuId.toUpperCase()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with %s is not found", stuId)));
        log.info("Student card : " +user.getName());
        return userMapStruct.userToUserDto(user);
    }


}
