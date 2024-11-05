package work.ngochuyen.spring.auth.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import work.ngochuyen.spring.auth.dto.reponse.UserDTO;
import work.ngochuyen.spring.auth.dto.reponse.UserInfoDTO;
import work.ngochuyen.spring.auth.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

//     @Mapping(target = "accessToken", ignore = true)
//     @Mapping(target = "accessTokenExpired", ignore = true)
     UserDTO toUserDTO(User user);

     UserInfoDTO toUserInfoDTO(User user);
}
