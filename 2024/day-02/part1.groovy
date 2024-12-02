long safe = 0;

new File("input.txt").eachLine { line ->
    data = line.split("\s+")*.toLong()
    
    boolean notsafe = false;
    
    boolean asc = (data[1] - data[0]) > 0
    
    for (int i = 1 ; i < data.size() ; ++i) {
    		long diff = Math.abs(data[i-1]-data[i]);
    		
    		if (diff < 1 || diff > 3) notsafe = true
    		
    		if ((data[i] - data[i-1]) > 0 && !asc) notsafe = true
    		if ((data[i] - data[i-1]) < 0 && asc) notsafe = true 
    }
    
    if (!notsafe) {
    		++safe
    		//System.out.println(data)
    	}
}

System.out.println(safe);

