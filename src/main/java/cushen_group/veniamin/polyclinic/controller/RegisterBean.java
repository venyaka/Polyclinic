package cushen_group.veniamin.polyclinic.controller;

import cushen_group.veniamin.polyclinic.dto.request.RegisterReqDTO;
import cushen_group.veniamin.polyclinic.entity.Role;
import cushen_group.veniamin.polyclinic.service.impl.AuthorizeServiceImpl;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Component(value = "registerBean")
@ViewScoped
public class RegisterBean implements Serializable {

    @Autowired
    private AuthorizeServiceImpl authorizeService;

    private String name;

    private String surname;

    private String password;

    private String email;

    private Role role;

    public void registerUser() {
        System.out.println("registerUser");
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        System.out.println(request);

        RegisterReqDTO registerDTO = new RegisterReqDTO();
        registerDTO.setEmail(email);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        registerDTO.setRoles(roles);
        registerDTO.setName(name);
        registerDTO.setSurname(surname);
        registerDTO.setPassword(password);
        authorizeService.registerUser(registerDTO, request);
        System.out.println("registerUser");
    }
    public void sendVerificationCode() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        System.out.println("sendVerificationCode");
        authorizeService.sendVerificationCode(email, request);
        System.out.println("sendVerificationCode__2");

    }
}