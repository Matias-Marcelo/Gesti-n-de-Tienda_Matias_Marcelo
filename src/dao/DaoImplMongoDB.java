package dao;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import model.Amount;
import model.Employee;
import model.Product;



public class DaoImplMongoDB implements Dao{
	MongoCollection<Document> collection;
	MongoCollection<Document> collectionUser;
	MongoCollection<Document> collectionHistorical;
	ObjectId id;
	MongoClient mongoClient;

	@Override
	public void connect() {
		// TODO Auto-generated method stub
		String uri = "mongodb://localhost:27017";
		MongoClientURI mongoClientURI = new MongoClientURI(uri);
		mongoClient = new MongoClient(mongoClientURI);
		

		

	}

	@Override
	public Employee getEmployee(int employeeId, String password) {
		connect();
		// TODO Auto-generated method stub
		MongoDatabase mongoDatabase = mongoClient.getDatabase("shop");
		
		collectionUser = mongoDatabase.getCollection("users");
		
		
        Document employeedoc = collectionUser.find(eq("employeeId", employeeId))
                                      .filter(eq("password", password)) 
                                      .first();

        if (employeedoc != null) {
        	Employee employee = new Employee();
        
            employee.setEmployeeId(employeedoc.getInteger("employeeId"));       
           employee.setPassword(employeedoc.getString("password"));  
   		return employee;

        }
        
		return null;
	}

	@Override
	public void disconnect() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Product> getInventory() {
		// TODO Auto-generated method stub
		connect();
		MongoDatabase mongoDatabase = mongoClient.getDatabase("shop");
		collectionHistorical = mongoDatabase.getCollection("historical_inventory");
		collection = mongoDatabase.getCollection("inventory");
		ArrayList<Product> inventory = new ArrayList<>();
	    

	    FindIterable<Document> iterDoc = collection.find();

		
		for(Document doc : iterDoc) {
			String name = doc.getString("name");
	        Document wholesalerPriceDoc = (Document) doc.get("wholesalerPrice");
	        double value = wholesalerPriceDoc.getDouble("value");
	        String currency = wholesalerPriceDoc.getString("currency");
	        boolean available = doc.getBoolean("available");
	        int id = doc.getInteger("id");
	        int stock = doc.getInteger("stock");
	        
	        Amount wholesalerPrice = new Amount(value);
	        wholesalerPrice.setCurrency(currency);
	        
	        Product product = new Product(name, wholesalerPrice, available, stock, new Amount(wholesalerPrice.getValue() * 2));

			inventory.add(product);
			
			System.out.println(product);
		}
		
		return inventory;
	
	}

	@Override
	public boolean writeInventory(ArrayList<Product> inventory) {
		// TODO Auto-generated method stub
		connect();
	    try {
	        

	        for (Product product : inventory) {
	            Amount amount = new Amount();
	            amount = product.getWholesalerPrice();
	            Document document = new Document("_id", new ObjectId())
	                .append("name", product.getName())
	                .append("wholesalerPrice", 
	                    new Document("value", amount.getValue()).append("currency", amount.getCurrency()))
	                .append("available", product.isAvailable())
	                .append("stock", product.getStock())
	                .append("id", product.getId());

	            collectionHistorical.insertOne(document);
	        }
	        
	        return true; 
	        
	    } catch (MongoException e) {
	        System.err.println("Error en la operación con MongoDB: " + e.getMessage());
	    }
	    
	    return false; 
	}

	@Override
	public void addProduct(Product product) {
		// TODO Auto-generated method stub
       connect();
		Amount amount = new Amount();
		
		amount = product.getWholesalerPrice();
		Document document = new Document("_id", new ObjectId()).append("name", product.getName())
				.append("wholesalerPrice", new Document("value",amount.getValue()).append("currency", amount.getCurrency()))
				.append("available", product.isAvailable()).append("stock", product.getStock()).append("id", product.getId());
		collection.insertOne(document);
		
		System.out.println("document inserted" + document);

	}

	@Override
	public void updateProduct(Product product) {
		// TODO Auto-generated method stub
	
	    UpdateResult result = collection.updateOne(eq("id", product.getId()),
	        new Document("$inc", new Document("stock", product.getStock()))
	    );
	    product.setStock(product.getStock());
	    
	}

	@Override
	public void deleteProduct(int productId) {
		// TODO Auto-generated method stub
		connect();

		DeleteResult result = collection.deleteOne(eq("id", productId));
		System.out.println("document deleted by name" + result.toString());
		
	}

}
