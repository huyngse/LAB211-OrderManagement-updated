/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataStructure;

import entity.Customer;
import entity.Product;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import tools.MyUtil;

/**
 *
 * @author huyngo
 */
public class CustomerList extends ArrayList<Customer> {

    private static String fName = "src/data/customers.txt/";

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
                String name = stk.nextToken();
                String address = stk.nextToken();
                String phone = stk.nextToken();
                this.add(new Customer(id, name, address, phone));
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Failed to read file");
        }
        System.out.println("Load Customers from file successfully");
    }//End readFromFile()

    // FUNCTION #2
    public void display() {
        System.out.println("");
        if (this.isEmpty()) {
            System.out.println("Customers list is empty");
            return;
        }
        attribute();
        for (Customer c : this) {
            System.out.println(c);
        }
    }//End display()

    //FUNCTION #3
    public void searchCustomer() {
        System.out.println("");
        if (this.isEmpty()) {
            System.out.println("Customers list is empty");
            return;
        }

        System.out.println("#Search a customer by his/her ID");
        String id = MyUtil.getString("Enter ID\n>> ", "");

        int pos = this.indexOf(new Customer(id));
        if (pos < 0) {
            System.out.println("This customer does not exist");
            return;
        }
        attribute();
        System.out.println(this.get(pos));
    } //End searchCustomer()

    //FUNCTION #4
    public void addCustomer() {
        boolean continueMethod = true;
        while (continueMethod) {
            System.out.println("#Add a customer");
            int pos;
            String id;
            do {
                id = MyUtil.getString("Enter ID\n>> ", "");
                pos = this.indexOf(new Customer(id));
                if (pos >= 0) {
                    System.out.println("ID is not allowed to be duplicated");
                }
            } while (pos >= 0);
            String name = MyUtil.getString("Enter name\n>> ", "uppercase");
            String address = MyUtil.getString("Enter address\n>> ", "uppercase");
            String phone = MyUtil.getString("Enter phone number (10-12 digits)\n>> ", "[0-9]{10,12}", "");
            this.add(new Customer(id, name, address, phone));

            System.out.println("New Customer added successfully"
                    + "\nAdd another Customer? Y/N");
            continueMethod = MyUtil.getString(">> ", "").equalsIgnoreCase("Y");
        } //End while(continueMethod)
    } //End  addCustomer()

    //FUNCTION #5
    public void updateCustomer() {
        System.out.println("");
        if (this.isEmpty()) {
            System.out.println("Customers list is empty");
            return;
        }

        System.out.println("#Update a customer");
        String id = MyUtil.getString("Enter ID\n>> ", "");
        int pos = this.indexOf(new Customer(id));
        if (pos < 0) {
            System.out.println("This customer does not exist");
            System.out.println("Failed to update customer");
            return;
        }
        Customer customer = this.get(pos);
        attribute();
        System.out.println(customer);
        
        String name = MyUtil.getString("Enter new name\n>> ", "uppercase update");
        if (name.equals("_not_change")) {
            name = customer.getCustomerName();
            System.out.println(">> " + name);
        }
        String address = MyUtil.getString("Enter new address\n>> ", "uppercase update");
        if (address.equals("_not_change")) {
            address = customer.getCustomerAddress();
            System.out.println(">> " + address);
        }
        String phone = MyUtil.getString("Enter new phone number (10-12 digits)\n>> ", "[0-9]{10,12}", "update");
        if (phone.equals("_not_change")) {
            phone = customer.getCustomerPhone();
            System.out.println(">> " + phone);
        }
        
        customer.setCustomerName(name);
        customer.setCustomerAddress(address);
        customer.setCustomerPhone(phone);
        System.out.println("Customer updated successfully");
    }//End updateCustomer()
    
    //FUNCTION #6
    public void saveToFile() {
        System.out.println("#Save Customers to file customers.txt");
        try {
            PrintWriter pw = new PrintWriter(fName);
            for (Customer c : this) {
                pw.println(c.getCustomerID() + "," + c.getCustomerName() + "," + c.getCustomerAddress() + "," + c.getCustomerPhone());
            }
            pw.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Failed to save file!");
        }
        System.out.println("Save Customers to file successfully");
    } //End saveToFile()

    private static void attribute() {
        System.out.format("%-8s%-25s%-15s%-15s", "ID", "Customer Name", "Address", "Phone Number");
        System.out.println("\n================================================================");
    }
}
