package kit.tools;

import kit.dal.*;
import kit.model.*;

import java.sql.SQLException;
import java.util.List;


public class Inserter {

	public static void main(String[] args) throws SQLException {
		// DAO instances.
		UsersDao usersDao = UsersDao.getInstance();
		CreditCardsDao creditCardsDao = CreditCardsDao.getInstance();
		CrimesDao crimesDao = CrimesDao.getInstance();
		AttractionsDao attractionsDao = AttractionsDao.getInstance();

		// READ.
		Users user = usersDao.findUserByUserName("zoe99");
		System.out.println("User: " + user.getFirstName() + " " + user.getLastName());



		List<CreditCards> cardsList = creditCardsDao.findCardsByUserName("sarah84");
		System.out.println(cardsList.size());
        for (CreditCards card : cardsList) {
            System.out.format("Reading credit card: u:%s cn:%s e:%s \n",
                card.getUserName(), card.getCardNumber(), card.getExpiration());
            }

		List<Attractions> attractions = attractionsDao.findAttractionByZipCode(98109);

		for (Attractions attraction : attractions) {
	        System.out.format("Reading attractions:" + attraction.getAttractionsName());
	    }
	}
//        List<Crimes> crimes = crimesDao.findCrimesByZipCode(98178);
//	    for (Crimes crime : crimes) {
//	        System.out.format("Reading crimes:" + crime.getAddress());
//	        }
//	    }
//
}

