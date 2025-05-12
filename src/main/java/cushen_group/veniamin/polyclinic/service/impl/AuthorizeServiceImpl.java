package cushen_group.veniamin.polyclinic.service.impl;

import cushen_group.veniamin.polyclinic.dto.request.RegisterReqDTO;
import cushen_group.veniamin.polyclinic.dto.request.UserAuthorizeReqDTO;
import cushen_group.veniamin.polyclinic.dto.response.TokenRespDTO;
import cushen_group.veniamin.polyclinic.entity.Patient;
import cushen_group.veniamin.polyclinic.entity.User;
import cushen_group.veniamin.polyclinic.exception.BadRequestException;
import cushen_group.veniamin.polyclinic.exception.NotFoundException;
import cushen_group.veniamin.polyclinic.exception.errors.AuthorizedError;
import cushen_group.veniamin.polyclinic.exception.errors.BadRequestError;
import cushen_group.veniamin.polyclinic.exception.errors.NotFoundError;
import cushen_group.veniamin.polyclinic.repository.PatientRepository;
import cushen_group.veniamin.polyclinic.repository.UserRepository;
import cushen_group.veniamin.polyclinic.service.AuthorizeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthorizeServiceImpl implements AuthorizeService {

    private final UserRepository userRepo;

    private final PatientRepository patientRepository;

    private final PasswordEncoder passwordEncoder;

    private final MailServiceImpl mailService;

    private final AuthenticationManager authenticationManager;

//    private final SessionServiceImpl sessionService;

    @Override
    public ResponseEntity<TokenRespDTO> authorizeUser(UserAuthorizeReqDTO userAuthorizeDTO) {
        System.out.println("authorizeUser_____");
        String userEmail = userAuthorizeDTO.getEmail();
        String userPassword = userAuthorizeDTO.getPassword();
        Optional<User> userOptional = userRepo.findByEmail(userEmail);

        if (userOptional.isEmpty()) {
            throw new BadCredentialsException(AuthorizedError.USER_WITH_THIS_EMAIL_NOT_FOUND.getMessage());
        }
        User user = userOptional.get();
        if (!passwordEncoder.matches(userPassword, user.getPassword())) {
            throw new BadCredentialsException(AuthorizedError.NOT_CORRECT_PASSWORD.getMessage());
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userAuthorizeDTO.getEmail(), userAuthorizeDTO.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
//        sessionService.saveNewSession(user.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public void registerUser(RegisterReqDTO registerDTO, HttpServletRequest request) {
        if (userRepo.findByEmail(registerDTO.getEmail()).isPresent()) {
            throw new BadRequestException(BadRequestError.USER_ALREADY_EXISTS);
        }

        Patient patient = new Patient();
        patient.setEmail(registerDTO.getEmail());
        patient.setName(registerDTO.getName());
        patient.setSurname(registerDTO.getSurname());
        patient.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        patient.setIsEmailVerificated(Boolean.FALSE);
        patient.setToken(generateValidatingToken());
        patient.setRoles(registerDTO.getRoles());
        patient.setDiagnosis(new ArrayList<>());
        patient.setDoctors(new ArrayList<>());

        patientRepository.save(patient);
//        userRepo.save(user);

        mailService.sendUserVerificationMail(patient, request);
    }

//
//    @Override
//    public void registerUser(RegisterReqDTO registerDTO, HttpServletRequest request) {
//        if (userRepo.findByEmail(registerDTO.getEmail()).isPresent()) {
//            throw new BadRequestException(BadRequestError.USER_ALREADY_EXISTS);
//        }
//        User user = new User();
//        user.setEmail(registerDTO.getEmail());
//        user.setName(registerDTO.getName());
//        user.setSurname(registerDTO.getSurname());
//        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
//        user.setIsEmailVerificated(Boolean.FALSE);
//        user.setToken(generateValidatingToken());
//        user.setRoles(registerDTO.getRoles());
//
//        Patient patient = (Patient) user;
//        patient.setDiagnosis(new ArrayList<>());
//        patient.setDoctors(new ArrayList<>());
//
//        patientRepository.save(patient);
//        userRepo.save(user);
//
//        mailService.sendUserVerificationMail(user, request);
//    }

    @Override
    public void verificateUser(String email, String verificationToken) {
        Optional<User> optionalUser = userRepo.findByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new NotFoundException(NotFoundError.USER_NOT_FOUND);
        }
        User user = optionalUser.get();

        if (user.getIsEmailVerificated()) {
            throw new BadRequestException(BadRequestError.USER_ALREADY_VERIFICATED);
        }

        if (null == user.getToken() || !user.getToken().equals(verificationToken)) {
            throw new BadRequestException(BadRequestError.NOT_CORRECT_VERIFICATION_CODE);
        }

        user.setToken(null);
        user.setIsEmailVerificated(Boolean.TRUE);
        userRepo.save(user);
    }

    @Override
    public void sendVerificationCode(String email, HttpServletRequest request) {
        User user = userRepo.findByEmail(email).orElseThrow(() -> new NotFoundException(NotFoundError.USER_NOT_FOUND));
        if (user.getIsEmailVerificated()) {
            throw new BadRequestException(BadRequestError.USER_ALREADY_VERIFICATED);
        }
        mailService.sendUserVerificationMail(user, request);
    }

    private String generateValidatingToken() {
        return RandomStringUtils.randomAlphanumeric(50);
    }

}
