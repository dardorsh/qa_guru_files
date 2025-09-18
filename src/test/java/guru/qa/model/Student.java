package guru.qa.model;

import java.util.List;

public class Student {
    private String firstName;
    private String lastName;
    private int age;
    private boolean isStudent;
    private List<Course> courses;

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public boolean isStudent() { return isStudent; }
    public void setStudent(boolean student) { this.isStudent = student; }

    public List<Course> getCourses() { return courses; }
    public void setCourses(List<Course> courses) { this.courses = courses; }

}
