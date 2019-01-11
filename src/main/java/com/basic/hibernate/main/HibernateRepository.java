package com.basic.hibernate.main;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import com.basic.hibernate.dto.Laptop;
import com.basic.hibernate.dto.User;
import com.basic.hibernate.dto.Vehicle;

public class HibernateRepository {
	
	ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
	Metadata metaData = new  MetadataSources(serviceRegistry).getMetadataBuilder().build();
	SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
	
	
	public void saveUser (User user) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		session.save(user);
		
		transaction.commit();
		session.close();
	}
	
	public void getUserById(long id) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		User user = session.get(User.class, id);
		System.out.println("User 1 name : " + user.getName());
		
		transaction.commit();
		session.close();
	}
	
	public void saveUserWithOneToOne () {
		// To add new class into cfg file
		// add new field into user class as vehicle_id
		// set vehicle object into user obj. 
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		User user = new User();
		user.setName("One to one - 3");
		user.setSurname("One to one- 3");
		
		Vehicle vehicle = new Vehicle();
		vehicle.setName("One to one name - 3");
		vehicle.setNumber(1328);
		
		//---------------//
		user.setVehicle(vehicle);
		//--------------//
		
		session.save(user);
		session.save(vehicle);
		
		transaction.commit();
		session.close();
	}
	
	public void saveUserByOneToMany() {
		
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		User user = new User();
		user.setName("One to one - 7");
		user.setSurname("One to one- 7");
		
		List<Laptop> laptops = new ArrayList<Laptop>(); 
		
		Laptop laptop1 = new Laptop();
		laptop1.setCompanyName("company name 7");
		laptop1.setRamSize(256);
		laptop1.setUser(user);
		
		Laptop laptop2 = new Laptop();
		laptop2.setCompanyName("company name 8");
		laptop2.setRamSize(512);
		laptop2.setUser(user);
		
		laptops.add(laptop1);
		laptops.add(laptop2);
		user.setLaptop(laptops);
		
		session.save(user);
		/*session.save(laptop1);
		session.save(laptop2);*/
		
		transaction.commit();
		session.close();
	}
	
	public void getUserAndUserListByHQL() {
		
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		long id = 3;
		/*Query<User> query = session.createQuery("from User where id < ?1 ",User.class);
		query.setParameter(1, id);*/
		
		Query<User> query = session.createQuery("from User where id < :id ",User.class);
		query.setParameter("id", id);
		
		/*query.setFirstResult(5);*/
		/*query.setMaxResults(10);*/
		
		List<User> users = query.getResultList();
		for (User user : users) {
			System.out.println("List of all User by HQL : "+user);
		}
 		
		transaction.commit();
		session.close();
	}
	
	@SuppressWarnings("unchecked")
	public void getUniqueUserById() {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		Query<User> query = session.getNamedQuery("get-user-by-id");
		long id = 2;
		query.setParameter("id", id);		
		
		User user = query.uniqueResult();
		System.out.println("Got user : "+ user);
		
		transaction.commit();
		session.close();
	}
	
	private void getUserByCriteriaApi() {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		CriteriaQuery<User> criteria = session.getCriteriaBuilder().createQuery(User.class);		
		
		transaction.commit();
		session.close();
	}
	
	public void getUserListByNativeQuery() {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		NativeQuery<User> nativeQuery = session.createNativeQuery("select * from user", User.class);
		List<User> userList = nativeQuery.list();
		for (User user : userList) {
			System.out.println("User from native Query : " + user);
		}
		
		NativeQuery<Object[]> nativeQuery2= session.createNativeQuery("select v.id, v.name from user u inner join vehicle v on v.id = u.vehicle_id");
		List<Object[]> vehicleList = nativeQuery2.list();
		for (Object[] vehicle : vehicleList) {
			System.out.println("vehicle from native Query : " + vehicle[1]);
		}
		
		transaction.commit();
		session.close();
	}
	
}
