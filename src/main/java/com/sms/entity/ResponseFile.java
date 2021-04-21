package com.sms.entity;

public class ResponseFile {

	
	 private String name;
	  private String url;
	  private String type;
	  private long size;
	  private String department;
	  private String course;
	  private String description;
	  private String id;
	  private String studentName ; 
	  
	  //this is for submitted assignments from the student 
	  //it also contains the studentName on it .
	  public ResponseFile(String id ,String name, String url, String type, String department, String course,
			String description, String studentName) {
		this.name = name;
		this.url = url;
		this.type = type;
		this.department = department;
		this.course = course;
		this.description = description;
		this.id = id;
		this.studentName = studentName;
	}



	public String getStudentName() {
		return studentName;
	}



	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}



	public ResponseFile(String name, String url, String type, long size) {
	    this.name = name;
	    this.url = url;
	    this.type = type;
	    this.size = size;
	  }

	 

	public ResponseFile(String id,String name, String url, String type , String department, String course,
			String description) {
		this.id=id;
		this.name = name;
		this.url = url;
		this.type = type;
	//	this.size = size;
		this.department = department;
		this.course = course;
		this.description = description;
	}



	// this one is for notification because notification will not have course
	public ResponseFile(String id,String name, String url, String type , String department,
			String description) {
		this.id=id;
		this.name = name;
		this.url = url;
		this.type = type;
		this.department = department;
		this.description = description;
	}


	public String getDepartment() {
		return department;
	}



	public void setDepartment(String department) {
		this.department = department;
	}



	public String getCourse() {
		return course;
	}



	public void setCourse(String course) {
		this.course = course;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getName() {
	    return name;
	  }

	  public void setName(String name) {
	    this.name = name;
	  }

	  public String getUrl() {
	    return url;
	  }

	  public void setUrl(String url) {
	    this.url = url;
	  }

	  public String getType() {
	    return type;
	  }

	  public void setType(String type) {
	    this.type = type;
	  }

	  public long getSize() {
	    return size;
	  }

	  public void setSize(long size) {
	    this.size = size;
	  }



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}
	  
	  
	  
}
