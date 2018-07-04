package com.guilhermecamargo.testeuol.domain.entities.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Entidade responsável por salvar 
 * os nomes dos heróis.
 * Mapeamento JSON.
 * 
 * @author guicamargo
 */
public class Heroi implements Serializable {

	private static final long serialVersionUID = 443931505859329163L;
	
	@JsonProperty("codinome")
    private String codinome;

    public String getCodinome() {
        return codinome;
    }

    public void setCodinome(String codinome) {
        this.codinome = codinome;
    }

}
