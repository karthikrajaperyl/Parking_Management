package Parking_System;

import java.io.BufferedReader;
import java.io.InputStreamReader;

interface Parking_Management {
    void addAccount() throws Exception;
    void addParkingArea() throws Exception;

    void assignVehicleSpot() throws Exception;

    void spotDisplay();

    void destroySpot() throws Exception;
}

public class HomePage {
    static BufferedReader readerobj = new BufferedReader(new InputStreamReader(System.in));
    static String OrganizationName = "Ola";

    public static void main(String[] args) {
        boolean available = true;
        while (available) {
            try {
                // TODO Auto-generated method stub
                System.out.println("Enter Your Choice");
                System.out.println(
                        "1.Add an Account\n2.Add Parking Area\n3.Assign Vehicle Spot\n4.Change Detials\n5.DisplayBoardDetails\n6.DestroySpot\n7.Exist");
                int n = Integer.parseInt(readerobj.readLine());
                Parking_Management parkingObj = new Parking_Details();
                switch (n) {
                case 1:
                    parkingObj.addAccount();
                    break;
                case 2:
                    parkingObj.addParkingArea();
                    break;
                case 3:
                    parkingObj.assignVehicleSpot();
                    break;
                case 4:
                    Parking_Details.changeDetails();
                    break;
                case 5:
                    parkingObj.spotDisplay();
                    break;
                case 6:
                    parkingObj.destroySpot();
                    break;
                case 7:
                    available = false;
                    System.out.println("Program Termination");
                    break;
                default:
                    System.out.println("Enter the Correct choice");
                }
            }

            catch (Exception e) {
                e.printStackTrace();
                System.out.println(e);
            }
        }
    }
}
