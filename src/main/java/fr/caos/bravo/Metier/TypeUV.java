package fr.caos.bravo.Metier;

/**
 * Equipe Bravo :
 * Classe TypeUV
 *
 * @param String label : nom de l'UV
 * @param int effectifMax : nombre max de participants à la session
 * @param int effectifMin : nombre min de participants à la session
 * @param List<TypeUV> : Liste des UV prerequis pour y participer
 */

import fr.caos.bravo.DTO.TypeUVDTO;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class TypeUV {

    private String label;
    private int effectifMax;
    private int effectifMin;
    private List<TypeUV> prerequis;

    public TypeUV() {
    }

    ;

    public TypeUV(String label, int max) {
        this.label = label;
        this.effectifMax = max;
        this.effectifMin = 1;
        this.prerequis = new ArrayList<TypeUV>();

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

    public List<TypeUV> getPrerequis() {
        return prerequis;
    }

    public void setPrerequis(TypeUV prerequis) {
        this.prerequis.add(prerequis);
    }

    public TypeUVDTO toDto() {
        TypeUVDTO typeUVDTO = new TypeUVDTO();
        typeUVDTO.setEffectifMax(getEffectifMax());
        typeUVDTO.setEffectifMin(getEffectifMin());
        typeUVDTO.setLabel(getLabel());
        List<TypeUVDTO> listTypeUVDto = new ArrayList<TypeUVDTO>();
        for (TypeUV t : prerequis) {
            listTypeUVDto.add(t.toDto());
        }
        typeUVDTO.setPrerequis(listTypeUVDto);

        return typeUVDTO;
    }
}
