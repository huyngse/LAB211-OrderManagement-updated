/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import tools.MyUtil;

/**
 *
 * @author huyngo
 */
public class Menus {
    public static int choice = 0;

    public static void mainMenu() {
        System.out.println(
                "\nORDER MANAGER\n"
                + " 1. Show all Products\n"
                + " 2. Show all Customers\n"
                + " 3. Search a Customer\n"
                + " 4. Add a Customer\n"
                + " 5. Update a Customer\n"
                + " 6. Save Customers to file\n"
                + " 7. Show all Orders\n"
                + " 8. Show all pending Orders\n"
                + " 9. Add an Order\n"
                + "10. Update an Order\n"
                + "11. Save Orders to file\n"
                + "Others - Quit\n");
    }

    public static void updateOrderMenu() {
        System.out.println(
                "\n10. Update an Order\n"
                + "\t10.1. Update an Order based on its ID\n"
                + "\t10.2. Delete an Order based on its ID\n "
                + "\tOthers - Back\n");
    }
    
    public static void  getUserChoice() {
        choice = MyUtil.getInt(">> ", "");
    }
    
}
