//cameron young send2250 assignment 3
import java.util.*;
import java.math.*;

public class a3 {
	public static void main(String args[])
	{	
		//creating necessary variables for the RSA Key Generation
		hash Hash = new hash();
		long eVal = 65537; 
		int length = 2048;
		Random random = new Random();
		Scanner keyboard = new Scanner(System.in); //to take inputs
		
		//generating necessary variables from the asignment specs
		BigInteger E = BigInteger.valueOf(eVal);
		BigInteger P = BigInteger.probablePrime(length, random);
		BigInteger Q = BigInteger.probablePrime(length, random);
		BigInteger N = P.multiply(Q);
		BigInteger privateKey;
		
		
		System.out.println("hello!"); // to see if the program is running
		System.out.println("Please enter a message to encrypt:");
		String userInput = keyboard.nextLine(); //takes the plaintext input
		System.out.println("your message was: " + userInput);
		Hash.setToHex(userInput); //turns user input in to byte array in to hexadecimal string
		System.out.println("your message in hex is: " + Hash.getHex());
		Hash.setHexToBI(Hash.getHex()); //method to turn hexadecimal in to biginteger
		System.out.println("your message in big integer form is: " + Hash.getHexToBI());
		System.out.println("Making RSA key...");
		privateKey = Hash.powMod2(Hash.getHexToBI(), E, N); //creates a biginteger private key of the form input^E mod n
		System.out.println("your RSA key is: " + privateKey);
	}
	
}
