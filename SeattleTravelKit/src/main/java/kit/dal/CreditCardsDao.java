package kit.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kit.model.CreditCards;


public class CreditCardsDao {
	protected ConnectionManager connectionManager;
	
	private static CreditCardsDao instance = null;
	

	public CreditCardsDao() {
		connectionManager = new ConnectionManager();
	}

	
	public static CreditCardsDao getInstance() {
		if(instance == null) {
			instance = new CreditCardsDao();
		}
		return instance;
	}
	
	
	public CreditCards create(CreditCards card) throws SQLException {
		Connection connection = null;
		PreparedStatement insertStmt = null;
		String insertCard = "INSERT INTO CreditCards(UserName, CardNumber, Expiration) VALUES(?,?,?);";
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCard);
			insertStmt.setString(1,card.getUserName());
			insertStmt.setString(2,card.getCardNumber());
			insertStmt.setString(3,card.getExpiration());
			insertStmt.executeUpdate();
			
			return card;	
		}catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} 
		
	}
	

	public CreditCards findCardByCardNumber(String cardNumber) throws SQLException {
		Connection connection = null;
		PreparedStatement selectStmt = null;
		String selectCard = "SELECT UserName, CardNumber, Expiration FROM CreditCards WHERE CardNumber=?;";
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCard);
			selectStmt.setString(1,cardNumber);
			
			results = selectStmt.executeQuery();
			if (results.next()) {
				String userName = results.getString("UserName");
				String expiration = results.getString("Expiration");
				
				
				CreditCards card = new CreditCards(userName, cardNumber, expiration);
				return card;
				} 
		}catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return null; 
			
	}
	
	
	public List<CreditCards> findCardsByUserName(String userName) throws SQLException {
		List<CreditCards> cards = new ArrayList<CreditCards>();
		Connection connection = null;
		PreparedStatement selectStmt = null;
		String selectCard = "SELECT UserName, CardNumber, Expiration FROM CreditCards WHERE CardNumber=?;";
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCard);
			selectStmt.setString(1,userName);
			
			results = selectStmt.executeQuery();
			while (results.next()) {
				String cardNumber = results.getString("CardNumber");
				String expiration = results.getString("Expiration");
				
				
				CreditCards card = new CreditCards(userName, cardNumber, expiration);
				cards.add(card);
				} 
			return cards;
			}catch (SQLException e) {
				e.printStackTrace();
				throw e;
			} 

	}
	
	
	public CreditCards updateExpiration(CreditCards card, String newExpiration) throws SQLException {
		Connection connection = null;
		PreparedStatement updateStmt = null;
		String updateCard = "UPDATE CreditCards SET Expitation=? WHERE CardNumber=?;";
		
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateCard);
			
			updateStmt.setString(1,newExpiration);
			updateStmt.setString(2,  card.getCardNumber());
			
			updateStmt.executeUpdate();
			
			card.setExpiration(newExpiration);
			return card;
			
		}catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} 

	}
	
	
	public CreditCards delete(CreditCards card) throws SQLException {
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		String deleteCard= "DELETE FROM CreditCards WHERE CardNumber=?;";
		
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCard);
			
			deleteStmt.setString(1, card.getCardNumber());
			deleteStmt.executeUpdate();

			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} 
	}

}
