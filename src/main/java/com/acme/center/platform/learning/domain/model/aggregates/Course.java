package com.acme.center.platform.learning.domain.model.aggregates;


import com.acme.center.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

@Getter
@Entity //tiene persistencia
public class Course extends AuditableAbstractAggregateRoot<Course> {

    private String title;
    private String description;

    //constructor
    public Course(String title, String description) {
        this.title = title;
        this.description = description;
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




}
