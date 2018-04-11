import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import static org.junit.Assert.*;
import org.junit.Test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;


public class GraphProcessorTest {
	private GraphProcessor processor;
	public GraphProcessorTest() {
		this.processor = new GraphProcessor();
	}
	ArrayList<String> dic = new ArrayList<>();
	
	String Sexpected = null;
	String Sactual = null;
	Integer expected = null;
	Integer actual = null;
	List<String> Listexpected = new ArrayList<>();
	List<String> Listactual = new ArrayList<>();
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		dic = new ArrayList<String>();
		//initilize the dictionary before every single test
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	public void test01_populateGraph_encounter_error() { 
	// When eccountered any error, or accidentally returned -1 
	// while this method populateGraph is actually working properly
		expected = -1; 
		actual = processor.populateGraph("write_the_filepath"); ;
		if ( expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual + "\n error encountered!");
	}
	@Test
	public void test02_populateGraph_return_correct_number() {
	// See if the populateGraph returns the correct number of 
	// vertice(words) added 
		expected = 10; // TODO: MAY MODIFY this number
		actual = processor.populateGraph("write_the_filepath");
		if (! expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
	}
	@Test
	public void test03_throws_IllegalArgumentException() {
		try {
			processor.populateGraph("write_the_filepath");
			fail("did not throw IllegalArgumentException");
		}catch (IllegalArgumentException e) {
			
		}
	}
	@Test
	public void test04_getShortestPath_get_shortest_path() {
		dic.add("cat");
		dic.add("rat");
		dic.add("hat");
		dic.add("neat");
		dic.add("wheat");
		dic.add("kit");
		
		Sexpected = "[cat, hat, heat, wheat]";
		Listactual = processor.getShortestPath(dic.get(0),dic.get(4));
		
		StringBuilder b = new StringBuilder();
		Listactual.forEach(b::append);
		if (!Sexpected.equals(b))
			fail ("expected:"+ Sexpected+ "actual: "+ b);
	}
	@Test
	public void test05_getShortestDistance_get_distance_match_with_shortest_path() {
		dic.add("cat");
		dic.add("rat");
		dic.add("hat");
		dic.add("neat");
		dic.add("wheat");
		dic.add("kit");
		
		expected = 3;
		actual = processor.getShortestDistance(dic.get(0),dic.get(4));
		if (!expected.equals(actual))
			fail ("expected:"+ expected+ "actual: "+ actual);
	}
}


