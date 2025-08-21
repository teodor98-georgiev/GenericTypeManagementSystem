import java.util.Comparator;

public class EmployeeSalaryComparator implements Comparator<Employee> {

    @Override
    public int compare(Employee o1, Employee o2) {
        int result = Double.compare(o2.salary, o1.salary);
        if (result == 0){
            result = o1.getDisplayName().compareTo(o2.getDisplayName());
        }
        return result;
    }
}
