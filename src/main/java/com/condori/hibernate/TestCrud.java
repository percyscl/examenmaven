package com.condori.hibernate;

import java.util.List;

import com.condori.dao.LibroDao;
import com.condori.model.Libro;

import com.condori.dao.TemaDao;
import com.condori.model.Tema;

public class TestCrud {

	public TestCrud() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
				
		TemaDao dao = new TemaDao();
		Tema est1 = new Tema("Economia");
		dao.guardarTemas(est1);
		
		Tema est2 = new Tema("General");
		dao.guardarTemas(est2);			
		
		LibroDao daoLib = new LibroDao();
		Libro lib1 = new Libro("El Drama de America Latina", 40.0, 80, "John Gunther", est1);
		Libro lib2 = new Libro("La Riqueza de las Naciones", 30.0, 90, "Adam Smith", est2);
		Libro lib3 = new Libro("Mi libro", 30.0, 90, "Yo");
		daoLib.guardarLibro(lib1);
		daoLib.guardarLibro(lib2);
		daoLib.guardarLibro(lib3);
		
		//llamar al metodo para obtener todos los libros
		List<Libro> listaLibros = daoLib.obtTodosLibros();
		listaLibros.forEach(est->System.out.println(est.getIdlibro()+"-"+est.getTitulo()));		
		
		//modificar un objeto libro
		lib1.setTitulo("El Drama de America Latina 1.0");
		daoLib.actualizarLibro(lib1);
		lib2.setTitulo("La Riqueza de las Naciones 1.0");
		daoLib.actualizarLibro(lib2);
		
		//llamar al metodo para obtener todos los estudiantes
		listaLibros = daoLib.obtTodosLibros();
		listaLibros.forEach(est->System.out.println(est.getIdlibro()+"-"+est.getTitulo()));
		
		//eliminar un estudiante
		daoLib.eliminarLibro(lib3.getIdlibro());

		//llamar al metodo para obtener todos los estudiantes
		listaLibros = daoLib.obtTodosLibros();
		listaLibros.forEach(est->System.out.println(est.getIdlibro()+"-"+est.getTitulo()));
		
	}
	
}
