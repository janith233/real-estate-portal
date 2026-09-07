package real_estate_portal.model;

public class Apartment extends Property {

    private int floorNumber;

    public Apartment(int id, String title, String location, double price,
                     String description, int floorNumber) {

        super(id, title, location, price, "Apartment", description);

        this.floorNumber = floorNumber;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    @Override
    public String getPropertyInfo() {
        return getTitle() + " - Apartment on floor " + floorNumber;
    }
}