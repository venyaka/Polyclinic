package cushen_group.veniamin.polyclinic.bean;

import cushen_group.veniamin.polyclinic.dto.request.ExtractCreateReqDTO;
import cushen_group.veniamin.polyclinic.service.impl.ExtractServiceImpl;
import jakarta.faces.view.ViewScoped;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Getter
@Setter
@Component(value = "extractBean")
@ViewScoped
public class ExtractBean implements Serializable {

    @Autowired
    private ExtractServiceImpl extractService;

//    private Long extractId;
//
//    private String patientEmail;
//    private LocalDate date;
//    private String doctorEmail;
//    private String diagnosis;
//    private String prescription;
//    private List<String> reports = new ArrayList<>();
    ExtractCreateReqDTO extractCreateReqDTO = new ExtractCreateReqDTO();

    public void createExtract() {


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails userDetails) {
                extractCreateReqDTO.setDoctorEmail(userDetails.getUsername()); // или getEmail(), если ты его переопределил
            }
        }

//        extractCreateReqDTO.setDoctorEmail(doctorEmail);
//        extractCreateReqDTO.setPatientEmail(patientEmail);
//        extractCreateReqDTO.setDiagnosis(diagnosis);
//        extractCreateReqDTO.setPrescription(prescription);
        extractService.createExtract(extractCreateReqDTO);
    }

    public StreamedContent getExcelExport() {
        return extractService.getExcelExport(extractCreateReqDTO);
    }
}
