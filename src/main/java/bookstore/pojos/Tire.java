package bookstore.pojos;

import java.util.Objects;
import java.util.Scanner;

public class Tire extends VehiclePart{
    private  int diameter = 0;
    public Tire(String manufacturer, double price, int stock, int diameter) {
        super(manufacturer, price, stock);
        this.diameter = diameter;
    }

    public Tire() {

    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Tire tire = (Tire) o;
        return diameter == tire.diameter;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), diameter);
    }

    @Override
    public String toString() {
        return "Tire{" +
                "diameter=" + diameter + ", " + super.toString() +
                '}';
    }

    @Override
    public void initialize(Scanner input) {
        super.initialize(input);

        System.out.println("Enter diameter:");
        this.diameter = getInput(input, 0);

    }

    @Override
    public void edit(Scanner input) {
        super.edit(input);

        System.out.println("Edit Diameter [" + this.diameter + "]:");
        this.diameter = getInput(input, this.diameter);

    }


    @Override
    public void sellItem() {
        super.sellItem();
        System.out.println("Selling Tire: Diameter " + diameter + " by " + getManufacturer() + " for " + getPrice());
        IO.println("Remaining stock:" + getCopies());
    }
}
