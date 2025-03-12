package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "CardIssuers")
public class CardIssuer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "issuer_id")
    private int issuerId;

    @Column(name = "issuer_name", nullable = false)
    private String issuerName;

    // Constructors
    public CardIssuer() {}

    public CardIssuer(String issuerName) {
        this.issuerName = issuerName;
    }

    // Getters and Setters
    public int getIssuerId() {
        return issuerId;
    }

    public void setIssuerId(int issuerId) {
        this.issuerId = issuerId;
    }

    public String getIssuerName() {
        return issuerName;
    }

    public void setIssuerName(String issuerName) {
        this.issuerName = issuerName;
    }

    @Override
    public String toString() {
        return "CardIssuer{" +
                "issuerId=" + issuerId +
                ", issuerName='" + issuerName + '\'' +
                '}';
    }

}


