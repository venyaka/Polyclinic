package cushen_group.veniamin.polyclinic.bean;

import cushen_group.veniamin.polyclinic.dto.PatientDTO;
import cushen_group.veniamin.polyclinic.dto.response.DoctorCabinetRespDTO;
import cushen_group.veniamin.polyclinic.entity.Doctor;
import cushen_group.veniamin.polyclinic.entity.User;
import cushen_group.veniamin.polyclinic.exception.NotFoundException;
import cushen_group.veniamin.polyclinic.exception.errors.NotFoundError;
import cushen_group.veniamin.polyclinic.repository.DoctorRepository;
import cushen_group.veniamin.polyclinic.service.impl.AuthorizeServiceImpl;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Component(value = "doctorBean")
@ViewScoped
public class DoctorBean {

    @Autowired
    private DoctorRepository doctorRepository;

    private DoctorCabinetRespDTO doctorCabinetDTO = new DoctorCabinetRespDTO();


    @PostConstruct
    public void init() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(userEmail + " userEmailuserEmailuserEmailuserEmailuserEmail");
        Optional<Doctor> optionalDoctor = doctorRepository.findByEmail(userEmail);
        if (optionalDoctor.isEmpty()) {
            throw new NotFoundException(NotFoundError.USER_NOT_FOUND);
        }
        Doctor doctor = optionalDoctor.get();

        doctorCabinetDTO.setName(doctor.getName());
        doctorCabinetDTO.setSurname(doctor.getSurname());
        doctorCabinetDTO.setEmail(doctor.getEmail());
        doctorCabinetDTO.setSpecialization(doctor.getSpecialization());
        doctorCabinetDTO.setPatients(doctor.getPatients().stream().map(patient -> new PatientDTO(patient.getName(), patient.getDiagnosis())).toList());

//        doctorCabinetDTO.setName("Иван Петров");
//        doctorCabinetDTO.setSpecialization("Терапевт");

//        List<PatientDTO> patientList = new ArrayList<>();
//        patientList.add(new PatientDTO("Сидоров Алексей", "1990-05-12", "ОРВИ"));
//        patientList.add(new PatientDTO("Иванова Мария", "1985-02-22", "Грипп"));

//        doctorCabinetDTO.setPatients(patientList);
    }

}
