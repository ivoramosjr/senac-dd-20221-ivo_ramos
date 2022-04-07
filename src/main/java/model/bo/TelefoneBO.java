package model.bo;

import java.sql.SQLException;
import java.util.ArrayList;

import controller.LinhaTelefonicaController;
import model.dao.LinhaTelefonicaDAO;
import model.dao.TelefoneDAO;
import model.entity.Telefone;

public class TelefoneBO {
	
	private TelefoneDAO telefoneDAO = new TelefoneDAO();
	private LinhaTelefonicaDAO linhaTelefonicaDAO = new LinhaTelefonicaDAO();

	public ArrayList<Telefone> listAll() {
		return telefoneDAO.consultarTodos();
	}

	public void desativarTelefone(Integer id) throws SQLException {
		if(id == null)
			throw new RuntimeException("Id do telefone está nulo!");
		
		telefoneDAO.desativarTelefone(id);
		linhaTelefonicaDAO.desativarLinhaTelefonica(id);
	}

	public void ativarTelefone(Integer id) throws SQLException {
		if(id == null)
			throw new RuntimeException("Id do telefone está nulo!");
		
		telefoneDAO.ativarTelefone(id);
	}
}
