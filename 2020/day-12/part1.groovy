
EW = 0L;
NS = 0L;

compass = ['N','E','S','W'];

facing = 1;

new File("input.txt").eachLine {line ->
	moveBoat(line);
}

public void moveBoat(String line) {
System.out.println(line);
	long value = Long.valueOf(line.substring(1));
	char direction = line.charAt(0);

	if (direction == 'F')
		direction = compass[(int)facing];
		

	switch (direction) {
		case 'N':
			NS += value;
			break;
		case 'S':
			NS -= value;
			break;
		case 'E':
			EW += value;
			break;
		case 'W':
			EW -= value;
			break;
		case 'R':
			value = value / 90;
			System.out.println(value);
			value = value % 4;
			System.out.println(value);
			facing = facing + value;
			System.out.println(facing);
			if (facing >= 4) facing = facing - 4;

			System.out.println(facing);
			break;
		case 'L':
			value = value / 90;
			value = value % 4;

			facing = facing - value;
			if (facing < 0) facing = facing + 4;

			System.out.println(facing);
			break;

	}
System.out.println(compass[(int)facing]+"/"+EW+"/"+NS+"="+(Math.abs(EW)+Math.abs(NS)));
}



