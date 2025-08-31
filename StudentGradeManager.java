import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private String name;
    private double grade;

    public Student(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public double getGrade() {
        return grade;
    }
}

public class StudentGradeManager {
    private ArrayList<Student> students;

    public StudentGradeManager() {
        students = new ArrayList<>();
    }

    public void addStudent(String name, double grade) {
        students.add(new Student(name, grade));
    }

    public double getHighestGrade() {
        double highest = Double.MIN_VALUE;
        for (Student s : students) {
            if (s.getGrade() > highest) {
                highest = s.getGrade();
            }
        }
        return highest;
    }

    public double getLowestGrade() {
        double lowest = Double.MAX_VALUE;
        for (Student s : students) {
            if (s.getGrade() < lowest) {
                lowest = s.getGrade();
            }
        }
        return lowest;
    }

    public double getAverageGrade() {
        if (students.isEmpty()) return 0;
        double sum = 0;
        for (Student s : students) {
            sum += s.getGrade();
        }
        return sum / students.size();
    }

    public void displaySummary() {
        System.out.println("\n----- Students Summary Report -----");
        System.out.println("Name\t\tGrade");
        System.out.println("-------------------------");
        for (Student s : students) {
            System.out.printf("%-15s %.2f%n", s.getName(), s.getGrade());
        }
        System.out.println("-------------------------");
        System.out.printf("Highest Grade: %.2f%n", getHighestGrade());
        System.out.printf("Lowest Grade: %.2f%n", getLowestGrade());
        System.out.printf("Average Grade: %.2f%n", getAverageGrade());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentGradeManager manager = new StudentGradeManager();

        System.out.println("Student Grade Manager");
        System.out.println("======================");

        while (true) {
            System.out.print("Enter student name (or 'done' to finish): ");
            String name = scanner.nextLine();
            if (name.equalsIgnoreCase("done")) {
                break;
            }

            double grade = -1;
            while (grade < 0 || grade > 100) {
                System.out.print("Enter grade for " + name + " (0 - 100): ");
                try {
                    grade = Double.parseDouble(scanner.nextLine());
                    if (grade < 0 || grade > 100) {
                        System.out.println("Grade must be between 0 and 100. Try again.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                }
            }

            manager.addStudent(name, grade);
        }

        if (manager.students.isEmpty()) {
            System.out.println("No students entered.");
        } else {
            manager.displaySummary();
        }

        scanner.close();
    }
}
 
