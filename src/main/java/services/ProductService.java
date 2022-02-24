package services;

import java.util.List;

import exceptions.ServiceException;
import modals.Product;

public interface ProductService {

	void save(Product product) throws ServiceException;
	
	void update(Product product) throws ServiceException;
	
	void delete(int productId) throws ServiceException;
	
	List<Product> findAll() throws ServiceException;
	
	Product findById(int productId);
	
}
