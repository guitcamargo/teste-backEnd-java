package com.guilhermecamargo.testeuol.domain.entities;

import org.hibernate.validator.constraints.Email;

import com.guilhermecamargo.testeuol.domain.enums.EnumGrupo;
import com.guilhermecamargo.testeuol.infra.converters.EnumGrupoConverter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Entidade principal do sistema.
 * 
 * @author guilhermecamargo
 */
@Entity
@Table(name = "JOGADOR")
public class Jogador implements Serializable {

	private static final long serialVersionUID = 4182019617826616903L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(name = "nome", length = 255)
	private String nome;

	@NotNull
	@Column(name = "email")
	@Email
	private String email;

	@Column(name = "telefone")
	private String telefone;

	@Column(name = "codinome")
	private String codinome;

	@NotNull
	@Column(name = "grupo")
	@Convert(converter = EnumGrupoConverter.class)
	private EnumGrupo grupo;

	public Jogador() {
	}

	public Jogador(Long id) {
		this.id = id;
	}

	public Jogador(String nome, String email, String telefone, EnumGrupo grupo) {
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.grupo = grupo;
	}

	public Jogador(String nome, String email, String telefone, String codinome, EnumGrupo grupo) {
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.codinome = codinome;
		this.grupo = grupo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCodinome() {
		return codinome;
	}

	public void setCodinome(String codinome) {
		this.codinome = codinome;
	}

	public EnumGrupo getGrupo() {
		return grupo;
	}

	public void setGrupo(EnumGrupo grupo) {
		this.grupo = grupo;
	}
	
}