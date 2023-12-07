package choice;

import clients.Clients;
import database.DbManagement;
import product.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UtilsChoice {

    public static void stampResult(ArrayList<Product> a){
        for (Product i : a){
            System.out.println(i);
        }
    }

    public static ArrayList<Product> stampaStockDb()throws SQLException{
        ArrayList<Product> stock = new ArrayList<>();
        try{
            Statement stmt = DbManagement.makeConnection();
            ResultSet rs = stmt.executeQuery("SELECT * FROM stock AS s\n" +
                    "JOIN product AS p ON s.idStock = p.id\n" +
                    "WHERE s.qty >= 1");

            while(rs.next()){
                stock.add(DbManagement.costructProd(rs));
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return stock;
    }

    public static ArrayList<Product> byTypeDb()throws SQLException{
        ArrayList<Product> byTypeArray = new ArrayList<>();
        try{
            Statement stmt = DbManagement.makeConnection();
            whichType(stmt,byTypeArray);

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return byTypeArray;
    }

    public static ArrayList<Product> next(ArrayList<Product> byTypeArray, ResultSet rs) throws SQLException {
        while(rs.next()){
            byTypeArray.add(DbManagement.costructProd(rs));
        }
        return byTypeArray;
    }

    public static ArrayList<Product> whichType(Statement stmt, ArrayList<Product> byTypeArray) throws SQLException {
        Scanner in = new Scanner(System.in);
        System.out.println("Please select one of the following categories: \n 1) Notebook \n 2) Smartphone \n 3) Tablet");
        int category = in.nextInt();
        switch (category) {
            case 1:
                String queryNotebook = "SELECT * FROM stock AS s\n" +
                        "JOIN product AS p ON s.idStock = p.id\n" +
                        "WHERE p.type = 'notebook' AND s.qty >= 1;";
                ResultSet rsn = stmt.executeQuery(queryNotebook);
                next(byTypeArray, rsn);
                break;
            case 2:
                String querySmartphone = "SELECT * FROM stock AS s\n" +
                        "JOIN product AS p ON s.idStock = p.id\n" +
                        "WHERE p.type = 'smartphone' AND s.qty >= 1;";
                ResultSet rss = stmt.executeQuery(querySmartphone);
                next(byTypeArray, rss);
                break;
            case 3:
                String queryTablet = "SELECT * FROM stock AS s\n" +
                        "JOIN product AS p ON s.idStock = p.id\n" +
                        "WHERE p.type = 'tablet' AND s.qty >= 1;";
                ResultSet rst = stmt.executeQuery(queryTablet);
                next(byTypeArray, rst);
                break;
            default:
                System.out.println("The selected category is not available");
        }
        return byTypeArray;
    }


    public static ArrayList<Product> byBrandDb()throws SQLException{
        Scanner in = new Scanner(System.in);
        ArrayList<Product> prodByBrand = new ArrayList<>();
        try {
            Statement stmt = DbManagement.makeConnection();
            System.out.println("Which brand do you want to look for?");
            String brand = in.next();
            String queryBrand = "SELECT * FROM stock AS s\n" +
                    "JOIN product AS p ON s.idStock = p.id\n" +
                    "WHERE p.brand = '" + brand + "' AND s.qty >= 1;";
            ResultSet rs = stmt.executeQuery(queryBrand);

            while (rs.next()){
                prodByBrand.add(DbManagement.costructProd(rs));
            }

        }catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return prodByBrand;
    }

    public static ArrayList<Product> byModelDb()throws SQLException{
        Scanner in = new Scanner(System.in);
        ArrayList<Product> prodByModel = new ArrayList<>();
        try {
            Statement stmt = DbManagement.makeConnection();
            System.out.println("Which brand do you want to look for?");
            String model = in.nextLine();
            String queryModel = "SELECT * FROM stock AS s\n" +
                    "JOIN product AS p ON s.idStock = p.id\n" +
                    "WHERE p.model = '" + model + "' AND s.qty >= 1;";
            ResultSet rs = stmt.executeQuery(queryModel);

            while (rs.next()) {
                prodByModel.add(DbManagement.costructProd(rs));
            }

        }catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return prodByModel;
    }

    public static ArrayList<Product> bySellPriceRangeDb() throws SQLException{
        ArrayList<Product> searchSellPrice = new ArrayList<>();
        double[] range = new double[2];
        try {
            Scanner in = new Scanner(System.in);
            Statement stmt = DbManagement.makeConnection();
            System.out.println("Search products by price range");
            System.out.println("From");
            range[0] = in.nextDouble();
            System.out.println("to");
            range[1] = in.nextDouble();
            Arrays.sort(range);

            String searchRange = "SELECT * FROM stock AS s\n" +
                    "JOIN product AS p ON s.idStock = p.id\n" +
                    "WHERE p.sellprice  >= " + range[0] + " AND p.sellprice <= " + range[1] + " AND s.qty >= 1;";

            ResultSet rs = stmt.executeQuery(searchRange);

            while (rs.next()){
                searchSellPrice.add(DbManagement.costructProd(rs));
            }

        } catch (InputMismatchException e) {
            System.out.println("Please use an integer number (e.g. 250");
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return searchSellPrice;
    }


    public static int rescueIdClient(Clients c) throws SQLException{
        int idClient = 0;
        try{
            Statement stmt = DbManagement.makeConnection();
            String checkClientMail = "SELECT * FROM client WHERE email = '" + c.getEmail() + "';";
            ResultSet rs = stmt.executeQuery(checkClientMail);

            while (rs.next()){
                idClient = rs.getInt("id");
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return idClient;
    }

    public static int rescueIdCart(Clients c) {
        int idCart = -1;
        try {
            Statement stmt = DbManagement.makeConnection();
            String checkClientMail = "SELECT * FROM cart AS c\n" +
                    "WHERE c.idClient = " + rescueIdClient(c) + " AND c.status = 0;";
            ResultSet rs = stmt.executeQuery(checkClientMail);

            while (rs.next()) {
                idCart = rs.getInt("idCart");
            }

            if (idCart == -1) {
                String checkNewIdCart = "SELECT MAX(idCart) AS idCartMax FROM cart;";
                ResultSet rs1 = stmt.executeQuery(checkNewIdCart);
                while (rs1.next()) {
                    idCart = (rs1.getInt("idCartMax") + 1);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return idCart;
    }
}