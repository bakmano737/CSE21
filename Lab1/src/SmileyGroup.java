// SmileyGroup.java
//
// ICS 21: Lab Assignment 1
//
// Originally coded by Norman Jacobson, September 2006
// Minor modifications introduced by Alex Thornton, June 2009
// Minor modifications by Norman Jacobson for ICS 21 Fall 2009, September 2009
//
// A SmileyGroup represents a collection of up to three smiley faces,
// each with its own attributes.

import java.awt.Color;


public class SmileyGroup
{
	// The three smiley faces that make up the group
	private SmileyFace smiley1;
	private SmileyFace smiley2;
	private SmileyFace smiley3;
	

	// This constructor builds up to three smileys in the group.  You
	// can construct each face either from scratch or as a copy of an
	// existing one, setting the attributes of the various face parts,
	// translating and/or scaling face parts, or translating the
	// entire smiley face.
	//
	// See the lab write-up for the kinds of smiley faces we're expecting
	// you to create and the methods we expect you to employ.
	//
	// The predefined Colors are
	//		BLACK, BLUE, CYAN, GRAY, DARK_GRAY, LIGHT_GRAY, GREEN, MAGENTA,
	//		ORANGE, PINK, RED, WHITE, YELLOW
	//
	// The exact characteristics (shape, color, position) of each smiley are
	// up to you; on the lab exam, you will be given specific instructions
	// on what the smileys' characteristics are required to be.
	public SmileyGroup()
	{
		/* Declare local center variables */
		int screenCenterX = 240;
		int screenCenterY = 240;
		int faceSize = 150;
		int delta = faceSize * 3/4;
		
		/* Create First SmileyFace object */
		smiley1 = new SmileyFace();
		/* Set smiley1's attributes */
		getSmiley1().getFace().setAttributes(Color.BLUE, screenCenterX, screenCenterY, faceSize, faceSize);
		getSmiley1().getLeftEye().setAttributes(Color.RED, screenCenterX - faceSize/4, screenCenterY - faceSize/4, faceSize/5, faceSize/5);
		getSmiley1().getRightEye().setAttributes(Color.RED, screenCenterX + faceSize/4, screenCenterY - faceSize/4, faceSize/5, faceSize/5);
		getSmiley1().getSmile().setAttributes(Color.BLACK, screenCenterX, screenCenterY + faceSize/4, faceSize/5, faceSize/5);
		
		/* Make smiley2 and smiley3 copies of smiley1 */
		smiley2 = new SmileyFace(smiley1);
		smiley3 = new SmileyFace(smiley1);
		
		/* Translate the smiley1, smiley2, and smiley3 */
		getSmiley1().translate(0, -delta);
		getSmiley2().translate(delta, delta);
		getSmiley3().translate(-delta, delta);
	}
	
	// Accessor methods that return each of the smileys in the group
	
	public SmileyFace getSmiley1()
	{
		return smiley1;
	}
	
	
	public SmileyFace getSmiley2()
	{
		return smiley2;
	}
	
	
	public SmileyFace getSmiley3()
	{
		return smiley3;
	}
}
