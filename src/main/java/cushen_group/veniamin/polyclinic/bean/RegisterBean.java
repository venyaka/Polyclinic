package cushen_group.veniamin.polyclinic.bean;

import cushen_group.veniamin.polyclinic.dto.request.RegisterReqDTO;
import cushen_group.veniamin.polyclinic.service.impl.AuthorizeServiceImpl;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Getter
@Setter
@Component(value = "registerBean")
@ViewScoped
public class RegisterBean implements Serializable {

    @Autowired
    private AuthorizeServiceImpl authorizeService;

    private RegisterReqDTO registerDTO = new RegisterReqDTO();

    public void registerUser() {
        System.out.println("registerUser");
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        authorizeService.registerUser(registerDTO, request);
    }

    public void sendVerificationCode() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        System.out.println("sendVerificationCode");
        authorizeService.sendVerificationCode(registerDTO.getEmail(), request);
        System.out.println("sendVerificationCode__2");

    }
}