package fr.caos.bravo.Metier;

import java.util.ArrayList;
import java.util.List;

public class Agents {

    private String idAgent;
    private String login;
    private String motDePasse;
    private boolean gestionnaire;
    private List<TypeUV> UVObtenus;

    public Agents() {
        this.UVObtenus = new ArrayList<TypeUV>();
    }

    ;

    public Agents(String idAgent, String login, String motDePasse) {
        this.idAgent = idAgent;
        this.login = login;
        this.motDePasse = motDePasse;
        this.gestionnaire = false;
        this.UVObtenus = new ArrayList<TypeUV>();
    }

    public String getIdAgent() {
        return idAgent;
    }

    public void setIdAgent(String idAgent) {
        this.idAgent = idAgent;
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

    public boolean isGestionnaire() {
        return this.gestionnaire;
    }

    public void setGestionnaire(boolean gestionnaire) {
        this.gestionnaire = gestionnaire;
    }

    public List<TypeUV> getUVObtenus() {
        return UVObtenus;
    }

    public void setUVObtenus(List<TypeUV> UVObtenus) {
        this.UVObtenus = UVObtenus;
    }

    public void ajoutUVValide(TypeUV uv) {
        this.UVObtenus.add(uv);
    }
}
