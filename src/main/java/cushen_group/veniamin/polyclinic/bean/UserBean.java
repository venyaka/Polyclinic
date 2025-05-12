package cushen_group.veniamin.polyclinic.bean;

import cushen_group.veniamin.polyclinic.entity.Role;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Component(value = "userBean")
@SessionScoped
public class UserBean implements Serializable {

//    private String name;
//    private Set<Role> roles; // например: "USER", "DOCTOR", "ADMIN"

    public boolean hasRole(String role) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<String> roles = auth.getAuthorities().stream().map(Object::toString).toList();
        return roles.contains(role);
    }
}
