package cushen_group.veniamin.polyclinic.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ExtractCreateReqDTO {

    private LocalDate date;

    @Email
    private String doctorEmail;

    @Email
    private String patientEmail;

    @Size(max = 5000, message = "Количество символов не должно превышать 5000")
    private String diagnosis;

    @Size(max = 5000, message = "Количество символов не должно превышать 5000")
    private String prescription;
}
