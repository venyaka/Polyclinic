package cushen_group.veniamin.polyclinic.service;

import cushen_group.veniamin.polyclinic.dto.request.RegisterReqDTO;
import cushen_group.veniamin.polyclinic.dto.request.UserAuthorizeReqDTO;
import cushen_group.veniamin.polyclinic.dto.response.TokenRespDTO;
import org.springframework.http.ResponseEntity;

import jakarta.servlet.http.HttpServletRequest;

public interface AuthorizeService {

    ResponseEntity<TokenRespDTO> authorizeUser(UserAuthorizeReqDTO userAuthorizeDTO);

    void registerUser(RegisterReqDTO registerDTO, HttpServletRequest request);

    void verificateUser(String email, String verificationToken);

    void sendVerificationCode(String email, HttpServletRequest request);

}
