package HMS;

import java.text.ParseException;
import java.util.*;
import java.util.Date;
import java.io.*;
import java.text.SimpleDateFormat;


public class Controller {
    //declare arraylist
    private ArrayList hrList = new ArrayList();
    private ArrayList doctorList = new ArrayList();
    private ArrayList receptionistList = new ArrayList();
    private ArrayList patientList = new ArrayList();
    private ArrayList treatmentList = new ArrayList();
    private ArrayList roomList = new ArrayList();
    private ArrayList invoiceList = new ArrayList();
    private ArrayList paymentList = new ArrayList();
    private ArrayList visitationList = new ArrayList();
    private ArrayList patientTreatmentList = new ArrayList();
    private ArrayList wardList = new ArrayList();
    private ArrayList wardPatientList = new ArrayList();
    private ArrayList reportList = new ArrayList();

    //declare files as constants(final)
    private final String hrFile = "hr.txt";
    private final String doctorFile = "doctor.txt";
    private final String receptionistFile = "receptionist.txt";
    private final String patientFile = "patient.txt";
    private final String treatmentFile = "treatment.txt";
    private final String wardFile = "ward.txt";
    private final String invoiceFile = "invoice.txt";
    private final String paymentFile = "payment.txt";
    private final String loggingFile = "logging.txt";
    private final String visitationFile = "visitation.txt";
    private final String patientTreatmentFile = "patientTreatment.txt";
    private final String wardPatientFile = "wardPatient.txt";

    //declare Buffered Reader/writer
    private BufferedReader reader;
    private BufferedWriter writer;
    private Formatter output;

    //declare authentication variables
    private int hrId = 0;
    private int doctorId = 0;
    private int receptionistId = 0;
    private HR theHR;
    private Doctor theDoctor;
    private Receptionist theReceptionist;
    private Patient thePatient;

    private Employee loggedUser = null;

    private boolean isLoggedIN = false;
    private boolean isHR = false;
    private boolean isDoctor = false;
    private boolean isReceptionist = false;

    private Scanner console;

    private static int loginFailed = 0;

    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final String DATETIME_FORMAT = "dd/MM/yyyy HH:mm:ss";
    private static final String MONTH_FORMAT = "MM/yyyy";
    private SimpleDateFormat dateformat = new SimpleDateFormat(DATE_FORMAT);
    private SimpleDateFormat datetimeformat = new SimpleDateFormat(DATETIME_FORMAT);
    private SimpleDateFormat monthformat = new SimpleDateFormat(MONTH_FORMAT);
    private Employee p;

    //default constructor
    public Controller() {
        console = new Scanner(System.in);
        console.useDelimiter("\n");

    }

    //clear screen
    public void clearScreen() {
        System.out.println('\u000c');
    }

