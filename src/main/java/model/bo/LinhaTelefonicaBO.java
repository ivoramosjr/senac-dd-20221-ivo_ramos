package model.bo;

import java.util.ArrayList;

import model.dao.LinhaTelefonicaDAO;
import model.entity.LinhaTelefonica;

public class LinhaTelefonicaBO {
	
	LinhaTelefonicaDAO dao = new LinhaTelefonicaDAO();

	public ArrayList<LinhaTelefonica> consultarTodos() {
		return dao.consultarTodos();
	}

}
