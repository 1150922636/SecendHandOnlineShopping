/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package add_handler;

import dao.AddDao;
import dao.DeleteDao;
import dao.EditDao;
import dao.ListDao;
import entity.Order;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Omer
 */
@ManagedBean
@SessionScoped
public class OrderManager implements Serializable{

    Order order;
    private List<Order> allOrders;

    public OrderManager() {
        order = new Order();
        allOrders = new ArrayList<>();
    }

    public List<Order> orderbydate(Date from, Date to) {
        allOrders = new ListDao().ProductListByDates(from, to);
        return allOrders;

    }

    public List<Order> orderbyOrderNum(int num) {
        allOrders = new ListDao().ProductListByOrderNumber(num);
        return allOrders;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void updateOrder() {
        new EditDao().updateOrder(order);
    }

    public void deleteOrder() {
        new DeleteDao().deleteOrder(order);
    }

    public double orderTotalPrice() {
        return order.getTotalPrice();
    }

}
