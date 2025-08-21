import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        System.out.println(" GENERIC ENTITY MANAGEMENT SYSTEM TEST ===\n");

        // Test Student Manager
        testStudentManager();

        // Test Employee Manager
        testEmployeeManager();

        // Test Product Manager
        testProductManager();

        System.out.println(" ALL TESTS COMPLETED SUCCESSFULLY ");
    }

    private static void testStudentManager() {
        System.out.println(" TESTING STUDENT MANAGER ");
        System.out.println("========================================");

        // Create student manager
        ManagerClass<Student, Integer> studentManager = new ManagerClass<>();

        // Add students
        Student s1 = new Student(103, "Alice Johnson", 20, 95.5);
        Student s2 = new Student(101, "Bob Smith", 19, 87.2);
        Student s3 = new Student(105, "Charlie Brown", 21, 92.8);
        Student s4 = new Student(102, "Diana Prince", 20, 98.1);
        Student s5 = new Student(104, "Edward Norton", 22, 84.5);

        studentManager.addEntity(s1);
        studentManager.addEntity(s2);
        studentManager.addEntity(s3);
        studentManager.addEntity(s4);
        studentManager.addEntity(s5);

        System.out.println(" Added " + studentManager.getEntitiesCount() + " students");

        // Test natural sorting (by ID)
        System.out.println("\n Students sorted naturally (by ID):");
        List<Student> natSortedStudents = studentManager.getEntitiesSortedNaturally();
        for (int i = 0; i < natSortedStudents.size(); i++){
            System.out.println(natSortedStudents.get(i));
        }

        // Test name sorting
        System.out.println("\n Students sorted by name:");

        List<Student> studentsSortedByName = studentManager.getEntitiesSortedByName();
        for (int i = 0; i < studentsSortedByName.size(); i++){
            System.out.println(studentsSortedByName.get(i));
        }

        // Test grade sorting
        System.out.println("\n Students sorted by grade (highest first):");

        List<Student> studentsSortedByGrade = studentManager.getEntitiesSortedBy(new StudentGradeComparator());
        for (int i = 0; i < studentsSortedByGrade.size(); i++){
            System.out.println(studentsSortedByGrade.get(i));
        }

        // Test ID lookup
        System.out.println("\n Looking up student with ID 103:");
        Student found = studentManager.getEntityById(103);
        System.out.println(found != null ? found : "Not found");

        // Test pattern search
        System.out.println("\n Students with 'o' in their name:");
        List<String> studentsByGivenPattern = studentManager.findEntitiesByNamePattern("o");
        for (int i = 0; i < studentsByGivenPattern.size(); i++){
            System.out.println(studentsByGivenPattern.get(i));
        }

        // Test ID range search
        System.out.println("\n Students with ID between 102 and 104:");

        List<Student> studentsInGivenRange = studentManager.getEntitiesByIdRange(102,104);
        for (int i = 0; i < studentsInGivenRange.size(); i++){
            System.out.println(studentsInGivenRange.get(i));
        }

        // Test unique entities (TreeSet)
        System.out.println("\n All unique students (automatically sorted):");

        Set<Student> allUniqueEntities = studentManager.getAllUniqueEntities();
        for (Student s : allUniqueEntities){
            System.out.println(s);
        }

        // Test removal
        System.out.println("\n Removing student with ID 101:");
        boolean removed = studentManager.removeEntity(101);
        System.out.println("Removed: " + removed);
        System.out.println("Count after removal: " + studentManager.getEntitiesCount());


    }

    private static void testEmployeeManager() {
        System.out.println(" TESTING EMPLOYEE MANAGER ");
        System.out.println("========================================");

        // Create employee manager
        ManagerClass<Employee, String> employeeManager = new ManagerClass<>();

        // Add employees
        Employee e1 = new Employee("EMP003", "John Davis", 75000.0, "Engineering");
        Employee e2 = new Employee("EMP001", "Sarah Wilson", 82000.0, "Marketing");
        Employee e3 = new Employee("EMP005", "Mike Johnson", 68000.0, "Sales");
        Employee e4 = new Employee("EMP002", "Lisa Anderson", 95000.0, "Engineering");
        Employee e5 = new Employee("EMP004", "Tom Brown", 71000.0, "HR");

        employeeManager.addEntity(e1);
        employeeManager.addEntity(e2);
        employeeManager.addEntity(e3);
        employeeManager.addEntity(e4);
        employeeManager.addEntity(e5);

        System.out.println(" Added " + employeeManager.getEntitiesCount() + " employees");

        // Test natural sorting (by employee code)
        System.out.println("\n Employees sorted naturally (by code):");
        List<Employee> natSortedEmployes = employeeManager.getEntitiesSortedNaturally();
        for (int i = 0; i < natSortedEmployes.size(); i++){
            System.out.println(natSortedEmployes.get(i));
        }

        // Test name sorting
        System.out.println("\n Employees sorted by name:");
        List<Employee> nameSortedEmployes = employeeManager.getEntitiesSortedByName();
        for (int i = 0; i < nameSortedEmployes.size(); i++){
            System.out.println(nameSortedEmployes.get(i));
        }

        // Test salary sorting
        System.out.println("\n Employees sorted by salary (highest first):");
        List<Employee> bySalarySortedEmployes = employeeManager.getEntitiesSortedBy(new EmployeeSalaryComparator());
        for (int i = 0; i < bySalarySortedEmployes.size(); i++){
            System.out.println(bySalarySortedEmployes.get(i));
        }

        // Test ID lookup
        System.out.println("\n Looking up employee with code 'EMP003':");
        Employee found = employeeManager.getEntityById("EMP003");
        System.out.println(found != null ? found : "Not found");

        // Test pattern search
        System.out.println("\n Employees with 'son' in their name:");
        List<String> employeeByPatternSearch = employeeManager.findEntitiesByNamePattern("son");
        for (int i = 0; i < employeeByPatternSearch.size(); i++){
            System.out.println(employeeByPatternSearch.get(i));
        }

        // Test ID range search
        System.out.println("\n Employees with code between 'EMP002' and 'EMP004':");
        List<Employee> empsByIdRange = employeeManager.getEntitiesByIdRange("EMP002", "EMP004");
        for (int i = 0; i < empsByIdRange.size(); i++){
            System.out.println(empsByIdRange.get(i));
        }

        // Test removal
        System.out.println("\n Removing employee with code 'EMP001':");
        boolean removed = employeeManager.removeEntity("EMP001");
        System.out.println("Removed: " + removed);
        System.out.println("Count after removal: " + employeeManager.getEntitiesCount());


    }

    private static void testProductManager() {
        System.out.println(" TESTING PRODUCT MANAGER ");
        System.out.println("========================================");

        // Create product manager
        ManagerClass<Product, Long> productManager = new ManagerClass<>();

        // Add products
        Product p1 = new Product(1003L, "Laptop Pro", 1299.99, "Electronics");
        Product p2 = new Product(1001L, "Wireless Mouse", 29.99, "Electronics");
        Product p3 = new Product(1005L, "Office Chair", 249.50, "Furniture");
        Product p4 = new Product(1002L, "Coffee Maker", 89.99, "Appliances");
        Product p5 = new Product(1004L, "Desk Lamp", 45.75, "Furniture");

        productManager.addEntity(p1);
        productManager.addEntity(p2);
        productManager.addEntity(p3);
        productManager.addEntity(p4);
        productManager.addEntity(p5);

        System.out.println(" Added " + productManager.getEntitiesCount() + " products");

        // Test natural sorting (by product ID)
        System.out.println("\n Products sorted naturally (by ID):");
        List<Product> natSortedProducts = productManager.getEntitiesSortedNaturally();
        for (int i = 0; i < natSortedProducts.size(); i++){
            System.out.println(natSortedProducts.get(i));
        }

        // Test name sorting
        System.out.println("\n Products sorted by name:");
        List<Product> nameSortedProducts = productManager.getEntitiesSortedByName();
        for (int i = 0; i < nameSortedProducts.size(); i++){
            System.out.println(nameSortedProducts.get(i));
        }

        // Test price sorting
        System.out.println("\n Products sorted by price (lowest first):");
        List<Product> prodsSortedByPrice = productManager.getEntitiesSortedBy(new ProductPriceComparator());
        for (int i = 0; i < prodsSortedByPrice.size(); i++){
            System.out.println(prodsSortedByPrice.get(i));
        }

        // Test ID lookup
        System.out.println("\n Looking up product with ID 1003:");
        Product found = productManager.getEntityById(1003L);
        System.out.println(found != null ? found : "Not found");

        // Test pattern search
        System.out.println("\n Products with 'e' in their name:");
        List<String> prodsByName = productManager.findEntitiesByNamePattern("e");
        for (int i = 0; i < prodsByName.size(); i++){
            System.out.println(prodsByName.get(i));
        }

        // Test ID range search
        System.out.println("\n Products with ID between 1002 and 1004:");
        List<Product> prodsInRange = productManager.getEntitiesByIdRange(1002L, 1004L);
        for (int i = 0; i < prodsInRange.size(); i++){
            System.out.println(prodsInRange.get(i));
        }

        // Test edge cases
        System.out.println("\n TESTING EDGE CASES:");

        // Test null pattern search
        System.out.println("Search with empty pattern:");
        System.out.println("Results: " + productManager.findEntitiesByNamePattern("").size());

        // Test non-existent ID lookup
        System.out.println("Looking up non-existent ID 9999:");
        Product notFound = productManager.getEntityById(9999L);
        System.out.println("Result: " + (notFound == null ? "null (expected)" : "Found (unexpected)"));

        // Test duplicate addition
        Product duplicate = new Product(1001L, "Another Mouse", 35.99, "Electronics");
        productManager.addEntity(duplicate);
        System.out.println("test uniqueness after adding a duplicate");
        Set<Product> uniqueProducts = productManager.getAllUniqueEntities();
        for (Product p : uniqueProducts){
            System.out.println(p);
        }
        System.out.println("After adding duplicate ID, count: " + productManager.getEntitiesCount());
        System.out.println("Product with ID 1001: " + productManager.getEntityById(1001L));

        // Test removal
        System.out.println("\n Removing product with ID 1001:");
        boolean removed = productManager.removeEntity(1001L);
        System.out.println("Removed: " + removed);
        System.out.println("Final count: " + productManager.getEntitiesCount());

    }
}