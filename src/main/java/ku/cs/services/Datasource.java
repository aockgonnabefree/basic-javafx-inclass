package ku.cs.services;

import ku.cs.models.StudentList;

public interface Datasource<T> {
    T readData();
    void writeData(T studentList);
}


