package bookstore.pojos;

import java.util.Objects;
import java.util.Scanner;

public class Battery extends VehiclePart {
    private int coldCrankingAmps = 0;

    public Battery() {
    }

    public Battery(String manufacturer, double price, int stock, int coldCrankingAmps) {
        super(manufacturer, price, stock);
        this.coldCrankingAmps = coldCrankingAmps;
    }

    public int getColdCrankingAmps() {
        return coldCrankingAmps;
    }

    public void setColdCrankingAmps(int coldCrankingAmps) {
        this.coldCrankingAmps = coldCrankingAmps;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Battery battery = (Battery) o;
        return coldCrankingAmps == battery.coldCrankingAmps;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), coldCrankingAmps);
    }

    @Override
    public String toString() {
        return "Battery{" +
                "coldCrankingAmps=" + coldCrankingAmps + ", " + super.toString() +
                '}';
    }

    @Override
    public void edit(Scanner input) {
        super.edit(input);

        System.out.println("Edit coldCrankingAmps [" + this.coldCrankingAmps + "]:");
        this.coldCrankingAmps = getInput(input, this.coldCrankingAmps);
    }

    @Override
    public void initialize(Scanner input) {
        super.initialize(input);

        System.out.println("Enter coldCrankingAmps:");
        this.coldCrankingAmps = getInput(input, 0);
    }

    @Override
    public void sellItem() {
        super.sellItem();
        System.out.println("Selling Tire: coldCrankingAmps " + coldCrankingAmps + " by " + getManufacturer() + " for " + getPrice());
        IO.println("Remaining stock:" + getCopies());
    }
}
