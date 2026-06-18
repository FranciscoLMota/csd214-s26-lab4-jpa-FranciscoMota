package bookstore.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class CassetteTapeEntity extends PhysicalMusicFormatEntity {
    @Column(name = "has_auto_reverse", nullable = true)
    private boolean hasAutoReverse;

    public boolean getHasAutoReverse() {
        return hasAutoReverse;
    }

    public void setHasAutoReverse(boolean hasAutoReverse) {
        this.hasAutoReverse = hasAutoReverse;
    }

    @Override
    public String toString() {
        return "CassetteTapeEntity{" +
                super.toString() +
                "hasAutoReverse=" + hasAutoReverse +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CassetteTapeEntity that = (CassetteTapeEntity) o;
        return hasAutoReverse == that.hasAutoReverse;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(hasAutoReverse);
    }

    public CassetteTapeEntity() {
    }

    public CassetteTapeEntity(String productId, double playbackDurationMinutes, String title, String artist, String dateOfRelease, double price, int copies, boolean hasAutoReverse ) {
        super(productId, playbackDurationMinutes, title, artist, dateOfRelease, price, copies);
        this.hasAutoReverse = hasAutoReverse;
    }


}