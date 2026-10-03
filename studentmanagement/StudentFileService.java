package studentmanagement;


import java.io.*;
import java.util.HashMap;

public class StudentFileService {

    private final String fileName = "students.dat";

    public void saveStudents(HashMap<Integer, Student> students) {

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {

            outputStream.writeObject(students);

            System.out.println("Student details saved successfully.");

        } catch (IOException e) {
            System.out.println("Error while saving student details.");
            e.printStackTrace();
        }
    }

    public HashMap<Integer, Student> loadStudents() {

        File file = new File(fileName);

        if (!file.exists()) {
            System.out.println("No saved student data found.");
            return new HashMap<>();
        }

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {

            HashMap<Integer, Student> students = (HashMap<Integer, Student>) inputStream.readObject();

            System.out.println("Student details loaded successfully.");

            return students;

        } catch (IOException | ClassNotFoundException e) {

            System.out.println("Error while loading student details.");
            e.printStackTrace();

            return new HashMap<>();
        }
    }
}