package cushen_group.veniamin.polyclinic.exception.errors;

public enum NotFoundError {
    USER_NOT_FOUND("Пользователь не был найден"),
    DOCTOR_NOT_FOUND("Врач не был найден"),
    PATIENT_NOT_FOUND("Пациент не был найден"),
    EXTRACT_NOT_FOUND("Выписка не была найдена");

    private String message;

    NotFoundError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
