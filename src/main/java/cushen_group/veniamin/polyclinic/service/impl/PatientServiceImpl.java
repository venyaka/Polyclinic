package cushen_group.veniamin.polyclinic.service.impl;

import cushen_group.veniamin.polyclinic.entity.Patient;
import cushen_group.veniamin.polyclinic.repository.DoctorRepository;
import cushen_group.veniamin.polyclinic.repository.PatientRepository;
import cushen_group.veniamin.polyclinic.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public List<Patient> findAllByDoctorEmail(String doctorEmail) {

        return patientRepository.findAllByDoctorEmail(doctorEmail);
    }

    @Override
    public void save(Patient patient) {
        patient.setPassword(passwordEncoder.encode(patient.getPassword()));
        patientRepository.save(patient);
    }

    @Override
    public Patient findById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }
}