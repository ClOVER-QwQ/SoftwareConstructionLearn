package chapter02;

import java.util.Random;

public class Constrcution00 {

	public static void main(String[] args) {
		int m=0;
		int n=0;
		int ov=0;
		char o='+';
		Random random=new Random();
		for(int i=0;i<50;i++) {
			m=random.nextInt(101);
			n=random.nextInt(101);
			ov=random.nextInt(2);
			if(ov==1) {
				o='+';
			}
			else {
				o='-';
			}
			System.out.println((i+1)+":"+" "+m+o+n+"=");
		}
		

	}

}
