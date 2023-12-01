package clients;

import java.math.BigInteger;

public class Company extends Clients{
    private String pIva;

    public Company(ClientType type, String name, String email,String pIva, String username, String password, BigInteger numTel) {
        super(type, name, email, username, password, numTel);
        this.pIva = pIva;
    }

    public String getpIva() {
        return pIva;
    }

    public void setpIva(String pIva) {
        this.pIva = pIva;
    }

}
