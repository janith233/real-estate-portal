package real_estate_portal.service;

import real_estate_portal.model.Property;
import real_estate_portal.repository.PropertyFileRepository;

import java.util.List;
import java.util.ArrayList;

public class PropertyService {

    private PropertyFileRepository repository;

    public PropertyService() {
        repository = new PropertyFileRepository();
    }

    public List<Property> getAllProperties() {
        return repository.getAllProperties();
    }

    public Property getPropertyById(int id) {
        List<Property> properties = repository.getAllProperties();

        for (Property property : properties) {
            if (property.getId() == id) {
                return property;
            }
        }

        return null;
    }

    public void addProperty(Property property) {
        List<Property> properties = repository.getAllProperties();

        int nextId = 1;

        for (Property existingProperty : properties) {
            if (existingProperty.getId() >= nextId) {
                nextId = existingProperty.getId() + 1;
            }
        }

        property.setId(nextId);

        repository.addProperty(property);
    }

    public void updateProperty(Property property) {
        repository.updateProperty(property);
    }

    public void deleteProperty(int id) {
        repository.deleteProperty(id);
    }

    public List<Property> sortProperties(List<Property> properties, String sortBy) {

        if (sortBy.equals("priceLow")) {

            properties.sort((property1, property2) ->
                    Double.compare(property1.getPrice(), property2.getPrice()));

        } else if (sortBy.equals("priceHigh")) {

            properties.sort((property1, property2) ->
                    Double.compare(property2.getPrice(), property1.getPrice()));

        } else if (sortBy.equals("title")) {

            properties.sort((property1, property2) ->
                    property1.getTitle().compareToIgnoreCase(property2.getTitle()));
        }

        return properties;
    }

    public List<Property> filterProperties(
            String keyword,
            String propertyType,
            String minPrice,
            String maxPrice) {

        List<Property> properties = repository.getAllProperties();
        List<Property> results = new ArrayList<>();

        for (Property property : properties) {

            boolean matchesKeyword = keyword.isEmpty()
                    || property.getTitle().toLowerCase().contains(keyword.toLowerCase())
                    || property.getLocation().toLowerCase().contains(keyword.toLowerCase())
                    || property.getPropertyType().toLowerCase().contains(keyword.toLowerCase());

            boolean matchesType = propertyType.isEmpty()
                    || property.getPropertyType().equalsIgnoreCase(propertyType);

            boolean matchesMinPrice = minPrice.isEmpty()
                    || property.getPrice() >= Double.parseDouble(minPrice);

            boolean matchesMaxPrice = maxPrice.isEmpty()
                    || property.getPrice() <= Double.parseDouble(maxPrice);

            if (matchesKeyword
                    && matchesType
                    && matchesMinPrice
                    && matchesMaxPrice) {

                results.add(property);
            }
        }

        return results;
    }
}