package cushen_group.veniamin.polyclinic.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "patients")
@Getter
@Setter
@NoArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;

    @Column(unique = true)
    private String policyNumber;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Discharge> discharges;

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Patient)) {
            return false;
        }

        Patient otherPatient = (Patient) obj;
        return this.getId().equals(otherPatient.getId());
    }
}

