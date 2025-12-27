package SchoolManagementSystem;

public class Course {
    private String courseCode;
    private String courseTitle;
    private int courseUnit;
    Course(String courseTitle, String courseCode, int courseUnit) {
        this.courseTitle = courseTitle;
        this.courseCode = courseCode;
        this.courseUnit = courseUnit;
    }

    String getCourseCode() {
        return courseCode;
    }

    void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    String getCourseTitle() {
        return courseTitle;
    }

    void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }
    int getCourseUnit() {
        return courseUnit;
    }
    void getCourseUnit(int courseUnit) {
        this.courseUnit = courseUnit;
    }
    @Override
    public String toString() {
        return getCourseCode() + "-" + getCourseTitle() + " " + getCourseUnit() + "units";
    }
}
