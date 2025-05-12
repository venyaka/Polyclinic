package cushen_group.veniamin.polyclinic.service.impl;

import cushen_group.veniamin.polyclinic.dto.request.UserUpdateReqDTO;
import cushen_group.veniamin.polyclinic.dto.response.UserRespDTO;
import cushen_group.veniamin.polyclinic.entity.Role;
import cushen_group.veniamin.polyclinic.entity.User;
import cushen_group.veniamin.polyclinic.exception.BadRequestException;
import cushen_group.veniamin.polyclinic.exception.NotFoundException;
import cushen_group.veniamin.polyclinic.exception.errors.BadRequestError;
import cushen_group.veniamin.polyclinic.exception.errors.NotFoundError;
import cushen_group.veniamin.polyclinic.repository.UserRepository;
import cushen_group.veniamin.polyclinic.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername " + "email " + email);
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new NotFoundException(NotFoundError.USER_NOT_FOUND));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<UserRespDTO> getAllUsersDTO() {
        List<UserRespDTO> userDTOS = this.getAllUsers().stream().map(this::getResponseDTO).toList();

        return userDTOS;
    }

    @Override
    public boolean deleteUserById(Long id) {
        userRepository.deleteById(id);
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new NotFoundException(NotFoundError.USER_NOT_FOUND);
        }
        return optionalUser.get();
    }

    @Transactional
    public void assignRole(Long userId, Role role) {
        System.out.println("assignRole()  ----------------------START--------------------");

        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            throw new NotFoundException(NotFoundError.USER_NOT_FOUND);
        }
        System.out.println("assignRole()  ----------------------2--------------------");

        User user = optionalUser.get();
        user.setRoles(Set.of(role));

        userRepository.save(user);

    }

    public boolean hasRole(UserRespDTO user, String role) {
        return user.getRoles().stream()
                .anyMatch(n -> n.getAuthority().equals(role));
    }
//
//    @Override
//    public User createUser(UserCreateReqDTO userCreateReqDTO) {
//        Optional<User> optionalUser = userRepository.findByEmail(userCreateReqDTO.getEmail());
//        if (optionalUser.isPresent()) {
//            throw new BadRequestException(BadRequestError.USER_ALREADY_EXISTS);
//        }
//
//        User user = new User();
//        user.setName(userCreateReqDTO.getName());
//        user.setSurname(userCreateReqDTO.getSurname());
//        user.setEmail(userCreateReqDTO.getEmail());
//        user.setRoles((Set<Role>) userCreateReqDTO.getRoles());
//        user.setPassword(passwordEncoder.encode("12345Qazdevelop"));
//
//        if (null != userCreateReqDTO.getRoles() && !userCreateReqDTO.getRoles().isEmpty()) {
//            user.setRoles(userCreateReqDTO.getRoles().stream().collect(Collectors.toSet()));
//        } else {
//            user.setRoles((Set<Role>) userCreateReqDTO.getRoles());
//        }
//
//        userRepository.save(user);
//
//        return user;
//    }

    @Override
    public User updateUser(UserUpdateReqDTO userUpdateReqDTO, Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new NotFoundException(NotFoundError.USER_NOT_FOUND);
        }
        User user = userOptional.get();
        checkUserUpdateCorrectData(user, userUpdateReqDTO);

        if (null != userUpdateReqDTO.getRoles() && !userUpdateReqDTO.getRoles().isEmpty()) {
            user.setRoles(userUpdateReqDTO.getRoles().stream().collect(Collectors.toSet()));
        }

        userRepository.save(user);

        return user;
    }


    @Override
    public List<Role> getRoles() {
        return Arrays.stream(Role.values()).collect(Collectors.toList());
    }


    @Override
    public UserRespDTO getResponseDTO(User user) {
        UserRespDTO userRespDTO = new UserRespDTO();

        userRespDTO.setEmail(user.getEmail());
        userRespDTO.setId(user.getId());
        userRespDTO.setName(user.getName());
        userRespDTO.setSurname(user.getSurname());

        userRespDTO.setRoles(user.getRoles().stream().collect(Collectors.toList()));
        return userRespDTO;
    }

    private void checkUserUpdateCorrectData(User user, UserUpdateReqDTO userUpdateReqDTO) {
        if (null != userUpdateReqDTO.getRoles() && userUpdateReqDTO.getRoles().isEmpty()) {
            throw new BadRequestException(BadRequestError.NOT_CORRECT_REQUEST_BODY_DATA);
        }
    }

    private User getCurrentUser(){
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(userEmail).get();
    }

}
