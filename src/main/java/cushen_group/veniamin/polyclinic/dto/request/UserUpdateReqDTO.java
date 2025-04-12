package cushen_group.veniamin.polyclinic.dto.request;

import cushen_group.veniamin.polyclinic.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class UserUpdateReqDTO {

    @Pattern(regexp = "(?i)[a-zа-я]*", message = "Имя должно состоять из букв")
    private String name;

    @Pattern(regexp = "(?i)[a-zа-я]*", message = "Фамилия должна состоять из букв")
    private String surname;

    @Email
    private String email;

    @Size(max = 254, message = "Текст о себе не должен превышать 254 символов")
    private String aboutMe;

    private Long profileAvatarId;

    private Boolean deleteAvatar;

    private List<Role> roles;

    private MultipartFile avatar;
}
