//package cushen_group.veniamin.polyclinic.bean;
//
//import cushen_group.veniamin.polyclinic.entity.Doctor;
//import cushen_group.veniamin.polyclinic.entity.Patient;
//import cushen_group.veniamin.polyclinic.service.impl.DoctorServiceImpl;
//import cushen_group.veniamin.polyclinic.service.impl.PatientServiceImpl;
//import cushen_group.veniamin.polyclinic.service.impl.UserServiceImpl;
//import jakarta.annotation.PostConstruct;
//import jakarta.faces.view.ViewScoped;
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.io.Serializable;
//import java.util.List;
//
//@Getter
//@Setter
//@Component("userManagementBean")
//@ViewScoped
//public class UserManagementBean implements Serializable {
//
//    @Autowired
//    private UserServiceImpl userService;
//
//    @Autowired
//    private DoctorServiceImpl doctorService;
//
//    @Autowired
//    private PatientServiceImpl patientService;
//
//    private List<Patient> patients;
//    private List<Doctor> doctors;
//    private List<Patient> allPatients;
//
//    private String doctorNameFilter;
//    private Patient selectedPatient;
//    private Doctor selectedDoctor;
//    private Long selectedPatientIdForDoctor;
//
//    @PostConstruct
//    public void init() {
//        loadData();
//    }
//
//    public void loadData() {
//        patients = patientService.findAll();
//        doctors = doctorService.findAll();
//        allPatients = patientService.findAll();
//    }
//
//    public void filterPatientsByDoctor() {
//        if (doctorNameFilter != null && !doctorNameFilter.trim().isEmpty()) {
//            patients = patientService.findAllByDoctorEmail(doctorNameFilter);
//        } else {
//            patients = patientService.findAll();
//        }
//    }
//
//    public void addNewPatient() {
//        patientService.save(selectedPatient);
//        selectedPatient = new Patient();
//        loadData();
//    }
//
//    public void updatePatient() {
//        patientService.save(selectedPatient);
//        selectedPatient = new Patient();
//        loadData();
//    }
//
//    public void addDoctorFromPatient() {
//        Patient base = patientService.findById(selectedPatientIdForDoctor);
//        doctorService.createDoctorFromPatient(base, selectedDoctor.getSpecialization());
//        selectedDoctor = new Doctor();
//        selectedPatientIdForDoctor = null;
//        loadData();
//    }
//
//    public void updateDoctor() {
//        doctorService.save(selectedDoctor);
//        selectedDoctor = new Doctor();
//        loadData();
//    }
//}
