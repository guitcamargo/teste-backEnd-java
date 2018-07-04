package com.guilhermecamargo.testeuol.domain.entities.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classe responsável por ser a entidade principal
 * do xml que é consumido da liga da justiça.
 * 
 * @author guicamargo
 */
@XmlRootElement(name ="liga_da_justica")
public class LigaDaJusticaResponse implements Serializable {

	private static final long serialVersionUID = -1931591551240938129L;

	private Grupo grupo;

    public Grupo getGrupo() {
        return grupo;
    }

    @XmlElement(name = "codinomes")
    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
}
