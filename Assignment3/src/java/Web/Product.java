package Web;

public class Product {

    private String isbn;
    private String name;
    private String imgSrc;
    private double price; // Base price without quantity
    private double cost; // Total price with quantity
    private int quantity;



    public Product(String isbn, String name, String imgSrc, double price){
        this.isbn = isbn;
        this.name = name;
        this.imgSrc = imgSrc;
        this.price = price;
        this.cost = price;
        this.quantity = 1;
    }


    public String getIsbn(){
        return this.isbn;
    }
    
    public String getName(){
        return this.name;
    }

    public String getImgSrc(){
        return this.imgSrc;
    }

    public double getPrice(){
        return this.price;
    }

    public int getQuantity(){
        return this.quantity;
    }

    public double getCost(){
        return this.cost;
    }

    public void setQuantity(int n){
        this.quantity = n;
        setCost();
    }

    public void setPrice(int n){
        this.price = n;
        setCost();
    }

    private void setCost(){
        this.cost = price * quantity;
    }
}
