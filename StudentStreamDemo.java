import java.util.*;
import java.util.stream.*;

class Student {
    private String name;
    private int marks;

    Student(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }

    public String getName() { return name; }
    public int getMarks() { return marks; }

    @Override
    public String toString() {
        return name + " (" + marks + "%)";
    }
}

public class StudentStreamDemo {
    public static void main(String[] args) {
        List<Student> students = List.of(
            new Student("Ayindrila", 88),
            new Student("Rahul", 72),
            new Student("Sneha", 95),
            new Student("Kaushal", 64),
            new Student("Ishita", 80)
        );

        System.out.println("Students scoring above 75%, sorted by marks:");

        students.stream()
                .filter(s -> s.getMarks() > 75)              // filter
                .sorted(Comparator.comparingInt(Student::getMarks)) // sort ascending
                .map(Student::getName)                       // map names
                .forEach(System.out::println);               // display
    }
}