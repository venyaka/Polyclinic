package cushen_group.veniamin.polyclinic.controller;

import cushen_group.veniamin.polyclinic.dto.request.UserAuthorizeReqDTO;
import cushen_group.veniamin.polyclinic.dto.response.TokenRespDTO;
import cushen_group.veniamin.polyclinic.repository.UserRepository;
import cushen_group.veniamin.polyclinic.service.AuthorizeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class AuthorizeRestController {

    private final AuthorizeService authorizeService;

    private final UserRepository userRepository;

    @RequestMapping(value = "/register/verification", method = { RequestMethod.GET, RequestMethod.POST })
    public void verificateUser(@RequestParam(required = true) String email,
                               @RequestParam(required = true) String token,
                               HttpServletRequest request,
                               HttpServletResponse response) throws IOException {
        authorizeService.verificateUser(email, token);

        response.sendRedirect("/login");
    }

//    @PostMapping("/login")
//    public ResponseEntity<TokenRespDTO> authorizeUser(@RequestBody UserAuthorizeReqDTO userAuthorizeDTO) {
//        return authorizeService.authorizeUser(userAuthorizeDTO);
//    }
}
