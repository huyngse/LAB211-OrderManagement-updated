/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataStructure;

import entity.Product;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author huyngo
 */
public class ProductList extends ArrayList<Product> {

    private static String fName = "src/data/products.txt/";

    public  void readFromFile() {
        File f = new File(fName);
        if (!f.exists()) {
            System.out.println("File is not existed!");
            System.exit(0);
        }
        try {
            this.clear();
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(line, ",");
                String id = stk.nextToken();
                String name = stk.nextToken();
                String unit = stk.nextToken();
                String origin = stk.nextToken();
                double price = Double.parseDouble(stk.nextToken());
                this.add(new Product(id, name, unit, origin, price));
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Failed to read file");
        }
        System.out.println("Load Products from file successfully");
    }
    
    public void display() {
        System.out.println("");
        if (this.isEmpty()) {
            System.out.println("Products list is empty");
            return;
        }
        System.out.format("%-8s%-30s%-20s%-25s%-6s", "ID", "Product Name", "Unit", "Origin", "Price");
        System.out.println("\n==========================================================================================");
        for (Product p: this) {
            System.out.println(p);
        }
    }
}
