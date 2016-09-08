/**
 * https://www.hackerrank.com/challenges/cipher
 * Challenge Description: Decode an encoded message
 */

package bitmanipulation;

import java.util.Scanner;

public class Cipher {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int K = in.nextInt();
		String str = in.next();
		in.close();
		StringBuffer solution = decodedMessage(N, K, str);
		System.out.println(solution);
	}

	private static StringBuffer decodedMessage(int N, int K, String encodedMessage) {
		StringBuffer decodedMessage = new StringBuffer();
		decodeFirstPart(N, K, encodedMessage,decodedMessage);
		decodeSecondPart(N, K, encodedMessage, decodedMessage);
		return decodedMessage;
	}

	private static void decodeFirstPart(int N, int K, String encodedMessage,StringBuffer decodedMessage) {
		decodedMessage.append(encodedMessage.charAt(0));
		
		for (int i = 1; i < K && i < N; i++) {
			if (encodedMessage.charAt(i) == encodedMessage.charAt(i - 1)) {
				decodedMessage.append(0);
			} else {
				decodedMessage.append(1);
			}
		}
	}

	private static void decodeSecondPart(int N, int K, String encodedMessage, StringBuffer decodedMessage) {
		if (K < N) {
			for (int i = K; i < N; i++) {
				if (encodedMessage.charAt(i) == encodedMessage.charAt(i - 1)) {
					decodedMessage.append(decodedMessage.charAt(i - K));
				} else {
					if (decodedMessage.charAt(i - K) == '0') {
						decodedMessage.append(1);
					} else {
						decodedMessage.append(0);
					}
				}
			}
		}
	}
}