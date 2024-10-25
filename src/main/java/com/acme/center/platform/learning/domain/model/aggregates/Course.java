package com.acme.center.platform.learning.domain.model.aggregates;


import com.acme.center.platform.learning.domain.model.valueobjects.LearningPath;
import com.acme.center.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

@Getter
@Entity //tiene persistencia
public class Course extends AuditableAbstractAggregateRoot<Course> {

    private String title;
    private String description;

    @Embedded
    private final LearningPath learningPath;

    //constructor
    public Course(String title, String description) {
        this.title = title;
        this.description = description;
        learningPath = new LearningPath();
    }

    //inicializa vacio
    public Course(){
        this(Strings.EMPTY, Strings.EMPTY);
    }

    //función para actualizar
    public Course updateInformation(String title, String description){
        this.title = title;
        this.description = description;
        return this;
    }


    public void addTutorialToLearningPath(Long tutorialId){
        learningPath.addItem(this, tutorialId);
    }

    public void addTutorialToLearningPath(Long tutorialId, Long nextTutorialId){
        learningPath.addItem(this, tutorialId, nextTutorialId);
    }




}
