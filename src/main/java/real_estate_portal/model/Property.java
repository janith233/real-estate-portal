package real_estate_portal.model;

public class Property {

    private int id;
    private String title;
    private String location;
    private double price;
    private String propertyType;
    private String description;

    public Property(int id, String title, String location, double price,
                    String propertyType, String description) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.price = price;
        this.propertyType = propertyType;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPropertyInfo() {
        return title + " - " + propertyType;
    }
}