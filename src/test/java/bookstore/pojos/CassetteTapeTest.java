package bookstore.pojos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CassetteTapeTest {
    @Test
    void TestConstructor() {
        CassetteTape cassette = new CassetteTape("After Laughter", "2017-05-12", "Paramore", 42.5, 60.0,11, true );
        IO.println(cassette.toString());

        assertEquals("After Laughter", cassette.getTitle());
        assertEquals("2017-05-12", cassette.getDateOfRelease());
        assertEquals("Paramore", cassette.getArtist());
        assertEquals(42.5, cassette.getPlaybackDurationMinutes());
        assertEquals(60.0, cassette.getPrice());
        assertEquals(11 , cassette.getCopies());
        assertTrue(cassette.getHasAutoReverse());
    }

    @Test
    void testSellItemDecrementsCopies() {
        CassetteTape cassette = new CassetteTape("After Laughter", "2017-05-12", "Paramore", 42.5, 60.0,5, true );
        IO.println(cassette.toString());

        cassette.sellItem();
        assertEquals(4, cassette.getCopies());
    }

}
