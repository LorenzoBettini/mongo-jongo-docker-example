package com.examples.school;

import java.util.List;

public interface Database {

	public List<Student> getAllStudentsList();

	public void updateDB(String id, String name);

	public boolean exists(String id);

	public Student findStudentById(String id);
}
