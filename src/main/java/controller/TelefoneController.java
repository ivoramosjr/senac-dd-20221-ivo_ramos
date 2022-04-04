package controller;

import java.util.ArrayList;

import model.bo.TelefoneBO;
import model.entity.Telefone;

public class TelefoneController {
	
	private TelefoneBO telefoneBO = new TelefoneBO();
	
	public ArrayList<Telefone> listAll(){
		return telefoneBO.listAll();
	}
	
}
