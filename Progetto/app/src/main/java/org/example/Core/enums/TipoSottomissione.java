package org.example.Core.enums;

public enum TipoSottomissione {
    DOMANDA_APERTA("Domanda Aperta"),
    PROGETTO("Progetto");

    private final String displayName;

    TipoSottomissione(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}