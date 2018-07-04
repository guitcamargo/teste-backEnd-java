package com.guilhermecamargo.testeuol.infra.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guilhermecamargo.testeuol.domain.entities.Jogador;
import com.guilhermecamargo.testeuol.domain.enums.EnumGrupo;

/**
 * Interface responsável pelo repositório da entidade Jogador
 * @author guicamargo
 *
 */
@Repository
public interface JogadorRepository  extends JpaRepository<Jogador, Long> {

    Optional<Jogador> findJogadorByCodinomeEqualsAndGrupo(String codigNome, EnumGrupo grupo);

    List<Jogador> findJogadorByGrupoEquals(EnumGrupo grupo);

}