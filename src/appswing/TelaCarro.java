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
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Carro;
import modelo.Fabricante;
import modelo.Modelo;
import requisito.Fachada;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.border.EtchedBorder;

public class TelaCarro {
	private JDialog frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField fieldPlaca;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JLabel label;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_1;
	private JTextField fieldAno;
	private JLabel label_5;
	private JTextField fieldCor;
	private JLabel label_6;
	private JTextField fieldValor;
	private JComboBox modeloComboBox;
	private JPanel panel;
	private JLabel label_7;
	private JButton button_3;
	private JButton button_4;
	private JButton button_5;
	private BufferedImage buffer; // armazena a foto na memória durante a edicao


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCarro tela = new TelaCarro();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaCarro() {
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
		frame.setBounds(100, 100, 729, 497);
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
				String placaCarro = (String) table.getValueAt(table.getSelectedRow(), 0);
				Carro c = Fachada.localizarCarro(placaCarro);
				fieldPlaca.setText(c.getPlaca());				
				fieldAno.setText(Integer.toString(c.getAno()));
				fieldValor.setText(Double.toString(c.getValor()));
				fieldCor.setText(c.getCor());
				Modelo modeloDoCarro = c.getModelo();
				if(modeloDoCarro != null) {
					String nomeDoModelo = modeloDoCarro.getNome();
					
					modeloComboBox.setSelectedItem(nomeDoModelo);
				}
				else {
					modeloComboBox.setSelectedIndex(-1);
				}
				
				if (c.getFoto() != null) {
					InputStream in = new ByteArrayInputStream(c.getFoto());
					buffer = ImageIO.read(in);
					ImageIcon icon = new ImageIcon(
							buffer.getScaledInstance(buffer.getWidth(), buffer.getHeight(), Image.SCALE_DEFAULT));
					icon.setImage(icon.getImage().getScaledInstance(label_7.getWidth(), label_7.getHeight(), 1));
					label_7.setIcon(icon);
				} else {
					buffer = null;
					label_7.setText("sem foto");
					label_7.setIcon(null);
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

		label = new JLabel("");
		label.setForeground(Color.BLUE);
		label.setBounds(10, 420, 688, 14);
		frame.getContentPane().add(label);

		label_4 = new JLabel("resultados:");
		label_4.setBounds(21, 190, 431, 14);
		frame.getContentPane().add(label_4);

		label_2 = new JLabel("placa:");
		label_2.setHorizontalAlignment(SwingConstants.LEFT);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_2.setBounds(10, 218, 71, 14);
		frame.getContentPane().add(label_2);

		fieldPlaca = new JTextField();
		fieldPlaca.setFont(new Font("Dialog", Font.PLAIN, 12));
		fieldPlaca.setColumns(10);
		fieldPlaca.setBounds(47, 215, 105, 20);
		frame.getContentPane().add(fieldPlaca);

		button_1 = new JButton("Criar novo carro");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				    String placa = fieldPlaca.getText();
				    
				    Modelo modeloObj = Fachada.localizarModelo(modeloComboBox.getSelectedItem().toString());
				    String nomeModelo = modeloObj.getNome();  
				    
				    String anoString = fieldAno.getText();
				    int ano = Integer.parseInt(anoString);
				    
				    String cor = fieldCor.getText();
				    
				    String valorString = fieldValor.getText();

				    Double valor = Double.parseDouble(valorString.replace(",", "."));
				    
				    Fachada.cadastrarCarro(placa, ano, cor, valor);
				    Fachada.adicionarCarroDeModelo(placa, nomeModelo);
				    byte[] bytesfoto = null;
					if (buffer != null)
						try {
							ByteArrayOutputStream baos = new ByteArrayOutputStream();
							ImageIO.write(buffer, "jpg", baos);
							bytesfoto = baos.toByteArray();
							baos.close();
						} catch (IOException ex1) {
							label.setText("problema na conversão da imagem em bytes");
						}
					System.out.println("Carro cadastrado");
				    Fachada.trocaFotoCarro(bytesfoto, placa);
				    System.out.println("Foto adicionada");
				    listagem();
				    label.setText("Carro cadastrado");
				   
				    

	

				} catch (NumberFormatException ex) {
				    System.out.println("Erro de conversão: O ano e o valor devem ser números válidos.");
				    JOptionPane.showMessageDialog(null, "O ano e o valor devem ser números válidos!");
				} catch (NullPointerException ex) {
				    System.out.println("Erro: Modelo não localizado.");
				    JOptionPane.showMessageDialog(null, "Modelo não localizado!");
				} catch (Exception e1) {
					label.setText(e1.getMessage());
					System.out.println("Cai nessa exceção");
					e1.printStackTrace();
				}
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_1.setBounds(23, 336, 153, 23);
		frame.getContentPane().add(button_1);

		button = new JButton("Listar");
		button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listagem();
			}
		});
		button.setBounds(359, 336, 89, 23);
		frame.getContentPane().add(button);

		label_3 = new JLabel("Modelo:");
		label_3.setHorizontalAlignment(SwingConstants.LEFT);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_3.setBounds(162, 218, 63, 14);
		frame.getContentPane().add(label_3);

		button_2 = new JButton("Deletar selecionado");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if (table.getSelectedRow() >= 0){
						label.setText("nao implementado " );
						String placa = (String) table.getValueAt( table.getSelectedRow(), 0);

						Fachada.excluirCarro(placa);
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
		button_2.setBounds(178, 336, 171, 23);
		frame.getContentPane().add(button_2);
		
		label_1 = new JLabel("Ano");
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_1.setBounds(172, 245, 71, 14);
		frame.getContentPane().add(label_1);
		
		fieldAno = new JTextField();
		fieldAno.setBounds(209, 243, 86, 20);
		frame.getContentPane().add(fieldAno);
		fieldAno.setColumns(10);
		
		label_5 = new JLabel("Cor: ");
		label_5.setHorizontalAlignment(SwingConstants.LEFT);
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_5.setBounds(10, 245, 71, 14);
		frame.getContentPane().add(label_5);
		
		fieldCor = new JTextField();
		fieldCor.setFont(new Font("Dialog", Font.PLAIN, 12));
		fieldCor.setColumns(10);
		fieldCor.setBounds(47, 242, 105, 20);
		frame.getContentPane().add(fieldCor);
		
		label_6 = new JLabel("Valor");
		label_6.setHorizontalAlignment(SwingConstants.LEFT);
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_6.setBounds(359, 218, 71, 14);
		frame.getContentPane().add(label_6);
		
		fieldValor = new JTextField();
		fieldValor.setColumns(10);
		fieldValor.setBounds(394, 215, 86, 20);
		frame.getContentPane().add(fieldValor);
		
		modeloComboBox = new JComboBox();
		modeloComboBox.setBounds(209, 215, 140, 22);
		frame.getContentPane().add(modeloComboBox);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(
						new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Foto",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(490, 202, 102, 105);
		frame.getContentPane().add(panel);
		
		label_7 = new JLabel("sem foto");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setBounds(10, 21, 78, 73);
		panel.add(label_7);
		
		button_3 = new JButton("Buscar foto");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fieldPlaca.getText().isEmpty()) {
					label.setText("selecione uma pessoa");
					return;
				}
				File file = selecionarArquivoFoto();
				if (file == null)
					return; 

				try {
					buffer = ImageIO.read(file);
					ImageIcon icon = new ImageIcon(
							buffer.getScaledInstance(buffer.getWidth(), buffer.getHeight(), Image.SCALE_DEFAULT));
					icon.setImage(icon.getImage().getScaledInstance(label_7.getWidth(), label_7.getHeight(), 1));
					label_7.setIcon(icon);
					label.setText("Precisa atualizar/criar carro para salvar a foto");
				} catch (IOException ex) {
					label.setText(ex.getMessage());
				}
			
			}
		});
		button_3.setBounds(602, 215, 108, 23);
		frame.getContentPane().add(button_3);
		
		button_4 = new JButton("Limpar foto");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						buffer = null;
						label_7.setIcon(null);
						label_7.setText("sem foto");
						label.setText("");
						label.setText("Precisa atualizar/criar pessoa para salvar a foto");

					}
			
		});
		button_4.setBounds(604, 268, 105, 23);
		frame.getContentPane().add(button_4);
		
		button_5 = new JButton("Atualizar");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fieldPlaca.getText().isEmpty())
					label.setText("nome vazio");
				else
					atualizarPessoaSelecionada();
			}
		});
		button_5.setToolTipText("atualizar pessoa ");
		button_5.setBounds(458, 337, 95, 23);
		frame.getContentPane().add(button_5);
	}

	public void listagem() {
		try{
			List<Carro> lista = Fachada.listarCarros();
			// model armazena todas as linhas e colunas do table
			DefaultTableModel model = new DefaultTableModel();
			table.setModel(model);

			//adicionar colunas no model
			model.addColumn("Placa");
			model.addColumn("Modelo");
			model.addColumn("Cor");
			model.addColumn("Ano");
			model.addColumn("Valor");;

			//adicionar linhas no model
			for(Carro car : lista)
				model.addRow(new Object[]{car.getPlaca(), car.getModelo().getNome(), car.getCor(), car.getAno(), car.getValor()} );

			label_4.setText("resultados: "+lista.size()+ " objetos");
		}
		catch(Exception erro){
			label.setText(erro.getMessage());
		}
	}
	
	public void carregarCombobox() {
	    try {
	    	Fachada.inicializar();
	        modeloComboBox.removeAllItems();
	        List<Modelo> fabricantes = Fachada.listarModelos();
	        for(Modelo fabricante : fabricantes ) {
	            modeloComboBox.addItem(fabricante.getNome());
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        
	        JOptionPane.showMessageDialog(frame, "Erro ao carregar fabricantes: " + e.getMessage());
	    }
	}
	
	public File selecionarArquivoFoto() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagens", "jpg", "gif");
		chooser.setFileFilter(filter);
		try {
			// exibir pasta externa no Windows
			// chooser.setCurrentDirectory(new File("c:\\"));
			// exibir pasta interna \fotos
			chooser.setCurrentDirectory(new File((new File(".").getCanonicalPath() + "\\src\\arquivos")));
		} catch (IOException e) {
		}
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.showOpenDialog(null);
		File file = chooser.getSelectedFile();
		return file;
	}
	
	public void atualizarPessoaSelecionada() {
		try {
			 String placa = fieldPlaca.getText();
			    
		    Modelo modeloObj = Fachada.localizarModelo(modeloComboBox.getSelectedItem().toString());
		    String nomeModelo = modeloObj.getNome();  
		    
		    String anoString = fieldAno.getText();
		    int ano = Integer.parseInt(anoString);
		    
		    String cor = fieldCor.getText();
		    
		    String valorString = fieldValor.getText();

		    Double valor = Double.parseDouble(valorString.replace(",", "."));
		    
		    Fachada.atualizarCarro(placa, cor, valor, ano);
		    Fachada.adicionarCarroDeModelo(placa, nomeModelo);
			byte[] bytesfoto = null;
			if (buffer != null)
				try {
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					ImageIO.write(buffer, "jpg", baos);
					bytesfoto = baos.toByteArray();
					baos.close();
				} catch (IOException ex1) {
					label.setText("problema na conversão da imagem em bytes");
				}
		    
		    Fachada.trocaFotoCarro(bytesfoto, placa);
		    label.setText("Carro atualizado");
		    
		} catch (Exception ex2) {
			label.setText(ex2.getMessage());
		}
	}

}
