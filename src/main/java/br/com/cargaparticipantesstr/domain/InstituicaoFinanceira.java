package br.com.cargaparticipantesstr.domain;

public class InstituicaoFinanceira {
    private String ispb;
    private String nomeReduzido;
    private String codigo;
    private String participaCompensacao;
    private String acessoPrincipal;
    private String nome;
    private String inicioOperacao;

    public String getCodigo() {
	return codigo;
    }

    public void setCodigo(String codigo) {
	this.codigo = codigo;
    }

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public String getNomeReduzido() {
	return nomeReduzido;
    }

    public void setNomeReduzido(String nomeReduzido) {
	this.nomeReduzido = nomeReduzido;
    }

    public String getParticipaCompensacao() {
	return participaCompensacao;
    }

    public void setParticipaCompensacao(String participaCompensacao) {
	this.participaCompensacao = participaCompensacao;
    }

    public String getAcessoPrincipal() {
	return acessoPrincipal;
    }

    public void setAcessoPrincipal(String acessoPrincipal) {
	this.acessoPrincipal = acessoPrincipal;
    }

    public String getInicioOperacao() {
	return inicioOperacao;
    }

    public void setInicioOperacao(String inicioOperacao) {
	this.inicioOperacao = inicioOperacao;
    }

    public String getIspb() {
	return ispb;
    }

    public void setIspb(String ispb) {
	this.ispb = ispb;
    }
}
