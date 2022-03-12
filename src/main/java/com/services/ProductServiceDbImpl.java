package com.services;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.exceptions.ServiceException;
import com.modals.Product;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("productServiceDbImpl")
public class ProductServiceDbImpl implements ProductService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void save(Product product) throws ServiceException {
		
		log.debug("saving a product. {}", product);

		String sql = "INSERT INTO product(name, category, quantity, code, availableDate) VALUES (?, ?, ?, ?, ?);";
		jdbcTemplate.update(sql, product.getName(), product.getCategory(), product.getQuantity(), product.getCode(),
				new Date(System.currentTimeMillis()));

	}

	@Override
	public void update(Product product) throws ServiceException {

		String sql = "UPDATE product set name = ?, category = ?, quantity = ?, code = ?, availableDate = ?"
				+ "WHERE id = ?;";

		jdbcTemplate.update(sql, product.getName(), product.getCategory(), product.getQuantity(), product.getCode(),
				new Date(System.currentTimeMillis()), product.getId());

	}

	@Override
	public void delete(int productId) throws ServiceException {

		String sql = "DELETE FROM product WHERE id = ?";
		jdbcTemplate.update(sql, productId);
	}

	@Override
	public List<Product> findAll() throws ServiceException {

		String sql = "SELECT * FROM product;";
		List<Product> productList = jdbcTemplate.query(sql, new ProductRowMapper());
		return productList;

	}

	@Override
	public Product findById(int productId) {
		String sql = "SELECT * FROM product where id = ? ";
		Product p = jdbcTemplate.queryForObject(sql, new ProductRowMapper(), productId);
		return p;
	}

	@Override
	public int productCount() {
		String sql = "SELECT count(id) FROM product";
		int count = jdbcTemplate.queryForObject(sql, Integer.class);
		return count;
	}

}

class ProductRowMapper implements RowMapper<Product> {

	@Override
	public Product mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Product product = new Product();
		product.setId(resultSet.getInt("id"));
		product.setName(resultSet.getString("name"));
		product.setCategory(resultSet.getString("category"));
		product.setCode(resultSet.getString("code"));
		product.setQuantity(resultSet.getInt("quantity"));
		return product;
	}

}
