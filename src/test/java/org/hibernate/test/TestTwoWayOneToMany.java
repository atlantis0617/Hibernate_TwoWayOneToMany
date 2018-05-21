package org.hibernate.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.entity.Grade;
import org.hibernate.entity.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestTwoWayOneToMany {
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	
	@Before
	public void before() {
		// 创建SessionFactory对象
		sessionFactory = new Configuration().configure().buildSessionFactory();
		// 获取Session对象
		session = sessionFactory.openSession();
		// 开启事务
		transaction = session.beginTransaction();
	}
	
	@After
	public void after() {
		// 提交事务
		transaction.commit();
		// 关闭Session
		session.close();
		// 关闭SessionFactory
		sessionFactory.close();
	}
	
	
	@Test
	public void testAdd() {
		Grade grade = new Grade("Java一班", "Java应用开发一班");
		Student student1 = new Student("张三", "男", grade);
		Student student2 = new Student("李四", "女", grade);
		
		session.save(student1);
		session.save(student2);
		session.save(grade);
	}
	
	@Test
	public void testQuery() {
		Grade grade = session.get(Grade.class, 1);
		System.out.println(grade.getGname() + " - " + grade.getGdesc());
		for (Student student: grade.getStudents()) {
			System.out.println(student.getSname() + " - " + student.getSex());
		}
		
		Student student = session.get(Student.class, 1);
		System.out.println(student.getSname() + " - " + student.getSex() + " - "
			+ student.getGrade().getGname() + student.getGrade().getGdesc());
	}
	
	@Test
	public void testUpdate() {
		Grade grade = new Grade("Java二班", "Java应用开发二班");
		Student student = session.get(Student.class, 2);
		student.setGrade(grade);
		session.update(student);
	}
	
	@Test
	public void testDelete() {
		Grade grade = session.get(Grade.class, 2);
		session.delete(grade);
		Student student = session.get(Student.class, 1);
		session.delete(student);
	}
	
	
}
