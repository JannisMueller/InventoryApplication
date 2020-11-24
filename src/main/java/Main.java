import jannis.Classes.Product;
import jannis.Controller.Controller;
import java.util.*;

import static jannis.Controller.Controller.categories;
import static jannis.Controller.Controller.productArrayList;

public class Main {

   private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        //reads in existing Json file
        Controller.loadArrayListFromJson();

        //search for item and returns object
        System.out.println("Which article do you want to add to your cart?");
        String input = scanner.nextLine();
        System.out.println(Controller.searchItem(productArrayList,input).getBrand());

        //creates new objects and add them to Arraylist
        createProduct();

        //saves the arraylist to the Json-file
        Controller.saveAsJsonFile(productArrayList);
    }

    /*
     * Creates a new object jannis.Classes.Product
     * @return create jannis.Classes.Product
     */
    private static Product createProduct(){

        boolean exitProductCreation = false;

        while (!exitProductCreation) {

            Product product = new Product();

            System.out.print("Name: ");

            product.setProductName(scanner.nextLine());

            System.out.print("Price: ");
            product.setPrice(scanner.nextDouble());
            scanner.nextLine();

            System.out.println("Category: ");
            product.setCategory(choseProductCategory());

            System.out.print("Brand: ");
            product.setBrand(scanner.nextLine());

            productArrayList.add(product);

            System.out.println("Do you want to add another item? Press x when you are finished!");
            String exitProgram = scanner.nextLine();
            if (exitProgram.equalsIgnoreCase("x")) {
                break;
            } else
                continue;

        }
        return null;
    }

    /*
     * lets the user choose the category of the product
     * @return chosen category
     */
    private static String choseProductCategory() {

        for (int i = 0; i < categories.length; i++) {
            System.out.println("Press " + i + " for " + categories[i]);
        }
        String ChooseCategory = scanner.nextLine();

        switch (ChooseCategory) {
            case "0":
                return categories[0];

            case "1":
                return categories[1];

            case "2":
                return categories[2];

            case "3":
                return categories[3];

        }
        return null;
    }

}

