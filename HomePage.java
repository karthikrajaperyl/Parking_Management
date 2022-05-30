import java.io.BufferedReader;
import java.io.InputStreamReader;

interface Parking_Management {
	void addParkingArea() throws Exception;

	void addAccount() throws Exception;

	void assignVehicleSpot() throws Exception;

	void spotDisplay();

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
						"1.Add an Account\n2.Add Parking Area\n3.Assign Vehicle Spot\n4.change Detials\n5.DisplayBoardDetails\n6.Exist");
				int n = Integer.parseInt(readerobj.readLine());
				Parking_Management parkingobj = new Parking_Details();
				switch (n) {
				case 1:
					parkingobj.addAccount();
					break;
				case 2:
					parkingobj.addParkingArea();
					break;
				case 3:
					parkingobj.assignVehicleSpot();
					break;
//    	 System.out.println();
				case 4:
					Parking_Details.changeDetails();
					break;
				case 5:
					parkingobj.spotDisplay();
					break;
				case 6:
					available = false;
					System.out.println("Program Termination");
					break;
				default:
					System.out.println("Enter the Correct choice");
				}
			}

			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
