package HMS;

public class Report {
    private int reportID;
    private int patientTreatmentID;

    public Report(int reportID,int patientTreatmentID){
        this.reportID=reportID;
        this.patientTreatmentID=patientTreatmentID;
    }
    //setter
    public void setReportID(int reportID){
        this.reportID=reportID;
    }
    public void setPatientTreatmentID(int patientTreatmentID){
        this.patientTreatmentID=patientTreatmentID;
    }
    //getter
    public int getReportID(int reportID){
        return reportID;
    }
    public int getPatientTreatmentID(int patientTreatmentID){
        return patientTreatmentID;
    }
}
