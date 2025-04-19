package cushen_group.veniamin.polyclinic.service;

import cushen_group.veniamin.polyclinic.dto.request.UserUpdateReqDTO;
import cushen_group.veniamin.polyclinic.dto.response.UserRespDTO;
import cushen_group.veniamin.polyclinic.entity.Role;
import cushen_group.veniamin.polyclinic.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface UserService extends UserDetailsService {

    UserDetails loadUserByUsername(String username);

    List<User> getAllUsers();

    User getUserById(Long id);

//    User createUser(UserCreateReqDTO userCreateReqDTO);

    User updateUser(UserUpdateReqDTO userUpdateReqDTO, Long userId);

    boolean deleteUserById(Long userId);

    List<Role> getRoles();

    UserRespDTO getResponseDTO(User user);

}
