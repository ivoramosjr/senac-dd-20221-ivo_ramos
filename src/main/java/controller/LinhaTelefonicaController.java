package controller;

import java.util.ArrayList;

import model.bo.LinhaTelefonicaBO;
import model.entity.LinhaTelefonica;

public class LinhaTelefonicaController {
	
	LinhaTelefonicaBO linhaTelefonicaBO = new LinhaTelefonicaBO();
	
	public ArrayList<LinhaTelefonica> listAll(){
		return linhaTelefonicaBO.consultarTodos();
	}

	public void desativarLinhaTelefonica(Integer id) {
		linhaTelefonicaBO.desativarLinhaTelefonica(id);		
	}

	public void ativarLinhaTelefonica(Integer idTelefone, Integer idCliente) {
		linhaTelefonicaBO.ativarLinhaTelefonica(idTelefone, idCliente);
	}
	
}
