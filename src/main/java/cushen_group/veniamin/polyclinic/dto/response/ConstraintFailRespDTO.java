package cushen_group.veniamin.polyclinic.dto.response;

import lombok.Data;

@Data
public class ConstraintFailRespDTO {

    private String fieldName;

    private String message;

    private Object rejectedValue;

    private String code;
}
