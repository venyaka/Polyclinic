package cushen_group.veniamin.polyclinic.controller;

import jakarta.faces.view.ViewScoped;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Getter
@Setter
@Component(value = "homeBean")
@ViewScoped
public class HomeBean {
    private String text;
}
