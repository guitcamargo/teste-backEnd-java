package com.guilhermecamargo.testeuol.domain.entities.dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Entidade responsável por salvar a lista de acordo com o consumo
 * Json -> Lista de herois
 * Xml -> Lista de Strings, com os nomes dos herois da liga da justiça
 * @author guicamargo
 */
@XmlRootElement(name = "codinomes")
public class Grupo implements Serializable {

	private static final long serialVersionUID = -2276238068916625325L;

	@JsonProperty("vingadores")
    private List<Heroi> vingadores;

    private List<String> justiceiros;

    public List<Heroi> getVingadores() {
        return vingadores;
    }

    public void setVingadores(List<Heroi> vingadores) {
        this.vingadores = vingadores;
    }

    @XmlElement(name = "codinome")
    public List<String> getJusticeiros() {
        return justiceiros;
    }

    public void setJusticeiros(List<String> justiceiros) {
        this.justiceiros = justiceiros;
    }
}