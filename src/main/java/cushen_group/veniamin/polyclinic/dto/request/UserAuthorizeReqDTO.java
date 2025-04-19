package cushen_group.veniamin.polyclinic.dto.request;

import lombok.Data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Data
public class UserAuthorizeReqDTO {

    @Email
    private String email;

    @NotBlank
    private String password;
}
