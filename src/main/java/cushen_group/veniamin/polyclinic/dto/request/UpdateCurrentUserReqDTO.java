package cushen_group.veniamin.polyclinic.dto.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UpdateCurrentUserReqDTO {
    @Pattern(regexp = "(?i)[a-zа-я]*", message = "Имя должно состоять из букв")
    private String firstName;

    @Pattern(regexp = "(?i)[a-zа-я]*", message = "Фамилия должна состоять из букв")
    private String lastName;
    
}
