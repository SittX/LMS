package org.kst.lms.models.enums;

import lombok.Getter;

@Getter
public enum Gender {
    MALE("male"), FEMALE("female"), OTHER("other");

    private final String name;

    Gender(String name) {
        this.name = name;
    }

    public static Gender findByValue(String value) {
        for (Gender gender : Gender.values()) {
            if (gender.getName().equalsIgnoreCase(value)) {
                return gender;
            }
        }
        throw new IllegalArgumentException("No enum constant with name " + value);
    }

}
