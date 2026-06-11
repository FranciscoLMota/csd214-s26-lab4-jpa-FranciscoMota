package bookstore.pojos;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public class CassetteTape extends PhysicalMusicFormat {
    private boolean hasAutoReverse = false;

    public CassetteTape(String title, String dateOfRelease, String artist, double playbackDurationMinutes, double price, int copies,  boolean hasAutoReverse) {
        super(title, dateOfRelease, artist, playbackDurationMinutes, price, copies);
        this.hasAutoReverse = hasAutoReverse;
    }

    public CassetteTape() {

    }

    public boolean getHasAutoReverse() {
        return hasAutoReverse;
    }

    public void setHasAutoReverse(boolean hasAutoReverse) {
        this.hasAutoReverse = hasAutoReverse;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CassetteTape that = (CassetteTape) o;
        return hasAutoReverse == that.hasAutoReverse;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), hasAutoReverse);
    }

    @Override
    public String toString() {
        return "CassetteTape{" +
                "hasAutoReverse=" + hasAutoReverse + ", " + super.toString() +
                '}';
    }

    @Override
    public void edit(Scanner input) {
        super.edit(input);

        System.out.println("Enter hasAutoReverse [" + this.hasAutoReverse + "]:" );
        this.hasAutoReverse = getInput(input, false);
    }

    @Override
    public void initialize(Scanner input) {
        super.initialize(input);

        System.out.println("Enter hasAutoReverse:");
        this.hasAutoReverse = getInput(input, this.hasAutoReverse);
    }

    @Override
    public void sellItem() {
        super.sellItem();
        System.out.println("Selling Cassette:  " + getTitle() + " by " + getArtist() + " for " + getPrice());
        IO.println("Remaining stock:" + getCopies());
    }
}
