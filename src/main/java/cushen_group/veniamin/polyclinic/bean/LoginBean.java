//package cushen_group.veniamin.polyclinic.bean;
//
//import cushen_group.veniamin.polyclinic.dto.request.UserAuthorizeReqDTO;
//import cushen_group.veniamin.polyclinic.dto.response.TokenRespDTO;
//import cushen_group.veniamin.polyclinic.service.AuthorizeService;
//import jakarta.faces.context.ExternalContext;
//import jakarta.faces.context.FacesContext;
//import jakarta.faces.view.ViewScoped;
//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotBlank;
//import lombok.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//
//import java.io.Serializable;
//
//@Getter
//@Setter
//@Component(value = "loginBean")
//@ViewScoped
//public class LoginBean implements Serializable {
//
//    @Autowired
//    private AuthorizeService authorizeService;
//
//    private UserAuthorizeReqDTO userAuthorizeDTO = new UserAuthorizeReqDTO();;
//
//    public ResponseEntity<TokenRespDTO> login() {
//        System.out.println("login login loginloginlogin login");
//        System.out.println("login" + userAuthorizeDTO.getEmail() + userAuthorizeDTO.getPassword());
//        return authorizeService.authorizeUser(userAuthorizeDTO);
//
//    }
//}
