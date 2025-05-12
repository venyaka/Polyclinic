package cushen_group.veniamin.polyclinic.repository;

import cushen_group.veniamin.polyclinic.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>, JpaSpecificationExecutor<Patient> {
    @Query(value =
            "SELECT p.*\n" +
            "FROM patient p\n" +
            "JOIN doctor_patient dp ON p.id = dp.patient_id\n" +
            "JOIN doctor d ON dp.doctor_id = d.id\n" +
            "JOIN user u ON d.id = u.id\n" +
            "WHERE u.email = :doctorEmail;",
            nativeQuery = true
    )
    List<Patient> findAllByDoctorEmail(@Param("doctorEmail") String doctorEmail);
}
