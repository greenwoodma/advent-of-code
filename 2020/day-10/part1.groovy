import java.util.*;

def sequence = [0L]

long diff1 = 0;
long diff3 = 1;

new File("input.txt").eachLine {line ->
    sequence << Long.valueOf(line)
}

sequence = sequence.sort();

System.out.println(sequence);

for (int i = 1 ; i < sequence.size() ; ++i) {
	long diff = sequence.get(i)-sequence.get(i-1);

	if (diff == 1) ++diff1;
	if (diff == 3) ++diff3;
}

System.out.println(diff1+"*"+diff3+"="+(diff1*diff3));
