
def list1 = []
def list2 = []

new File("input.txt").eachLine { line ->
    data = line.split("\s+");
    list1.add(Long.valueOf(data[0]));
    list2.add(Long.valueOf(data[1]));
}

list1 = list1.sort();
list2 = list2.sort();

long distance = 0;

for (int i = 0 ; i < list1.size() ; ++i) {
	distance += Math.abs(list1[i]-list2[i])
}

System.out.println(distance)
