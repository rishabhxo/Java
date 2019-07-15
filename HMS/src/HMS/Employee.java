package HMS;

public abstract class Employee {
    protected int employeeId;
    protected String loginId;
    protected String password;
    protected String fullName;
    protected String role;

    public Employee(){
        this.employeeId=0;
        this.loginId="";
        this.password="";
        this.fullName="";
        this.role="";
    }
    public Employee(int employeeId,String loginId,String password,String fullName,String role){
        this.employeeId= employeeId;
        this.loginId=loginId;
        this.password=password;
        this.fullName=fullName;
        this.role=role;
    }
    //setter
    public void setEmployeeId(int employeeId){
        employeeId=employeeId;
    }
    public void setPassword(String password){
        password=password;
    }
    public void setLoginId(String loginId){
        loginId=loginId;
    }
    public void setFullName(String fullName){
        fullName=fullName;
    }
    public void setRole(String role){
        role=role;
    }
    //getter
    public int getEmployeeId(){
        return employeeId;
    }
    public String getLoginId(){
        return loginId;
    }
    public String getPassword(){
        return password;
    }
    public String getFullName(){
        return fullName;
    }
    public String getRole(){
        return role;
    }
}
