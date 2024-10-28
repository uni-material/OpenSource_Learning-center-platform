package com.acme.center.platform.learning.domain.model.valueobjects;

import ch.qos.logback.classic.joran.action.ClassicEvaluatorAction;
import com.acme.center.platform.learning.domain.model.aggregates.Enrollment;
import com.acme.center.platform.learning.domain.model.entities.LearningPathItem;
import com.acme.center.platform.learning.domain.model.entities.ProgressRecordItem;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;
import org.aspectj.weaver.IEclipseSourceContext;

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

        if(Objects.isNull(progressRecordItem)){
            throw new IllegalStateException("Tutorial with this ID is not in the progress record");
        };

        if (progressRecordItem.isNotStarted()) progressRecordItem.start();
            else throw new IllegalStateException("Tutorial with this ID is already started or completed");

    }

    public void completeTutorial(Long tutorialId, LearningPath learningPath){
        if(hasAnItemInProgress()) throw new IllegalStateException("The tutorial is in progress");

        ProgressRecordItem progressRecordItem = getProgressRecordItemWithTutorial(tutorialId);

        if(Objects.isNull(progressRecordItem)){
            throw new IllegalStateException("Tutorial with this ID is not in the progress record");
        }

        if(progressRecordItem.isInProgress()) progressRecordItem.complete();
            else throw new IllegalStateException("Tutorial with this ID s not in progress");

        Long nextTutorialId = learningPath.getNextTutorialIdLearningPath(tutorialId);

        if(Objects.nonNull(nextTutorialId)){
            //si no es nulo obtener siguiente elemento
            ProgressRecordItem nextProgressRecordItem = getProgressRecordItemWithTutorial(nextTutorialId);
            //si es nulo crear nuevo siguiente elemento
            if(Objects.isNull(nextProgressRecordItem)) {
                nextProgressRecordItem = new ProgressRecordItem(progressRecordItem.getEnrollment(), nextTutorialId);
                progressRecordItems.add(nextProgressRecordItem);
            }
        }

    }

    //calcular días trascurridos desde la inscripción
    public long calculateDaysElapsedForEnrollment(Enrollment enrollment){

        return progressRecordItems.stream()
                //La función flecha toma cada progressRecordItem y verifica si su Enrollment asociado es igual al que se pasa como argumento (enrollment).
                .filter(progressRecordItem -> progressRecordItem.getEnrollment().equals(enrollment))
                /*
                /Este paso convierte cada ProgressRecordItem restante en un valor long,
                /aplicando calculateDaysElapsed de cada ProgressRecordItem.
                /Cada valor devuelto por calculateDaysElapsed se agrega como un long al
                /flujo de datos (stream) numérico.
                */
                .mapToLong(ProgressRecordItem::calculateDaysElapsed)
                .sum();
    }

}
