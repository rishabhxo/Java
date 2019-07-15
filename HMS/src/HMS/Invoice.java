package HMS;

import java.util.Date;
public class Invoice {
    private int invoiceID;
    private int patientID;
    private int treatmentID;
    private double invoicedAmount;
    private Date visitedDate;

    //constructor with parameterised
    public Invoice(int invoiceID, int patientID, int treatmentID, double invoicedAmount, Date visitedDate) {
        this.invoiceID = invoiceID;
        this.patientID = patientID;
        this.treatmentID = treatmentID;
        this.invoicedAmount = invoicedAmount;
        this.visitedDate = visitedDate;
    }

    //setter
    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public void setTreatmentID(int treatmentID) {
        this.treatmentID = treatmentID;
    }
    public void setInvoicedAmount(double invoicedAmount) {
        this.invoicedAmount = invoicedAmount;
    }
    public void setVisitedDate(Date visitedDate){

        this.visitedDate=visitedDate;
}
//getter
    public int getInvoiceID(){
        return this.invoiceID;
    }
    public int getPatientID(){
        return this.patientID;
    }
    public int getTreatmentID(){
        return this.treatmentID;
    }
    public double getInvoicedAmount(){
        return this.invoicedAmount;
    }
    public Date getVisitedDate(){
        return this.visitedDate;
    }
}