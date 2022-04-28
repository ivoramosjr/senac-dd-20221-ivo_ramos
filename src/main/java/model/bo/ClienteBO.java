package model.bo;

import java.util.ArrayList;
import java.util.List;

import model.dao.ClienteDAO;
import model.entity.Cliente;
import model.exception.ClienteComLinhaTelefonicaException;
import model.exception.ErroAoSalvarClienteException;

public class ClienteBO {

	public ClienteDAO dao = new ClienteDAO();
	
	public String salvar(Cliente novo) throws ErroAoSalvarClienteException {
		String mensagem = "";
		
		if(novo.getId() > 0) {
			if(dao.atualizar(novo)) {
				mensagem = "Cliente atualizado com sucesso";
			}else {
				throw new ErroAoSalvarClienteException(
						"Erro ao atualizars cliente, entre em contato com o suporte");
			}
		}else {
			if(dao.cpfJaCadastrado(novo.getCpf())) {
				throw new ErroAoSalvarClienteException(
						"CPF informado (" + novo.getCpf() + ") já foi utilizado");
			}else {
				novo = dao.inserir(novo);
				
				if(novo.getId() > 0) {
					mensagem = "Cliente cadastrado com sucesso (id: " + novo.getId() + ")";
				}else {
					throw new ErroAoSalvarClienteException(
							"Erro ao cadastrar cliente, entre em contato com o suporte");
				}
			}
		}
		return mensagem;
	}

	public ArrayList<Cliente> consultarTodos() {
		return dao.consultarTodos();
	}

	/**
	 * Exclui um cliente, somente se ele não possuir nenhuma linha telefônica
	 * @param clienteParaExcluir o cliente a ser excluído
	 * @return se excluiu ou não
	 * @throws ClienteComLinhaTelefonicaException lançada quando o cliente possui linha(s)
	 */
	public boolean excluir(Cliente clienteParaExcluir) throws ClienteComLinhaTelefonicaException {
		boolean excluiu;
		
		if(clienteParaExcluir.getLinhas().size() > 0) {
			throw new ClienteComLinhaTelefonicaException("Cliente informado não pode ser excluído,"
					+ " pois possui linha(s) telefônica(s)");
		}else {
			excluiu = dao.remover(clienteParaExcluir.getId());
		}
		
		return excluiu;
	}

	public Cliente getClienteByIdTelefone(int id) {
		return dao.getClienteByIdTelefone(id);
	}

	public Integer getIdClienteByIdTelefone(int id) {
		return dao.getIdClienteByIdTelefone(id);
	}

	public Cliente getClienteByCpf(String cpf) {
		List<String> erros = new ArrayList<>();
		if (cpf == null)
			erros.add("CPF não pode estar nulo!");
		
		if (cpf.isEmpty())
			erros.add("CPF não pode ser vazio!");
		
		if (cpf.length() != 11)
			erros.add("CPF dever conter 11 dígitos!");
		
		return dao.getClienteByCpf(cpf);
	}
	
	
	
	
	
	
	
	
	
	
}
