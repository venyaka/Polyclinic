package cushen_group.veniamin.polyclinic.dto.request;

import cushen_group.veniamin.polyclinic.entity.Role;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
public class UserCreateReqDTO {

    @NotBlank
    @Pattern(regexp = "[A-Za-zА-Яа-я]*", message = "Имя должно состоять из букв")
    private String name;

    @NotBlank
    @Pattern(regexp = "[A-Za-zА-Яа-я]*", message = "Фамилия должна состоять из букв")
    private String surname;

    @Email
    private String email;

    @NotEmpty
    private List<Role> roles;
}
