package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.entity.LinhaTelefonica;

public class LinhaTelefonicaDAO {
	public LinhaTelefonica inserir(LinhaTelefonica novaLinha) {
		Connection conexao = Banco.getConnection();
		String sql = " INSERT INTO LINHA_TELEFONICA(ID_CLIENTE, ID_LINHA_TELEFONICA, DT_ATIVACAO, DT_DESATIVACAO)" 
					+ "VALUES (?, ?, ?, ?);";
		
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, sql);
		
		try {
			stmt.setInt(1, novaLinha.getIdCliente());
			stmt.setInt(2, novaLinha.getTelefone().getId());
			stmt.setObject(3, novaLinha.getDataAtivacao());
			stmt.setObject(4, novaLinha.getDataDesativacao());
			
			stmt.execute();
			
			ResultSet chavesGeradas = stmt.getGeneratedKeys();
			if(chavesGeradas.next()) {
				novaLinha.setId(chavesGeradas.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao inserir linha telefônica. Causa:" + e.getMessage());
		}
		
		return novaLinha;
	}
	
	public boolean atualizar(LinhaTelefonica linhaTelefonica) {
		boolean atualizou = false;
		Connection conexao = Banco.getConnection();
		String sql = " UPDATE LINHA_TELEFONICA "
					+" SET ID_CLIENTE=?, ID_LINHA_TELEFONICA=?, DT_ATIVACAO=?, DT_DESATIVACAO=? "
					+" WHERE ID=? ";
		
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, sql);
		
		try {
			stmt.setInt(1, linhaTelefonica.getIdCliente());
			stmt.setInt(2, linhaTelefonica.getTelefone().getId());
			stmt.setObject(3, linhaTelefonica.getDataAtivacao());
			stmt.setObject(4, linhaTelefonica.getDataDesativacao());
			
			int linhasAfetadas = stmt.executeUpdate();
			atualizou = linhasAfetadas > 0;
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar linha telefônica. Causa:" + e.getMessage());
		}
		
		return atualizou;
	}
	
	public boolean remover(int id) {
		boolean removeu = false;
		
		Connection conexao = Banco.getConnection();
		String sql = " DELETE FROM LINHA_TELEFONICA "
					+" WHERE ID=?";
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, sql);
		
		try {
			stmt.setInt(1, id);
			removeu = stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Erro ao remover linha telefônica. Causa:" + e.getMessage());
		}		
		
		return removeu;
	}
	
	public LinhaTelefonica consultar(int id) {
		LinhaTelefonica linhaTelefonicaConsultado = null;
		//TODO implementar		
		//SELECT * FROM LINHA_TELEFONICA WHERE ID = ?
		
		return linhaTelefonicaConsultado;
	}
	
	public ArrayList<LinhaTelefonica> consultarTodos(){
		ArrayList<LinhaTelefonica> linhaTelefonicas = new ArrayList<LinhaTelefonica>();
		//TODO implementar
		//SELECT * FROM LINHA_TELEFONICA
		
		return linhaTelefonicas;
	}

	public List<LinhaTelefonica> consultarLinhasTelefonicasByIdCliente(int id) {
		//TODO implementar
		Connection conn = Banco.getConnection();
		List<LinhaTelefonica> linhasConsultadas = new ArrayList<LinhaTelefonica>();
		
		String sql = "SELECT id, id_telefone, id_cliente, dt_ativacao, dt_desativacao "
				+ "FROM linha_telefonica WHERE id_cliente = ? AND dt_desativacao IS NULL";
		
		try(PreparedStatement stmt = Banco.getPreparedStatementWithPk(conn, sql)){
			
			stmt.setInt(1, id);
			
			stmt.execute();
			
			ResultSet resultSet = stmt.getResultSet();
			
			//TODO continuar implementação
			
		}catch(SQLException e) {
			System.out.println("Erro ao consultar as linhas telefónicas do cliente. Causa: "+e.getMessage());
		}
		
		return linhasConsultadas;
	}
}
