/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import dao.ListDao;
import entity.Category;
import entity.Product;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author YuZhihang
 */
@ManagedBean()
@SessionScoped
public class DisplayProductManager2 implements Serializable {

    private Category cat = new Category();

    private String param;

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public Category getCat() {
        return cat;
    }

    public void setCat(Category cat) {
        this.cat = cat;
    }

    public List<Category> getAllProductByCatName() {
        String catName = cat.getCatName();
        //System.out.println(param.toLowerCase() + "--------------");
        List<Category> clist = new ListDao()
                .ProductListByCatName(catName);
        return clist;
    }
  
    public String toUpperCase(String name) {
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        return name;
    }

}
