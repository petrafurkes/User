/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.usernew.resources;

import com.google.gson.Gson;
import com.mycompany.usernew.model.CreditCard;
import com.mycompany.usernew.model.User;
import com.mycompany.usernew.services.UserService;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author petra
 */
@Path("/user")
public class UserResources {
    UserService us = new UserService();

    // After restarting or building this project...... MUST ALWAYS run the create URI
    //curl -vi -H "Accept: application/xml" -X GET -G "http://localhost:49000/api/user/create"    
    @GET
    @Path("/create")
    @Produces(MediaType.APPLICATION_XML)
    public List<User> createUsers(){
        return us.createUsers();
    }
    
    //curl -vi -H "Accept: application/xml" -X GET -G "http://localhost:49000/api/user/getAllU"    
    @GET
    @Path("/getAllU")
    @Produces(MediaType.APPLICATION_XML)
    public List<User> getAllUsersXML(){
        return us.getAllUsers();
    }

    //curl -vi -H "Accept: application/json" -X GET -G "http://localhost:49000/api/messages/getAllU"        
    @GET
    @Path("/getAllU")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsersJSON(){
        Gson gson = new Gson();
        
        List<User> user = us.getAllUsers();
        
        return Response.status(Response.Status.CREATED).entity(gson.toJson(user)).build();
    }   
    
    //curl -vi -H "Accept: application/xml" -X GET -G "http://localhost:49000/api/user/getAU/1"    
    @GET
    @Path("/getAU/{param}")
    @Produces(MediaType.APPLICATION_XML)
    public User getAUserXML(@PathParam ("param") int id){
        return us.getUser(id);
    }
    
    //curl -vi -H "Accept: application/json" -X GET -G "http://localhost:49000/api/user/getAU/1"    
    @GET
    @Path("/getAU/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAUserJSON(@PathParam("param") int id){
        Gson gson = new Gson();
        
        User user = us.getUser(id);
        
        return Response.status(Response.Status.CREATED).entity(gson.toJson(user)).build();        
    }  
    
    
    // curl -v -X POST "http://localhost:49000/api/user/" -d {"id":4,"name":"'Marina Furkes'","address":"'Street 3, Dublin 3'","age":31, "cardList":[{"type":"'Visa Debit'", "cardNumber":75436975}]}
    
    /* In Postman...Body -> raw
    {
       "id": 4,
       "name":"Marina Furkes",
       "address": "Street 3, Dublin 3",
       "age":31,
       "cardList":[
            {
                "cardId":1,
                "type":"Visa Debit",
                "cardNumber":75436975
            }
        ]  
    }
    
     {
        "id": 5,
        "name": "Kristina Furkes",
        "address": "Street 4, Dublin ",
        "age": 38,
        "created": "Jul 14, 2020 1:13:03 PM",
        "cardList": [
            {
                "cardId":1,
                "type": "Visa Debit",
                "cardNumber": 54218645
            }
        ]
    }
    */
    
    @POST
    public Response addUser(String body){
        Gson gson = new Gson();
        User newUser = gson.fromJson(body, User.class);
        us.addUser(newUser);
        return Response.status(Response.Status.CREATED).entity(gson.toJson(newUser)).build();    
    }  
    
    
    //search user Petra Furkes, id = 1
    // curl -v -X GET http://localhost:49000/api/user/searchId?id=1
    @GET
    @Path("/searchId")
    public Response searchUserId(@QueryParam("id") int id){
        Gson gson = new Gson();
        List <User> newUser = us.searchUserId(id);
        return Response.status(Response.Status.CREATED).entity(gson.toJson(newUser)).build();
    }  
    
    
    // curl -v -X GET http://localhost:49000/api/user/searchName?name=Petra Furkes
    @GET
    @Path("/searchName")
    public Response search(@QueryParam("name") String name){
        Gson gson = new Gson();
        List <User> newUser = us.searchUser(name);
        return Response.status(Response.Status.CREATED).entity(gson.toJson(newUser)).build();
    }  
    
    
    // DELETE a resource, user by id
    // curl -v -X DELETE http://localhost:49000/api/user/id/delete/3
    @DELETE
    @Path("delete/id/{id}")
    //@Produces(MediaType.APPLICATION_JSON)
    public String deleteUserIDJSON(@PathParam ("id") int id){
        String result = us.deleteUserID(id);
        return result;
    }
    
    
    // DELETE a resource, user by name
    // curl -v -X DELETE http://localhost:49000/api/user/delete/Marina Furkes
    @DELETE
    @Path("delete/{name}")
    //@Produces(MediaType.APPLICATION_JSON)
    public String deleteUserNameJSON(@PathParam ("name") String name){
        String result = us.deleteUserName(name);
        return result;
    }
    
    
    // UPDATE A resource
    // In Postman... http://localhost:49000/api/user/1/updateName/Ms Petra Furkes
    @PUT
    @Path("/{id}/updateName/{name}")
    @Produces(MediaType.APPLICATION_XML)  
    public Response updateUser(@PathParam("id") int id, @PathParam("name") String newName){
        Gson gson = new Gson();
        us.updateUser(id, newName);
        
        return Response.status(Response.Status.CREATED).entity(gson.toJson(us.getUser(id))).build();
        
    }   
    
    // FETCH ALL sub-resources    
    // curl -v -X GET http://localhost:49000/api/user/1/getAllCards/
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{param1}/getAllCards/")
    public Response getCardJSON(@PathParam("param1") int id){
        Gson gson = new Gson();
        
        User user = us.getUser(id);
        List cards = user.getCardList();
        
        return Response.status(Response.Status.CREATED).entity(gson.toJson(cards)).build();
    }

    // FETCH a specific sub-resource    
    // curl -v -X GET http://localhost:49000/api/user/1/getAllCards/2
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{param1}/getAllCards/{param2}")
    public Response getCardJSON(@PathParam("param1") int id, @PathParam("param2") int cardId){
        Gson gson = new Gson();
        
        return Response.status(Response.Status.CREATED).entity(gson.toJson(us.displayCardId(id, cardId))).build();
    }    
    
}
