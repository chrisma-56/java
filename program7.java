import java.util.*;

// Customer class 
class Customer {
    private int id;
    private String name;
    private int loyaltyPoints;

    public Customer(int id, String name, int loyaltyPoints) {
        this.id = id;
        this.name = name;
        this.loyaltyPoints = loyaltyPoints;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void updateLoyaltyPoints(int points) {
        this.loyaltyPoints = points;
    }

    
    public String toString() {
        return "Customer{id=" + id + ", name='" + name + '\'' + ", loyaltyPoints=" + loyaltyPoints + '}';
    }
}

// Product class 
class Product {
    private int id;
    private String name;
    private double price;

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void updatePrice(double price) {
        this.price = price;
    }

   
    public String toString() {
        return "Product{id=" + id + ", name='" + name + '\'' + ", price=" + price + '}';
    }
}

// Order class 
class Order {
    private int orderId;
    private int customerId;
    private Date deliveryDate;
    private Set<Product> products;

    public Order(int orderId, int customerId, Date deliveryDate) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.deliveryDate = deliveryDate;
        this.products = new HashSet<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public Set<Product> getProducts() {
        return products;
    }

    
    public String toString() {
        return "Order{orderId=" + orderId + ", customerId=" + customerId + ", deliveryDate=" + deliveryDate + ", products=" + products + '}';
    }
}

// inheritance //comparator interface
class ProductPriceComparator implements Comparator<Product> {
    
    public int compare(Product p1, Product p2) {
        return Double.compare(p1.getPrice(), p2.getPrice());
    }
}

// for sorting
class CustomerLoyaltyComparator implements Comparator<Customer> {
   
    public int compare(Customer c1, Customer c2) {
        return Integer.compare(c2.getLoyaltyPoints(), c1.getLoyaltyPoints());
    }
}

public class program7 {
    public static void main(String[] args) {
        // ArrayLists for dynamic storage
        ArrayList<Customer> customerList = new ArrayList<>();
        ArrayList<Product> productList = new ArrayList<>();

        // HashMaps for fast retrieval
        HashMap<Integer, Customer> customerMap = new HashMap<>();
        HashMap<Integer, Product> productMap = new HashMap<>();

        // HashSet to ensure unique products per order
        HashSet<Product> uniqueProductSet = new HashSet<>();

        // TreeSet for sorted customers by loyalty points
        TreeSet<Customer> sortedCustomers = new TreeSet<>(new CustomerLoyaltyComparator());

        // TreeSet for sorted products by price
        TreeSet<Product> sortedProducts = new TreeSet<>(new ProductPriceComparator());

        // Add customers
        Customer customer1 = new Customer(1, "Aaron", 100);
        Customer customer2 = new Customer(2, "sharon", 200);
        customerList.add(customer1);
        customerList.add(customer2);
        customerMap.put(customer1.getId(), customer1);
        customerMap.put(customer2.getId(), customer2);
        sortedCustomers.add(customer1);
        sortedCustomers.add(customer2);

        // Add products
        Product product1 = new Product(101, "product1", 800.00);
        Product product2 = new Product(102, "Product2", 500.00);
        productList.add(product1);
        productList.add(product2);
        productMap.put(product1.getId(), product1);
        productMap.put(product2.getId(), product2);
        sortedProducts.add(product1);
        sortedProducts.add(product2);

        // Create and manage orders
        Order order1 = new Order(1, customer1.getId(), new Date());
        order1.addProduct(product1);
        order1.addProduct(product2);
        uniqueProductSet.add(product1);
        uniqueProductSet.add(product2);

        // Display customers sorted by loyalty points
        System.out.println("\nCustomers sorted by loyalty points:");
        for (Customer customer : sortedCustomers) {
            System.out.println(customer);
        }

        // Display products sorted by price
        System.out.println("\nProducts sorted by price:");
        for (Product product : sortedProducts) {
            System.out.println(product);
        }

        // Display order details
        System.out.println("\nOrder Details:");
        System.out.println(order1);
    }
}

