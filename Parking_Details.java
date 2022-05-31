package Parking_System;

import java.util.*;

import java.io.*;

public class Parking_Details implements Parking_Management {
    static BufferedReader readerobj = new BufferedReader(new InputStreamReader(System.in));
    static List<ParkingArea> arealist = new ArrayList<>();
    static Map<String, Vehicle> vehiclestorage = new HashMap<>();
    static Map<String, Account> accountstorage = new HashMap<>();
    static Map<Integer, ParkingSpot> spotStorage = new HashMap<>();
    static Map<Integer, UserSpot> userObject = new HashMap<>();

    ParkingSpot getSpotInstance(int number) {
        switch (number) {
        case 1:
            return new Handicapped();
        case 2:
            return new TwoWheeler();
        case 3:
            return new FourWheelerMin();
        default:
            return new FourWheelerMax();
        }
    }

    private Vehicle getVehicleInstance(int number) {
        switch (number) {
        case 1:
            return new Car();
        case 2:
            return new Truck();
        case 3:
            return new Van();
        case 4:
            return new Bike();
        default:
            return new Electric();
        }
    }

    public void addParkingArea() throws IOException {
        System.out.println("Enter the parking Building name");
        String name = readerobj.readLine();
        System.out.println("Enter the Floor No");
        int floor = Integer.parseInt(readerobj.readLine());
        System.out.println("Enter the total number of vacancy for each floor");
        int totalvacancy = Integer.parseInt(readerobj.readLine());
        int spottype[] = new int[totalvacancy];
        System.out.println("Enter the vacancy for different spots");
        System.out.println("1.HANDICAPPED\n2.TWOWHEELER\n3.FOURWHEELERMIN\n4.FOURWHEELERMAX\n");
        for (int i = 0; i < totalvacancy; i++) {
            spottype[i] = Integer.parseInt(readerobj.readLine());
        }
        ParkingArea parkingDetails = new ParkingArea(name, floor, totalvacancy, spottype);
        arealist.add(parkingDetails);
    }

    public void addAccount() throws IOException {
    	Account accountObj;
        System.out.println("Enter your name");
        String name = readerobj.readLine();
        System.out.println("Enter your vehicle plate No");
        String plateNo = readerobj.readLine();
        System.out.println("Enter the phone no");
        String phoneNo = readerobj.readLine();
        Boolean notValid = true;
        while (notValid) {
            try {
                Long.parseLong(phoneNo);
                notValid = false;
            } catch (Exception e) {
                System.out.println("please enter the valid number");
                phoneNo = readerobj.readLine();
            }
        }
        System.out.println("Enter your Email address");
        String email = readerobj.readLine();
        while (!email.contains("@")) {
            System.out.println("please Enter your valid Email address");
            email = readerobj.readLine();
        }
        System.out.println("Enter your place name");
        String address = readerobj.readLine();
        if(email==" "||address==" ")
        accountObj=new Account(name,plateNo,phoneNo);
        else
        accountObj = new Account(name, plateNo, phoneNo, email, address);
        accountObj.changeAccountStatus(1);
        accountObj.changeTicketStatus(1);
        accountstorage.put(plateNo, accountObj);
    }

    protected Vehicle addVehicleDetails() throws IOException {
        Vehicle vehicle = null;// =new Vehicle();
        System.out.println("1.Bike\n2.Truck\n3.Electric\n4.Van\n5.Car");
        int vehicletype = Integer.parseInt(readerobj.readLine());
        System.out.println("Enter your account  vehicle plate no");
        String plateNo = readerobj.readLine();
        System.out.println("Enter the setposition-\n1.Enter\n2.Exist");
        int positionstatus = Integer.parseInt(readerobj.readLine());
        vehicle = getVehicleInstance(vehicletype);
        vehicle.setPositionStatus(positionstatus);
        vehicle.setPlateNo(plateNo);
        vehiclestorage.put(plateNo, vehicle);
        return vehicle;
    }

    public ParkingSpot addParkingSpot() throws IOException {
        ParkingSpot spotObj = null;
        Random random = new Random();
        int id = random.nextInt(200);
        System.out.println("Available Spots Space are");
        spotDisplay();
        System.out.println("Choose the building name");
        String name = readerobj.readLine();
        if (!validParkingName(name)) {
            System.out.println("you have entered wrong parking building name");
            return null;
        }
        System.out.println("Enter the Spot Type for Vehicle");
        System.out.println("1.HANDICAPPED\n2.TWOWHEELER\n3.FOURWHEELERMIN\n4.FOURWHEELERMAX");
        int spotType = Integer.parseInt(readerobj.readLine());
       
        System.out.println("Choose the Floor");
        int floor = Integer.parseInt(readerobj.readLine());
        if (!validEntry(name,spotType,floor) ){
            System.out.println("you have entered wrong entry");
            return null;
        }
        System.out.println("Enter the Fare Amount");
        int fareAmount = Integer.parseInt(readerobj.readLine());
        spotObj = getSpotInstance(spotType);
        spotObj.setBuildingName(name);
        spotObj.setFare(fareAmount);
        spotObj.setFloor(floor);
        spotStorage.put(id, spotObj);
        return spotObj;
    }

