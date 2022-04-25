package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import net.miginfocom.swing.MigLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import controller.ClienteController;
import model.entity.Cliente;
import model.exception.ClienteComLinhaTelefonicaException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import javax.swing.ImageIcon;

public class TelaConsultaCliente {

	private JFrame frame;
	private JTable tableClientes;
	JButton btnNovoCliente;
	JButton btnExcluir;
	JButton btnEditarCliente;
	private ClienteController clienteController = new ClienteController();
	List<Cliente> clientes;
	private JButton btnAtualizarClientes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaConsultaCliente window = new TelaConsultaCliente();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaConsultaCliente() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 380);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		btnNovoCliente = new JButton("Novo cliente");
		
		btnExcluir = new JButton("Excluir");
		
		btnEditarCliente = new JButton("Editar");
		
		habilitarBotoes(false);

		tableClientes = new JTable();
		
		carregarClientes();
		
		btnAtualizarClientes = new JButton("");
		
		btnAtualizarClientes.setIcon(new ImageIcon("C:\\Users\\ivo.junior\\Downloads\\refresh-arrow_icon-icons.com_73442.png"));
		btnAtualizarClientes.setFont(new Font("Tahoma", Font.PLAIN, 8));
				
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(tableClientes, GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnAtualizarClientes, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
							.addGap(76)
							.addComponent(btnNovoCliente)
							.addGap(67)
							.addComponent(btnEditarCliente)
							.addGap(65)
							.addComponent(btnExcluir)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(tableClientes, GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnNovoCliente)
								.addComponent(btnEditarCliente)
								.addComponent(btnExcluir))
							.addGap(16))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnAtualizarClientes, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		frame.getContentPane().setLayout(groupLayout);
		
		tableClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				if(tableClientes.getSelectedRow() > 0) {
					habilitarBotoes(true);
				}
			}
		});
		
		btnAtualizarClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				carregarClientes();
			}
		});
		
		btnNovoCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				TelaCadastroCliente cadastroCliente = new TelaCadastroCliente();
				cadastroCliente.setEnabled(true);
				cadastroCliente.setVisible(true);
			}
		});
		
		btnExcluir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				Cliente cliente = clientes.get(tableClientes.getSelectedRow()-1);
				
				try {
					JOptionPane.showMessageDialog(null, clienteController.excluir(cliente), "Cliente Excluído!", JOptionPane.INFORMATION_MESSAGE);
					carregarClientes();
				}catch(ClienteComLinhaTelefonicaException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Atenção!", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		btnEditarCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				Cliente cliente = clientes.get(tableClientes.getSelectedRow()-1);
				TelaCadastroCliente cadastroCliente = new TelaCadastroCliente();
				cadastroCliente.setEnabled(true);
				cadastroCliente.setVisible(true);
			}
		});
		
	}

	private void habilitarBotoes(boolean b) {
		btnExcluir.setEnabled(b);
		btnEditarCliente.setEnabled(b);	
	}

	private void carregarClientes() {
		clientes = clienteController.consultarTodos();
		tableClientes.setModel(new DefaultTableModel(new String [][] {
			{"#", "Nome", "CPF"},},
				new String[] {"#", "Nome", "CPF"}));
		
		DefaultTableModel model = (DefaultTableModel) tableClientes.getModel();
		
		Integer i = 1;
		
		for(Cliente cliente : clientes) {
			String [] novaLinha = new String[] {
				i.toString(), cliente.getNome(), cliente.getCpf()	
			};
			model.addRow(novaLinha);
			i++;
		}
		
		habilitarBotoes(false);
	}
}
