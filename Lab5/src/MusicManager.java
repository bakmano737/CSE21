// MusicManager.java
//
// ICS 21 Lab Assignment #5 - Completed
//
// Written by Norman Jacobson, December 2006
// Minor modifications for ICS 21 Fall 2009 by Norman Jacobson, September 2009


import java.util.*;


// Manage the making of the index and category counts
class MusicManager
{
	// Music and index file names
	private static final String MUSIC_FILE_NAME = "music.txt";
	private static final String INDEX_FILE_NAME = "index.txt";

	// Music and index files
	private MusicFile musicToSort;
	private IndexFile indexedMusicList;

	// The data structure used to sort the music items
	private MusicList itemList;


	// Constructs the files and music list
	public MusicManager()
	{
		musicToSort = new MusicFile();
		indexedMusicList = new IndexFile();
		itemList = new MusicList();
	}

	// Prepares an index file from an incoming list of music items and
	// displays to the screen the media category counts and
	// a total count of items items processed.
	public void makeIndexAndDisplayCounts()
	{
		makeMusicIndex();
		displayCounts();
		writeMusicIndex();
	}



	// Open the file
	// While there are more lines...
	//	read in an item (a line of the file)
	//	based on the MEDIA_CODE_POSITION of the line,
	//	create a new music item of the correct type,
	//	and add it into the itemList
	// Close file (when no more lines)
	private void makeMusicIndex()
	{
		musicToSort.open(MUSIC_FILE_NAME);
		while(musicToSort.hasMoreItems())
		{
			ArrayList<String> itemStr = musicToSort.readItem();
			String mediaCode = itemStr.get(MusicItem.MEDIA_CODE_POSITION);
			if(mediaCode.equals(MusicItem.PAPER))
			{
				//System.out.println(mediaCode);
				MusicItem item = new Paper(itemStr);
				itemList.addItem(item);
			}
			else if(mediaCode.equals(MusicItem.VINYL))
			{
				MusicItem item = new VinylRecord(itemStr);
				itemList.addItem(item);
			}
			else if(mediaCode.equals(MusicItem.WAX_CYLINDER))
			{
				MusicItem item = new WaxCylinder(itemStr);
				itemList.addItem(item);
			}
			else if(mediaCode.equals(MusicItem.COMPACT))
			{
				MusicItem item = new CompactMedia(itemStr);
				itemList.addItem(item);
			}
			else
			{
				System.out.println("Invalid Media Code!");			
			}
		}
		musicToSort.close();
	}


	// Open the indexed music file;
	// write out the sorted music list to the file;
	// close the file
	private void writeMusicIndex()
	{
		indexedMusicList.open(INDEX_FILE_NAME);
		for(int i = 0; i < itemList.getBuckets().size(); i++)
		{
			for(int j = 0; j < itemList.getBuckets().get(i).getItems().size(); j++)
			{
				indexedMusicList.writeItem(itemList.getBuckets().get(i).getItems().get(j));
			}
		}
		indexedMusicList.close();
	}


	// Display the counts
	private void displayCounts()
	{
		System.out.print("Total: " + itemList.getTotalItemCount() + "\n");
		System.out.print("Paper: " + itemList.getPaperItemCount() + "\n");
		System.out.print("Wax Cylinder: " + itemList.getWaxCylinderItemCount() + "\n");
		System.out.print("Vinyl: " + itemList.getVinylItemCount() + "\n");
		System.out.print("Compact Media: " + itemList.getCompactMediaItemCount() + "\n");
	}
}
