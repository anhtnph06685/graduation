package fpoly.graduation.project.service.mapper;

import fpoly.graduation.project.domain.Classes;
import fpoly.graduation.project.domain.User;
import fpoly.graduation.project.service.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses = {})
public interface UserMapper extends EntityMapper<UserDTO, User>{

    default User fromId(Integer id){
        if(id == null){
            return null;
        }
        User user = new User();
        user.setId(id);
        return user;
    }
}
