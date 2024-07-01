package org.kst.lms.models.enums;

import lombok.Getter;

@Getter
public enum LeaveRequestStatus {
    APPROVED("approved"), DENIED("denied"), SUBMITTED("submitted");

    private final String name;

    LeaveRequestStatus(String name) {
        this.name = name;
    }

    public static LeaveRequestStatus findByValue(String value) {
        for (LeaveRequestStatus status : LeaveRequestStatus.values()) {
            if (status.getName().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("No enum constant with name " + value);
    }
}
