// SmileyFace.java
//
// ICS 21: Lab Assignment 1
//
// Originally coded by Norm Jacobson, September 2006
// Minor modifications introduced by Alex Thornton, June 2009
//
// A SmileyFace consists of four parts:
// * A face (the circle that underlies the entire smiley face)
// * A left eye
// * A right eye
// * A smile
public class SmileyFace
{
	private Face face;
	private Eye leftEye;
	private Eye rightEye;
	private Smile smile;

	// When we construct a new SmileyFace from scratch, we create
	// a new face, new eyes, and a new smile, but don't give them
	// any characteristics; the characteristics are to be filled in
	// later.
	public SmileyFace()
	{
		face = new Face();
		leftEye = new Eye();
		rightEye = new Eye();
		smile = new Smile();
	}

	// When we construct a new SmileyFace that is intended to be a
	// copy of an original SmileyFace, we construct each new part
	// from the same part of the old face
	public SmileyFace(SmileyFace original)
	{
		face =  new Face(original.getFace());
		leftEye = new Eye(original.getLeftEye());
		rightEye = new Eye(original.getRightEye());
		smile = new Smile(original.getSmile());
	}

	// translate() moves the entire face, including all of its parts,
	// by the given distance horizontally (deltaX) and vertically (deltaY).
	// Positive values move the figure right and down; negative ones up and left
	public void translate(int deltaX, int deltaY)
	{
		face.translate(deltaX, deltaY);
		leftEye.translate(deltaX, deltaY);
		rightEye.translate(deltaX, deltaY);
		smile.translate(deltaX, deltaY);
	}


	// Accessors that return each part of a SmileyFace

	public Face getFace()
	{
		return face;
	}


	public Eye getLeftEye()
	{
		return leftEye;
	}


	public Eye getRightEye()
	{
		return rightEye;
	}


	public Smile getSmile()
	{
		return smile;
	}
}


