package fr.caos.bravo.Metier;

import fr.caos.bravo.DTO.CandidaturesDTO;
import fr.caos.bravo.DTO.SessionsDTO;
import fr.caos.bravo.DTO.TypeUVDTO;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Sessions {

    private String idSession;
    private String intitule;
    public List<String> dates;
    private List<Candidatures> listeCandidatures;
    private TypeUV UVdelivre;

    public Sessions() {
    }

    ;

    public Sessions(String idSession, TypeUV typeUV) {
        this.idSession = idSession;
        this.intitule = typeUV.getLabel();
        this.listeCandidatures = new LinkedList<Candidatures>();
        this.UVdelivre = typeUV;
    }

    public String getIdSession() {
        return idSession;
    }

    public void setIdSession(String idSession) {
        this.idSession = idSession;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public void ajoutCandidature(Candidatures cand) {
        listeCandidatures.add(cand);
    }

    public List<Candidatures> getListeCandidatures() {
        return this.listeCandidatures;
    }

    public SessionsDTO toDTO() {
        SessionsDTO sDto = new SessionsDTO();
        sDto.setIdSession(this.idSession);
        sDto.setIntitule(this.intitule);
        List<CandidaturesDTO> candDTO = new LinkedList<CandidaturesDTO>();
        for (Candidatures c : this.listeCandidatures) {
            candDTO.add(c.toDTO());
        }
        sDto.setCandidaturesDTOList(candDTO);

        sDto.setTypeUVDTO(this.UVdelivre.toDto());

        return sDto;
    }

    public TypeUV getUVdelivre() {
        return UVdelivre;
    }

    public void setUVdelivre(TypeUV UVdelivre) {
        this.UVdelivre = UVdelivre;
    }
}
