package ku.cs.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ku.cs.models.Student;

public class StudentController {
    @FXML
    private Label nameLabel;

    @FXML
    public void initialize() {
        Student student = new Student("6610401985", "AockChaiyawat");
        showStudent(student);
    }

    private void showStudent(Student student) {
        nameLabel.setText(student.getName());
    }
}
