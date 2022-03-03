package model.entity;

public class Endereco {

	private int id;
	private String rua;
	private int numero;
	private String cidade;
	private String uf;
	private String cep;
	
	public Endereco() {
		
	}
	
	public Endereco(int id, String rua, int numero, String cidade, String uf, String cep) {
		super();
		this.id = id;
		this.rua = rua;
		this.numero = numero;
		this.cidade = cidade;
		this.uf = uf;
		this.cep = cep;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
	
	@Override
	public String toString() {
		return String.format("Rua: "+this.rua+"%n"
				+ "Número: "+this.numero+"%n"
				+ "Cidade: "+this.cidade+"%n"
				+ "UF: "+this.uf+"%n"
				+ "CEP: "+this.cep);
	}
}
