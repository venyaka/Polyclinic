package cushen_group.veniamin.polyclinic.controller;

import cushen_group.veniamin.polyclinic.repository.UserRepository;
import cushen_group.veniamin.polyclinic.service.AuthorizeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/register")
public class AuthorizeController {

    private final AuthorizeService authorizeService;

    private final UserRepository userRepository;


    @RequestMapping(value = "/verification", method = { RequestMethod.GET, RequestMethod.POST })
    public void verificateUser(@RequestParam(required = true) String email,
                               @RequestParam(required = true) String token,
                               HttpServletRequest request,
                               HttpServletResponse response) throws IOException {
        authorizeService.verificateUser(email, token);

        response.sendRedirect("/login?verified=true");

    }

//    @PostMapping("/login")
//    public ResponseEntity<TokenRespDTO> authorizeUser(@Valid @RequestBody UserAuthorizeReqDTO userAuthorizeDTO) {
//        return authorizeService.authorizeUser(userAuthorizeDTO);
//    }
}
