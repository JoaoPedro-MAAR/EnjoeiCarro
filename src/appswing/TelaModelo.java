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
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
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

import modelo.Carro;
import modelo.Fabricante;
import modelo.Modelo;
import requisito.Fachada;
import javax.swing.JComboBox;

public class TelaModelo {
	private JDialog frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JLabel label;
	private JLabel label_4;
	private JLabel label_1;
	private JTextField fieldnomeModelo;
	private JLabel label_2;
	private JComboBox fabricantecomboBox;
	private JButton atualizarButton;
	private JLabel label_3;
	private JLabel labelID;

	/**
	 * Launch the application.
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args) {
		Fachada.inicializar();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new TelaModelo();
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
	public TelaModelo() {
		Fachada.inicializar();
		initialize();
		carregarCombobox();
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
					Modelo modelo = Fachada.localizarModelo(nomeModelo);
					labelID.setText(Integer.toString(modelo.getID()));
					fieldnomeModelo.setText(modelo.getNome());
					Fabricante fabricanteModelo = modelo.getFabricante();
					if(fabricanteModelo != null) {
						String nomeFabricante = fabricanteModelo.getNome();
						fabricantecomboBox.setSelectedItem(nomeFabricante);

						
					}else {
						fabricantecomboBox.setSelectedIndex(-1);
					}
					
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

		label = new JLabel("");		//label de mensagem
		label.setForeground(Color.BLUE);
		label.setBounds(21, 321, 688, 14);
		frame.getContentPane().add(label);

		label_4 = new JLabel("resultados:");
		label_4.setBounds(21, 190, 431, 14);
		frame.getContentPane().add(label_4);

		button_1 = new JButton("Criar novo modelo");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					fabricantecomboBox.getSelectedItem().toString();
					String nomeModelo = fieldnomeModelo.getText();
					Fabricante fabricante = Fachada.localizarFabricante(fabricantecomboBox.getSelectedItem().toString());
					if (fabricante == null) {
					    System.out.println("Erro: Fabricante não localizado.");
					    JOptionPane.showMessageDialog(null, "Fabricante não localizado!");
					}
					String fabricanteNome = fabricante.getNome();
				    Fachada.cadastrarModelo(nomeModelo);
				    Fachada.trocarModeloAoFabricante(nomeModelo, fabricanteNome);
				    listagem();
				    label.setText("Modelo cadastrado");

				    

	

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
		button.setBounds(527, 266, 166, 23);
		frame.getContentPane().add(button);

		button_2 = new JButton("Deletar selecionado");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if (table.getSelectedRow() >= 0){
						label.setText("nao implementado " );
						String placa = (String) table.getValueAt( table.getSelectedRow(), 1);

						Fachada.excluirModelo(placa);
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
		label_1.setBounds(107, 241, 43, 14);
		frame.getContentPane().add(label_1);
		
		fieldnomeModelo = new JTextField();
		fieldnomeModelo.setBounds(150, 238, 86, 20);
		frame.getContentPane().add(fieldnomeModelo);
		fieldnomeModelo.setColumns(10);
		
		label_2 = new JLabel("Nome Fabricante");
		label_2.setBounds(246, 241, 81, 14);
		frame.getContentPane().add(label_2);
		
		fabricantecomboBox = new JComboBox();
		fabricantecomboBox.setBounds(337, 237, 115, 22);
		frame.getContentPane().add(fabricantecomboBox);
		
		atualizarButton = new JButton("Atualizar modelo");
		atualizarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					fabricantecomboBox.getSelectedItem().toString();
					int id = Integer.parseInt(labelID.getText());
					String nomeModelo = fieldnomeModelo.getText();
					Fabricante fabricante = Fachada.localizarFabricante(fabricantecomboBox.getSelectedItem().toString());
					if (fabricante == null) {
					    System.out.println("Erro: Fabricante não localizado.");
					    JOptionPane.showMessageDialog(null, "Fabricante não localizado!");
					}
					String fabricanteNome = fabricante.getNome();
				    Fachada.alterarNomeModelo(id, nomeModelo);
				    Fachada.trocarModeloAoFabricante(id, fabricanteNome);
				    listagem();
				    label.setText("Modelo Atualizado");
				}catch (Exception x) {
					label.setText(x.getMessage());
					System.out.println(x.getStackTrace());
				
				}
				
			}
		});
		atualizarButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		atualizarButton.setBounds(529, 300, 166, 23);
		frame.getContentPane().add(atualizarButton);
		
		label_3 = new JLabel("ID: ");
		label_3.setBounds(35, 241, 46, 14);
		frame.getContentPane().add(label_3);
		
		labelID = new JLabel("");
		labelID.setBounds(52, 241, 46, 14);
		frame.getContentPane().add(labelID);
	}
	
	public void carregarCombobox() {
	    try {
	    	Fachada.inicializar();
	        fabricantecomboBox.removeAllItems();
	        List<Fabricante> fabricantes = Fachada.listarFabricantes();
	        for(Fabricante fabricante : fabricantes ) {
	            fabricantecomboBox.addItem(fabricante.getNome());
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        
	        JOptionPane.showMessageDialog(frame, "Erro ao carregar fabricantes: " + e.getMessage());
	    }
	}

	public void listagem() {
		try{
			List<Modelo> lista = Fachada.listarModelos();
			// model armazena todas as linhas e colunas do table
			DefaultTableModel model = new DefaultTableModel();
			table.setModel(model);

			//adicionar colunas no model
			model.addColumn("id");
			model.addColumn("Nome");
			model.addColumn("Fabricante");

			//adicionar linhas no model
			for(Modelo car : lista) {
				if (car.getFabricante() != null)
				model.addRow(new Object[]{car.getID(), car.getNome(),car.getFabricante().getNome(),} );
				if (car.getFabricante() == null)
				model.addRow(new Object[]{car.getID(), car.getNome(),"N/A",} );

			}

			label_4.setText("resultados: "+lista.size()+ " objetos");
		}
		catch(Exception erro){
			label.setText(erro.getMessage());
		}
	}
}
