package org.hibernate.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Grade {
	@Id
	@GeneratedValue(generator="gid")
	@GenericGenerator(name="gid", strategy="native")
	private int gid;// 班号
	@Column(length=100)
	private String gname;// 班名
	private String gdesc;// 班级描述
	//mappedBy表示grade表不再参与关系维护，将主控方交给学生类;配置了mappedBy就不能再@JoinColumn	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="grade")
	private Set<Student> students = new HashSet<Student>();
	
	public Grade() {
		
	}

	public Grade(String gname, String gdesc) {
		this.gname = gname;
		this.gdesc = gdesc;
	}

	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public String getGdesc() {
		return gdesc;
	}

	public void setGdesc(String gdesc) {
		this.gdesc = gdesc;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

}
