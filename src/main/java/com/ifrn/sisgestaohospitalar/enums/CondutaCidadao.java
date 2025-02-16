package com.ifrn.sisgestaohospitalar.enums;

public enum CondutaCidadao {

	UBS("UBS", "O Cidadão foi liberado e orientado a procurar uma UBS"),
	ALTAEPISODIOAPOSPRESCRICAO("Liberar após administrar prescrição",
			"O Cidadão pode ser liberado após a administração da prescrição"),
	TRANSFERIDO("Transferido", "Transferido para serviço de maior complexidade"),
	OBSERVACAO("Observação", "Manter paciente em observação"), LIBERADO("Liberado", "Cidadão liberado do atendimento"),
	NAOAGUARDOUATENDIMENTO("Não aguardou atendimento", "O Cidadão deixou a unidade sem aguardar atendimento");

	private String nome;
	private String descricao;

	private CondutaCidadao(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
