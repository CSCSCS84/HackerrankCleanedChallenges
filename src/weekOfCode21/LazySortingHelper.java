package weekOfCode21;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LazySortingHelper {
	public static void main(String[] args) {

		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);

		int numOfTries = 1000;
		int EV=0;
		for (int i = 0; i < numOfTries; i++) {
			Collections.shuffle(list);
			if(LazySortingHelper.isSorted(list)){
				EV++;
			}
		}
		System.out.println((EV));
		
		//reihenrechner
//		double sum=0;
//		double p=(1.0)/6.0;
//		for(int i=1;i<1000;i++){
//			sum+=i*p*Math.pow(1-p, i-1);
//		}
//		System.out.println(sum);
		long dsaf=1543214444444L;
	      BigDecimal a=BigDecimal.valueOf(dsaf);
	        BigDecimal b=BigDecimal.valueOf(1);
	        BigDecimal sol=a.divide(b,6 , RoundingMode.HALF_UP);
	        System.out.println(sol.toPlainString());

	}
	static boolean isSorted(List<Integer> list){
		
		for(int i=0;i<list.size()-1;i++){
			if(list.get(i)>list.get(i+1)){
				return false;
			}
		}
		return true;
	}
}
