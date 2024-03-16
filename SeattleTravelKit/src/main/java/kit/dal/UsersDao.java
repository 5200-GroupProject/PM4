package kit.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kit.model.Users;

public class UsersDao {
	protected ConnectionManager connectionManager;
	
	private static UsersDao instance = null;
	

	public UsersDao() {
		connectionManager = new ConnectionManager();
	}

	

	public static UsersDao getInstance() {
		if(instance == null) {
			instance = new UsersDao();
		}
		return instance;
	}
	
	
	public Users create(Users user) throws SQLException {
		Connection connection = null;
		PreparedStatement insertStmt = null;
		String insertUser= "INSERT INTO Users(UserName, Email, Password, FirstName, LastName, Phone) VALUES(?,?,?,?,?,?);";
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertUser);
			insertStmt.setString(1,user.getUserName());
			insertStmt.setString(2,user.getEmail());
			insertStmt.setString(3,user.getPassword());
			insertStmt.setString(4,user.getFirstName());
			insertStmt.setString(5,user.getLastName());
			insertStmt.setInt(6,user.getPhone());
			insertStmt.executeUpdate();
			
			return user;	
		}catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} 
		
	}
	
	
	public Users findUserByUserName(String userName) throws SQLException {
		Connection connection = null;
		PreparedStatement selectStmt = null;
		String selectUser = "SELECT UserName, Email, Password, FirstName, LastName, Phone FROM Users WHERE UserName=?;";
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUser);
			selectStmt.setString(1, userName);
			
			results = selectStmt.executeQuery();
			if(results.next()) {
				String resutUserName = results.getString("UserName");
				String email = results.getString("Email");
				String password = results.getString("Password");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				int phone = results.getInt("Phone");
				

				Users user = new Users(resutUserName, email, password, firstName, lastName, phone);
				return user;
		} 
		}catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return null;
		
	}
	
	
	public List<Users> findUserByEmail(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement selectStmt = null;
		String selectUser = "SELECT UserName, Email, Password, FirstName, LastName, Phone FROM Users WHERE Email=?;";
		ResultSet results = null;
		List<Users> users = new ArrayList<Users>();
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUser);
			selectStmt.setString(1, email);
			
			results = selectStmt.executeQuery();
			while(results.next()) {
				String resutUserName = results.getString("UserName");
				String password = results.getString("Password");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				int phone = results.getInt("Phone");
				

				Users user = new Users(resutUserName, email, password, firstName, lastName, phone);
				users.add(user);
			} 
			return users;
		}catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		
	}
	
	
	public Users updateUserPassword(Users user, String newPassword) throws SQLException {
		Connection connection = null;
		PreparedStatement updateStmt = null;
		String updateUser = "UPDATE Users SET Password=? WHERE UserName=?;";
		
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateUser);
			updateStmt.setString(1, newPassword);
			updateStmt.setString(2, user.getUserName());
			
			
			updateStmt.executeUpdate();
			user.setPassword(newPassword);
			
			return user;
		 
		}catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}

	}
	
	
	public Users updateUserEmail(Users user, String newEmail) throws SQLException {
		Connection connection = null;
		PreparedStatement updateStmt = null;
		String updateUser = "UPDATE Users SET Email=? WHERE UserName=?;";
		
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateUser);
			updateStmt.setString(1, newEmail);
			updateStmt.setString(2, user.getUserName());
			
			
			updateStmt.executeUpdate();
			user.setEmail(newEmail);
			
			return user;
		 
		}catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public Users deleteUser(Users user) throws SQLException {
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		String deleteUser = "DELETE FROM Users WHERE UserName=?;";
		
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteUser);
			deleteStmt.setString(1, user.getUserName());
			
			deleteStmt.executeUpdate();
			return null;
		}catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		
	}
	
	

}
