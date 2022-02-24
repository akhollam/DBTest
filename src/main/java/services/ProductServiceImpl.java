package services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exceptions.ServiceException;
import modals.Product;
import utils.DBUtil;

public class ProductServiceImpl implements ProductService {

	@Override
	public void save(Product product) throws ServiceException {

		String sql = "INSERT INTO product(name, category, quantity, code, availableDate) VALUES (?, ?, ?, ?, ?);";

		try (Connection con = DBUtil.getConnection();
				PreparedStatement preparedStatement = con.prepareStatement(sql);) {

			preparedStatement.setString(1, product.getName());
			preparedStatement.setString(2, product.getCategory());
			preparedStatement.setInt(3, product.getQuantity());
			preparedStatement.setString(4, product.getCode());
			preparedStatement.setDate(5, new Date(System.currentTimeMillis()));

			preparedStatement.executeUpdate();

		} catch (SQLException e) {

			throw new ServiceException("Error while saving the product." + e.getMessage());
		}

	}

	@Override
	public void update(Product product) throws ServiceException {

		String sql = "UPDATE product set name = ?, category = ?, quantity = ?, code = ?, availableDate = ?"
				+ "WHERE id = ?;";

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

			throw new ServiceException("Error while saving the product." + e.getMessage());
		}
		
	}

	@Override
	public void delete(int productId) throws ServiceException {

		String sql = "DELETE FROM product WHERE id = ?";

		try (Connection con = DBUtil.getConnection();
				PreparedStatement preparedStatement = con.prepareStatement(sql);) {

			preparedStatement.setInt(1, productId);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new ServiceException("Error while saving the product." + e.getMessage());
		}

	}

	@Override
	public List<Product> findAll() throws ServiceException {

		String sql = "SELECT * FROM product;";
		List<Product> products = new ArrayList<Product>();

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

			throw new ServiceException("Error while saving the product." + e.getMessage());
		}

		return products;
	}

	@Override
	public Product findById(int productId) {
		// TODO Auto-generated method stub
		return null;
	}

}
