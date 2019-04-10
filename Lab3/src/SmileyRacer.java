// SmileyRacer.java
//
// ICS 21: Lab Assignment 3
//
// Originally coded by Norm Jacobson, October 2006
// Minor modifications introduced by Alex Thornton, Summer 2009
// Modified for ICS21 Fall 2009 by Norman Jacobson, September 2009
// Clarify comments for ICS 21 Winter 2010 by Norman Jacobson, December 2009


import java.awt.Color;
import java.util.Random;


// A SmileyRacer is a special kind of SmileyFace that is capable of
// participating in a smiley race and tracking its own progress.
// When we say that SmileyRacer "extends SmileyFace", we're saying
// that SmileyRacer objects can do anything that SmileyFace objects
// can do, and can do some other things additionally.  The methods
// and fields in SmileyFace are automatically "inherited" by SmileyRacer,
// making them available to us without us having to copy them into the
// SmileyRacer class.
public class SmileyRacer extends SmileyFace
{
	// The name of this smiley, which is displayed on top of the smiley
	// in the graphics window, and also in the statistics area after the
	// race concludes.
	private String smileyName;
	
	// The color that should be used to show the name of the smiley in the
	// graphics window.
	private Color smileyNameColor;
	
	// A random number generator, to be used in choosing speeds at random.
	private Random generator;

	// The number of ticks that this smiley has been running.
	private int ticks;
	
	// The direction that the smiley is currently moving: RIGHT or LEFT.
	private int directionMoving;
	
	// The number of laps that have been completed by this smiley.
	private int lapsCompleted;
	
	// The number of pixels this smiley moves per tick (i.e., its present
	// speed).
	private int pixelsToMovePerTick;
	
	// The speed change that this smiley will make when each lap is
	// completed.  A steady (unchanging) speed would be indicated by
	// the value 0 in this field; a negative value would indicate
	// that the smiley will get slower after every lap; a positive
	// value means it would get faster.
	private int perLapSpeedChange;
	
	// The distance that the smile of the racer should be moved after each lap, 
	// which is part of what needs to be done to make the racer appear to
	// reverse direction.
	private int distanceToMoveSmile;

	// The fastest possible speed that a smiley may have.
	private static final int MAX_MOVEMENT_PER_TICK = 10;
	
	// Value to store in directionMoving to indicate that this smiley is
	// moving to the right.
	private static final int RIGHT = 1;
	
	// Value to store in directionMoving to indicate that this smiley is
	// moving to the left.
	private static final int LEFT = -1;

	
	// This constructor initializes a SmileyRacer from an existing SmileyFace.
	// (Because SmileyRacers are also SmileyFaces, you can also use an
	// existing SmileyRacer.)  You also need to pass in its name and the
	// name's color.
	public SmileyRacer(SmileyFace existingFace, String name, Color nameColor)
	{
		// To build a racer, we first build the smiley face itself
		super(existingFace);

		// Translate the SmileyRacer so that its leftmost point is at the
		// left edge of the window.  This ensures that there's no cheating
		// -- everyone has to start at the left edge.
		//translate(-getLeftEdge(), 0);

		// Initialize the smiley's name and name color.
		
		smileyName = name;
		smileyNameColor = nameColor;


		// Construct a random number generator.
		
		generator = new Random();

		// Initialize ticks and laps completed to zero.  Set the current
		// direction to RIGHT.
		
		ticks = 0;
		lapsCompleted = 0;
		directionMoving = RIGHT;

		// When a racer hits a wall, it needs to face the other direction.
		// To do this, the mouth has to move to the other side of the face,
		// so that it's off-center on one side by the same amount that it's
		// off-center presently.  The distance that the smile should move
		// is twice the distance that the center of the smile is away from
		// the center of the face.
		
		distanceToMoveSmile = 2 * (getFace().getCenterX() - getSmile().getCenterX());
		// Choose the current speed randomly, so that it lies between 1 and
		// MAX_MOVEMENT_PER_TICK (inclusive).
		
		pixelsToMovePerTick = generator.nextInt(MAX_MOVEMENT_PER_TICK)+1;
		
		// Choose the per-lap speed change randomly, so that it lies between
		// 0 and pixelsToMovePerTick / 2.  Choose its sign (i.e., positive or
		// negative) randomly, with a 50% chance of it being an increase and
		// a 50% chance of it being a decrease.  (Hint: Generate a 0 or 1
		// randomly; if a 0 is generated, make perLapSpeedChange negative.)
		
		/* If next int is 0 the value will be negative, if the next int is 1 the value will be positive */
		perLapSpeedChange = (2 * (generator.nextInt(1)-1) + 1) * generator.nextInt((pixelsToMovePerTick/2)+1);
		
	}


