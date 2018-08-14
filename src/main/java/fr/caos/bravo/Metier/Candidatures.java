package fr.caos.bravo.Metier;

import fr.caos.bravo.DTO.CandidaturesDTO;

public class Candidatures {

    private String idCandidature;
    private String idAgent;
    private String idSession;
    private String statut;
    private String qualite;
    private int position;

    public Candidatures(String idAgent, String idSession, String qualite) {
        this.idAgent = idAgent;
        this.idSession = idSession;
        this.qualite = qualite;
    }

    public String getIdCandidature() {
        if (this.idCandidature == null) {
            this.idCandidature = Integer.toHexString(this.hashCode());
        }
        return idCandidature;
    }

    public void setIdCandidature(String idCandidature) {
        this.idCandidature = idCandidature;
    }

    public String getIdAgent() {
        return idAgent;
    }

    public void setIdAgent(String idAgent) {
        this.idAgent = idAgent;
    }

    public String getIdSession() {
        return idSession;
    }

    public void setIdSession(String idSession) {
        this.idSession = idSession;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getQualite() {
        return qualite;
    }

    public void setQualite(String qualite) {
        this.qualite = qualite;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public CandidaturesDTO toDTO() {
        CandidaturesDTO candidaturesDTO = new CandidaturesDTO();

        candidaturesDTO.setIdCandidature(getIdCandidature());
        candidaturesDTO.setIdAgent(getIdAgent());
        candidaturesDTO.setIdSessionUV(getIdSession());
        candidaturesDTO.setQualite(getQualite());
        candidaturesDTO.setPosition(getPosition());
        candidaturesDTO.setStatut(getStatut());

        return candidaturesDTO;
    }
}
