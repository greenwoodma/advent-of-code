long count = 0;

def summed = null;

new File("input.txt").eachLine { line ->

	current = line.collect { it as int }
	
	++count;
	
	if (summed == null) {
		summed = current
	}
	else {
		summed = [summed, current].transpose()*.sum()
	}
}

System.out.println(summed);

def gamma = (summed.collect { it > count/2 ? 1 : 0 }).join("");
def epsilon = (summed.collect { it < count/2 ? 1 : 0 }).join("");

def answer = Integer.parseInt(gamma,2) * Integer.parseInt(epsilon,2);

System.out.println(answer);
