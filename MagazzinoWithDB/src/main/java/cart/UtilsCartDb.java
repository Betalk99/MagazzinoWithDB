package cart;

import choice.UtilsChoice;
import database.DbManagement;
import product.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class UtilsCartDb {

    public static ArrayList<Product> statusCart(int idClient){
        ArrayList<Product> status = new ArrayList<>();
        try{
            Statement stmt = DbManagement.makeConnection();
            String joinCartProd = "SELECT * FROM cart AS c\n" +
                    "JOIN product AS p ON c.idProduct = p.id\n" +
                    "WHERE c.idClient = "+ idClient +";";
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
            UtilsChoice.stampaStockDb();
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
            cartManagement.stampYourCart(idClient);
            System.out.println("Which device do you want to remove from your cart? \\n Indicate the id : ");

            int idProd = in.nextInt();

            String addProductId = "DELETE FROM cart AS c WHERE c.id = "+ idProd +";";
            stmt.execute(addProductId);
        }catch (SQLException e ){
            System.out.println(e.getMessage());
        }
    }

}
