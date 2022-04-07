package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import model.bo.TelefoneBO;
import model.entity.Telefone;

public class TelefoneController {
	
	private TelefoneBO telefoneBO = new TelefoneBO();
	
	public ArrayList<Telefone> listAll(){
		return telefoneBO.listAll();
	}

	public void desativarTelefone(Integer id) throws SQLException {
		telefoneBO.desativarTelefone(id);
	}

	public void ativarTelefone(Integer id) throws SQLException {
		telefoneBO.ativarTelefone(id);
	}
	
}
