package com.acme.center.platform.learning.domain.model.valueobjects;

import jakarta.persistence.Embeddable;


@Embeddable //es parte de otra clase
public record TutorialId(Long tutorialId) {
    public TutorialId{
        if(tutorialId<0){
            throw new IllegalArgumentException("Tutorial id cannot be 0 or less");

        }
    }

    public TutorialId(){
        this(0L);
    }

}
