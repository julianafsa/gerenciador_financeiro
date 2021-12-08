package br.com.dh.enumerations;

public enum TipoMovimentacao {
	
	DESPESA("Despesa"),
    RECEITA("Receita");
	
    private String descricao;

    TipoMovimentacao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
