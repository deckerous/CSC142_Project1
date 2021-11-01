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
      System.out.println();
      System.out.println("/---------------------------------------------------------\\");
      System.out.println("|Welcome To the RegistrationTool for North Seattle College|");
      System.out.println("\\---------------------------------------------------------/");
      System.out.println();
      do{
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
        int id1 = 0;
        int id2 = 0;
        int id3 = 0;
        do
        {
            System.out.println("Please enter the first 3 digits of ");
            System.out.print("your student ID number: ");

            while(!input.hasNextInt() && input!=null) {
                System.out.println("That isn't an appropriate number");
                System.out.println();
                System.out.println("Please enter the first 3 digits of ");
                System.out.print("your student ID number: ");
                id1 = input.nextInt();
                }
            id1 = input.nextInt();
            if (id1>999)
            {
                System.out.println("Too many digits");
            }
            System.out.println();
        } while (id1 > 999 || id1 < 0);
        do
        {
            System.out.println("Please enter the next 2 digits of ");
            System.out.print("your student ID number: ");

            while(!input.hasNextInt() && input!=null) {
                System.out.println("That isn't an appropriate number");
                System.out.println();
                System.out.println("Please enter the next 2 digits of ");
                System.out.print("your student ID number: ");
                id2 = input.nextInt();
                }
            id2 = input.nextInt();
            if (id2>99)
            {
                System.out.println("Too many digits");
            }
        } while (id2 > 99 || id2 < 0);
        do
        {
            System.out.println("Please enter the last 4 digits of ");
            System.out.print("your student ID number: ");

            while(!input.hasNextInt() && input!=null) {
                System.out.println("That isn't an appropriate number");
                System.out.println();
                System.out.println("Please enter the last 4 digits of ");
                System.out.print("your student ID number: ");
                id3 = input.nextInt();
                }
            id3 = input.nextInt();
            if (id2>9999)
            {
                System.out.println("Too many digits");
            }
            System.out.println();
        } while (id3 > 9999 || id3 < 0);
        System.out.print("The student number that you provided is: ");
        if(id1<10)
        {
            System.out.print("00" + id1 + "-");
            output.println(id1 + "-");
        } else if (id1<100)
        {
            System.out.print("0" + id1 + "-");
            output.print(id1 + "-");
        } else
        {
            System.out.print(id1 + "-");
            output.print(id1 + "-");
        }
        if(id2<10)
        {
            System.out.print("0" + id2 + "-");
            output.print(id2 + "-");
        } else
        {
            System.out.print(id2 + "-");
            output.print(id2 + "-");
        }
        if(id3<10)
        {
            System.out.println("000" + id3);
            output.println(id3);
        } else if (id3<100)
        {
            System.out.println("00" + id3);
            output.println(id3);
        } else if (id3<1000)
        {
            System.out.println("0" + id3);
            output.println(id3);
        } else
        {
            System.out.println(id3);
            output.println(id3);
        }
        System.out.println();

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
            if(rcred <= 0 || rcred > 20) //Max of 20 but ASK???!??!?!
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
        return creditAndStatus;
       System.out.println();
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
          System.out.print();
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
  static double getFee(double number_Of_Credit, Scanner scanner) {
      System.out.println();
      System.out.println("Please use the fee table to add");
      System.out.println("any applicable fees.");
      double fee = 0.00;
      int numberOfCourse = 0;
      int option = 0;
      int option1 = 0;
      while (true) {
          menuFee();
          System.out.print("----------------------------------\n Enter fee (#): ");
          option = scanner.nextInt();
          if (option == 1) {
              System.out.println("1 class(1) or 2 classes(2) ?");
              option1 = scanner.nextInt();
              if (option1 == 1) {
                  fee += 62.00;
              } else if (option1 == 2) {
                  fee += 97.00;
              } else {
                  System.err.println("Option invalid !!");
              }
          } else if (option == 2) {
              System.out.println("All the time permit: $50.00 (1) or Evening (after 4 p.m.): $25.00 (2) : ");
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
                  System.err.println("Invalid Selection! please choose (1) for 1-9cr or (2) for 10+ cr !!");
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
          } else {
              System.err.println("Your Option invalid !!!");
          }
          scanner.nextLine(); //Clear data
          System.out.print("Add another fee? (Y/N) ");
          String answer = scanner.nextLine();
          if (answer.equalsIgnoreCase("n") || answer.equalsIgnoreCase("no")) {
              return fee;
          }
      System.out.println();
      }
      return 0;
  }
  static void menuFee() {
      System.out.println("************Fee Table*************");
      System.out.println("1. Computer Lab Fee (CL)");
      System.out.println("2. Parking Permit Fee (CQ)");
      System.out.println("3. Transportation Management Plan Fee (CP)");
      System.out.println("4. Universal eLearning Fee (UD)");
      System.out.println("5. Universal Technology Fee (UT)");
      System.out.println("6. Wellness Center Access Fee (DX)");
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
       //String address = input.nextLine();
       String credits = input.nextLine();

       // print studentInfo from local variables
       System.out.println("----------------------------------");
       System.out.println(name + "\t Student ID: " + sID);
       //System.out.println(address);
       System.out.println("Number of credits: " + credits);
       System.out.println("Residency status: " + residency);
       System.out.printf("Tution is $%.2f\n", tuition);
       System.out.printf("Tution is $%.2f\n", fees);
       System.out.printf("Total cost is $%.2f",(tuition + fees));
       System.out.println();
     }
   }
}
