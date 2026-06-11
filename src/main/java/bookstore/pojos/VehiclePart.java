package bookstore.pojos;

import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public abstract class VehiclePart extends Product {
    private String manufacturer = "";
    private double price = 0.0;
    private int copies = 0;


    public VehiclePart() {}

    public VehiclePart(String manufacturer, double price, int copies) {
        this.manufacturer = manufacturer;
        this.price = price;
        this.copies = copies;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public double getPrice() {
        return this.price;
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
        VehiclePart that = (VehiclePart) o;
        return Double.compare(price, that.price) == 0 && Objects.equals(manufacturer, that.manufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(manufacturer, price);
    }


    @Override
    public String toString() {
        return "VehiclePart{" +
                "manufacturer='" + manufacturer + '\'' +
                ", price=" + price +
                ", copies=" + copies +
                '}';
    }

    @Override
    public void initialize(Scanner input) {
        System.out.println("Enter Manufacturer:");
        this.manufacturer = getInput(input, "Manufacturer");

        System.out.println("Enter price:");
        this.price = getInput(input, 0.0);

        System.out.println("Enter copies:");
        this.copies = getInput(input, 0);
    }


    @Override
    public void edit(Scanner input) {
        System.out.println("Edit Manufacturer [" + this.manufacturer + "]:");
        this.manufacturer = getInput(input, this.manufacturer);

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
