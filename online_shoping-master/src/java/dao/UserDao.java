/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Users;
import java.util.List;
import javax.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.NewHibernateUtil;

public class UserDao {


    SessionFactory factory = NewHibernateUtil.getSessionFactory();
    Session session;

    public UserDao() {
        this.session = factory.openSession();
    }
    
    public Users getUser(String userName, String password) {
        try {
            List<Users> users =  session.createQuery(
                    "SELECT u from Users u where u.username = "
                    + ":name and u.pass = :password"
            )
                    .setParameter("name", userName)
                    .setParameter("password", password)
                    .list();
            if(!users.isEmpty())
                return users.get(0);
            else
                return null;
        } catch (NoResultException e) {
            return null;
        } finally{
            session.close();
        }
    }
    public void setUser(String userName,String password,String contact_number){
        
        try {
            List<Users> users1 = session.createQuery("insert into Users(name,password,contact_number) values(':name',':password',':contact_number')")
                    .setParameter("name", userName).setParameter("password", password).setParameter("contact_number", contact_number).list();
        } catch (NoResultException e) {
         
        } finally{
            session.close();
        }
        
    }
}
