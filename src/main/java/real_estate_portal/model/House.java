package real_estate_portal.model;

public class House extends Property {

    private int numberOfBedrooms;

    public House(int id, String title, String location, double price,
                 String description, int numberOfBedrooms) {

        super(id, title, location, price, "House", description);

        this.numberOfBedrooms = numberOfBedrooms;
    }

    public int getNumberOfBedrooms() {
        return numberOfBedrooms;
    }

    public void setNumberOfBedrooms(int numberOfBedrooms) {
        this.numberOfBedrooms = numberOfBedrooms;
    }

    @Override
    public String getPropertyInfo() {
        return getTitle() + " - House with " + numberOfBedrooms + " bedrooms";
    }
}
