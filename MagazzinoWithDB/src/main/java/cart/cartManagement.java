package cart;

import java.util.Scanner;
public class cartManagement {
    public static void operCar(int idClient, int idCart) {
        try {
            Scanner sc = new Scanner(System.in);
            boolean stay = true;
            while (stay) {
                System.out.println("""
                        Hello dear customer, please select one of the following options:
                         1) Cart status\s
                         2) Add product/s to cart via ID\s
                         3) Remove product/s to cart via ID\s
                         4) Proceed to checkout\s
                         5) Add products to your cart\s
                         6) Get the total price of the items in the cart\s
                         7) Get empty cart
                         8) Get the average amount spent""");


                int operCarr = sc.nextInt();
                switch (operCarr) {
                    case 1://controllo stato carrello
                        if (stampCart(cart)) {
                            System.out.println("The cart is empty");
                        } else {
                            System.out.println(cart);
                        }
                        break;
                    case 2://aggiunta elementi da carrello tramite id
//                        addId(arrayTemp, cart);
                        break;
                    case 3://rimozione elementi da carrello tramite id
                        insertRemoveId(cart, arrayTemp);
                        break;
                    case 4://Finalizza acquisti
                        buyProducts(cart, stock, arrayTemp);
                        break;
                    case 5://Aggiunta prodotti al carrello
                        insertAddProdCart(cart, arrayTemp);
                        break;
                    case 6://Prezzo totale dei prodotti nel carrello.
                        cartTotal(cart);
                        break;
                    case 7:
                        cart.getCart().clear();
                        break;
                    case 8:
                        averageSpending(cart);
                        break;
                }

            }
            boolean stay2 = true;
            while (stay2) {
                System.out.println("If you'd like to perform other cart-related operations, type '1'.\nIf you wish to go back to the user's menu, type '2'.");
                String selectOption = sc.nextLine();
                if (Objects.equals(selectOption, "1")) {
                    stay = true;
                    stay2 = false;
                } else if (Objects.equals(selectOption, "2")) {
                    stay = false;
                    stay2 = false;
                } else {
                    System.out.println("Invalid input.");
                    stay = true;
                    stay2 = true;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: invalid input.");
        }
    }






}
