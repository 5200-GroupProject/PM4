package kit.model;

public class CreditCards {
	protected String userName;
    protected String cardNumber;
    protected String expiration;
	/**
	 * @param userName
	 * @param cardNumber
	 * @param expiration
	 */
	public CreditCards(String userName, String cardNumber, String expiration) {
		this.userName = userName;
		this.cardNumber = cardNumber;
		this.expiration = expiration;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the cardNumber
	 */
	public String getCardNumber() {
		return cardNumber;
	}
	/**
	 * @param cardNumber the cardNumber to set
	 */
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	/**
	 * @return the expiration
	 */
	public String getExpiration() {
		return expiration;
	}
	/**
	 * @param expiration the expiration to set
	 */
	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}
    
    

}