	// finishedRace() returns true if the SmileyRacer has finished the race,
	// false if not.
	public boolean finishedRace()
	{
		if(getLapsCompleted() == 4)
		{
			return true;
		}
		else
		{
			return false;
		}
	}


	// raceForOneTick() moves the racer forward the distance that it moves
	// for one tick.  Also, it increases the number of ticks that the racer
	// has been in the race.
	//
	// A loop is a handy way to accomplish this task: loop over the number of
	// pixels that the racer should move, moving one pixel forward and
	// checking if adjustments are needed (e.g., reverse direction when
	// edge is hit) each iteration. Since moveForwardOnePixel() handles these
	// tasks, all this method has to do is loop through moveForwardOnePixel()
	// for each pixel the racer should move in this turn
	public void raceForOneTick()
	{
		ticks++;
		for( int i = 1; i <= pixelsToMovePerTick; i++ )
		{
			if(!finishedRace())
			{
				moveForwardOnePixel();
			}
		}
	}


	// moveForwardOnePixel(): If the racer has not already finished the race:
	//   moves the racer forward one pixel
	//	 If a wall has been hit:
	//	   increase by 1 the number of laps completed
	// 	   reverse the racer's profile
	//	   reverse the direction of movement
	//         adjust the number of pixels moved per tick by the perLapSpeedChange
	// Remember when adjusting the speed that, post-adjustment, the speed is
	// not permitted to be outside of the range 1..MAX_MOVEMENT_PER_TICK.
	private void moveForwardOnePixel()
	{
		
		if( hitSomething() )
		{
			lapsCompleted++;
			if(!finishedRace())
			{
				directionMoving *= -1;
				reverseProfile();
				pixelsToMovePerTick = perLapSpeedChange + pixelsToMovePerTick;
				if( pixelsToMovePerTick > MAX_MOVEMENT_PER_TICK)
				{
					pixelsToMovePerTick = MAX_MOVEMENT_PER_TICK;
				}
				else if( pixelsToMovePerTick < 1 )
				{
					pixelsToMovePerTick = 1;
				}
			}
		}
		else
		{
			translate(directionMoving, 0);
		}
	}


	// reverseProfile() reverses the appearance of the racer, by swapping
	// the colors of its left and right eyes and moving its smile to the
	// opposite side of its face.
	private void reverseProfile()
	{
		Color temp = getLeftEye().getColor();
		getLeftEye().setColor(getRightEye().getColor());
		getRightEye().setColor(temp);
		getSmile().translate(-directionMoving * distanceToMoveSmile, 0);
	}


	// hitSomething() returns true if the racer has hit either wall.
	// (Hint: use the two methods below.)
	private boolean hitSomething()
	{
		if( hitLeftWall() || hitRightWall() )
		{
			return true;
		}
		else
		{
			return false;
		}
	}


	// hitLeftWall() returns true if the racer has hit the left wall while 
	// moving to the left, false otherwise. (It's possible that the racer, 
	// when moving several pixels at a time, actually goes through the left wall
	// when it reaches it (though this does not appear on screen). So, if the
	// racer has already turned around -- is facing right -- it could hit the left wall
	// again on its "way out." In this case, we don;t want to call it a left wall hit,
	// as it is just an artifact of the racer heading right from beyond the left edge.)
	private boolean hitLeftWall()
	{
		if( getFace().getCenterX() - getFace().getXLength()/2 <= 0 && directionMoving == LEFT )
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	
	// hitRightWall() returns true if the racer has hit the right wall while 
	// moving to the right, false otherwise. (It's possible that the racer, 
	// when moving several pixels at a time, actually goes through the right wall
	// when it reaches it (though this does not appear on screen). So, if the
	// racer has already turned around -- is facing left -- it could hit the right wall
	// again on its "way out." In this case, we don;t want to call it a right wall hit,
	// as it is just an artifact of the racer heading left from beyond the right edge.)
	private boolean hitRightWall()
	{
		if( getFace().getCenterX() + getFace().getXLength()/2 >= 500 && directionMoving == RIGHT )
		{
			return true;
		}
		else
		{
			return false;
		}			
	}


	// Accessors

	public int getTicks()
	{
		return ticks;
	}

	public String getSmileyName()
	{
		return smileyName;
	}

	public Color getSmileyNameColor()
	{
		return smileyNameColor;
	}
	
	public int getLapsCompleted()
	{
		return lapsCompleted;
	}
}
