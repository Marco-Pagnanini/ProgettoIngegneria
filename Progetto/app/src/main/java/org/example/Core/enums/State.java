package org.example.Core.enums;

public enum State {
    IN_ISCRIZIONE,
    IN_PREPARAZIONE,
    IN_CORSO,
    IN_VALUTAZIONE,
    CONCLUSO;

    public State next(){
        return switch (this){
            case IN_ISCRIZIONE -> IN_PREPARAZIONE;
            case IN_PREPARAZIONE -> IN_CORSO;
            case IN_CORSO -> IN_VALUTAZIONE;
            case IN_VALUTAZIONE,CONCLUSO -> CONCLUSO;
        };
    }
}
