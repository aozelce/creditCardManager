package entity;

import java.math.BigDecimal;
import java.util.Date;

public class CreditCard {

    private int cardId;
    private int userId;
    private int issuerId;
    private String cardNumber;
    private Date expDate;
    private String ccv;
    private Date dueDate;
    private BigDecimal creditLimit;
    private BigDecimal currentBalance;

    // Constructors
    public CreditCard() {}

    public CreditCard(int userId, int issuerId, String cardNumber, Date expDate, String ccv, Date dueDate, BigDecimal creditLimit, BigDecimal currentBalance) {
        this.userId = userId;
        this.issuerId = issuerId;
        this.cardNumber = cardNumber;
        this.expDate = expDate;
        this.ccv = ccv;
        this.dueDate = dueDate;
        this.creditLimit = creditLimit;
        this.currentBalance = currentBalance;
    }

    // Getters and Setters
    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getIssuerId() {
        return issuerId;
    }

    public void setIssuerId(int issuerId) {
        this.issuerId = issuerId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public String getCcv() {
        return ccv;
    }

    public void setCcv(String ccv) {
        this.ccv = ccv;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "cardId=" + cardId +
                ", userId=" + userId +
                ", issuerId=" + issuerId +
                ", cardNumber='" + cardNumber + '\'' +
                ", expDate=" + expDate +
                ", ccv='" + ccv + '\'' +
                ", dueDate=" + dueDate +
                ", creditLimit=" + creditLimit +
                ", currentBalance=" + currentBalance +
                '}';
    }
}
