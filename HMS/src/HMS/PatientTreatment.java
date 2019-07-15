package HMS;

public class PatientTreatment {
    private int patientTreatmentID;
    private int patientID;
    private String patientTreatmentName;
    private int doctorID;
    private String totalCost;
    private String treatmentStartDate;
    private String treatmentEndDate;
    private String treatmentStatus;

    //constructor with parameterisation
    public PatientTreatment(int patientTreatmentID,int patientID,String patientTreatmentName,int doctorID,String totalCost,String treatmentStartDate,String treatmentEndDate,String treatmentStatus){
        this.patientTreatmentID = patientTreatmentID;
        this.patientID = patientID;
        this.patientTreatmentName=patientTreatmentName;
        this.doctorID = doctorID;
        this.totalCost = totalCost;
        this.treatmentStartDate = treatmentStartDate;
        this.treatmentEndDate = treatmentEndDate;
        this.treatmentStatus = treatmentStatus;
    }

    //setter
   public void setPatientTreatmentID(int patientTreatmentID){
        this.patientTreatmentID=patientTreatmentID;
    }
    public void setPatientID(int patientID){
        this.patientID = patientID;
    }
    public void setPatientTreatmentName(String patientTreatmentName){
        this.patientTreatmentName=patientTreatmentName;
    }
    public void setDoctorID(int doctotID){
        this.doctorID = doctotID;
    }
    public void setTotalCost(String totalCost){this.totalCost=totalCost;}
    public void setTreatmentStartDate(String treatmentStartDate){this.treatmentStartDate=treatmentStartDate;}
    public void setTreatmentEndDate(String treatmentEndDate){this.treatmentEndDate=treatmentEndDate;}
    public void setTreatmentStatus(String treatmentStatus){this.treatmentStatus=treatmentStatus;}


    //getter
    public int getPatientTreatmentID(){ return patientTreatmentID; }
    public int getPatientID(){
        return patientID;
    }
    public String getPatientTreatmentName(){
        return patientTreatmentName;
    }
    public int getDoctorID(){return doctorID;}
    public String getTotalCost (){return totalCost;}
    public String getTreatmentStartDate (){return treatmentStartDate;}
    public String getTreatmentEndDate (){return treatmentEndDate;}
    public String getTreatmentStatus (){return treatmentStatus;}



}
