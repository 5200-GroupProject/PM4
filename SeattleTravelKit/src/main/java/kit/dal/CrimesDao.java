package kit.dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kit.model.CityCrimeStats;
import kit.model.Crimes;

public class CrimesDao {
    protected ConnectionManager connectionManager;

    private static CrimesDao instance = null;
    protected CrimesDao() {
        connectionManager = new ConnectionManager();
    }
    public static CrimesDao getInstance() {
        if(instance == null) {
            instance = new CrimesDao();
        }
        return instance;
    }

    public Crimes create(Crimes crime) throws SQLException {
        String insertCrime = "INSERT INTO Crimes(CaseNumber,CreatedDateTime,Address,ZipCode) VALUES(?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertCrime);
            insertStmt.setString(1, crime.getCaseNumber());
            insertStmt.setTimestamp(2, new Timestamp(crime.getCreatedDateTime().getTime()));
            insertStmt.setString(3, crime.getAddress());
            insertStmt.setString(4, crime.getZipCode());
            insertStmt.executeUpdate();
            return crime;
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

    public List<Crimes> findCrimesByZipCode(Integer zipCode) throws SQLException {
        List<Crimes> crimes = new ArrayList<>();
        
        String selectCrimes = "SELECT CaseNumber,CreatedDateTime,Address,ZipCode FROM Crimes WHERE ZipCode LIKE ?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectCrimes);
       
            selectStmt.setString(1, "%" + zipCode + "%");
            results = selectStmt.executeQuery();
            while(results.next()) {
                String caseNumber = results.getString("CaseNumber");
                Timestamp timestamp = results.getTimestamp("CreatedDateTime");
                Date createdDateTime = null;
                if (timestamp != null) {
                    createdDateTime = new Date(timestamp.getTime());
                }
                String address = results.getString("Address");
                String zipCodeResult = results.getString("ZipCode");
                Crimes crime = new Crimes(caseNumber, createdDateTime, address, zipCodeResult);
                crimes.add(crime);
            }
            return crimes;
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


    public Crimes findCrimeByCaseNumber(String caseNumber) throws SQLException {
        String selectCrime = "SELECT CaseNumber,CreatedDateTime,Address,ZipCode FROM Crimes WHERE CaseNumber=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectCrime);
            selectStmt.setString(1, caseNumber);
            results = selectStmt.executeQuery();
            if(results.next()) {
                Crimes crime = new Crimes(
                    results.getString("CaseNumber"),
                    new Date(results.getTimestamp("CreatedDateTime").getTime()),
                    results.getString("Address"),
                    results.getString("ZipCode")
                );
                return crime;
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

    public Crimes updateAddress(Crimes crime, String newAddress) throws SQLException {
        String updateCrime = "UPDATE Crimes SET Address=? WHERE CaseNumber=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateCrime);
            updateStmt.setString(1, newAddress);
            updateStmt.setString(2, crime.getCaseNumber());
            updateStmt.executeUpdate();
            crime.setAddress(newAddress);
            return crime;
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
    
    public List<CityCrimeStats> getTop10CitiesWithLeastCrimes() throws SQLException {
        List<CityCrimeStats> cityCrimeStats = new ArrayList<>();

        String selectStats = "SELECT ZipCode, COUNT(*) AS CrimeCount FROM Crimes GROUP BY ZipCode ORDER BY CrimeCount ASC LIMIT 10;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectStats);
            results = selectStmt.executeQuery();
            while (results.next()) {
                String zipCode = results.getString("ZipCode");
                int crimeCount = results.getInt("CrimeCount");
                CityCrimeStats stats = new CityCrimeStats(zipCode, crimeCount);
                cityCrimeStats.add(stats);
            }
            return cityCrimeStats;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (selectStmt != null) {
                selectStmt.close();
            }
            if (results != null) {
                results.close();
            }
        }
    }
    

    public Crimes delete(Crimes crime) throws SQLException {
        String deleteCrime = "DELETE FROM Crimes WHERE CaseNumber=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {            connection = connectionManager.getConnection();
        deleteStmt = connection.prepareStatement(deleteCrime);
        deleteStmt.setString(1, crime.getCaseNumber());
        deleteStmt.executeUpdate();
        // Return null so the caller can no longer operate on the Crimes instance after deletion.
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

           
