package br.usinadigital.msgsystemws.util;

import java.util.Arrays;

public class Util {
	
	public static String intArrayToString(int[] intArray){
		String strArray = Arrays.toString(intArray);
        return strArray.substring(1,strArray.length()-1);
	}
	
	public static int sumIntArray(int[] array){
		int sum = 0;
		for(int item : array) sum += item;
		
		return sum;
	}
}
