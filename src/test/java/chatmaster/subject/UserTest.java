package chatmaster.subject;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by andgra on 2015-01-26.
 */
public class UserTest {
    @Test
    public void userTest(){
        String id = "123";
        String nickname = "poker";
        String email = "poker@gmail.com";

        User user = new User(id, nickname, email);
        assertEquals(id, user.id());
        assertEquals(nickname, user.nickname());
        assertEquals(email, user.email());

    }
}
