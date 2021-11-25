import entity.*;
import org.junit.jupiter.api.*;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.matchers.JUnitMatchers.hasItems;
public class ShowTest {
    String showID="showID";
    String filmName="filmName";
    String screensize="screensize";
    String location="location";
    String type="type";
    String time="time";
    int front=1;
    int middle=1;
    int rear=1;
    float price=1;
    String director="director";
    String casts="casts";
    String film_synopsis="film_synopsis";
    Show show=new Show();
    @Test
    public void idTest(){
        show.setShowID(showID);
        assertEquals("showID", show.getShowID());
    }
    @Test
    public void nameTest(){
        show.setFilmName(filmName);
        assertEquals("filmName",show.getFilmName());
    }
    @Test
    public void setScreensizeTest(){
        show.setScreensize(screensize);
        assertEquals(screensize,show.getScreensize());
    }
    @Test
    public void setTypeTest(){
        show.setType(type);
        assertEquals(type,show.getType());
    }
    @Test
    public void setTimeTest(){
        show.setTime(time);
        assertEquals(time,show.getTime());
    }
    @Test
    public void Test(){
        show.setLocation(location);
        assertEquals(location,show.getLocation());
    }
    @Test
    public void frontTest(){
        show.setFront(front);
        assertEquals(1,show.getFront());
    }
    @Test
    public void setMiddleTest(){
        show.setMiddle(middle);
        assertEquals(middle,show.getMiddle());
    }
    @Test
    public void setRearTest(){
        show.setRear(10);
        assertEquals(10, show.getRear());
    }
    @Test
    public void setPriceTest(){
        show.setPrice(price);
        assertEquals(price,show.getPrice());
    }
    @Test
    public void setDirectorTest(){
        show.setDirector(director);
        assertEquals(director,show.getDirector());
    }
    @Test
    public void setCastsTest(){
        show.setCasts(casts);
        assertEquals(casts,show.getCasts());
    }
    @Test
    public void setFilm_synopsisTest(){
        show.setFilm_synopsis(film_synopsis);
        assertEquals(film_synopsis,show.getFilm_synopsis());
    }

}