    public ParkingSpot getUserSpot() throws IOException {
        ParkingSpot parkingspotobj = null;
      
        parkingspotobj = addParkingSpot();
        if (parkingspotobj==null||parkingspotobj.checkAvailableSpot()) {
            return null;
        }
        parkingspotobj.DecrementCount();
        return parkingspotobj;
    }

    public Account getAccount() throws IOException {
        System.out.println("Enter your account PlateNo");
        String plateNo = readerobj.readLine();
        return accountstorage.get(plateNo);
    }

    protected static void changeDetails() throws IOException {
        System.out.println("Enter the account Plate no");
        String plateNo = readerobj.readLine();
        Admin tempObj1 =(Admin) accountstorage.get(plateNo);
       
        Vehicle tempObj2 = vehiclestorage.get(plateNo);
        System.out.println("Enter the field to change");
        System.out.println("1.Accountstatus\n2.TicketStatus\n3.Exist");
        int i = Integer.parseInt(readerobj.readLine());
        switch (i) {
        case 1:
            System.out.println("Enter the Account status");
            System.out.println("1.Active\n2.Archived\n3.Blocked\n4.Unknown");
            int j = Integer.parseInt(readerobj.readLine());
            tempObj1.changeAccountStatus(j);
            break;
        case 2:
            System.out.println("Enter the Ticket Status");
            System.out.println("1.Active\n2.Paid\n3.Lost");
            j = Integer.parseInt(readerobj.readLine());
            tempObj1.changeTicketStatus(j);
            break;
        }
        // accountstorage.put(platno, tempObj2);
        tempObj2.setPositionStatus();
    }

    public void assignVehicleSpot() throws IOException {
        UserSpot userobj = new UserSpot();
        ParkingSpot parkingObj = getUserSpot();
        if (parkingObj == null) {
            return;
        }
        Account accountObj = getAccount();
        Vehicle vehicleObj = addVehicleDetails();
        if (accountObj.getAccountStatus() == AccountStatus.ACTIVE) {
            userobj.setSpotInstance(parkingObj);
            userobj.setAccountInstance(accountObj);
            userobj.setVehiclInstance(vehicleObj);
            parkingObj.IncrementCount();
            userObject.put(userobj.id, userobj);
        } else
            System.out.println("Account is not active");

    }

    public boolean validParkingName(String name) {
        for (ParkingArea tempobj : arealist) {
            if (name.equals(tempobj.buildingName)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean validEntry(String name, int spotType, int floor) {
        for (ParkingArea tempobj : arealist) {
            if (name.equals(tempobj.buildingName) && tempobj.getFloorno()>=floor) {
                for (int i = 0; i < tempobj.getSpotTypes().length; i++) {
                    if (spotType == tempobj.getSpotTypes()[i]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public void spotDisplay() {
        for (ParkingArea tempobj : arealist) {
            System.out.print("Building Name-" + tempobj.buildingName + "\nAvailable Floor-" + tempobj.Floorno
                    + "\nSpots-Available");
            for (int i = 0; i < tempobj.getSpotTypes().length; i++) {
                int j = tempobj.getSpotTypes()[i];
                switch (j) {
                case 1:
                    System.out.print(" 1.HANDICAPPED\n ");
                    break;
                case 2:
                    System.out.print(" 2.TWOWHEELER\n ");
                    break;
                case 3:
                    System.out.print(" 3.FOURWHEELERMIN\n ");
                    break;
                case 4:
                    System.out.print(" 4.FOURWHEELERMAX\n");
                    break;
                }
            }
        }
    }

    public void destroySpot() throws IOException {
        System.out.println("Enter the User assigned Spot Id");
        System.out.println("Occupied Spots are");
        for(Map.Entry<Integer, ParkingSpot> mapiter:spotStorage.entrySet())
        {
        	System.out.println(mapiter.getKey());
        }
        int id = Integer.parseInt(readerobj.readLine());
        if (spotStorage.containsKey(id)) {
            ParkingSpot spotObj = spotStorage.get(id);
            spotObj.DecrementCount();
            spotObj = null;
        } else {
            System.out.println("Entered  Spot Id is anot available");
        }
    }
}

