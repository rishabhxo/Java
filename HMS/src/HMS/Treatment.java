package HMS;

import java.util.Date;

public class Treatment {

    private int treatmentID;
    private String treatmentName;
    private String treatmentDescription;
    private String treatmentCost;

    //constructor with initialisation
    public Treatment(){
        this.treatmentID=0;
        this.treatmentName="";
        this.treatmentDescription="";
        this.treatmentCost="";
    }
    //constructor with parameterisation
    public Treatment(int treatmentID,String treatmentName,String treatmentDescription,String treatmentCost){
        this.treatmentID=treatmentID;
        this.treatmentName=treatmentName;
        this.treatmentDescription=treatmentDescription;
        this.treatmentCost=treatmentCost;
    }

    //setter
    public void setTreatmentID(int treatmentID){
        this.treatmentID=treatmentID;
    }
    public void setTreatmentName(String treatmentName){
        this.treatmentName=treatmentName;
    }
    public void setTreatmentDescription(String treatmentDescription){
        this.treatmentDescription=treatmentDescription;
    }
    public void setTreatmentCost(String cost){
        this.treatmentCost=treatmentCost;
    }

    //getter
    public int getTreatmentID(){ return treatmentID; }
    public String getTreatmentName(){
        return treatmentName;
    }
    public String getTreatmentDescription(){
        return treatmentDescription;
    }
    public String getTreatmentCost (){return treatmentCost;}


}
