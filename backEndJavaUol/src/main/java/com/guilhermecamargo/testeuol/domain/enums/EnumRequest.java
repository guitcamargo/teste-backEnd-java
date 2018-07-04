package com.guilhermecamargo.testeuol.domain.enums;

import java.io.Serializable;

import com.guilhermecamargo.testeuol.domain.entities.dto.Grupo;
import com.guilhermecamargo.testeuol.domain.entities.dto.LigaDaJusticaResponse;

/**
 * Enum responsável por salvar os dados de 
 * captura dos herois de acordo com a tipagem do retorno
 * e a classe responsável por serealizar o valor.
 * @author guicamargo
 */
public enum EnumRequest implements Serializable {

    JSON("https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/vingadores.json" ,"application/json", Grupo.class),
    XML("https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/liga_da_justica.xml", "application/xml", LigaDaJusticaResponse.class);

    private String url;
    private String acceptRequest;
    private Class typeConvert;

    EnumRequest(String url, String acceptRequest, Class typeConvert){
        this.url = url;
        this.acceptRequest = acceptRequest;
        this.typeConvert = typeConvert;
    }

    public String getUrl(){
        return this.url;
    }

    public String getAcceptRequest() {
        return this.acceptRequest;
    }

    public Class getTypeConvert() {
        return typeConvert;
    }

}
