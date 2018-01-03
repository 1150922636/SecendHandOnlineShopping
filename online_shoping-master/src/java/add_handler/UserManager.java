/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package add_handler;

import dao.AddDao;
import entity.UserRole;
import entity.Users;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Yuzhihang
 */
@ManagedBean
@SessionScoped
public class UserManager implements Serializable{
    private Users user = new Users();
    private UserRole userRole = new UserRole();
      public String addUser() {
        userRole.setRoleId(2);
        user.setUserRole(userRole);
        user.setUsername(user.getUsername());
        user.setPass(user.getPass());
        user.setContactNum(user.getContactNum());  
        Date d = new Date();
        user.setRegDate(d);
        boolean status = new AddDao().addUser(user);
        if (status) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_INFO, "您已注册成功，快去登录吧", 
                                     "User added to the database sucessfully"
                    )
            );
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_WARN, "对不起，您的用户名或手机号已被注册",
                            "Something wen't wrong Product database failed"
                            + " to add the product!"
                    )
            );
        }
        return null;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
