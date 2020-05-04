package System.test;

import domain.Developer;
import org.junit.Test;
import System.App;

import static org.junit.Assert.*;

    public class WhiteBoxTest {
        App app = new App();

        @Test
        public void testInputDataSetA() {
            assertNull(app.getActiveDeveloper());
        }
        @Test
        public void testInputDataSetB() {
            app.registerDeveloper(new Developer("Hans","Hansen"));
            app.setActiveDeveloper("HAHA01");
            assertEquals("HAHA01",app.getActiveDeveloper().getID());
        }

    }
