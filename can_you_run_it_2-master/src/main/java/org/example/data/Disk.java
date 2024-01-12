package org.example.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Disk {

	private long total;
	private long used;
	private long free;

	public Disk(long total, long used, long free) {
		this.total = total;
		this.used = used;
		this.free = free;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getUsed() {
		return used;
	}

	public void setUsed(long used) {
		this.used = used;
	}

	public long getFree() {
		return free;
	}

	public void setFree(long free) {
		this.free = free;
	}

//	public int getTotal() {
//		return total;
//	}
//
//
//	public void setTotal(int total) {
//		this.total = total;
//	}
//
//
//	public int getUsed() {
//		return used;
//	}
//
//
//	public void setUsed(int used) {
//		this.used = used;
//	}
//
//
//	public int getFree() {
//		return free;
//	}
//
//
//	public void setFree(int free) {
//		this.free = free;
//	}

	public static Disk getDiskInfo() throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec("wmic logicaldisk get deviceid, freespace, size, volumename");

        BufferedReader reader = null;
        try {
            process.waitFor();

            reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            long free = 0, used = 0, total = 0;

            // Skip the header line
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                // Parse each line and update the variables accordingly
                String[] parts = line.trim().split("\\s+");
                if (parts.length == 4) {
                    free += Long.parseLong(parts[1]);
                    total += Long.parseLong(parts[2]);
                }
            }

            // Convert bytes to gigabytes
            free /= (1024 * 1024 * 1024);

            total /= (1024 * 1024 * 1024);
			used = total - free;

            return new Disk(total, used, free);
        } finally {
            // Close the BufferedReader in a finally block to ensure proper cleanup
            if (reader != null) {
                reader.close();
            }
        }
    }
}