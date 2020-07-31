/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.usernew.model;

/**
 *
 * @author petra
 */
public class CreditCard {
    private int cardId;
    private String type;
    private long cardNumber;
   
    public CreditCard(){

    }

    public CreditCard(int cardId, String type, long cardNumber) {
        this.cardId = cardId;
        this.type = type;
        this.cardNumber = cardNumber;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }
    
    
}
