package jannis.Classes;

import java.util.Random;

public class Product {

    private String productName;
    private double price;
    private String category;
    private String brand;
    private final String productEan13Kod;

    Random random = new Random();

    //constructor
    public Product() {
        this.productEan13Kod = createProductCode();
    }

    /*
     *Create EAN-13 product ID
     * @return  unique EAN 13 code
     */
    private String createProductCode() {
        int len = 13;
        StringBuilder ean13 = new StringBuilder();

        for (int i = 0; i < len; i++) {
            ean13.append(((Integer) random.nextInt(10)).toString());
        }
        return ean13.toString();
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
}
