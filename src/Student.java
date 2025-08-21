public class Student implements Comparable<Student>, Identifiable<Integer> {
    int id;
    String name;
    int age;
    double grade;

    public Student(int id, String name, int age, double grade) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.grade = grade;
    }


    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public String getDisplayName() {
        return this.name;
    }


    @Override
    public int compareTo(Student o) {
        int result = this.getId().compareTo(o.getId());
        return result;
    }

    public String toString(){
        return "Name: " + this.name + "---Grade: " + this.grade;
    }


}
