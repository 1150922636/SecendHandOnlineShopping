/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Category;
import entity.Order;
import entity.Product;
import entity.SubCategory;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.NewHibernateUtil;

/**
 *
 * @author YuZhihang
 */
public class ListDao {

    public List catList() {
        try {
            SessionFactory factory = NewHibernateUtil.getSessionFactory();
            Session session = factory.openSession();
            List<Category> cList
                    = session.createQuery("SELECT a1.catName FROM Category a1 ")
                    .list();
            cList.toString();
            session.close();
            return cList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public List<Category> catListAll() {
        SessionFactory factory = NewHibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        List<Category> cList
                = session.createQuery("SELECT a1 FROM Category a1")
                .list();
        cList.toString();
        session.close();
        return cList;

    }

    public List<Category> catListByName(String name) {

        try {
            SessionFactory factory = NewHibernateUtil.getSessionFactory();
            Session session = factory.openSession();
            List<Category> cList
                    = session.createQuery("SELECT a1 FROM Category a1"
                            + " WHERE lower(catName) = '" + name.toLowerCase() + "'")
                    .list();
            cList.toString();
            session.close();
            return cList;
        } catch (Exception e) {

        }
        return null;

    }

    public List<SubCategory> SubcatListByName(String name) {

        try {
            SessionFactory factory = NewHibernateUtil.getSessionFactory();
            Session session = factory.openSession();
            List<SubCategory> cList
                    = session.createQuery(
                            "SELECT a1 FROM SubCategory a1"
                            + " WHERE lower(a1.category.catName) = '" + name.toLowerCase() + "'")
                    .list();
            cList.toString();
            session.close();
            return cList;
        } catch (Exception e) {

        }
        return null;

    }

    public List SubcatList(String name) {

        try {
            SessionFactory factory = NewHibernateUtil.getSessionFactory();
            Session session = factory.openSession();
            List<SubCategory> cList
                    = session.createQuery(
                            "SELECT DISTINCT(a1.subCatName) FROM SubCategory a1"
                            + " WHERE a1.category.catId"
                            + " IN (SELECT a.catId FROM Category a "
                            + "where lower(a.catName) = '"
                            + name.toLowerCase() + "')")
                    .list();
            cList.toString();
            session.close();
            return cList;
        } catch (Exception e) {

        }
        return null;

    }

    public List allProductList() {

        try {
            SessionFactory factory = NewHibernateUtil.getSessionFactory();
            Session session = factory.openSession();
            List<Product> cList
                    = session.createQuery("SELECT a1 FROM Product a1")
                    .list();
            cList.toString();
            session.close();
            return cList;
        } catch (Exception e) {

        }
        return null;

    }

    
      public List ProductListByProductName(String productName) {

        try {
            SessionFactory factory = NewHibernateUtil.getSessionFactory();
            Session session = factory.openSession();
            List<Product> cList
                    = session.createQuery("SELECT a1 FROM Product a1"+" where prod_name = '"+ productName+"'")
                    .list();
            cList.toString();
            session.close();
            return cList;
        } catch (Exception e) {

        }
        return null;

    }
    
    public List ProductListByCatName(String catname) {
        try {
            SessionFactory factory = NewHibernateUtil.getSessionFactory();
            Session session = factory.openSession();
            /*List<Product> cList
                    = session.createQuery("SELECT product FROM SubCategory "
                            + "sub_category, Category category,Product product"
                            + "WHERE sub_category.category.catId = category.catId "
                            + "AND product.subCategory.subCatId = sub_category.subCatId"
                            + "AND (category.catName= '" + catname + "')")
                    .list();*/
            //System.out.println("Cat Name: " + catname);
            List<Product> cList
                    = session.createQuery(
                              "SELECT product FROM "
                            + "SubCategory sub,"
                            + "Category category,"
                            + "Product product"
                            + " WHERE "
                            + " product.subCategory.subCatId = sub.subCatId"
                            + " AND "
                            + " sub.category.catId = category.catId"
                            + " AND "
                            + " lower(category.catName) = '"+catname+"' "
                            //+ " ( lower(category.catName) = '" + catname + "' );"
                    )
                    .list();            
//            System.out.println(cList.toString());
            cList.toString();
            session.close();
            return cList;
        } catch (Exception e) {

        }
        return null;

    }

    public List ProductListByOrderNumber(int ordernum) {
        try {
            SessionFactory factory = NewHibernateUtil.getSessionFactory();
            Session session = factory.openSession();
            List<Order> oList
                    = session.createQuery("SELECT deliveryCost, orderDetail, users, orderDate, orderQty, totalPrice, orderStatus FROM Order "
                            + "WHERE (Order.orderId= '" + ordernum + "')")
                    .list();
            oList.toString();
            session.close();
            return oList;
        } catch (Exception e) {

        }
        return null;

    }

    public List allOrdersList() {

        try {
            SessionFactory factory = NewHibernateUtil.getSessionFactory();
            Session session = factory.openSession();
            List<Product> cList
                    = session.createQuery("SELECT * FROM Order")
                    .list();
            cList.toString();
            session.close();
            return cList;
        } catch (Exception e) {

        }
        return null;

    }

    public List ProductListByDates(Date from, Date to) {
        try {
            SessionFactory factory = NewHibernateUtil.getSessionFactory();
            Session session = factory.openSession();
            List<Order> oList
                    = session.createQuery("SELECT deliveryCost, orderDetail, users, orderDate, orderQty, totalPrice, orderStatus FROM Order "
                            + "WHERE (Order.orderDate >= " + from + "')"
                            + "And (order.orderDate <= " + to + ")")
                    .list();
            oList.toString();
            session.close();
            return oList;
        } catch (Exception e) {

        }
        return null;

    }
    
}
