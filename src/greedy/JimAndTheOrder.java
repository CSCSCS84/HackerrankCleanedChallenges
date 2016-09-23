/**
 * https://www.hackerrank.com/challenges/jim-and-the-orders
 * Solution: Sort in increasing order after the sum t_i+d_i
 */
package greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class JimAndTheOrder {

	public static void main(String[] args) {
		
		Order[] elements = readInput();
		Arrays.sort(elements, new OrderComparator());
		for (int i = 0; i < elements.length; i++) {
			System.out.print(elements[i].getIndex() + " ");
		}
	}

	private static Order[] readInput() {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		Order[] orders = new Order[n];
		for (int i = 0; i < n; i++) {
			Order o = new Order(sc.nextInt() + sc.nextInt(), i + 1);
			orders[i] = o;
		}
		sc.close();
		return orders;
	}

	static class OrderComparator implements Comparator<Order> {

		@Override
		public int compare(Order e1, Order e2) {
			if (e1.getEatingTime() < e2.getEatingTime()) {
				return -1;
			} else if (e1.getEatingTime() > e2.getEatingTime()) {
				return 1;
			} else {
				return 0;
			}
		}

	}

	private static class Order {
		int eatingTime;
		int index;

		Order(int a, int index) {
			this.eatingTime = a;
			this.index = index;

		}

		public int getEatingTime() {
			return this.eatingTime;
		}

		public int getIndex() {
			return this.index;
		}
	}
}