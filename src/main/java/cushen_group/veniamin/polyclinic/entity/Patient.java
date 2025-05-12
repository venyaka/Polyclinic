package cushen_group.veniamin.polyclinic.entity;

import jakarta.persistence.*;
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
public class Patient extends User {

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Extract> extracts = new ArrayList<>();

    @ManyToMany()
    @JoinTable(name = "doctor_patient", joinColumns = @JoinColumn(name = "doctor_id"), inverseJoinColumns = @JoinColumn(name = "patient_id"))
    private List<Doctor> doctors = new ArrayList<>();

    @CollectionTable(name = "patient_diagnosis", joinColumns = @JoinColumn(name = "id"))
    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @Column(name = "diagnosis")
    private List<String> diagnosis = new ArrayList<>();

}
