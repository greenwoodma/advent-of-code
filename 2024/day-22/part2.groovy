
input = (new File("input.txt")).readLines()*.toBigInteger()


BigInteger total = 0;

sequenceTotals = new HashMap<Tuple4,Long>()

for (secret : input) {
	buyer = [secret%10]
	for (int i = 0; i < 2000 ; ++i) {
		secret = next(secret)
		buyer.add(secret%10)
	}

	Set seen = new HashSet<Tuple4>()
	for (int i = 0 ; i < (buyer.size() - 4); ++i) {
		(a, b, c, d, e) = buyer[i..(i+4)]
		Tuple4 sequence = new Tuple4(b-a, c-b, d-c, e-d)
		if (seen.add(sequence)) {
			sequenceTotals.put(sequence,sequenceTotals.getOrDefault(sequence,0)+e)
		}
	}
}

//println sequenceTotals

println sequenceTotals.values().max()

def next(secret) {
	secret = prune(mix(secret,secret*64))
	
	secret = prune(mix(secret,secret.intdiv(32)))
	
	secret = prune(mix(secret, secret*2048))
	
	return secret
}


def mix(secret, value) {
	return secret ^ value
}

def prune(secret) {
	return secret % 16777216
}
