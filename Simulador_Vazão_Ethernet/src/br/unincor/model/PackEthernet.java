package br.unincor.model;

public class PackEthernet {
	
	private Long header;
	private Long dados;
	private Long trailer;
	
	public PackEthernet() {
		super();
		this.header = 14L;
		this.dados = (long)(Math.random()*1454+46);
		this.trailer = 4L;
	}
	
	public Long tamanhoTotal(){
		return this.header + this.dados + this.trailer;
	}
	
	public Long getDados(){
		return this.dados;
	}

}
