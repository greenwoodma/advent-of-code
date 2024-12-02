Long previous = null;

long answer = 0;

new File("input.txt").eachLine { line ->
	Long current = Long.valueOf(line);

    if (previous != null && current > previous) ++answer
    
    previous = current;
}

System.out.println(answer);
