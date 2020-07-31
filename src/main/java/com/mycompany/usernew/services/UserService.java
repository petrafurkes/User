/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.usernew.services;

import com.mycompany.usernew.model.CreditCard;
import com.mycompany.usernew.model.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author petra
 */
public class UserService {
    public static List<User> list = new ArrayList<>();
    
    
    public List<User> createUsers(){
        //create an instance of users and pass actual arguments in it
        User u1 = new User(1, "Petra Furkes", "Street 25, Dublin 1", 36);
        User u2 = new User(2, "Mary Poppins", "Street 35, Dublin 2", 40);
        User u3 = new User(3, "Lena Lenic", "Street 05, Dublin 5", 37);
        
        //build up the actual list
        list.add(u1);
        list.add(u2);
        list.add(u3);
        
        //CreditCard data for first user
        List<CreditCard> c1List = new ArrayList<>();
        
        CreditCard c11 = new CreditCard(1, "Visa Debit", 12547854);
        CreditCard c12 = new CreditCard(2, "Visa Credit", 78942547);
        CreditCard c13 = new CreditCard(3, "Master", 65478921);
        
        c1List.add(c11);
        c1List.add(c12);
        c1List.add(c13);
        
        //to set data into first user's card list
        u1.setCardList(c1List);
        
        //CreditCard data for second user
        List<CreditCard> c2List = new ArrayList<>();
        
        CreditCard c21 = new CreditCard(1, "Visa Debit", 54627548);
        CreditCard c22 = new CreditCard(2, "Visa Credit", 74621548);
        CreditCard c23 = new CreditCard(3, "Master", 65478921);
        
        c2List.add(c21);
        c2List.add(c22);
        c2List.add(c23);
       
        u2.setCardList(c2List);
        
        //CreditCard data for second user
        List<CreditCard> c3List = new ArrayList<>();
        
        CreditCard c31 = new CreditCard(1, "Visa Debit", 462175647);
        CreditCard c32 = new CreditCard(2, "Visa Credit", 56348514);
        CreditCard c33 = new CreditCard(3, "Master", 34520124);
        
        c3List.add(c31);
        c3List.add(c32);
        c3List.add(c33);
       
        u3.setCardList(c3List);
        
        
        return list;
        
    }
    
    //to get all users
    public List<User> getAllUsers() {
        return list;
    }
    //to get specific user by his ID
    public User getUser(int id){
        for(int i = 0; i < list.size(); i++){
            if(id==list.get(i).getId()){
                return list.get(i);
            }
        }
        return null;
    }
    
    
    
    
    
    //to add new user and create it with current date when created
    public void addUser(User newUser){
        newUser.setCreated(new Date());
        list.add(newUser);
    }
  
    //search user by his id
    public List searchUserId(int id){
        List<User> match = new ArrayList<>();
        for (User user : list){
            if(user.getId()==id){
                match.add(user);
            }
        }
        return match;
    }
   
    
    //search user by his name
    public List searchUser(String name){
       List<User> match = new ArrayList<>();
        for (User user : list){
            if(user.getName().equals(name)){
                match.add(user);
            }
        }
        return match;
    }
    
    public CreditCard displayCardId(int id, int cardId){
        
        for (int i = 0; i < list.size(); i++){
            if (id==list.get(i).getId()){
                User user = list.get(i);
                List<CreditCard> cardList = user.getCardList();
                for(int j = 0; j < cardList.size(); j++){
                    CreditCard card = cardList.get(j);
                    if(cardId == card.getCardId()){
                        return card;
                    }
                }
                
            }
        }
        return null;
    }
    
    
    public List updateUser(int id, String newName){
        List<User> newUser = new ArrayList<>();
        for (int i = 0; i < list.size(); i++){
            if (id==list.get(i).getId()){
                list.get(i).setName(newName);
                newUser.add(list.get(i));
                return newUser;
            }
        }
        return null;
    }
    
    public String deleteUserID(int id){
  
        //List<User> match = new ArrayList<>();
        for (int i = 0; i < list.size(); i++){
            if(id==list.get(i).getId()){
                list.remove(list.get(i));
                return "The user was successfully deleted: " + id;
            }
        }
        return "The user does not exist, message: " + id;
    }
    
    
    public String deleteUserName(String name){
  
        //List<User> match = new ArrayList<>();
        for (int i = 0; i < list.size(); i++){
            if(name.equals(list.get(i).getName())){
                list.remove(list.get(i));
                return "The user was successfully deleted: " + name;
            }
        }
        return "The user does not exist, message: " + name;
    }
        
}

