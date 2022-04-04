package model.bo;

import java.util.ArrayList;

import model.dao.TelefoneDAO;
import model.entity.Telefone;

public class TelefoneBO {
	
	private TelefoneDAO telefoneDAO = new TelefoneDAO();

	public ArrayList<Telefone> listAll() {
		return telefoneDAO.consultarTodos();
	}
}
