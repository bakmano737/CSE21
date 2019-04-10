// SmileyAnimation.java
//
// ICS 21: Lab Assignment 3
//
// Originally implemented by Norman Jacobson, October 2006
// Minor modifications introduced by Alex Thornton, Summer 2009
// Minor modifications for ICS21 Fall 2009 by Norman Jacobson, September 2009
// Various updates to comments to clarify/expand upon requirements, by
//	Norman Jacobson, December 2009


import java.awt.Color;
import java.util.ArrayList;


// A SmileyAnimation represents an animation in which a collection of
// smiley faces race one another, each completing a set number of laps.

public class SmileyAnimation
{
	// The duration, in milliseconds, that the animation will pause
	// between animation frames.
	private static final int DELAY = 5;

	// The number of laps that each smiley will complete before the
	// race is over.
	public static final long LAPS_TO_RUN = 4;

	// A representation of the window in which the animation appears.
	private SmileyDisplay display;

	// The list of smileys that are competing in the race.
	private ArrayList<SmileyRacer> racers;

	
	// Information for the statistics area, shown after the race is
	// concluded.
	
	// Title of the statistics area.
	private String statisticsTitle;
	
	// The average time, in ticks, it took for a smiley to complete
	// the race.
	private double averageTicks;
	
	// The number of ticks that the fastest smiley took to complete
	// the race.
	private int fewestTicks;
	
	// The name of the fastest smiley.
	private String fastestSmileyName;

	// The number of ticks that the slowest smiley took to complete
	// the race.
	private int mostTicks;
	
	// The name of the slowest smiley.
	private String slowestSmileyName;
	
	
	// This constructor initializes the race, performing a variety of set-up
	// tasks.
	//
	// Your program should work for anywhere from one up to tens of smileys.
	// Be sure to size the smileys so that they all fit on the screen and do
	// not overlap each other.
	//
	// All smiley faces must have the mouth set to the background color of
	// the SmileyDisplay and should initially be constructed so that they're
	// facing to the right (i.e., their mouth is on the right-hand side of
	// the face so it looks like an open mouth, with only the right eye visible)
	//
	// Remember, there are two ways to build a SmileyRacer:
	//
	// (1) Build a SmileyFace first -- including setting its attributes --
	//     then "feed it" to the SmileyRacer constructor.
	// (2) Feed the SmileyRacer constructor an existing SmileyRacer.
	//
	// Remember, also, that SmileyRacers *are* SmileyFaces, so any method
	// you can call on a SmileyFace can also be called on a SmileyRacer.
	public SmileyAnimation(SmileyDisplay d)
	{
		display = d;
		averageTicks = 0;
		fastestSmileyName = "No Racers";
		fewestTicks = 0;
		mostTicks = 0;
		slowestSmileyName = "No Racers";
		statisticsTitle = "No Racers";
		racers = new ArrayList<SmileyRacer>();
		SmileyFace starter = new SmileyFace();
		starter.getFace().setAttributes(Color.RED, 0, 0, 40, 40);
		starter.getSmile().setAttributes(Color.BLACK, starter.getFace().getCenterX() + 10, starter.getFace().getCenterY() + 10, 25, 15);
		starter.getRightEye().setAttributes(Color.BLUE, starter.getFace().getCenterX() + 10, starter.getFace().getCenterY() - 10, 12, 12);
		starter.getLeftEye().setAttributes(starter.getFace().getColor(), starter.getFace().getCenterX() - 10, starter.getFace().getCenterY() - 10, 12, 12);
		for( int i = 1; i <= 10; i++)
		{
			//SmileyRacer racko = new SmileyRacer(starter, "#" + i, Color.blue);
			racers.add((i-1), new SmileyRacer(starter, "#" + i, Color.blue) );
			racers.get(i-1).translate(50, 45 * (i-1) + 30);
		}
	}

	
	// animate() is called once, from SmileyFrame, to show the running race when
	// the GO! button is pressed; your code should not call it.
	//
	// For each tick until all racers have completed all of their laps, animate()
	// moves the racers forward the distance they should go in that tick,
	// based on their current speeds.  In  more detail, animate() follows this
	// logic:
	//
	//     Until all racers have finished the race...
	//     Each time through the loop is one 'tick' of the race clock
	//     {   For each racer in the list of racers...
	//            If the racer has not yet finished the race...
	//                Move the racer forward one clock tick
	//         Repaint the screen to show the movement made this tick
	//         Pause to slow the animation to a visible speed
	//     }
	//     Race done!  Compute the statistics
	//
	public void animate()
	{
		while(!(allRacersFinished()))
		{
			for( int i = 0; i < racers.size(); i++ )
			{
				if( !(racers.get(i).finishedRace()) )
					racers.get(i).raceForOneTick();
					pause();
					display.draw();
			}
		}
		statisticsTitle = "Race Done!";
		computeRaceStatistics();
	}


