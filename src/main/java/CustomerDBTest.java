import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utils.DBUtil;

public class CustomerDBTest {

	public static void main(String[] args) {

//		selectTest();
//		System.out.println("------------");
		updateTest();
//		System.out.println("------------");
//		selectTest();
//		System.out.println("------------");
//		deleteTest();
//		System.out.println("------------");
//		selectTest();

		insertTest();
	}

	private static void deleteTest() {

		String sql = "DELETE FROM members WHERE member_id >= ?";

		try (Connection connection = DBUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);) {

			statement.setInt(1, 112);
			Integer count = statement.executeUpdate();

			System.out.println("Record delete - " + count);

		} catch (

		SQLException e) {

			e.printStackTrace();
		}

	}

	private static void updateTest() {

		String sql = "UPDATE customers SET contactFirstName = ?, contactLastName = ? WHERE customerNumber = ?";

		try (Connection connection = DBUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);) {

			statement.setString(1, "Sachin");
			statement.setString(2, "Tendulkar");
			statement.setInt(3, 112);

			Integer count = statement.executeUpdate();

			System.out.println("Record updated - " + count);

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	private static void selectTest() {

		try (Connection connection = DBUtil.getConnection();

				Statement statement = connection.createStatement();

				ResultSet resultSet = statement
						.executeQuery("SELECT customerNumber, contactFirstName, contactLastName FROM customers");) {

			while (resultSet.next()) {

				Integer custNo = resultSet.getInt("customerNumber");
				String custName = resultSet.getString("contactFirstName");
				String contactLastName = resultSet.getString("contactLastName");

				System.out.println(custNo + "\t" + custName + "\t" + contactLastName);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	private static void insertTest() {

		String sql = "INSERT INTO members(name) VALUES(?);";

		try (Connection connection = DBUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

			statement.setString(1, "Akshay");
			int rowAffected = statement.executeUpdate();

			System.out.println("rowAffected - " + rowAffected);

			ResultSet resultSet = statement.getGeneratedKeys();

			if (resultSet.next()) {

				System.out.println(resultSet.getInt(1));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
