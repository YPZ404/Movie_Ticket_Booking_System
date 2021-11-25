import entity.*;
import org.junit.jupiter.api.*;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.matchers.JUnitMatchers.hasItems;
public class FilmTest {
    int filmID=1;
    String filmName="filmname";
    String synopis="synopsis";
    String filmClassification="filmClassification";
    String releaseDate= "releaseDate";
    String director="director";
    String casts="casts";
    String comingDate="comingDate";
    Film film= new Film();
    @Test
    public void idtest(){
        film.setFilmID(filmID);
        assertEquals(1,film.getFilmID());
    }
    @Test
    public void nametest(){
        film.setFilmName(filmName);
        assertEquals("filmname",film.getFilmName());
    }
    @Test
    public void syntest(){
        film.setSynopsis(synopis);
        assertEquals("synopsis",film.getSynopsis());
    }
    @Test
    public void classtest(){
        film.setFilmClassification(filmClassification);
        assertEquals("filmClassification",film.getFilmClassification());
    }
    @Test
    public void datetest(){
        film.setReleaseDate(releaseDate);
        assertEquals("releaseDate",film.getReleaseDate());
    }
    @Test
    public void setDirectortest(){
        film.setDirector(director);
        assertEquals("director",film.getDirector());
    }
    @Test
    public void setCaststest(){
        film.setCasts(casts);
        assertEquals("casts",film.getCasts());
    }
    @Test
    public void setComingDateTest(){
        film.setComingDate(comingDate);
        assertEquals("comingDate",film.getComingDate());
    }
}

