package com.services;

import java.util.List;

import com.exceptions.ServiceException;
import com.modals.Product;

public interface ProductService {

	void save(Product product) throws ServiceException;
	
	void update(Product product) throws ServiceException;
	
	void delete(int productId) throws ServiceException;
	
	List<Product> findAll() throws ServiceException;
	
	int productCount();
	
	Product findById(int productId);
	
}
