package com.guilhermecamargo.testeuol.domain.services;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guilhermecamargo.testeuol.domain.entities.Jogador;
import com.guilhermecamargo.testeuol.domain.entities.dto.Grupo;
import com.guilhermecamargo.testeuol.domain.entities.dto.Heroi;
import com.guilhermecamargo.testeuol.domain.enums.EnumGrupo;
import com.guilhermecamargo.testeuol.infra.connectors.ConnectorSup;
import com.guilhermecamargo.testeuol.infra.exceptions.CodinomeExceptions;
import com.guilhermecamargo.testeuol.infra.repositories.JogadorRepository;

/**
 * Classe responsável pelos serviços necessários da entidade
 * Jogador.
 * @author guicamargo
 */
@Service
public class JogadorService implements Serializable {

    @Autowired
    private JogadorRepository jogadorRepository;
    @Autowired
    private ConnectorSup connectionService;

    public List<Jogador> findAll(){
        return this.jogadorRepository.findAll();
    }

    public Jogador findById(Long id){
        return this.jogadorRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public void create(Jogador jogador) throws CodinomeExceptions {
        this.validaCodinome(jogador);
        this.jogadorRepository.save(jogador);
    }

    @Transactional
    public void deleteById(Long id){
        this.jogadorRepository.deleteById(id);
    }

    private void validaCodinome(Jogador jogador) throws CodinomeExceptions {
        Set<String> codinomesJogadoresBanco = this.jogadorRepository.findJogadorByGrupoEquals(jogador.getGrupo()).stream().map(Jogador::getCodinome).collect(Collectors.toSet());


        Grupo grupo = this.connectionService.getObjectRequest(jogador.getGrupo());

        Set<String> codinomesGrupo = (jogador.getGrupo().equals(EnumGrupo.LIGA_DA_JUSTICA)) ? grupo.getJusticeiros().stream().collect(Collectors.toSet())
                : grupo.getVingadores().stream().map(Heroi::getCodinome).collect(Collectors.toSet());

        //caso não tenha nenhum jogador que pertence a esse grupo cadastrado no banco, pega o primeiro valor
        if (codinomesJogadoresBanco.isEmpty()) {
            jogador.setCodinome(codinomesGrupo.iterator().next());
        } else {
            /**
             * caso já tenha jogadores no banco, verifica um que esteja disponível
             * caso não tenha, levanta exceção
             **/
            Optional<String> codinomeEscolhido = codinomesGrupo.stream().filter(nome -> !codinomesJogadoresBanco.contains(nome)).findAny();

            if (codinomeEscolhido.isPresent()) {
                jogador.setCodinome(codinomeEscolhido.get());
            } else {
                throw new CodinomeExceptions("Codinomes indisponíveis.");
            }
        }
    }
}
