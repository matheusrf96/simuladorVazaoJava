package br.unincor.model;

public class PackWifi {
	
	private Long header;
	private Long dados;
	private Long trailer;
	
	public PackWifi() {
		super();
		this.header = 38L;
		this.dados = (long)(Math.random()*2266+46);
		this.trailer = 16L;
	}
	
	public Long getHeader() {
		return header;
	}

	public void setHeader(Long header) {
		this.header = 38L;
	}

	public Long getTrailer() {
		return trailer;
	}

	public void setTrailer(Long trailer) {
		this.trailer = 16L;
	}

	public Long getDados(){
		return this.dados;
	}
	
	public void setDados(Long dados) {
		if (dados >= 46 && dados <= 2266)
			this.dados = dados;
		else
			this.dados = (long)(Math.random()*2266+46);
	}
	
	public Long tamanhoTotal(){
		return this.header + this.dados + this.trailer;
	}
	
}

