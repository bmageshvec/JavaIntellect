package com.app.usecase;

import java.util.Random;

public class RandomGenrator {
	
	public static void main(String[] args) {
		int TOT_PAGES=45;
		if(TOT_PAGES>10)
		{
			Random tempRandom = new Random();
			for(int i=0;i<2503;i++)
			{
				int randomInteger = tempRandom.nextInt(25);
				System.out.println("randomInteger"+randomInteger);
			}
		}
		else
		{
			System.out.println("randomInteger set  to default");
		}
	}

}
