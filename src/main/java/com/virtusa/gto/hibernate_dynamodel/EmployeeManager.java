package com.virtusa.gto.hibernate_dynamodel;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.BasicConfigurator;
import org.hibernate.EntityMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import com.google.gson.Gson;

public class EmployeeManager {
	
	public static void main(String[] args) {
		EmployeeManager employeeManager = new EmployeeManager();
		Configuration configuration = employeeManager.init();

//		employeeManager.createSchema(configuration);
//		employeeManager.saveTestData(configuration);
		Map<String, Object> employee = employeeManager.getEmployee(configuration);
		System.out.println(employee);
		String jsonEmployee = new Gson().toJson(employee);
		System.out.println(jsonEmployee);
		System.out.println("Operation Completed!");
	}

	private Map<String, Object> getEmployee(Configuration configuration) {
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Session dynamicSession = session.getSession(EntityMode.MAP);
//		Transaction tx = session.beginTransaction();
		Map<String, Object> entity = (Map<String, Object>) dynamicSession.get("Employee", new Long(1));
		dynamicSession.close();
		return entity;
	}

	private Configuration init() {
		BasicConfigurator.configure();
		Configuration configuration = new Configuration();
		// mappingClass.xml is generated according to the information entered by
		// user
		configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
		configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/sukhitha");
		configuration.setProperty("hibernate.connection.username", "root");
		configuration.setProperty("hibernate.connection.password", "root");
		configuration.setProperty("hibernate.show_sql", "true");
		// configuration.addResource("hibernate.cfg.xml");
		configuration.addResource("mappingClass.xml");
		return configuration;
	}

	private void createSchema(Configuration configuration) {
		SchemaExport schemaExport = new SchemaExport(configuration);
		schemaExport.create(true, true);
	}

	private void saveTestData(Configuration configuration) {
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Session dynamicSession = session.getSession(EntityMode.MAP);

		// Create a customer
		Map<String, Object> david = new HashMap<String, Object>();
		david.put("id", 4);
		david.put("name", "David");
		david.put("address", "testsdgdsgstsdgf sgssd");
		
		Map<String, Object> finance = new HashMap<String, Object>();
		finance.put("name", "finance");
		
		Map<String, Object> colombo = new HashMap<String, Object>();
		colombo.put("name", "Colombo");
		
		Map<String, Object> kandy = new HashMap<String, Object>();
		kandy.put("name", "Kandy");
		
		Set<Map<String, Object>> cities = new HashSet<Map<String, Object>>();
		cities.add(colombo);
		cities.add(kandy);
		
		finance.put("cities", cities);
		
		david.put("department", finance);
		
		dynamicSession.save("Employee", david);
//		dynamicSession.save("Department", finance);
		// dynamicSession.flush();
		tx.commit();
		dynamicSession.close();
	}

//	Session dom4jSession = session.getSession(EntityMode.DOM4J);
//	Element cust = (Element) dom4jSession.get("Customer", 2);
//	List results = dom4jSession
//		    .createQuery("name from Customer")
//		    .list();
	
	
	
	
	
	
	
//	Map<String, Object> data = new HashMap<String, Object>();
//	data.put("name", new String("Kamal"));
//	session.save("Customer", data);
//	session.get("Customer", 2);
//	session.close();
//	System.out.println(data);
//      try{
//         factory = new AnnotationConfiguration().
//                   configure().
//                   //addPackage("com.xyz") //add package if used.
//                   addAnnotatedClass(Employee.class).
//                   buildSessionFactory();
//      }catch (Throwable ex) { 
//         System.err.println("Failed to create sessionFactory object." + ex);
//         throw new ExceptionInInitializerError(ex); 
//      }
//      ManageEmployee ME = new ManageEmployee();
//
//      /* Add few employee records in database */
//      Integer empID1 = ME.addEmployee("Zara", "Ali", 1000);
//      Integer empID2 = ME.addEmployee("Daisy", "Das", 5000);
//      Integer empID3 = ME.addEmployee("John", "Paul", 10000);
//
//      /* List down all the employees */
//      ME.listEmployees();
//
//      /* Update employee's records */
//      ME.updateEmployee(empID1, 5000);
//
//      /* Delete an employee from the database */
//      ME.deleteEmployee(empID2);
//
//      /* List down new list of the employees */
//      ME.listEmployees();
   
   /* Method to CREATE an employee in the database */
//   public Integer addEmployee(String fname, String lname, int salary){
//      Session session = factory.openSession();
//      Transaction tx = null;
//      Integer employeeID = null;
//      try{
//         tx = session.beginTransaction();
//         Employee employee = new Employee();
//         employee.setFirstName(fname);
//         employee.setLastName(lname);
//         employee.setSalary(salary);
//         employeeID = (Integer) session.save(employee); 
//         tx.commit();
//      }catch (HibernateException e) {
//         if (tx!=null) tx.rollback();
//         e.printStackTrace(); 
//      }finally {
//         session.close(); 
//      }
//      return employeeID;
//   }
//   /* Method to  READ all the employees */
//   public void listEmployees( ){
//      Session session = factory.openSession();
//      Transaction tx = null;
//      try{
//         tx = session.beginTransaction();
//         List employees = session.createQuery("FROM Employee").list(); 
//         for (Iterator iterator = 
//                           employees.iterator(); iterator.hasNext();){
//            Employee employee = (Employee) iterator.next(); 
//            System.out.print("First Name: " + employee.getFirstName()); 
//            System.out.print("  Last Name: " + employee.getLastName()); 
//            System.out.println("  Salary: " + employee.getSalary()); 
//         }
//         tx.commit();
//      }catch (HibernateException e) {
//         if (tx!=null) tx.rollback();
//         e.printStackTrace(); 
//      }finally {
//         session.close(); 
//      }
//   }
//   /* Method to UPDATE salary for an employee */
//   public void updateEmployee(Integer EmployeeID, int salary ){
//      Session session = factory.openSession();
//      Transaction tx = null;
//      try{
//         tx = session.beginTransaction();
//         Employee employee = 
//                    (Employee)session.get(Employee.class, EmployeeID); 
//         employee.setSalary( salary );
//		 session.update(employee); 
//         tx.commit();
//      }catch (HibernateException e) {
//         if (tx!=null) tx.rollback();
//         e.printStackTrace(); 
//      }finally {
//         session.close(); 
//      }
//   }
//   /* Method to DELETE an employee from the records */
//   public void deleteEmployee(Integer EmployeeID){
//      Session session = factory.openSession();
//      Transaction tx = null;
//      try{
//         tx = session.beginTransaction();
//         Employee employee = 
//                   (Employee)session.get(Employee.class, EmployeeID); 
//         session.delete(employee); 
//         tx.commit();
//      }catch (HibernateException e) {
//         if (tx!=null) tx.rollback();
//         e.printStackTrace(); 
//      }finally {
//         session.close(); 
//      }
//   }
}