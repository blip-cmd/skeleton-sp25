/*
Exercise 1.2.3: Try to write a program that sums up the command line arguments, assuming they are numbers.
*/

public class DemoArgs{

	public static void main(String[] args){
	int sum = 0;

	for(int i=0;i<args.length ;i++){
	sum = sum + Integer.parseInt(args[i]);
	}
	System.out.println(sum);

	}
}