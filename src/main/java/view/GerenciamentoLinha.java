package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ClienteController;
import controller.LinhaTelefonicaController;
import controller.TelefoneController;
import model.entity.Cliente;
import model.entity.LinhaTelefonica;
import model.entity.Telefone;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Window.Type;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class GerenciamentoLinha extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBoxTelefones;
	private JComboBox comboBoxClientes;
	private JButton btnAssociarUsuario = new JButton("Associar usuário ao telefone");
	private JButton btnLiberarLinha = new JButton("Liberar Telefone");
	private ClienteController clienteController = new ClienteController();
	private LinhaTelefonicaController linhaTelefonicaController = new LinhaTelefonicaController();
	private TelefoneController telefoneController = new TelefoneController();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GerenciamentoLinha frame = new GerenciamentoLinha();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GerenciamentoLinha() {
		setType(Type.UTILITY);
		setTitle("Gerenciamento de linha telefonica");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 525, 265);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		comboBoxTelefones = new JComboBox(telefoneController.listAll().toArray());
		comboBoxTelefones.setSelectedIndex(-1);
		comboBoxTelefones.setBounds(36, 69, 436, 22);
		
		JLabel lblLinhasTelefonicas = new JLabel("Telefones");
		lblLinhasTelefonicas.setBounds(229, 34, 86, 24);
		lblLinhasTelefonicas.setFont(new Font("Arial", Font.PLAIN, 20));
		lblLinhasTelefonicas.setLabelFor(comboBoxTelefones);
		
		JLabel lblCliente = new JLabel("Usuários");
		lblCliente.setBounds(229, 97, 86, 24);
		lblCliente.setFont(new Font("Arial", Font.PLAIN, 20));
		comboBoxClientes = new JComboBox(clienteController.consultarTodos().toArray());
		comboBoxClientes.setSelectedIndex(-1);
		comboBoxClientes.setEnabled(false);
		comboBoxClientes.setBounds(36, 132, 436, 22);
		
		btnAssociarUsuario.setEnabled(false);
		
		btnAssociarUsuario.setBounds(36, 194, 244, 23);
		contentPane.setLayout(null);
		btnLiberarLinha.setEnabled(false);
		
		btnLiberarLinha.setBounds(290, 194, 182, 23);
		contentPane.add(btnLiberarLinha);
		contentPane.add(lblCliente);
		contentPane.add(comboBoxClientes);
		contentPane.add(btnAssociarUsuario);
		contentPane.add(comboBoxTelefones);
		contentPane.add(lblLinhasTelefonicas);
		
		btnLiberarLinha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Telefone telefone = (Telefone)comboBoxTelefones.getSelectedItem();
				Integer id = telefone.getId();
				try {
					telefoneController.desativarTelefone(id);
					JOptionPane.showMessageDialog(null
							, "Telefone desativado com sucesso!\nTelefone pode ser associado a outro usuário!", 
							"Telefone desativado!", JOptionPane.INFORMATION_MESSAGE);
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Erro ao desativar telefone!", JOptionPane.ERROR_MESSAGE);
				}
				btnAssociarUsuario.setEnabled(false);
				btnLiberarLinha.setEnabled(false);
				comboBoxClientes.setEnabled(false);
				comboBoxClientes.setSelectedIndex(-1);
				comboBoxTelefones.setSelectedIndex(-1);
				comboBoxTelefones.removeAllItems();
				telefoneController.listAll().stream().forEach(cliente -> comboBoxTelefones.addItem(cliente));
			}
		});
		
		comboBoxTelefones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Telefone telefone = (Telefone)comboBoxTelefones.getSelectedItem();
				if(telefone != null) {
					if(telefone.isAtivo()) {
						btnAssociarUsuario.setEnabled(false);
						btnLiberarLinha.setEnabled(true);
						comboBoxClientes.setEnabled(false);
						Integer idCliente = clienteController.getIdClienteByIdTelefone(telefone.getId());
						if(idCliente == null) {
							comboBoxClientes.setSelectedIndex(-1);
						}else {
							int nItens = comboBoxClientes.getItemCount();
							if(nItens > 0) {
								for(int i = 0; i < nItens; i++) {
									Cliente clienteLista = (Cliente) comboBoxClientes.getItemAt(i);
									if(clienteLista.getId() == idCliente) {
										comboBoxClientes.setSelectedIndex(i);
									}
								}
							}else {
								comboBoxClientes.setSelectedIndex(-1);
							}
							
						}
					}else {
						btnLiberarLinha.setEnabled(false);
						comboBoxClientes.removeAllItems();
						clienteController.consultarTodos().stream().forEach(cliente -> comboBoxClientes.addItem(cliente));
						comboBoxClientes.setEnabled(true);
						btnAssociarUsuario.setEnabled(true);
					}
				}
			}
		});
		
		btnAssociarUsuario.addActionListener(e -> {
			// ativar o telefone
			// setar na linha telefonica id do cliente e data ativação atual
			Telefone telefoneSelecionado = (Telefone)comboBoxTelefones.getSelectedItem();
			Integer idTelefone = telefoneSelecionado.getId();
			Cliente cliente = (Cliente)comboBoxClientes.getSelectedItem();
			Integer idCliente = cliente.getId();
			try {
				telefoneController.ativarTelefone(idTelefone);
				linhaTelefonicaController.ativarLinhaTelefonica(idTelefone, idCliente);
				JOptionPane.showMessageDialog(null
						, "Telefone "+telefoneSelecionado.toString()+" associado ao usuário: "+cliente.toString(), 
						"Telefone Ativado!", JOptionPane.INFORMATION_MESSAGE);
			}catch(Exception error) {
				JOptionPane.showMessageDialog(null, error.getMessage(), "Erro ao associar telefone!", JOptionPane.ERROR_MESSAGE);
			}
			btnAssociarUsuario.setEnabled(false);
			btnLiberarLinha.setEnabled(false);
			comboBoxClientes.setEnabled(false);
			comboBoxClientes.setSelectedIndex(-1);
			comboBoxTelefones.setSelectedIndex(-1);
			comboBoxTelefones.removeAllItems();
			telefoneController.listAll().stream().forEach(telefone -> comboBoxTelefones.addItem(telefone));
		});
	}
}
