package com.acme.center.platform.learning.domain.services;


import com.acme.center.platform.learning.domain.model.aggregates.Course;
import com.acme.center.platform.learning.domain.model.commands.AddTutorialToCourseLearningPathCommand;
import com.acme.center.platform.learning.domain.model.commands.CreateCourseCommand;
import com.acme.center.platform.learning.domain.model.commands.DeleteCourseCommand;
import com.acme.center.platform.learning.domain.model.commands.UpdateCourseCommand;

import java.util.Optional;

//Por cada aggregate un command y un query service

/**
 * This interface represents the service that handles the commands related to course aggregate
 */
public interface CourseCommandService {

    Long handle (CreateCourseCommand command);

    Optional<Course> handle(UpdateCourseCommand command);

    void handle(DeleteCourseCommand command);

    void handle(AddTutorialToCourseLearningPathCommand command);

}
