/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Users;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


/**
 *
 * @author YuZhihang
 */
@ManagedBean(name="login")
@SessionScoped
public class LoginDao implements Serializable{
   
    private UserDao userDAO = new UserDao();
    private Users user = new Users();

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
    
    public String login(){
        
        user = userDAO.getUser(user.getUsername(), user.getPass());

         if(user == null){
             user = new Users();
             FacesContext.getCurrentInstance().addMessage(null, 
                     new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                      "Wrong Username or password",
                                      "Login Error!"));
             return "login";
         }else if(user.getUserRole().getRoleId().equals(2)){
             return "dashboard";
         }else{
             return "administrator";
         }
    }
    
    public static boolean validate(String user,String pss){
        
        return false;
    }
}
