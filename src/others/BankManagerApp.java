package others;

import java.io.IOException;
import java.util.ArrayList;

public class BankManagerApp {
	public static void main(String[] args) throws IOException{
		//create new Archive
		TransactionArchive Archive = new TransactionArchive();
		
		//To be implemented UI
		String inputMonth = "July";
		String shortForm = "Jul";
		
		//downloading text from PDF
		String st = ReadPDF.readPDF(inputMonth +".pdf");
		int pages = ExtractTransactions.getPagesToDecrypt(st);
		st = st.substring(st.indexOf("Balance Brought Forward"), st.indexOf("Total")+40);
		
		double depWith=0;
		double tempBalance=0;
		int count=0;
		int count1=0;
		double balanceBF = 0;
		String temp;
		
		for (int j=0;j<pages;j++){
			balanceBF = ExtractTransactions.getBalanceBF(st);
			count = st.indexOf("Balance Brought Forward",count);
			count = st.indexOf("\n",count);
			boolean pageFinish = false;
			
			while(!pageFinish){
				//Date
				temp = st.substring(count+1,count+3);
				String date = temp;
				int dateInt = Integer.valueOf(temp);
				
				//Month
				temp = st.substring(count+4,count+7);
				String month = temp;
				
				//Details
				ArrayList<String> details = new ArrayList<>();
				count = count+8;
				while (true){
					count1 = st.indexOf("\n",count);
					temp = st.substring(count,count1);
					System.out.println(temp);
					if (temp.contains("Interest Earned")){
						details.add(temp);
						break;
					}
					if (temp.contains(shortForm))
						break;
					if(temp.contains("Balance Carried Forward")){
						pageFinish = true;
						break;
					}
					details.add(temp);
					count = count1+1;
				}
				
				//Deposits/WithDrawal + temporary balance
				temp = details.get(details.size()-1);
				String[] parts = temp.split(" ");
				if (parts.length==2){
					tempBalance = removeComma(parts[1]);
				}
				else if (parts.length==4){System.out.println("Tracing");
					tempBalance = removeComma(parts[3]);
					depWith=removeComma(parts[2]);
					pageFinish = true;
				}
				else
					removeComma(parts[0]);
				
				count = count-1;
				BankTransaction t1 = new BankTransaction(date,month,details,depWith,tempBalance);
				t1.printTransaction();
				Archive.addTransaction(t1);
			}
		}
		Archive.printTransactionList();
		
		temp = st.substring(count,count+10);
		
		String s = Archive.outputTransactionList();
		System.out.println(s instanceof String);
		System.out.println(s);
		FileReaderWriter.writeFile("TransactionList.txt", s);
	}
	
	public static double removeComma(String s){
		String[] parts = s.split(",");
		double out=0;
		for (int i=0;i<parts.length;i++)
			out += Double.valueOf(parts[i])*Math.pow(1000, parts.length-i-1);
		return out;
	}
}
