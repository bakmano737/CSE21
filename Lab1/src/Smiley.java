// Smiley.java
//
// ICS 21: Lab Assignment 1
//
// Originally coded by Norm Jacobson, September 2006
// Minor modifications introduced by Alex Thornton, June 2009
// Modified for ICS21 Fall 2009 by Norman Jacobson, September 2009
// Modified for ICS21 Winter 2011 by Norman Jacobson, October 2010
//
// This is where the program begins. 
// We construct a SmileyFrame (i.e., the window that holds the smiley group);
// we construct a smiley group to display in it;
// we add that group into the frame,
// and then make the frame visible, so we can see the drawn group.
// The provided graphics routines take over from there, drawing 
// the provided group in the frame.
public class Smiley
{
	public static void main(String[] args)
	{
		SmileyFrame f = new SmileyFrame();
		SmileyGroup group = new SmileyGroup();
		f.addSmileyGroupToFrame(group);
		f.setVisible(true);
	}
}
