package clients;
import choice.*;
import database.*;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AccessOrRegister {

    public static void inputSelect(){
        boolean isTrue = false;
        while(!isTrue){
            try{
                Scanner in = new Scanner(System.in);
                registerAcces();
                System.out.println("Would you like to make other researches? 1) Yes  2)No ");
                int stay = in.nextInt();
                if(stay == 2){
                    isTrue = true;
                }
            }catch (InputMismatchException e){
                System.out.println("Please use a character between 1, 2 or 3");
            }
        }
    }


    public static void registerAcces(){
        try{
            Scanner in = new Scanner(System.in);
            boolean stay = false;
            while (!stay){
                System.out.println("""
                        Hello dear user, please select one of the following options:\s
                        1) Log In \s
                        2) Register \s
                        3) Reset Password \s
                        """);
                int selection = in.nextInt();
                switch (selection){
                    case 1:
                        accessPage();
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    default:
                        break;
                }
            }
        }catch (InputMismatchException e){
            System.out.println("Please use a character between 1 or 2");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void accessPage() throws SQLException {
        Scanner in = new Scanner(System.in);
        boolean isTrue = false;
        boolean isOk = false;
        while (!isTrue) {
            boolean find = false;
            System.out.println("Insert Email: ");
            String mail = in.next();
            if (checkMailDb(mail)) {
                Clients client = getClientByUsernameDb(mail);
                while(!isOk) {
                    System.out.println("Insert Password: ");
                    String psw = in.next();
                    if (checkPassword(psw, client)) {
                        whatType(client);
                        isOk = true;
                    } else {
                        System.out.println("\nPassword error");
                        System.out.println("Please re-enter your password \n");
                    }
                }
            } else {
                System.out.println("\nEmail error");
                System.out.println("Please re-enter your email \n");
            }
        }
    }

    public static Clients getClientByUsernameDb(String mail) throws SQLException{
        try {
            Statement stmt = DbManagement.makeConnection();
            ResultSet rs = stmt.executeQuery("SELECT * FROM client WHERE email = '" + mail + "';");

            while (rs.next()){
                String checkMailRs = rs.getString("email");
                String typeClient = rs.getString("type");
                if(checkMailRs.equals(mail)){
                    if(typeClient.equals("Customer")){
                        Customer c1 = new Customer(ClientType.valueOf(rs.getString("type")), rs.getString("name"), rs.getString("surname"), rs.getString("email"), rs.getString("username"), rs.getString("password"), BigInteger.valueOf(rs.getInt("phoneNumber")));
                        return c1;
                    }else{
                        Company cc1 = new Company(ClientType.valueOf(rs.getString("type")), rs.getString("name"), rs.getString("email"), rs.getString("vat"), rs.getString("username"), rs.getString("password"), BigInteger.valueOf(rs.getInt("phoneNumber")));
                        return cc1;
                    }
                }
            }
        }catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static boolean checkMailDb(String mail) throws SQLException{
        try {
            Statement stmt = DbManagement.makeConnection();
            ResultSet rs = stmt.executeQuery("SELECT * FROM client WHERE email = '" + mail + "';");

            while (rs.next()){
                String checkMailRs = rs.getString("email");
                if(checkMailRs.equals(mail)){
                    System.out.println("Correct Mail");
                    return true;
                }
            }
        }catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }


    public static boolean checkPassword(String psw, Clients c) {
        if (c.getPassword().equals(psw)) {
            System.out.println("Correct Password");
            return true;
        }

        return false;
    }

    public static void whatType(Clients c1) {
        if (c1.getType().equals(ClientType.Customer)) {
            whichOperationCustomer.oper(c1);
        } else if (c1.getType().equals(ClientType.Company)) {
            //whichOperationCompany.oper(c1);
        }
    }




}
