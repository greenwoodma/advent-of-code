

//System.out.println(findNumbers(9232416));



//9232416 --> [8927518, 7] --> 1478097
//14144084 --> [13240670, 7] --> 1478097


//System.out.println(getKey(9232416,13240670));
System.out.println(getKey(14144084,8927518));

public long getKey(long publicKey, long loopSize) {
	long value = 1;

	int subjectNumber = publicKey;

	for (int i = 0 ; i < loopSize ; ++i) {
		value = value * subjectNumber;
		value = value % 20201227;
	}

	return value;

}


public int[] findNumbers(int publicKey) {

	int subjectNumber = 7;

	while(true) {

		//if (publicKey % subjectNumber == 0) {

			System.out.println("Trying Subject Number " + subjectNumber);

			int loopSize = 0;

			int value = 1;

			while (value != publicKey) {	
				value = value * subjectNumber;
				value = value % 20201227;

				//System.out.println(value);

				++loopSize;
			}

			System.out.println(value);

			if (value == publicKey)
				return [loopSize,subjectNumber];

		//}

		++subjectNumber
	}
}
