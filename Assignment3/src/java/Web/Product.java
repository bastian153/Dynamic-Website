package Web;

public class Product {

    private String isbn;
    private String name;
    private String imgSrc;
    private double price; // Base price without quantity



    public Product(String isbn, String name, String imgSrc, double price){
        this.isbn = isbn;
        this.name = name;
        this.imgSrc = imgSrc;
        this.price = price;
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

    public void setPrice(int n){
        this.price = n;
    }
}
