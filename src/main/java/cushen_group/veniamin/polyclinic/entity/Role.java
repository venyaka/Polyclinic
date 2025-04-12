package cushen_group.veniamin.polyclinic.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    PATIENT, DOCTOR, ADMIN;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
