
def list = []
def counts = new HashMap<Long,Long>();

new File("input.txt").eachLine { line ->
    data = line.split("\s+");
    list.add(Long.valueOf(data[0]));
    
    long l2 = Long.valueOf(data[1])
    counts.put(l2, 1L+counts.getOrDefault(l2,0))
}

long similarity = 0;

for (Long num : list) {
	similarity += num * counts.getOrDefault(num,0)
}

System.out.println(similarity)
