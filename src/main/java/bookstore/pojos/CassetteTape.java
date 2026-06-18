package bookstore.pojos;

import bookstore.entities.CassetteTapeEntity;
import bookstore.entities.VinylRecordEntity;

import java.util.Objects;
import java.util.Scanner;

/**
 * DTO for {@link CassetteTapeEntity}
 */
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

    // Mapping: DTO to Database Entity
    public CassetteTapeEntity toEntity() {
        CassetteTapeEntity entity = new CassetteTapeEntity();

        //Product
        entity.setId(this.getDbId());
        entity.setProductId(this.getProductId());

        //PhysicalMediaFormat
        entity.setPlaybackDurationMinutes(this.getPlaybackDurationMinutes());
        entity.setTitle(this.getTitle());
        entity.setArtist(this.getArtist());
        entity.setDateOfRelease(this.getDateOfRelease());
        entity.setPrice(this.getPrice());
        entity.setCopies(this.getCopies());

        //Cassette
        entity.setHasAutoReverse(this.getHasAutoReverse());

        return entity;
    }

    // Mapping: Database Entity to DTO
    public static CassetteTape fromEntity(CassetteTapeEntity entity) {
        CassetteTape cassette = new CassetteTape(
                entity.getTitle(),
                entity.getDateOfRelease(),
                entity.getArtist(),
                entity.getPlaybackDurationMinutes(),
                entity.getPrice(),
                entity.getCopies(),
                entity.getHasAutoReverse()
        );

        cassette.setDbId(entity.getId());
        cassette.setProductId(entity.getProductId());
        return cassette;
    }

}
