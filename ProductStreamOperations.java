import java.util.*;
import java.util.stream.*;
import java.util.Map.Entry;

class Product {
    private String name;
    private double price;
    private String category;

    Product(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }

    @Override
    public String toString() {
        return String.format("%s (%.2f) - %s", name, price, category);
    }
}

public class ProductStreamOperations {
    public static void main(String[] args) {
        List<Product> products = List.of(
            new Product("Laptop", 75000, "Electronics"),
            new Product("Smartphone", 50000, "Electronics"),
            new Product("TV", 60000, "Electronics"),
            new Product("Shirt", 1500, "Clothing"),
            new Product("Jeans", 2000, "Clothing"),
            new Product("Blender", 3000, "Home Appliances"),
            new Product("Microwave", 8000, "Home Appliances")
        );

        // 1. Group by category
        Map<String, List<Product>> grouped = products.stream()
            .collect(Collectors.groupingBy(Product::getCategory));

        System.out.println("Products grouped by category:");
        grouped.forEach((cat, list) -> {
            System.out.println(cat + " -> " + list);
        });

        // 2. Most expensive product in each category
        Map<String, Optional<Product>> mostExpensive = products.stream()
            .collect(Collectors.groupingBy(
                Product::getCategory,
                Collectors.maxBy(Comparator.comparingDouble(Product::getPrice))
            ));

        System.out.println("\nMost expensive product in each category:");
        mostExpensive.forEach((cat, prod) ->
            System.out.println(cat + " -> " + prod.orElse(null))
        );

        // 3. Average price of all products
        double avgPrice = products.stream()
            .collect(Collectors.averagingDouble(Product::getPrice));

        System.out.printf("\nAverage price of all products: %.2f\n", avgPrice);
    }
}