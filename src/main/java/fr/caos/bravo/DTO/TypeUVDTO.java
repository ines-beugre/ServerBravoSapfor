package fr.caos.bravo.DTO;

import fr.caos.bravo.Metier.TypeUV;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class TypeUVDTO {

    private String label;
    private int effectifMax;
    private int effectifMin;
    private List<TypeUVDTO> prerequis;

    public TypeUVDTO() {
    }

    public TypeUVDTO(String label, int max) {
        this.label = label;
        this.effectifMax = max;
        this.effectifMin = 1;
        this.prerequis = new ArrayList<TypeUVDTO>();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getEffectifMax() {
        return effectifMax;
    }

    public void setEffectifMax(int effectifMax) {
        this.effectifMax = effectifMax;
    }

    public int getEffectifMin() {
        return effectifMin;
    }

    public void setEffectifMin(int effectifMin) {
        this.effectifMin = effectifMin;
    }

    public List<TypeUVDTO> getPrerequis() {
        return prerequis;
    }

    public void setPrerequis(List<TypeUVDTO> prerequis) {
        this.prerequis = prerequis;
    }
}
