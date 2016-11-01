package others;

public class ExtractTransactions {
	private static double depWith=0;
	private static double tempBalance=0;
	private static int count=0;
	private static int count1=0;
	private static String temp;
	
	public static int getPagesToDecrypt(String st){
		int count = st.indexOf("Page")+10;
		String temp = st.substring(count, count+1);
		return Integer.valueOf(temp)-3;
	}
	
	public static double getBalanceBF(String st){
		//BBF
		count=st.indexOf("Balance Brought Forward",count)+"Balance Brought Forward ".length();
		count1 = st.indexOf("\n",count);
		temp = st.substring(count,count1);
		//System.out.println(temp);
		return removeComma(temp);
		//count=count1;
		//System.out.println(balanceBF);
	}
	
	
	public static double removeComma(String s){
		String[] parts = s.split(",");
		double out=0;
		for (int i=0;i<parts.length;i++)
			out += Double.valueOf(parts[i])*Math.pow(1000, parts.length-i-1);
		return out;
	}
}
