/**
 * https://www.hackerrank.com/challenges/sherlock-and-valid-string
 */

package strings;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class SherlockAndValidStrings {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String line = sc.nextLine();
		sc.close();
		String solution = solve(line);
		System.out.println(solution);
	}

	private static String solve(String line) {
		HashMap<Integer, Integer> characterMap = countCharacters(line);

		Collection<Integer> col = characterMap.values();
		Iterator<Integer> it = col.iterator();
		int value1 = it.next();
		int countvalue1 = 1;
		int countvalue2 = 0;

		int value2 = 0;
		while (it.hasNext()) {
			int value3 = it.next();
			if (value3 != value1) {
				if (value2 == 0) {
					value2 = value3;
					countvalue2++;
				} else if (value3 != value2) {
					return "NO";
				} else {
					countvalue2++;
				}
			} else {
				countvalue1++;
			}
		}
		if (countvalue1 > 1 && countvalue2 > 1) {
			return "YES";
		}
		return "YES";
	}

	private static HashMap<Integer, Integer> countCharacters(String line) {
		HashMap<Integer, Integer> characterMap = new HashMap<Integer, Integer>();
		for (int i = 0; i < line.length(); i++) {
			char character = line.charAt(i);
			if (!characterMap.containsKey(Character.getNumericValue(character))) {
				characterMap.put(Character.getNumericValue(character), 1);
			} else {
				int value = characterMap.get(Character.getNumericValue(character));
				characterMap.put(Character.getNumericValue(character), value + 1);
			}
		}
		return characterMap;
	}
}