package fr.caos.bravo.Rest;

import com.sun.jersey.spi.resource.Singleton;
import fr.caos.bravo.DTO.CandidaturesDTO;
import fr.caos.bravo.DTO.SessionsDTO;
import fr.caos.bravo.Metier.Agents;
import fr.caos.bravo.Metier.Candidatures;
import fr.caos.bravo.Metier.Sessions;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Ines Beugre
 */

@Singleton
@Path("{sid}/candidature")
@Produces({MediaType.APPLICATION_JSON}) /*format de données*/
@Consumes({MediaType.APPLICATION_JSON})

public class CandidatureManager { //

    ControleurMetierImple controleur;
    private List<CandidaturesDTO> candidaturesList;


    public CandidatureManager() {

        candidaturesList = new ArrayList<CandidaturesDTO>();
        controleur = ControleurMetierImple.getControleur();

    } //End constructor

    /**
     * Default action for the Candidature manager: return all candidature
     *
     * @return The list of candidature(all fields)
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public synchronized List<CandidaturesDTO> getCandidaturesList(@PathParam("sid") String sid) {
        /**Creation de la liste DTO*/
        List<CandidaturesDTO> candidaturesListDTO = new ArrayList<CandidaturesDTO>();

        /**on vérifie si la session existe*/
        if (this.controleur.exist(sid)) {
            candidaturesListDTO = this.controleur.getListCandidaturesDTO();
            return candidaturesListDTO;
        }

        return null;  /**alors il peut consulter la liste des candidatures*/
    }//End List<Candidature> getCandidaturesList


    /**
     * Remove a candidature
     *
     * @param idCandidature id The id of the candidature to be removed
     */
    @DELETE
    @Path("/{uid}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces("text/plain")
    public synchronized String deleteCandidature(@PathParam("sid") String sid, @PathParam("uid") String idCandidature) {
        /*Si l'agent est effectivement connecté*/
        String message = null;
        if (this.controleur.exist(sid)) {

            boolean deleted = this.controleur.supprCandidature(idCandidature);
            // on regarde dans la liste des candidatures s'il existe et on le supprime
            if (deleted) {
                message = "Candidature " + idCandidature + " deleted";
            } else {
                message = "Candidature is not deleted, because it don't exist.";
            }
        }
        return message;
    }//End String deleteCandidature

    /**
     * Display details of a candidature by session
     * Variables to send: sid & idSessionUV
     * Response : Agent name, candidature...
     */
    @GET
    @Path("/liste")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public synchronized List<CandidaturesDTO> detailCandidature
    (@PathParam("sid") String sid, @QueryParam("idSessionUV") String idSessionUV) {

        candidaturesList = this.controleur.getListCandidaturesDTO();
        List<CandidaturesDTO> candidatureBySession = new ArrayList<CandidaturesDTO>();
        /*Si l'agent est effectivement connecté*/
        if (this.controleur.exist(sid)) {
            /*on recupère la liste des candidatures*/
            for (CandidaturesDTO candidature : candidaturesList) {
                if (candidature.getIdSessionUV().equals(idSessionUV)) {
                    candidatureBySession.add(candidature);
                }
            }
        }
        return candidatureBySession;
    }//End List<Candidature> detailCandidature

    /**
     * Modifie une candidature
     * Variables to send: sid, idSessionUV, idCandidature, statutCandidature
     */
    @POST
    @Path("/{uid}/qualifierCand")
    /**C/:sid/candidatures/qualifierCand?idSUV=:idSessionUV&idCand=:idCandidature&statCand="accepter"*/
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces("text/plain")
    public synchronized String setCandidature
    (@PathParam("sid") String sid, @PathParam("uid") String idCandidature, @QueryParam("statut") String statut) {
        String message = null;

        /*Si l'agent est effectivement connecté*/
        if (this.controleur.exist(sid)) {
            // On recupère l'id de l'agent Connecté
            String idA = this.controleur.getIdaTableAuthentif(sid);
            // On vérifie qu'il est gestionnaire
            if (this.controleur.estGestionnaire(idA)) {
                //On vérifie que la candidature existe
                if (this.controleur.existCandidature(idCandidature)) {
                    //on modifie le statut demandé
                    this.controleur.modifStatutCandidatur(idCandidature, statut);
                    message = idCandidature + " : Statut is modified ";
                } else {
                    message = " The specified candidature doesn't exist";
                }
            } else {
                message = " You are not allowed to modified candidature";
            }
        }
        return message;
    }//End String acceptCandidature

    @POST
    @Path("/repositionerCand")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public synchronized List<CandidaturesDTO> setListeAttente
            (@PathParam("sid") String sid, @QueryParam("idSessionUV") String idSession,
             @QueryParam("idCand") String idCandidature, @QueryParam("nouvellePosition") int position) {

        List<CandidaturesDTO> listToSend = new ArrayList<CandidaturesDTO>();
        if (this.controleur.exist(sid)) {
            if (this.controleur.existCandidature(idCandidature)) {
                this.controleur.modifListAttente(idSession, idCandidature, position);
                listToSend = this.controleur.getListCandSession(idSession);
            }
        }
        return listToSend;
    }

} //End CandidatureManager
