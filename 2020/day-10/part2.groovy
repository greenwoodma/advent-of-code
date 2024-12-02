import java.util.*;

def sequence = [0L]

long diff1 = 0;
long diff3 = 1;

new File("input.txt").eachLine {line ->
    sequence << Long.valueOf(line)
}

sequence = sequence.sort();

sequence << sequence.get(sequence.size()-1)+3;

System.out.println(sequence);

System.out.println(countTerminals(new HashMap(), sequence));


private countTerminals(Map counts, List sequence) {
	if (sequence.size() == 1) return 1;

	long current = 0;

	long count = 0;

	long head = sequence.head();


	if (counts.containsKey(head)) return counts.get(head);

System.out.println(head);

	List tail = sequence.tail();

	for (int i = 0 ; i < tail.size() ; ++i) {
		long value = tail.get(i);

		if ((value-head) > 3) break;

		current += countTerminals(counts, tail.subList(i, tail.size()));
	}

	counts.put(head,current);

	return current;	
}
