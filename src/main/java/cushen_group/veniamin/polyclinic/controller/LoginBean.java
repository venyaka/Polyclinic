package cushen_group.veniamin.polyclinic.controller;

import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Serializable;

@Getter
@Setter
@Component(value = "loginBean")
@ViewScoped
public class LoginBean implements Serializable {
    private String email;
    private String password;

    public String login() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(ec.getRequestContextPath() + "/login?email=" + email + "&password=" + password);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
