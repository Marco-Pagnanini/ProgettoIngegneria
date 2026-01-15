package org.example.Core.models.enums;

public enum State {
    inIscrizione,
    inPreparazione,
    inCorso,
    concluso;

    public State next(){
        return switch (this){
            case inIscrizione -> inPreparazione;
            case inPreparazione -> inCorso;
            case inCorso, concluso -> concluso;
        };
    }
}
