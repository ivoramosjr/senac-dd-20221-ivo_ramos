package executavel;

import java.util.ArrayList;
import java.util.List;

import model.entity.Cliente;
import model.entity.Endereco;
import model.entity.Telefone;

public class ExecutavelExercicioAula4 {

	public static void main(String[] args) {
		
		List<Cliente> clientes = new ArrayList<Cliente>();
		
		Endereco endereco1 = new Endereco("Rua das margaridas", "75","Floripa","SC","012345123");
		
		List<Telefone> telefones = new ArrayList<Telefone>();
		Telefone t1 = new Telefone("48","9090-8989", 1, true);
		Telefone t2 = new Telefone("48","8989-1535", 2, true);

		telefones.add(t1);
		telefones.add(t2);
		
		Cliente jorge = new Cliente("Jorge","12345678901", telefones, endereco1);
		
		clientes.add(jorge);
		
		jorge.mostrarTelefones();
	}

}
