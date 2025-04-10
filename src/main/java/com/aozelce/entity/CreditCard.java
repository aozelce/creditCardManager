package com.aozelce.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

/**
 * The type Credit card.
 *
 *  @author aozelce
 */
@Entity
@Table(name = "CreditCards")
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private int cardId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "issuer_id", nullable = false)
    private CardIssuer cardIssuer;

    @Column(name = "card_number", nullable = false)
    private String cardNumber;

    @Column(name = "exp_date", nullable = false)
    private Date expDate;

    @Column(name = "ccv", nullable = false)
    private String ccv;

    @Column(name = "due_date", nullable = false)
    private Date dueDate;

    @Column(name = "credit_limit", nullable = false, precision = 10, scale = 2)
    private BigDecimal creditLimit;

    @Column(name = "current_balance", nullable = false, precision = 10, scale = 2)
    private BigDecimal currentBalance;

    /**
     * Instantiates a new Credit card.
     */
    public CreditCard() {
    }

    /**
     * Instantiates a new Credit card.
     *
     * @param user           the user
     * @param cardIssuer     the card issuer
     * @param cardNumber     the card number
     * @param expDate        the exp date
     * @param ccv            the ccv
     * @param dueDate        the due date
     * @param creditLimit    the credit limit
     * @param currentBalance the current balance
     */
    public CreditCard(User user, CardIssuer cardIssuer, String cardNumber, Date expDate, String ccv, Date dueDate, BigDecimal creditLimit, BigDecimal currentBalance) {
        this.user = user;
        this.cardIssuer = cardIssuer;
        this.cardNumber = cardNumber;
        this.expDate = expDate;
        this.ccv = ccv;
        this.dueDate = dueDate;
        this.creditLimit = creditLimit;
        this.currentBalance = currentBalance;
    }

    /**
     * Gets card id.
     *
     * @return the card id
     */
    public int getCardId() {
        return cardId;
    }

    /**
     * Sets card id.
     *
     * @param cardId the card id
     */
    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets card issuer.
     *
     * @return the card issuer
     */
    public CardIssuer getCardIssuer() {
        return cardIssuer;
    }

    /**
     * Sets card issuer.
     *
     * @param cardIssuer the card issuer
     */
    public void setCardIssuer(CardIssuer cardIssuer) {
        this.cardIssuer = cardIssuer;
    }

    /**
     * Gets card number.
     *
     * @return the card number
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Sets card number.
     *
     * @param cardNumber the card number
     */
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * Gets exp date.
     *
     * @return the exp date
     */
    public Date getExpDate() {
        return expDate;
    }

    /**
     * Sets exp date.
     *
     * @param expDate the exp date
     */
    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    /**
     * Gets ccv.
     *
     * @return the ccv
     */
    public String getCcv() {
        return ccv;
    }

    /**
     * Sets ccv.
     *
     * @param ccv the ccv
     */
    public void setCcv(String ccv) {
        this.ccv = ccv;
    }

    /**
     * Gets due date.
     *
     * @return the due date
     */
    public Date getDueDate() {
        return dueDate;
    }

    /**
     * Sets due date.
     *
     * @param dueDate the due date
     */
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Gets credit limit.
     *
     * @return the credit limit
     */
    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    /**
     * Sets credit limit.
     *
     * @param creditLimit the credit limit
     */
    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    /**
     * Gets current balance.
     *
     * @return the current balance
     */
    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    /**
     * Sets current balance.
     *
     * @param currentBalance the current balance
     */
    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }

    /**
     * Returns a string representation of the CreditCard object.
     *
     */
    @Override
    public String toString() {
        return "CreditCard{" +
                "cardId=" + cardId +
                ", user=" + (user != null ? user.getUserId() : null) +
                ", cardIssuer=" + (cardIssuer != null ? cardIssuer.getIssuerId() : null) +
                ", cardNumber='" + cardNumber + '\'' +
                ", expDate=" + expDate +
                ", ccv='" + ccv + '\'' +
                ", dueDate=" + dueDate +
                ", creditLimit=" + creditLimit +
                ", currentBalance=" + currentBalance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CreditCard that = (CreditCard) o;
        return getCardId() == that.getCardId() && Objects.equals(getCardNumber(), that.getCardNumber()) && Objects.equals(getExpDate(), that.getExpDate()) && Objects.equals(getCcv(), that.getCcv());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCardId(), getCardNumber(), getExpDate(), getCcv());
    }
}