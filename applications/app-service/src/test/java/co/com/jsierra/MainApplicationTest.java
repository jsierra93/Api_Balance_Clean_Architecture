package co.com.jsierra;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class MainApplicationTest {

    @Test
    public void main() {
        MainApplication.main(new String[] {});
    }

    @Test(expected = IllegalArgumentException.class)
    public void mainNull() {
        MainApplication.main(null);
    }

}
