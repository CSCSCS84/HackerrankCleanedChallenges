package weekOfCode21;

public class CreateInPutForLetterN {
	
	public static void main(String[] args) {
		int N=8;
		String str=""+N+"\n";
		
		for(int i=0;i<N;i++){
			double x=Math.random()*1000;
			int a=(int) Math.floor(x);
			double y=Math.random()*1000;
			int b=(int) Math.floor(y);
			str+=a+ " "+b+"\n";
		}
		System.out.println(str);
	}

}
