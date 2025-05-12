package cushen_group.veniamin.polyclinic.bean;

import cushen_group.veniamin.polyclinic.entity.Doctor;
import cushen_group.veniamin.polyclinic.entity.Patient;
import cushen_group.veniamin.polyclinic.entity.Role;
import cushen_group.veniamin.polyclinic.service.impl.DoctorServiceImpl;
import cushen_group.veniamin.polyclinic.service.impl.PatientServiceImpl;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Component("userManagementBean")
@ViewScoped
public class UserManagementBean implements Serializable {

    private List<Patient> patients;
    private List<Doctor> doctors;

    private Patient selectedPatient;
    private Doctor selectedDoctor;

    private Long selectedPatientId;
    private String newDoctorSpecialization;

    @Inject
    private PatientServiceImpl patientService;

    @Inject
    private DoctorServiceImpl doctorService;

    @PostConstruct
    public void init() {
        loadAllPatients();
        loadAllDoctors();
    }

    public void loadAllPatients() {
        patients = patientService.findAll();
    }

    public void loadAllDoctors() {
        doctors = doctorService.findAll();
    }

    public void initNewPatient() {
        selectedPatient = new Patient();
    }

    public void savePatient() {
        patientService.save(selectedPatient);
        loadAllPatients();
    }

    public void editPatient(Patient patient) {
        selectedPatient = patient;
    }

    public void initNewDoctor() {
        selectedPatientId = null;
        newDoctorSpecialization = "";
    }

    public void createDoctorFromPatient() {
        Patient basePatient = patientService.findById(selectedPatientId);
        if (basePatient != null) {
            Doctor doctor = new Doctor();
            doctor.setName(basePatient.getName());
            doctor.setSurname(basePatient.getSurname());
            doctor.setEmail(basePatient.getEmail());
            doctor.setPassword(basePatient.getPassword());
            doctor.setSpecialization(newDoctorSpecialization);
            doctor.getRoles().addAll(basePatient.getRoles());
            doctor.getRoles().add(Role.ROLE_DOCTOR);
            doctorService.save(doctor);
            loadAllDoctors();
        }
    }

    public void editDoctor(Doctor doctor) {
        selectedDoctor = doctor;
    }

    public void saveDoctor() {
        doctorService.save(selectedDoctor);
        loadAllDoctors();
    }
}
