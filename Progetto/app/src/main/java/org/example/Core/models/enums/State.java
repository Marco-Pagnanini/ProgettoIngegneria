package org.example.Core.models.enums;

public enum State {
    IN_ISCRIZIONE,
    IN_PREPARAZIONE,
    IN_CORSO,
    CONCLUSO;

    public State next(){
        return switch (this){
            case IN_ISCRIZIONE -> IN_PREPARAZIONE;
            case IN_PREPARAZIONE -> IN_CORSO;
            case IN_CORSO, CONCLUSO -> CONCLUSO;
        };
    }
}
