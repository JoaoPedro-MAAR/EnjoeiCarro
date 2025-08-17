/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */
package appswing;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Fabricante;
import modelo.Modelo;
import requisito.Fachada;
import javax.swing.JComboBox;

public class TelaFabricante {
	private JDialog frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JLabel label;
	private JLabel label_4;
	private JLabel label_1;
	private JTextField fieldnomefabricante;
	private JLabel label_2;
	private JLabel labelID;
	private JButton atualizarButton;

	/**
	 * Launch the application.
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args) {
		Fachada.inicializar();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new TelaFabricante();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public TelaFabricante() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JDialog();
		frame.setModal(true);

		frame.setResizable(false);
		frame.setTitle("Carro");
		frame.setBounds(100, 100, 729, 385);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				Fachada.inicializar();
				listagem();
			}
			@Override
			public void windowClosing(WindowEvent e) {
				Fachada.finalizar();
			}
		});

		scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 43, 674, 148);
		frame.getContentPane().add(scrollPane);

		table = new JTable() {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}
		};
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					if (table.getSelectedRow() >= 0)
						label_4.setText("selecionado="+ table.getValueAt( table.getSelectedRow(), 0));
					String nomeModelo = (String) table.getValueAt(table.getSelectedRow(), 1);
					Fabricante fabricante = Fachada.localizarFabricante(nomeModelo);
					labelID.setText(Integer.toString(fabricante.getID()));
					fieldnomefabricante.setText(nomeModelo);
					

					
				}catch (Exception ex) {
					label.setText(ex.getMessage());
				}
					
				}
		});
		table.setGridColor(Color.BLACK);
		table.setRequestFocusEnabled(false);
		table.setFocusable(false);
		table.setBackground(new Color(255, 255, 255));
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowGrid(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		label = new JLabel("");		
		label.setForeground(Color.BLUE);
		label.setBounds(21, 321, 688, 14);
		frame.getContentPane().add(label);

		label_4 = new JLabel("resultados:");
		label_4.setBounds(21, 190, 431, 14);
		frame.getContentPane().add(label_4);

		button_1 = new JButton("Criar novo fabricante");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String fabricanteNome = fieldnomefabricante.getText();
				    Fachada.cadastrarFabricante(fabricanteNome);
				    listagem();
				    label.setText("Fabricante cadastrado");


	

				}catch (Exception e1) {
					e1.printStackTrace();
					label.setText(e1.getMessage());
				}
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_1.setBounds(524, 236, 171, 23);
		frame.getContentPane().add(button_1);

		button = new JButton("Listar");
		button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listagem();
			}
		});
		button.setBounds(524, 270, 171, 23);
		frame.getContentPane().add(button);

		button_2 = new JButton("Deletar selecionado");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if (table.getSelectedRow() >= 0){
						label.setText("nao implementado " );
						String placa = (String) table.getValueAt( table.getSelectedRow(), 1);

						Fachada.excluirFabricante(placa);
						label.setText("carro apagado" );
						listagem();
					}
					else
						label.setText("nao selecionado");
				}
				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_2.setBounds(524, 202, 171, 23);
		frame.getContentPane().add(button_2);
		
		label_1 = new JLabel("Nome:  ");
		label_1.setBounds(118, 225, 43, 14);
		frame.getContentPane().add(label_1);
		
		fieldnomefabricante = new JTextField();
		fieldnomefabricante.setBounds(171, 222, 86, 20);
		frame.getContentPane().add(fieldnomefabricante);
		fieldnomefabricante.setColumns(10);
		
		label_2 = new JLabel("ID:");
		label_2.setBounds(21, 225, 46, 14);
		frame.getContentPane().add(label_2);
		
		labelID = new JLabel("");
		labelID.setBounds(41, 225, 46, 14);
		frame.getContentPane().add(labelID);
		
		atualizarButton = new JButton("Atualizar");
		atualizarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				Integer id = Integer.parseInt(labelID.getText());
				String novoNome = fieldnomefabricante.getText();
				Fachada.alterarNomeFabricante(id, novoNome);
				label.setText("Fabricante atualizado");
				listagem();
				}catch (Exception ex) {
					System.out.println(ex.getStackTrace());
					label.setText(ex.getMessage());
				}
				
			}
		});
		atualizarButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		atualizarButton.setBounds(524, 304, 171, 23);
		frame.getContentPane().add(atualizarButton);
	}
	

	public void listagem() {
		try{
			List<Fabricante> lista = Fachada.listarFabricantes();
			DefaultTableModel model = new DefaultTableModel();
			table.setModel(model);

			model.addColumn("id");
			model.addColumn("Nome");
			for(Fabricante car : lista) {
				model.addRow(new Object[]{car.getID(), car.getNome()} );


			}

			label_4.setText("resultados: "+lista.size()+ " objetos");
		}
		catch(Exception erro){
			label.setText(erro.getMessage());
		}
	}
}
