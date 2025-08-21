import java.util.Comparator;

public class ProductPriceComparator implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        int result = Double.compare(o1.price, o2.price);
        if (result == 0){
            result = o1.name.compareTo(o2.name);
        }
        return result;
    }
}
