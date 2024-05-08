import java.math.BigInteger;
import java.util.Random;
import java.util.*;

public class hash
{
	//declaring necessary variables
	private BigInteger n;
	private BigInteger thetaM;
	private BigInteger d;
	private BigInteger[] pubKey;
	private BigInteger[] privKey;
	private BigInteger hexKey;
	private BigInteger cipherText;
	private BigInteger plainText;
	private String hexText;
	private StringBuilder builder = new StringBuilder(); //used to continually concatenate a string
	private BigInteger rs;
	
	//N, D, thetaM Based off the notes in lecture 2
	public void setN(BigInteger p, BigInteger q)
	{
		this.n = p.multiply(q);
	}
	
	public BigInteger getN()
	{
		return this.n;
	}
	
	public void setThetaM(BigInteger p, BigInteger q)
	{
		BigInteger one = BigInteger.ONE;
		p = p.subtract(one);
		q = q.subtract(one);
		this.thetaM = p.multiply(q);
	}
	
	public BigInteger getThetaM()
	{
		return this.thetaM;
	}
	
	public void setD(BigInteger e, BigInteger thetaM)
	{
		//find a d s.t. ed = 1mod thetaM
		//so we solve e*d - thetaM*y = 1 
		//d = e/gcd(e, thetaM)
		BigInteger gcd = e.gcd(thetaM); 
		this.d = e.divide(gcd); 
			
	}
	
	public BigInteger getD()
	{
		return this.d;
	}
	
	public void setPubKey(BigInteger n, BigInteger e)
	{
		//store the public key in an array
		pubKey = new BigInteger[2]; 
		pubKey[0] = e;
		pubKey[1] = n;
	}
	
	public BigInteger[] getPubKey()
	{
		return this.pubKey;
	}
	
	public void setPrivKey(BigInteger p, BigInteger q, BigInteger d)
	{
		//store private key in an array
		privKey = new BigInteger[3];
		privKey[0] = p;
		privKey[1] = q;
		privKey[2] = d;
	}
	
	public BigInteger[] getPrivKey()
	{
		return this.privKey;
	}
	
	public void setToHex(String s)
	{
		byte[] byteArray = s.getBytes(); // turns the plain text in to hex bytes and adds to the array
		for (byte b : byteArray) //for bytes b in byteArray
		{
			//from https://stackoverflow.com/questions/923863/converting-a-string-to-hexadecimal-in-java
			builder.append(String.format("%02x", b));
			//my understanding is string.format adds padded 0's to the hex byte in the case where the hex would be A it becomes 0A.
			//%02x returns hex output of a byte (https://www.javatpoint.com/java-string-format)
			//stringbuilder.append concatenates in a loop to the end of the string continually
		}
	}
	
	public String getHex()
	{
		return this.builder.toString();
	}
	
	public void setHexToBI(String s)
	{
		//BI hexKey
		//making use of BI method public BigInteger(String val, int radix) where the radix is 16 for hexadecimal
		this.hexKey = new BigInteger(s, 16);
	}
	
	public BigInteger getHexToBI()
	{
		return this.hexKey;
	}
	
	
	public BigInteger powMod2(BigInteger base, BigInteger exponent, BigInteger mod)
	{
		//based off the pseudocode in lab2
		//makes and returns the encrypted plaintext as biginteger
		BigInteger two = BigInteger.ONE.add(BigInteger.ONE); //need BigInt constant 2
		
		if (mod.compareTo(BigInteger.ZERO) == 0)
		{
			return BigInteger.ZERO;
		}
		BigInteger rs = BigInteger.ONE;
		while (exponent.compareTo(BigInteger.ZERO) > 0)
		{
			if ((exponent.compareTo(BigInteger.ONE) == 1) && (1 == 1))
			{
				rs = (rs.multiply(base)).mod(mod);
			}
			exponent = exponent.divide(two); //pseudocode in lab2 divides by 2 here
			base = (base.multiply(base)).mod(mod);					
		}
		return rs;
	}

}


	

