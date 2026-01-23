package org.example.Api.Models.Mapper;


import org.example.Api.Models.Request.sottomissione.SottoMissioneDomandaRequest;
import org.example.Api.Models.Request.sottomissione.SottoMissioneRequest;
import org.example.Api.Models.Response.sottomissione.SottoMissioneDomandaResponse;
import org.example.Core.models.sottoMissioni.SottoMissioneDomanda;
import org.example.utils.Factory.SottoMissioneFactory;
import org.example.Core.enums.TipoSottomissione;

public class SottoMissioneDomandaMapper {

    /**
     * Converte Request DTO → Entity
     *
     * @param request Il DTO della richiesta
     * @return Entity SottoMissioneDomanda
     */
    public static SottoMissioneDomanda toEntity(SottoMissioneDomandaRequest request) {
        // Usa la factory per creare l'entità
        SottoMissioneDomanda domanda = (SottoMissioneDomanda)
                SottoMissioneFactory.crea(
                        TipoSottomissione.DOMANDA_APERTA,
                        request.getTitolo(),
                        request.getDescrizione()
                );

        // Configura campi specifici
        if (request.getRisposta() != null) {
            domanda.setRisposta(request.getRisposta());
        }

        return domanda;
    }

    /**
     * Converte Entity → Response DTO
     *
     * @param domanda L'entità SottoMissioneDomanda
     * @return DTO di risposta
     */
    public static SottoMissioneDomandaResponse toResponse(SottoMissioneDomanda domanda) {
        SottoMissioneDomandaResponse response = new SottoMissioneDomandaResponse();

        // Campi comuni
        response.setId(domanda.getId());
        response.setTitolo(domanda.getTitolo());
        response.setDescrizione(domanda.getDescrizione());
        response.setTipo(domanda.getTipo());

        // Campi specifici
        response.setRisposta(domanda.getRisposta());

        return response;
    }

    /**
     * Aggiorna un'entità esistente con i dati del request
     *
     * @param domanda L'entità da aggiornare
     * @param request I nuovi dati
     */
    public static void updateEntity(SottoMissioneDomanda domanda, SottoMissioneDomandaRequest request) {
        if (request.getTitolo() != null) {
            domanda.setTitolo(request.getTitolo());
        }
        if (request.getDescrizione() != null) {
            domanda.setDescrizione(request.getDescrizione());
        }
        if (request.getRisposta() != null) {
            domanda.setRisposta(request.getRisposta());
        }
    }
}