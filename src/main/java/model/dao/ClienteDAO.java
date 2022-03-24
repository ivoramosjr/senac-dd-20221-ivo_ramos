package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.Cliente;
import model.entity.Endereco;
import model.entity.LinhaTelefonica;

public class ClienteDAO {
 
	public Cliente inserir(Cliente novoCliente) {
		Connection conexao = Banco.getConnection();
		String sql = " INSERT INTO CLIENTE(NOME, CPF)" 
					+ "VALUES (?, ?);";
		
		//TODO como inserir o endereço e as linhas telefônicas?
		
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, sql);
		
		try {
			stmt.setString(1, novoCliente.getNome());
			stmt.setString(2, novoCliente.getCpf());
			
			stmt.execute();
			
			ResultSet chavesGeradas = stmt.getGeneratedKeys();
			if(chavesGeradas.next()) {
				novoCliente.setId(chavesGeradas.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao inserir cliente. Causa:" + e.getMessage());
		}
		
		return novoCliente;
	}
	
	public boolean atualizar(Cliente cliente) {
		boolean atualizou = false;
		Connection conexao = Banco.getConnection();
		String sql = " UPDATE CLIENTE "
					+" SET NOME=?, CPF=? "
					+" WHERE ID=? ";
		
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, sql);
		
		try {
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getCpf());
			
			int linhasAfetadas = stmt.executeUpdate();
			atualizou = linhasAfetadas > 0;
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar cliente. Causa:" + e.getMessage());
		}
		
		return atualizou;
	}
	
	public boolean remover(int id) {
		boolean removeu = false;
		
		Connection conexao = Banco.getConnection();
		String sql = " DELETE FROM CLIENTE "
					+" WHERE ID=?";
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, sql);
		
		try {
			stmt.setInt(1, id);
			removeu = stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Erro ao remover cliente. Causa:" + e.getMessage());
		}		
		
		return removeu;
	}
	
	public Cliente consultar(int id) {
		Cliente clienteConsultado = null;
		
		Connection conn = Banco.getConnection();
		//TODO implementar		
		String sql = "SELECT * FROM CLIENTE WHERE ID = ?";
		
		try(PreparedStatement stmt = Banco.getPreparedStatementWithPk(conn, sql)){
			stmt.setInt(1, id);
			
			stmt.executeUpdate();
			
			try(ResultSet result = stmt.getResultSet()) {
				if(result.next()) {
					clienteConsultado.setId(result.getInt("id"));
					clienteConsultado.setNome(result.getString("nome"));
					clienteConsultado.setCpf(result.getString("cpf"));
					
					EnderecoDAO enderecoDAO = new EnderecoDAO();
					Endereco endereco = enderecoDAO.consultar(result.getInt("id_endereco"));
					clienteConsultado.setEndereco(endereco);
					
					LinhaTelefonicaDAO linhaTelefonicaDao = new LinhaTelefonicaDAO();
					List<LinhaTelefonica> linhasTelefonicas = linhaTelefonicaDao.consultarLinhasTelefonicasByIdCliente(clienteConsultado.getId());
				}
			}
			
		} catch (SQLException e) {
			System.out.println("Problema ao consultar cliente. Causa: "+e.getMessage());
			e.printStackTrace();
		}
		
		return clienteConsultado;
	}
	
	public ArrayList<Cliente> consultarTodos(){
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		Connection conn = Banco.getConnection();
		String sql = "SELECT * FROM Cliente";
		try(PreparedStatement stmt = Banco.getPreparedStatementWithPk(conn, sql)){
			stmt.executeQuery();
			try(ResultSet result = stmt.getResultSet()) {
				while(result.next()) {
					Cliente cliente = new Cliente();
					cliente.setId(result.getInt("id"));
					cliente.setNome(result.getString("nome"));
					cliente.setCpf(result.getString("cpf"));
					
					EnderecoDAO enderecoDAO = new EnderecoDAO();
					Endereco endereco = enderecoDAO.consultar(result.getInt("id_endereco"));
					cliente.setEndereco(endereco);
					
					LinhaTelefonicaDAO linhaTelefonicaDao = new LinhaTelefonicaDAO();
					List<LinhaTelefonica> linhasTelefonicas = linhaTelefonicaDao.consultarLinhasTelefonicasByIdCliente(cliente.getId());
					cliente.setLinhas(linhasTelefonicas);
					clientes.add(cliente);
				}
			}
		}catch (SQLException e) {
			System.out.println("Problema ao consultar todos os clientes. Causa: "+e.getMessage());
			e.printStackTrace();
		}
		return clientes;
	}
}
