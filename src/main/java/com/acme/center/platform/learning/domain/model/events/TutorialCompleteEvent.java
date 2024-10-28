package com.acme.center.platform.learning.domain.model.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
/**
 * Event that is published when a tutorial is completed
 */

@Getter
public class TutorialCompleteEvent extends ApplicationEvent {

    private final Long enrollmentId;
    private final Long tutorialId;

    public TutorialCompleteEvent(Object source, Long enrollmentId, Long tutorialId) {
        super(source);
        this.enrollmentId = enrollmentId;
        this.tutorialId = tutorialId;
    }

}
