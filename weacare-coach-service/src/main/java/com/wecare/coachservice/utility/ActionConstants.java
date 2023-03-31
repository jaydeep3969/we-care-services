package com.wecare.coachservice.utility;

public enum ActionConstants {
    APPOINTMENT_SCHEDULED_SUCCESS("appointment.create.success");

    private final String type;

    private ActionConstants(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }
}
