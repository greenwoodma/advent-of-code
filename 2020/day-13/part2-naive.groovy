import java.io.*;
import java.math.BigInteger;

BufferedReader fin = new BufferedReader(new FileReader("sample.txt"));

long startTime = Long.valueOf(fin.readLine());

def buses = []

long max = 0;
long offset = 0;

for (String bus : fin.readLine().split(",")) {
	if ("x".equals(bus))
		buses.add(bus);
	else {
		long id = Long.valueOf(bus);
		buses.add(BigInteger.valueOf(id));
		if (id > max) {
			max = id;
			offset = buses.size()-1;
		}
	}
}

System.out.println(buses);

System.out.println(max);
System.out.println(offset);

BigInteger t = BigInteger.valueOf(max).minus(BigInteger.valueOf(offset));

BigInteger counter = BigInteger.ZERO;

while (true) {
	System.out.println(t);
	boolean valid = true;	
	for (int i = 0 ; i < buses.size() ; ++i) {

		if (!buses.get(i).equals("x")) {
			//System.out.println("\t"+t.plus(BigInteger.valueOf(i))+"--"+buses.get(i));
			valid = valid && t.plus(BigInteger.valueOf(i)).mod(buses.get(i)).equals(BigInteger.ZERO);
		}
	}

	if (valid) {
		System.out.println("*** " + t);
		System.out.println(counter);
		return;
	}

	t = t.add(BigInteger.valueOf(max));

	counter = counter.add(BigInteger.ONE);
}

