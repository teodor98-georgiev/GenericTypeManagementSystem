public class Product implements Comparable<Product>, Identifiable<Long> {
    long productId;
    String name;
    double price;
    String category;

    public Product(long productId, String name, double price, String category) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.category = category;
    }


    @Override
    public Long getId() {
        return this.productId;
    }

    @Override
    public String getDisplayName() {
        return this.name;
    }

    @Override
    public int compareTo(Product o) {
        int result = Long.compare(this.productId, o.productId);
        return result;
    }

    public String toString(){
        return "Name: " + this.name + "---Price: " + this.price + "---Category: " + this.category;
    }
}
