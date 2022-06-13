package com.condori.util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.condori.model.Libro;
import com.condori.model.Tema;

public class HibernateUtil {
	
	private static SessionFactory sessionFactory;
	
	public static SessionFactory getSessionFactory() {
		if(sessionFactory ==null) {
			try {
				Configuration configuration = new Configuration();
				Properties settings = new Properties();
				settings.put(Environment.DRIVER,"com.mysql.jdbc.Driver");
				//?serverTimezone=UTC
				settings.put(Environment.URL,"jdbc:mysql://localhost:3306/cl1_libro?serverTimezone=UTC");
				settings.put(Environment.USER,"root");
				settings.put(Environment.PASS,"");
				settings.put(Environment.DIALECT,"org.hibernate.dialect.MySQL5Dialect");
				settings.put(Environment.SHOW_SQL,"true");
				settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS,"thread");
				settings.put(Environment.HBM2DDL_AUTO,"create-drop");
				configuration.setProperties(settings);
				configuration.addAnnotatedClass(Libro.class);
				configuration.addAnnotatedClass(Tema.class);
				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
													applySettings(configuration.getProperties()).build();
				
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
						
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}
	
	public HibernateUtil() {
		// TODO Auto-generated constructor stub
	}

}
