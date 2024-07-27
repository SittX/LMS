package org.kst.lms.models.enums;

import lombok.Getter;

@Getter
public enum DaysOfWeek {
    SUN(1),MON(2),TUE(3), WED(4), THU(5), FRI(6), SAT(7);
    private final int dateOrder;

    DaysOfWeek(int dateOrder) {
        this.dateOrder = dateOrder;
    }

    public static DaysOfWeek findByValue(int dateOrder) {
        for (DaysOfWeek day : DaysOfWeek.values()) {
            if (day.getDateOrder() == dateOrder) return day;
        }
        throw new IllegalArgumentException("No enum constant with value " + dateOrder);
    }
}
