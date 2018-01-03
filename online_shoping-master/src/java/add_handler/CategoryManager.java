/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package add_handler;

import dao.AddDao;
import entity.Category;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import dao.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author YuZhihang
 */
@ManagedBean
@SessionScoped
public class CategoryManager implements Serializable{
    Category category = new Category();
    private List<Category> allCat = new ArrayList<>();

    public List<Category> getAllCat() {
        allCat = new dao.ListDao().catList();
        return allCat;
    }

    public void setAllCat(List<Category> allCat) {
        this.allCat = allCat;
    }
    
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    
    public String addCategory(){
        category.setCatName(category.getCatName());
        category.setCatDesc(category.getCatDesc());
        boolean status = new AddDao().addCatagory(category);
        if(status){
            FacesContext.getCurrentInstance().addMessage(null, new 
            FacesMessage(FacesMessage.SEVERITY_INFO, "", "Data Saved"));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new 
            FacesMessage(FacesMessage.SEVERITY_WARN, "Data wasn't saved", ""));
        }
        return null;
    }
    
}
