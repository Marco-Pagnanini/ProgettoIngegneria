package org.example.Api.Models.Mapper;

import org.example.Api.Models.Request.sottomissione.SottoMissioneProgettoRequest;
import org.example.Api.Models.Response.sottomissione.SottoMissioneProgettoResponse;
import org.example.Core.models.sottoMissioni.SottoMissioneProgetto;
import org.example.utils.Factory.SottoMissioneFactory;
import org.example.Core.enums.TipoSottomissione;

public class SottoMissioneProgettoMapper {

    /**
     * Converte Request DTO → Entity
     */
    public static SottoMissioneProgetto toEntity(SottoMissioneProgettoRequest request) {
        SottoMissioneProgetto progetto = (SottoMissioneProgetto)
                SottoMissioneFactory.crea(
                        TipoSottomissione.PROGETTO,
                        request.getTitolo(),
                        request.getDescrizione()
                );

        // Configura campi specifici
        if (request.getUrlProgetto() != null) {
            progetto.setUrlProgetto(request.getUrlProgetto());
        }

        return progetto;
    }

    /**
     * Converte Entity → Response DTO
     */
    public static SottoMissioneProgettoResponse toResponse(SottoMissioneProgetto progetto) {
        SottoMissioneProgettoResponse response = new SottoMissioneProgettoResponse();

        // Campi comuni
        response.setId(progetto.getId());
        response.setTitolo(progetto.getTitolo());
        response.setDescrizione(progetto.getDescrizione());
        response.setTipo(progetto.getTipo());

        // Campi specifici
        response.setUrlProgetto(progetto.getUrlProgetto());

        return response;
    }
}