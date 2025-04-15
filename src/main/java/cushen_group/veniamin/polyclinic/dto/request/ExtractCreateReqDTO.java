package cushen_group.veniamin.polyclinic.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ExtractCreateReqDTO {

    @NotBlank
    private LocalDate date;

    @Size(max = 5000, message = "Количество символов не должно превышать 5000")
    private String text;

    @Email
    private String doctorEmail;

    @Email
    private String patientEmail;
}
