package com.guilhermecamargo.testeuol.infra.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.guilhermecamargo.testeuol.domain.enums.EnumGrupo;

@Converter(autoApply = true)
public class EnumGrupoConverter implements AttributeConverter<EnumGrupo, Integer> {

    @Override
    public Integer convertToDatabaseColumn(EnumGrupo mes) {
        return mes == null ? null : mes.getId();
    }

    @Override
    public EnumGrupo convertToEntityAttribute(Integer mes) {
        return mes == null ? null : EnumGrupo.getById(mes);
    }

}
