package fr.caos.bravo.Rest;

/**
 * Equipe Bravo
 * Manager des sessions
 */

import com.sun.jersey.spi.resource.Singleton;
import fr.caos.bravo.DTO.CandidaturesDTO;
import fr.caos.bravo.DTO.TypeUVDTO;
import fr.caos.bravo.Metier.Candidatures;
import fr.caos.bravo.Metier.Sessions;
import fr.caos.bravo.DTO.SessionsDTO;
import fr.caos.bravo.Metier.TypeUV;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Singleton
@Path("{sid}/sessions")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class SessionManager {

    private List<SessionsDTO> sessions; // liste des sessions
    ControleurMetier controleur; //objet partagé par les managers

    public SessionManager() {
        controleur = ControleurMetierImple.getControleur();
    }

    /**
     * Methode appelée pour retourner la liste complète des sessions DTO
     *
     * @return List<SessionsDTO>
     */
    @GET
    @Path("listeSessionDTO")
    @Produces({MediaType.APPLICATION_JSON})
    public synchronized List<SessionsDTO> getSessions(@PathParam("sid") String sid) {
        if (this.controleur.exist(sid)) { // on vérifie le SID
            // this.sessions = this.controleur.getListSessionsDTO();
            String idA = this.controleur.getIdaTableAuthentif(sid);
            this.sessions = this.controleur.getListSessionsAccessibles(idA);
            return this.sessions;
        }// on retourne la liste des sessions
        else {
            return null;
        }
    }


    /**
     * Methode appelée pour retourner une sessions précise
     *
     * @param uid : identifiant de session
     * @return SessionDTO au format JSON
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("{uid}")
    public synchronized SessionsDTO getSessionDetail(@PathParam("sid") String sid, @PathParam("uid") String uid) {
        if (this.controleur.exist(sid)) { // on vérifie le SID
            // Si SID valide, on recherche la session demandée
            for (SessionsDTO s : sessions) {
                if (s.getIdSession().equals(uid)) {
                    // on rend la session si elle existe
                    return s;
                }
            }
        }
        return null;
    }

    /**
     * Method qui ajoute une candidature
     *
     * @param idSessionDTO : id de Session
     * @param sid          : id session informatique
     * @param qualite      : formateur ou apprenant
     * @return cand
     */
    @POST
    @Path("cand")
    //@Consumes({MediaType.APPLICATION_JSON})
    @Produces("text/plain")
    public synchronized String ajoutCandidature
    (@PathParam("sid") String sid, @QueryParam("idSessionDTO") String idSessionDTO, @QueryParam("qualite") String qualite) {

        CandidaturesDTO cand = new CandidaturesDTO();
        String message = "rien";
        if (this.controleur.exist(sid)) { // On vérifie que le SID est correct
            //Si SID correct, on récupère id de l'agent connecté
            String idA = this.controleur.getIdaTableAuthentif(sid);
            // on ajoute une nouvelle candidature
            cand.setIdAgent(idA);
            cand.setIdSessionUV(idSessionDTO);
            cand.setQualite(qualite);
            String idC = Integer.toHexString(this.hashCode());
            cand.setIdCandidature(idC);
            this.controleur.ajoutCandidature(cand);
            if (this.controleur.existCandidature(idC)) {
                message = "Ajout réussi";
            } else {
                message = "pas d'ajout";
            }
        } else {
            message = "mauvais sid";
        }
        // on retourne la candidature
        return message;
    }

    /**
     * Methode qui retourne la liste des sessions obtenues par l'agent connecté
     *
     * @param sid
     * @return
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("listeSessionsValidees")
    public synchronized List<TypeUVDTO> getSessionsValidees(@PathParam("sid") String sid) {
        // Si le SID est validé
        if (this.controleur.exist(sid)) {
            List<TypeUVDTO> listToSend = new ArrayList<TypeUVDTO>();
            // On recheche l'agent connecté
            String idAgentConnect = this.controleur.getIdaTableAuthentif(sid);
            //on récupère la liste des UV qu'il a validé
            listToSend = this.controleur.getListUVValidees(idAgentConnect);
            return listToSend;
        }
        return null;
    }
}
