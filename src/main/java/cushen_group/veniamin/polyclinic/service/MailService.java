package cushen_group.veniamin.polyclinic.service;

import cushen_group.veniamin.polyclinic.entity.User;
import jakarta.servlet.http.HttpServletRequest;

public interface MailService {

    void sendUserVerificationMail(User user, HttpServletRequest request);

    void sendPasswordRestoreMail(User user, HttpServletRequest request);
}
