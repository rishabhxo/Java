package HMS;

public class Doctor extends Employee {
    public Doctor(){
        super();
    }
    public Doctor(int employeeId, String loginId, String password,String fullName, String role){
        super(employeeId, loginId, password, fullName, role);
    }
}
