
EW = 0L;
NS = 0L;

WEW = 10;
WNS = 1;

compass = ['N','E','S','W'];

facing = 1;

new File("input.txt").eachLine {line ->
	moveBoat(line);
}

public void moveBoat(String line) {
System.out.println(line);
	long value = Long.valueOf(line.substring(1));
	char direction = line.charAt(0);		

	switch (direction) {
		case 'F':
			NS += (value * WNS);
			EW += (value * WEW);
			break;
		case 'N':
			WNS += value;
			break;
		case 'S':
			WNS -= value;
			break;
		case 'E':
			WEW += value;
			break;
		case 'W':
			WEW -= value;
			break;
		case 'L':
			value = value / 90;
			
			for (int i = 0 ; i < value ; ++i) {
				x = WEW;
				y = WNS;

				WEW = -1 * y;
				WNS = x;
			}

			break;
			
		case 'R':
			value = value / 90;

			for (int i = 0 ; i < value ; ++i) {
				x = WEW;
				y = WNS;

				WEW = y;
				WNS = -1 * x;
			}
			
			break;
	}
System.out.println(WEW+"/"+WNS);
System.out.println(EW+"/"+NS+"="+(Math.abs(EW)+Math.abs(NS)));
}



