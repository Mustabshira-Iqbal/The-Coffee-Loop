/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package thecoffeeloop;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.IOException;
import java.io.FileWriter;

/**
 *
 * @author IA Computers
 */
public class TheCoffeeLoop {
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) { 
             
         Scanner input = new Scanner(System.in);
         String[] hotCoffeeFlavors = { "Espresso","Cappuccino" ,"Latte","Mocha", "Flat White", "Americano", "Hot Chocolate", "Spanish Latte","Caramel Latte","Vanilla Latte"};
         String[] coldCoffeeFlavors = { "Affogato","Cold Brew","Iced Latte","Iced Mocha", "Iced Americano", "Caramel Frappe","Mocha Frappe","Vanilla Frappe","Oreo Frappe","Coffee Shake"};
         
         int[] hotCoffeePrices = {400,480, 500, 600, 550,400, 500, 650,620, 600};
         int[] coldCoffeePrices = {650, 580, 550,600, 420,700, 720, 680, 750, 650};
         
         int choice;
         int menuChoice;
         int price = 0;
         int quantity;
         char orderAgain = 0;
         String coffeeName = " ";
         int total;       
         int finalTotal;  
         String customerName;
         String paymentMethod = "  ";
      
         System.out.println("====================================================");
         System.out.println("            WELCOME TO THE COFFEE LOOP ");
         System.out.println("         WHERE EVERY SIP INSPIRES YOUR SOUL");
         System.out.println("====================================================");
         System.out.print("\nEnter Customer Name: ");
         customerName = input.nextLine();
         System.out.println("\nHello, " + customerName + "!\n");
         
     
        do {
            System.out.println("\n....... THE COFFEE LOOP ORDERING SYSTEM .......");
            System.out.println("1. Place Order");
            System.out.println("2. Search Coffee");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");
            menuChoice = input.nextInt();
            input.nextLine(); 
            System.out.println();

            try {
             
                if (menuChoice == 1){    
                    
                    System.out.println("HOT COFFEE FLAVORS");
                    for (int i = 0; i < hotCoffeeFlavors.length; i++) {
                        System.out.println((i + 1) + ". " + hotCoffeeFlavors[i] + " ........ Rs. " + hotCoffeePrices[i]);
                    }   

                    System.out.println("\nCOLD COFFEE & ICED DRINKS");
                    for (int i = 0; i < coldCoffeeFlavors.length; i++) {
                        System.out.println((i + 11) + ". " + coldCoffeeFlavors[i] + " ........ Rs. " + coldCoffeePrices[i]);
                    }
                    
                    int orderID = (int) (Math.random() * 9000) + 1000;
                    System.out.print("\nWhat would you like to order (1-20)? ");
                    choice = input.nextInt();
                    input.nextLine();
       
                    System.out.print("How many cups do you want? ");
                    quantity = input.nextInt();
                    
                    System.out.println("\nSelect Payment Method");
                    System.out.println("1. Cash");
                    System.out.println("2. Card");
                    System.out.println("3. EasyPaisa");
                    System.out.println("4. JazzCash");

                    System.out.print("Enter Choice: ");
                    int paymentChoice = input.nextInt();

                    switch (paymentChoice) {

                        case 1:
                            paymentMethod = "Cash";
                            break;

                        case 2:
                            paymentMethod = "Card";
                            break;

                        case 3:
                            paymentMethod = "EasyPaisa";
                            break;

                        case 4:
                            paymentMethod = "JazzCash";
                            break;

                        default:
                            paymentMethod = "Cash";
                            System.out.println("Invalid Choice! Cash selected by default.");
                    }

           
                    if (quantity <= 0) {
                        System.out.println("Quantity must be greater than zero.");
                        continue;
                    }

            
                    if (choice >= 1 && choice <= 10) {
                        coffeeName = hotCoffeeFlavors[choice - 1];
                        price = hotCoffeePrices[choice - 1];
                    }
                    
                    else if (choice >= 11 && choice <= 20) {
                        coffeeName = coldCoffeeFlavors[choice - 11];
                        price = coldCoffeePrices[choice - 11];

                    }
                    else {
                        System.out.println("Invalid Choice!");
                        continue;
                    }
                    int discount = 0;
                    total = calculateTotal(price, quantity);
                    
                    if (quantity >= 5) {
                        discount = total * 10 / 100;
                        System.out.println();
                        System.out.println("Congratulations!");
                        System.out.println("You received a 10% Discount.");
                    }
                    finalTotal = total - discount;
                    System.out.println();
                                       
                    printSummary(customerName,orderID, coffeeName,price,quantity,total,discount, finalTotal, paymentMethod);
                    
                    saveReceiptToFile( customerName, orderID,  coffeeName, price, quantity, total, discount, finalTotal, paymentMethod);
                    System.out.println();
                    System.out.println();
                    System.out.print("Do you want to order again? (Y/N): ");
                    orderAgain = input.next().charAt(0);                    
                   
                }
                
                else if(menuChoice == 2)
                    {
                        searchCoffee(hotCoffeeFlavors, coldCoffeeFlavors, hotCoffeePrices, coldCoffeePrices, input);
                        orderAgain = 'Y';
                    }
 
                else if(menuChoice == 3)
                    {
                        break;
                    }
                
                else
                    {
                        System.out.println("Invalid Menu Choice!");
                        orderAgain='Y';
                    }
            }    
    
            catch (InputMismatchException ex) {
                System.out.println();
                System.out.println("Invalid Input!");
                System.out.println("Please enter numbers only.");
                input.nextLine();
                continue;
            }
        }

        while(orderAgain == 'Y' || orderAgain == 'y');
        
        System.out.println();
        System.out.println("Thank you for visiting The Coffee Loop!");
        System.out.println("May your day be as delightful as your drink!");
        input.close();    
    
    }
    
    public static void searchCoffee(String[] hotCoffeeFlavors,String[] coldCoffeeFlavors,int[] hotCoffeePrices,int[] coldCoffeePrices,Scanner input){
        
        String searchName;
        boolean found = false;
        System.out.println("\n========== SEARCH COFFEE ==========");
        System.out.print("Enter Coffee Name: ");
        searchName = input.nextLine();
         
        for (int i = 0; i < hotCoffeeFlavors.length; i++) {
            
            if (hotCoffeeFlavors[i].equalsIgnoreCase(searchName)) {
                
                System.out.println("\nCoffee Found!");
                System.out.println("Coffee Name : " + hotCoffeeFlavors[i]);
                System.out.println("Price       : Rs. " + hotCoffeePrices[i]);
                found = true;
                break;
            }
        }
        if (!found) {
            
            for (int i = 0; i < coldCoffeeFlavors.length; i++) {
                if (coldCoffeeFlavors[i].equalsIgnoreCase(searchName)) {
                    System.out.println("\nCoffee Found!");
                    System.out.println("Coffee Name : " + coldCoffeeFlavors[i]);
                    System.out.println("Price       : Rs. " + coldCoffeePrices[i]);
                    found = true;
                    break;
                }
            }
        }

        if (!found) {
            System.out.println("\nCoffee Not Found!");
        }
    }
     
     
    public static int calculateTotal(int price, int quantity) {
        return price * quantity;
    }

    
    public static void printSummary(String customerName,int orderID, String coffeeName,int price,int quantity,int total,int discount,int finalTotal, String paymentMethod) {
        System.out.println();
        System.out.println("=================================================");
        System.out.println("                 ORDER RECEIPT");
        System.out.println("=================================================");

        System.out.println("Order ID        : " + orderID);
        System.out.println("Customer Name   : " + customerName);


        System.out.println("-----------------------------------------------");

        System.out.println("Coffee          : " + coffeeName);
        System.out.println("Quantity        : " + quantity);
        System.out.println("Price Per Cup   : Rs. " + price);

        System.out.println("-----------------------------------------------");

        System.out.println("Calculation     : Rs. " + price + " * " + quantity + " = Rs. " + total);

        System.out.println("Subtotal        : Rs. " + total);
        System.out.println("Discount        : Rs. " + discount);
        System.out.println("Final Total     : Rs. " + finalTotal);
        System.out.println("Payment Method  : " + paymentMethod);

        System.out.println("-----------------------------------------------");

        System.out.println("-----------------------------------------------");
    
        System.out.println("Enjoy your coffee! ");

        System.out.println("=================================================");
    }
    
    public static void saveReceiptToFile(String customerName, int orderID, String coffeeName, int price,int quantity,int total,int discount,int finalTotal, String paymentMethod){
        try {
            FileWriter writer = new FileWriter("Receipt.txt", true);
            
            writer.write("\n====================================\n");
            writer.write("THE COFFEE LOOP RECEIPT\n");
            writer.write("====================================\n");
            writer.write("Order ID      : " + orderID + "\n");
            writer.write("Customer Name : " + customerName + "\n");
            writer.write("Coffee        : " + coffeeName + "\n");
            writer.write("Price         : Rs. " + price + "\n");
            writer.write("Quantity      : " + quantity + "\n");
            writer.write("Subtotal      : Rs. " + total + "\n");
            writer.write("Discount      : Rs. " + discount + "\n");
            writer.write("Final Total   : Rs. " + finalTotal + "\n");
            writer.write("Payment Method: " + paymentMethod + "\n");
            writer.write("====================================\n");
            writer.close();
            System.out.println("Receipt saved successfully!");
        }
        catch (IOException e) {           
            System.out.println("Error while saving receipt.");
        }
    }
}

