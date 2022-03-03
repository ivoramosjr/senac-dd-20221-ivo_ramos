package model.entity;

public class Telefone {

	private int id;
	private String ddd;
	private String numero;
	private int tipo; // alterar para enum
	private boolean ativo;
	
	public Telefone(String ddd, String numero, int tipo, boolean ativo) {
		super();
		this.ddd = ddd;
		this.numero = numero;
		this.tipo = tipo;
		this.ativo = ativo;
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
				+ "DDD: " + ddd + "%n"
				+ "Número: " + numero + "%n"
				+ "Tipo: " + tipo + "%n"
				+ "Ativo: " + ativo + "%n");
	}
	
}
