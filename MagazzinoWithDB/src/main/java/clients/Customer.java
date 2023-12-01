package clients;

import java.math.BigInteger;

public class Customer extends Clients{
    private String surname;
    public Customer(ClientType type, String name,String surname ,String email, String username, String password, BigInteger numTel) {
        super(type, name, email, username, password, numTel);
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
