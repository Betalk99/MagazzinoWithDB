package cart;

import product.Product;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;
public class cartManagement {
    public static void operCar(int idClient, int idCart) {

        System.out.println(" ID CARRRELLO : " + idCart);



        try {
            Scanner sc = new Scanner(System.in);
            boolean stay = true;
            while (stay) {
                System.out.println("""
                        Hello dear customer, please select one of the following options:
                         1) Cart status\s
                         2) Add product/s to cart via ID\s
                         3) Remove product/s to cart via ID\s
                         4) Get the total price of the items in the cart\s
                         5) Add products to your cart\s
                         6) Proceed to checkout\s
                         7) Get empty cart
                         8) My orders""");


                int operCarr = sc.nextInt();
                switch (operCarr) {
                    case 1://controllo stato carrello
                        checkCartEmpty(idClient);
                        break;
                    case 2://aggiunta elementi da carrello tramite id
                        UtilsCartDb.addCartIdDb(idCart,idClient);
                        stampYourCart(UtilsCartDb.statusCart(idClient));
                        break;
                    case 3://rimozione elementi da carrello tramite id
                        UtilsCartDb.removeCartIdDB(idClient);
                        stampYourCart(UtilsCartDb.statusCart(idClient));
                        break;
                    case 4:// costo totale
                        stampYourCart(UtilsCartDb.statusCart(idClient));
                        System.out.println("Total Sell Price Cart: " + UtilsCartDb.totalCostCart(idClient, idCart));
                        break;
                    case 5://Aggiunta prodotti al carrello
                        break;
                    case 6:// checkout
                        UtilsCartDb.checkout(idCart,idClient);
                        break;
                    case 7:
                        UtilsCartDb.getEmptyCart(idClient);
                        checkCartEmpty(idClient);
                        break;
                    case 8:
                        stampYourCart(UtilsCartDb.myOrder(idClient));
                        break;
                }
                boolean stay2 = true;
                while (stay2) {
                    System.out.println("\nIf you'd like to perform other cart-related operations, type '1'.\nIf you wish to go back to the user's menu, type '2'.");
                    int selectOption = sc.nextInt();
                    if (selectOption == 1) {
                        stay = true;
                        stay2 = false;
                    } else if (selectOption == 2) {
                        stay = false;
                        stay2 = false;
                    } else {
                        System.out.println("Invalid input.");
                        stay = true;
                        stay2 = true;
                    }
                }
            }

        } catch (InputMismatchException e) {
            System.out.println("Error: invalid input.");
        }
    }

    public static void stampYourCart(ArrayList<Product> cart){
        System.out.println("Your Cart :  \n");
        for(Product i : cart){
            System.out.println(i);
        }
        System.out.println("\n");
    }

    public static void checkCartEmpty(int idClient){
        ArrayList<Product> cart = UtilsCartDb.statusCart(idClient);
        if(cart.isEmpty()){
            System.out.println("Cart is Empty");
        }else{
            stampYourCart(UtilsCartDb.statusCart(idClient));
        }
    }


}
