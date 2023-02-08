/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import dataStructure.CustomerList;
import dataStructure.OrderList;
import dataStructure.ProductList;
import entity.Order;

/**
 *
 * @author huyngo
 */
public class OrderManager {

    static ProductList productList = new ProductList();
    static CustomerList customerList = new CustomerList();
    static OrderList orderList = new OrderList(customerList, productList);

    public static void main(String[] args) {
        productList.readFromFile();
        customerList.readFromFile();
        orderList.readFromFile();
        Order.setCustomerList(customerList);

        do {
            Menus.mainMenu();
            Menus.getUserChoice();
            switch (Menus.choice) {
                case 1: { //1.	List all Products
                    productList.display();
                    break;
                }
                case 2: {//2.	List all Customers
                    customerList.display();
                    break;
                }
                case 3: {//3.	Search a Customer based on his/her ID
                    customerList.searchCustomer();
                    break;
                }
                case 4: {//4.	Add a Customer
                    customerList.addCustomer();
                    break;
                }
                case 5: {//5.	Update a Customer
                    customerList.updateCustomer();
                    break;
                }
                case 6: {//6.	Save Customers to the file, named customers.txt
                    customerList.saveToFile();
                    break;
                }
                case 7: {//7.	List all Orders in ascending order of Customer name
                    orderList.display();
                    break;
                }
                case 8: {//8.	List all pending Orders
                    orderList.displayPendingOrder();
                    break;
                }
                case 9: {//9.	Add an Order
                    orderList.addOrder();
                    break;
                }
                case 10: {//10.	Update an Order
                    runUpdateOrderMenu();
                    break;
                }
                case 11: {//11.	Save Orders to file, named orders.txt
                    orderList.saveToFile();
                    break;
                }
            }
        } while (Menus.choice >= 1 && Menus.choice <= 11);
    }

    static void runUpdateOrderMenu() {
        do {
            Menus.updateOrderMenu();
            Menus.getUserChoice();
            switch (Menus.choice) {
                case 1: {
                    orderList.updateOrder();
                    break;
                }
                case 2: {
                    orderList.removeOrder();
                    break;
                }
            }
        } while (Menus.choice >= 1 && Menus.choice <= 2);
        Menus.choice = 10;
    }
}
