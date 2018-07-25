package by.htp.main;


public abstract class Borrower{
  private String name;
  private String ID;
  
  //Constructor. Params for name and ID of user.
  public Borrower(String n, String i){
    name = n;
    ID = i;
  }
  
  public abstract Boolean allowBorrow(int time, int books);
}
