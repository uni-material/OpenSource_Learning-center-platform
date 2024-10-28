package com.acme.center.platform.learning.domain.model.commands;

public record CancelEnrollmentCommand(Long enrollmentId) {

    //regla de negocio
    public CancelEnrollmentCommand{
        if (enrollmentId==null || enrollmentId <= 0) throw new IllegalArgumentException("Enrollment id cannot be less than 0");
    }
}
