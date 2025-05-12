package cushen_group.veniamin.polyclinic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PatientDTO {
    private String fullName;
    private List<String> diagnosis;
}