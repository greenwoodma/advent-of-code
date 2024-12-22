
input = (new File("input.txt")).readLines()*.toBigInteger()

BigInteger total = 0;

for (secret : input) {
	for (int i = 0; i < 2000 ; ++i) {
		secret = next(secret)
	}
	total += secret
}




println total

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
