package com.examples.school.mongo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.net.UnknownHostException;

import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.junit.Before;
import org.junit.Test;

import com.examples.school.Student;
import com.mongodb.DB;
import com.mongodb.MongoClient;

public class MongoDatabaseIT {

	private MongoDatabase mongoDatabase;
	private MongoCollection students;

	@Before
	public void initDB() throws UnknownHostException {
		MongoClient mongoClient = new MongoClient();
		DB db = mongoClient.getDB("school");
		db.getCollection("student").drop();
		mongoDatabase = new MongoDatabase(mongoClient);
		Jongo jongo = new Jongo(db);
		students = jongo.getCollection("student");
	}

	@Test
	public void testGetAllStudentsEmpty() {
		assertTrue(mongoDatabase.getAllStudentsList().isEmpty());
	}

	@Test
	public void testGetAllStudentsNotEmpty() {
		students.save(new Student("1", "first"));
		students.save(new Student("2", "second"));

		assertEquals(2, mongoDatabase.getAllStudentsList().size());
	}

	@Test
	public void testFindStudentByIdNotFound() {
		students.save(new Student("1", "first"));

		assertNull(mongoDatabase.findStudentById("2"));
	}

	@Test
	public void testFindStudentByIdFound() {
		students.save(new Student("1", "first"));
		Student studentToFind = new Student("2", "second");
		students.save(studentToFind);

		Student findStudentById = mongoDatabase.findStudentById("2");
		assertNotNull(findStudentById);
		assertNotSame(studentToFind, findStudentById);
		assertEquals("2", findStudentById.getId());
		assertEquals("second", findStudentById.getName());
	}

}
