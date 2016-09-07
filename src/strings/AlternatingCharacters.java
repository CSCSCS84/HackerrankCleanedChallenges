/**
 * https://www.hackerrank.com/challenges/alternating-characters/submissions/code/20643230
 */

package strings;

import java.util.Scanner;

public class AlternatingCharacters {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int T=sc.nextInt();
        sc.nextLine();
       
        for(int t=1;t<=T;t++){
            String inputLine=sc.nextLine();
            int solution = numOfRequiredDeletions(inputLine);
            System.out.println(solution);
        }
        
        sc.close();
    }

	private static int numOfRequiredDeletions(String line) {
		int count=0;
		
		for(int i=0;i<line.length()-1;i++){
		    if(line.charAt(i) ==(line.charAt(i+1))){
		        count++;
		    }
		}
		
		return count;
	}
}