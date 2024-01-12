package org.example;
import java.io.IOException;

import org.example.data.CPU;
import org.example.data.Computer;
import org.example.data.Disk;
import org.example.data.RAM;
import org.example.programs.Programs;

public class Main {

	public static void main(String[] args) throws Exception {

		  try {
	            CPU cpu = CPU.getCPUinfo();
	            RAM ram = RAM.getRAMinfo();
	            Disk disk = Disk.getDiskInfo();
	            Computer myComputer = new Computer(disk, cpu, ram);

	            System.out.println("CPU Frequency: " + myComputer.getCpu().getFrequency() + " GHz, Cores: " + myComputer.getCpu().getCores());
	            System.out.println("RAM Memory: " + myComputer.getRam().getMemory() + " GB");
	            System.out.println("Disk Free Space: " + myComputer.getDisk().getFree() + " GB, Used Space: " + myComputer.getDisk().getUsed() + " GB, Total Space: " + myComputer.getDisk().getTotal()+ " GB");
			  Programs[] samplePrograms = Programs.createSamplePrograms();

			  for (Programs program : samplePrograms) {
				  System.out.println(myComputer.canRunProgram(program));
			  }

		  } catch (IOException | InterruptedException e) {
			  e.printStackTrace();
		  }
		}
}


/*
 * 
*/
