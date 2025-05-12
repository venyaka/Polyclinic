package cushen_group.veniamin.polyclinic.service;

import cushen_group.veniamin.polyclinic.entity.Patient;

import java.util.List;

public interface PatientService {
    List<Patient> findAll();

    List<Patient> findAllByDoctorEmail(String doctorName);

    void save(Patient patient);

    Patient findById(Long id);
}
