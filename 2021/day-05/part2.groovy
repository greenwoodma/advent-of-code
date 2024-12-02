Map grid = new HashMap();

new File("input.txt").eachLine { line ->
	int[] data = line.split("[^0-9]+").collect { it as int };
	
	if (data[0] == data[2] || data[1] == data[3]){
		//System.out.println("\n\n"+data);
		
		for (int x = Math.min(data[0],data[2]) ; x <= Math.max(data[0],data[2]) ; ++x) {
			for (int y = Math.min(data[1],data[3]); y <= Math.max(data[1],data[3]) ; ++y) {
				//System.out.println(x+","+y);
				grid.put(x+","+y,grid.getOrDefault(x+","+y,0)+1);
			}
		} 
	}
	else {
		//System.out.println("\n\n"+data);
		
		dX = data[0] < data[2] ? 1 : -1;
		dY = data[1] < data[3] ? 1 : -1;
		
		
		width = (dX > 0 ? data[2]-data[0] : data[0]-data[2]);
		
		//System.out.println(width);
		
		for (int i = 0 ; i <= width ; ++i){
			x = data[0] + i*dX;
			y = data[1] + i*dY;
			//System.out.println(x+","+y);
			grid.put(x+","+y,grid.getOrDefault(x+","+y,0)+1);
		}
		
	}
}

long answer = 0;
grid.values().each { if (it > 1) ++answer }

System.out.println(answer)
