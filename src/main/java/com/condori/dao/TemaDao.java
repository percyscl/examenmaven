package com.condori.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.condori.model.Libro;
import com.condori.model.Tema;
import com.condori.util.HibernateUtil;


public class TemaDao {

	public TemaDao() {
		// TODO Auto-generated constructor stub
	}
	
	//guardar un tema
		public void guardarTemas(Tema tema) {
			Transaction transaction = null;
			try(Session session = HibernateUtil.getSessionFactory().openSession()) {
				//iniciar la transaccion
				transaction = session.beginTransaction();
				session.save(tema);
				transaction.commit();
			} catch (Exception e) {
				// TODO: handle exception
				if(transaction!=null) {
					transaction.rollback();
				}
			}
		}
		
		//obtener todos los temas
		public List<Tema> obtTodasTemas(){
			Transaction transaction = null;
			List<Tema> listaTemas = null;
			try(Session session = HibernateUtil.getSessionFactory().openSession()) {
				//iniciar la transaccion
				transaction = session.beginTransaction();
				//obtener la lista de temas
				listaTemas = session.createQuery("from Tema").list();
				transaction.commit();
			} catch (Exception e) {
				// TODO: handle exception
				if(transaction!=null) {
					transaction.rollback();
				}
			}
			return listaTemas;
		}
	
}
