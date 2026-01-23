package org.example.utils.Factory;

import org.example.Core.enums.TipoSottomissione;
import org.example.Core.models.sottoMissioni.*;

public class SottoMissioneFactory {

    /**
     * Factory Method principale per creare sottomissioni
     *
     * @param tipo Il tipo di sottomissione da creare
     * @return Una nuova istanza del tipo di sottomissione richiesto
     */
    public static SottoMissione crea(TipoSottomissione tipo) {
        return switch (tipo) {
            case DOMANDA_APERTA -> creaDomandaAperta();
            case PROGETTO -> creaProgetto();
        };
    }

    /**
     * Factory Method con parametri base
     *
     * @param tipo Il tipo di sottomissione
     * @param titolo Il titolo della sottomissione
     * @param descrizione La descrizione della sottomissione
     * @return Sottomissione configurata con titolo e descrizione
     */
    public static SottoMissione crea(TipoSottomissione tipo, String titolo, String descrizione) {
        SottoMissione sottoMissione = crea(tipo);
        sottoMissione.setTitolo(titolo);
        sottoMissione.setDescrizione(descrizione);
        return sottoMissione;
    }

    // Factory Methods privati per ogni tipo

    private static SottoMissioneDomanda creaDomandaAperta() {
        return new SottoMissioneDomanda();
    }

    private static SottoMissioneProgetto creaProgetto() {
        return new SottoMissioneProgetto();
    }

}