package others;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class BankTransaction {
	private GregorianCalendar date;
	private ArrayList<String> details;
	private boolean deposit;
	private double amount;
	private double tempBalance;
	
	public BankTransaction(GregorianCalendar date, ArrayList<String> details, boolean deposit, double wd, double tempBalance){
		this.date = date;
		this.details = details;
		this.deposit = deposit;
		this.amount = wd;
		this.tempBalance = tempBalance;
	}
	
	public void printTransaction(){
		System.out.println(date);
		System.out.println(details);
		System.out.println(deposit);
		System.out.println(amount);
		System.out.println(tempBalance);
	}
}
