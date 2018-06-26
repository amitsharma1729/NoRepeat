import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class RepeatMain {
	static ArrayList<Integer> digits;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int date, month, year, level = 1, optionNo, startYear, endYear, lastDayOfMonth, dayNoCal, monthNoCal, yearNoCal,
			classifiedNo1, classifiedNo2, finalNo, currentTriplet;
		List<Integer> availableNo = new ArrayList<>();
		List<Integer> missingNo = new ArrayList<>();
		List<Integer> availableTriplets = new ArrayList<>();
		List<Integer> missingTriplets = new ArrayList<>();
		boolean isNumRepeating = false, tripletPresent1 = false, tripletPresent2 = false, tripletPresent3 = false;
		int[] triplets = new int[]{438, 951, 276, 492, 357, 816, 456, 258};
		String classifiedNo;
		Map<String, ArrayList> dateSets = new HashMap<>();
		System.out.println("Select your mode of operation: ");
		System.out.println("1: Automatic date");
		System.out.println("2: Check validty of date");

		optionNo = in.nextInt();
		digits = new ArrayList<>();
		if (optionNo == 2) {
			System.out.println("enter date: ");
			date = in.nextInt();
			System.out.println("enter month: ");
			month = in.nextInt();
			System.out.println("enter year: ");
			year = in.nextInt();
			System.out.println("Data for date : "+date+"/"+month+"/"+year);
			date = findSum(date, level);
			month = findSum(month, level);
			year = findSum(year, level);
			// System.out.println("date: "+date+" mont: "+month+" year: "+year);
			classifiedNo1 = date;
			digits.add(date);
			digits.add(month);
			digits.add(year);
			finalNo = findSum(date + month + year, level + 1);
			classifiedNo2 = finalNo;
			digits.add(finalNo);
			System.out.println("Missing no:-");
			for (int i = 1; i < 10; i++) {
				if (!digits.contains(i)) {
					missingNo.add(i);
				}
				else {
					availableNo.add(i);
				}
			}
			System.out.println("Missing no: ");
			System.out.println(missingNo);
			System.out.println("Available no: ");
			System.out.println(availableNo);
			for(int i=0; i<triplets.length; i++) {
				tripletPresent1 = false;
				tripletPresent2 = false;
				tripletPresent3 = false;
				currentTriplet = triplets[i];
				if(availableNo.contains(currentTriplet%10)) {
					tripletPresent1 = true;
					currentTriplet = currentTriplet/10;
					if(availableNo.contains(currentTriplet%10)) {
						tripletPresent2 = true;
						currentTriplet = currentTriplet/10;
						if(availableNo.contains(currentTriplet)) {
							tripletPresent3 = true;
							
						}
					}
				}
				if(tripletPresent1&&tripletPresent2&&tripletPresent3) {
					availableTriplets.add(triplets[i]);
				}
				else {
					missingTriplets.add(triplets[i]);
				}
			}
			System.out.println("Set: "+"("+classifiedNo1+","+classifiedNo2+")");
			System.out.println("Available triplets: ");
			System.out.println(availableTriplets);
			System.out.println("Missing triplets: ");
			System.out.println(missingTriplets);
		} else {
			System.out.println("Enter the starting year: ");
			startYear = in.nextInt();

			System.out.println("Enter the end year: ");
			endYear = in.nextInt();
			System.out.println("No number repeating dates: ");
			for (int yearNo = startYear; yearNo <= endYear; yearNo++) {
				for (int monthNo = 1; monthNo <= 12; monthNo++) {
					if (monthNo == 2) {
						if (yearNo % 400 == 0) {
							lastDayOfMonth = 29;
						} else if (yearNo % 100 == 0) {
							lastDayOfMonth = 28;
						} else if (yearNo % 4 == 0) {
							lastDayOfMonth = 29;
						} else {
							lastDayOfMonth = 28;
						}
					} else {

						if (monthNo > 7) {
							if (monthNo % 2 == 0) {
								lastDayOfMonth = 31;
							} else {
								lastDayOfMonth = 30;
							}
						} else {
							if (monthNo % 2 == 0) {
								lastDayOfMonth = 30;
							} else {
								lastDayOfMonth = 31;
							}
						}

					}

					for (int dayNo = 1; dayNo <= lastDayOfMonth; dayNo++) {

						dayNoCal = dayNo;
						monthNoCal = monthNo;
						yearNoCal = yearNo;

						level = 1;
						digits.clear();
						isNumRepeating = false;
						//System.out.println("dayno: " + dayNo + " month no: " + monthNo + " year no : " + yearNo);
						dayNoCal = findSum(dayNoCal, level);
						monthNoCal = findSum(monthNoCal, level);
						yearNoCal = findSum(yearNoCal, level);
						// System.out.println("date: "+date+" mont: "+month+" year: "+year);
						digits.add(dayNoCal);
						digits.add(monthNoCal);
						digits.add(yearNoCal);
						classifiedNo1 = dayNoCal; 
						finalNo = findSum(dayNoCal + monthNoCal + yearNoCal, level + 1);
						digits.add(finalNo);
						classifiedNo2 = finalNo;
						for (int i = 1; i < 10; i++) {
							if (!digits.contains(i)) {
								isNumRepeating = true;
							}
						}
						if (isNumRepeating) {

						} else {
							classifiedNo = classifiedNo1+","+classifiedNo2;
							dateSets.putIfAbsent(classifiedNo,new ArrayList<>());
							dateSets.get(classifiedNo).add(dayNo + ":" + monthNo + ":" + yearNo);
						}

					}
				}
			}
			for(Entry<String, ArrayList> ee : dateSets.entrySet()) {
				String key = ee.getKey();
				ArrayList values = ee.getValue();
				System.out.println("("+key+")");
				System.out.println(values);
			}
		}
	}

	private static int findSum(int digit, int level) {
		// TODO Auto-generated method stub
		int sum = 0;
		if (digit < 10) {
			return digit;
		} else {
			while (digit > 0) {
				sum += (digit % 10);
				if (level == 1) {
					digits.add(digit % 10);
				}
				digit /= 10;
			}
			if (sum > 9) {
				level++;
				sum = findSum(sum, level);
			} else {
				return sum;
			}

		}

		return sum;
	}

}
