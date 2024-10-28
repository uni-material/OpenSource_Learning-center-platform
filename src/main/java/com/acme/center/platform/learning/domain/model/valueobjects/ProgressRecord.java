package com.acme.center.platform.learning.domain.model.valueobjects;

import com.acme.center.platform.learning.domain.model.aggregates.Enrollment;
import com.acme.center.platform.learning.domain.model.entities.LearningPathItem;
import com.acme.center.platform.learning.domain.model.entities.ProgressRecordItem;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Embeddable
public class ProgressRecord {

    @OneToMany(mappedBy = "enrollment", cascade = CascadeType.ALL)
    private List<ProgressRecordItem> progressRecordItems;

    public ProgressRecord(){
        this.progressRecordItems = new ArrayList<>();
    }

    public void initializeProgressRecord(Enrollment enrollment, LearningPath learningPath){
        if(learningPath.isEmpty()) return;

        Long tutorialId = learningPath.getFirstTutorialIdInLearningPath();
        ProgressRecordItem progressRecordItem = new ProgressRecordItem(enrollment, tutorialId);
        progressRecordItems.add(progressRecordItem);

    }

    //retorna un objeto ProgressRecordItem
    public ProgressRecordItem getProgressRecordItemWithTutorial(Long tutorialId){
        return progressRecordItems.stream()
                .filter(progressRecordItem -> progressRecordItem.getTutorialId().equals(tutorialId))
                .findFirst().orElse(null);
    }

    //.stream() convierte una lista en un flujo de datos
    //verificar si algún item está en progreso
    private boolean hasAnItemInProgress(){
        //anyMatch debe ejecutar isInProgress en cada ProgressRecordItem de la lista.
        return progressRecordItems.stream().anyMatch(ProgressRecordItem::isInProgress);
    }

    public void startTutorial(Long tutorialId){
        if (hasAnItemInProgress()) throw new IllegalStateException("A tutorial is already in progress");

        //obtener tutorial
        //Objeto  + nombrepropio = función que devuelve un objeto
        ProgressRecordItem progressRecordItem = getProgressRecordItemWithTutorial(tutorialId);
        if(Objects.nonNull(progressRecordItem)){
            if (progressRecordItem.isNotStarted()){
                progressRecordItem.start();
            }
            else throw new IllegalStateException("Tutorial with this Id is already started or completed");
        }
        else throw new IllegalStateException("Tutorial with this ID is not in progress record");

    }




}
