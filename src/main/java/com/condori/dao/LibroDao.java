package com.condori.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.condori.model.Libro;
import com.condori.util.HibernateUtil;

public class LibroDao {

	public LibroDao() {
		// TODO Auto-generated constructor stub
	}
	
	//guardar un libro
		public void guardarLibro(Libro libro) {
			Transaction transaction = null;
			try(Session session = HibernateUtil.getSessionFactory().openSession()) {
				//iniciar la transaccion
				transaction = session.beginTransaction();
				session.save(libro);
				transaction.commit();
			} catch (Exception e) {
				// TODO: handle exception
				if(transaction!=null) {
					transaction.rollback();
				}
			}
		}
		
		//obtener todos los libros
		public List<Libro> obtTodosLibros(){
			Transaction transaction = null;
			List<Libro> listaLibros = null;
			try(Session session = HibernateUtil.getSessionFactory().openSession()) {
				//iniciar la transaccion
				transaction = session.beginTransaction();
				//obtener la lista de libros
				listaLibros = session.createQuery("from Libro").list();
				transaction.commit();
			} catch (Exception e) {
				// TODO: handle exception
				if(transaction!=null) {
					transaction.rollback();
				}
			}
			return listaLibros;
		}
		
		//Obtener un libro por id
		public Libro obtLibroPorid(int id) {
			Transaction transaction = null;
			Libro libro = null;
			try(Session session = HibernateUtil.getSessionFactory().openSession()) {
				//iniciar la transaccion
				transaction = session.beginTransaction();
				//obtener el objeto Libro a partir del id
				libro = session.get(Libro.class, id);
				transaction.commit();
			} catch (Exception e) {
				// TODO: handle exception
				if(transaction!=null) {
					transaction.rollback();
				}
			}
			return libro;
		}
		
		//actualizar un libro
		public void actualizarLibro(Libro libro) {
			Transaction transaction = null;
			try(Session session = HibernateUtil.getSessionFactory().openSession()) {
				//iniciar la transaccion
				transaction = session.beginTransaction();
				session.saveOrUpdate(libro);
				transaction.commit();
			} catch (Exception e) {
				// TODO: handle exception
				if(transaction!=null) {
					transaction.rollback();
				}
			}
		}
		
		//eliminar un libro
		public void eliminarLibro(int id) {
			Transaction transaction = null;
			Libro libro = null;
			try(Session session = HibernateUtil.getSessionFactory().openSession()) {
				//iniciar la transaccion
				transaction = session.beginTransaction();
				//obtener el objeto Libro a partir del id
				libro = session.get(Libro.class, id);
				session.delete(libro);
				transaction.commit();
			} catch (Exception e) {
				// TODO: handle exception
				if(transaction!=null) {
					transaction.rollback();
				}
			}
		}
	
}
