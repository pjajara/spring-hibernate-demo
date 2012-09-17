/**
 * 
 */
package com.emc.xcp.domain;

/**
 * @author jajarp
 *
 */
import java.util.Date;
import javax.persistence.*;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "CUSTOMERS")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column
	private String sex;
	@Column
	private int age;
	@Column
	private String address;
	@Column
	private String email;
	@Column
	private String phone;
	@Column
	private String company;
	@Column
	private double salary;
	
	public Customer(){
		
	}
	
	public Customer(int id, String firstName, String lastName, String sex, int age, String address, 
			String email, String phone, String company, double salary){
		this.id=id;
		this.firstName=firstName;
		this.lastName=lastName;
		this.sex=sex;
		this.age=age;
		this.address=address;
		this.email=email;
		this.phone=phone;
		this.company=company;
		this.salary=salary;
	}
	
	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

}
