package jannis.Classes;

import com.google.gson.annotations.SerializedName;

import java.util.Iterator;
import java.util.Random;

public class Product {

   private String productName;
   private double price;
   private String category;
   private String brand;
   private String productEan13Kod;

   Random random = new Random();

    public Product(String productName, double price, String category, String brand) {
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.brand = brand;
        this.productEan13Kod = createProductCode();
    }

    public Product() {
    }

    /*
     *Create EAN-13 product ID
     * @return  unique EAN 13 code
     */
    private String createProductCode(){
        int len = 13;
        String ean13 = "";

        for(int i = 0; i < len; i++){
            ean13 += ((Integer) random.nextInt(10)).toString();
        }
        return ean13;
    }

    @Override
    public String toString() {
        return "jannis.Classes.Product{" +
                "productName='" + productName + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", brand='" + brand + '\'' +
                ", productEan13Kod='" + productEan13Kod + '\'' +
                ", random=" + random +
                '}';
    }





    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getProductEan13Kod() {
        return productEan13Kod;
    }

    public void setProductEan13Kod(String productEan13Kod) {
        this.productEan13Kod = productEan13Kod;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public Iterator getAllHeaders() {
        return null;
    }
}
