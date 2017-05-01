package methods_classes;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import model.Conference;
import model.ErrorException;
import model.Paper;


/**
 * @author Dongsheng Han
 * @version 4/30/2017
 * 
 */
public class Test_getPapersAuthoredBy {

	@Test
	public void test() {
		/**
		* @before
		* Create a new conference
		*/
		@SuppressWarnings("deprecation")
		Date deadline = new Date(117, 6, 1, 23, 59, 59);
		int Author_Paper_Submission_Limit = 5;
	    int Reviewer_Paper_Assignment_Limit = 8;
		Conference new_conference = Conference.createConference("Test Conference", 
													 deadline, 
													 Author_Paper_Submission_Limit, 
													 Reviewer_Paper_Assignment_Limit);
		assertNotNull(new_conference);
		
		/**
		* @before
		* Create a new a Paper 
		*/
		List<String> the_Authors = new ArrayList<String>();
		the_Authors.add("Malik, P");
		the_Authors.add("Melik, Q");
		the_Authors.add("Ca S");
		String file_name = "Hungry for life.txt";
		File the_Paper_File = null;
		try {
			// create new file
			the_Paper_File = new File(file_name);
		}catch(Exception e) {
			// if any I/O error occurs
			e.printStackTrace();
		}
		String the_Paper_Title = "Hungry for life";
		String the_Submitter_UID = "Malik55813";
		Paper new_paper = Paper.createPaper(the_Paper_File, the_Authors, the_Paper_Title, the_Submitter_UID);
		assertNotNull(new_paper);
		
		/**
		* @before
		* Add the paper to the Conference
		*/
		try {
			new_conference.addPaper(the_Submitter_UID, new_paper);
		} catch (ErrorException e) {
			e.printStackTrace();
		}
	    
		/**
		* @test
		* Test the getPapersAuthoredBy() method fully to check that addPaper() worked correctly.
		*/
		List<Paper> papers;
		for(int i = 0; i < the_Authors.size(); i++){
			papers = new_conference.getPapersAuthoredBy(the_Authors.get(i));
			assertTrue(papers.contains(new_paper));
		}
	}

}
