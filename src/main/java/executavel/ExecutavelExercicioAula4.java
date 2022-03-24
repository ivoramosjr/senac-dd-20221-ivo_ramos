package executavel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JOptionPane;

import model.dao.ClienteDAO;
import model.entity.Cliente;
import model.entity.Endereco;
import model.entity.Telefone;

public class ExecutavelExercicioAula4 {

	public static void main(String[] args) {
		List<Cliente> clientes = new ArrayList<>();
		Cliente cliente = (Cliente) JOptionPane.showInputDialog(null, "Escolha o cliente que deseja excluir", "Exclusão cliente", 1, null, clientes.toArray(), clientes.toArray()[0]);
	}

}
