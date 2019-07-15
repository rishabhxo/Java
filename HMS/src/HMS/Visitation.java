package HMS;


public class Visitation {
    private int visitationID;
    private String patientName;
    private String visitationDate;
    private String visitationPurpose;
    private String visitedDoctor;


    public Visitation(int visitationID,String patientName, String visitationDate, String visitationPurpose, String visitedDoctor) {
        this.visitationID = visitationID;

        this.patientName = patientName;
        this.visitationDate = visitationDate;
        this.visitationPurpose = visitationPurpose;
        this.visitedDoctor = visitedDoctor;
    }

    //setter
    public void setVisitationID(int visitationID) {
        this.visitationID = visitationID;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public void setVisitationDate(String visitationDate) {
        this.visitationDate = visitationDate;
    }

    public void setVisitationPurpose(String visitationPurpose) {
        this.visitationPurpose = visitationPurpose;
    }

    public void setVisitedDoctor(String visitedDoctor) {
        this.visitedDoctor = visitedDoctor;
    }


    //getter
    public int getVisitationID()
    {
        return visitationID;
    }
    public String getPatientName()
    {
        return patientName;
    }
    public String getVisitationDate()
    {
        return visitationDate;
    }
    public String getVisitedDoctor()
    {
        return visitedDoctor;
    }
    public String getVisitationPurpose()
    {
        return visitationPurpose;
    }

}
