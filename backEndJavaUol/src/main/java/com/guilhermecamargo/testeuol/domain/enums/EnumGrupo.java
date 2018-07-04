package com.guilhermecamargo.testeuol.domain.enums;

import java.io.Serializable;
import java.util.stream.Stream;

/**
 * Enum responsável por mapear qual é o grupo do jogador
 * e quais os dados necessários para obter a lista.
 * 
 * @author guicamargo
 *
 */
public enum EnumGrupo  implements Serializable {

    VINGADORES(0, "Vingadores" ,EnumRequest.JSON),
    LIGA_DA_JUSTICA(1, "Liga da Justiça", EnumRequest.XML);

    private int id;
    private String descricao;
    private EnumRequest enumRequest;

    EnumGrupo(int id, String descricao, EnumRequest enumRequest){
        this.id = id;
        this.descricao = descricao;
        this.enumRequest = enumRequest;
    }

    public static EnumGrupo getById(int id) {
        return Stream.of(EnumGrupo.values())
                .filter(e -> id == e.getId()).findAny()
                .orElseThrow(() -> new EnumConstantNotPresentException(EnumGrupo.class, String.valueOf(id)));
    }

    public int getId() {
        return this.id;
    }

    public String getDescricao(){
        return this.descricao;
    }

    public EnumRequest getEnumRequest() {
        return this.enumRequest;
    }

}
