import entity.*;
import org.junit.jupiter.api.*;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.matchers.JUnitMatchers.hasItems;

public class BookTest {

    ArrayList user= new ArrayList<Integer>();
    ArrayList seat= new ArrayList<Integer>();
    float price =1;
    String filename= "filename";
    String location ="location";
    String showID= "showID";
    Book b=new Book();
    @Test
    public void showIDTest(){
        b.setShowID(showID);
        assertEquals("showID",b.getShowID());
    }
    @Test
    public void LocationTest(){
        b.setLocation(location);
        assertEquals("location",b.getLocation());
    }
    @Test
    public void filmnameTest(){
        b.setFilmname(filename);
        assertEquals("filename",b.getFilmname());
    }
    @Test
    public void priceTest(){
        b.setPrice(price);
        assertEquals(price,b.getPrice());
    }
    @Test
    public void seatsTest(){
        b.setSeats(seat);
        assertEquals(seat,b.getSeats());
    }
    @Test
    public void userTest(){
        b.setUser(user);
        assertEquals(user,b.getUser());
    }

}
