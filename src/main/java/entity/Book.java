package entity;
import java.util.ArrayList;
import java.util.Date;

public class Book {
    private ArrayList<Integer> user;
    private ArrayList<Integer> seats;
    private float price;
    private String filmname;
    private String location;
    private String showID;

    public void setShowID(String showID) {
        this.showID = showID;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getShowID() {
        return showID;
    }

    public String getLocation() {
        return location;
    }

    public float getPrice() {
        return price;
    }

    public String getFilmname() {
        return filmname;
    }

    public void setFilmname(String filmname) {
        this.filmname = filmname;
    }

    public ArrayList<Integer> getSeats() {
        return seats;
    }

    public ArrayList<Integer> getUser() {
        return user;
    }

    public void setSeats(ArrayList<Integer> seats) {
        this.seats = seats;
    }

    public void setUser(ArrayList<Integer> user) {
        this.user = user;
    }
}
