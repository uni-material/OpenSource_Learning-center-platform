package com.acme.center.platform.learning.domain.model.commands;

//Commando es una estructura de trasporte y validación de elementos.
public record CompleteTutorialForEnrollmentCommand(Long enrollmentId, Long tutorialId) {
    public CompleteTutorialForEnrollmentCommand{
        if(enrollmentId == null || enrollmentId <=0) throw new IllegalArgumentException("Enrollment Id cannot be null or less than or equal to 0");
        if(tutorialId == null || tutorialId <= 0) throw new IllegalArgumentException("Tutorial Id cannot be 0 or less");
    }


}
