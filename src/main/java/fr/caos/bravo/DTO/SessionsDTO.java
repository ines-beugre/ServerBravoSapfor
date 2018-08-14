package fr.caos.bravo.DTO;

/**
 * Equipe Bravo :
 * DTO Session côté server
 *
 * @param String idSession : identifiant de session
 * @param String intitule : nom de session
 * @param List<String> dates : liste des dates de la session
 */

import fr.caos.bravo.Metier.Candidatures;
import fr.caos.bravo.Metier.TypeUV;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@XmlRootElement
public class SessionsDTO {

    private String idSession;
    private String intitule;
    private List<String> dates;
    private List<CandidaturesDTO> candidaturesDTOList;
    private TypeUVDTO typeUVDTO;


    /**
     * Donne un identifiant unique généré automatiquement
     *
     * @return String idSession
     */
    public String getIdSession() {
        return this.idSession;
    }

    public void setIdSession(String idSession) {
        this.idSession = idSession;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String label) {
        this.intitule = label;
    }

    public List<String> getDates() {
        return dates;
    }

    public void setDates(String date) {
        this.dates.add(date);
    }

    public List<CandidaturesDTO> getCandidaturesDTOList() {
        return candidaturesDTOList;
    }

    public void setCandidaturesDTOList(List<CandidaturesDTO> list) {
        this.candidaturesDTOList = list;
    }

    public void setDates(List<String> dates) {
        this.dates = dates;
    }

    public TypeUVDTO getTypeUVDTO() {
        return typeUVDTO;
    }

    public void setTypeUVDTO(TypeUVDTO typeUVDTO) {
        this.typeUVDTO = typeUVDTO;
    }

    public SessionsDTO() {
    }

    public SessionsDTO(String label) {
        this.intitule = label;
        dates = new ArrayList<String>();
        candidaturesDTOList = new LinkedList<CandidaturesDTO>();
        this.typeUVDTO = new TypeUVDTO();
    }
}
