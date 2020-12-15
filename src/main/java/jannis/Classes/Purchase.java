package jannis.Classes;


import jannis.Controller.Controller;

import java.util.ArrayList;

import static jannis.Controller.Controller.productArrayList;

public class Purchase {

    private String orderNumber;
    private double totalSum;

    public static ArrayList<Product> purchaseList = new ArrayList<Product>();

    public Purchase() {
        this.orderNumber = "234";
        this.totalSum = 12334;
    }
    public static Product getProductForPurchase (String input){
        return Controller.searchItem(productArrayList,input);
    }

   public static void addProductToPurchaseList(Product product){
        purchaseList.add(product);
        System.out.println(product.getProductName() + " was added to cart\n");

   }

}


