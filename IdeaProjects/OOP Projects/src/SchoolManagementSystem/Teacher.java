package SchoolManagementSystem;

import java.util.ArrayList;

public class Teacher extends Person {
    private String idStart;
    private String Department;
    static int num = 1;
    final private ArrayList<Course> coursesAssigned;
    Teacher (String name, String email) {
        super(name, email);
        if(num>=1000){
            setIdStart("TCH");
        } else if (num>=100) {
            setIdStart("TCH0");
        } else if (num>=10) {
            setIdStart("TCH00");
        } else {
            setIdStart("TCH000");
        }
        this.Department = "";
        this.setId(idStart + num);
        num++;
        coursesAssigned = new ArrayList<>();
    }

    void Menu() {
        System.out.println("1. Update Profile");
        System.out.println("2. View My Profile");
        System.out.println("3. View All Courses");
        System.out.println("4. View Assigned Courses");
        System.out.println("5. Logout");
        System.out.println("6. Exit");
    }

    String getDepartment() {
        return Department;
    }

    void setDepartment(String department) {
        this.Department = department;
    }


    void setIdStart(String idStart) {
        this.idStart = idStart;
    }
    @Override
    String getDetails() {
        return getid() + " " + getName()+ " " + getEmail() + " " + getDepartment();
    }
    @Override
    String getid() {
        return this.getId();
    }
    @Override
    public String toString() {
        return getId()+","+ getName()+"," + getEmail() + "," + getPassword() + "," + getDepartment();
    }

    //getter for teachers assigned course
    ArrayList<Course> getCoursesAssigned() {
        return this.coursesAssigned;
    }
    //setter for teachers assigned course
    void setCoursesAssigned(Course course){
        this.coursesAssigned.add(course);
    }
    //Add course to teacher
    void addCourse(Course course) {
        setCoursesAssigned(course);
    }
    //Getting all the course code of each student
    String getTeachersCourseListCode() {
        StringBuilder courses = new StringBuilder();
        for(Course course : coursesAssigned) {
            String courseCode = course.getCourseCode();
            courses.append(courseCode);
            if(coursesAssigned.size()>1 && !course.equals(coursesAssigned.getLast())) {
                courses.append(",");
            }
        }
        return courses.toString();
    }
}
