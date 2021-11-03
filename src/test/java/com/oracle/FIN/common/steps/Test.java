package com.oracle.common.steps;

public class Test {
	public static void main(String[] args) {
		String ac="1,000.0";
		String[] split = ac.split("\\.");
		for(int i=0;i<split.length;i++) {
			System.out.println(split[i]);
		}
		int actualCost =Integer.parseInt(split[0].replaceAll(",",""));
		System.out.println(" Result :"+actualCost);

	}

}
