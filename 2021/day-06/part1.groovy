// a map that stores how many fish have a given time value
Map dayN = new HashMap<Long,Long>();

// split the input into separate entries and put them into the map
(new File("input.txt")).collect()[0].split(",").each { f ->
	f = Long.valueOf(f);
	dayN.put(f, dayN.getOrDefault(f,0L)+1);
}

// show the initial state
System.out.println(dayN);

for (int d = 1 ; d <= 80 ; ++d) {

	// a new map to hold day N+1
	Map dayNP1 = new HashMap<Long,Long>();
	
	for (long age = 1 ; age <= 8 ; ++age) {
		// for each age above 0, make them a day older
		dayNP1.put(age-1, dayN.getOrDefault(age,0L));
	}
	
	// for each fish with a timer 0 add a new one with timer 8
	dayNP1.put(8L,dayN.getOrDefault(0L,0L));

	// for each fish with a timer 0 move it back to being a 6
	// (add it to the 6's we have from the 7's aging)
	dayNP1.put(6L,dayN.getOrDefault(0L,0L) + dayNP1.getOrDefault(6L,0L));
	
	// show the new days state
	System.out.println(dayNP1.values().sum()+": "+dayNP1);
	
	// day N is now N+1 ready for next loop
	dayN = dayNP1;
}
