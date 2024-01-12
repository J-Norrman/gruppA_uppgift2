package org.example.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CPU {
	
	private double frequency;
	private int cores;
	
	public CPU(double frequency, int cores) {
		this.frequency = frequency;
		this.cores = cores;
	}
	
	
	
	public double getFrequency() {
		return frequency;
	}



	public void setFrequency(double frequency) {
		this.frequency = frequency;
	}



	public int getCores() {
		return cores;
	}



	public void setCores(int cores) {
		this.cores = cores;
	}



	public static CPU getCPUinfo() throws IOException, InterruptedException {
		Process process = Runtime.getRuntime().exec("wmic cpu get numberofcores, maxclockspeed");
		process.waitFor();
		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String line;
		int cores = 0;
		double maxClockSpeed = 0.0;
		reader.readLine();
		while ((line = reader.readLine()) != null) {
			String[] tokens = line.split("\\s+");
			if (tokens.length >= 2) {
				cores = Integer.parseInt(tokens[1]);
				maxClockSpeed = Double.parseDouble(tokens[0])/1000;
			}
		}

		return new CPU(maxClockSpeed, cores);
	}


}
