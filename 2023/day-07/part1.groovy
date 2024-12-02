long total = 0;

List game = new ArrayList();

new File("input.txt").eachLine { line ->
	game.add(new Hand(line));
}

game =game.sort() 

//System.out.println(game.sort());


for (int h = 0 ; h < game.size() ; ++h) {
	Hand hand = game.get(h);
	total += hand.bid*(h+1)
	System.out.println(hand.bid + " * "+(h+1));
}

System.out.println(total)

class Hand implements Comparable {

	static numbers = "TJQKA";
	
	String cards;
	Long bid
	
	List values = new ArrayList();
	
	Long strength = 0;
	
	public Hand(String desc) {
		def data = desc.split("\\s+");
		cards = data[0];
		bid = Long.valueOf(data[1]);
		
		Map counts = new HashMap();
		for (char c : cards.toCharArray()) {
			counts.put(c,counts.getOrDefault(c,0)+1)
			
			if (Character.isDigit(c)) {
				values.add(Integer.valueOf(String.valueOf(c)));
			} else {
				values.add(10+numbers.indexOf(String.valueOf(c)));
			}
		}
		
		if (counts.size()==1) {
			// five of a kind
			strength = 7;
		} else if (counts.size()==2) {
			if (counts.containsValue(4)) {
				//four of a kind
				strength = 6;
			} else {
				// full house
				strength = 5;
			}
		} else if (counts.size() == 3) {
			if (counts.containsValue(3)) {
				// three of a kind
				strength = 4;
			} else {
				// two pair
				strength = 3;
			}
		} else if (counts.size() == 4) {
			// one pair
			strength = 2;
		} else if (counts.size() == 5) {
			// High Card
			strength = 1;
		} else {
			System.out.println("No ides on "+desc);
		}
	}
	
	public String toString() {
		return cards+" - " +strength+" - "+values;
	}
	
	public int compareTo(Object other) {
		Hand otherHand = (Hand)other;
	
		int val = strength.compareTo(otherHand.strength);
		
		if (val != 0) return val;
		
		for (int c = 0 ; c < values.size() ; ++c) {
			val = values.get(c).compareTo(other.values.get(c));
			
			if (val != 0) return val;
		}
		
		return 0;
	}
}
