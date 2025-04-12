package cushen_group.veniamin.polyclinic.exception;

import cushen_group.veniamin.polyclinic.exception.errors.AuthorizedError;
import lombok.Getter;

@Getter
public class AuthorizeException extends BusinessException {

    private String errorName;

    public AuthorizeException(AuthorizedError authorizedError) {
        super(authorizedError.getMessage());
        this.errorName = authorizedError.name();
    }

    public AuthorizeException(String message, String errorName) {
        super(message);
        this.errorName = errorName;
    }
}
