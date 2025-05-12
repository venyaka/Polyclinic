package cushen_group.veniamin.polyclinic.controller.web;


import cushen_group.veniamin.polyclinic.dto.request.RegisterReqDTO;
import cushen_group.veniamin.polyclinic.dto.request.UserAuthorizeReqDTO;
import cushen_group.veniamin.polyclinic.exception.BadRequestException;
import cushen_group.veniamin.polyclinic.exception.errors.BadRequestError;
import cushen_group.veniamin.polyclinic.service.AuthorizeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class AuthorizeController {

    private final AuthorizeService authorizeService;

//    @PostMapping("/login")
//    public String login(@RequestParam(required = true) Boolean verified) {
//        if (!verified) {
//            throw new BadRequestException(BadRequestError.USER_NOT_VERIFICATED);
//        }
//        return "redirect:/home";
//    }

//
//    @GetMapping("/register")
//    public String registerForm(Model model) {
//        model.addAttribute("registerDTO", new RegisterReqDTO());
//        return "register"; // templates/register.html
//    }
//
//    @PostMapping("/register")
//    public String registerUser(@ModelAttribute("registerDTO") @Valid RegisterReqDTO registerDTO,
//                               HttpServletRequest request,
//                               Model model) {
//        try {
//            authorizeService.registerUser(registerDTO, request);
//            return "redirect:/authorize/login?registered";
//        } catch (Exception e) {
//            model.addAttribute("error", "Ошибка регистрации: " + e.getMessage());
//            return "register";
//        }
//    }
//
//    @RequestMapping(value = "/verification", method = { RequestMethod.GET, RequestMethod.POST })
//    public String verificateUser(@RequestParam String email,
//                                 @RequestParam String token,
//                                 HttpServletRequest request,
//                                 Model model) {
//        try {
//            authorizeService.verificateUser(email, token);
//            return "redirect:/login";
//        } catch (Exception e) {
//            model.addAttribute("error", "Ошибка подтверждения: " + e.getMessage());
//            return "verification";
//        }
//    }
}
