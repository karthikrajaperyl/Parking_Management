import java.util.*;

enum VehicleType {
	CAR, TRUCK, VAN, BIKE, ELECTRIC
}

enum ParkingSpotType {
	HANDICAPPED, TWOWHEELER, FOURWHEELERMIN, FOURWHEELERMAX
}

enum AccountStatus {
	ACTIVE, BLOCKED, UNKNOWN, ARCHIVED
}

enum ParkingTicketStatus {
	ACTIVE, PAID, LOST
}

class ParkingArea {

	Vehicle vehicleobj;
	String buildingName;
	int Floorno;
	int totalVacancy;
	int[] spottype;

	ParkingArea(String name, int floorno, int total) {
		this.buildingName = name;
		this.Floorno = floorno;
		this.totalVacancy = total;
	}

	void setSpotTypes(int[] spottype) {
		this.spottype = spottype;
	}

	int[] getSpotTypes() {
		return spottype;
	}

	String getBuildingName() {
		return this.buildingName;
	}

	int getFloorno() {
		return this.Floorno;
	}

	int getVacancy() {
		return totalVacancy;
	}

	int getFloorSpot(int index) {
		return this.spottype[index];
	}
}

class Account {
	private String name;
	private AccountStatus accountstatus;
	private String platno;
	private String phoneno;
	private String email;
	String address;
	ParkingTicketStatus ticketstatus;

	Account() {
		ticketstatus = ParkingTicketStatus.ACTIVE;
	}

	Account(String name, String platno, String phoneno) {
		this.name = name;
		this.platno = platno;
		this.phoneno = phoneno;
	}

	Account(String name, String platno, String phoneno, String email, String address) {
		this.name = name;
		this.platno = platno;
		this.phoneno = phoneno;
		this.email = email;
		this.address = address;
	}

	protected void setAccountStatus(int no) {
		switch (no) {
		case 1:
			this.accountstatus = AccountStatus.ACTIVE;
			break;
		case 2:
			this.accountstatus = AccountStatus.ARCHIVED;
			break;
		case 3:
			this.accountstatus = AccountStatus.BLOCKED;
			break;
		default:
			this.accountstatus = AccountStatus.UNKNOWN;
		}
	}

	protected AccountStatus getAccountStatus() {
		return this.accountstatus;
	}

	protected void setTicketStatus(int no) {
		switch (no) {
		case 1:
			this.ticketstatus = ParkingTicketStatus.ACTIVE;
			break;
		case 2:
			this.ticketstatus = ParkingTicketStatus.PAID;
			break;
		default:
			this.ticketstatus = ParkingTicketStatus.LOST;
		}
	}

	protected ParkingTicketStatus getTicketStatus() {
		return this.ticketstatus;
	}
}

abstract class Admin extends Account {
	Vehicle vehicle;
	ParkingTicketStatus ticketstatus;
	AccountStatus accountstatus;

	protected void changeAccountStatus(int n) {
		super.setAccountStatus(n);
	}

	protected void changeTicketStatus(int n) {
		super.setTicketStatus(n);
	}

	abstract void setPositionStatus();
}

abstract class ParkingSpot {
	private ParkingSpotType spottype;
	private int floor, fare;
	private ParkingArea areaobject;
	static int count = 0;
	private String name;

	protected void setArea(ParkingArea area) {
		this.areaobject = area;
	}

	protected void setBuildingName(String name) {
		this.name = name;
	}

	protected String getBuildingName() {
		return name;
	}

	protected void setFare(int fare) {
		this.fare = fare;
	}

	protected int getFare() {
		return fare;
	}

	protected void setRealCount() {
		count = areaobject.getFloorSpot(count);
	}

	protected void setCount(int count) {
		ParkingSpot.count = count;
	}

	protected int getCount() {
		return count;
	}

	protected void setSpotType(ParkingSpotType spot) {
		this.spottype = spot;
	}

	protected ParkingSpotType getSpotType() {
		return spottype;
	}

	protected void setFloor(int floor) {
		this.floor = floor;
	}

	protected int getFloor() {
		return floor;
	}

	// abstract void reduceType(String platno, int n);
	abstract void addSpotType();

	abstract boolean checkAvailableSpot();

	abstract void IncrementCount();

	abstract void DecrementCount();
}

class Handicapped extends ParkingSpot {
	protected void addSpotType() {
		super.setSpotType(ParkingSpotType.HANDICAPPED);
	}

	protected boolean checkAvailableSpot() {
		if (getCount() <= 0)
			return false;
		return true;
	}

	protected void IncrementCount() {
		super.setCount(getCount() + 1);
	}

