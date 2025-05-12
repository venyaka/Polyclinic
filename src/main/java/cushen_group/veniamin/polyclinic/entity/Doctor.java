package cushen_group.veniamin.polyclinic.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "id")
public class Doctor extends User {

    @ManyToMany()
    @JoinTable(name = "doctor_patient", joinColumns = @JoinColumn(name = "patient_id"), inverseJoinColumns = @JoinColumn(name = "doctor_id"))
    private List<Patient> patients = new ArrayList<>();

    @NotBlank
    private String specialization;


}
