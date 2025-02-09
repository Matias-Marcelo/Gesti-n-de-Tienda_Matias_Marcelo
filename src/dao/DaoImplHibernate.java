package dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import model.Amount;
import model.Employee;
import model.Product;
import model.ProductHistory;


public class DaoImplHibernate implements Dao {
	private Transaction tx;
	private Session session;
	@Override
	public void connect() {
//		// TODO Auto-generated method stub
//
//		Configuration config = new Configuration();
//		config.configure("hibernate.cfg.xml");
		// En este punto se crea el SessionFactory local
//		SessionFactory sessionFactory = config.buildSessionFactory();
//		session = sessionFactory.getCurrentSession();
		
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		org.hibernate.SessionFactory sessionFactory = configuration.buildSessionFactory();
    	session = sessionFactory.openSession();		
	}

	@Override
	public Employee getEmployee(int employeeId, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void disconnect() {
		// TODO Auto-generated method stub
		session.close();
	}

	@Override
	public ArrayList<Product> getInventory() {

	    ArrayList<Product> inventory = new ArrayList<>();

	    connect();
		
		try {
			
			 tx = session.beginTransaction();
			 
			Query q = session.createQuery("FROM Product");
			
			List<Product> inventoryList = q.getResultList();
			
			inventory.addAll(inventoryList);
			
			for (Product p : inventory) {
				double amount = p.getPrice();
				Amount amountPrice =  new Amount(amount);
				p.setWholesalerPrice(amountPrice);
				System.out.println(p);
			}
			tx.commit();
			
			 //inventory = session.createQuery(Product.class).list();
		}catch(HibernateException e){
			tx.rollback();
			throw new RuntimeException(e);
		}

		disconnect();
		return inventory;
	}

	@Override
	public boolean writeInventory(ArrayList<Product> inventory) {
	   connect();
	   try {
	        tx = session.beginTransaction();
	    
	        for (Product product : inventory) {
	        	ProductHistory history = new ProductHistory();
	            history.setAvailable(true);
	            history.setCreatedAt(LocalDateTime.now());
	            history.setIdProduct(product.getId());
	            history.setName(product.getName());
	            history.setPrice(product.getPrice());
	            history.setStock(product.getStock());
	            
	            session.save(history);
	        }
	        
	        tx.commit();
	        disconnect();
	        return true;
	    } catch (HibernateException e) {
	        if (tx != null) tx.rollback();
	        e.printStackTrace();
	        return false;
	    }
	}



	@Override
	public void addProduct(Product product) {
		// TODO Auto-generated method stub
		connect();
		try {
			
			tx = session.beginTransaction();
			
			session.save(product);
			
			tx.commit();
			
		}catch(HibernateException e) {
		
			throw new RuntimeException(e);

		}
		disconnect();
		
	}

	@Override
	public void updateProduct(Product product) {
		// TODO Auto-generated method stub
		connect();
		try {
			tx = session.beginTransaction();
			
			session.update(product);
			tx.commit();
			System.out.println("Updated Successfully.");

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback(); // Roll back if any exception occurs.
			e.printStackTrace();
		}
		disconnect();
		
	}

	@Override
	public void deleteProduct(int productId) {
		// TODO Auto-generated method stub
		connect();
		Product product = new Product();
		try {
			tx = session.beginTransaction();
			product.setId(productId);
			session.remove(product);
			tx.commit();
			System.out.println("Deleted Successfully.");

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback(); // Roll back if any exception occurs.
			e.printStackTrace();
		}
		disconnect();
	}

}
