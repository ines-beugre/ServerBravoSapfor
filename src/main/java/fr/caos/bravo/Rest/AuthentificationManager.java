package fr.caos.bravo.Rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

import fr.caos.bravo.autre.Credentials;
import fr.caos.bravo.DTO.AgentsDTO;
import fr.caos.bravo.Metier.Agents;

/**
 * Equipe Bravo
 * Manager d'authentification
 */

@Path("/authentification")
public class AuthentificationManager {

    private ControleurMetierImple controleur;

    public AuthentificationManager() {

        controleur = ControleurMetierImple.getControleur();

    }


    /**
     * Methode appelé lors d'une requête GET pour la connexion
     * @param log
     * @param mdp
     * @param request
     * @return
     */
    /*@GET
    @Path("/login")
    @Produces("text/plain")
    public String authentification
            (@QueryParam("log") String log, @QueryParam("mdp") String mdp, @Context HttpServletRequest request){
            // on vérifie la validité des identifiants
            String idA = this.controleur.existAgent(log,mdp);
            if(idA != null){
                HttpSession si = request.getSession();
                // on le stock sur le serveur au niveau du controleur
                //Controleur.tableauAuthentification.put(si.getId(),a.getIdAgent());
                controleur.setTableAuthentif(si.getId(),idA);
                // on le retourne au client pour qu'il puisse l'utiliser dans ses prochaines requêtes
                return si.getId() ;
            }
        return null;
    }*/

    /**
     * Methode appelé lors d'une requête POST pour la connexion
     *
     * @param login
     * @param mdp
     * @param request
     * @return
     */
    @POST
    @Path("/login")
    @Produces("text/plain")
    public String authorization
    (@QueryParam("log") String login, @QueryParam("mdp") String mdp, @Context HttpServletRequest request) {
        String idA = this.controleur.existAgent(login, mdp);
        if (idA != null) {
            HttpSession si = request.getSession();
            // on le stock sur le serveur au niveau du controleur
            //Controleur.tableauAuthentification.put(si.getId(),a.getIdAgent());
            controleur.setTableAuthentif(si.getId(), idA);
            // on le retourne au client pour qu'il puisse l'utiliser dans ses prochaines requêtes
            return si.getId();
        }
        return null;
    }

    /**
     * Méthode appelée par une requête POST pour se déconnecter
     *
     * @param sid
     * @return
     */
    @POST
    @Path("{sid}/logout")
    @Produces("text/plain")
    public String logout(@PathParam("sid") String sid) {
        String message = "mauvais sid";
        // si le SID est valide alors on modifie la table des agents connectés
        if (this.controleur.exist(sid)) {
            message = "Deconnexion réussie";
            this.controleur.deleteSid(sid);
        }
        return message;
    }


}
