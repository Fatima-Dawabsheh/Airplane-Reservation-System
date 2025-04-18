import java.util.Scanner;

public class Airplane_Reservation_System {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		boolean[] status = new boolean[26]; // One dimensional array to save the status of the seats
		int choice;
		int first_count = 0; // To count the number of first class seats reserved until now
		int economy_count = 0; // To count the number of economy seats reserved until now
		do {
			menu();
			choice = input.nextInt(); // To take the user's choice number as input
			switch (choice) {
			case 1:
				if (firstClass(status)) { // Call the firstClass method
					first_count++; // Increment the number of first class seats reserved
					System.out.println("Total number of first class seats reserved until now = " + first_count);
					System.out.println("Total number of economy seats reserved until now = " + economy_count);
					System.out.println("Total number of seats reserved on the plane until now = " + (first_count + economy_count));
					// To check if all economy seats are sold out
					if (economy_count == 24) {
						System.out.println("all economy seats are sold out.");
					}
					// To check if all first class seats are sold out
					if (first_count == 2) {
						System.out.println("all first class seats are sold out.");
					}
				}
				break;
			case 2:
				// To check if there is an economy seats available
				boolean reserved = true;
				for (int i = 2; i < status.length; i++) {
					if (!status[i]) {
						reserved = false;
					}
				}
				if (reserved) {
					System.out.println("Sorry,no economy seats are available");
				} else {
					// To choose a specific number of the economy seat
					System.out.print("Please choose an economy seat number between 3 and 26 to reserve: ");
					int seat_number = input.nextInt();
					// To check if the specific number is valid
					if (seat_number < 3 || seat_number > 26) {
						System.out.println("Invalid economy seat number!Plaese try again");
					} else if (specificEconomySeat(status, seat_number)) { // Call the specificEconomySeat method
						economy_count++; // Increment the number of economy seats reserved
						System.out.println("Total number of first class seats reserved until now = " + first_count);
						System.out.println("Total number of economy seats reserved until now = " + economy_count);
						System.out.println("Total number of seats reserved on the plane until now = "+ (first_count + economy_count));
						if (economy_count == 24) {
							System.out.println("all economy seats are sold out.");
						}
						if (first_count == 2) {
							System.out.println("all first class seats are sold out.");
						}
					}
				}
				break;
			case 3:
				if (firstAvailableEconomyWindowSeat(status)) { // Call the irstAvailableEconomyWindowSeat method
					economy_count++; // Increment the number of economy seats reserved
					System.out.println("Total number of first class seats reserved until now = " + first_count);
					System.out.println("Total number of economy seats reserved until now = " + economy_count);
					System.out.println("Total number of seats reserved on the plane until now = " + (first_count + economy_count));
					if (economy_count == 24) {
						System.out.println("all economy seats are sold out.");
					}
					if (first_count == 2) {
						System.out.println("all first class seats are sold out.");
					}
				}
				break;
			case 4:
				// To enter the specific number of economy seat the user want to remove
				System.out.print("Enter the economy seat number from 3 to 26 you want to remove: ");
				int seat_num = input.nextInt();
				// To check if the specific number is valid
				if (seat_num < 3 || seat_num > 26) {
					System.out.println("Invalid economy seat number!Plaese try again");
				} else if (removeSpecificReservedEconomy(status, seat_num)) { // Call the removeSpecificReservedEconomy method
					economy_count--; // decrement the number of economy seats reserved
					System.out.println("Total number of first class seats reserved until now = " + first_count);
					System.out.println("Total number of economy seats reserved until now = " + economy_count);
					System.out.println("Total number of seats reserved on the plane until now = " + (first_count + economy_count));
					if (economy_count == 24) {
						System.out.println("all economy seats are sold out.");
					}
					if (first_count == 2) {
						System.out.println("all first class seats are sold out.");
					}
				}
				break;
			case 5:
				// To enter the specific number of first class seat the user want to remove
				System.out.print("Enter the first class seat number 1 or 2 you want to remove: ");
				int seatnum = input.nextInt();
				// To check if the specific number is valid
				if (seatnum < 1 || seatnum > 2) {
					System.out.println("Invalid first class seat number!Plaese try again");
				} else if (removeSpecificReservedFirstClass(status, seatnum)) { // Call the removeSpecificReservedFirstClass method
					first_count--; // decrement the number of first class seats reserved
					System.out.println("Total number of reserved seats in first class seats until now = " + first_count);
					System.out.println("Total number of reserved seats in economy seats until now = " + economy_count);
					System.out.println("Total number of reserved seats in plane until now = " + (first_count + economy_count));
					if (economy_count == 24) {
						System.out.println("all economy seats are sold out.");
					}
					if (first_count == 2) {
						System.out.println("all first class seats are sold out.");
					}
				}
				break;
			case 6:
				printReservationlayout(status); // Call the printReservationlayout method
				break;
			case 7:
				System.out.println("Exiting!! Thank you"); // Exit from the program
				break;
			default:
				System.out.println("Invalid !! try again please"); // The choice number not valid in the menu
			}
		} while (choice != 7);
		input.close();
	}

