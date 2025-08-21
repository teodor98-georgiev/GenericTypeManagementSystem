public class Employee implements Comparable<Employee>, Identifiable<String> {
    String employeeCode;
    String name;
    double salary;
    String department;

    public Employee(String employeeCode, String name, double salary, String department) {
        this.employeeCode = employeeCode;
        this.name = name;
        this.salary = salary;
        this.department = department;
    }


    @Override
    public String getId() {
        return this.employeeCode;
    }

    @Override
    public String getDisplayName() {
        return this.name;
    }



    public String toString(){
        return "Name: " + this.name + "---Salary: " + this.salary + "---Department: " + this.department;
    }

    @Override
    public int compareTo(Employee o) {
        int result = this.employeeCode.compareTo(o.employeeCode);
        return result;
    }
}
