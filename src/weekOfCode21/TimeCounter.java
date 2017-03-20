package weekOfCode21;

import java.util.LinkedList;

public class TimeCounter {
	public static void main(String[] args) {
		double time1=System.currentTimeMillis();
		
		int M=2300;
		
		for(int i=0;i<M;i++){
			System.out.println(i);
			for(int j=0;j<M;j++){
				TimeCounter.randomList(M);
			}
		}
		
		double time2=System.currentTimeMillis();
		System.out.println((time2-time1)/1000);
	}
	static LinkedList<Double> randomList(int M){
		LinkedList<Double> list=new LinkedList<Double>();
		for(int i=0;i<50;i++){
			double a=Math.random();
			list.add(a);
		}
		return list;
	}

}
