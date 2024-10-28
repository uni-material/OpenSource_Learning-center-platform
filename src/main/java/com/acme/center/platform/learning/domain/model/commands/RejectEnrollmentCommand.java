package com.acme.center.platform.learning.domain.model.commands;

public record RejectEnrollmentCommand(Long enrollmentId) {

    public RejectEnrollmentCommand {
        if (enrollmentId == null || enrollmentId <= 0) {
            throw new IllegalArgumentException("enrollmentId cannot be null or less than or equal to 0");
        }
    }


}
