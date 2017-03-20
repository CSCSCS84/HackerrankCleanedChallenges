package weekOfCode21;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

public class DemandingMoney {

	static boolean[][] roads;
	static int[] C;
	static int N;
	static int M;
	static long maxAmountGlobal = 0;
	static HashMap<Long, Long> memorySol = new HashMap<>();
	static HashMap<Long, Long> memoryCount = new HashMap<>();
	static HashMap<Long, Long> memoryKey = new HashMap<>();
	static HashSet<Long> solutionsAlreadyCounted = new HashSet<>();
	static HashMap<Long, Long> memory = new HashMap<Long, Long>();
	static double timeSum = 0;

	static long countUsedsolutions = 0;

	public static void main(String[] args) throws FileNotFoundException {
		double time1 = System.currentTimeMillis();
		//
		// String[] testCases = { "501", "00", "101", "102", "151", "201",
		// "251", "252", "253", "254", "301", "341",
		// "342", "344" };
		String[] testCases = { "151" };
		for (int t = 0; t < testCases.length; t++) {
			String testCase = testCases[t];

			String pathOfTestCaseFile = "/home/christoph/Development2/HackerRank2/TestData/DemandingMoney/DemandingMoneyTest"
					+ testCase + ".txt";
			File file = new File(pathOfTestCaseFile);
			Scanner sc = new Scanner(file);
			N = sc.nextInt();
			M = sc.nextInt();
			C = new int[N];
			for (int i = 0; i < N; i++) {
				C[i] = sc.nextInt();
			}
			roads = new boolean[N][N];
			for (int i = 0; i < M; i++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				roads[a - 1][b - 1] = true;
			}

			long sol[] = DemandingMoney.solve();
			double time2 = System.currentTimeMillis();
			System.out.println(testCase + ": " + sol[0] + " " + sol[1]);
			System.out.println((time2 - time1) / 1000);
			System.out.println(timeSum);

		}

	}

	static long[] solve() {
		// status false, if house not robbed, true if house has been robbed
		boolean[] statusHouses = new boolean[N];
		maxAmountGlobal = calcMaxDemandingMoney(statusHouses, -1);
		statusHouses = new boolean[N];
		memorySol = new HashMap<Long, Long>();
		solutionsAlreadyCounted = new HashSet<>();

		memory = new HashMap<Long, Long>();
		solutionsAlreadyCounted = new HashSet<>();
		int numOfOptimal = numOfCombinations(maxAmountGlobal, (byte) 0, new LinkedList<Byte>(), 0);
		long dummy[] = new long[2];
		dummy[0] = maxAmountGlobal;
		dummy[1] = numOfOptimal;
		return dummy;
	}

	private static int numOfCombinations(long amount, byte coinIndex, LinkedList<Byte> robbedHouse, long currentKeyValue) {
		// System.out.println(memory.size());
		// System.out.println(memory.size());
		if (amount < 0) {
			return 0;
		}
		if (coinIndex == N) {
			return 0;
		}
		int sum = 0;
		int curCoin = C[coinIndex];
		int count = 0;
		LinkedList<Byte> copyRobbedHouse0 = new LinkedList<Byte>();

		while (amount >= 0 && count < 2) {
			if (count == 0) {
				copyRobbedHouse0 = copyList(robbedHouse);
			}

			if (count == 1) {
				if (isNeighbor(robbedHouse, coinIndex)) {
					copyRobbedHouse0 = copyList(robbedHouse);

					copyRobbedHouse0.add(coinIndex);
				} else {
					break;
				}
				// hier checken ob das überhaupt geht

			}
			long copyCurrentKey = 0;
			if (amount >= 0) {
				copyCurrentKey = currentKeyValue;

				if (count == 1) {
					copyCurrentKey += Math.pow(2, coinIndex);
				}
				long a = 0;

				if (!memory.containsKey(copyCurrentKey)) {
					a = numOfCombinations(amount, (byte) (coinIndex + 1), copyRobbedHouse0, copyCurrentKey);
					memory.put(copyCurrentKey, a);

				} else {
					a = memory.get(copyCurrentKey);
					countUsedsolutions++;
				}

				sum += a;

			}
			if (amount == 0) {
				if (!solutionsAlreadyCounted.contains(copyCurrentKey)) {
					sum++;
					solutionsAlreadyCounted.add(copyCurrentKey);

					// System.out.println("Optimale Liste gefunden " + sum +
					// "count+" + count);
					// for (Integer e : copyRobbedHouse0) {
					// System.out.print((e + 1) + " ");
					// }
					//
					// System.out.println();
				}
			}
			amount -= curCoin;
			count++;
		}
		return sum;

	}

	private static boolean isNeighbor(LinkedList<Byte> robbed, int index) {
		for (Byte house : robbed) {
			if (roads[house][index] || roads[index][house]) {
				return false;
			}
		}
		return true;
	}

	private static LinkedList<Byte> copyList(LinkedList<Byte> list) {
		LinkedList<Byte> copy = new LinkedList<Byte>();
		for (Byte e : list) {
			copy.add(e);
		}
		return copy;
	}

