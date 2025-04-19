package cushen_group.veniamin.polyclinic.service.impl;

import cushen_group.veniamin.polyclinic.dto.request.ExtractCreateReqDTO;
import cushen_group.veniamin.polyclinic.dto.request.ExtractUpdateReqDTO;
import cushen_group.veniamin.polyclinic.entity.Extract;
import cushen_group.veniamin.polyclinic.entity.User;
import cushen_group.veniamin.polyclinic.exception.NotFoundException;
import cushen_group.veniamin.polyclinic.exception.errors.NotFoundError;
import cushen_group.veniamin.polyclinic.repository.ExtractRepository;
import cushen_group.veniamin.polyclinic.repository.UserRepository;
import cushen_group.veniamin.polyclinic.service.ExtractService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class ExtractServiceImpl implements ExtractService {

    private final ExtractRepository extractRepository;

    private final UserRepository userRepository;

    @Override
    public Extract getExtractById(Long extractId) {
        Optional<Extract> optionalExtract = extractRepository.findById(extractId);
        if (optionalExtract.isEmpty()) {
            throw new NotFoundException(NotFoundError.EXTRACT_NOT_FOUND);
        }
        return optionalExtract.get();
    }

    @Override
    public Extract createExtract(ExtractCreateReqDTO extractCreateReqDTO) {

        Extract extract = new Extract();

        Optional<User> optionalPatient = userRepository.findByEmail(extractCreateReqDTO.getPatientEmail());
        if (optionalPatient.isEmpty()) {
            throw new NotFoundException(NotFoundError.PATIENT_NOT_FOUND);
        }
        extract.setPatient(optionalPatient.get());

        Optional<User> optionalDoctor = userRepository.findByEmail(extractCreateReqDTO.getDoctorEmail());
        if (optionalDoctor.isEmpty()) {
            throw new NotFoundException(NotFoundError.DOCTOR_NOT_FOUND);
        }
        extract.setDoctor(optionalDoctor.get());

        extract.setDate(extractCreateReqDTO.getDate());
        extract.setDiagnosis(extractCreateReqDTO.getDiagnosis());
        extract.setPrescription(extractCreateReqDTO.getPrescription());

        extractRepository.save(extract);

        return extract;
    }

    @Override
    public Extract updateExtract(ExtractUpdateReqDTO extractUpdateReqDTO, Long extractId) {
        Optional<Extract> optionalExtract = extractRepository.findById(extractId);
        if (optionalExtract.isEmpty()) {
            throw new NotFoundException(NotFoundError.EXTRACT_NOT_FOUND);
        }
        Extract extract = optionalExtract.get();

        Optional<User> optionalPatient = userRepository.findByEmail(extractUpdateReqDTO.getPatientEmail());
        if (optionalPatient.isEmpty()) {
            throw new NotFoundException(NotFoundError.PATIENT_NOT_FOUND);
        }
        extract.setPatient(optionalPatient.get());

        Optional<User> optionalDoctor = userRepository.findByEmail(extractUpdateReqDTO.getDoctorEmail());
        if (optionalDoctor.isEmpty()) {
            throw new NotFoundException(NotFoundError.DOCTOR_NOT_FOUND);
        }
        extract.setDoctor(optionalDoctor.get());

        extract.setDate(extractUpdateReqDTO.getDate());
        extract.setDiagnosis(extractUpdateReqDTO.getDiagnosis());
        extract.setPrescription(extractUpdateReqDTO.getPrescription());

        extractRepository.save(extract);

        return extract;
    }


    public StreamedContent getExcelExport(ExtractCreateReqDTO extractCreateReqDTO) {

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Extract");

            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Дата");
            header.createCell(1).setCellValue("Врач");
            header.createCell(2).setCellValue("Пациент");
            header.createCell(3).setCellValue("Диагноз");
            header.createCell(4).setCellValue("Назначение");

            Row data = sheet.createRow(1);
            data.createCell(0).setCellValue(extractCreateReqDTO.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            data.createCell(1).setCellValue(extractCreateReqDTO.getDoctorEmail());
            data.createCell(2).setCellValue(extractCreateReqDTO.getPatientEmail());
            data.createCell(3).setCellValue(extractCreateReqDTO.getDiagnosis());
            data.createCell(4).setCellValue(extractCreateReqDTO.getPrescription());

            for (int i = 0; i < 5; i++) {
                sheet.autoSizeColumn(i);
            }

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);

            InputStream inputStream = new ByteArrayInputStream(out.toByteArray());

            return DefaultStreamedContent.builder()
                    .name("extract.xlsx")
                    .contentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                    .stream(() -> inputStream)
                    .build();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteExtractById(Long extractId) {
        extractRepository.deleteById(extractId);
        if (extractRepository.findById(extractId).isPresent()) {
            extractRepository.deleteById(extractId);
            return true;
        }
        return false;
    }
}
