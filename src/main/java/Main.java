import com.google.gson.*;
import org.json.simple.parser.ParseException;


import java.io.*;

import java.util.*;

public class Main {

    public static ArrayList<Product> productArrayList = new ArrayList<>();

    public static String[] categories = {"fruit", "meat", "frozen goods", "dry goods"};

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        //reads in existing Json file
        readJsonFile();
        //creates new objects and add them to Arraylist
        Product product = createProduct();
        //saves the arraylist to the Json-file
        saveAsJsonFile(productArrayList);
    }

    /*
     * Creates a new object Product
     * @return create Product
     */
    public static Product createProduct() throws Exception {

        boolean exitProductCreation = false;

        while (!exitProductCreation) {

            System.out.print("Name: ");

            String productName = scanner.nextLine();

            System.out.print("Price: ");
            double price = scanner.nextDouble();
            scanner.nextLine();

            System.out.println("Category: ");
            String category = choseProductCategory();

            System.out.print("Brand: ");
            String brand = scanner.nextLine();

            Product product = new Product(productName, price, category, brand);
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
    public static String choseProductCategory() {

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
    /*
     * Saves arraylist in Json file
     * @param arrayList
     * @throws IOException
     */
    public static void saveAsJsonFile(ArrayList arrayList) throws IOException {
        Gson g = new GsonBuilder().setPrettyPrinting().create();
        Writer writer = new FileWriter("productlist", true);
        g.toJson(arrayList, writer);

        writer.flush();
        writer.close();

    }
    /*
     * Reads Json file and prints the content to console
     * @throws IOException
     * @throws ParseException
     */
    public static void readJsonFile() throws IOException, ParseException {
        try {
            FileReader reader = new FileReader("/Users/jannismuller/Documents/Jannis/It-högskolan/ownProjects/LagerHanteringsApp/productlist");
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Could not find file!");
        }
    }
}


