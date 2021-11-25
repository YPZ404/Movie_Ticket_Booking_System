package entity;

public class User {
    private String userID;
    private String account;
    private String username;
    private String pin;
    private int authority;
    private String cardname;
    private String cardnumber;

    public User(){}

//    public String getUserid() {
//        return userID;
//    }

    public int getAuthority() {
        return authority;
    }

    public String getAccount() {
        return account;
    }

    public String getPin() {
        return pin;
    }

    public String getUsername() {
        return username;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserid(){return userID;}

    public String getCardname() {
        return cardname;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardname(String cardname) {
        this.cardname = cardname;
    }
}
