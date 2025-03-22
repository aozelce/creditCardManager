package entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "CreditCards")
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private int cardId; // Using int as requested

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

    // Constructors
    public CreditCard() {
    }

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

    // Getters and Setters
    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CardIssuer getCardIssuer() {
        return cardIssuer;
    }

    public void setCardIssuer(CardIssuer cardIssuer) {
        this.cardIssuer = cardIssuer;
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
}