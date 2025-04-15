package cushen_group.veniamin.polyclinic.service.impl;

import cushen_group.veniamin.polyclinic.dto.request.ExtractCreateReqDTO;
import cushen_group.veniamin.polyclinic.dto.request.ExtractUpdateReqDTO;
import cushen_group.veniamin.polyclinic.entity.Extract;
import cushen_group.veniamin.polyclinic.entity.Role;
import cushen_group.veniamin.polyclinic.entity.User;
import cushen_group.veniamin.polyclinic.exception.BadRequestException;
import cushen_group.veniamin.polyclinic.exception.NotFoundException;
import cushen_group.veniamin.polyclinic.exception.errors.BadRequestError;
import cushen_group.veniamin.polyclinic.exception.errors.NotFoundError;
import cushen_group.veniamin.polyclinic.repository.ExtractRepository;
import cushen_group.veniamin.polyclinic.repository.UserRepository;
import cushen_group.veniamin.polyclinic.service.ExtractService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
        extract.setText(extractCreateReqDTO.getText());

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
        extract.setText(extractUpdateReqDTO.getText());

        extractRepository.save(extract);

        return extract;
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
