package jannis.Controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import jannis.Classes.Product;

public class Controller {

    public static ArrayList<Product> productArrayList = Controller.loadArrayListFromJson();

    public static String[] categories = {"fruit", "meat", "frozen goods", "dry goods"};

    /*
     * Saves arraylist in Json file
     * @param arrayList
     * @throws IOException
     */
    public static void saveAsJsonFile(ArrayList<Product> saveProductArrayList) throws IOException {
        Gson g = new GsonBuilder().setPrettyPrinting().create();
        Writer writer = new FileWriter("productlist.json");
        g.toJson(saveProductArrayList, writer);

        writer.flush();
        writer.close();
    }

    public static ArrayList<Product> loadArrayListFromJson() {
        Gson g = new GsonBuilder().setPrettyPrinting().create();

        System.out.println("Trying to load ArrayList from .json file...");

        // Defines what type of data we're loading
        Type productArrayType = new TypeToken<ArrayList<Product>>() {
        }.getType();

        try (FileReader fileReader = new FileReader("productlist.json")) {
            // Loads our ArrayList from Gson, we use Type parameter to specify what data we're loading
            ArrayList<Product> loadedArrayList = g.fromJson(fileReader, productArrayType);
            // If the .json-file is empty we want the method to return an empty Arraylist
            if (null == loadedArrayList) {
                System.out.println(".json-file was empty, returning an empty ArrayList.");
                loadedArrayList = new ArrayList<>();
            }
            System.out.println("Returning loaded ArrayList...");
            //printArrayList(loadedArrayList);
            return loadedArrayList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<Product>();
    }

    private static void printArrayList(ArrayList<Product> printArrayList) {
        if (null != printArrayList) {
            for (Product product : printArrayList) {
                System.out.println("jannis.Classes.Product: " + product.getProductName());
                System.out.println("Price: " + product.getPrice() + " SEK");
                System.out.println("Category: " + product.getCategory());
                System.out.println("Brand: " + product.getBrand());
            }
        } else {
            System.out.println("ArrayList is empty.");
        }

    }

    public static Product searchItem(ArrayList<Product> arrayList, String input) {

        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getProductName().equalsIgnoreCase(input)) {
                return arrayList.get(i);
            }
        }
        System.out.println("Product not found!");
        return null;
    }
}
