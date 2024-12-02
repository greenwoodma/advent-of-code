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


BigInteger t = buses.get(0);
BigInteger increment = buses.get(0);

// once we know when bus x and y arrive at the right time, gap to next must be a multiple of x*y
// for example 3,5 first align at t=9 (9/3 and (9+1)/5). They next align at t=24 which is
// 15 intervals later (i.e. 3*5) and that keeps going 39, 54....

for (int i = 1 ; i < buses.size ; ++i) {

	if (!"x".equals(buses.get(i))) {
		while (!t.add(BigInteger.valueOf(i)).mod(BigInteger.valueOf(buses.get(i))).equals(BigInteger.ZERO)) {
			t = t.add(increment);
		}

		increment = increment.multiply(BigInteger.valueOf(BigInteger.valueOf(buses.get(i))));
	}	
}

System.out.println(t);
