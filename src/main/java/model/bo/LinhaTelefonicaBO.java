package model.bo;

import java.util.ArrayList;

import model.dao.LinhaTelefonicaDAO;
import model.entity.LinhaTelefonica;

public class LinhaTelefonicaBO {
	
	LinhaTelefonicaDAO dao = new LinhaTelefonicaDAO();

	public ArrayList<LinhaTelefonica> consultarTodos() {
		return dao.consultarTodos();
	}

	public void desativarLinhaTelefonica(Integer id) {
		if(id == null)
			throw new RuntimeException("Id da linha telefonica está nulo!");
		
		dao.desativarLinhaTelefonica(id);
	}

	public void ativarLinhaTelefonica(Integer idTelefone, Integer idCliente) {
		if(idCliente == null)
			throw new RuntimeException("Id do cliente da linha telefonica está nulo!");
		if(idTelefone == null)
			throw new RuntimeException("Id do telefone da linha telefonica está nulo!");
		
		dao.ativarLinhaTelefonica(idTelefone, idCliente);
	}

}
