package cart;

import choice.*;
import database.*;
import product.*;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class UtilsCartDb {

    public static ArrayList<Product> statusCart(int idClient){
        ArrayList<Product> status = new ArrayList<>();
        try{
            Statement stmt = DbManagement.makeConnection();
            String joinCartProd = "SELECT * FROM cart AS c\n" +
                    "JOIN product AS p ON c.idProduct = p.id\n" +
                    "WHERE c.idClient = "+ idClient +" AND c.status = 0;";
            ResultSet rs = stmt.executeQuery(joinCartProd);
            while (rs.next()){
                status.add(DbManagement.costructProd(rs));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return status;
    }


    public static void addCartIdDb(int idCart, int idClient){
        try {
            Scanner in = new Scanner(System.in);
            Statement stmt = DbManagement.makeConnection();
            UtilsChoice.stampResult(UtilsChoice.stampaStockDb());
            System.out.println("Which device do you want to add from your cart? \\n Indicate the id : ");

            int idProd = in.nextInt();

            String addProductId = "INSERT INTO cart (idCart, idProduct, idClient)\n" +
                    "VALUES ("+ idCart+", "+idProd+", "+idClient+");";
            stmt.execute(addProductId);
        }catch (SQLException e ){
            System.out.println(e.getMessage());
        }
    }

    public static void removeCartIdDB(int idClient){
        try {
            Scanner in = new Scanner(System.in);
            Statement stmt = DbManagement.makeConnection();
            cartManagement.stampYourCart(statusCart(idClient));
            System.out.println("Which device do you want to remove from your cart? \\n Indicate the id : ");

            int idProd = in.nextInt();

            String removeProductId = "DELETE FROM cart AS c WHERE c.id = "+ idProd +";";
            stmt.execute(removeProductId);
        }catch (SQLException e ){
            System.out.println(e.getMessage());
        }
    }

    public static BigDecimal totalCostCart(int idClient, int idCart){
        BigDecimal somma = null;
        try {
            Statement stmt = DbManagement.makeConnection();
            cartManagement.stampYourCart(statusCart(idClient));

            ResultSet rs = stmt.executeQuery("SELECT SUM(p.sellprice) AS sum FROM cart AS c\n" +
                    "JOIN product AS p ON c.idProduct = p.id\n" +
                    "WHERE c.idClient = "+idClient+" AND c.idCart = "+idCart+";");
            while(rs.next()){
                somma = BigDecimal.valueOf(rs.getInt("sum"));
            }


        }catch (SQLException e ){
            System.out.println(e.getMessage());
        }
        return somma;
    }

    public static void getEmptyCart(int idClient){
        try{
            Scanner in = new Scanner(System.in);
            Statement stmt = DbManagement.makeConnection();
            cartManagement.stampYourCart(statusCart(idClient));

            System.out.println("Are you sure you're emptying your cart?   1) YES  2) NO");
            int ans = in.nextInt();
            if(ans == 1){
                stmt.executeQuery("DELETE FROM cart AS c WHERE c.idClient = "+idClient+";");
            }else{
                System.out.println("You have chosen not to empty your cart");
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void checkout(int idCart, int idClient){
        try{
            Scanner in = new Scanner(System.in);
            Statement stmt = DbManagement.makeConnection();
            cartManagement.stampYourCart(statusCart(idClient));

            System.out.println("Are you sure you're checkout your cart?   1) YES  2) NO");
            System.out.println("Total cost is: " + totalCostCart(idClient, idCart));
            int ans = in.nextInt();

            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            System.out.println(dateFormat.format(date));

            if(ans == 1){
                String checkout = "INSERT INTO orders (idCart, date)" +
                        "VALUES ("+idCart+", '"+ dateFormat.format(date) +"' )";
                stmt.execute(checkout);
                stmt.executeUpdate("UPDATE cart AS c " +
                        "SET c.status = 1 " +
                        "WHERE c.idClient = "+ idClient +";");
                System.out.println("You have completed your order");
            }else{
                System.out.println("You have chosen not to checkout your cart");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }


}
