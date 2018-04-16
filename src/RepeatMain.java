import java.util.ArrayList;
import java.util.Scanner;

public class RepeatMain {
	static ArrayList<Integer> digits;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int date, month, year, level=1;
		digits = new ArrayList<>();
		System.out.println("enter date: ");
		date = in.nextInt();
		System.out.println("enter month: ");
		month = in.nextInt();
		System.out.println("enter year: ");
		year = in.nextInt();
		date = findSum(date, level);
		month = findSum(month, level);
		year = findSum(year, level);
	//	System.out.println("date: "+date+" mont: "+month+" year: "+year);
		digits.add(date);
		digits.add(month);
		digits.add(year);
		digits.add(findSum(date+month+year,level+1));
		System.out.println("Missing no:-");
		for(int i=1; i<10; i++) {
			if(!digits.contains(i)) {
				System.out.print(i+" ");
			}
		}
	}

	private static int findSum(int digit, int level) {
		// TODO Auto-generated method stub
		int sum = 0;
		if(digit<10) {
			return digit;
		}
		else {
			while(digit>0) {
				sum+=(digit%10);
				if(level==1) {
					digits.add(digit%10);
				}
				digit/=10;
			}
			if(sum>9) {
				level++;
				sum = findSum(sum, level);
			}else {
				return sum;
			}
				
		}
	
		return sum;
	}
	
}
