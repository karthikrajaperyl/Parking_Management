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
		System.out.println("Enter the Building name");
		String name = readerobj.readLine();
		System.out.println("Enter the Floor no");
		int floor = Integer.parseInt(readerobj.readLine()), totalvacancy;
		int[] spottype = new int[floor];
		System.out.println("Enter the total number of vacancy for each floor");
		totalvacancy = Integer.parseInt(readerobj.readLine());
		System.out.println("Enter the vacancy for different spots");
		System.out.println("1.HANDICAPPED\n2.TWOWHEELER\n3.FOURWHEELERMIN\n4.FOURWHEELERMAX\n");
		for (int i = 0; i < floor; i++) {
			spottype[i] = Integer.parseInt(readerobj.readLine());
		}
		ParkingArea parkingdetails = new ParkingArea(name, floor, totalvacancy);
		parkingdetails.setSpotTypes(spottype);
		arealist.add(parkingdetails);
	}

	public void addAccount() throws IOException {
		Account accountobj = null;
//	   System.out.println("Enter the Choice");
		System.out.println("Enter your name");
		String name = readerobj.readLine();
		System.out.println("Enter the platno");
		String platno = readerobj.readLine();
		System.out.println("Enter the phone");
		String phoneno = readerobj.readLine();
		System.out.println("Enter your Email");
		String email = readerobj.readLine();
		System.out.println("Enter your address");
		String address = readerobj.readLine();
		outer: {
			if (email == " ")
				break outer;
			if (email == " " || address == " ")
				break outer;
			accountobj = new Account(name, platno, phoneno, email, address);
		}
		if (accountobj == null)
			accountobj = new Account(name, platno, phoneno);
		accountobj.setAccountStatus(1);
		accountobj.setTicketStatus(1);
		accountstorage.put(platno, accountobj);
	}

	protected Vehicle addVehicleDetails() throws IOException {
		Vehicle vehicle = null;// =new Vehicle();
		System.out.println("1.Bike\n2.Truck\n3.Electric\n4.Van\n5.Car");
		int vehicletype = Integer.parseInt(readerobj.readLine());
		System.out.println("Enter the platno");
		String platno = readerobj.readLine();
		System.out.println("Enter the setposition-\n1.Enter\n2.Exist");
		int positionstatus = Integer.parseInt(readerobj.readLine());
		vehicle = getVehicleInstance(vehicletype);
		vehicle.setPositionStatus(positionstatus);
		vehicle.setPlatno(platno);
		vehiclestorage.put(platno, vehicle);
		return vehicle;
	}

	public ParkingSpot addParkingSpot(int spottype) throws IOException {
		ParkingSpot spotobj = null;
		Random random = new Random();
		int id = random.nextInt(20);
		System.out.println("Available Spots Space are");
		spotDisplay();
		System.out.println("Choose the building");
		String name = readerobj.readLine();
		System.out.println("Choose the Floor");
		int floor = Integer.parseInt(readerobj.readLine());
		System.out.println("Enter the Fare Amount");
		int fareAmount = Integer.parseInt(readerobj.readLine());
		switch (spottype) {
		case 1:
			spotobj = getSpotInstance(spottype);
			break;

		case 2:
			spotobj = getSpotInstance(spottype);
			break;

		case 3:
			spotobj = getSpotInstance(spottype);
			break;
		default:
			spotobj = getSpotInstance(spottype);
			break;
		}
		spotobj.setBuildingName(name);
		spotobj.setFare(fareAmount);
		spotobj.setFloor(floor);
		spotobj.setArea(arealist.get(0));
		spotobj.setRealCount();
		spotStorage.put(id, spotobj);
		return spotobj;
	}

	public ParkingSpot getUserSpot() throws IOException {
		ParkingSpot parkingspotobj = null;
		System.out.println("Enter the user Vehicle");
		System.out.println("1.Handicapped\n2.Bike\n3.car\n4.VAN\n5.Truck\n6.ELECTRIC");
		int vehicletype = Integer.parseInt(readerobj.readLine());
		parkingspotobj = addParkingSpot(vehicletype);
		if (parkingspotobj.checkAvailableSpot()) {
			return null;
		}
		parkingspotobj.DecrementCount();
		return parkingspotobj;
	}

	public Account getAccount() throws IOException {
		System.out.println("Enter the Platno");
		String platno = readerobj.readLine();
		return accountstorage.get(platno);
	}

	protected static void changeDetails() throws IOException {
		System.out.println("Enter the Plat no");
		String platno = readerobj.readLine();
		Account tempObj1 = accountstorage.get(platno);
		Admin tempObj2;
		Vehicle tempObj3 = vehiclestorage.get(platno);
		tempObj2 = (Admin) tempObj1;

		System.out.println("Enter the field to change");
		System.out.println("1.Accountstatus\n2.TicketStatus\n3.Exist");
		int i = Integer.parseInt(readerobj.readLine());
		switch (i) {
		case 1:
			System.out.println("Enter the Account status");
			System.out.println("1.Active\n2.Archived\n3.Blocked\n4.Unknown");
			int j = Integer.parseInt(readerobj.readLine());
			tempObj2.setAccountStatus(j);
			break;
		case 2:
			System.out.println("Enter the Ticket Status");
			System.out.println("1.Active\n2.Paid\n3.Lost");
			j = Integer.parseInt(readerobj.readLine());
			tempObj2.setTicketStatus(j);
			break;
		}
		// accountstorage.put(platno, tempObj2);s
		tempObj2 = tempObj3;
		tempObj2.setPositionStatus();
	}

	public void assignVehicleSpot() throws IOException {

		UserSpot userobj = new UserSpot();
		ParkingSpot parkingObj = getUserSpot();
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

	public void spotDisplay() {
		for (ParkingArea tempobj : arealist)
			System.out.println("Building Name-" + tempobj.buildingName + "\nAvailable Floor-" + tempobj.Floorno
					+ "\nSpots-Available" + tempobj.getSpotTypes());
	}

	public void destroySpot() throws IOException {
		System.out.println("Enter the Spot Id");
		int id = Integer.parseInt(readerobj.readLine());
		ParkingSpot spotobj = spotStorage.get(id);
		spotobj.DecrementCount();
		spotobj = null;
	}
}
