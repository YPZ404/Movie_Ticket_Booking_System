import entity.*;
import org.junit.jupiter.api.*;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.matchers.JUnitMatchers.hasItems;

public class UserTest{
    @Test
    public void accountTest() {
        User user = new User();
        String account = "account";
        user.setAccount(account);
        assertEquals("account",user.getAccount());
    }

    @Test
    public void usernameTest() {
        User user = new User();
        String username = "username";
        user.setUsername(username);
        assertEquals("username",user.getUsername());
    }

    @Test
    public void pinTest() {
        User user = new User();
        String pin = "pin";
        user.setPin(pin);
        assertEquals("pin", user.getPin());
    }

    @Test
    public void authorityTest() {
        User user = new User();
        int Authority = 0;
        user.setAuthority(Authority);
        assertEquals(0,user.getAuthority());
    }

    @Test
    public void userIdTest() {
        User user = new User();
        String id = "0";
        user.setUserID(id);
        assertEquals("0", user.getUserid());
    }

    @Test
    public void cardNameTest() {
        User user = new User();
        String cardName = "0";
        user.setCardname(cardName);
        assertEquals("0", user.getCardname());
    }

    @Test
    public void cardNumberTest() {
        User user = new User();
        String cardNumber = "0";
        user.setCardnumber(cardNumber);
        assertEquals("0", user.getCardnumber());
    }

}