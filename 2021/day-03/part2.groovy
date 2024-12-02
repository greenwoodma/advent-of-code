
def list = new File("input.txt").collect();

def oxygen = new ArrayList<String>(list);

int index = 0;
while (oxygen.size() > 1) {
	oxygen = filter(oxygen,index,mostCommon(oxygen,index,'1','0'));
	++index;
}



def co2 = new ArrayList<String>(list);
index = 0;
while (co2.size() > 1) {
	co2 = c02 = filter(co2,index,mostCommon(co2,index,'0','1'));
	++index;
}

System.out.println(oxygen);
System.out.println(c02);

int answer = Integer.parseInt(oxygen[0],2)*Integer.parseInt(co2[0],2);

System.out.println(answer);

public char mostCommon(List list, int offset, String first, String second) {

	int count = 0;

	list.each {
		if (it.charAt(offset).equals((char)'1')) ++count;
	}
	
	if (count > list.size()/2) return first;
	
	if (count < list.size()/2) return second;
	
	return first;
}

public List filter(List list, int offset, char symbol) {

	List result = new ArrayList<String>();
	
	list.each {
		if (it.charAt(offset).equals(symbol))
			result.add(it);
	}
	
	return result;

}
