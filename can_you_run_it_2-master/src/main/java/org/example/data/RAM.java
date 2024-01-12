package org.example.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RAM {
	
	private long memory;
	
	
	public long getMemory() {
		return memory;
	}
	public void setMemory(long memory) {
		this.memory = memory;
	}
	public RAM(long memory) {
		this.memory = memory;
	}
	public static RAM getRAMinfo() throws IOException, InterruptedException {
		Process process = Runtime.getRuntime().exec("wmic memorychip get capacity");
		process.waitFor();
		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String line;
		long totalMemoryBytes = 0;
		reader.readLine();
		while ((line = reader.readLine()) != null) {
			try {
				long capacityBytes = Long.parseLong(line.trim());
				totalMemoryBytes += capacityBytes;

			} catch (NumberFormatException e) {
			}
		}
		long totalMemoryGB = totalMemoryBytes / (1024 * 1024 * 1024);
		RAM ram = new RAM(totalMemoryGB);
		System.out.println("Total Memory: " + totalMemoryGB + " GB");
		return ram;
	}


}
