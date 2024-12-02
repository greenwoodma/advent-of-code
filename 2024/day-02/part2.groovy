long safe = 0;

new File("input.txt").eachLine { line ->
    data = line.split("\s+")*.toLong()
    
    if (isSafe(data))
    		++safe
    	else {
 		for (int i = 0 ; i < data.size() ; ++i) {
 			missing = [];
 			missing.addAll(data)
 			missing.remove(i)
 			
 			if (isSafe(missing)) {
 				++safe;
 				break;
 			}
 		}
    	}
}

System.out.println(safe);

public boolean isSafe(data) {
	boolean notsafe = false;
    
    boolean asc = (data[1] - data[0]) > 0
    
    for (int i = 1 ; i < data.size() ; ++i) {
    		long diff = Math.abs(data[i-1]-data[i]);
    		
    		if (diff < 1 || diff > 3) return false
    		
    		if ((data[i] - data[i-1]) > 0 && !asc) return false
    		if ((data[i] - data[i-1]) < 0 && asc) return false 
    }
    
    return true
}
