package cushen_group.veniamin.polyclinic.controller;

import jakarta.faces.view.ViewScoped;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;


@Getter
@Setter
@Component(value = "homeBean")
@ViewScoped
public class HomeBean implements Serializable {
    public String getWelcomeMessage() {
        return "Добро пожаловать в систему!";
    }
}
