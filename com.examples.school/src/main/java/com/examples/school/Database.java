package com.examples.school;

import java.util.List;

public interface Database {

	public List<Student> getAllStudentsList();

	public Student findStudentById(String id);
}
