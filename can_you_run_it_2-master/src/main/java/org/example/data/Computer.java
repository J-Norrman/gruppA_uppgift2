package org.example.data;

import org.example.data.CPU;
import org.example.data.Disk;
import org.example.data.RAM;
import org.example.programs.Programs;

public class Computer {
    private Disk disk;
    private CPU cpu;
    private RAM ram;

    public Computer(Disk disk, CPU cpu, RAM ram) {
        this.cpu = cpu;
        this.disk = disk;
        this.ram = ram;
    }

    public CPU getCpu() {
        return cpu;
    }

    public RAM getRam() {
        return ram;
    }

    public Disk getDisk() {
        return disk;
    }
    public String canRunProgram(Programs program) {
        CPU programCpu = program.getCpu();
        RAM programRam = program.getRam();
        Disk programDisk = program.getDisk();

        boolean cpuCheck = this.getCpu().getFrequency() >= programCpu.getFrequency() &&
                this.getCpu().getCores() >= programCpu.getCores();

        boolean ramCheck = this.getRam().getMemory() >= programRam.getMemory();

        boolean diskCheck = this.getDisk().getFree() >= programDisk.getUsed(); // Adjust as needed

        if (cpuCheck && ramCheck && diskCheck) {
            return "You can run " + program.getName();
        } else {
            StringBuilder message = new StringBuilder("You cannot run " + program.getName() + " because:");

            if (!cpuCheck) {
                message.append("\n- Insufficient CPU power");
            }

            if (!ramCheck) {
                message.append("\n- Insufficient RAM");
            }

            if (!diskCheck) {
                message.append("\n- Insufficient Disk space");
            }

            return message.toString();
        }
    }
}

