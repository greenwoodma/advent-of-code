import java.io.*;

List<Long> player1 = new ArrayList<Long>();
List<Long> player2 = new ArrayList<Long>();

BufferedReader fin = new BufferedReader(new FileReader("input.txt"));

fin.readLine();

String line = fin.readLine();

while (!"".equals(line)) {
	player1.add(Long.valueOf(line));
	line = fin.readLine();
}

fin.readLine();

line = fin.readLine();
while (line != null && !"".equals(line)) {
	player2.add(Long.valueOf(line));
	line = fin.readLine();
}

System.out.println(player1);
System.out.println(player2);

play(player1,player2);



public int play(List<Long> player1, List<Long> player2) {
	Set<String> previous = new HashSet<String>();

	while (player1.size() > 0 && player2.size() > 0) {
		// seen these cards before so player 1 wins
		if (!previous.add(player1.toString()+player2.toString())) return 1;

		long card1 = player1.head();
		player1 = player1.tail();

		long card2 = player2.head();
		player2 = player2.tail();

		if (player1.size() >= card1 && player2.size() >= card2) {
			List<Long> p1 = new ArrayList<Long>(player1.subList(0,(int)card1));
			List<Long> p2 = new ArrayList<Long>(player2.subList(0,(int)card2));

			if (play(p1,p2) == 1) {
				player1.add(card1);
				player1.add(card2);
			}
			else {
				player2.add(card2);
				player2.add(card1);
			}

		}
		else if (card1 > card2) {
			player1.add(card1);
			player1.add(card2);
		}
		else if (card2 > card1) {
			player2.add(card2);
			player2.add(card1);
		}
	}

	System.out.println(player1);
	System.out.println(player2);


	winner = player1.size() > 0 ? player1 : player2;

	long total = 0;

	for (int i = 0 ; i < winner.size() ; ++i) {
		total = total + (winner.get(i) * (winner.size()-i));
	}

	System.out.println(total);


	return player1.size() > 0 ? 1 : 2;
}

