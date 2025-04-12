package cushen_group.veniamin.polyclinic.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ChangePasswordReqDTO {
    @NotBlank
    private String oldPassword;

    @NotBlank
    @Size(min = 8, max = 24, message = "Размер пароля должен быть больше 8 и меньше 24")
    private String newPassword;
}