	// computeRaceStatistics() computes the statistics for this race.  If
	// there were no racers, the title should indicate that no race took
	// place and no statistics are computed.  Otherwise, the title should 
	// be an introduction to the statistics, then the various statistics 
	// should be computed.
	private void computeRaceStatistics()
	{
		computeFastestRunner();
		computeSlowestRunner();
		computeAverageTime();
	}


	// computeFastestSmiley() determines which racer was the fastest,
	// storing the number of ticks and the name of that racer into
	// the appropriate fields. If there is a tie, any one of the tied
	// racers can be named the fastest,
	private void computeFastestRunner()
	{
		if(racers.size() != 0) 
		{
			fewestTicks = racers.get(0).getTicks();
			for(int i = 1; i < racers.size(); i++ )
			{
				if( racers.get(i).getTicks() < fewestTicks )
				{
					fastestSmileyName = racers.get(i).getSmileyName();
					fewestTicks = racers.get(i).getTicks();
				}
			}
		}
		else
		{
			fastestSmileyName = "No Racers";
			fewestTicks = 0;
		}
	}

	
	// computeSlowestSmiley() determines which racer was the slowest,
	// storing the number of ticks and the name of that racer into
	// the appropriate fields. If there is a tie, any one of the tied
	// racers can be named the slowest
	private void computeSlowestRunner()
	{
		if(racers.size() != 0) 
		{
			mostTicks = racers.get(0).getTicks();
			for(int i = 1; i < racers.size(); i++ )
			{
				if( racers.get(i).getTicks() > mostTicks )
				{
					slowestSmileyName = racers.get(i).getSmileyName();
					mostTicks = racers.get(i).getTicks();
				}
			}
		}
		else
		{
			slowestSmileyName = "No Racers";
			mostTicks = 0;
		}		
	}

	
	// computeAverageTime() determines the average time, in ticks, each
	// racer spent completing the race.
	private void computeAverageTime()
	{
		int sum = 0;
		for(int i = 0; i < racers.size(); i++ )
		{
			sum = sum + racers.get(i).getTicks();
		}
		averageTicks = sum / racers.size();
	}

	
	// allRacersFinished() returns true if all of the racers have finished
	// the race, false otherwise.
	//
	// The algorithm for solving this kind of problem is:
	//
	//     Assume all have finished
	//     Go through the racers, looking for one that hasn't finished
	//     If you find one that hasn't finished, return false
	//     If you never find one, return true
	//
	private boolean allRacersFinished()
	{
		for(int i = 0; i < racers.size(); i++ )
		{
			if( !(racers.get(i).finishedRace()) )
			{
				return false;
			}
		}
		return true;
	}
	
	
	// pause() pauses the animation for the number of milliseconds given
	// by the DELAY constant declared above.
	private void pause()
	{
		try
		{
			Thread.sleep(DELAY);
		}
		catch (InterruptedException e)
		{
		}
	}

	
	// Accessors -- used by the graphics routine to obtain the information
	// that it needs to display

	// getRacers() returns all the racers (with their information).
	public ArrayList<SmileyRacer> getRacers()
	{
		return racers;
	}


	// getStatisticsTitle() returns the title that should be shown in the
	// statistics area of the window.
	public String getStatisticsTitle()
	{
		return statisticsTitle;
	}

	
	// getAverageTicks() returns the average time, in ticks, that each
	// smiley spent completing the race.
	public double getAverageTicks()
	{
		return averageTicks;
	}

	
	// getFewestTicks() returns the number of ticks spent by the fastest
	// smiley in completing the race.
	public int getFewestTicks()
	{
		return fewestTicks;
	}

	
	// getMostTicks() returns the number of ticks spent by the slowest
	// smiley in completing the race.
	public int getMostTicks()
	{
		return mostTicks;
	}

	
	// getFastestSmileyName() returns the name of the fastest smiley.
	public String getFastestSmileyName()
	{
		return fastestSmileyName;
	}


	// getSlowestSmileyName() returns the name of the slowest smiley.
	public String getSlowestSmileyName()
	{
		return slowestSmileyName;
	}
}
