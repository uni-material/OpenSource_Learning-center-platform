package com.acme.center.platform.learning.domain.model.aggregates;

import com.acme.center.platform.learning.domain.model.events.TutorialCompleteEvent;
import com.acme.center.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;
import com.acme.center.platform.learning.domain.model.valueobjects.EnrollmentStatus;
import com.acme.center.platform.learning.domain.model.valueobjects.LearningPath;
import com.acme.center.platform.learning.domain.model.valueobjects.ProgressRecord;
import com.acme.center.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

//enrollment -> matricula
@Entity
public class Enrollment extends AuditableAbstractAggregateRoot<Enrollment> {

    @Getter
    @Embedded
    private AcmeStudentRecordId acmeStudentRecordId;

    @Getter
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;


    private EnrollmentStatus status;

    @Embedded
    private ProgressRecord progressRecord;

    //siempre debe haber un constructor sin parametros
    public Enrollment(){
    }

    public Enrollment(AcmeStudentRecordId studentRecordId, Course course){
        this.acmeStudentRecordId = studentRecordId;
        this.course = course;
        this.status = EnrollmentStatus.REQUESTED;
        this.progressRecord = new ProgressRecord();
    }

    public void confirm(){
        this.status = EnrollmentStatus.CONFIRM;
        this.progressRecord.initializeProgressRecord(this, course.getLearningPath());


    }

    public void reject(){
        this.status = EnrollmentStatus.REJECTED;
    }

    public void cancel(){
        this.status = EnrollmentStatus.CANCELLED;
    }

    public String getStatus(){
        return status.name().toLowerCase();
    }

    public boolean isConfirmed(){
        return status == EnrollmentStatus.CONFIRM;
    }

    public boolean isRejected(){
        return status == EnrollmentStatus.REJECTED;
    }

    public boolean isCancelled(){
        return status == EnrollmentStatus.CANCELLED;
    }

    public void completeTutorial(Long tutorialId){
        //Update Progress Record
        this.progressRecord.completeTutorial(tutorialId, course.getLearningPath());
        // Emit the tutorial Completed Event
        this.registerEvent(new TutorialCompleteEvent(this, this.getId(), tutorialId));
    }

}
