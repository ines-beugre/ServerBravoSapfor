package fr.caos.bravo.Rest;

import com.sun.jersey.spi.resource.Singleton;
import fr.caos.bravo.DTO.CandidaturesDTO;
import fr.caos.bravo.DTO.SessionsDTO;
import fr.caos.bravo.DTO.TypeUVDTO;
import fr.caos.bravo.Metier.Agents;
import fr.caos.bravo.Metier.Candidatures;
import fr.caos.bravo.Metier.Sessions;
import fr.caos.bravo.Metier.TypeUV;

import java.util.*;

@Singleton
public class ControleurMetierImple implements ControleurMetier {

    /*
    static : sera accessible des differents managers
     */
    static ControleurMetierImple controleur;

    static ControleurMetierImple getControleur() {
        if (controleur == null) {
            controleur = new ControleurMetierImple();
        }
        return controleur;
    }

    /*
    Section dédiée à l'accès des Agents
     */
    private List<Agents> caserne = new ArrayList<Agents>();

    {
        caserne.add(new Agents("agent1", "login1", "mdp1"));
        caserne.get(0).setGestionnaire(true);
        caserne.add(new Agents("agent2", "login2", "mdp2"));
        caserne.add(new Agents("agent3", "login3", "mdp3"));
    }

    //HashMap avec l'id de session informatique en clé et l'id de l'agent en valeur
    private Map<String, String> tableAuthentif = new HashMap<String, String>();

    @Override
    public boolean exist(String sid) {
        return this.tableAuthentif.containsKey(sid);
    }

    @Override
    public void setTableAuthentif(String sid, String ida) {
        this.tableAuthentif.put(sid, ida);
    }

    @Override
    public String getIdaTableAuthentif(String sid) {
        return this.tableAuthentif.get(sid);
    }

    @Override
    public void deleteSid(String sid) {
        this.tableAuthentif.remove(sid);
    }

    @Override
    public String existAgent(String log, String mdp) {
        String res = null;
        for (Agents a : caserne) {
            if (a.getLogin().equals(log) && a.getMotDePasse().equals(mdp)) {
                res = a.getIdAgent();
                break;
            }
        }
        return res;
    }
/*
Section dédiée à l'accès aux Sessions
 */

    private List<TypeUV> typeUVMetier = new ArrayList<TypeUV>();

    {
        TypeUV typeUV1 = new TypeUV("Lance Incendie", 15);
        TypeUV typeUV2 = new TypeUV("Camion", 20);
        TypeUV typeUV3 = new TypeUV("Feu de Fôret", 15);
        typeUV3.setPrerequis(typeUV1);
        typeUV3.setPrerequis(typeUV2);

        TypeUV typeUV4 = new TypeUV("Premiers Secours", 10);
        TypeUV typeUV5 = new TypeUV("Sauvetage", 15);
        typeUV5.setPrerequis(typeUV4);

        typeUVMetier.add(typeUV1);
        typeUVMetier.add(typeUV2);
        typeUVMetier.add(typeUV3);
        typeUVMetier.add(typeUV4);
        typeUVMetier.add(typeUV5);

        caserne.get(0).ajoutUVValide(typeUV4);

    }

    @Override
    public List<TypeUV> getTypeUVMetier() {
        return typeUVMetier;
    }


    private List<Sessions> sessionsMetier = new ArrayList<Sessions>();

    {
        sessionsMetier.add(new Sessions("session1", typeUVMetier.get(0)));
        sessionsMetier.add(new Sessions("session2", typeUVMetier.get(1)));
        sessionsMetier.add(new Sessions("session3", typeUVMetier.get(2)));
        sessionsMetier.add(new Sessions("session4", typeUVMetier.get(3)));
        sessionsMetier.add(new Sessions("session5", typeUVMetier.get(4)));
    }

    @Override
    public boolean existSession(String idS) {
        boolean res = false;
        for (Sessions s : sessionsMetier) {
            if (s.getIdSession().equals(idS)) {
                res = true;
                break;
            }
        }
        return res;
    }

    @Override
    public List<SessionsDTO> getListSessionsDTO() {
        List<SessionsDTO> sessionsDTOList = new ArrayList<SessionsDTO>();
        for (Sessions s : sessionsMetier) {
            sessionsDTOList.add(s.toDTO());
        }
        return sessionsDTOList;
    }

    @Override
    public Sessions getSessions(String idSession) {
        Sessions session = null;
        for (Sessions s : sessionsMetier) {
            if (s.getIdSession().equals(idSession)) {
                session = s;
                break;
            }
        }
        return session;
    }

    @Override
    public List<CandidaturesDTO> getListCandSession(String idSession) {
        List<CandidaturesDTO> listTo = new ArrayList<CandidaturesDTO>();
        Sessions session = getSessions(idSession);
        for (Candidatures c : session.getListeCandidatures()) {
            listTo.add(c.toDTO());
        }
        return listTo;
    }

