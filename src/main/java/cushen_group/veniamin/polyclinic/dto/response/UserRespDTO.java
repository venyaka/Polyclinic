package cushen_group.veniamin.polyclinic.dto.response;

import cushen_group.veniamin.polyclinic.entity.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserRespDTO {

    private Long id;

    private String name;

    private String surname;

    private String email;

    private List<Role> roles;

}
