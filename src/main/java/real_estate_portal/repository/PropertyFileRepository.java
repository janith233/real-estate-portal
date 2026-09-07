package real_estate_portal.repository;

import real_estate_portal.model.Property;
import real_estate_portal.model.House;
import real_estate_portal.model.Apartment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class PropertyFileRepository {
    public List<Property> getAllProperties() {
        List<Property> properties = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("data/properties.txt"))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\\|");

                if (data.length == 6 || data.length == 7) {
                    int id = Integer.parseInt(data[0]);
                    String title = data[1];
                    String location = data[2];
                    double price = Double.parseDouble(data[3]);
                    String propertyType = data[4];
                    String description = data[5];
                    int extraValue = 0;

                    if (data.length == 7) {
                        extraValue = Integer.parseInt(data[6]);
                    }

                    Property property;

                    if (propertyType.equals("House")) {

                        property = new House(
                                id,
                                title,
                                location,
                                price,
                                description,
                                extraValue);

                    } else if (propertyType.equals("Apartment")) {

                        property = new Apartment(
                                id,
                                title,
                                location,
                                price,
                                description,
                                extraValue);
                    } else {

                        property = new Property(
                                id,
                                title,
                                location,
                                price,
                                propertyType,
                                description);
                    }

                    properties.add(property);
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading property file: " + e.getMessage());
        }

        return properties;
    }

    public void addProperty(Property property) {
        try (FileWriter writer = new FileWriter("data/properties.txt", true)) {

            writeProperty(writer, property);

        } catch (IOException e) {
            System.out.println("Error writing property to file: " + e.getMessage());
        }
    }

    public void updateProperty(Property updatedProperty) {
        List<Property> properties = getAllProperties();

        try (FileWriter writer = new FileWriter("data/properties.txt")) {

            for (Property property : properties) {

                if (property.getId() == updatedProperty.getId()) {
                    property = updatedProperty;
                }

                writeProperty(writer, property);
            }

        } catch (IOException e) {
            System.out.println("Error updating property: " + e.getMessage());
        }
    }

    public void deleteProperty(int id) {
        List<Property> properties = getAllProperties();

        try (FileWriter writer = new FileWriter("data/properties.txt")) {

            for (Property property : properties) {

                if (property.getId() != id) {
                    writeProperty(writer, property);
                }
            }

        } catch (IOException e) {
            System.out.println("Error deleting property: " + e.getMessage());
        }
    }

    public List<Property> searchProperties(String keyword) {

        List<Property> properties = getAllProperties();
        List<Property> results = new ArrayList<>();

        for (Property property : properties) {

            if (property.getTitle().toLowerCase().contains(keyword.toLowerCase())
                    || property.getLocation().toLowerCase().contains(keyword.toLowerCase())
                    || property.getPropertyType().toLowerCase().contains(keyword.toLowerCase())) {

                results.add(property);
            }
        }

        return results;
    }

    private void writeProperty(FileWriter writer, Property property) throws IOException {

        writer.write(
                property.getId() + "|" +
                        property.getTitle() + "|" +
                        property.getLocation() + "|" +
                        property.getPrice() + "|" +
                        property.getPropertyType() + "|" +
                        property.getDescription());

        if (property instanceof House) {
            House house = (House) property;
            writer.write("|" + house.getNumberOfBedrooms());

        } else if (property instanceof Apartment) {
            Apartment apartment = (Apartment) property;
            writer.write("|" + apartment.getFloorNumber());
        }

        writer.write(System.lineSeparator());
    }
}
