package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import controller.TelefoneController;
import model.entity.Telefone;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class TelaListagemTelefone {

	private JFrame frmListagemTelefones;
	private JTable tableTelefone;
	private TelefoneController telefoneController = new TelefoneController();
	private List<Telefone> telefones = new ArrayList<Telefone>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaListagemTelefone window = new TelaListagemTelefone();
					window.frmListagemTelefones.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaListagemTelefone() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmListagemTelefones = new JFrame();
		frmListagemTelefones.setResizable(false);
		frmListagemTelefones.setTitle("Listagem Telefones");
		frmListagemTelefones.setBounds(100, 100, 600, 412);
		frmListagemTelefones.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{572, 0};
		gridBagLayout.rowHeights = new int[]{53, 303, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		frmListagemTelefones.getContentPane().setLayout(gridBagLayout);
		
		JButton btnAtualizar = new JButton("Atualizar");
		GridBagConstraints gbc_btnAtualizar = new GridBagConstraints();
		gbc_btnAtualizar.insets = new Insets(0, 0, 5, 0);
		gbc_btnAtualizar.gridx = 0;
		gbc_btnAtualizar.gridy = 0;
		frmListagemTelefones.getContentPane().add(btnAtualizar, gbc_btnAtualizar);
		
		tableTelefone = new JTable();
		GridBagConstraints gbc_tableTelefone = new GridBagConstraints();
		gbc_tableTelefone.fill = GridBagConstraints.BOTH;
		gbc_tableTelefone.gridx = 0;
		gbc_tableTelefone.gridy = 1;
		frmListagemTelefones.getContentPane().add(tableTelefone, gbc_tableTelefone);
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				telefones = telefoneController.listAll();
				
				tableTelefone.setModel(new DefaultTableModel(new String[][]
						{{"DDD","Número","Tipo","Ativo"},},
						new String[] {"DDD","Número","Tipo","Ativo"}));
				DefaultTableModel modelo = (DefaultTableModel) tableTelefone.getModel();
				
				for(Telefone tel : telefones) {
					String[] novaLinha = new String[] {
							tel.getDdd(),
							tel.getNumero(),
							tel.getTipo() == Telefone.TIPO_FIXO ? "Fixo" : "Móvel",
							tel.isAtivo() ? "Sim" : "Não"
					};
					
					modelo.addRow(novaLinha);
				}
			}
		});
	}

}
