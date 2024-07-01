package org.kst.lms.models.enums;

import lombok.Getter;

@Getter
public enum RegistrationStatus {
    APPROVED("approved"), DENIED("denied"), REGISTERED("registered");

    private final String name;

    RegistrationStatus(String name) {
        this.name = name;
    }

    public static RegistrationStatus findByValue(String value) {
        for (RegistrationStatus status : RegistrationStatus.values()) {
            if (status.getName().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("No enum constant with name " + value);
    }
}
