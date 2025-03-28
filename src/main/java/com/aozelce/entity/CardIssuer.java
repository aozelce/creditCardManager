package com.aozelce.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Card issuer.
 *
 *  @author aozelce
 */
@Entity
@Table(name = "CardIssuers")
public class CardIssuer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "issuer_id")
    private int issuerId;

    @Column(name = "issuer_name", nullable = false)
    private String issuerName;

    // Arraylist of CreditCards
    @OneToMany(mappedBy = "cardIssuer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CreditCard> creditCards = new ArrayList<>();

    /**
     * Instantiates a new Card issuer.
     */
    public CardIssuer() {}

    /**
     * Instantiates a new Card issuer.
     *
     * @param issuerName the issuer name
     */
    public CardIssuer(String issuerName) {
        this.issuerName = issuerName;
    }

    /**
     * Gets issuer id.
     *
     * @return the issuer id
     */
    public int getIssuerId() {
        return issuerId;
    }

    /**
     * Sets issuer id.
     *
     * @param issuerId the issuer id
     */
    public void setIssuerId(int issuerId) {
        this.issuerId = issuerId;
    }

    /**
     * Gets issuer name.
     *
     * @return the issuer name
     */
    public String getIssuerName() {
        return issuerName;
    }

    /**
     * Sets issuer name.
     *
     * @param issuerName the issuer name
     */
    public void setIssuerName(String issuerName) {
        this.issuerName = issuerName;
    }

    /**
     * Returns a string representation of the CardIssuer object.
     *
     */
    @Override
    public String toString() {
        return "CardIssuer{" +
                "issuerId=" + issuerId +
                ", issuerName='" + issuerName + '\'' +
                '}';
    }

}


