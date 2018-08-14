package fr.caos.bravo.autre;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Credentials {

    private String login;
    private String mdp;

    public Credentials(String login, String mdp) {
        this.login = login;
        this.mdp = mdp;
    }

    public String getLogin() {
        return login;
    }

    public String getMdp() {
        return mdp;
    }
}
