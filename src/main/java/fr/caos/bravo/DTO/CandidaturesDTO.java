package fr.caos.bravo.DTO;

import fr.caos.bravo.Metier.Candidatures;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Ines Beugre
 */

@XmlRootElement
public class CandidaturesDTO {

    private String idCandidature;
    private String idAgent;
    private String idSessionUV;
    private String statut;
    private String qualite;
    private Integer position;

    public CandidaturesDTO(Candidatures candidature) {
        this.idCandidature = candidature.getIdCandidature();
        this.idAgent = candidature.getIdAgent();
        this.idSessionUV = candidature.getIdSession();
        this.statut = candidature.getStatut();
        this.qualite = candidature.getQualite();
        this.position = candidature.getPosition();
    }

    public CandidaturesDTO(String idA, String idS) {
        this.idAgent = idA;
        this.idSessionUV = idS;
    }

    public CandidaturesDTO() {
    }

    public String getIdCandidature() {
        return idCandidature;
    }

    public void setIdCandidature(String idCandidature) {
        this.idCandidature = idCandidature;
    }

    public String getIdAgent() {
        return this.idAgent;
    }

    public void setIdAgent(String idAgent) {
        this.idAgent = idAgent;
    }

    public String getIdSessionUV() {
        return this.idSessionUV;
    }

    public void setIdSessionUV(String idSessionUV) {
        this.idSessionUV = idSessionUV;
    }

    public String getStatut() {
        return this.statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getQualite() {
        return this.qualite;
    }

    public void setQualite(String qualite) {
        this.qualite = qualite;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
