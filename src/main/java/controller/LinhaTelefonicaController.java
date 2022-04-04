package controller;

import java.util.ArrayList;

import model.bo.LinhaTelefonicaBO;
import model.entity.LinhaTelefonica;

public class LinhaTelefonicaController {
	
	LinhaTelefonicaBO linhaTelefonicaBO = new LinhaTelefonicaBO();
	
	public ArrayList<LinhaTelefonica> listAll(){
		return linhaTelefonicaBO.consultarTodos();
	}
	
}
