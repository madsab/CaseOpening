package exampleproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import caseOpening.logIn.User;

public class UserTest {
    @Test
    public void testConstructor(){
        assertThrows(IllegalArgumentException.class, () -> new User(null, null));
        
        User validUser = new User("GeirKL", "Mongo123");
        
    }
}
