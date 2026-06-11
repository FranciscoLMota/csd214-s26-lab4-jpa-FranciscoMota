package bookstore.pojos;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public abstract class PhysicalMusicFormat extends Product {
    private double playbackDurationMinutes;
    private String title;
    private String artist;
    private String dateOfRelease;
    private double price;
    private int copies;

    public PhysicalMusicFormat() {}

    public PhysicalMusicFormat(String title, String dateOfRelease, String artist, double playbackDurationMinutes, double price, int copies) {
        this.title = title;
        this.dateOfRelease = dateOfRelease;
        this.artist = artist;
        this.playbackDurationMinutes = playbackDurationMinutes;
        this.price = price;
        this.copies = copies;
    }

    public double getPlaybackDurationMinutes() {
        return playbackDurationMinutes;
    }

    public void setPlaybackDurationMinutes(double playbackDurationMinutes) {
        this.playbackDurationMinutes = playbackDurationMinutes;
    }

    public String getartist() {
        return artist;
    }

    public void setartist(String artist) {
        this.artist = artist;
    }

    public String getDateOfRelease() {
        return dateOfRelease;
    }

    public void setDateOfRelease(String dateOfRelease) {
        this.dateOfRelease = dateOfRelease;
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

    @Override
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PhysicalMusicFormat that = (PhysicalMusicFormat) o;
        return playbackDurationMinutes == that.playbackDurationMinutes && Objects.equals(artist, that.artist) && Objects.equals(dateOfRelease, that.dateOfRelease);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playbackDurationMinutes, artist, dateOfRelease);
    }

    @Override
    public String toString() {
        return "PhysicalMusicFormat{" +
                "playbackDurationMinutes=" + playbackDurationMinutes +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", dateOfRelease=" + dateOfRelease +
                ", price=" + price +
                ", copies=" + copies +
                '}';
    }

    @Override
    public void initialize(Scanner input) {
        System.out.println("Enter title:");
        this.title = getInput(input, "title");

        System.out.println("Enter Artist:");
        this.artist = getInput(input, "artist");

        System.out.println("Enter duration of playback (in Minutes):");
        this.playbackDurationMinutes = getInput(input, 0.0);

        System.out.println("Enter Release Date (dd-MM-yyyy):");
        this.dateOfRelease = getInput(input, "01-01-1999");

        System.out.println("Enter price:");
        this.price = getInput(input, 0.0);

        System.out.println("Enter copies:");
        this.copies = getInput(input, 0);
    }

    @Override
    public void edit(Scanner input) {
        System.out.println("Edit title [" + this.title + "]:");
        this.title = getInput(input, this.title);

        System.out.println("Edit artist [" + this.artist + "]:");
        this.artist = getInput(input, this.artist);

        System.out.println("Edit duration of playback (in Minutes) [" + this.playbackDurationMinutes + "]:");
        this.playbackDurationMinutes = getInput(input, this.playbackDurationMinutes);

        System.out.println("Edit date of release [" + this.dateOfRelease + "]:");
        this.dateOfRelease = getInput(input, this.dateOfRelease);

        System.out.println("Edit Price [" + this.price + "]:");
        this.price = getInput(input, this.price);

        System.out.println("Edit Copies [" + this.copies + "]:");
        this.copies = getInput(input, this.copies);
    }

    @Override
    public void sellItem() {
        this.setCopies(this.getCopies() - 1);
    }
}
