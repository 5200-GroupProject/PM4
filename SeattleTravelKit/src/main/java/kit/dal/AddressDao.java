package kit.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kit.model.Address;

public class AddressDao {
    protected ConnectionManager connectionManager;

    private static AddressDao instance = null;
    protected AddressDao() {
        connectionManager = new ConnectionManager();
    }
    public static AddressDao getInstance() {
        if(instance == null) {
            instance = new AddressDao();
        }
        return instance;
    }

    public Address create(Address address) throws SQLException {
        String insertAddress = "INSERT INTO Address(UserName,City,Street1,Street2,State,ZipCode,Country) " +
                "VALUES(?,?,?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertAddress);
            insertStmt.setString(1, address.getUserName());
            insertStmt.setString(2, address.getCity());
            insertStmt.setString(3, address.getStreet1());
            insertStmt.setString(4, address.getStreet2());
            insertStmt.setString(5, address.getState());
            insertStmt.setInt(6, address.getZipCode());
            insertStmt.setString(7, address.getCountry());
            insertStmt.executeUpdate();
            return address;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(insertStmt != null) {
                insertStmt.close();
            }
        }
    }

   

   
    public List<Address> findAddressesByZipCode(int zipCode) throws SQLException {
        List<Address> addresses = new ArrayList<>();
        String selectAddresses = "SELECT UserName,City,Street1,Street2,State,ZipCode,Country FROM Address WHERE ZipCode=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectAddresses);
            selectStmt.setInt(1, zipCode);
            results = selectStmt.executeQuery();
            while(results.next()) {
                Address address = new Address(results.getString("UserName"),
                                              results.getString("City"),
                                              results.getString("Street1"),
                                              results.getString("Street2"),
                                              results.getString("State"),
                                              results.getInt("ZipCode"),
                                              results.getString("Country"));
                addresses.add(address);
            }
            return addresses;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(selectStmt != null) {
                selectStmt.close();
            }
            if(results != null) {
                results.close();
            }
        }
    }

    public Address findAddressByName(String userName) throws SQLException {
        String selectAddress = "SELECT UserName,City,Street1,Street2,State,ZipCode,Country FROM Address WHERE UserName=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectAddress);
            selectStmt.setString(1, userName);
            results = selectStmt.executeQuery();
            if(results.next()) {
                Address address = new Address(results.getString("UserName"),
                                              results.getString("City"),
                                              results.getString("Street1"),
                                              results.getString("Street2"),
                                              results.getString("State"),
                                              results.getInt("ZipCode"),
                                              results.getString("Country"));
                return address;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(selectStmt != null) {
                selectStmt.close();
            }
            if(results != null) {
                results.close();
            }
        }
        return null;
    }
    
    
    public Address updateAddress(Address address, String newCity) throws SQLException {
        String updateAddress = "UPDATE Address SET City=? WHERE UserName=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateAddress);
            updateStmt.setString(1, newCity);
            updateStmt.setString(2, address.getUserName());
            updateStmt.executeUpdate();

            // Update the address before returning to the caller.
            address.setCity(newCity);
            return address;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(updateStmt != null) {
                updateStmt.close();
            }
        }
    }

    
    
    
    
    public Address delete(Address address) throws SQLException {
        String deleteAddress = "DELETE FROM Address WHERE UserName=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteAddress);
            deleteStmt.setString(1, address.getUserName());
            deleteStmt.executeUpdate();

            // Return null so the caller can no longer operate on the Address instance.
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(deleteStmt != null) {
                deleteStmt.close();
            }
        }
    }
    
    
}
