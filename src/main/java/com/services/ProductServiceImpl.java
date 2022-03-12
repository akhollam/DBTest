package com.services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.exceptions.ServiceException;
import com.modals.Product;
import com.utils.DBUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ProductServiceImpl implements ProductService {
	
	@Override
	public void save(Product product) throws ServiceException {

		String sql = "INSERT INTO product(name, category, quantity, code, availableDate) VALUES (?, ?, ?, ?, ?);";
		log.trace("save query - {}", sql);
		
		try (Connection con = DBUtil.getConnection();
				PreparedStatement preparedStatement = con.prepareStatement(sql);) {

			preparedStatement.setString(1, product.getName());
			preparedStatement.setString(2, product.getCategory());
			preparedStatement.setInt(3, product.getQuantity());
			preparedStatement.setString(4, product.getCode());
			preparedStatement.setDate(5, new Date(System.currentTimeMillis()));

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			log.error("SQL Exception occured.", e);
			throw new ServiceException("Error while saving the product.", e);
		}

	}

	@Override
	public void update(Product product) throws ServiceException {

		String sql = "UPDATE product set name = ?, category = ?, quantity = ?, code = ?, availableDate = ?"
				+ "WHERE id = ?;";

		log.trace("update query - {}", sql);
		
		try (Connection con = DBUtil.getConnection();
				PreparedStatement preparedStatement = con.prepareStatement(sql);) {

			preparedStatement.setString(1, product.getName());
			preparedStatement.setString(2, product.getCategory());
			preparedStatement.setInt(3, product.getQuantity());
			preparedStatement.setString(4, product.getCode());
			preparedStatement.setDate(5, new Date(System.currentTimeMillis()));
			preparedStatement.setInt(6, product.getId());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			log.error("SQL Exception occured.", e);
			throw new ServiceException("Error while saving the product." + e.getMessage());
		}
		
	}

	@Override
	public void delete(int productId) throws ServiceException {

		String sql = "DELETE FROM product WHERE id = ?";
		
		log.trace("delete query - {}", sql);

		try (Connection con = DBUtil.getConnection();
				PreparedStatement preparedStatement = con.prepareStatement(sql);) {

			preparedStatement.setInt(1, productId);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			log.error("SQL Exception occured.", e);
			throw new ServiceException("Error while saving the product." + e.getMessage());
		}

	}

	@Override
	public List<Product> findAll() throws ServiceException {

		String sql = "SELECT * FROM product;";
		List<Product> products = new ArrayList<>();

		try (Connection con = DBUtil.getConnection();
				Statement preparedStatement = con.createStatement();
				ResultSet resultSet = preparedStatement.executeQuery(sql);) {

			while (resultSet.next()) {
				Product product = new Product();
				product.setId(resultSet.getInt("id"));
				product.setName(resultSet.getString("name"));
				product.setCategory(resultSet.getString("category"));
				product.setCode(resultSet.getString("code"));
				product.setQuantity(resultSet.getInt("quantity"));
				products.add(product);
			}

		} catch (SQLException e) {
			log.error("SQL Exception occured.", e);
			throw new ServiceException("Error while saving the product." + e.getMessage());
		}

		return products;
	}

	@Override
	public Product findById(int productId) {
		return null;
	}

	@Override
	public int productCount() {
		return 0;
	}

}
