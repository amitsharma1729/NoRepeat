import java.util.ArrayList;
import java.util.Scanner;

public class RepeatMain {
	static ArrayList<Integer> digits;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int date, month, year, level = 1, optionNo, startYear, endYear, lastDayOfMonth, dayNoCal, monthNoCal, yearNoCal;
		boolean isNumRepeating = false;
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
			date = findSum(date, level);
			month = findSum(month, level);
			year = findSum(year, level);
			// System.out.println("date: "+date+" mont: "+month+" year: "+year);
			digits.add(date);
			digits.add(month);
			digits.add(year);
			digits.add(findSum(date + month + year, level + 1));
			System.out.println("Missing no:-");
			for (int i = 1; i < 10; i++) {
				if (!digits.contains(i)) {
					System.out.print(i + " ");
				}
			}
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
						digits.add(findSum(dayNoCal + monthNoCal + yearNoCal, level + 1));
						for (int i = 1; i < 10; i++) {
							if (!digits.contains(i)) {
								isNumRepeating = true;
							}
						}
						if (isNumRepeating) {

						} else {
							System.out.println(dayNo + ":" + monthNo + ":" + yearNo);
						}

					}
				}
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
