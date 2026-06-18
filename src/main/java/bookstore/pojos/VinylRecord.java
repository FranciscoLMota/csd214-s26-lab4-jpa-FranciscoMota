package bookstore.pojos;

import bookstore.entities.BookEntity;
import bookstore.entities.VinylRecordEntity;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

/**
 * DTO for {@link bookstore.entities.VinylRecordEntity}
 */
public class VinylRecord extends PhysicalMusicFormat{
    private int recordSizeInches = 0;
    private int rotationsPerMinute = 0;

    public VinylRecord() {}

    public VinylRecord(String title, String dateOfRelease, String artist, double playbackDurationMinutes, double price, int copies, int recordSizeInches, int rotationsPerMinute) {
        super(title, dateOfRelease, artist, playbackDurationMinutes, price, copies);
        this.recordSizeInches = recordSizeInches;
        this.rotationsPerMinute = rotationsPerMinute;
    }

    public int getRecordSizeInches() {
        return recordSizeInches;
    }

    public void setRecordSizeInches(int recordSizeInches) {
        this.recordSizeInches = recordSizeInches;
    }

    public int getRotationsPerMinute() {
        return rotationsPerMinute;
    }

    public void setRotationsPerMinute(int rotationsPerMinute) {
        this.rotationsPerMinute = rotationsPerMinute;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        VinylRecord that = (VinylRecord) o;
        return recordSizeInches == that.recordSizeInches && rotationsPerMinute == that.rotationsPerMinute;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), recordSizeInches, rotationsPerMinute);
    }

    @Override
    public String toString() {
        return "VinylRecord{" +
                "recordSizeInches=" + recordSizeInches +
                ", rotationsPerMinute=" + rotationsPerMinute + ", " + super.toString() +
                '}';
    }

    @Override
    public void edit(Scanner input) {
        super.edit(input);

        System.out.println("Enter the size of the record (Inches) [" + this.recordSizeInches + "]:" );
        this.recordSizeInches = getInput(input, this.recordSizeInches);

        System.out.println("Enter the rotations Per Minute [" + this.rotationsPerMinute + "]:" );
        this.rotationsPerMinute = getInput(input, this.rotationsPerMinute);

    }

    @Override
    public void initialize(Scanner input) {
        super.initialize(input);

        System.out.println("Enter the size of the record (Inches):" );
        this.recordSizeInches = getInput(input, 0);

        System.out.println("Enter the rotations Per Minute:" );
        this.rotationsPerMinute = getInput(input, 0);

    }

    @Override
    public void sellItem() {
        super.sellItem();
        System.out.println("Selling Vinyl Record:  " + getTitle() + " by " + getArtist() + " for " + getPrice());
        IO.println("Remaining stock:" + getCopies());
    }

    // Mapping: DTO to Database Entity
    public VinylRecordEntity toEntity() {
        VinylRecordEntity entity = new VinylRecordEntity();

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

        //Vinyl
        entity.setRecordSizeInches(this.getRecordSizeInches());
        entity.setRotationsPerMinute(this.getRotationsPerMinute());

        return entity;
    }

    // Mapping: Database Entity to DTO
    public static VinylRecord fromEntity(VinylRecordEntity entity) {
        VinylRecord vinyl = new VinylRecord(
                entity.getTitle(),
                entity.getDateOfRelease(),
                entity.getArtist(),
                entity.getPlaybackDurationMinutes(),
                entity.getPrice(),
                entity.getCopies(),
                entity.getRecordSizeInches(),
                entity.getRotationsPerMinute()
        );

        vinyl.setDbId(entity.getId());
        vinyl.setProductId(entity.getProductId());
        return vinyl;
    }
}
