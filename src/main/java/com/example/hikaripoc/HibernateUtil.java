package com.example.hikaripoc;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.boraji.tutorial.hibernate.entity.Employee;


/**
 * @author imssbora
 */
public class HibernateUtil {

   private static SessionFactory sessionFactory;

   public static synchronized SessionFactory getSessionFactory() {
	   
	  
	  if(sessionFactory == null){
	  Properties p = new Properties();
	  String username = "deveki";
	  String pass = "deveki";
	  String url = "jdbc:oracle:thin:@localhost:1521:xe";
	  p.put("hibernate.connection.username", username);
	  p.put("hibernate.connection.password", pass); 
	  p.put("hibernate.connection.url", url);
	  p.put("hibernate.connection.driver_class", "oracle.jdbc.OracleDriver");
	  p.put("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
	  
	  
	  p.put("hibernate.connection.provider_class", "com.zaxxer.hikari.hibernate.HikariConnectionProvider");
	  p.put("hibernate.hikari.connectionTestQuery", "select 1 from dual");
	  p.put("hibernate.hikari.connectionTimeout", "60000");
	  p.put("hibernate.hikari.minimumIdle", "1");
	  p.put("hibernate.hikari.maximumPoolSize", "8");
	  p.put("hibernate.hikari.idleTimeout", "120000");
      //p.put("hibernate.hikari.maxLifetime", "180000");
      
      
	  Configuration config = new Configuration();
	  config.addAnnotatedClass(Employee.class);
	  config.addProperties(p);
	  
	  ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
	  
	  sessionFactory = config.buildSessionFactory(registry);
	  
	  
	  }
	  
      return sessionFactory;
   }

   
}
