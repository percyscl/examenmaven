package com.condori.vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.condori.dao.LibroDao;
import com.condori.dao.TemaDao;
import com.condori.model.Libro;
import com.condori.model.Tema;

public class FrmCrudLibros extends JFrame implements ActionListener, MouseListener{

	private JPanel contentPane;
	private JTextField txtTitulo;
	private JTextField txtPrecio;
	private JTextField txtCantidad;
	private JTextField txtOrigen;
	private JButton btnRegistrar;
	private JButton btnActualizar;
	private JButton btnEliminar;
	private JTable table;
	private JComboBox<Tema> cboTemas;
	List<Tema> temas = null;	
	LibroDao daoLibro = new LibroDao();	
	int idSeleccionado = -1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCrudLibros frame = new FrmCrudLibros();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FrmCrudLibros() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 485);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblHeadLibro = new JLabel("Mantenimiento de Libros");
		lblHeadLibro.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeadLibro.setForeground(Color.BLUE);
		lblHeadLibro.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblHeadLibro.setBounds(10, 11, 414, 35);
		contentPane.add(lblHeadLibro);

		JLabel lblTitulo = new JLabel("Titulo");
		lblTitulo.setBounds(60, 57, 84, 26);
		contentPane.add(lblTitulo);

		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(60, 88, 84, 26);
		contentPane.add(lblPrecio);

		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(60, 119, 84, 26);
		contentPane.add(lblCantidad);
		
		JLabel lblOrigen = new JLabel("Origen");
		lblOrigen.setBounds(60, 150, 84, 26);
		contentPane.add(lblOrigen);
		
		JLabel lblTemas = new JLabel("Tema");
		lblTemas.setBounds(60, 181, 84, 26);
		contentPane.add(lblTemas);

		txtTitulo = new JTextField();
		txtTitulo.setBounds(150, 60,211, 20);
		contentPane.add(txtTitulo);
		txtTitulo.setColumns(10);

		txtPrecio = new JTextField();
		txtPrecio.setBounds(150, 91,211, 20);
		contentPane.add(txtPrecio);
		txtPrecio.setColumns(10);

		txtCantidad = new JTextField();
		txtCantidad.setBounds(150, 122,211, 20);
		contentPane.add(txtCantidad);
		txtCantidad.setColumns(10);
		
		txtOrigen = new JTextField();
		txtOrigen.setBounds(150, 153,211, 20);
		contentPane.add(txtOrigen);
		txtOrigen.setColumns(10);
				
		cboTemas = new JComboBox();
		cboTemas.setBounds(150, 181, 211, 20);
		contentPane.add(cboTemas);

		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(this);
		btnRegistrar.setBounds(10, 210, 114, 23);
		contentPane.add(btnRegistrar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 243, 424, 184);
		contentPane.add(scrollPane);
				
		table = new JTable();
		table.addMouseListener(this);
		table.setModel(new DefaultTableModel(
				new Object[][] {					
				},
				new String [] {
						"Id", "Titulo", "Precio", "CantEjemplares", "Origen", "Tema"
				}
				));
		scrollPane.setViewportView(table);
		
		//carga cbo temas
		cargaComboTemas();
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(this);
		btnActualizar.setBounds(310, 210, 114, 23);
		contentPane.add(btnActualizar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(159, 210, 114, 23);
		contentPane.add(btnEliminar);
		
		//listar libros
		//listaLibros();
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==btnRegistrar) {			
			fnbtnRegistrar_actionPerformed(e);
		}
		
		if(e.getSource()==btnActualizar) {			
			fnbtnActualizar_actionPerformed(e);
		}
		
		if(e.getSource()==btnEliminar) {			
			fnbtnEliminar_actionPerformed(e);
		}
	}
	
	protected void fnbtnRegistrar_actionPerformed(ActionEvent e) {
		
		int cantidad = Integer.parseInt(txtCantidad.getText());
		String origen = txtOrigen.getText().trim();
		Double precio = Double.parseDouble(txtPrecio.getText());
		String titulo = txtTitulo.getText().trim();
		Tema tema = (Tema)cboTemas.getSelectedItem();
		
		
		Libro libro = new Libro();
		libro.setTitulo(titulo);
		libro.setPrecio(precio);
		libro.setCantEjemplares(cantidad);
		libro.setOrigen(origen);
		libro.setTema(tema);

		daoLibro.guardarLibro(libro);
		System.out.println("Registrado Correctamente!");				
		listaLibros();	
		limpiarControles();
	}
	
	protected void fnbtnActualizar_actionPerformed(ActionEvent e) {
		
		int cantidad = Integer.parseInt(txtCantidad.getText());
		String origen = txtOrigen.getText().trim();
		Double precio = Double.parseDouble(txtPrecio.getText());
		String titulo = txtTitulo.getText().trim();					
				
		Libro libro = new Libro();
		libro.setIdlibro(idSeleccionado);
		libro.setTitulo(titulo);
		libro.setPrecio(precio);
		libro.setCantEjemplares(cantidad);
		libro.setOrigen(origen);
		
		daoLibro.actualizarLibro(libro);
		System.out.println("Registrado Correctamente!");
				
		listaLibros();
						
		limpiarControles();
	}
	
	protected void fnbtnEliminar_actionPerformed(ActionEvent e) {
		daoLibro.eliminarLibro(idSeleccionado);
		listaLibros();
		limpiarControles();
	}
	
	private void listaLibros() {	
		List<Libro> data = daoLibro.obtTodosLibros();		
		DefaultTableModel dtm = (DefaultTableModel)table.getModel();
		dtm.setRowCount(0);		
		for(Libro e: data) {
			Object[] fila = {
					e.getIdlibro(),
					e.getTitulo(),
					e.getPrecio(),
					e.getCantEjemplares(),
					e.getOrigen(), 
					e.getTema().getNombre()
			};
			dtm.addRow(fila);
		}
		
		dtm.fireTableDataChanged();
	}
	
	private void limpiarControles() {
		txtTitulo.setText("");
		txtPrecio.setText("");
		txtCantidad.setText("");
		txtOrigen.setText("");
		txtTitulo.requestFocus();		
	}
	
	public void mouseClicked(MouseEvent e) {		
		if(e.getSource()==table) {			
			fntable_mouseClicked(e);
		}
	}
	
	protected void  fntable_mouseClicked(MouseEvent e) {
		int fila = table.getSelectedRow();
		DefaultTableModel dtm = (DefaultTableModel)table.getModel();
		idSeleccionado = (int) dtm.getValueAt(fila, 0);
		String tit = (String) dtm.getValueAt(fila, 1);
		Double pre = (Double) dtm.getValueAt(fila, 2);
		Integer can = (int) dtm.getValueAt(fila, 3);
		String ori = (String) dtm.getValueAt(fila, 4);
		String tem = (String) dtm.getValueAt(fila, 5);
		
		txtTitulo.setText(tit);
		txtPrecio.setText(pre+"");
		txtCantidad.setText(can+"");
		txtOrigen.setText(ori);
		cboTemas.setSelectedItem(tem);
		
	}
	
	private void cargaComboTemas() {
		Tema t1 = new Tema("Fabulas");
		Tema t2 = new Tema("Novelas");
		Tema t3 = new Tema("Drama");
		
		TemaDao temaDao = new TemaDao();
		
		temaDao.guardarTemas(t1);
		temaDao.guardarTemas(t2);
		temaDao.guardarTemas(t3);
		
		temas = temaDao.obtTodasTemas();
		
		for(Tema c: temas) {
			cboTemas.addItem(c);
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
