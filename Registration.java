// Project 1: Procedural Program
// Project Members: Henri Lower, James Decker, Kassandra Mares and Long Quan Ha
// CSC 142 tth



import java.util.*;
import java.io.*;

public class Registration{
   public static void main(String[] args)
   throws FileNotFoundException
   {
      String repeat;
      do{
        System.out.println();
        System.out.println("/---------------------------------------------------------\\");
        System.out.println("|Welcome To the RegistrationTool for North Seattle College|");
        System.out.println("\\---------------------------------------------------------/");
        System.out.println();
        // IO calls - - - - - - - - - -
        PrintStream output = new PrintStream(new File("StudentInfo.txt"));
        Scanner input = new Scanner(new File("StudentInfo.txt"));
        Scanner console = new Scanner(System.in); // call console for input

        // Main Method integration by James
        // getInfo by Henri
        String creditAndStatus = getInfo(console, output);
        Scanner parse = new Scanner(creditAndStatus);
        int credits = parse.nextInt();
        String residency = parse.next();
        // Tuition by Kassandra
        double tuition = calcTuition(credits, residency);
        //Fees by Quan Ha
        double fees = getFee(credits, console);
        // Display by James
        printAll(input, tuition, fees, residency);
        PrintStream erase = new PrintStream(new File("StudentInfo.txt"));

      // Other end of Repetition Structure - - - - - - - - - -
      System.out.println();
      System.out.println("Would like to start again? (y/n)");
      repeat = console.next();
      }while ( repeat.equals ( "y") || repeat.equals ( "Y")); // Any non-Y input exits the program
   }
   // Method Definitions

// Info Method - - - - - - - - - -
    public static String getInfo(Scanner input, PrintStream output)
    throws FileNotFoundException
    {
        String creditAndStatus;
        //Enter Name---------------------------------------

        System.out.print("Please enter your first name: ");
        String fname = input.next();

        System.out.print("Please enter your last name: ");
        String lname = input.next();

        System.out.println();
        output.println("Student Name  :  " + fname + " " + lname);

        //Enter Student ID---------------------------------
        
        //Initialize Variables
        int id = 0;
        int length = 0;
        String sid = "";
        while(length!=9)
        //While loop that runs until length of user input equals 9 digits
        {
            System.out.print("Please input your 9 digit student ID number:  ");
            sid = input.next(); //Set String "sid" equal to the user's input
            length = sid.length(); //Set int "length" equal to the length of the string
            if(length==9) 
            //If the length does equal 9
            {
                for(int i = 0; i<=8; i++) 
                //For loop that iterates from 0 to 8. Basically 1 less than the value of length
                //Which is guaranteed to be 9 while within this for loop
                {
                    if(sid.charAt(i) < '0' && sid.charAt(i) > '9') 
                    //While the character at each digit isn't a single positive digit
                    //Then print this out
                    {
                        System.out.println("Not a valid 9 digit entry try again");
                        System.out.println("");
                        length = 0; //To ensure that the while loop repeats
                        i=8; //Set i to 8 so the loop breaks and doesn't print extra lines
                    } 
                }
            }else //Input isn't 9 digits long
            {
                System.out.println("Invalid Input. Try again."); 
            }
        }
        //Yay we made it outta the loop and can finally print a useable 9 digit number.
        
        //Printing Student ID to console
        System.out.print("Here is the ID number:  ");
        System.out.print(sid.substring(0,3));
        System.out.print("-");
        System.out.print(sid.substring(3,5));
        System.out.print("-");
        System.out.println(sid.substring(5,9));
        //Printing Student ID to file
        output.print(sid.substring(0,3));
        output.print("-");
        output.print(sid.substring(3,5));
        output.print("-");
        output.println(sid.substring(5,9));
        
        //Enter Address--------------------------------------------------------
        if (1 == 1){
            //Initialize variables
            String address = "";
            String city = "";
            String state = "";
            String zip = "";
            
            System.out.print("Please input your street address:  ");
            address = input.next();
            
            System.out.print("Please input your city:  ");
            city = input.next();
            
            while (state.length() != 2)
            {
                System.out.print("Please enter your state (e.g. NY):  ");
                state = input.next();
                if(state.length()!=2)
                {
                    System.out.println();
                    System.out.println("Invalid input");
                    System.out.println();
                    //System.out.println("Please enter your state (e.g. NY)");
                } 
            }
    
            while (zip.length() != 5)
            {
                System.out.print("Please enter your 5 digit zip code:  ");
                zip = input.next();
                if(zip.length()!=5)
                {
                    System.out.println();
                    System.out.println("Invalid input");
                    System.out.println();
                } 
            }
            output.println(address +" "+ city +" "+ state.toUpperCase() +" "+ zip);
        }

        //Enter Registered Credits---------------------------------------------
        int rcred = 0;
        do
        {
            System.out.print("Please enter your registered credits: ");
            while(!input.hasNextInt()) {
                System.out.println("That isn't a number");
                System.out.println("");
                System.out.println("Please enter your registered credits.");
                input.next();
            }
            rcred = input.nextInt();
            if(rcred <= 0 || rcred > 20) 
            {
                System.out.println("That number isn't valid");
                System.out.println("");
                //System.out.println("Please enter your registered credits.");
            }
        }
        while(rcred <= 0 || rcred > 20);
        System.out.println();
        output.println(rcred);
        creditAndStatus = rcred +" ";

        //Indicate Residency Status------------------------------------------
        int stat = 0;
        while(stat <= 0 || stat>7)
        {
            residencyMenu(); // line 252
            while(!input.hasNextInt()) {
                System.out.println();
                System.out.println("That isn't a number. Try again");
                System.out.println();
                residencyMenu();
                input.next();
            }
            stat = input.nextInt();
            if(stat <= 0 || stat>7)
            {
                System.out.println("Incorrect number detected. Try again.");
            }
        }
        if(stat == 1)
        {
            String status = ("Washington_resident");
            System.out.println("You have indicated that your status");
            System.out.println("is " + status);
            creditAndStatus += status;
        } else if(stat == 2)
        {
            String status = ("Non-resident");
            System.out.println("You have indicated that your status");
            System.out.println("is " + status);
            creditAndStatus += status;
        } else if(stat == 3)
        {
            String status = ("Non-Washington_e-learning");
            System.out.println("You have indicated that your status");
            System.out.println("is " + status);
            creditAndStatus += status;
        } else if(stat == 4)
        {
            String status = ("International");
            System.out.println("You have indicated that your status");
            System.out.println("is " + status);
            creditAndStatus += status;
        } else if(stat == 5)
        {
            String status = ("Washington_State_Employee");
            System.out.println("You have indicated that your status");
            System.out.println(" is " + status);
            creditAndStatus += status;
        } else if(stat == 6)
        {
            String status = ("Veteran");
            System.out.println("You have indicated that your status");
            System.out.println(" is " + status);
            creditAndStatus += status;
        } else if(stat == 7)
        {
            String status = ("Running_Start");
            System.out.println("You have indicated that your status");
            System.out.println(" is " + status);
            creditAndStatus += status;
        }
        System.out.println();
        return creditAndStatus;
        
    }
    static void residencyMenu() //eliminates some redundancy
       {
          System.out.println("Please indicate your residency status by ");
          System.out.println("entering the appropriate number");
          System.out.println("(1) Washington resident");
          System.out.println("(2) Non-Washington resident");
          System.out.println("(3) Non-Washington e-learning");
          System.out.println("(4) International");
          System.out.println("(5) Washington State Employee");
          System.out.println("(6) Veteran");
          System.out.println("(7) Running Start");
          System.out.print("Enter option: ");
       }
    //Calculate Tuition Method - - - - - - - - - -
    static double calcTuition(int credits, String residentStatus)
      {
        double tuition = 0.00;
        if (residentStatus.equals("Washington_resident")) {
          if (credits < 11) {
                tuition = 116.05 * credits;
             } else if (credits < 19) {
                tuition = credits * 57.46 + 585.90;
             } else {
                tuition = credits * 104.12 - 253.98;
             } return tuition;

        } else if(residentStatus.equals("Non-resident_waiver_eligible") || residentStatus.equals("Non-resident_e-learning") ) {
          if (credits < 11) {
                tuition = 131.27 * credits;
            } else if (credits < 19) {
                tuition = credits * 58.26 + 730.10;
            } else {
                tuition = credits * 104.12 - 95.38;
            } return tuition;
        } else if(residentStatus.equals("International")) {
             if (credits < 11) {
             tuition = 299.12 * credits;
             } else if (credits < 19) {
                tuition = credits * 64.92 + 2342;
             } else {
                tuition = credits * 287.19 - 1658.86;
             } return tuition;
        } else if(residentStatus.equals("Veteran")) {
          if (credits < 11) {
                tuition = credits * 58.025;
            } else if (credits < 19) {
                tuition = credits * 28.73 + 292.95;
            } else {
                tuition = credits * 52.06 - 126.99;
            } return tuition;
        }
        return tuition;
    }
// Get Fees Method - - - - - - - - - -
    static float getFee(float number_Of_Credit, Scanner scanner) {
        float fee = 0;
        int numberOfCourse = 0;
        int option = 0 ;
        int option1 = 0;
        while (true) {
            menuFee();
            System.out.println("---------------------------------------\n Enter Your option :");
            option = scanner.nextInt();
            if (option == 1) {
                System.out.println("1class(1) or 2classes(2) ?");
                option1 = scanner.nextInt();
                if (option1 == 1) {
                    fee += 62.00;
                } else if (option1 == 2) {
                    fee += 97.00;
                } else {
                    System.err.println("Option invalid !!");
                }
            } else if (option == 2) {
                System.out.println("All the time permit  $50.00 (1)  or Evening (after 4 p.m.)  $25.00 (2) : ");
                option1 = scanner.nextInt();
                if (option1 == 1) {
                    fee += 50.00;
                } else if (option1 == 2) {
                    fee += 25.00;
                } else {
                    System.err.println("Option invalid !!");
                }
            } else if (option == 3) {
                System.out.println("1-9cr (1) or 10+cr (2) :");
                option1 = scanner.nextInt();
                if (option1 == 1) {
                    fee += 0.00;

                } else if (option1 == 2) {
                    fee += 15.00;
                } else {
                    System.err.println("your option invalid ! pls choose (1) for 1-9cr or (2) for 10+ cr !!");
                }
            } else if (option == 4) {
                fee += 25.00;
            } else if (option == 5) {
                System.out.println("Enter number of course(1-10) : ");
                numberOfCourse = scanner.nextInt();
                if (numberOfCourse >= 1 && numberOfCourse <= 3) {
                    fee += 0.00;
                } else if (numberOfCourse >= 4 && numberOfCourse <= 10) {
                    fee += (3.00 * numberOfCourse);
                } else {
                    System.out.println("Number Of Course Invalid ");
                }
                break;
            } else if (option == 6) {

                //CASE: THE SAME NUMBER OF COURSE  IN CASE 5 
                fee += 1.25 * numberOfCourse;

                //CASE: DIFFERENT COURSES IN CASE 5
//                    System.out.println("Enter number of course(1-10) : ");
//                    numberOfCourse = scanner.nextInt();
//                    if (numberOfCourse >= 1 && numberOfCourse <= 10) {
//                        fee += 1.25*numberOfCourse;
//                    } else {
//                        System.out.println("Number Of Course Invalid ");
//                    }
            } else if (option == 7) {
                return fee;
               
            } else {
                System.err.println("Your Option invalid !!!");
            }
        }
        return 0;
    }
    static void menuFee() {
        System.out.println("************Fee Table*************");
        System.out.println("1. Computer Lab Fee (CL)");
        System.out.println("2. Parking Permit Fee (CQ)");
        System.out.println("3. Transportation Management Plan Fee (CP)");
        System.out.println("4.  Universal eLearning Fee (UD)");
        System.out.println("5. Universal Technology Fee (UT)");
        System.out.println("6. Wellness Center Access Fee (DX)");
        System.out.println("7. No additional fee");
    }
// Print Method - - - - - - - - - -
   static void printAll(Scanner input, double tuition, double fees, String residency)
  {
     // Read student info file
     while (input.hasNextLine())
     {
       // Initiate and assign info variables from file
       String name = input.nextLine();
       String sID = input.nextLine();
       String address = input.nextLine();
       String rcred = input.nextLine();

       // print studentInfo from local variables
       System.out.println("----------------------------------");
       System.out.println(name + "\t Student ID: " + sID);
       System.out.println(address);
       System.out.println("Number of credits: " + rcred);
       System.out.println("Residency status: " + residency);
       System.out.printf("Tuition is $%.2f\n", tuition);
       System.out.printf("Fees is $%.2f\n", fees);
       System.out.printf("Total cost is $%.2f",(tuition + fees));
       System.out.println();
     }
   }
}
