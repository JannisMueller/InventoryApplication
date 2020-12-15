import jannis.Classes.Product;
import jannis.Controller.Controller;
import java.io.IOException;
import java.util.*;
import static jannis.Classes.Purchase.*;
import static jannis.Controller.Controller.*;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        //reads in existing Json file with all items on stock
        Controller.loadArrayListFromJson();
        //load menu
        menu();
    }

    /*
     * Creates a new object jannis.Classes.Product
     * @return create jannis.Classes.Product
     */
    private static void createProduct(){

        boolean exitProductCreation = true;

        while (exitProductCreation) {

            Product product = new Product();

            System.out.print("Name: ");

            product.setProductName(scanner.nextLine());

            System.out.print("Units in Stock at registration: ");
            product.setStock(scanner.nextInt());
            scanner.nextLine();

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
            }
        }
    }
    /*
     * lets the user choose the category of the product
     * @return chosen category
     */
    private static String choseProductCategory() {

        for (int i = 0; i < categories.length; i++) {
            System.out.println("Press " + i + " for " + categories[i]);
        }
        String chooseCategory = scanner.nextLine();

        switch (chooseCategory) {
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
    private  static void menu() throws IOException {

        boolean exitProgram = true;

        while (exitProgram) {

            System.out.println("-----------------------------------------");
            System.out.println(
                            "Press 1 for adding a product to the warehouse\n" +
                            "Press 2 Creating an order\n" +
                            "Press 3 for searching a stock item\n" +
                            "Press x for quiting the program");
            System.out.println("-----------------------------------------");
            String choiceMenu = scanner.nextLine();


            switch (choiceMenu) {

                case "1":
                    //creates new objects and add them to Arraylist
                    createProduct();
                    //saves the arraylist to the Json-file
                    Controller.saveAsJsonFile(productArrayList);
                    continue;

                case "2":
                    boolean exitOrder = true;
                    do {

                        System.out.print("Article do add to new order: \n");
                        String inputOrder = scanner.nextLine();
                        System.out.print("How many units? \n");
                        int unitsToTakeFromStock = scanner.nextInt();
                        scanner.nextLine();
                        Product productToBePurchases = printOutSearchedProduct(inputOrder);
                        productToBePurchases.setStock(changesInStock(productToBePurchases, unitsToTakeFromStock));
                        addProductToPurchaseList(productToBePurchases);
                        System.out.println("<<<<Order>>>>");
                        Controller.printArrayList(purchaseList);
                        System.out.println("Total sum of Order: " + getTotalSumOrder());

                        System.out.println("Press x for finishing up the order or any other button to adding more articles");
                        String exit = scanner.nextLine();
                        if (exit.equalsIgnoreCase("x")) {
                            exitOrder = false;
                        }

                    } while (exitOrder);

                    System.out.println("Order saved to system and will processed by the warehouse soon");
                    break;

                case "3":
                    //search for item and returns object
                    System.out.print("Name of article to search for: \n");
                    String inputSearch = scanner.nextLine();
                    printOutSearchedProduct(inputSearch);
                    continue;

                case "x":
                    System.out.println("Program is shutting down....");
                    exitProgram = false;

            }
        }
    }
    private static Product printOutSearchedProduct(String input){
            //searching for the product to purchased
            Product searchedProduct = Controller.searchItem(productArrayList,input);

            assert searchedProduct != null;
            System.out.print(
                    "Name: " + searchedProduct.getProductName() + "\n" +
                            "Price: " + searchedProduct.getPrice() + "\n" +
                            "Units in stock: " + searchedProduct.getStock() + "\n" +
                            "Category: " + searchedProduct.getCategory() + "\n" +
                            "Brand: " +  searchedProduct.getBrand() + "\n" +
                            "EAN-13 code: " + searchedProduct.getProductEan13Kod() + "\n" +
                            "-----------------------------------------------------" + "\n");

            return searchedProduct;
        }
    private static double getTotalSumOrder(){
            double sum = 0;
            for(int i = 0; i < purchaseList.size();i++){
                sum = sum + purchaseList.get(i).getPrice();
            }
            return sum;
            }

}

