/**
 * Writer for Testcases for SashaAndSwap Challenge. Not cleaned.
 */
package weekofcode23;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class SashaAndSwapWriter {
	static int numberOfNodes = 25000;

	public static void main(String[] args) {
		Writer writer = null;
		try {
			String currentPath = System.getProperty("user.dir");
			String filename = currentPath + "" + "/TestData/SashaAndSwap/NeueInstanzen/SashaAndSwapTestDataOwn"
					+ numberOfNodes + ".txt";
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename)));
			String input = "";
			input = "" + numberOfNodes;
			input += "\n";
			for (int i = 1; i <= numberOfNodes; i++) {
				input += "" + i + " ";
			}

			writer.write(input);
		} catch (IOException ex) {
			// report
		} finally {
			try {
				writer.close();
			} catch (Exception ex) {/* ignore */
			}
		}
	}

}
