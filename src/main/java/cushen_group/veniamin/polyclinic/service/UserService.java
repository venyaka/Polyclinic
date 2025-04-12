package cushen_group.veniamin.polyclinic.service;

import cushen_group.veniamin.polyclinic.dto.ActionWithUserStatusResponseDTO;
import cushen_group.veniamin.polyclinic.dto.request.ChangePasswordReqDTO;
import cushen_group.veniamin.polyclinic.dto.request.UpdateCurrentUserReqDTO;
import cushen_group.veniamin.polyclinic.dto.request.UserCreateReqDTO;
import cushen_group.veniamin.polyclinic.dto.request.UserUpdateReqDTO;
import cushen_group.veniamin.polyclinic.dto.response.UserRespDTO;
import cushen_group.veniamin.polyclinic.entity.Role;
import cushen_group.veniamin.polyclinic.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;


public interface UserService extends UserDetailsService {

    UserRespDTO getCurrentUserInfo();

    UserRespDTO updateCurrentUser(UpdateCurrentUserReqDTO updateCurrentUserReqDTO, MultipartFile avatar);

    void changePassword(ChangePasswordReqDTO changePasswordReqDTO);

    UserRespDTO getUserById(Long id);

    ActionWithUserStatusResponseDTO deleteUserById(Long id);

    UserRespDTO createUser(UserCreateReqDTO userCreateReqDTO);

    UserRespDTO updateUser(UserUpdateReqDTO userUpdateReqDTO, Long userId);

    void logout();

    List<Role> getRoles();

    UserRespDTO getResponseDTO(User user);
}
