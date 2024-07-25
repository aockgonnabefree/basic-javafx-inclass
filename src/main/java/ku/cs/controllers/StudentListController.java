package ku.cs.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import ku.cs.models.Student;
import ku.cs.models.StudentList;
import ku.cs.services.FXRouter;
import ku.cs.services.StudentHardCodeDatasource;

import java.io.IOException;

public class StudentListController {
    @FXML private ListView<Student> studentListView;
    @FXML private Label idLabel;
    @FXML private Label nameLabel;
    @FXML private Label scoreLabel;
    @FXML private TextField giveScoreTextField;
    @FXML private Label errorLabel;

    private StudentList studentList;
    private Student selectedStudent;

    @FXML
    public void initialize() {
        clearStudentInfo();
        clearForm();
        StudentHardCodeDatasource datasource = new StudentHardCodeDatasource();
        studentList = datasource.readData();
        showList(studentList);
        studentListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Student>() {
            @Override
            public void changed(ObservableValue<? extends Student> observable, Student oldValue, Student newValue) {
                if (newValue == null) {
                    clearStudentInfo();
                    selectedStudent = null;
                } else {
                    showStudentInfo(newValue);
                    selectedStudent = newValue;
                }
            }
            @FXML
            public void onBackButtonClick() {
                try {
                    FXRouter.goTo("hello");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void showList(StudentList studentList) {
        studentListView.getItems().clear();
        studentListView.getItems().addAll(studentList.getStudents());
    }

    private void showStudentInfo(Student student) {
        idLabel.setText(student.getId());
        nameLabel.setText(student.getName());
        scoreLabel.setText(String.format("%.2f", student.getScore()));
    }

    private void clearStudentInfo() {
        idLabel.setText("");
        nameLabel.setText("");
        scoreLabel.setText("");
    }
    @FXML
    public void onBackButtonClick() {
        try {
            FXRouter.goTo("hello");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearForm(){
        errorLabel.setText("");
        giveScoreTextField.setText("");
    }

    @FXML
    public void onGiveScoreButtonClick() {
        if (selectedStudent != null) {
            try {
                String scoreText = giveScoreTextField.getText();
                double score = Double.parseDouble(scoreText);
                selectedStudent.addScore(score);
                clearForm();
                showStudentInfo(selectedStudent);
            }catch (NumberFormatException e) {
                errorLabel.setText("Please enter a valid number");
            }catch (IllegalArgumentException e) {
                errorLabel.setText(e.getMessage());
            }

        } else {
            clearForm();
        }
    }
}