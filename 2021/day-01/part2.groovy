def list = new File("input.txt").collect {Long.valueOf(it)}

Long previous = null;

long answer = 0;

for (i = 0 ; i < list.size()-2 ; ++i) {
	Long current = list[i]+list[i+1]+list[i+2];
	
	if (previous != null && current > previous) ++answer
    
    previous = current;
}

System.out.println(answer);
