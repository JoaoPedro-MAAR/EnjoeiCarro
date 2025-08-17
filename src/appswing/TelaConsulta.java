/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */
package appswing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Carro;
import modelo.Fabricante;
import modelo.Modelo;
import requisito.Fachada;
import javax.swing.JTextField;

public class TelaConsulta {
	private JDialog frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton button;
	private JLabel label;
	private JLabel label_4;

	private JComboBox<String> comboBox;
	private JLabel labelParams;
	private JTextField textField;
	private JLabel labellComboBoxFabricante;
	private JComboBox fabricantesComboBox;

	/**
	 * Launch the application.
	 */
	//	public static void main(String[] args) {
	//		EventQueue.invokeLater(new Runnable() {
	//			public void run() {
	//				try {
	//					new TelaConsulta();
	//				} catch (Exception e) {
	//					e.printStackTrace();
	//				}
	//			}
	//		});
	//	}

	/**
	 * Create the application.
	 */
	public TelaConsulta() {
		initialize();
		carregarCombobox();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Fachada.inicializar();
		frame = new JDialog();
		frame.setModal(true);

		frame.setResizable(false);
		frame.setTitle("Consultas");
		frame.setBounds(100, 100, 729, 385);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				Fachada.inicializar();
			}
			@Override
			public void windowClosing(WindowEvent e) {
				Fachada.finalizar();
			}
		});

		scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 145, 674, 148);
		frame.getContentPane().add(scrollPane);

		label = new JLabel("");		//label de mensagem
		label.setForeground(Color.BLUE);
		label.setBounds(21, 321, 688, 14);
		frame.getContentPane().add(label);

		label_4 = new JLabel("resultados:");
		label_4.setBounds(20, 296, 431, 14);
		frame.getContentPane().add(label_4);

		button = new JButton("Consultar");
		button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int index = comboBox.getSelectedIndex();
		        if(index < 0) {
		            label_4.setText("Consulta não selecionada");
		            return;
		        }

		        try {
		            switch(index) {
		                case 0: { 
		                    String textoAno = textField.getText();

		                    if (textoAno.trim().isEmpty()) {
		                        label_4.setText("Erro: O campo 'Ano' não pode ser vazio.");
		                        return;
		                    }

		                    int ano = Integer.parseInt(textoAno);
		                    List<Carro> carros = Fachada.procurarCarroporAno(ano);
		                    listagemCarro(carros);
		                    break;
		                }

		                case 1: {
		                    String fabricante = fabricantesComboBox.getSelectedItem().toString();
		                    List<Carro> resultado2 = Fachada.listarTodosOsCarrosDeUmFabricante(fabricante);
		                    listagemCarro(resultado2);
		                    break;
		                }
		                
		                case 2: {
		                    String textoN = textField.getText();
		                    if (textoN.trim().isEmpty()) {
		                        label_4.setText("Erro: O campo 'N' não pode ser vazio.");
		                        return;
		                    }
		                    
		                    int numero = Integer.parseInt(textoN);
		                    List<Modelo> resultado3 = Fachada.procurarModeloPorqntdDeCarro(numero);
		                    System.out.println(resultado3);
		                    listagemModelo(resultado3);
		                    break;
		                }
		            }
		        } catch (NumberFormatException ex) {
		            label_4.setText("Erro: O valor digitado para o parâmetro não é um número válido.");
		        } catch (Exception ex) {
		            label_4.setText("Erro na consulta: " + ex.getMessage());
		            ex.printStackTrace();
		        }
		    }
		});
		button.setBounds(606, 47, 89, 23);
		frame.getContentPane().add(button);

		comboBox = new JComboBox<String>();
		comboBox.setToolTipText("selecione a consulta");
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Carros do ano X", "Carros do fabricante X", "Modelos com mais de N carros"}));
		comboBox.setBounds(21, 10, 513, 22);


		comboBox.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int index = comboBox.getSelectedIndex();
		        switch(index) {
		            case 0: 
		                labelParams.setText("Digite o Ano:");
		                textField.setEnabled(true);         
		                fabricantesComboBox.setEnabled(false); 
		                break;
		            case 1: 
		                labelParams.setText("Parametro:");
		                textField.setEnabled(false);       
		                fabricantesComboBox.setEnabled(true);  
		                break;
		            case 2: 
		                labelParams.setText("digite N:");
		                textField.setEnabled(true);        
		                fabricantesComboBox.setEnabled(false);
		                break;
		            default: 
		                textField.setEnabled(false);
		                fabricantesComboBox.setEnabled(false);
		                labelParams.setText("parametro");
		                break;
		        }
		    }
		});

		frame.getContentPane().add(comboBox);


		
				table = new JTable() {
					public boolean isCellEditable(int rowIndex, int vColIndex) {
						return false;
					}
				};
				table.setBounds(23, 147, 672, 146);
				frame.getContentPane().add(table);
				
						table.setGridColor(Color.BLACK);
						table.setRequestFocusEnabled(false);
						table.setFocusable(false);
						table.setBackground(new Color(255, 255, 255));
						table.setFillsViewportHeight(true);
						table.setRowSelectionAllowed(true);
						table.setFont(new Font("Tahoma", Font.PLAIN, 14));
						table.setBorder(new LineBorder(new Color(0, 0, 0)));
						table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						table.setShowGrid(true);
						table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
						
						labelParams = new JLabel("Parametro");
						labelParams.setBounds(21, 52, 68, 14);
						frame.getContentPane().add(labelParams);
						
						textField = new JTextField();
						textField.setBounds(99, 49, 140, 20);
						frame.getContentPane().add(textField);
						textField.setColumns(10);
						
						labellComboBoxFabricante = new JLabel("Fabricantes");
						labellComboBoxFabricante.setBounds(247, 52, 68, 14);
						frame.getContentPane().add(labellComboBoxFabricante);
						
						fabricantesComboBox = new JComboBox();
						fabricantesComboBox.setEnabled(false);
						fabricantesComboBox.setBounds(352, 48, 125, 22);
						frame.getContentPane().add(fabricantesComboBox);
	}

	public void listagemCarro(List<Carro> lista) {
		try {
			// objeto model armazena os dados do grid
			DefaultTableModel model = new DefaultTableModel();
			table.setModel(model);

			// inserir colunas (0,1,2) no grid
			model.addColumn("placa");
			model.addColumn("Modelo");
			model.addColumn("cor");
			model.addColumn("valor");
			model.addColumn("ano");

			// inserir linhas no grid
			for (Carro p : lista) {
				//adicionar linha no grid
				model.addRow(new Object[] { p.getPlaca(), p.getModelo().getNome(), p.getCor(), p.getValor(), p.getAno() });

			}
			// redimensionar a coluna 0,3 e 4
//			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // desabilita
//			table.getColumnModel().getColumn(0).setMaxWidth(40); // coluna id
//			table.getColumnModel().getColumn(3).setMinWidth(200); // coluna dos apelidos
//			table.getColumnModel().getColumn(4).setMinWidth(200); // coluna dos telefones
//			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); // desabilita

		} catch (Exception erro) {
			label.setText(erro.getMessage());
		}
	}
	
	public void listagemModelo(List<Modelo> lista) {
		try {
			// objeto model armazena os dados do grid
			DefaultTableModel model = new DefaultTableModel();
			table.setModel(model);
			// inserir colunas (0,1,2) no grid
			model.addColumn("id");
			model.addColumn("Nome");
			model.addColumn("Fabricante");


			// inserir linhas no grid
			for (Modelo p : lista) {
				//adicionar linha no grid
				model.addRow(new Object[] { p.getID(), p.getNome(), p.getFabricante().getNome() });

			}
//			 redimensionar a coluna 0,3 e 4
//			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // desabilita
//			table.getColumnModel().getColumn(0).setMaxWidth(40); // coluna id
//			table.getColumnModel().getColumn(1).setMinWidth(200); // coluna dos apelidos
//			table.getColumnModel().getColumn(2).setMinWidth(200); // coluna dos telefones
//			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); // desabilita

		} catch (Exception erro) {
			label.setText(erro.getMessage());
		}
	}
	
	
	
	
	public void carregarCombobox() {
		System.out.println("Estou sendo chamado");
	    try {
	    	Fachada.inicializar();
	       fabricantesComboBox.removeAllItems();
	        List<Fabricante> fabricantes = Fachada.listarFabricantes();
	        for(Fabricante fabricante : fabricantes ) {
	            fabricantesComboBox.addItem(fabricante.getNome());
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        
	        JOptionPane.showMessageDialog(frame, "Erro ao carregar fabricantes: " + e.getMessage());
	    }
	}



}
