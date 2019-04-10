// MusicItem.java
// ICS 21 Lab Assignment 5
//
// Coded by Norman Jacobson, November 2006
// Minor revisions for ICS 21 Fall 2009 by Norman Jacobson, September 2009
// Minor revisions for ICS 21 Winter 2010 by Norman Jacobson, December 2009


import java.util.*;


// MusicItem contains the fields common to all music items:
//	accession number, title, media code
// The class is used as the basis of all music item types,
//	but since there is no non-specific music item, the class
//	is abstract, preventing us from inadvertently trying to
//	create a non-specialized music item.
abstract class MusicItem
{
	// Category codes
	public static final String PAPER = "P";
	public static final String COMPACT = "C";
	public static final String VINYL = "V";
	public static final String WAX_CYLINDER = "W";

	// ..and where in input array they are located
	public static final int ACCESSION_NUMBER_POSITION = 0;
	public static final int TITLE_POSITION = 1;
	public static final int MEDIA_CODE_POSITION = 2;


	// Fields common to all music items
	private String accessionNumber;			// accession number
	private String title;				// title
	private String media;				// media code


	// Construct a music item from item
	// position 0: accession number
	// position 1: title
	// position 2: media code
	public MusicItem(ArrayList<String> item)
	{
		accessionNumber = item.get(ACCESSION_NUMBER_POSITION);
		title = item.get(TITLE_POSITION);
		media = item.get(MEDIA_CODE_POSITION);
	}

	// Force each media type to provide its supplemental
	// information in a form suitable for printing
	public abstract String displaySupplementalInfo();


	// Accessors
	public String getAccessionNumber()
	{
		return accessionNumber;
	}

	public String getTitle()
	{
		return title;
	}

	public String getMedia()
	{
		return media;
	}
}



// Make a derived class for each kind of music item


// Compact Media -- CDs, DVDS, ...
class CompactMedia extends MusicItem
{
	// Supplemental information for this kind of media
	private int numberOfTracks;		// number of tracks
	private String yearReleased;	// year media released;

	// Where supplemental info is located in the input ArrayList
	private static int NUMBER_OF_TRACKS_POSITION = 3;
	private static int YEAR_RELEASED_POSITION = 4;

	// Make a compact media item from the read line of the music file
	public CompactMedia(ArrayList<String> item)
	{
		
		// Use music item constructor to set accession number, title, media code
		super(item);
		// Set number of tracks and year released
		numberOfTracks = Integer.parseInt(item.get(NUMBER_OF_TRACKS_POSITION));
		yearReleased = item.get(YEAR_RELEASED_POSITION);
	}

	// Place the supplemental into a string suitable for display as part
	// of the index report
	public String displaySupplementalInfo()
	{
		String accStr = this.getAccessionNumber();
		String nameStr = this.getTitle();
		String medStr = this.getMedia();
		String str = "Accession Number: " + accStr;
		str += "\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Title: " + nameStr;
		str += "\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Media Type: " + medStr;
		str += "\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t " + numberOfTracks + " tracks";
		str += "\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t (" + yearReleased + ")\n";
		return str;
	}
}


// Vinyl records
class VinylRecord extends MusicItem
{
	// Supplemental information for this kind of media
	private String label;		// record company's imprint (label)
	private int rpm;			// speed of play (33, 45, 78)

	// Where supplemental info is located in the input ArrayList
	private static int LABEL_POSITION = 3;
	private static int RPM_POSITION = 4;

	// Make a vinyl record item from the read line of the music file
	public VinylRecord(ArrayList<String> item)
	{
		// Use music item constructor sets accession number, title, media code
		super(item);
		// Set label and RPM
		label = item.get(LABEL_POSITION);
		rpm = Integer.parseInt(item.get(RPM_POSITION));
	}

	// Place the supplemental into a string suitable for display as part
	// of the index report
	public String displaySupplementalInfo()
	{
		String accStr = this.getAccessionNumber();
		String nameStr = this.getTitle();
		String medStr = this.getMedia();
		String str = "Accession Number: " + accStr;
		str += "\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Title: " + nameStr;
		str += "\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Media Type: " + medStr;
		str += "\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Produced by: " + label;
		str += "\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t @ " + rpm + "rpm\n";
		return str;
	}

}


// Wax cylinders
class WaxCylinder extends MusicItem
{
	// Supplemental information for this kind of media
	private String maker;	// manufacturer/record company

	// Where supplemental info is located in the input ArrayList
	private static int MAKER_POSITION = 3;

	// Make a wax cylinder item from the read line of the music file
	public WaxCylinder(ArrayList<String> item)
	{
		// Use music item constructor sets accession number, title, media code
		super(item);
		// Set maker
		maker = item.get(MAKER_POSITION);
	}

	// Place the supplemental into a string suitable for display as part
	// of the index report
	public String displaySupplementalInfo()
	{
		String accStr = this.getAccessionNumber();
		String nameStr = this.getTitle();
		String medStr = this.getMedia();
		String str = "Accession Number: " + accStr;
		str += "\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Title: " + nameStr;
		str += "\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Media Type: " + medStr;
		str += "\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Made By: " + maker + "\n";
		return str;
	}

}

// Sheet music and books
class Paper extends MusicItem
{
	// Supplemental information for this kind of media
	private int numberOfPages;	// number of pages

	// Where supplemental info is located in the input ArrayList
	private static int NUMBER_OF_PAGES_POSITION = 3;

	// Make a paper item from the read line of the music file
	public Paper(ArrayList<String> item)
	{
		// Use music item constructor sets accession number, title, media code
		super(item);
		// Set number of pages
		//System.out.println(item.get(NUMBER_OF_PAGES_POSITION));
		numberOfPages = Integer.parseInt(item.get(NUMBER_OF_PAGES_POSITION));
	}

	// Place the supplemental into a string suitable for display as part
	// of the index report
	public String displaySupplementalInfo()
	{
		String accStr = this.getAccessionNumber();
		String nameStr = this.getTitle();
		String medStr = this.getMedia();
		String str = "Accession Number: " + accStr;
		str += "\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Title: " + nameStr;
		str += "\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Media Type: " + medStr;
		str += "\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t " + numberOfPages + " pages\n";
		return str;
	}
}



