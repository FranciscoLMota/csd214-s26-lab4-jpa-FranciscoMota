package bookstore.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class VinylRecordEntity extends PhysicalMusicFormatEntity {
    @Column(name = "record_size_inches", nullable = true)
    private int recordSizeInches;

    @Column(name = "rotations_per_minute", nullable = true)
    private int rotationsPerMinute;

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

    public VinylRecordEntity() {
    }

    public VinylRecordEntity(String productId, double playbackDurationMinutes, String title, String artist, String dateOfRelease, double price, int copies, int recordSizeInches, int rotationsPerMinute ) {
        super(productId, playbackDurationMinutes, title, artist, dateOfRelease, price, copies);
        this.recordSizeInches = recordSizeInches;
        this.rotationsPerMinute = rotationsPerMinute;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        VinylRecordEntity that = (VinylRecordEntity) o;
        return recordSizeInches == that.recordSizeInches && rotationsPerMinute == that.rotationsPerMinute;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), recordSizeInches, rotationsPerMinute);
    }

    @Override
    public String toString() {
        return "VinylRecordEntity{" +
                super.toString() +
                ", recordSizeInches=" + recordSizeInches +
                ", rotationsPerMinute=" + rotationsPerMinute +
                '}';
    }
}