	protected void DecrementCount() {
		super.setCount(getCount() - 1);
	}
}

class TwoWheeler extends ParkingSpot {
	protected void addSpotType() {
		super.setSpotType(ParkingSpotType.TWOWHEELER);
	}

	protected boolean checkAvailableSpot() {
		if (getCount() <= 0)
			return false;
		return true;
	}

	protected void IncrementCount() {
		super.setCount(getCount() + 1);
	}

	protected void DecrementCount() {
		super.setCount(getCount() - 1);
	}
}

class FourWheelerMax extends ParkingSpot {
	protected void addSpotType() {
		super.setSpotType(ParkingSpotType.FOURWHEELERMAX);
	}

	protected boolean checkAvailableSpot() {
		if (getCount() <= 0)
			return false;
		return true;
	}

	protected void IncrementCount() {
		super.setCount(getCount() + 1);
	}

	protected void DecrementCount() {
		super.setCount(getCount() - 1);
	}
}

class FourWheelerMin extends ParkingSpot {
	protected void addSpotType() {
		super.setSpotType(ParkingSpotType.FOURWHEELERMIN);
	}

	protected boolean checkAvailableSpot() {
		if (getCount() <= 0)
			return false;
		return true;
	}

	protected void IncrementCount() {
		super.setCount(getCount() + 1);
	}

	protected void DecrementCount() {
		super.setCount(getCount() - 1);
	}
}

class UserSpot {
	ParkingSpot parkingObj = null;
	Account accountObj = null;
	Vehicle vehicleObj = null;
	int id;

	UserSpot() {
		Random random = new Random();
		this.id = random.nextInt(50);
	}

	protected void setSpotInstance(ParkingSpot instance) {
		this.parkingObj = instance;
	}

	protected ParkingSpot getParkingInstance() {
		return parkingObj;
	}

	protected void setAccountInstance(Account instance) {
		this.accountObj = instance;
	}

	protected Account getAccountInstance() {
		return accountObj;
	}

	protected void setVehiclInstance(Vehicle instance) {
		this.vehicleObj = instance;
	}

	protected boolean checkAvailableSpot() {
		if (parkingObj.getCount() <= 0)
			return false;
		return true;
	}
}

abstract class Vehicle extends Admin {
	private boolean entry;
	private boolean exit;
	private VehicleType vehicletype;

	protected void setVehicletype(VehicleType type) {
		this.vehicletype = type;
	}

	protected void setPositionStatus(int no) {

		switch (no) {
		case 1:
			this.entry = true;
			this.exit = false;
			break;
		case 2:
			this.entry = false;
			this.exit = true;
			break;
		}
	}

	protected boolean getPositionStatus() {
		return exit;
	}

	abstract void addVehicle();

	abstract void setPlatno(String name);

	abstract void setPositionStatus();
}

class Car extends Vehicle {
	protected String platno;

	protected void addVehicle() {
		super.setVehicletype(VehicleType.CAR);
	}

	protected void setPlatno(String name) {
		this.platno = name;
	}

	protected String getPlatno() {
		return platno;
	}

	protected void setPositionStatus() {
		super.setPositionStatus(2);
	}
}

class Truck extends Vehicle {
	protected String platno;

	protected void addVehicle() {
		super.setVehicletype(VehicleType.TRUCK);
	}

	protected void setPlatno(String name) {
		this.platno = name;
	}

	protected String getPlatno() {
		return platno;
	}

	protected void setPositionStatus() {
		super.setPositionStatus(2);
	}
}

class Van extends Vehicle {
	protected String platno;

	protected void addVehicle() {
		super.setVehicletype(VehicleType.VAN);
	}

	protected void setPlatno(String name) {
		this.platno = name;
	}

	protected String getPlatno() {
		return platno;
	}

	protected void setPositionStatus() {
		super.setPositionStatus(2);
	}
}

class Bike extends Vehicle {
	protected String platno;

	protected void addVehicle() {
		super.setVehicletype(VehicleType.BIKE);
	}

	protected void setPlatno(String name) {
		this.platno = name;
	}

	protected String getPlatno() {
		return platno;
	}

	protected void setPositionStatus() {
		super.setPositionStatus(2);
	}
}

class Electric extends Vehicle {
	protected String platno;

	protected void addVehicle() {
		super.setVehicletype(VehicleType.ELECTRIC);
	}

	protected void setPlatno(String name) {
		this.platno = name;
	}

	protected String getPlatno() {
		return platno;
	}

	protected void setPositionStatus() {
		super.setPositionStatus(2);
	}
}
