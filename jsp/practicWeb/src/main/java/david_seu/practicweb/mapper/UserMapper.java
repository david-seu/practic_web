package david_seu.practicweb.mapper;

import david_seu.practicweb.dto.UserDto;
import david_seu.practicweb.model.User;

public class UserMapper {

    public static UserDto toUserDto(User user) {
        return new UserDto(user.getId(), user.getUsername(), user.getPassword());
    }


    public static User toUser(UserDto userDto) {
        return new User(userDto.getId(), userDto.getUsername(), userDto.getPassword());
    }
}
