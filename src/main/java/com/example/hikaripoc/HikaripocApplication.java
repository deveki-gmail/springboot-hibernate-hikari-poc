package com.example.hikaripoc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boraji.tutorial.hibernate.entity.Employee;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@RestController
@SpringBootApplication
public class HikaripocApplication {

	public static void main(String[] args) {
		SpringApplication.run(HikaripocApplication.class, args);
	}
	
	static public List<Connection> connections = new ArrayList<>();
	
	@GetMapping("/start")
	public String start(){
		System.out.println("start request received.");
		MyTask task = new MyTask();
		for(int i=0; i < 500; i++){ // start 30 threads
			Thread t = new Thread(task);
			t.start();
			System.out.println("thread started....count = "+i);
		}
		
		
		return "Started";
	}
	
	
	
	
	
	
	
}

class MyTask implements Runnable{
	
	public MyTask(){
	}
	private void executeQuery(){
		
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			
			String sql = "select * from EMP";
			
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(Employee.class);
			
			List<Employee> results = query.list();
			System.out.println("no of employees :  "+results.size());
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				session.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void run(){
		executeQuery();
	}
}