package cushen_group.veniamin.polyclinic.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ExtractUpdateReqDTO {

    private LocalDate date;

    @Size(max = 5000, message = "Количество символов не должно превышать 5000")
    private String diagnosis;

    @Size(max = 5000, message = "Количество символов не должно превышать 5000")
    private String prescription;

    @Email
    private String doctorEmail;

    @Email
    private String patientEmail;
}
