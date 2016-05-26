package Web;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class Cart {
    
    private List<Product> products;
    private int size;


    public Cart(){
        this.products = new LinkedList<Product>();
        this.size = 0;
    }


    public boolean contains(String isbn){
        for(Product entry: products){
            if(entry.getIsbn().equals(isbn))
                return true;
        }
        return false;
    }


    public void remove(String isbn){
        Iterator<Product> iter = products.iterator();
        while(iter.hasNext()){
            Product product = iter.next();
            if(product.getIsbn().equals(isbn)){
                iter.remove();
                size = size - 1;
                return;
            }
        }
    }
  
  
    public void add(String isbn, String name, String imgSrc, double price){
        products.add(new Product(isbn, name, imgSrc, price));
        size = size + 1;
    }
  
  
    public List<Product> getProducts(){
        return this.products;
    }
    
    
    public int size(){
        return this.size;
    }
    
    
    public boolean isEmpty(){
        return this.size == 0;
    }
    
    
    public Product getProduct(String isbn){
        Iterator<Product> it = products.iterator();
        while(it.hasNext()){
            Product p = it.next();
            if(p.getIsbn().equals(isbn))
                return p;
        }
        return null;
    }   
}
