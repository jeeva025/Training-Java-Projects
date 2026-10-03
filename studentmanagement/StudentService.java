package studentmanagement;


import studentmanagement.customexceptions.*;

import java.util.HashMap;


public class StudentService {

    public Student addStudent(HashMap<Integer, Student> studentsInfo, HashMap<Integer, Course> courses
                                ,int id,
                              String name,
                              String email,
                              double marks,
                              int attendance,
                              String status,
                              int courseId)  {
        if (studentsInfo.containsKey(id)) {
            throw new DuplicateIDNotAllowed(
                    "Student already exists with id " + id
            );
        }

        if (marks < 0 || marks > 500) {
            throw new InvalidMarks(
                    "Marks must be between 0 and 500"
            );
        }

        if (attendance < 0 || attendance > 100) {
            throw new InvalidAttendance(
                    "Attendance must be between 0 and 100"
            );
        }

        StudentStatus studentStatus;

        try {
            studentStatus =
                    StudentStatus.valueOf(status.toUpperCase());

        } catch (IllegalArgumentException e) {

            throw new InvalidStatus(
                    "Invalid student status"
            );
        }

        if (!courses.containsKey(courseId)) {
            throw new InvalidCourseId(
                    "No course found with ID " + courseId
            );
        }

        Course course = courses.get(courseId);

        return new Student(id, name, email, marks, attendance, studentStatus, course);


    }

    public void displayAllStudents(HashMap<Integer, Student> map){
        if (map.isEmpty()){
            System.out.println("Nothing to display");
            return;
        }
        System.out.println("------------------All Student Information---------------");
        for ( Student student : map.values()){
            System.out.println(student);        }
    }

    public void searchStudentById(Integer id, HashMap<Integer, Student> studentsInfo ){
        if (!studentsInfo.containsKey(id)){
            throw new NoStudentPresent("No student present with id "+id);
        }
        Student student = studentsInfo.get(id);
        System.out.println(student);
    }

    public void searchStudentByName(String name, HashMap<Integer, Student> map ){
        boolean found = false;
        for ( Student student : map.values()){
            if (student.getName().equalsIgnoreCase(name)){
                System.out.println(student);
                found = true;
            }
        }
        if (!found){
            throw new NoStudentPresent("No student found with name " + name);
        }
    }

    public void updateAttendance(Integer id, HashMap<Integer, Student> studentInfo , int newAttendance ){
        if (!studentInfo.containsKey(id)){
            throw new NoStudentPresent("No student present with id :  "+id);
        }
        if (newAttendance < 0 || newAttendance > 100){
            throw  new InvalidAttendance("Attendance must be between 0 and 100");
        }
        studentInfo.get(id).setAttendance(newAttendance);
        System.out.println("Attendance Updated Successfully");
        System.out.println(studentInfo.get(id));
    }

    public void removeStudent(Integer id, HashMap<Integer, Student> studentInfo ){
        if (!studentInfo.containsKey(id)){
            throw new NoStudentPresent("No student present with id :  "+id);

        }
        studentInfo.remove(id);
        System.out.println("Student Removed with the id : "+id);
    }

    public void updateMarks(Integer id, HashMap<Integer, Student> studentInfo, double newMarks){
        if (!studentInfo.containsKey(id)){
            throw new NoStudentPresent("No student present with id : "+id);
        }
        if (newMarks < 0 || newMarks > 500){
            throw new InvalidMarks("Marks must be between 0 and 500");
        }
        Student student = studentInfo.get(id);

        student.setMarks(newMarks);

        System.out.println("Marks Updated Successfully");

        System.out.println(student);


    }

    public void showTopper(HashMap<Integer, Student> studentInfo){

        if (studentInfo.isEmpty()){

            System.out.println("No Student");
        }
        double topMark = 0;
        Student student = null ;
        for (Student value: studentInfo.values()){
            if (value.getMarks() > topMark){
                topMark = value.getMarks();
                student = value;

            }
        }
        if (student != null){
            System.out.println(student);
        }


    }


    public String gradeCalculator(double marks){
        if (marks >= 450){
            return "A";
        }
        else if (marks >= 400 )
            return "B";
        else if (marks >= 350){
            return "C";
        }
        else  return "D";
    }
}
