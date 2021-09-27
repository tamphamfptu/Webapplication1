/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tampvn.cart;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Polaris
 */
public class CartObject {
    private Map<String, Integer> items;

    public Map<String, Integer> getItems() {
        return items;
    }
    
    public void addItemToCart(String id){
        //1. check id's item is existed
        if(id == null){
            return;
        }
        if(id.trim().isEmpty()){
            return;
        }
        //2. When item is existed, checking existed items 
        if(this.items == null){
            this.items = new HashMap<>();
        }
        //3.When items has existed, checking existed id
        int quantity = 1;
        if(this.items.containsKey(id)){
            quantity = this.items.get(id) + 1;
        }
        
        //4. Update items
        this.items.put(id, quantity);
        
    }
    
    public void removeItemFromCart(String id){
        //1. check existed items in Map
        if(this.items == null){
            return;
        }
        
        //2.when items has existed, check existed id
        if(this.items.containsKey(id)){
            this.items.remove(id);
            if(this.items.isEmpty()){
                this.items = null;
            }
        }
    }
}
