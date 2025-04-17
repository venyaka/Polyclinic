package cushen_group.veniamin.polyclinic.controller;

import cushen_group.veniamin.polyclinic.entity.Role;
import jakarta.faces.view.ViewScoped;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Getter
@Setter
@Component(value = "registerBean")
@ViewScoped
public class RegisterBean implements Serializable {

    private String username;
    private String password;
    private String email;
    private Role role;

    public String register() {
        // Реализуй регистрацию и сохранение пользователя
        return "/views/login.xhtml?faces-redirect=true";
    }
}