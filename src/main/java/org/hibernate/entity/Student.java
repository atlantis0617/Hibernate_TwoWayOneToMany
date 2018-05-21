package org.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Student {
	@Id
	@GeneratedValue(generator="sid")
	@GenericGenerator(name="sid", strategy="native")
	private int sid;// 学号
	@Column(length=100)
	private String sname;// 姓名
	@Column(length=2)
	private String sex;// 性别
	/**
	 * fetch=FetchType.EAGER：积极加载，加载数据时，将与该数据关联的数据也加载到内存中；一般在多方使用，因为关联的一方数据较少
　　	fetch=FetchType.LAZY：延迟加载，加载数据时，不加载关联数据，当使用到关联数据时再从数据库中查询；一般在一方使用，因为关联的多方数据较多
	　  mappedBy：注解方式下使用，是在@OneToOne、@OneToMany、@ManyToMany上才有的属性，ManyToOne不存在该属性；在一的一方配置mappedBy将维护权
　　　　　　　　交由多的一方（因为外键在多的一方，所以多的一方维护起来更为方便）；当使用mappedBy时不能再使用@JoinColumn
　　    inverse：xml方式下使用，inverse="false"表示由自身维护映射关系，因此在一方将其设置为true，将维护权交给多的一方

	 * */
	@ManyToOne(fetch=FetchType.EAGER)
	@Cascade(value=CascadeType.SAVE_UPDATE)
	@JoinColumn(name="gid")
	private Grade grade;
	
	public Student() {

	}

	public Student(String sname, String sex) {
		this.sname = sname;
		this.sex = sex;
	}
	
	public Student(String sname, String sex, Grade grade) {
		this.sname = sname;
		this.sex = sex;
		this.grade = grade;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	
}
