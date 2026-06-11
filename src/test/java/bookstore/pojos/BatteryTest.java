package bookstore.pojos;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BatteryTest {

    private Scanner createMockScanner(String input) {
        return new Scanner(new ByteArrayInputStream(input.getBytes()));
    }

    @Test
    void TestConstructor() {
       Battery battery = new Battery("East Penn Canada", 250.0, 10, 24);
       IO.println(battery.toString());

       assertEquals("East Penn Canada", battery.getManufacturer());
       assertEquals(250.0, battery.getPrice());
       assertEquals(10, battery.getCopies());
       assertEquals(24, battery.getColdCrankingAmps());
    }

    @Test
    void testSellItemDecrementsCopies() {
        Battery battery = new Battery("East Penn Canada", 250.0, 10, 24);
        IO.println(battery.toString());
        battery.sellItem();
        assertEquals(9, battery.getCopies());
    }
}
