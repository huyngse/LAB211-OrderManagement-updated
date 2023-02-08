/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import dataStructure.CustomerList;
import java.util.ArrayList;

/**
 *
 * @author huyngo
 */
public class Order implements Comparable<Order> {

    //ATTRIBUTES
    private String orderID;
    private String customerID;
    private ArrayList<String> productIDs;
    private ArrayList<Integer> quantities;
    private String orderDate;
    private boolean status;
    private static CustomerList customerList;

    //CONSTRUCTORS
    public Order() {
    }

    public Order(String orderID) {
        this.orderID = orderID;
    }

    public Order(String orderID, String customerID, ArrayList productIDs, ArrayList quantities, String orderDate, boolean status) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.productIDs = productIDs;
        this.quantities = quantities;
        this.orderDate = orderDate;
        this.status = status;
    }

    //GETTERS AND SETTERS

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public ArrayList getProductIDs() {
        return productIDs;
    }

    public void setProductIDs(ArrayList<String> productIDs) {
        this.productIDs = productIDs;
    }

    public ArrayList<Integer> getOrderQuantities() {
        return quantities;
    }

    public void setOrderQuantities(ArrayList<Integer> quantities) {
        this.quantities = quantities;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public static void setCustomerList(CustomerList list) {
        customerList = list;
    }

    //OVERRIDE METHODS
    @Override
    public String toString() {
        String s = String.format("%-9s%-12s%-12s%-10s%-15s%-10b\n", orderID, customerID, productIDs.get(0), quantities.get(0), orderDate, status);
        for (int i = 1; i < productIDs.size(); i++) {
            s += String.format("%-9s%-12s%-12s%-10s%-15s%-10s\n", "", "", productIDs.get(i), quantities.get(i), "", "");
        }
        s += ("__________________________________________________________________");
        return s;
    }

    @Override
    public int compareTo(Order o) {
        int pos1 = customerList.indexOf(new Customer(this.customerID));
        int pos2 = customerList.indexOf(new Customer(o.customerID));
        if (pos1 < 0 || pos2 < 0) {
            return 0;
        }
        String name1 = customerList.get(pos1).getCustomerName();
        String name2 = customerList.get(pos2).getCustomerName();
        return name1.compareTo(name2);
    }

    @Override
    public boolean equals(Object obj) {
        Order other = (Order) obj;
        return this.orderID.equalsIgnoreCase(other.orderID);
    }
    
}
//