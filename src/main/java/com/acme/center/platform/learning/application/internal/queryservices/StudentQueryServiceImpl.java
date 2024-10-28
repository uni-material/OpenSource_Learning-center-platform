package com.acme.center.platform.learning.application.internal.queryservices;

import com.acme.center.platform.learning.domain.model.aggregates.Student;
import com.acme.center.platform.learning.domain.model.queries.GetAllEnrollmentsByAcmeStudentRecordIdQuery;
import com.acme.center.platform.learning.domain.model.queries.GetStudentByProfileIdQuery;
import com.acme.center.platform.learning.domain.services.StudentQueryService;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class StudentQueryServiceImpl implements StudentQueryService {
    @Override
    public Optional<Student> handle(GetStudentByProfileIdQuery query) {
        return Optional.empty();
    }

    @Override
    public Optional<Student> handle(GetAllEnrollmentsByAcmeStudentRecordIdQuery query) {
        return Optional.empty();
    }
}
