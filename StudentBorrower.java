package by.htp.main;

import java.lang.String;


public class StudentBorrower extends Borrower{
  //Fields for each borrower's info.
  private String name;
  private String id;
  private String grade;              
  private String phNumber;       
  private int borrowLimit = 2;    //Two book limit.
  private Boolean canBorrow;
  
  //Constructor.
  public StudentBorrower(String ID, String n, String g, String c){
    super(n, ID);
    id = ID;
    name = n;
    grade = g;
    phNumber = c;
  }
  

  public Boolean allowBorrow(int borrowTime, int bookCount){
    if(borrowTime >= 14 || bookCount >= 2)
      return false;
    else
      return true;
  }
  
  
  
  public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public String getGrade() {
	return grade;
}

public void setGrade(String grade) {
	this.grade = grade;
}

public String getOffClass() {
	return phNumber;
}

public void setOffClass(String offClass) {
	this.phNumber = offClass;
}

//Converts object to String.
  public String toString(){
        return name + ", " + id + ", " + grade + ", " + phNumber;
    }
}
