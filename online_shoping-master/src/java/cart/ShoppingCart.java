
package cart;

import entity.Product;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author yuzhihang 
 */
@ManagedBean
@SessionScoped
public class ShoppingCart implements Serializable{

    private List<Item> cart = new ArrayList<>();
    private double total;
    int cartsize;
    String item = "item";
    private static DecimalFormat df2 = new DecimalFormat(".##");

    
    public double getTotal() {
        total = 0.0;
        for(Item item : cart){
            total = total + (item.getQuantity() * item.getP().getProdPrice());
        }
        //df2.setRoundingMode(RoundingMode.UP);
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getCartsize() {
        cartsize = cart.size();
        return cartsize;
    }

    public void setCartsize(int cartsize) {
        this.cartsize = cartsize;
    }

    public String getItem() {
        if(cartsize > 1)
            item = "items";
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
    
    public List<Item> getCart() {
        return cart;
    }

    public void setCart(List<Item> cart) {
        this.cart = cart;
    }

    public String addToCart(Product p){
        System.out.println("Add to Cart..................");
        if(cart.size() > 0){
            for (Item item : cart){
                if(item.getP().getProdId().equals(p.getProdId())){
                    item.setQuantity(item.getQuantity() + 1);
                    return "cart";
                    
                }
            }
        }
        Item i = new Item();
        i.setQuantity(1);
        i.setP(p);
        cart.add(i);
        System.out.println(i.getP().getProdName() + " 1 item added..................");
        return "cart";
    }
    
    public void removeFromCart(Item i){
        for(Item item: cart){
            if(item.equals(i)){
                cart.remove(i);
                break;
            }
        }
    }
    
    public void updateCart(){
           
    }
    
    public String payment(){
        return "payment";
    }
}
