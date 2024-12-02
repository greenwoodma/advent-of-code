
Map<String,Set<String>> allergens = new HashMap<String,Set<String>>();
Map<String,Long> ingredients = new HashMap<String,Long>();

new File("input.txt").eachLine {line ->
	items = line;
	contains = null;

	if (line.indexOf("(")) {
		items = line.substring(0,line.indexOf("(")).trim();
		contains = line.substring(line.indexOf("(")+10,line.length()-1).trim();
	}

	items = new HashSet<String>(Arrays.asList(items.split("\\s+")));

	for (item in items) {
		ingredients.put(item, ingredients.getOrDefault(item,0)+1);
	}

	if (contains != null) {
		contains = Arrays.asList(contains.split(",\\s+"));

		for (allergen in contains) {
			Set<String> possible = allergens.get(allergen);

			if (possible == null) {
				possible = new HashSet<String>(items);
			}
			else {
				possible.retainAll(items);
			}

			allergens.put(allergen, possible);
		}
	}
}

System.out.println(allergens);


while (true) {
	int count = 0;

	for (String allergen : allergens.keySet()) {
		Set<String> possibles = allergens.get(allergen);

		if (possibles.size() == 1) {
			++count;
			for (String a : allergens.keySet()) {
				if (!a.equals(allergen)) {
					allergens.get(a).removeAll(possibles);
				}
			}
		}
	}

	System.out.println(allergens);

	if (count == allergens.size()) break;

}

System.out.println(ingredients);

for (Map.Entry<String,Set<String>> a : allergens.entrySet()) {
	ingredients.remove(a.getValue().iterator().next());
}

System.out.println(ingredients);

long count = 0;

for (Map.Entry<String,Long> i : ingredients.entrySet()) {
	count += i.getValue();
}

System.out.println(count);