    /**
     * Affiche la liste des sessions accessibles par un agent
     *
     * @param idAgent
     * @return
     */
    @Override
    public List<SessionsDTO> getListSessionsAccessibles(String idAgent) {
        List<SessionsDTO> listSessionsDto = new ArrayList<SessionsDTO>();
        Agents agent = null;
        for (Agents a : caserne) {
            if (a.getIdAgent().equals(idAgent)) {
                agent = a;
                break;
            }
        }
        List<TypeUV> typeUVValide = agent.getUVObtenus();
        for (Sessions s : sessionsMetier) {
            List<TypeUV> typeUVprerq = s.getUVdelivre().getPrerequis();
            int compteur = typeUVprerq.size();
            for (TypeUV t : typeUVprerq) {
                if (typeUVValide.contains(t)) {
                    compteur--;
                }
            }
            if (compteur == 0 && !typeUVValide.contains(s.getUVdelivre())) {
                listSessionsDto.add(s.toDTO());
            }
        }
        return listSessionsDto;
    }

    /**
     * Retourne la liste des UV valides par un agent
     *
     * @param idAgent
     * @return
     */
    @Override
    public List<TypeUVDTO> getListUVValidees(String idAgent) {
        List<TypeUVDTO> typeUVDTOList = new ArrayList<TypeUVDTO>();
        Agents agent = new Agents();
        for (Agents a : caserne) {
            if (a.getIdAgent().equals(idAgent)) {
                agent = a;
                break;
            }
        }
        List<TypeUV> typeUVList = agent.getUVObtenus();
        for (TypeUV t : typeUVList) {
            typeUVDTOList.add(t.toDto());
        }
        return typeUVDTOList;
    }

/*
Section dédiée à la gestion des Candidatures
 */


    private List<Candidatures> candidaturesMetier = new ArrayList<Candidatures>();

    {
        candidaturesMetier.add(new Candidatures("agent1", "session1", "apprenant"));
        candidaturesMetier.get(0).setStatut("accepté");
        candidaturesMetier.add(new Candidatures("agent2", "session2", "apprenant"));
        candidaturesMetier.get(1).setStatut("en_attente");
        candidaturesMetier.add(new Candidatures("agent3", "session3", "apprenant"));
        candidaturesMetier.add(new Candidatures("agent1", "session2", "apprenant"));
        candidaturesMetier.add(new Candidatures("agent3", "session1", "apprenant"));
        candidaturesMetier.add(new Candidatures("agent3", "session2", "apprenant"));
        candidaturesMetier.get(candidaturesMetier.size() - 1).setStatut("en_attente");
        candidaturesMetier.add(new Candidatures("agent4", "session2", "apprenant"));
        candidaturesMetier.get(candidaturesMetier.size() - 1).setStatut("en_attente");

        for (Sessions s : sessionsMetier) {
            for (Candidatures c : candidaturesMetier) {
                if (c.getIdSession().equals(s.getIdSession()) && c.getStatut() == "en_attente") {
                    int index = s.getListeCandidatures().size() + 1;
                    c.setPosition(index);
                    s.ajoutCandidature(c);
                }
            }
        }
    }

    @Override
    public boolean existCandidature(String idCandidature) {
        boolean res = false;
        for (Candidatures c : candidaturesMetier) {
            if (c.getIdCandidature().equals(idCandidature)) {
                res = true;
                break;
            }
        }
        return res;
    }

    @Override
    public void ajoutCandidature(CandidaturesDTO cand) {
        Candidatures candidature = new Candidatures(cand.getIdAgent(), cand.getIdSessionUV(), cand.getQualite());
        candidature.setIdCandidature(cand.getIdCandidature());
        this.candidaturesMetier.add(candidature);
    }

    @Override
    public List<CandidaturesDTO> getListCandidaturesDTO() {
        List<CandidaturesDTO> candidaturesDTOS = new ArrayList<CandidaturesDTO>();
        for (Candidatures c : candidaturesMetier) {
            candidaturesDTOS.add(c.toDTO());
        }
        return candidaturesDTOS;
    }

    @Override
    public boolean supprCandidature(String idCand) {
        boolean deleted = false;
        Candidatures candtoRemove = null;
        for (Candidatures c : candidaturesMetier) {
            if (c.getIdCandidature().equals(idCand)) {
                candtoRemove = c;
                break;
            }
        }
        if (candtoRemove != null) {
            candidaturesMetier.remove(candtoRemove);
            deleted = true;
        }
        return deleted;
    }

    @Override
    public boolean estGestionnaire(String idA) {
        boolean res = false;
        for (Agents a : caserne) {
            if (a.isGestionnaire()) {
                res = true;
                break;
            }
        }
        return res;
    }

    @Override
    public void modifStatutCandidatur(String idCandidature, String statut) {
        for (Candidatures c : candidaturesMetier) {
            if (c.getIdCandidature().equals(idCandidature) && (c.getQualite() == "apprenant" || c.getQualite() == "formateur")) {
                c.setStatut(statut);
                break;
            }
        }
    }

    @Override
    public void modifListAttente(String sid, String cand, int rangListeAttente) {
        List<Candidatures> candidaturesList = null;
        Candidatures candidatures = null;

        candidaturesList = getSessions(sid).getListeCandidatures();
        for (Candidatures c : candidaturesList) {
            if (c.getIdCandidature().equals(cand)) {
                candidatures = c;
                candidaturesList.remove(c);
                break;
            }
        }
        candidaturesList.add(rangListeAttente - 1, candidatures);

        for (Candidatures c : candidaturesList) {
            c.setPosition(candidaturesList.indexOf(c) + 1);
        }

    }

}
