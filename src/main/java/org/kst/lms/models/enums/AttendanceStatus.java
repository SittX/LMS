package org.kst.lms.models.enums;

import lombok.Getter;

@Getter
public enum AttendanceStatus {
    PRESENCE("presence"), ABSENCE("absence"), LEAVE("leave");

    private final String name;

    AttendanceStatus(String name) {
        this.name = name;
    }

    public static AttendanceStatus findByValue(String value) {
        for (AttendanceStatus status : AttendanceStatus.values()) {
            if (status.getName().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("No enum constant with name " + value);
    }

}
