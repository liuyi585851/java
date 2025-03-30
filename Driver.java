import java.util.Scanner;

/**
 * This class runs the application and handles the Product I/O
 *
 * @author Guoqing Lu, Siobhan Drohan, Mairead Meagher
 * @version 1.0
 *
 */
public class Driver{

    private Scanner input = new Scanner(System.in);
    private Product product;
    private Store store;

    /**
     * The main method is the starting point for the program.
     * When started, the main method calls the addProduct() method
     * followed by the printProduct() method.
     *
     * @param args  Any arguments for the main method
     */
    public static void main(String[] args) {
		new Driver();
	}
    public Driver() {
        processOrder();
        runMenu();
    }
    //gather the product data from the user and create a new product object.
    private void addProduct(){
        input.nextLine();
    	System.out.print("Enter the Product Name:  ");
        String productName = input.nextLine();
        System.out.print("Enter the Product Code:  ");
        int productCode = input.nextInt();
        System.out.print("Enter the Unit Cost:  ");
        double unitCost = input.nextDouble();

        //Ask the user to type in either a Y or an N.  This is then
        //converted to either a True or a False (i.e. a boolean value).
    	System.out.print("Is this product in your current line (y/n): ");
    	char currentProduct = input.next().charAt(0);
    	boolean inCurrentProductLine = false;
    	if ((currentProduct == 'y') || (currentProduct == 'Y'))
    		inCurrentProductLine = true;

        boolean isAdded = store.add(new Product(productName,productCode,unitCost,inCurrentProductLine));
        if(isAdded){
            System.out.println("Product Added Successfully");
        }else{
            System.out.println("No Product Added");
        }
    }
    
    //print the product (the toString method is automatically called).
    private void printProducts(){
    	System.out.println(store.listProducts());
    }

    private void processOrder(){
        System.out.print("How many Products would you like to have in your Store?  ");
        int numberProducts = input.nextInt();

        store = new Store(numberProducts);
        for(int i = 0;i < numberProducts;i++){
            addProduct();
        }
    }

    private void printCurrentProducts(){
        System.out.println("List of CURRENT Products are:");
        System.out.println(store.listCurrentProducts());
    }

    private void printAverageProductPrice() {
        double averagePrice = store.averageProductPrice();
        if (averagePrice == -1) {
            System.out.println("There are no products in the store");
        } else {
            System.out.printf("The average product price is: " + averagePrice + "\n");
        }
    }

    private void printCheapestProduct() {
        Product cheapestProduct = store.cheapestProduct();
        if (cheapestProduct == null) {
            System.out.println("There are no products in the store.");
        } else {
            System.out.println("The cheapest product is: " + cheapestProduct.getProductName());
        }
    }

    private void printProductsAboveAPrice() {
        System.out.println("View the products costing more than this price: ");
        double price = input.nextDouble();
        System.out.println(store.listProductsAboveAPrice(price));
    }
    private int mainMenu(){
        System.out.print(""" 
               Shop Menu 
               --------- 
                   1) List the Products 
                   2) List the current products 
                   3) Display average product unit cost 
                   4) Display cheapest product 
                   5) List products that are more expensive than a given price 
                   0) Exit 
               ==>> """);
        int option = input.nextInt();
        return option;
    }
    private void runMenu(){
        int option = mainMenu();
        while (option != 0){
            switch (option){
                case 1 -> printProducts();
                case 2 -> printCurrentProducts();
                case 3 -> printAverageProductPrice();
                case 4 -> printCheapestProduct();
                case 5 -> printProductsAboveAPrice();
                default -> System.out.println("Invalid option entered: " + option);
            }
//pause the program so that the user can read what we just printed to the terminal window
            System.out.println("\nPress enter key to continue...");
            input.nextLine();
            input.nextLine(); //second read is required - bug in Scanner class; a String read is ignored straight after reading an int.
//display the main menu again option = mainMenu();
        }
//the user chose option 0, so exit the program
        System.out.println("Exiting...bye");
        System.exit(0);
    }
}