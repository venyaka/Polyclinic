package cushen_group.veniamin.polyclinic.dto.response;

import cushen_group.veniamin.polyclinic.dto.PatientDTO;
import cushen_group.veniamin.polyclinic.entity.Patient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DoctorCabinetRespDTO {
    @NotBlank
    @Pattern(regexp = "(?i)[a-zа-я]*", message = "Имя должно состоять из букв")
    private String name;

    @NotBlank
    @Pattern(regexp = "(?i)[a-zа-я]*", message = "Фамилия должна состоять из букв")
    private String surname;

    @Email
    private String email;

    @NotBlank
    private String specialization;

    private List<PatientDTO> patients = new ArrayList<>();
}
