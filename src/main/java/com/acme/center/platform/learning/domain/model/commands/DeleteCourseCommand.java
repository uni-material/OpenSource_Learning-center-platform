package com.acme.center.platform.learning.domain.model.commands;

public record DeleteCourseCommand(Long courseId) {
    public DeleteCourseCommand {
        if (courseId == null || courseId <= 0) {
            throw new IllegalArgumentException("courseId cannot be null or less than or equal to 0");
        }
    }
}