	private static SubSolution countMaxDemandingMoneyFast(boolean[] statusHouses, int lastRobbed, long sumToReach,
			LinkedList<Long> robbedHouses) {
		long maxAmount = 0;
		long countOptimalSolutions = 0;
		long finalKey = 0;
		for (int i = lastRobbed + 1; i < N; i++) {
			if (!statusHouses[i]) {
				boolean[] copyStatusHouse = copyArraySetNeighborsAlreadyRobbed(statusHouses, i);
				long copyAmount = 0;
				copyAmount += C[i];
				robbedHouses.add((long) i);
				if (copyAmount == sumToReach) {
					long keyOpt = calcKeyForList(robbedHouses);
					if (!solutionsAlreadyCounted.contains(keyOpt)) {
						countOptimalSolutions++;
						solutionsAlreadyCounted.add(keyOpt);
					}
				}

				long key = calcKey(copyStatusHouse, i);
				long partSolAmount = 0;
				long partSolCount = 0;
				SubSolution partSol = new SubSolution();
				if (key != 0) {

					if (memorySol.containsKey(key)) {
						partSolAmount = memorySol.get(key);
						partSolCount = memoryCount.get(key);
					} else {
						partSol = countMaxDemandingMoneyFast(copyStatusHouse, i, sumToReach - C[i], robbedHouses);
						memorySol.put(key, partSol.getMaxAmount());
						memoryCount.put(key, partSol.getCountOptimalSolution());
						memoryKey.put(key, partSol.getKeyStatus());
						partSolAmount = partSol.getMaxAmount();
						partSolCount = partSol.getCountOptimalSolution();
					}

					copyAmount += partSolAmount;

					countOptimalSolutions += partSolCount;

				}
				if (copyAmount == sumToReach) {
					countOptimalSolutions++;
				}
				robbedHouses.removeLast();

				if (copyAmount >= maxAmount) {
					maxAmount = copyAmount;

				}
			}
		}

		SubSolution solution = new SubSolution(maxAmount, countOptimalSolutions, finalKey);
		return solution;
	}

	private static long calcKeyForList(LinkedList<Long> list) {
		long sum = 0;
		for (Long l : list) {
			sum += Math.pow(2, l);
		}
		return sum;
	}

	private static class SubSolution {
		long maxAmount;
		long countOptimalSolution;
		long keyStatus;

		public SubSolution() {

		}

		public SubSolution(long maxAmount, long countOptimalSolution, long keyStatus) {
			super();
			this.maxAmount = maxAmount;
			this.countOptimalSolution = countOptimalSolution;
			this.keyStatus = keyStatus;
		}

		public long getMaxAmount() {
			return maxAmount;
		}

		public void setMaxAmount(long maxAmount) {
			this.maxAmount = maxAmount;
		}

		public long getCountOptimalSolution() {
			return countOptimalSolution;
		}

		public void setCountOptimalSolution(long countOptimalSolution) {
			this.countOptimalSolution = countOptimalSolution;
		}

		public long getKeyStatus() {
			return keyStatus;
		}

		public void setKeyStatus(long keyStatus) {
			this.keyStatus = keyStatus;
		}

	}

	private static long[] countMaxDemandingMoneyCorrectSlow(boolean[] statusHouses, int lastRobbed, long sum) {
		long maxAmount = 0;
		long countOptimalSolutions = 0;
		for (int i = lastRobbed + 1; i < N; i++) {
			if (!statusHouses[i]) {
				boolean[] copyStatusHouse = copyArraySetNeighborsAlreadyRobbed(statusHouses, i);
				long copyAmount = 0;
				copyAmount += C[i];
				if (copyAmount + sum == maxAmountGlobal) {
					countOptimalSolutions++;
				}
				long key = calcKey(copyStatusHouse, i);
				long partSol = 0;
				if (key != 0) {

					long[] solPart = countMaxDemandingMoneyCorrectSlow(copyStatusHouse, i, sum + C[i]);
					partSol = solPart[0];
					countOptimalSolutions += solPart[1];

					copyAmount += partSol;
				}
				if (copyAmount >= maxAmount) {
					maxAmount = copyAmount;
				}
			}
		}
		long solution[] = new long[2];
		solution[0] = maxAmount;
		solution[1] = countOptimalSolutions;
		return solution;
	}

	private static long calcMaxDemandingMoney(boolean[] statusHouses, int lastRobbed) {
		long maxAmount = 0;
		for (int i = lastRobbed + 1; i < N; i++) {
			if (!statusHouses[i]) {
				boolean[] copyStatusHouse = copyArraySetNeighborsAlreadyRobbed(statusHouses, i);
				long copyAmount = 0;
				copyAmount += C[i];
				long key = calcKey(copyStatusHouse, i);
				long partSol = 0;
				if (key != 0) {
					if (memorySol.containsKey(key)) {
						partSol = memorySol.get(key);
					} else {
						partSol = calcMaxDemandingMoney(copyStatusHouse, i);
						memorySol.put(key, partSol);
					}
					copyAmount += partSol;
				}
				if (copyAmount >= maxAmount) {
					maxAmount = copyAmount;
				}
			}
		}

		return maxAmount;
	}

	private static long calcKey(boolean[] statusHouses, int lastRobbed) {
		long key = 0;
		for (int i = lastRobbed; i < statusHouses.length; i++) {
			if (!statusHouses[i]) {
				key += Math.pow(2, i);
			}
		}
		return key;

	}

	private static boolean[] copyArraySetNeighborsAlreadyRobbed(boolean[] arrayToCopy, int robbedHouse) {
		boolean[] copy = new boolean[arrayToCopy.length];
		for (int i = 0; i < copy.length; i++) {
			if (arrayToCopy[i]) {
				copy[i] = true;
			}
			if (i == robbedHouse) {
				copy[i] = true;
			}
			if (roads[robbedHouse][i] || roads[i][robbedHouse]) {
				copy[i] = true;
			}
		}

		// System.out.println(key);
		return copy;
	}
}
