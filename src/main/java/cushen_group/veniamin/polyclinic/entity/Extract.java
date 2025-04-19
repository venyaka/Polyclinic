package cushen_group.veniamin.polyclinic.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Extracts")
@Getter
@Setter
@NoArgsConstructor
public class Extract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @Lob
    @Column(length = 5000)
    private String diagnosis;

    @Lob
    @Column(length = 5000)
    private String prescription;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private User doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private User patient;

    @PrePersist
    public void prePersist() {
        if (date == null) {
            date = LocalDate.from(LocalDateTime.now());
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Extract)) {
            return false;
        }

        Extract otherExtract = (Extract) obj;
        return this.getId().equals(otherExtract.getId());
    }
}
