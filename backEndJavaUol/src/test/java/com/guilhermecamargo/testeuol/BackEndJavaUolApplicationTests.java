package com.guilhermecamargo.testeuol;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.guilhermecamargo.testeuol.domain.entities.Jogador;
import com.guilhermecamargo.testeuol.domain.entities.dto.Grupo;
import com.guilhermecamargo.testeuol.domain.entities.dto.Heroi;
import com.guilhermecamargo.testeuol.domain.enums.EnumGrupo;
import com.guilhermecamargo.testeuol.domain.services.JogadorService;
import com.guilhermecamargo.testeuol.infra.connectors.ConnectorSup;
import com.guilhermecamargo.testeuol.infra.exceptions.CodinomeExceptions;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BackEndJavaUolApplicationTests {
	

	@Autowired
	private JogadorService jogadorService;
	
	@Autowired
	private ConnectorSup connectionSuport;

	
	@Test
	public void salvaJogadores() throws CodinomeExceptions{
		Jogador justiceiro = new Jogador();
		justiceiro.setId(1L);
		justiceiro.setNome("Guilherme Theodoro Camargo");
		justiceiro.setGrupo(EnumGrupo.LIGA_DA_JUSTICA);
		justiceiro.setEmail("gtheodorocamargo@gmail.com");
		jogadorService.create(justiceiro);
		
		Jogador vingador = new Jogador();
		vingador.setId(2L);
		vingador.setNome("Guilherme Theodoro Camargo");
		vingador.setGrupo(EnumGrupo.VINGADORES);
		vingador.setEmail("gtheodorocamargo@gmail.com");
		jogadorService.create(vingador);
		
		List<Jogador> jogadores = jogadorService.findAll();
		
		assertThat(jogadores.size()).isEqualTo(2L);
		assertThat(jogadores.get(0).getGrupo()).isEqualTo(EnumGrupo.LIGA_DA_JUSTICA);
		assertThat(jogadores.get(1).getGrupo()).isEqualTo(EnumGrupo.VINGADORES);
	}
	
	@Test
	public void getCodinomes(){
		Grupo grupoLigaDaJustica = connectionSuport.getObjectRequest(EnumGrupo.LIGA_DA_JUSTICA);
		Grupo grupoVingadores = connectionSuport.getObjectRequest(EnumGrupo.VINGADORES);
		
		Set<String> codinomesJusticeiros = grupoLigaDaJustica.getJusticeiros().stream().collect(Collectors.toSet());
		Set<String> codinomesVingadores = grupoVingadores.getVingadores().stream().map(Heroi::getCodinome).collect(Collectors.toSet());
		
		assertThat(grupoLigaDaJustica.getJusticeiros().size()).isEqualTo(6L);
		assertThat(grupoVingadores.getVingadores().size()).isEqualTo(7L);
		assertThat(codinomesJusticeiros).contains("Mulher Maravilha");
		assertThat(codinomesVingadores).contains("Hulk");
	}

}
