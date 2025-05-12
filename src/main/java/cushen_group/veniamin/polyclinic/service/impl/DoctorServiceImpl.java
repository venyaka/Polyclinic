package cushen_group.veniamin.polyclinic.service.impl;

import cushen_group.veniamin.polyclinic.entity.Doctor;
import cushen_group.veniamin.polyclinic.entity.Patient;
import cushen_group.veniamin.polyclinic.entity.Role;
import cushen_group.veniamin.polyclinic.repository.DoctorRepository;
import cushen_group.veniamin.polyclinic.repository.PatientRepository;
import cushen_group.veniamin.polyclinic.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    @Override
    public void save(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    @Override
    public void createDoctorFromPatient(Patient basePatient, String specialization) {
        Doctor doctor = new Doctor();
        doctor.setId(basePatient.getId());
        doctor.setSpecialization(specialization);
        doctor.getRoles().add(Role.ROLE_DOCTOR);
        doctorRepository.save(doctor);
    }
}
