package cushen_group.veniamin.polyclinic.service;

import cushen_group.veniamin.polyclinic.dto.request.ExtractCreateReqDTO;
import cushen_group.veniamin.polyclinic.dto.request.ExtractUpdateReqDTO;
import cushen_group.veniamin.polyclinic.entity.Extract;
import org.primefaces.model.StreamedContent;

public interface ExtractService {

    Extract getExtractById(Long extractId);

    Extract createExtract(ExtractCreateReqDTO extractCreateReqDTO);

    StreamedContent getExcelExport(ExtractCreateReqDTO extractCreateReqDTO);

    Extract updateExtract(ExtractUpdateReqDTO extractUpdateReqDTO, Long extractId);

    boolean deleteExtractById(Long extractId);

}