// Method to display the menu for the user
	public static void menu() {
		System.out.println("\t\tWelcome to BZU Sky Reservation System,choose: ");
		System.out.println("1_ To reserve one first class seat (1-2).");
		System.out.println("2_ To reserve a specific economy seat (3-26).");
		System.out.println("3_ To reserve the 1st available economy window seat.");
		System.out.println("4_ To remove a specific reserved economy seat.");
		System.out.println("5_ To remove a specific reserved first-class seat.");
		System.out.println("6_ To print reservation layout details.");
		System.out.println("7_ To exit.");
		System.out.print("\tEnter your choice (1 - 7)? ");
	}

// Method to reserve one first class seat (1-2)
	public static boolean firstClass(boolean[] status) {
		for (int i = 0; i < 2; i++) {
			if (!status[i]) { // Check if seat not reserved
				status[i] = true; // Reserve seat
				System.out.println("Reservation successful for first class seat #" + (i + 1));
				return true; // Return true to the main to increment the number of first class seats reserved
			}
		}
		System.out.println("Sorry,no first class seats are available");
		return false; // Return false to the main which is mean no first class seats are available
	}

// Method to reserve a specific economy seat (3-26)
	public static boolean specificEconomySeat(boolean[] status, int seat_number) {
		if (!status[seat_number - 1]) { // Check if seat not reserved
			status[seat_number - 1] = true;// Reserve seat
			System.out.println("Reservation successful for economy seat #" + seat_number);
			return true;// Return true to the main to increment the number of economy seats reserved
		} else {
			System.out.println("Sorry,the economy seat is already reserved");
			return false;// Return false to the main which is mean the economy seat is already reserved
		}
	}

// Method to reserve the 1st available economy window seat
	public static boolean firstAvailableEconomyWindowSeat(boolean[] status) {
		int i = 2;
		while (i < status.length) {
			if (!status[i]) {
				status[i] = true;
				System.out.println("Reservation successful for first available economy window seat #" + (i + 1));
				return true;
			} else if (status[i]) {
				i += 3; // increment 3 to i for to represent the Window seats
				if (!status[i]) {
					status[i] = true;
					System.out.println("Reservation successful for first available economy window seat #" + (i + 1));
					return true;
				} else if (i + 1 < status.length && !status[i + 1]) {
					status[i + 1] = true;
					System.out.println("Reservation successful for first available economy window seat #" + (i + 2));
					return true;
				}
			}
			i++;
		}
		System.out.println("Sorry,no economy window seats are available");
		return false;
	}

// Method to remove a specific reserved economy seat
	public static boolean removeSpecificReservedEconomy(boolean[] status, int seat_num) {
		if (status[seat_num - 1]) { // Check if seat reserved
			status[seat_num - 1] = false; // Remove the reserve
			System.out.println("Removal successful for economy seat #" + seat_num);
			return true; // Return true to the main to decrement the number of economy seats reserved
		} else {
			System.out.println("The economy seat is already not reserved");
			return false;// Return false to the main which is mean the economy seat is already not reserved
		}
	}

// Method to remove a specific reserved first-class seat
	public static boolean removeSpecificReservedFirstClass(boolean[] status, int seatnum) {
		if (status[seatnum - 1]) { // Check if seat reserved
			status[seatnum - 1] = false; // Remove the reserve
			System.out.println("Removel successful for first class seat #" + seatnum);
			return true;// Return true to the main to decrement the number of first class seats reserved
		} else {
			System.out.println("The first class seat is aleady not reserved");
			return false;// Return false to the main which is mean the first class seat is already not reserved
		}
	}

// Method to print reservation layout details
	public static void printReservationlayout(boolean[] status) {
		for (int i = 0; i < status.length; i++) {
			if (i == 0) {
				// To check if seat reserved or not
				if (status[i + 1]) {
					System.out.print("[(2)]\t"); // Print () around the number of seat for represent the reserved
				} else
					System.out.print("[2]\t"); // Print just the number if the seat not reserved
				for (int j = 5; j < status.length; j += 4) {
					// To check if seat reserved or not
					if (status[j]) {
						System.out.print("[(" + (j + 1) + ")]\t");// Print () around the number of seat for represent the reserved
					} else
						System.out.print("[" + (j + 1) + "]\t");// Print just the number if the seat not reserved
				}
				System.out.print("\n\t");
				for (int j = 4; j < status.length; j += 4) {
					// To check if seat reserved or not
					if (status[j]) {
						System.out.print("[(" + (j + 1) + ")]\t");// Print () around the number of seat for represent the reserved
					} else
						System.out.print("[" + (j + 1) + "]\t");// Print just the number if the seat not reserved
				}
			}
			if (i == 1) {
				System.out.print("\n\n\t");
				for (int j = 3; j < status.length; j += 4) {
					// To check if seat reserved or not
					if (status[j]) {
						System.out.print("[(" + (j + 1) + ")]\t");// Print () around the number of seat for represent the reserved
					} else
						System.out.print("[" + (j + 1) + "]\t");// Print just the number if the seat not reserved
				}
				// To check if seat reserved or not
				if (status[i - 1]) {
					System.out.print("\n[(1)]\t");// Print () around the number of seat for represent the reserved
				} else
					System.out.print("\n[1]\t");// Print just the number if the seat not reserved
				for (int j = 2; j < status.length; j += 4) {
					// To check if seat reserved or not
					if (status[j]) {
						System.out.print("[(" + (j + 1) + ")]\t");// Print () around the number of seat for represent the reserved
					} else
						System.out.print("[" + (j + 1) + "]\t");// Print just the number if the seat not reserved
				}
				System.out.println("");
			}
		}
	}
}