    //create pause for some seconds
    public void pause(long secs) {
        try {
            Thread.currentThread().sleep(secs * 3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //load hr file
    public boolean loadhrFile() {
        try {
            hrList.clear();
            reader = new BufferedReader(new FileReader(new File(hrFile)));
            String line = reader.readLine();
            while (line != null) {
                String[] record = line.split(",");

                int employeeId = Integer.parseInt(record[0].trim());
                String loginId = record[1].trim();
                String password = record[2].trim();
                String fullName = record[3].trim();
                String role = record[4].trim();

                theHR = new HR(employeeId, loginId, password, fullName, role);
                hrList.add(theHR);
                line = reader.readLine();
            }
            reader.close();
            return true;
        } catch (IOException e) {
            System.out.println(e.toString() + ":" + e.getMessage());
            return false;
        }
    }

    //load Doctor file
    public boolean loadDoctorFile() {
        try {
            doctorList.clear();
            reader = new BufferedReader(new FileReader(new File(doctorFile)));
            String line = reader.readLine();
            while (line != null) {
                String[] record = line.split(",");
                //String[] record = csvSplit(line);
                int employeeId = Integer.parseInt(record[0].trim());
                String loginId = record[1].trim();
                String password = record[2].trim();
                String fullName = record[3].trim();
                String role = record[4].trim();

                theDoctor = new Doctor(employeeId, loginId, password, fullName, role);
                doctorList.add(theDoctor);
                line = reader.readLine();
            }
            reader.close();
            return true;
        } catch (IOException e) {
            System.out.println(e.toString() + ":" + e.getMessage());
            return false;
        }
    }

    //load Receptionist file
    public boolean loadReceptionistFile() {
        try {
            receptionistList.clear();
            reader = new BufferedReader(new FileReader(new File(receptionistFile)));
            String line = reader.readLine();
            while (line != null) {
                String[] record = line.split(",");
                //String[] record = csvSplit(line);
                int employeeId = Integer.parseInt(record[0].trim());
                String loginId = record[1].trim();
                String password = record[2].trim();
                String fullName = record[3].trim();
                String role = record[4].trim();

                theReceptionist = new Receptionist(employeeId, loginId, password, fullName, role);
                receptionistList.add(theReceptionist);
                line = reader.readLine();
            }
            reader.close();
            return true;
        } catch (IOException e) {
            System.out.println(e.toString() + ":" + e.getMessage());
            return false;
        }
    }


    //load patient file
    public boolean loadPatientFile() {
        try {
            patientList.clear();
            reader = new BufferedReader(new FileReader(new File(patientFile)));
            String line = reader.readLine();
            while (line != null) {
                String[] record = line.split(",");

                int patientID = Integer.parseInt(record[0].trim());
                String fullName = record[1].trim();
                String phoneNumber = record[2].trim();
                String patientStatus = record[3].trim();

                thePatient = new Patient(patientID, fullName, phoneNumber, patientStatus);
                patientList.add(thePatient);
                line = reader.readLine();
            }
            reader.close();
            return true;

        } catch (IOException e) {
            System.out.println(e.toString() + ":" + e.getMessage());
            return false;
        }
    }

    //load treatment file

    public boolean loadTreatmentFile() {
        try {
            treatmentList.clear();
            reader = new BufferedReader(new FileReader(new File(treatmentFile)));
            String line = reader.readLine();
            while (line != null) {
                String[] record = line.split(",");

                int treatmentNumber = Integer.parseInt(record[0].trim());
                String treatmentName = record[1].trim();
                String description = record[2].trim();
                String treatCost = record[3].trim();

                Treatment theTreatment = new Treatment(treatmentNumber, treatmentName, description, treatCost);
                treatmentList.add(theTreatment);
                line = reader.readLine();
            }
            reader.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.toString() + ":" + e.getMessage());
            return false;
        }
    }

    //visitation file
    public boolean loadVisitationFile() {
        try {
            visitationList.clear();
            reader = new BufferedReader(new FileReader(new File(visitationFile)));
            String line = reader.readLine();

            while (line != null) {
                String[] record = line.split(",");

                int vID = Integer.parseInt(record[0].trim());
                String pName = record[1].trim();
                String vDate = record[2].trim();
                String vDoctor = record[3].trim();
                String vPurpose = record[4].trim();

                Visitation objVisitation = new Visitation(vID, pName, vDate, vDoctor, vPurpose);
                visitationList.add(objVisitation);
                line = reader.readLine();
            }
            reader.close();
            return true;

        } catch (IOException e) {
            System.out.println(e.toString() + ":" + e.getMessage());
            return false;
        }
    }

    //PatientTreatment file
    public boolean loadPatientTreatmentFile() {
        try {
            patientTreatmentList.clear();
            reader = new BufferedReader(new FileReader(new File(patientTreatmentFile)));
            String line = reader.readLine();

            while (line != null) {
                String[] record = line.split(",");

                int ptID = Integer.parseInt(record[0].trim());
                int pID = Integer.parseInt(record[1].trim());
                String ptName = record[2].trim();
                int pDoctorID = Integer.parseInt(record[3].trim());
                String pCost = record[4].trim();
                String startDate = record[5].trim();
                String endDate = record[6].trim();
                String tStatus = record[7].trim();

                PatientTreatment objPT = new PatientTreatment(ptID, pID, ptName, pDoctorID, pCost, startDate, endDate, tStatus);
                patientTreatmentList.add(objPT);
                line = reader.readLine();
            }
            reader.close();
            return true;

        } catch (IOException e) {
            System.out.println(e.toString() + ":" + e.getMessage());
            return false;
        }
    }

    //load payment file
    public boolean loadpaymentFile() {
        try {
            paymentList.clear();
            reader = new BufferedReader(new FileReader(new File(paymentFile)));
            String line = reader.readLine();
            while (line != null) {
                String[] record = line.split(",");

                int paymentID = Integer.parseInt(record[0].trim());
                int patientID = Integer.parseInt(record[1].trim());
                double paymentAmount = Double.parseDouble(record[2].trim());
                Date paymentDateTime = datetimeformat.parse(record[3].trim());

                Payment thePayment = new Payment(paymentID, patientID, paymentAmount, paymentDateTime);
                paymentList.add(thePayment);
                line = reader.readLine();
            }
            reader.close();
            return true;

        } catch (Exception e) {
            System.out.println(e.toString() + ": " + e.getMessage());
            return false;
        }
    }


    //ward file
    public boolean loadWardFile() {
        try {
            wardList.clear();
            reader = new BufferedReader(new FileReader(new File(wardFile)));
            String line = reader.readLine();
            while (line != null) {
                String[] record = line.split(",");

                int wardID = Integer.parseInt(record[0].trim());
                String wardName = record[1].trim();


                Ward theWard = new Ward(wardID, wardName);
                wardList.add(theWard);
                line = reader.readLine();
            }
            reader.close();
            return true;

        } catch (IOException e) {
            System.out.println(e.toString() + ":" + e.getMessage());
            return false;
        }
    }

    //Patient ward file
    public boolean loadWardPatientFile() {
        try {
            wardPatientList.clear();
            reader = new BufferedReader(new FileReader(new File(wardPatientFile)));
            String line = reader.readLine();
            while (line != null) {
                String[] record = line.split(",");

                int admitID = Integer.parseInt(record[0].trim());
                int wardID = Integer.parseInt(record[1].trim());
                int patientID = Integer.parseInt(record[2].trim());
                String dateAdmitted = record[3].trim();
                String dateDischarged = record[4].trim();

                WardPatient wp = new WardPatient(admitID, wardID, patientID, dateAdmitted, dateDischarged);
                wardPatientList.add(wp);
                line = reader.readLine();
            }
            reader.close();
            return true;
        } catch (IOException e) {
            System.out.println(e.toString() + ":" + e.getMessage());
            return false;
        }
    }

    //load invoice file
    public boolean loadinvoiceFile() {
        try {
            invoiceList.clear();
            reader = new BufferedReader(new FileReader(new File(invoiceFile)));
            String line = reader.readLine();
            while (line != null) {
                String[] record = line.split(",");

                int invoiceID = Integer.parseInt(record[0].trim());
                int patientID = Integer.parseInt(record[1].trim());
                int treatmentID = Integer.parseInt(record[2].trim());
                double invoicedAmount = Double.parseDouble(record[3].trim());
                Date visitedDate = dateformat.parse(record[4].trim());

                Invoice theInvoice = new Invoice(invoiceID, patientID, treatmentID, invoicedAmount, visitedDate);
                invoiceList.add(theInvoice);
                line = reader.readLine();
            }
            reader.close();
            return true;

        } catch (Exception e) {
            System.out.println(e.toString() + ":" + e.getMessage());
            return false;
        }
    }


    public int checkLoadingStatus() {
        if (!loadhrFile()) {
            return -1;
        }
        if (!loadDoctorFile()) {
            return -2;
        }
        if (!loadPatientFile()) {
            return -3;
        }
        if (!loadTreatmentFile()) {
            return -4;
        }
        if (!loadReceptionistFile()) {
            return -5;
        }
        if (!loadVisitationFile()) {
            return -6;
        }
        if (!loadPatientTreatmentFile()) {
            return -7;
        }
        if (!loadpaymentFile()) {
            return -8;
        }
        if (!loadinvoiceFile()) {
            return -9;
        }
        if (!loadWardFile()) {
            return -10;
        }
        if (!loadWardPatientFile()) {
            return -11;
        }
        return 1;
    }

    //hr authentication
    public void authenticateHR() {
        //Scanner console = new Scanner(System.in);
        boolean authenticate = false;

        if (loginFailed < 3) {
            HR h = null;
            boolean found = false;
            hrList = gethrList();
            Iterator it = hrList.iterator();

            String loginId = "";
            String password = "";
            System.out.println("Please enter hr ID: ");
            loginId = console.nextLine();
            System.out.println("Please enter hr Password: ");
            password = console.nextLine();

            while (it.hasNext() && !found) {
                h = (HR) it.next();
                if (h.getLoginId().equals(loginId) && h.getPassword().equals(password)) {
                    authenticate = true;
                    loggedUser = h;
                    hrId = h.getEmployeeId();
                    found = true;
                }
            }

            if (authenticate == true) {
                System.out.println("Login successful! Please make a selection.");

                isLoggedIN = true;
                isHR = true;
                pause(1);
                clearScreen();
                showMenu();
            } else {
                authenticate = false;
                loginFailed = loginFailed + 1;

                System.out.println("Login failed. Please try again!!!");

                pause(1);
                clearScreen();
                authenticateHR();
            }
        } else {
            System.out.println("You have violated the login rule: \"3 failed login attempted!!!\"");
            pause(1);
            System.out.println("System will shutdown now!!!");
            System.exit(1);
        }

    }

    // doctor authentication
    public void authenticateDoctor() {
        //Scanner console = new Scanner(System.in);
        boolean authenticate = false;

        if (loginFailed < 3) {
            Doctor d = null;
            boolean found = false;
            doctorList = getDoctorList();
            Iterator it = doctorList.iterator();

            String loginId = "";
            String password = "";
            System.out.println("Please enter Doctor ID: ");
            loginId = console.nextLine();
            System.out.println("Please enter Doctor Password: ");
            password = console.nextLine();

            while (it.hasNext() && !found) {
                d = (Doctor) it.next();
                if (d.getLoginId().equals(loginId) && d.getPassword().equals(password)) {
                    authenticate = true;
                    loggedUser = d;
                    doctorId = d.getEmployeeId();
                    found = true;
                }
            }

            if (authenticate == true) {
                System.out.println("Login successful! Please make a selection.");

                isLoggedIN = true;
                isDoctor = true;
                pause(1);
                clearScreen();
                showMenu();

            } else {
                authenticate = false;
                loginFailed = loginFailed + 1;

                System.out.println("Login failed. Please try again!!!");

                pause(1);
                clearScreen();
                authenticateDoctor();
            }
        } else {
            System.out.println("You have violated the login rule: \"3 failed login attempted!!!\"");
            pause(1);
            System.out.println("System will shutdown now!!!");
            System.exit(1);
        }

    }

    // receptionist authentication
    public void authenticateReceptionist() {
        //Scanner console = new Scanner(System.in);
        boolean authenticate = false;

        if (loginFailed < 3) {
            Receptionist r = null;
            boolean found = false;
            receptionistList = getReceptionistList();
            Iterator it = receptionistList.iterator();

            String loginId = "";
            String password = "";
            System.out.println("Please enter Receptionist ID: ");
            loginId = console.nextLine();
            System.out.println("Please enter Receptionist Password: ");
            password = console.nextLine();

            while (it.hasNext() && !found) {
                r = (Receptionist) it.next();
                if (r.getLoginId().equals(loginId) && r.getPassword().equals(password)) {
                    authenticate = true;
                    loggedUser = r;
                    receptionistId = r.getEmployeeId();
                    found = true;
                }
            }

            if (authenticate == true) {
                System.out.println("Login successful! Please make a selection.");

                isLoggedIN = true;
                isReceptionist = true;
                pause(1);
                clearScreen();
                showMenu();

            } else {
                authenticate = false;
                loginFailed = loginFailed + 1;

                System.out.println("Login failed. Please try again!!!");

                pause(1);
                clearScreen();
                authenticateDoctor();
            }
        } else {
            System.out.println("You have violated the login rule: \"3 failed login attempted!!!\"");
            pause(1);
            System.out.println("System will shutdown now!!!");
            System.exit(1);
        }

    }


    // logout
    public void logout() {
        hrId = 0;
        doctorId = 0;
        isLoggedIN = false;
        isHR = false;
        isDoctor = false;
        clearScreen();
        showChooseMenu();
    }

    // check authenticate
    public void checkIfAuthenticate() {
        if (isLoggedIN == false) {
            System.out.println("Please login first!!!");
            pause(2);
            showChooseMenu();
        }
    }

    //check if the user is HR,doctor or Receptionist
    public void checkUser() {
        if (isHR == false) {
            //  System.out.println("You can't do this !");
            showMenu();
        } else if (isDoctor == false) {
            // System.out.println("You can't do this");
        } else if (isReceptionist == false) {
        }
    }

    // get hr list
    public ArrayList gethrList() {
        return hrList;
    }

    // get doctor list
    public ArrayList getDoctorList() {
        return doctorList;
    }

    // get receptionist list
    public ArrayList getReceptionistList() {
        return receptionistList;
    }

    //get visitation list
    public ArrayList getVisitationList() {
        return visitationList;
    }

    //get Patient treatment list
    public ArrayList getPatientTreatmentList() {
        return patientTreatmentList;
    }

    // show the menu for login as hr, doctor or receptionist
    public void showChooseMenu() {
        // console = new Scanner(System.in);
        System.out.println("**************************************************************");
        System.out.println("*                        HMS System                         *");
        System.out.println("**************************************************************");
        System.out.println("");

        System.out.println("Please choose from --(H)R | (D)octor | (R)eceptionist | (Q)uit :");
        String choice = console.nextLine();

        switch (choice.toLowerCase()) {
            case "h":
                clearScreen();
                authenticateHR();
                break;
            case "d":
                clearScreen();
                authenticateDoctor();
                break;
            case "r":
                clearScreen();
                authenticateReceptionist();
                break;
            case "q":
                System.out.println("");
                System.out.println("                 ======Program Ended=======               ");
                System.exit(1);
                break;
            default:
                System.out.println("Your choice is not applicable. Please try again!");
                pause(2);
                clearScreen();
                showChooseMenu();
        }
    }
//show hr menu

    public void showMenu() {
        //Scanner console = new Scanner(System.in);
        String selection = "";

        System.out.println("*****************************************************************");
        System.out.println("*                       [HMS System]                           *");
        System.out.println("*                         Main Menu                             *");
        System.out.println("*****************************************************************");
        System.out.println("");
        System.out.println("What do you want to do?");

        if (isDoctor) {
            System.out.println("[1]  View Patient List");
            System.out.println("[2]  Register New Patient");
            System.out.println("[3]  Delete Patient");
            System.out.println("[4]  Update Patient Detail");
            System.out.println("[5]  Search Patient");
            System.out.println("[6]  View Treatment");
            System.out.println("[7]  Add Treatment");
            System.out.println("[8]  Update Treatment");
            System.out.println("[9]  Search Treatment");
            System.out.println("[10] Delete Treatment");
            System.out.println("[11] Add visitation");
            System.out.println("[12] Update visitation");
            System.out.println("[13] Delete visitation");
            System.out.println("[14] Search visitation");
            System.out.println("[15] Add Patient Treatment");
            System.out.println("[16] Update Patient Treatment");
            System.out.println("[17] Delete Patient Treatment");
            System.out.println("[18] Search Patient Treatment");
            System.out.println("[19] Create invoice");
            System.out.println("[20] Make Payment");
            System.out.println("[21] Add Patient to ward");
            System.out.println("[22] View Patient by ward");
            System.out.println("[23] View Patient Reports");


        }
        if (isReceptionist) {
            System.out.println("[1]  View Patient List");
            System.out.println("[2]  Register New Patient");
            System.out.println("[3]  Delete Patient");
            System.out.println("[4]  Update Patient Detail");
            System.out.println("[5]  Search Patient");
            System.out.println("[6]  View Treatment");
            System.out.println("[7]  Add Treatment");
            System.out.println("[8]  Update Treatment");
            System.out.println("[9]  Search Treatment");
            System.out.println("[10] Delete Treatment");
            System.out.println("[11] Add visitation");
            System.out.println("[12] Update visitation");
            System.out.println("[13] Delete visitation");
            System.out.println("[14] Search visitation");
            System.out.println("[15] Add Patient Treatment");
            System.out.println("[16] Update Patient Treatment");
            System.out.println("[17] Delete Patient Treatment");
            System.out.println("[18] Search Patient Treatment");
            System.out.println("[19] Create invoice");
            System.out.println("[20] Make Payment");
            System.out.println("[21] Add Patient to ward");
            System.out.println("[22] View Patient by ward");
            System.out.println("[23] View Patient Reports");


        }

        if (isHR) {
            System.out.println("[a] View Doctor list");
            System.out.println("[b] Add Doctor");
            System.out.println("[c] Update doctor");
            System.out.println("[d] Delete doctor");
            System.out.println("[e] Search doctor");
            System.out.println("[f] View Receptionist list");
            System.out.println("[g] Add Receptionist");
            System.out.println("[h] Update Receptionist");
            System.out.println("[i] Delete Receptionist");
            System.out.println("[j] Search Receptionist");


        }

        System.out.println("[m]  Return to Main Menu");
        System.out.println("[o]  Log out");
        System.out.println("[q]  Quit the program");
        System.out.println("");
        System.out.println("Please enter the menu code: >");

        try {
            selection = console.nextLine();

        } catch (Exception e) {
            System.out.println("Please enter the number listed on the menu.");
            System.out.println(e.toString() + ": " + e.getMessage());
            pause(2);
            clearScreen();
            showMenu();
        }

        switch (selection) {
            case "1":
                showPatientList();
                clearScreen();
                showMenu();
                break;
            case "2":
                addNewPatient();
                clearScreen();
                showMenu();
                break;
            case "3":
                try {
                    deletePatient();
                } catch (Exception e) {
                    System.out.println(e.toString() + ": " + e.getMessage());
                }
                clearScreen();
                showMenu();
                break;
            case "4":
                try {
                    updatePatientDetail();
                } catch (Exception e) {
                    System.out.println(e.toString() + ": " + e.getMessage());
                }
                clearScreen();
                showMenu();
                break;
            case "5":
                searchPatient();
                clearScreen();
                showMenu();
                break;
            case "6":
                showTreatmentList();
                clearScreen();
                showMenu();
                break;
            case "7":
                addNewTreatment();
                clearScreen();
                showMenu();
                break;
            case "8":
                //   updateTreatment();
                clearScreen();
                showMenu();
                break;
            case "9":
                searchTreatment();
                clearScreen();
                showMenu();
                break;
            case "10":
                try {
                    deleteTreatment();
                } catch (Exception e) {
                    System.out.println(e.toString() + ": " + e.getMessage());
                }
                clearScreen();
                showMenu();
                break;
            case "11":
                try {
                    addNewVisitation();
                } catch (Exception e) {
                    System.out.println(e.toString() + ": " + e.getMessage());
                }
                clearScreen();
                showMenu();
                break;
            case "12":
                try {
                    updateVisitation();
                } catch (Exception e) {
                    System.out.println(e.toString() + ": " + e.getMessage());
                }
                clearScreen();
                showMenu();
                break;
            case "13":
                try {
                    deleteVisitation();
                } catch (Exception e) {
                    System.out.println(e.toString() + ": " + e.getMessage());
                }
                clearScreen();
                showMenu();
                break;
            case "14":
                searchVisitation();
                clearScreen();
                showMenu();
                break;
            case "15":
                try {
                    addNewPatientTreatment();
                } catch (Exception e) {
                    System.out.println(e.toString() + ": " + e.getMessage());
                }
                clearScreen();
                showMenu();
                break;
            case "16":
                try {
                    // updatePatientTreatment();
                } catch (Exception e) {
                    System.out.println(e.toString() + ": " + e.getMessage());
                }
                clearScreen();
                showMenu();
                break;
            case "17":
                try {
                    deletePatientTreatment();
                } catch (Exception e) {
                    System.out.println(e.toString() + ": " + e.getMessage());
                }
                clearScreen();
                showMenu();
                break;
            case "18":
                searchPatientTreatment();
                clearScreen();
                showMenu();
                break;
            case "19":
                try {
                    createInvoice();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                clearScreen();
                showMenu();
                break;
            case "20":
                // make a payment
                try {
                    makePayment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                clearScreen();
                showMenu();
                break;
            case "21":
                //adding patient to ward
                addPatientToWard();
                clearScreen();
                showMenu();
                break;
            case "22":
                //view patient by ward
                viewPatientByWard();
                clearScreen();
                showMenu();
            case "23":
                //view patient reports
                viewReport();
                clearScreen();
                showMenu();
                break;
            case "a":
                checkUser();
                showDoctorList();
                clearScreen();
                showMenu();
                break;
            case "b":
                checkUser();
                addNewDoctor();
                clearScreen();
                showMenu();
                break;
            case "c":
                checkUser();
                try {
                    updateDoctor();
                } catch (Exception e) {
                    System.out.println(e.toString() + ":" + e.getMessage());
                }
                clearScreen();
                showMenu();
                break;
            case "d":
                // delete doctor
                checkUser();
                try {
                    deleteDoctor();
                } catch (Exception e) {
                    System.out.println(e.toString() + ": " + e.getMessage());
                }
                clearScreen();
                showMenu();
                break;
            case "e":
                // search doctor
                checkUser();
                try {
                    searchDoctor();
                } catch (Exception e) {
                    System.out.println(e.toString() + ": " + e.getMessage());
                }
                clearScreen();
                showMenu();
                break;
            case "f":
                checkUser();
                showReceptionistList();
                clearScreen();
                showMenu();
                break;
            case "g":
                checkUser();
                addNewReceptionist();
                clearScreen();
                showMenu();
                break;
            case "h":
                checkUser();
                try {
                    updateReceptionist();
                } catch (Exception e) {
                    System.out.println(e.toString() + ":" + e.getMessage());
                }
                clearScreen();
                showMenu();
                break;
            case "i":
                // delete receptionist
                checkUser();
                try {
                    deleteReceptionist();
                } catch (Exception e) {
                    System.out.println(e.toString() + ": " + e.getMessage());
                }
                clearScreen();
                showMenu();
                break;
            case "j":
                // search Receptionist
                checkUser();
                try {
                    searchReceptionist();
                } catch (Exception e) {
                    System.out.println(e.toString() + ": " + e.getMessage());
                }
                clearScreen();
                showMenu();
                break;

            case "m":
                pause(2);
                clearScreen();
                showChooseMenu();
                break;
            case "o":
                logout();
                break;
            case "q":
                quitProgram();
                break;
            default:
                System.out.println("The selected number is not part of the menu. Please try again");
                pause(2);
                clearScreen();
                showMenu();
                break;
        }
    }

    // HRQuitProgram
    public void quitProgram() {
        //Scanner console = new Scanner(System.in);
        String input = "";
        System.out.println("Are you sure you want to quit the program (Y/N)?");

        try {
            input = console.nextLine();

            if (input.equalsIgnoreCase("Y")) {
                clearScreen();
                System.out.println("Program ended!!! You can close the console now");
                System.exit(0);
            } else if (input.equalsIgnoreCase("N")) {
                clearScreen();
                showMenu();
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Enter 'Y' to quit or 'N' to continue the program");
            System.out.println("");
            quitProgram();
        }
    }

    // show patient list
    public void showPatientList() {
        clearScreen();
        int count = 0;
        Iterator it = getPatient().iterator();

        System.out.println("----------------------------------------------------------------------");

        while (it.hasNext()) {
            count++;
            Patient p = (Patient) it.next();
            System.out.println(String.format("%-30s: %s", "@ Patient Id", p.getPatientID()));
            System.out.println(String.format("%-30s: %s", "@ Patient Full Name", p.getFullName()));
            System.out.println(String.format("%-30s: %s", "@ Patient Phone Number", p.getPhoneNumber()));
            System.out.println(String.format("%-30s: %s", "@ Patient Status", p.getPatientStatus()));


            System.out.println("-----------------------------------------------------------------------");

        }
        System.out.println("-----------------------------------------------------------------------");
        System.out.println(String.format("%-30s: %s", "@ Total number of Patient", count));
        System.out.println("");
    }

    // search Patient
    public void searchPatient() {
        clearScreen();
        System.out.print("Please enter patient's name: ");
        String name = console.nextLine();

        Iterator it = patientList.iterator();
        int count = 0;

        System.out.println("-------------------------------------------------------------------------");

        while (it.hasNext()) {
            Patient p = (Patient) it.next();
            if (p.getFullName().toLowerCase().contains(name.toLowerCase())) {
                count++;
                System.out.println(String.format("%-30s: %s", "@ Patient Id", p.getPatientID()));
                System.out.println(String.format("%-30s: %s", "@ Patient Full Name", p.getFullName()));
                System.out.println(String.format("%-30s: %s", "@ Patient Phone Number", p.getPhoneNumber()));
                System.out.println(String.format("%-30s: %s", "@ Patient Status", p.getPatientStatus()));


                System.out.println("-----------------------------------------------------------------------");
            }
        }
        System.out.println("-----------------------------------------------------------------------");
        System.out.println(String.format("%-30s: %s", "@ Total number of Patient", count));
        System.out.println("");

    }

    // add new Patient
    public void addNewPatient() {
        clearScreen();
        //Scanner console = new Scanner(System.in);
        Iterator it = patientList.iterator();
        boolean loop = false;

        int tempID = 0;
        while (it.hasNext()) {
            Patient p = (Patient) it.next();
            tempID = p.getPatientID();
        }
        tempID++;
        int patientID = tempID;

        System.out.println("Please enter patient's full name: ");
        String fullName = console.nextLine();
        System.out.println("Please enter patient's phone number: ");
        String phoneNumber = console.nextLine();
        String patientStatus = "New";


        try {
            output = new Formatter(new FileWriter(patientFile, true));
            output.format("%d,%s,%s,%s\r\n", patientID, fullName, phoneNumber, patientStatus);
            output.close();
            System.out.println("Patient added successfully!");

        } catch (Exception e) {
            System.out.println(e.toString() + ":" + e.getMessage());
        }
        patientList.clear();
        loadPatientFile();
    }

    // delete patient
    public void deletePatient() throws Exception {
        clearScreen();
        //Scanner console = new Scanner(System.in);

        boolean done = false;
        output = new Formatter(new FileWriter("patientChanged.txt", true));

        System.out.println(String.format("%-20s%-20s%-20s", "Patient ID", "Patient Name", "Phone Number"));

        System.out.println("--------------------------------------------------------------------");

        Iterator it1 = getPatient().iterator();
        while (it1.hasNext()) {
            Patient p = (Patient) it1.next();
            System.out.println(String.format("%-20s%-20s%-20s", p.getPatientID(), p.getFullName(), p.getPhoneNumber()));
        }
        System.out.println();

        // delete a record from the text file based on the patientId
        System.out.println("Please enter the patient's ID number to delete the patient: ");
        int patientID = console.nextInt();

        Iterator it = getPatient().iterator();
        while (it.hasNext()) {
            Patient p = (Patient) it.next();
            if (!(p.getPatientID() == (patientID))) {
                output.format("%d,%s,%s,%s\r\n", p.getPatientID(), p.getFullName(), p.getPhoneNumber(), p.getPatientStatus());

            } else {
                done = true;
            }
        }

        output.close();
        if (done == true) {
            //clearScreen();
            System.out.println("Patient deleted successfully!");


            // delete old file
            File fileToDelete = new File("patient.txt");
            boolean delete = fileToDelete.delete();

            // rename patientChanged.txt to patient.txt
            File fileToRename = new File("patientChanged.txt");
            File renamedFile = new File("patient.txt");
            boolean rename = fileToRename.renameTo(renamedFile);

            patientList.clear();
            loadPatientFile();
        } else {
            System.out.println("Patient not found!!!");
        }

    }

    // add patient to ward
    public void addPatientToWard() {
        clearScreen();
        //Scanner console = new Scanner(System.in);
        Iterator it = wardPatientList.iterator();

        int tempID = 0;
        String treatmentStartDate = "";
        String treatmentEndDate = "";

        while (it.hasNext()) {
            WardPatient g = (WardPatient) it.next();
            tempID = g.getAdmitID();
        }
        tempID++;
        int patientWardRecordID = tempID;

        System.out.print("Please select the Ward (" + printWard() + "):");
        int wardID = inputWardID();

        System.out.print("Please select Patient (" + printPatient() + "):");
        int patientID = inputPatientID();

        System.out.print("Please enter admit date (dd/mm/yyyy) : ");
        boolean isValid = false;
        do {
            try {
                String input = console.nextLine();
                DateValidator dateValidator = new DateValidator();
                treatmentStartDate = dateValidator.validate(input);
                isValid = true;
            } catch (ParseException e) {
                System.out.println("Input date is invalid. Please enter the 'dd/mm/yyyy' format!");
                System.out.print("Please enter admit date : ");
            }
        } while (!isValid);

        System.out.print("Please enter discharge date (dd/mm/yyyy) : ");
        boolean isValidEndDate = false;
        boolean isendDateGreater = false;
        do {
            try {
                String input = console.nextLine();

                if (input.isEmpty()) {
                    isendDateGreater = true;
                    isValidEndDate = true;
                    treatmentEndDate = "Ongoing";

                } else {

                    DateValidator dateValidator = new DateValidator();
                    treatmentEndDate = dateValidator.validate(input);
                    isValidEndDate = true;
                    isendDateGreater = dateValidator.isSecondDateGreater(treatmentStartDate, treatmentEndDate);

                    if (!isendDateGreater) {
                        System.out.println("Discharge date cannot be earlier than admit date. Please enter a valid discharge in 'dd/mm/yyyy' format!");
                        System.out.print("Please enter discharge date : ");
                    }
                }


            } catch (ParseException e) {
                System.out.println("Input date is invalid. Please enter the 'dd/mm/yyyy' format!");
                System.out.print("Please enter discharge date : ");
            }
        } while (!isValidEndDate || !isendDateGreater);

        try {
            output = new Formatter(new FileWriter(wardPatientFile, true));
            output.format("%d,%d,%d,%s,%s\r\n", patientWardRecordID, wardID, patientID, treatmentStartDate, treatmentEndDate);
            output.close();
            System.out.println("Patient added to ward successfully!");

        } catch (Exception e) {
            System.out.println(e.toString() + ":" + e.getMessage());
        }
        wardPatientList.clear();
        loadWardPatientFile();
    }

    // view patient by ward
    public void viewPatientByWard() {
        clearScreen();
        System.out.print("Please enter Patient's Name to search : ");
        String findStr = console.nextLine();

        Iterator it = getWardPatientList().iterator();
        int count = 0;

        System.out.println("-------------------------------------------------------------------------");

        while (it.hasNext()) {
            WardPatient g = (WardPatient) it.next();
            Patient c = getPatient(g.getPatientID());
            Ward d = getWard(g.getWardID());

            if (c.getFullName().toLowerCase().contains(findStr.toLowerCase())) {
                count++;
                System.out.println(String.format("%-30s: %s", "@ Patient admit ID", g.getAdmitID()));
                System.out.println(String.format("%-30s: %s", "@ Patient Name", c.getFullName()));
                System.out.println(String.format("%-30s: %s", "@ Ward", d.getWardName()));
                System.out.println(String.format("%-30s: %s", "@ Admit date", g.getDateTransferred()));
                System.out.println(String.format("%-30s: %s", "@ Discharge date", g.getDateDischarged()));

                System.out.println("---------------------------------------------------------------------------");
            }
        }
        System.out.println("-----------------------------------------------------------------------");
        System.out.println(String.format("%-30s: %s", "@ Total number of records", count));
        System.out.println("");

    }

    // get Ward by wardID
    private Ward getWard(int wardID) {
        Ward selected = null;

        Iterator<Ward> it = wardList.iterator();

        while (it.hasNext()) {
            Ward a = (Ward) it.next();
            if (a.getWardID() == wardID) {
                selected = a;
                break;
            }
        }

        return selected;
    }

    //create invoice
    private void createInvoice() throws Exception {


        System.out.println("------------------------------------------------------------------");
        System.out.println("                        Create an Invoice                            ");
        System.out.println("------------------------------------------------------------------");

        try {

            System.out.print("Please enter the Patient ID for creating invoice :");
            int pd = inputPatientID();

            System.out.print("Please enter the Treatment ID for creating invoice :");
            int treatmentdetailId = inputTreatmentDetailIDFromConsole();

            String invoicedAmount;

            Iterator<PatientTreatment> it = patientTreatmentList.iterator();
            while (it.hasNext()) {
                PatientTreatment cg = (PatientTreatment) it.next();
                if (cg.getPatientID() == pd && cg.getPatientTreatmentID() == treatmentdetailId) {
                    ;

                    loadPatientTreatmentFile();
                    loadTreatmentFile();
                }
            }


            Iterator<Invoice> it1 = invoiceList.iterator();
            int tempID = 0;
            while (it.hasNext()) {
                Invoice invoice = (Invoice) it1.next();
                tempID = invoice.getInvoiceID();
            }
            tempID++;
            int invoiceId = tempID;


            Treatment treatDetail = getT(treatmentdetailId);
            invoicedAmount = treatDetail.getTreatmentCost();

            try {
                output = new Formatter(new FileWriter(invoiceFile, true));

                output.format("%d,%d,%d,%s,%s\r\n", invoiceId, pd, treatmentdetailId, invoicedAmount, datetimeformat.format(new Date()));
                System.out.println("Invoice created successfully!!!");

                System.out.println("Patient Invoice");
                System.out.println("-------------------");
                System.out.println("-------------------");
                System.out.println(String.format("%-30s: %s", "Invoice Id", invoiceId));
                System.out.println(String.format("%-30s: %s", "Patient Id", pd));
                System.out.println(String.format("%-30s: %s", "Treatment Id", treatmentdetailId));
                System.out.println(String.format("%-30s: %s", "Amount ", invoicedAmount));
                System.out.println(String.format("%-30s: %s", "Date Generated", datetimeformat.format(new Date())));

                output.close();
                invoiceList.clear();
                loadinvoiceFile();

            } catch (IOException e) {
                System.out.println(e.toString() + ":" + e.getMessage());
            }


            loadPatientFile();
            loadTreatmentFile();

        } catch (Exception e) {
            System.out.println(e.toString() + ":" + e.getMessage());
        }
    }

    private Treatment getT(int tNumber) {
        Treatment selected = null;

        Iterator<Treatment> it = treatmentList.iterator();

        while (it.hasNext()) {
            Treatment g = (Treatment) it.next();
            if (g.getTreatmentID() == tNumber) {
                selected = g;
                break;
            }
        }
        return selected;
    }

    // update patient's detail
    public void updatePatientDetail() throws Exception {
        //Scanner console = new Scanner(System.in);
        try {
            boolean done = false;

            System.out.println("Please enter the Patient ID that you wish to update : ");
            int patientID = inputPatientIDFromConsole();
//        console.nextLine();
            Patient p = getPatient(patientID);
            System.out.println("------------------------------------------------------------");
            System.out.print("@ Patient Full Name : [" + p.getFullName() + "] ");
            String fullName = console.nextLine();
            System.out.print("@ Patient Phone Number : [" + p.getPhoneNumber() + "] ");
            String phoneNumber = console.nextLine();
            String patientStatus = getPatientStatus(p.getPatientStatus());

            System.out.println("patient Status : " + patientStatus);

            if (fullName.length() <= 0 || fullName == "") fullName = p.getFullName();
            if (phoneNumber.length() <= 0 || phoneNumber == "") phoneNumber = p.getPhoneNumber();
            if (patientStatus.length() <= 0 || patientStatus == "") patientStatus = p.getPatientStatus();


            Patient uPatient = new Patient(p.getPatientID(), fullName, phoneNumber, patientStatus);

            done = updatePatient(uPatient);

            if (done) {
                System.out.println("Patient Detail updated successfully!!!");

                loadPatientFile();
            } else {
                System.out.println("System error occured!!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


    public boolean updatePatient(Patient patient) throws Exception {
        output = new Formatter(new FileWriter("patientChanged.txt"));

        Iterator<Patient> it = patientList.iterator();
        while (it.hasNext()) {
            Patient p = (Patient) it.next();
            if (p.getPatientID() == patient.getPatientID()) {
                output.format("%d,%s,%s,%s\r\n", patient.getPatientID(), patient.getFullName(), patient.getPhoneNumber(), patient.getPatientStatus());
            } else {
                output.format("%d,%s,%s,%s\r\n", p.getPatientID(), p.getFullName(), p.getPhoneNumber(), p.getPatientStatus());
            }
        }
        output.close();

        // delete old file
        File fileToDelete = new File(patientFile);
        boolean delete = fileToDelete.delete();

        // renames patientChanged.txt to patient.txt
        File fileToRename = new File("patientChanged.txt");
        File renamedFile = new File(patientFile);

        boolean rename = fileToRename.renameTo(renamedFile);

        return rename;

    }

    // get patient status
    public String getPatientStatus(String oStatus) {
        //Scanner console = new Scanner(System.in);

        boolean loop = true;

        System.out.print("@ Patient Status : [" + oStatus + "] ");
        String ans = console.nextLine();

        if (ans.length() <= 0 || ans == "") ans = oStatus;

        while (loop) {
            switch (ans) {
                case "New":
                case "Existing":
                    loop = false;
                    break;
                default:
                    System.out.print("New/Existing?");
                    ans = console.nextLine();
            }
        }
        return ans;
    }

    // show doctor list
    public void showDoctorList() {
        clearScreen();
        int count = 0;
        Iterator it = getDoctor().iterator();

        System.out.println("----------------------------------------------------------------------");

        while (it.hasNext()) {
            count++;
            Doctor d = (Doctor) it.next();
            System.out.println(String.format("%-30s: %s", "@ Employee Id", d.getEmployeeId()));
            System.out.println(String.format("%-30s: %s", "@ Full Name", d.getFullName()));
            System.out.println(String.format("%-30s: %s", "@ Role", d.getRole()));
            System.out.println("-----------------------------------------------------------------------");

        }
        System.out.println("-----------------------------------------------------------------------");
        System.out.println(String.format("%-30s: %s", "@ Total number of Doctor", count));
        System.out.println("");
    }

    // add new Doctor
    public void addNewDoctor() {
        clearScreen();
        Scanner console = new Scanner(System.in);
        Iterator it = doctorList.iterator();
        boolean loop = false;

        int tempID = 0;
        while (it.hasNext()) {
            Doctor d = (Doctor) it.next();
            tempID = d.getEmployeeId();
        }
        tempID++;
        int doctorID = tempID;

        System.out.println("Please enter doctor's login ID: ");
        String loginID = getLoginId();

        String password = "";
        String comparePassword = "";
        boolean isMatched = false;
        do {
            System.out.println("Please enter doctor's password: ");
            password = getPassword();
            System.out.println("Please confirm doctor's password: ");
            comparePassword = getPassword();

            if (password.equals(comparePassword)) {
                isMatched = true;
            } else {
                System.out.println("The password does not match. Please try again!");
            }

        } while (!isMatched);

        System.out.println("Please enter doctor's fullname: ");
        String fullName = console.nextLine();
        String role = "Doctor";
        try {
            output = new Formatter(new FileWriter(doctorFile, true));
            output.format("%d,%s,%s,%s,%s\r\n", doctorID, loginID, password, fullName, role);
            output.close();
            System.out.println("Doctor added successfully!");
        } catch (Exception e) {
            System.out.println(e.toString() + ":" + e.getMessage());
        }
        doctorList.clear();
        loadDoctorFile();

    }

    // update Doctor
    public void updateDoctor() throws Exception {
        try {

            boolean done = false;

            System.out.print("Please enter the Doctor ID that you with to update : ");
            int doctorID = inputDoctorIDFromConsole();
            Doctor d = getDoctor(doctorID);

            System.out.println("------------------------------------------------------------");

            String password = "";
            String comparePassword = "";
            boolean isValid = false;
            PasswordValidator passwordValidator = new PasswordValidator();

            do {
                System.out.print("@ Doctor's password (leave blank if not changed): ");
                password = console.nextLine();
                if (password.length() > 0) {
                    if (passwordValidator.validate(password)) {
                        System.out.println("@ Confirm password: ");
                        comparePassword = getPassword();
                        if (password.equals(comparePassword)) {
                            isValid = true;
                        } else {
                            System.out.println("The password does not match. Please try again!");
                        }
                    } else {
                        System.out.println("Invalid password! Password length must be between 6 and 20 at least 1 digit, 1 capital letter and 1 special character (@#$%)");
                    }
                } else {
                    isValid = true;
                }

            } while (!isValid);

            System.out.print("@ Doctor's fullname : [" + d.getFullName() + "] ");
            String fullName = console.nextLine();
            String role = "Doctor";

            if (password.length() <= 0 || "".equals(password)) password = d.getPassword();
            if (fullName.length() <= 0 || "".equals(fullName)) fullName = d.getFullName();

            Doctor uDoctor = new Doctor(d.getEmployeeId(), d.getLoginId(), password, fullName, role);

            done = updateDoctor(uDoctor);

            if (done) {
                System.out.println("Doctor Detail updated successfully!!!");

                loadDoctorFile();
            } else {
                System.out.println("System error occured!!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public boolean updateDoctor(Doctor doctor) throws Exception {
        output = new Formatter(new FileWriter("doctorChanged.txt"));

        Iterator<Doctor> it = doctorList.iterator();
        while (it.hasNext()) {
            Doctor d = (Doctor) it.next();
            if (d.getEmployeeId() == doctor.getEmployeeId()) {
                output.format("%d,%s,%s,%s,%s\r\n", doctor.getEmployeeId(), doctor.getLoginId(), doctor.getPassword(), doctor.getFullName(), doctor.getRole());
            } else {
                output.format("%d,%s,%s,%s,%s\r\n", d.getEmployeeId(), d.getLoginId(), d.getPassword(), d.getFullName(), d.getRole());
            }
        }
        output.close();

        // delete old file
        File fileToDelete = new File(doctorFile);
        boolean delete = fileToDelete.delete();

        // renames doctorChanged.txt to doctor.txt
        File fileToRename = new File("doctorChanged.txt");
        File renamedFile = new File(doctorFile);

        boolean rename = fileToRename.renameTo(renamedFile);

        return rename;

    }

    // delete doctor
    public void deleteDoctor() throws Exception {
        clearScreen();
        //Scanner console = new Scanner(System.in);

        boolean done = false;
        output = new Formatter(new FileWriter("doctorChanged.txt", true));

        System.out.println(String.format("%-20s%-20s%-20s%-20s%-20s", "ID", "login", "Password", "FullName", "Role"));
        System.out.println("--------------------------------------------------------------------");

        Iterator it1 = getDoctorList().iterator();

        while (it1.hasNext()) {


            Doctor d = (Doctor) it1.next();
            System.out.println(String.format("%-20s%-20s%-20s%-20s%-20s", d.getEmployeeId(), d.getLoginId(), d.getPassword(), d.getFullName(), d.getRole()));
        }
        System.out.println();

        // delete a record from the text file based on the patientId
        System.out.println("Please enter the doctor's ID number to delete the doctor: ");
        int DoctorId = Integer.parseInt(console.nextLine());

        Iterator it = getDoctorList().iterator();
        while (it.hasNext()) {
            Doctor d = (Doctor) it.next();
            if (!(d.getEmployeeId() == (DoctorId))) {
                output.format("%d,%s,%s,%s,%s\r\n", d.getEmployeeId(), d.getLoginId(), d.getPassword(), d.getFullName(), d.getRole());
            } else {
                done = true;
            }
        }

        output.close();
        if (done == true) {
            //clearScreen();
            System.out.println("Doctor deleted successfully!");


            // delete old file
            File fileToDelete = new File("doctor.txt");
            boolean delete = fileToDelete.delete();

            // rename doctorChanged.txt to doctor.txt
            File fileToRename = new File("doctorChanged.txt");
            File renamedFile = new File("doctor.txt");
            boolean rename = fileToRename.renameTo(renamedFile);

            doctorList.clear();
            loadDoctorFile();
        } else {
            System.out.println("Doctor not found!!!");
        }

    }

    //search Doctor
    public void searchDoctor() {
        clearScreen();
        System.out.print("Please enter doctor name: ");
        String docName = console.nextLine();

        Iterator it = doctorList.iterator();
        int count = 0;

        System.out.println("-------------------------------------------------------------------------");

        while (it.hasNext()) {

            Doctor d = (Doctor) it.next();

            if (d.getFullName().toLowerCase().contains(docName.toLowerCase())) {
                count++;
                System.out.println(String.format("%-30s: %d", "@ doctor Id", d.getEmployeeId()));
                System.out.println(String.format("%-30s: %s", "@ Doctor First Name", d.getFullName()));
                System.out.println(String.format("%-30s: %s", "@ Doctor Role", d.getRole()));
                System.out.println("-----------------------------------------------------------------------");
            }
        }
        System.out.println("-----------------------------------------------------------------------");
        System.out.println(String.format("%-30s: %s", "@ Total number of Doctor", count));
        System.out.println("");

    }


    //add new visitation details
    public void addNewVisitation() throws Exception {
        clearScreen();
        //Scanner console = new Scanner(System.in);
        Iterator it = visitationList.iterator();

        int tempID = 0;
        while (it.hasNext()) {
            Visitation v = (Visitation) it.next();
            tempID = v.getVisitationID();
        }
        tempID++;
        int visitNumber = tempID;

        System.out.print("Please select Patient (" + printPatient() + "):");
        int patientID = inputPatientID();


        System.out.print("Please enter visitation date: ");
        String vDate = console.nextLine();

        System.out.print("Please enter visitation purpose: ");
        String purpose = console.nextLine();
        System.out.print("Please select the Doctor: (" + printDoctor() + ")");
        int doctorID = inputDoctorIDFromConsole();

        try {
            output = new Formatter(new FileWriter(visitationFile, true));
            output.format("%d,%d,%s,%s,%d\r\n", visitNumber, patientID, vDate, purpose, doctorID);
            output.close();
            System.out.println("Visitation details added successfully!");

        } catch (Exception e) {
            System.out.println(e.toString() + ":" + e.getMessage());
        }
        visitationList.clear();
        loadVisitationFile();
    }

    public String printDoctor() {
        Iterator it = doctorList.iterator();
        String doctorStr = "";

        while (it.hasNext()) {
            Doctor c = (Doctor) it.next();
            if (doctorStr != "") {
                doctorStr += ", " + c.getEmployeeId() + ":" + c.getFullName();
            } else {
                doctorStr = c.getEmployeeId() + ":" + c.getFullName();
            }
        }
        return doctorStr;
    }


    public String printPatient() {
        Iterator it = patientList.iterator();
        String patientStr = "";

        while (it.hasNext()) {
            Patient p = (Patient) it.next();
            if (patientStr != "") {
                patientStr += ", " + p.getPatientID() + ":" + p.getFullName();
            } else {
                patientStr = p.getPatientID() + ":" + p.getFullName();
            }
        }
        return patientStr;
    }


    // get patient from
    public int inputPatientID() {
        //Scanner console = new Scanner(System.in);

        boolean loop = true;
        int ans = 0;

        while (loop) {
            try {
                int input = Integer.parseInt(console.nextLine());
                if (isValidPatient(input)) {
                    ans = input;
                    loop = false;
                } else {
                    System.out.println("Please choose valid patient id (" + printPatient() + "):");
                }
            } catch (Exception e) {
                System.out.println("Wrong data type is entered!!!");
            }
        }
        return ans;
    }


    public boolean isValidPatient(int patientID) {

        Iterator it = patientList.iterator();
        boolean valid = false;

        while (it.hasNext()) {
            Patient p = (Patient) it.next();
            if (patientID == p.getPatientID()) {
                valid = true;
            }
        }
        return valid;
    }


//Update visitation details

    public void updateVisitation() throws Exception {
        //Scanner console = new Scanner(System.in);
        try {
            boolean done = false;

            System.out.println("Please enter the Visitation ID that you wish to update : ");
            int vPatientId = inputVisitationIDFromConsole();
            console.nextLine();

            Visitation v = getVisitedPatient(vPatientId);
            System.out.println("------------------------------------------------------------");
            System.out.print("@ Patient Name : [" + v.getPatientName() + "] ");
            String firstName = console.nextLine();

            System.out.print("@ Visit Date : [" + v.getVisitationDate() + "] ");
            String visitDate = console.nextLine();

            System.out.print("@ Visit Doctor : [" + v.getVisitedDoctor() + "] ");
            String visitDoctor = console.nextLine();

            System.out.print("@ Visit Purpose : [" + v.getVisitationPurpose() + "] ");
            String visitPurpose = console.nextLine();


            if (firstName.length() <= 0 || firstName == "") firstName = v.getPatientName();
            if (visitDate.length() <= 0 || visitDate == "") visitDate = v.getVisitationDate();
            if (visitDoctor.length() <= 0 || visitDoctor == "") visitDoctor = v.getVisitedDoctor();
            if (visitPurpose.length() <= 0 || visitPurpose == "") visitPurpose = v.getVisitationPurpose();


            Visitation vPatient = new Visitation(vPatientId, firstName, visitDate, visitDoctor, visitPurpose);

            System.out.println(vPatient);

            done = updateDoneVisitPatient(vPatient);

            if (done) {
                System.out.println("Visitors Detail updated successfully!!!");
                loadVisitationFile();
            } else {
                System.out.println("System error occured!!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    //get visitation patient id from console
    public int inputVisitationIDFromConsole() throws Exception {


        int vpatientID = Integer.parseInt(console.nextLine());

        Iterator<Visitation> it = visitationList.iterator();
        boolean found = false;

        while (it.hasNext()) {
            Visitation d = (Visitation) it.next();
            if (vpatientID == d.getVisitationID()) {
                found = true;
                break;
            }
        }
        if (found) return vpatientID;
        else {
            throw new Exception("Cannot find the Visitor Patient with ID '" + vpatientID + "'");
        }
    }


    //
//    //update by entering id
    public boolean updateDoneVisitPatient(Visitation patient) throws Exception {
        output = new Formatter(new FileWriter("visitationChanged.txt"));

        Iterator<Visitation> it = visitationList.iterator();
        while (it.hasNext()) {

            Visitation v = (Visitation) it.next();
            if (v.getVisitationID() == patient.getVisitationID()) {

                System.out.println("equal");
                output.format("%d,%s,%s,%s,%s\r\n", patient.getVisitationID(), patient.getPatientName(), patient.getVisitationDate(), patient.getVisitedDoctor(), patient.getVisitationPurpose());
            } else {
                System.out.println("not equal");

                output.format("%d,%s,%s,%s,%s\r\n", v.getVisitationID(), v.getPatientName(), v.getVisitationDate(), v.getVisitedDoctor(), v.getVisitationPurpose());
            }
        }

        output.close();

        // delete old file
        File fileToDelete = new File("visitation.txt");
        boolean delete = fileToDelete.delete();

        // rename visitationChanged.txt to patient.txt
        File fileToRename = new File("visitationChanged.txt");
        File renamedFile = new File("visitation.txt");
        boolean rename = fileToRename.renameTo(renamedFile);


        return rename;

    }

    private Visitation getVisitedPatient(int vpatientID) {
        Visitation selected = null;

        Iterator<Visitation> it = visitationList.iterator();
        while (it.hasNext()) {
            Visitation v = (Visitation) it.next();
            if (v.getVisitationID() == vpatientID) {
                selected = v;
                break;
            }
        }
        return selected;
    }


//delete visitation details

    public void deleteVisitation() throws Exception {
        clearScreen();
        Scanner console = new Scanner(System.in);

        boolean done = false;
        output = new Formatter(new FileWriter("visitationChanged.txt", true));

        System.out.println(String.format("%-20s%-20s%-20s%-20s%-20s", "ID", "Name", "Date", "Doctor visited", "Purpose of visit"));


        System.out.println("--------------------------------------------------------------------");

        Iterator it1 = getVisitation().iterator();

        System.out.println(visitationList);

        while (it1.hasNext()) {
            Visitation v = (Visitation) it1.next();
            System.out.println(String.format("%-20s%-20s%-20s%-20s%-20s", v.getVisitationID(), v.getPatientName(), v.getVisitationDate(), v.getVisitedDoctor(), v.getVisitationPurpose()));
            System.out.println();
        }


        // delete a record from the text file based on the visitationid
        System.out.println("Please enter the Visitation ID number to delete the visitation details: ");
        int vId = Integer.parseInt(console.nextLine());

        Iterator it = getVisitation().iterator();
        while (it.hasNext()) {
            Visitation v = (Visitation) it.next();
            if (!(v.getVisitationID() == (vId))) {
                output.format("%d,%s,%s,%s,%s\r\n", vId, v.getPatientName(), v.getVisitationDate(), v.getVisitedDoctor(), v.getVisitationPurpose());
            } else {
                done = true;
            }
        }

        output.close();
        if (done == true) {
            //clearScreen();
            System.out.println("Visitation detail deleted successfully!");


            // delete old file
            File fileToDelete = new File("visitation.txt");
            boolean delete = fileToDelete.delete();

            // rename visitationChanged.txt to visitation.txt
            File fileToRename = new File("visitationChanged.txt");
            File renamedFile = new File("visitation.txt");
            boolean rename = fileToRename.renameTo(renamedFile);

            visitationList.clear();
            loadVisitationFile();
        } else {
            System.out.println("Visitation detail not found!!!");
        }

    }

    //Search visitation details
// search visitation by id
    public void searchVisitation() {
        clearScreen();

        System.out.println("Please enter the visitation ID number to search the record: ");
        int td = console.nextInt();

        //delete this code start
        Iterator it = getVisitation().iterator();
        while (it.hasNext()) {
            Visitation v = (Visitation) it.next();
            if ((v.getVisitationID() == (td))) {

                System.out.println(String.format("%-30s: %s", "@ Visitation Id", v.getVisitationID()));
                System.out.println(String.format("%-30s: %s", "@ Name", v.getPatientName()));
                System.out.println(String.format("%-30s: %s", "@ Date", v.getVisitationDate()));
                System.out.println(String.format("%-30s: %s", "@ Doctor", v.getVisitedDoctor()));
                System.out.println(String.format("%-30s: %s", "@ Purpose of visit", v.getVisitationPurpose()));


                System.out.println("-----------------------------------------------------------------------");
            }
        }
        System.out.println("");
    }

// Add patient treatment

    public void addNewPatientTreatment() throws Exception {
        clearScreen();
        //Scanner console = new Scanner(System.in);
        Iterator it = patientTreatmentList.iterator();

        int tempID = 0;
        while (it.hasNext()) {
            PatientTreatment pt = (PatientTreatment) it.next();
            tempID = pt.getPatientTreatmentID();
        }
        tempID++;
        int treatNumber = tempID;

        System.out.print("Please select Patient (" + printPatient() + "):");
        int patientID = inputPatientID();


        System.out.print("Please select Treatment name (" + printTreatment() + "):");
        String ptName = console.nextLine();

        System.out.print("Please select the Doctor: (" + printDoctor() + ")");
        int doctorID = inputDoctorIDFromConsole();

        System.out.print("Please enter cost of treatment: ");
        String pCost = console.nextLine();

        System.out.print("Please enter treatment start date: ");
        String startDate = console.nextLine();

        System.out.print("Please enter treatment end date: ");
        String endDate = console.nextLine();

        try {
            output = new Formatter(new FileWriter(patientTreatmentFile, true));
            output.format("%d,%d,%s,%d,%s,%s,%s,%s\r\n", treatNumber, patientID, ptName, doctorID, pCost, startDate, endDate, "ongoing");
            output.close();
            System.out.println("Patient Treatment details added successfully!");

        } catch (Exception e) {
            System.out.println(e.toString() + ":" + e.getMessage());
        }
        patientTreatmentList.clear();
        loadPatientTreatmentFile();
    }

    public String printTreatment() {
        Iterator it = treatmentList.iterator();
        String treatmentStr = "";

        while (it.hasNext()) {
            Treatment t = (Treatment) it.next();
            if (treatmentStr != "") {
                treatmentStr += ", " + t.getTreatmentName();
            } else {
                treatmentStr = t.getTreatmentName();
            }
        }
        return treatmentStr;
    }
//Delete Patient treatment

    public void deletePatientTreatment() throws Exception {
        clearScreen();
        Scanner console = new Scanner(System.in);

        boolean done = false;
        output = new Formatter(new FileWriter("patientTreatmentChanged.txt", true));

        System.out.println(String.format("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s", "ID", "Patient ID", "Treatment Name", "Doctor ID", "Total Cost", "Start date", "End date", "Status"));


        System.out.println("----------------------------------------------------------------------------------------------------------------------------");

        Iterator it1 = getPatientTreatment().iterator();

        System.out.println(patientTreatmentList);

        while (it1.hasNext()) {
            PatientTreatment v = (PatientTreatment) it1.next();
            System.out.println(String.format("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s", v.getPatientTreatmentID(), v.getPatientID(), v.getPatientTreatmentName(), v.getDoctorID(), v.getTotalCost(), v.getTreatmentStartDate(), v.getTreatmentEndDate(), v.getTreatmentStatus()));
            System.out.println();
        }


        // delete a record from the text file based on the patient treatmentid
        System.out.println("Please enter the Patient Treatment ID number to delete the Patient Treatment details: ");
        int vId = Integer.parseInt(console.nextLine());

        Iterator it = getPatientTreatment().iterator();
        while (it.hasNext()) {
            PatientTreatment v = (PatientTreatment) it.next();
            if (!(v.getPatientTreatmentID() == (vId))) {
                output.format("%d,%d,%s,%d,%s,%s,%s,%s\n", vId, v.getPatientID(), v.getPatientTreatmentName(), v.getDoctorID(), v.getTotalCost(), v.getTreatmentStartDate(), v.getTreatmentEndDate(), v.getTreatmentStatus());
            } else {
                done = true;
            }
        }

        output.close();
        if (done == true) {
            //clearScreen();
            System.out.println("Patient Treatment detail deleted successfully!");


            // delete old file
            File fileToDelete = new File("patientTreatment.txt");
            boolean delete = fileToDelete.delete();

            // rename patientTreatmentChanged.txt to patientTreatment.txt
            File fileToRename = new File("patientTreatmentChanged.txt");
            File renamedFile = new File("patientTreatment.txt");
            boolean rename = fileToRename.renameTo(renamedFile);

            patientTreatmentList.clear();
            loadPatientTreatmentFile();
        } else {
            System.out.println("Patient Treatment detail not found!!!");
        }

    }

    //Search patient treatment details
    public void searchPatientTreatment() {
        clearScreen();

        System.out.println("Please enter the Patient Treatment ID number to search the record: ");
        int td = console.nextInt();

        //delete this code start
        Iterator it = getPatientTreatment().iterator();
        while (it.hasNext()) {
            PatientTreatment pt = (PatientTreatment) it.next();
            if ((pt.getPatientTreatmentID() == (td))) {

                System.out.println(String.format("%-30s: %s", "@ Patient Treatment Id", pt.getPatientTreatmentID()));
                System.out.println(String.format("%-30s: %s", "@ Patient ID", pt.getPatientID()));
                System.out.println(String.format("%-30s: %s", "@ Treatment Name", pt.getPatientTreatmentName()));
                System.out.println(String.format("%-30s: %s", "@ Doctor ID", pt.getDoctorID()));
                System.out.println(String.format("%-30s: %s", "@ Cost of treatment", pt.getTotalCost()));
                System.out.println(String.format("%-30s: %s", "@ Treatment start date", pt.getTreatmentStartDate()));
                System.out.println(String.format("%-30s: %s", "@ Treatment end date", pt.getTreatmentEndDate()));
                System.out.println(String.format("%-30s: %s", "@ Treatment Status", pt.getTreatmentStatus()));


                System.out.println("--------------------------------------------------------------------------------------");
            }
        }
        System.out.println("");
    }

    //Make Payment
    private void makePayment() throws Exception {


        System.out.println("------------------------------------------------------------------");
        System.out.println("                        Make a payment                            ");
        System.out.println("------------------------------------------------------------------");

        int patientID;
        try {
            System.out.print("Please enter the Patient's ID to make a payment : ");
            try {
                patientID = inputPatientID();
            } catch (Exception e) {
                throw new Exception("Patient's ID Not Found!");
            }
            System.out.print("Please enter the amount : ");
            Double paymentAmount = getPriceFromConsole();

            Iterator<Payment> it1 = paymentList.iterator();
            int tempID = 0;
            while (it1.hasNext()) {
                Payment payment = (Payment) it1.next();
                tempID = payment.getPaymentID();
            }
            tempID++;
            int paymentID = tempID;
            try {
                output = new Formatter(new FileWriter(paymentFile, true));
                output.format("%d,%d,%.2f,%s\r\n", paymentID, patientID, paymentAmount, datetimeformat.format(new Date()));
                output.close();
                paymentList.clear();
                loadpaymentFile();
            } catch (IOException e) {
                System.out.println(e.toString() + ":" + e.getMessage());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    // show receptionist list
    public void showReceptionistList() {
        clearScreen();
        int count = 0;
        Iterator it = getReceptionist().iterator();

        System.out.println("----------------------------------------------------------------------");

        while (it.hasNext()) {
            count++;
            Receptionist r = (Receptionist) it.next();
            System.out.println(String.format("%-30s: %s", "@ Employee Id", r.getEmployeeId()));
            System.out.println(String.format("%-30s: %s", "@ Full Name", r.getFullName()));
            System.out.println(String.format("%-30s: %s", "@ Role", r.getRole()));
            System.out.println("-----------------------------------------------------------------------");

        }
        System.out.println("-----------------------------------------------------------------------");
        System.out.println(String.format("%-30s: %s", "@ Total number of Receptionist", count));
        System.out.println("");
    }

    // add new Receptionist
    public void addNewReceptionist() {
        clearScreen();
        Scanner console = new Scanner(System.in);
        Iterator it = receptionistList.iterator();
        boolean loop = false;

        int tempID = 0;
        while (it.hasNext()) {
            Receptionist r = (Receptionist) it.next();
            tempID = r.getEmployeeId();
        }
        tempID++;
        int receptionistId = tempID;

        System.out.println("Please enter Receptionist's login ID: ");
        String loginID = getLoginId();

        String password = "";
        String comparePassword = "";
        boolean isMatched = false;
        do {
            System.out.println("Please enter Receptionist's password: ");
            password = getPassword();
            System.out.println("Please confirm Receptionist's password: ");
            comparePassword = getPassword();

            if (password.equals(comparePassword)) {
                isMatched = true;
            } else {
                System.out.println("The password does not match. Please try again!");
            }

        } while (!isMatched);

        System.out.println("Please enter Receptionist's fullname: ");
        String fullName = console.nextLine();
        String role = "Receptionist";
        try {
            output = new Formatter(new FileWriter(receptionistFile, true));
            output.format("%d,%s,%s,%s,%s\r\n", receptionistId, loginID, password, fullName, role);
            output.close();
            System.out.println("Receptionist added successfully!");
        } catch (Exception e) {
            System.out.println(e.toString() + ":" + e.getMessage());
        }
        receptionistList.clear();
        loadReceptionistFile();
    }

    //view report
    public void viewReport() {

        int ptid;

        System.out.println("Please enter Patient Treatment ID for the report :");
        try {
            try {

                ptid=inputPatientTreatmentIDFromConsole();
            } catch (Exception e) {
                throw new Exception("Patient's ID Not Found!");
            }

            PatientTreatment pt=getPt(ptid);

           System.out.println(String.format("%-30s: %s", "Patient Name", getPName(pt.getPatientID())));
            System.out.println(String.format("%-30s: %s", "Treatment Name", pt.getPatientTreatmentName()));
            System.out.println(String.format("%-30s: %s", "Doctor Name", getDName(pt.getDoctorID())));
            System.out.println(String.format("%-30s: %s", "Treatment Cost", pt.getTotalCost()));
            System.out.println(String.format("%-30s: %s", "Treatment Start date", pt.getTreatmentStartDate()));
            System.out.println(String.format("%-30s: %s", "Treatment End date", pt.getTreatmentEndDate()));

                }catch (Exception e) {
            System.out.println(e.getMessage());
        }


}
    public String getPName(int pid)throws Exception
    {

        Iterator<Patient> it = getPatient().iterator();
        boolean found = false;

        String pName = null;

        while(it.hasNext())
        {
            Patient obj = (Patient) it.next();
            if(pid == obj.getPatientID())
            {
                found = true;
                pName = obj.getFullName();
                break;
            }
        }
        if(found) return pName;


        else {
            throw new Exception("Cannot find the Patient treatment with patientId '"+pid+"'");
        }



    }
    public String getDName(int pid)throws Exception
    {

        Iterator<Doctor> it = getDoctor().iterator();
        boolean found = false;

        String pName = null;

        while(it.hasNext())
        {
            Doctor obj = (Doctor) it.next();
            if(pid == obj.getEmployeeId())
            {
                found = true;
                pName = obj.getFullName();
                break;
            }
        }
        if(found) return pName;


        else {
            throw new Exception("Cannot find the Patient treatment with patientId '"+pid+"'");
        }



    }
    //get PatientTreatment by patientTreatmentID
    private PatientTreatment getPt(int patientTreatmentID) {
        PatientTreatment selected = null;
        Iterator<PatientTreatment> it = patientTreatmentList.iterator();

        while (it.hasNext()) {
            PatientTreatment p = (PatientTreatment) it.next();
            if (p.getPatientTreatmentID() == patientTreatmentID) {
                selected = p;
                break;
            }
        }
        return selected;
    }

    // update receptionist
    public void updateReceptionist() throws Exception {
        try {

            boolean done = false;

            System.out.print("Please enter the Receptionist ID that you with to update : ");
            int receptionistId = inputReceptionistIDFromConsole();
            Receptionist r = getReceptionist(receptionistId);

            System.out.println("------------------------------------------------------------");

            String password = "";
            String comparePassword = "";
            boolean isValid = false;
            PasswordValidator passwordValidator = new PasswordValidator();

            do {
                System.out.print("@ Receptionist's password (leave blank if not changed): ");
                password = console.nextLine();
                if (password.length() > 0) {
                    if (passwordValidator.validate(password)) {
                        System.out.println("@ Confirm password: ");
                        comparePassword = getPassword();
                        if (password.equals(comparePassword)) {
                            isValid = true;
                        } else {
                            System.out.println("The password does not match. Please try again!");
                        }
                    } else {
                        System.out.println("Invalid password! Password length must be between 6 and 20 at least 1 digit, 1 capital letter and 1 special character (@#$%)");
                    }
                } else {
                    isValid = true;
                }

            } while (!isValid);

            System.out.print("@ Receptionist's fullname : [" + r.getFullName() + "] ");
            String fullName = console.nextLine();
            String role = "Receptionist";


            if (password.length() <= 0 || "".equals(password)) password = r.getPassword();
            if (fullName.length() <= 0 || "".equals(fullName)) fullName = r.getFullName();


            Receptionist uReceptionist = new Receptionist(r.getEmployeeId(), r.getLoginId(), password, fullName, role);

            done = updateReceptionist(uReceptionist);

            if (done) {
                System.out.println("Receptionist Detail updated successfully!!!");

                loadReceptionistFile();
            } else {
                System.out.println("Receptionist error occured!!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


    public boolean updateReceptionist(Receptionist receptionist) throws Exception
    {
        output = new Formatter(new FileWriter("receptionistChanged.txt"));

        Iterator<Receptionist> it = receptionistList.iterator();
        while (it.hasNext()) {
            Receptionist r = (Receptionist) it.next();
            if (r.getEmployeeId() == receptionist.getEmployeeId()) {
                output.format("%d,%s,%s,%s,%s\r\n", receptionist.getEmployeeId(), receptionist.getLoginId(), receptionist.getPassword(), receptionist.getFullName(), receptionist.getRole());
            } else {
                output.format("%d,%s,%s,%s,%s\r\n", r.getEmployeeId(), r.getLoginId(), r.getPassword(), r.getFullName(), r.getRole());
            }
        }
        output.close();

        // delete old file
        File fileToDelete = new File(receptionistFile);
        boolean delete = fileToDelete.delete();

        // renames receptionistChanged.txt to receptionist.txt
        File fileToRename = new File("receptionistChanged.txt");
        File renamedFile = new File(receptionistFile);

        boolean rename = fileToRename.renameTo(renamedFile);

        return rename;
    }
    public int inputReceptionistIDFromConsole() throws Exception {
        int receptionistId = Integer.parseInt(console.nextLine());

        Iterator<Receptionist> it = receptionistList.iterator();
        boolean found = false;

        while (it.hasNext()) {
            Receptionist r = (Receptionist) it.next();
            if (receptionistId == r.getEmployeeId()) {
                found = true;
                break;
            }
        }
        if (found) return receptionistId;
        else {
            throw new Exception("Cannot find the Receptionist with ReceptionistId '" + receptionistId + "'");
        }
    }
    // get receptionist by receptionistID
    private Receptionist getReceptionist(int receptionistId) {
        Receptionist selected = null;

        Iterator<Receptionist> it = receptionistList.iterator();

        while (it.hasNext()) {
            Receptionist r = (Receptionist) it.next();
            if (r.getEmployeeId() == receptionistId) {
                selected = r;
                break;
            }
        }

        return selected;
    }
    // delete receptionist
    public void deleteReceptionist() throws Exception {
        clearScreen();
        //Scanner console = new Scanner(System.in);

        boolean done = false;
        output = new Formatter(new FileWriter("receptionistChanged.txt", true));

        System.out.println(String.format("%-20s%-20s%-20s%-20s%-20s", "ID", "login", "Password", "FullName", "Role"));

        System.out.println("--------------------------------------------------------------------");

        Iterator it1 = getReceptionistList().iterator();
        while (it1.hasNext()) {
            Receptionist r = (Receptionist) it1.next();
            System.out.println(String.format("%-20s%-20s%-20s%-20s%-20s", r.getEmployeeId(), r.getLoginId(), r.getPassword(),r.getFullName(),r.getRole()));
        }
        System.out.println();

        // delete a record from the text file based on the ReceptionistId
        System.out.println("Please enter the receptionist's ID number to delete the record: ");
        int receptionistId = console.nextInt();

        Iterator it = getReceptionistList().iterator();
        while (it.hasNext()) {
            Receptionist r = (Receptionist) it.next();
            if (!(r.getEmployeeId() == (receptionistId))) {
                output.format("%d,%s,%s,%s,%s\r\n", r.getEmployeeId(), r.getLoginId(), r.getPassword(),r.getFullName(),r.getRole());

            } else {
                done = true;
            }
        }

        output.close();
        if (done == true)
        {
            //clearScreen();
            System.out.println("Receptionist deleted successfully!");

            // delete old file
            File fileToDelete = new File("receptionist.txt");
            boolean delete = fileToDelete.delete();

            // rename receptionistChanged.txt to receptionist.txt
            File fileToRename = new File("receptionistChanged.txt");
            File renamedFile = new File("receptionist.txt");
            boolean rename = fileToRename.renameTo(renamedFile);

            receptionistList.clear();
            loadReceptionistFile();
        }
        else
        {
            System.out.println("Receptionist not found!!!");
        }

    }

    // search receptionist by name
    public void searchReceptionist() {
        clearScreen();
        System.out.print("Please enter receptionist's name: ");
        String name = console.nextLine();

        Iterator it = receptionistList.iterator();
        int count = 0;

        System.out.println("-------------------------------------------------------------------------");

        while (it.hasNext()) {
            Receptionist r = (Receptionist) it.next();
            if (r.getFullName().toLowerCase().contains(name.toLowerCase())) {
                count++;
                System.out.println(String.format("%-30s: %s", "@ Receptionist Id", r.getEmployeeId()));
                System.out.println(String.format("%-30s: %s", "@ Receptionist Full Name", r.getFullName()));
                System.out.println(String.format("%-30s: %s", "@ Receptionist Role", r.getRole()));


                System.out.println("-----------------------------------------------------------------------");
            }
        }
        System.out.println("-----------------------------------------------------------------------");
        System.out.println(String.format("%-30s: %s", "@ Total number of Receptionist", count));
        System.out.println("");

    }


    // search treatment by id
    public void searchTreatment()
    {
        clearScreen();

        System.out.println("Please enter the treatment ID number to search the record: ");
        int td = console.nextInt();

        //delete this code start
        Iterator it = getTreatment().iterator();
        while (it.hasNext()) {
            Treatment t = (Treatment) it.next();
            if ((t.getTreatmentID() == (td))) {

                System.out.println(String.format("%-30s: %s", "@ Treatment Id", t.getTreatmentID()));
                System.out.println(String.format("%-30s: %s", "@ Name", t.getTreatmentName()));
                System.out.println(String.format("%-30s: %s", "@ Description", t.getTreatmentDescription()));
                System.out.println(String.format("%-30s: %s", "@ Cost", t.getTreatmentCost()));


                System.out.println("-----------------------------------------------------------------------");
            }
        }
        System.out.println("");
    }


    // show treatment list
    public void showTreatmentList()
    {
        clearScreen();
        int count = 0;
        Iterator it = treatmentList.iterator();


        System.out.println("------------------------------------------------------------------------");

        while(it.hasNext()) {

            count++;
            Treatment t = (Treatment) it.next();
            System.out.println(String.format("%-30s: %d","@ Treatment Id",t.getTreatmentID()));
            System.out.println(String.format("%-30s: %s","@ Name",t.getTreatmentID()));
            System.out.println(String.format("%-30s: %s","@ Description",t.getTreatmentDescription()));
            System.out.println(String.format("%-30s: %s","@ Cost",t.getTreatmentCost()));

            System.out.println("---------------------------------------------------------------------------");
        }
        System.out.println("-------------------------------------------------------------------------");
        System.out.println(String.format("%-30s: %s","@ Total number of Treatment",count));
        System.out.println("");

    }


    // add new treatment
    public void addNewTreatment() {
        clearScreen();

        Scanner console = new Scanner(System.in);
        Iterator it = treatmentList.iterator();

        int tempID = 0;
        while (it.hasNext()) {
            Treatment t = (Treatment) it.next();
            tempID = t.getTreatmentID();
        }
        tempID++;
        int treatmentID = tempID;

        System.out.print("Please enter treatment name: ");
        String treatName = console.nextLine();

        System.out.print("Please enter treatment description: ");
        String treatDesc = console.nextLine();

        System.out.print("Please enter total cost: ");
        double treatCost = getPriceFromConsole();

        try {
            output = new Formatter(new FileWriter(treatmentFile, true));
            output.format("%d,%s,%s,%f\r\n", treatmentID,treatName, treatDesc, treatCost);
            output.close();
            System.out.println("Treatment added successfully!");

        } catch (Exception e) {
            System.out.println(e.toString() + ":" + e.getMessage());
        }
        treatmentList.clear();
        loadTreatmentFile();
    }



// Delete Treatment
public void deleteTreatment() throws Exception {
    clearScreen();
    Scanner console = new Scanner(System.in);

    boolean done = false;
    output = new Formatter(new FileWriter("treatmentChanged.txt", true));

    System.out.println(String.format("%-20s%-20s%-20s%-20s", "ID", "Name","Description", "Cost"));


    System.out.println("--------------------------------------------------------------------");

    Iterator it1 = getTreatment().iterator();

    System.out.println(treatmentList);

    while (it1.hasNext())
    {
        Treatment t = (Treatment) it1.next();
        System.out.println(String.format("%-20s%-20s%-20s%-20s", t.getTreatmentID(), t.getTreatmentName(), t.getTreatmentDescription(),t.getTreatmentCost()));
        System.out.println();}


    // delete a record from the text file based on the patientId
    System.out.println("Please enter the treatment ID number to delete the treatment: ");
    int tId = Integer.parseInt(console.nextLine());

    Iterator it = getTreatment().iterator();
    while (it.hasNext()) {
        Treatment t = (Treatment) it.next();
        if (!(t.getTreatmentID() == (tId))) {
            output.format("%d,%s,%s,%s\r\n",tId, t.getTreatmentName(), t.getTreatmentDescription(), t.getTreatmentCost());
        } else {
            done = true;
        }
    }

    output.close();
    if (done == true) {
        //clearScreen();
        System.out.println("Treatment deleted successfully!");


        // delete old file
        File fileToDelete = new File("treatment.txt");
        boolean delete = fileToDelete.delete();

        // rename treatmentChanged.txt to treatment.txt
        File fileToRename = new File("treatmentChanged.txt");
        File renamedFile = new File("treatment.txt");
        boolean rename = fileToRename.renameTo(renamedFile);

        treatmentList.clear();
        loadTreatmentFile();
    } else {
        System.out.println("Treatment not found!!!");
    }

}






    // get amount from console
    public double getPriceFromConsole() {
        //Scanner console = new Scanner(System.in);

        double ret = 0.00;

        boolean done = false;

        while (!done) {
            try {
                double paymentAmount = Double.parseDouble(console.nextLine());
                ret = paymentAmount;
                done = true;
            } catch (Exception e) {
                System.out.print("You entered the invalid amount. Please try again!");
            }
        }

        return ret;
    }



    public int inputDoctorIDFromConsole() throws Exception {
        int doctorID = Integer.parseInt(console.nextLine());

        Iterator<Doctor> it = doctorList.iterator();
        boolean found = false;

        while (it.hasNext()) {
            Doctor d = (Doctor) it.next();
            if (doctorID == d.getEmployeeId()) {
                found = true;
                break;
            }
        }
        if (found) return doctorID;
        else {
            throw new Exception("Cannot find the Doctor with doctorId '" + doctorID + "'");
        }
    }

        public int inputFromConsole() throws Exception {
            int doctorID = Integer.parseInt(console.nextLine());

            Iterator<Doctor> it = doctorList.iterator();
            boolean found = false;

            while (it.hasNext()) {
                Doctor d = (Doctor) it.next();
                if (doctorID == d.getEmployeeId()) {
                    found = true;
                    break;
                }
            }
            if (found) return doctorID;
            else {
                throw new Exception("Cannot find the Doctor with doctorId '" + doctorID + "'");
            }
        }


    public int inputPatientIDFromConsole() throws Exception {
        int patientID = Integer.parseInt(console.nextLine());

        Iterator<Patient> it = patientList.iterator();
        boolean found = false;

        while (it.hasNext()) {
            Patient p = (Patient) it.next();
            if (patientID == p.getPatientID()) {
                found = true;
                break;
            }
        }
        if (found) return patientID;
        else {
            throw new Exception("Cannot find the Patient with patientID '" + patientID + "'");
        }
    }

    public int inputTreatmentDetailIDFromConsole() throws Exception {
        int treatmentID = Integer.parseInt(console.nextLine());

        Iterator<Treatment> it = treatmentList.iterator();
        boolean found = false;

        while (it.hasNext()) {
            Treatment t = (Treatment) it.next();
            if (treatmentID == t.getTreatmentID()) {
                found = true;
                break;
            }
        }
        if (found) return treatmentID;
        else {
            throw new Exception("Cannot find the treatment with treatmentID '" + treatmentID + "'");
        }
    }
    public int inputPatientTreatmentIDFromConsole() throws Exception {
        int patientTreatmentID = Integer.parseInt(console.nextLine());

        Iterator<PatientTreatment> it = patientTreatmentList.iterator();
        boolean found = false;

        while (it.hasNext()) {
            PatientTreatment p = (PatientTreatment) it.next();
            if (patientTreatmentID == p.getPatientTreatmentID()) {
                found = true;
                break;
            }
        }
        if (found) return patientTreatmentID;
        else {
            throw new Exception("Cannot find the Patient with patientID '" + patientTreatmentID + "'");
        }
    }

    //return all the hr in the list
    public ArrayList getHr() {
        return hrList;
    }

    //return all the doctor in the list
    public ArrayList getDoctor() {
        return doctorList;
    }

    //return all the receptionist in the list
    public ArrayList getReceptionist() {
        return receptionistList;
    }

    //return all the patient in the list
    public ArrayList getPatient() {
        return patientList;
    }

    //return all the treatment in the list
    public ArrayList getTreatment() {
        return treatmentList;
    }
    //return all visitation in the list
    public ArrayList getVisitation(){
        return visitationList;
    }
    //return all patient treatment in the list
    public ArrayList getPatientTreatment(){
        return patientTreatmentList;
    }
    // return all the payment in the list
    public ArrayList getPayment() {
        return paymentList; }
        //return all invoice in the list
    public ArrayList getInvoice(){
        return invoiceList;
    }
    //return all the patient ward list
    public ArrayList getWardPatientList(){
        return wardPatientList;
    }


            // get Login ID
    public String getLoginId() {
        boolean loop = true;

        String ans = "";
        UsernameValidator usernameValidator = new UsernameValidator();

        while (loop) {
            ans = console.nextLine();
            if (usernameValidator.validate(ans)) {
                loop = false;
            } else {
                System.out.println("Invalid loginID! loginID length must be between 3 and 15 without space.");
                System.out.print("Try again : ");
            }
        }
        return ans;
    }

    // get Password
    public String getPassword() {
        boolean loop = true;

        String ans = "";
        PasswordValidator passwordValidator = new PasswordValidator();

        while (loop) {
            ans = console.nextLine();
            if (passwordValidator.validate(ans)) {
                loop = false;
            } else {
                System.out.println("Invalid password! Password length must be between 6 and 20 at least 1 digit, 1 capital letter and 1 special character (@#$%)");
                System.out.print("Try again : ");
            }
        }

        return ans;
    }
    public String printWard() {
        Iterator it = wardList.iterator();
        String wardStr = "";

        while(it.hasNext()) {
            Ward c = (Ward) it.next();
            if(wardStr != "") {
                wardStr += ", " + c.getWardID() + ":" + c.getWardName();
            } else {
                wardStr = c.getWardID() + ":" + c.getWardName();
            }
        }
        return wardStr;
    }
    public int inputWardID() {
        //Scanner console = new Scanner(System.in);

        boolean loop = true;
        int ans = 0;

        while(loop) {
            try {
                int input = Integer.parseInt(console.nextLine());
                if (isValidWard(input)) {
                    ans = input;
                    loop = false;
                } else {
                    System.out.println("Please choose valid Ward id (" + printWard() + "):");
                }
            } catch (Exception e) {
                System.out.println("Wrong data type is entered!!!");
            }
        }
        return ans;
    }

    public boolean isValidWard(int wardID) {

        Iterator it = wardList.iterator();
        boolean valid = false;

        while(it.hasNext()) {
            Ward c = (Ward) it.next();
            if(wardID == c.getWardID()) {
                valid = true;
            }
        }
        return valid;
    }






    //get Patient by patientID
    private Patient getPatient(int patientID) {
        Patient selected = null;
        Iterator<Patient> it = patientList.iterator();

        while (it.hasNext()) {
            Patient p = (Patient) it.next();
            if (p.getPatientID() == patientID) {
                selected = p;
                break;
            }
        }
        return selected;
    }

    // get Doctor by DoctorID
    private Doctor getDoctor(int doctorID) {
        Doctor selected = null;

        Iterator<Doctor> it = doctorList.iterator();

        while (it.hasNext()) {
            Doctor d = (Doctor) it.next();
            if (d.getEmployeeId() == doctorID) {
                selected = d;
                break;
            }
        }

        return selected;

    }
}
