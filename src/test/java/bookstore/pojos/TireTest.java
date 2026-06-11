package bookstore.pojos;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TireTest {

    private Scanner createMockScanner(String input) {
        return new Scanner(new ByteArrayInputStream(input.getBytes()));
    }

    @Test
    void TestConstructor() {
       Tire tire = new Tire("Michelan", 250.0, 10, 24);
        IO.println(tire.toString());

        assertEquals("Michelan", tire.getManufacturer());
        assertEquals(250.0, tire.getPrice());
        assertEquals(10, tire.getCopies());
        assertEquals(24, tire.getDiameter());
    }

    @Test
    void testSellItemDecrementsCopies() {
        Tire tire = new Tire("Michelan", 250.0, 5, 24);
        IO.println(tire.toString());

        tire.sellItem();
        assertEquals(4, tire.getCopies());
    }
}
