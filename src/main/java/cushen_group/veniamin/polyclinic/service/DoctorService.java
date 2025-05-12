package cushen_group.veniamin.polyclinic.service;

import cushen_group.veniamin.polyclinic.entity.Doctor;
import cushen_group.veniamin.polyclinic.entity.Patient;

import java.util.List;

public interface DoctorService {
    List<Doctor> findAll();

    void save(Doctor doctor);

    void createDoctorFromPatient(Patient basePatient, String specialization);
}
