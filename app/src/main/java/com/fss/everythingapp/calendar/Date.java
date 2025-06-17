package com.fss.everythingapp.calendar;

import java.time.LocalDateTime;

public class Date {
    protected String dateName;
    protected char dateType;

    protected LocalDateTime dueDate;
    protected LocalDateTime startDate;
    protected LocalDateTime endDate;

    Date() {
    }

    String getDateName() {
        return this.dateName;
    }

    char getDateType() {
        return this.dateType;
    }

    LocalDateTime getDueDate() {
        return this.dueDate;
    }

    LocalDateTime getStartDate() {
        return this.startDate;
    }

    LocalDateTime getEndDate() {
        return this.endDate;
    }
}