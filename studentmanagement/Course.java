package studentmanagement;

import java.io.Serializable;

public class Course implements Serializable {

    private int courseId;
    private String courseName;
    private double courseFee;
    private int durationInMonths;

    public Course(int courseId, String courseName,
                  double courseFee, int durationInMonths) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseFee = courseFee;
        this.durationInMonths = durationInMonths;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public double getCourseFee() {
        return courseFee;
    }

    public void setCourseFee(double courseFee) {
        this.courseFee = courseFee;
    }

    public int getDurationInMonths() {
        return durationInMonths;
    }

    public void setDurationInMonths(int durationInMonths) {
        this.durationInMonths = durationInMonths;
    }

    @Override
    public String toString() {
        return "Course ID : " + courseId +
                ", Course Name : " + courseName +
                ", Fee : " + courseFee +
                ", Duration : " + durationInMonths + " months";
    }
}
