import java.util.Comparator;

public class StudentGradeComparator implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        int result = Double.compare(o2.grade, o1.grade);
        if (result == 0){
            result = o1.getDisplayName().compareTo(o2.getDisplayName());
        }
        return result;
    }
}
