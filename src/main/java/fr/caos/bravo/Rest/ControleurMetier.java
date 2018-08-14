package fr.caos.bravo.Rest;

import fr.caos.bravo.DTO.CandidaturesDTO;
import fr.caos.bravo.DTO.SessionsDTO;
import fr.caos.bravo.DTO.TypeUVDTO;
import fr.caos.bravo.Metier.*;

import java.util.List;

public interface ControleurMetier {

    //Méthode d'authentification
    public String existAgent(String log, String mdp);

    public void setTableAuthentif(String ids, String idA);

    public void deleteSid(String sid);

    public String getIdaTableAuthentif(String sid);

    public boolean exist(String sid);

    //Méthode de gestion de sessions
    public List<SessionsDTO> getListSessionsDTO();

    public List<TypeUV> getTypeUVMetier();

    public boolean existSession(String idSession);

    public Sessions getSessions(String idSession);

    public List<CandidaturesDTO> getListCandSession(String idSession);

    public List<TypeUVDTO> getListUVValidees(String idAgent);

    public List<SessionsDTO> getListSessionsAccessibles(String idAgent);

    //Méthode de gestiond de candidatures
    public List<CandidaturesDTO> getListCandidaturesDTO();

    public boolean existCandidature(String idCandidature);

    public boolean estGestionnaire(String idA);

    public void ajoutCandidature(CandidaturesDTO cand);

    public boolean supprCandidature(String idCand);

    public void modifStatutCandidatur(String idCandidature, String statut);

    public void modifListAttente(String sid, String cand, int rangListeAttente);


}
