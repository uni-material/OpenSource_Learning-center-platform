package com.acme.center.platform.learning.domain.model.commands;

public record AddTutorialToCourseLearningPathCommand(Long tutorialId, Long courseId) {

    public AddTutorialToCourseLearningPathCommand{
        //reglas de negocio
        if(tutorialId == null) throw new IllegalArgumentException("Tutorial Id cannot be null");
        if(courseId == null) throw new IllegalArgumentException("Course Id cannot be null");

    }

}
