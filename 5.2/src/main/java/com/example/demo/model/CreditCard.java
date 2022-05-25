package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "creditcard")
@Data
@Getter
@Setter
public class CreditCard {
    @Id
    @Column(name = "CreditCardID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "CardType")
    private String cardType;

    @Column(name = "CardNumber")
    private Long cardNumber;

    @Column(name = "ExpMonth")
    private Integer expMonth;

    @Column(name = "ExpYear")
    private Integer expYear;
    
    @Column(name = "ModifiedDate")
    private LocalDateTime modifiedDate;

    public CreditCard(String cardType, Long cardNumber, Integer expMonth, Integer expYear, LocalDateTime modifieDate) {
        this.cardType = cardType;
        this.cardNumber = cardNumber;
        this.expMonth = expMonth;
        this.expYear = expYear;
        this.modifiedDate = modifiedDate;
    }

}
