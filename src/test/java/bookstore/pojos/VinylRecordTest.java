package bookstore.pojos;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.sql.Date;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VinylRecordTest {
    @Test
    void TestConstructor() {
        VinylRecord vinyl = new VinylRecord("After Laughter", "2017-05-12", "Paramore", 42.5, 60.0,11,12, 33 );
        IO.println(vinyl.toString());

        assertEquals("After Laughter", vinyl.getTitle());
        assertEquals("2017-05-12", vinyl.getDateOfRelease());
        assertEquals("Paramore", vinyl.getArtist());
        assertEquals(42.5, vinyl.getPlaybackDurationMinutes());
        assertEquals(60.0, vinyl.getPrice());
        assertEquals(11 , vinyl.getCopies());
        assertEquals(12, vinyl.getRecordSizeInches());
        assertEquals(33, vinyl.getRotationsPerMinute());
    }

    @Test
    void testSellItemDecrementsCopies() {
        VinylRecord vinyl = new VinylRecord("After Laughter", "2017-05-12", "Paramore", 42.5, 60.0,5,12, 33 );
        IO.println(vinyl.toString());

        vinyl.sellItem();
        assertEquals(4, vinyl.getCopies());
    }

}
