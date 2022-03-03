package model.entity;

public class Telefone {

	private int id;
	private String ddd;
	private String numero;
	private int tipo; // alterar para enum
	private boolean ativo;
	
	public Telefone(int id, String ddd, String numero, int tipo) {
		super();
		this.id = id;
		this.ddd = ddd;
		this.numero = numero;
		this.tipo = tipo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public String toString() {
		return String.format("Telefone%n"
				+ "Id: " + id + "%n"
				+ "DDD: " + ddd + "%n"
				+ "Número: " + numero + "%n"
				+ "Tipo: " + tipo + "%n"
				+ "Ativo: " + ativo);
	}
	
}
