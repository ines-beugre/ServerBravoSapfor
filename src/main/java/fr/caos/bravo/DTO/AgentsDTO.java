package fr.caos.bravo.DTO;


/**
 * Equipe Bravo:
 * DTO d'agent
 *
 * @param String idAgent : identifiant de l'Agent
 * @param String nomAgent : nom de l'Agent
 * @param String login : pseudo de l'Agent pour se connecter
 * @param String motDePasse : mot de passe de l'Agent pour se connecter
 * @param List<TypeUVDTO> UVVdalie : Liste des UV valide par l'agent
 * @param boolean gestionnaire : true si agent est gestionnaire
 */

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class AgentsDTO {
    private String idAgent;
    private String nomAgent;
    private String login;
    private String motDePasse;
    private List<String> UVValide;
    private boolean gestionnaire;

    public AgentsDTO() {
    }

    public AgentsDTO(String nom, boolean gestionnaire) {
        this.nomAgent = nom;
        this.gestionnaire = gestionnaire;
        this.UVValide = new ArrayList<String>();
    }

    public String getIdAgent() {
        if (this.idAgent == null) {
            this.idAgent = Integer.toHexString(this.hashCode());
        }
        return idAgent;
    }

    public void setIdAgent(String idAgent) {
        this.idAgent = idAgent;
    }

    public String getNomAgent() {
        return nomAgent;
    }

    public void setNomAgent(String nomAgent) {
        this.nomAgent = nomAgent;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public List<String> getUVValide() {
        return UVValide;
    }

    public void setUVValide(List<String> UVValide) {
        this.UVValide = UVValide;
    }

    public boolean isGestionnaire() {
        return gestionnaire;
    }

    public void setGestionnaire(boolean gestionnaire) {
        this.gestionnaire = gestionnaire;
    }
}
