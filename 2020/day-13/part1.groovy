import java.io.*;

BufferedReader fin = new BufferedReader(new FileReader("input.txt"));

long startTime = Long.valueOf(fin.readLine());

def buses = []

for (String bus : fin.readLine().split(",")) {
	if (!"x".equals(bus))
		buses.add(Long.valueOf(bus));
}

long checkTime = startTime;

while (true) {
	for (long bus : buses) {
		if (checkTime % bus == 0) {
			System.out.println(bus);
			System.out.println(checkTime);

			System.out.println(bus * (checkTime-startTime));
			return;
		}
	}

	++checkTime;
}
