package HMS;

public class Ward {
    private int wardID;
    private String wardName;

    public Ward(int wardID, String wardName) {
        this.wardID = wardID;
        this.wardName = wardName;
    }

    //setter
    public void setWardID(int wardID) {
        this.wardID = wardID;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public int getWardID()
    {
        return wardID;
    }
    public String getWardName() {return wardName;}

}


