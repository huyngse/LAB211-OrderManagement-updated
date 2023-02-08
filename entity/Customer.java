/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author huyngo
 */
public class Customer {

    //ATTRIBUTES
    private String customerID;
    private String customerName;
    private String customerAddress;
    private String customerPhone;

    //CONSTRUCTORS
    public Customer() {
    }

    public Customer(String customerID) {
        this.customerID = customerID;
    }

    public Customer(String customerID, String customerName, String customerAddress, String customerPhone) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPhone = customerPhone;
    }

    //GETTERS & SETTERS
    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }
    
    //OVERRIDE METHODS
    @Override
    public String toString() {
        return String.format("%-8s%-25s%-15s%-15s", customerID, customerName, customerAddress, customerPhone);
    }

    @Override
    public boolean equals(Object obj) {
        Customer other = (Customer) obj;
        return customerID.equalsIgnoreCase(other.customerID);
    }
    
}
