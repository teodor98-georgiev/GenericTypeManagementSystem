import java.util.Comparator;

public class nameComparator <T extends Identifiable<?>> implements Comparator<T> {


    @Override
    public int compare(T o1, T o2) {
        int result = o1.getDisplayName().compareTo(o2.getDisplayName());
        return result;
    }

}
