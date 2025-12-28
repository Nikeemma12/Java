package SchoolManagementSystem;

import java.util.ArrayList;

public class Student extends Person{
    private String courseOfStudy;
    private String idStart;
    static int num = 1;
   final private ArrayList<Course> courseLists;
    Student(String name, String email) {
        super(name, email);
        this.courseOfStudy = "";
        if(num>=1000){
            setIdStart("STD");
        } else if (num>=100) {
            setIdStart("STD0");
        } else if (num>=10) {
            setIdStart("STD00");
        } else {
            setIdStart("STD000");
        }
        this.setId(idStart + num);
        num++;
        courseLists = new ArrayList<>();
    }

    void Menu() {
        System.out.println("1. Update Profile");
        System.out.println("2. View All Courses");
        System.out.println("3. View My Profile");
        System.out.println("4. View Registered Courses");
        System.out.println("5. Register Courses");
        System.out.println("6. Drop Registered Courses");
        System.out.println("7. Logout");
        System.out.println("8. Exit");
    }
    void setIdStart(String idStart) {
        this.idStart = idStart;
    }
    String getCourseOfStudy() {
        return courseOfStudy;
    }
    void setCourseOfStudy(String courseOfStudy) {
        this.courseOfStudy = courseOfStudy;
    }

    @Override
    String getDetails() {
        return getid() + " " + getName()+ " " + getEmail() + " " + getCourseOfStudy();
    }
    @Override
    String getid() {
        return this.getId();
    }
    @Override
    public String toString() {
        return getId()+","+getName()+"," + getEmail() + "," + getPassword() + "," + getCourseOfStudy();
    }

    //Getter for courseList
    ArrayList<Course> getCourseList(){
        return this.courseLists;
    }
    //setter for courseList
    void setCourseList(Course course) {
        this.courseLists.add(course);
    }
    //Add course to courselist
    void addCourse(Course course) {
        setCourseList(course);
    }
    //Method to list out Course Lists of a student
    String getStudentCourseCode() {
        StringBuilder courselist  = new StringBuilder();
        for (Course course : courseLists){
            String courseCode = course.getCourseCode();
            courselist.append(courseCode);
            if(courseLists.size()>1 && !course.equals(courseLists.getLast())) {
                courselist.append(",");
            }
        }
        return courselist.toString();
    }
}
