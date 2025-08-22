package com.fastcampus.ch2.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class UserDto {
	private String id;
    private String pwd;
    private String name;
    private String email;
    private LocalDate birth;    
    private String sns;
    private LocalDateTime regDate;
    
    //생성자
    public UserDto() {super();}
	public UserDto(String id, String pwd) {
		super();
		this.id = id;
		this.pwd = pwd;
	}
	
	//getter/setter
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDate getBirth() {
		return birth;
	}
	public void setBirth(LocalDate birth) {
		this.birth = birth;
	}
	public String getSns() {
		return sns;
	}
	public void setSns(String sns) {
		this.sns = sns;
	}
	public LocalDateTime getRegDate() {
		return regDate;
	}
	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}
	
    @Override
	public String toString() {
		return "UserDto [id=" + id + ", pwd=" + pwd + ", name=" + name + ", email=" + email + ", birth=" + birth
				+ ", sns=" + sns + ", regDate=" + regDate + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(birth, email, id, name, pwd, regDate, sns);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDto other = (UserDto) obj;
		return Objects.equals(birth, other.birth) && Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(pwd, other.pwd)
				&& Objects.equals(regDate, other.regDate) && Objects.equals(sns, other.sns);
	}
    
    

}
