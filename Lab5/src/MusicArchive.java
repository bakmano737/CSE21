// MusicArchive.java
//
// ICS 21: Lab Assignment 5 
//
// Originally implemented by Norman Jacobson
// Minor modifications introduced by Alex Thornton, Summer 2009

// Make a manager and turn it loose!
public class MusicArchive
{
	public static void main(String[] args)
	{
		MusicManager musicManager = new MusicManager();
		musicManager.makeIndexAndDisplayCounts();
	}
}
