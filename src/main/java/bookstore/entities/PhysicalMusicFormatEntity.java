package bookstore.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class PhysicalMusicFormatEntity extends ProductEntity {
    @Column(name = "playback_duration_minutes", nullable = true)
    private double playbackDurationMinutes;

    @Column(name = "title", nullable = true)
    private String title;

    @Column(name = "artist", nullable = true)
    private String artist;

    @Column(name = "date_of_release", nullable = true)
    private String dateOfRelease;

    @Column(name = "price", nullable = true)
    private double price;

    @Column(name = "copies", nullable = true)
    private int copies;


    public double getPlaybackDurationMinutes() {
        return playbackDurationMinutes;
    }

    public void setPlaybackDurationMinutes(double playbackDurationMinutes) {
        this.playbackDurationMinutes = playbackDurationMinutes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getDateOfRelease() {
        return dateOfRelease;
    }

    public void setDateOfRelease(String dateOfRelease) {
        this.dateOfRelease = dateOfRelease;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public PhysicalMusicFormatEntity(String productId, double playbackDurationMinutes, String title, String artist, String dateOfRelease, double price, int copies) {
        super(productId);
        this.playbackDurationMinutes = playbackDurationMinutes;
        this.title = title;
        this.artist = artist;
        this.dateOfRelease = dateOfRelease;
        this.price = price;
        this.copies = copies;
    }

    public PhysicalMusicFormatEntity(double playbackDurationMinutes, String title, String artist, String dateOfRelease, double price, int copies) {
        this.playbackDurationMinutes = playbackDurationMinutes;
        this.title = title;
        this.artist = artist;
        this.dateOfRelease = dateOfRelease;
        this.price = price;
        this.copies = copies;
    }

    public PhysicalMusicFormatEntity() {
    }

    @Override
    public String toString() {
        return "PhysicalMusicFormatEntity{" +
                "playbackDurationMinutes=" + playbackDurationMinutes +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", dateOfRelease='" + dateOfRelease + '\'' +
                ", price=" + price +
                ", copies=" + copies +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PhysicalMusicFormatEntity that = (PhysicalMusicFormatEntity) o;
        return Double.compare(playbackDurationMinutes, that.playbackDurationMinutes) == 0 && Double.compare(price, that.price) == 0 && copies == that.copies && Objects.equals(title, that.title) && Objects.equals(artist, that.artist) && Objects.equals(dateOfRelease, that.dateOfRelease);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playbackDurationMinutes, title, artist, dateOfRelease, price, copies);
    }
}