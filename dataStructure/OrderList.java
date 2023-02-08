/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataStructure;

import entity.Customer;
import entity.Order;
import entity.Product;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import tools.MyUtil;

/**
 *
 * @author huyngo
 */
public class OrderList extends ArrayList<Order> {

    private final static String fName = "src/data/orders.txt/";
    private final CustomerList customerList;
    private final ProductList productList;

    public OrderList(CustomerList customerList, ProductList productList) {
        this.customerList = customerList;
        this.productList = productList;
    }

    public void readFromFile() {
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
                String customerId = stk.nextToken();

                String productLine = stk.nextToken();
                StringTokenizer pstk = new StringTokenizer(productLine, "-");
                ArrayList<String> productIDs = new ArrayList<>();
                while (pstk.hasMoreTokens()) {
                    productIDs.add(pstk.nextToken());
                }

                String quantityLine = stk.nextToken();
                StringTokenizer qstk = new StringTokenizer(quantityLine, "-");
                ArrayList<Integer> quantities = new ArrayList<>();
                while (qstk.hasMoreTokens()) {
                    quantities.add(Integer.parseInt(qstk.nextToken()));
                }

                String date = stk.nextToken();
                boolean status = Boolean.parseBoolean(stk.nextToken());
                this.add(new Order(id, customerId, productIDs, quantities, date, status));
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Failed to read file");
        }
        System.out.println("Load Orders from file succesffully");
    }//End readFromFile()

    //FUNCTION #7
    public void display() {
        System.out.println("");
        if (this.isEmpty()) {
            System.out.println("Order list is empty");
            return;
        }
        Collections.sort(this);
        attribute();
        for (Order o : this) {
            System.out.println(o);
        }
    }//End display()

    //FUNCTION #8
    public void displayPendingOrder() {
        System.out.println("");
        if (this.isEmpty()) {
            System.out.println("Order list is empty");
            return;
        }
        System.out.println("#Print all pending Orders");
        ArrayList<Order> resultList = new ArrayList<>();
        for (Order o : this) {
            if (o.isStatus()) {
                resultList.add(o);
            }
        }
        if (resultList.isEmpty()) {
            System.out.println("There is no pending Order");
            return;
        }
        attribute();
        for (Order o : resultList) {
            System.out.println(o);
        }
    } //End displayPendingOrder()

    //FUNCTION #9
    public void addOrder() {
        System.out.println("#Add an order");
        int pos;
        String id;
        do {
            id = MyUtil.getString("Enter ID\n>> ", "");
            pos = this.indexOf(new Order(id));
            if (pos >= 0) {
                System.out.println("ID is not allowed to be duplicated");
            }
        } while (pos >= 0);

        String customerId = selectCustomer("");

        ArrayList<String> productIDs = new ArrayList<>();
        ArrayList<Integer> quantities = new ArrayList<>();
        String choose = "";
        do {
            String productID = selectProduct("");
            int quantity = MyUtil.getInt("\tQuantity of " + productID + "\n>> ", 0, 9999, "");
            productIDs.add(productID);
            quantities.add(quantity);
            choose = MyUtil.getString("Add another product? Y/N\n>> ", "");
        } while (choose.equalsIgnoreCase("Y"));

        String date = MyUtil.getString("Enter order date (dd/mm/yyyy)\n>> ", "[0-9]{2}/[0-9]{2}/[0-9]{4}", "");
        String sStatus = MyUtil.getString("Enter order status (true/false)\n>> ", "");
        boolean status = sStatus.equalsIgnoreCase("true") || sStatus.equalsIgnoreCase("t");
        this.add(new Order(id, customerId, productIDs, quantities, date, status));
        System.out.println("New order added successfully");
    }//End addOrder()

    //FUNCTION #10.1
    public void updateOrder() {
        System.out.println("");
        if (this.isEmpty()) {
            System.out.println("Order list is empty");
            return;
        }
        System.out.println("#Update order information");
        String id = MyUtil.getString("Enter ID\n>> ", "");
        int pos = this.indexOf(new Order(id));
        if (pos < 0) {
            System.out.println("Order does not exist");
            System.out.println("Failed to update order");
            return;
        }
        Order order = this.get(pos);
        String customerId = selectCustomer("update");
        if (customerId.equals("_not_change")) {
            customerId = order.getCustomerID();
            System.out.println(">> " + customerId);
        }

        ArrayList<String> productIDs = order.getProductIDs();
        ArrayList<Integer> quantities = order.getOrderQuantities();
        if (MyUtil.getString("Change product information?  Y/N\n>> ", "").equalsIgnoreCase("y")) {
            productIDs = new ArrayList<>();
            quantities = new ArrayList<>();
            String choose = "";
            do {
                String productID = selectProduct("");
                int quantity = MyUtil.getInt("\tQuantity of " + productID + "\n>> ", 0, 9999, "");
                productIDs.add(productID);
                quantities.add(quantity);
                choose = MyUtil.getString("Add another product? Y/N", "");
            } while (choose.equalsIgnoreCase("Y"));
        }
        String date = MyUtil.getString("Enter order date (dd/mm/yyyy)\n>> ", "[0-9]{2}/[0-9]{2}/[0-9]{4}", "update");
        if (date.equals("_not_change")) {
            date = order.getOrderDate();
            System.out.println(">> " + date);
        }
        String sStatus = MyUtil.getString("Enter order status (true/false)\n>> ", "update");
        boolean status = sStatus.equalsIgnoreCase("true") || sStatus.equalsIgnoreCase("t");
        if (sStatus.equals("_not_change")) {
            status = order.isStatus();
            System.out.println(">> " + status);
        }

        order.setCustomerID(customerId);
        order.setProductIDs(productIDs);
        order.setOrderQuantities(quantities);
        order.setOrderDate(date);
        order.setStatus(status);
        System.out.println("Order updated successfully");
    }//End updateOrder()

    //FUNCTION #10.2
    public void removeOrder() {
        boolean continueMethod = true;
        while (continueMethod) {
            System.out.println("");
            if (this.isEmpty()) {
                System.out.println("Order list is empty");
                return;
            }
            System.out.println("#Delete order ");

            String id = MyUtil.getString("Enter ID\n>> ", "");
            int pos = this.indexOf(new Order(id));
            if (pos < 0) {
                System.out.println("Order does not exist");
                return;
            }
            Order order = this.get(pos);
            attribute();
            System.out.println(order);
            System.out.println("Are you sure you want to delete this order? Y/N");
            boolean confirm = MyUtil.getString(">> ", "").equalsIgnoreCase("Y");
            if (confirm) {
                this.remove(pos);
                System.out.println("Order removed successfully");
            }
            System.out.println("Delete another order? Y/N");
            continueMethod = MyUtil.getString(">> ", "").equalsIgnoreCase("Y");
        }//End while(continueMethod)

    }//End removeOrder()

    //FUNCTION #11
    public void saveToFile() {
        System.out.println("#Save Orders to file orders.txt");
        try {
            PrintWriter pw = new PrintWriter(fName);
            for (Order o : this) {
                String productLine = "";
                String quantityLine = "";
                for (int i = 0; i < o.getProductIDs().size(); i++) {
                    productLine += o.getProductIDs().get(i) + "-";
                    quantityLine += o.getOrderQuantities().get(i) + "-";
                }
                productLine = productLine.substring(0, productLine.length() - 1);
                quantityLine = quantityLine.substring(0, quantityLine.length() - 1);
                pw.println(o.getOrderID() + "," + o.getCustomerID() + "," + productLine + "," + quantityLine + ","
                        + o.getOrderDate() + "," + o.isStatus());
            }
            pw.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Failed to save file!");
        }
        System.out.println("Save Orders to file successfully");
    }

    //ADDITIONAL METHODS
    private void attribute() {
        System.out.format("%-9s%-12s%-12s%-10s%-15s%-10s\n", "Order ID", "Customer ID", "Product ID", "Quantity", "Order Date", "Status");
        System.out.println("=================================================================");
    }

    private String selectCustomer(String mode) {
        if (customerList.isEmpty()) {
            return "Unknown";
        }
        System.out.println("\t#Select a customer");
        for (int i = 1; i <= customerList.size(); i++) {
            Customer c = customerList.get(i - 1);
            System.out.println("\t" + i + ") " + c.getCustomerID() + " - " + c.getCustomerName());
        }
        int choice = MyUtil.getInt(">> ", 1, customerList.size(), mode);
        if (mode.contains("update") && choice == Integer.MIN_VALUE) {
            return "_not_change";
        }
        return customerList.get(choice - 1).getCustomerID();
    }

    private String selectProduct(String mode) {
        if (productList.isEmpty()) {
            return "Unknown";
        }
        int choice = 1;

        System.out.println("\t#Select a Product");
        for (int i = 1; i <= productList.size(); i++) {
            Product p = productList.get(i - 1);
            System.out.println("\t" + i + ") " + p.getProductID() + " - " + p.getProductName() + " - " + p.getOrigin());
        }
        System.out.println("\tOther - Done");
        choice = MyUtil.getInt(">> ", 1, productList.size(), mode);
        if (mode.contains("update") && choice == Integer.MIN_VALUE) {
            return "_not_change";
        }
        return productList.get(choice - 1).getProductID();
    }
}
