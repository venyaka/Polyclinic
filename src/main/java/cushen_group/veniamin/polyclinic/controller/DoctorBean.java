package cushen_group.veniamin.polyclinic.controller;

import cushen_group.veniamin.polyclinic.dto.response.UserRespDTO;
import jakarta.faces.view.ViewScoped;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Component(value = "doctorBean")
@ViewScoped
public class DoctorBean implements Serializable {

    private String patientName;
    private UserRespDTO patient;
    private String diagnosis;
    private String prescription;
    private List<String> reports = new ArrayList<>();

    public void createExtract() {
        String report = "Выписка для " + patientName + ": " + diagnosis;
        reports.add(report);
        patientName = "";
        diagnosis = "";
        prescription = "";
    }
}
