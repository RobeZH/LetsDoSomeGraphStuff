//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Dictionary Graph
// Files:           Graph.java, GraphADT.java, GraphProcesser.java, 
//                  GraphTest.java, GraphProcessorTest.java, WordProcessor.java
// Course:          CS400 Spring2018
// Due date:		4/16/2018
//
// Author:          Ziyi Zhang, Zidong Zhang, Matt Zimmers, Shuyan Zhang
// Email:           Matt Zimmers: mzimmers@wisc.edu
//					Ziyi Zhang: zzhang765@wisc.edu
//					Zidong Zhang zzhang773@wisc.edu
//					Shuyan Zhang: szhang399@wisc.edu 
// Lecturer's Name: Deb Deppeler

///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates 
// strangers, etc do.  If you received no outside help from either type of 
// source, then please explicitly indicate NONE.
//
// Persons:         
// Online Sources:  (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import static org.junit.Assert.fail;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


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
	
	@Test
	public void test01_populateGraph_encounter_error() { 
	// When eccountered any error, or accidentally returned -1 
	// while this method populateGraph is actually working properly
		expected = -1; 
		actual = processor.populateGraph("D:\\Eclipse files\\X-team project\\LetsDoSomeGraphStuff\\files"); ;
		if ( expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual + "\n error encountered!");
	}
	@Test
	public void test02_populateGraph_return_correct_number() {
	// See if the populateGraph returns the correct number of 
	// vertice(words) added 
		expected = 6; // TODO: MAY MODIFY this number
		actual = processor.populateGraph("files/GP_test.txt");
		System.out.println(actual);
		if (! expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
	}
	@Test
	public void test03_getShortestPath_get_shortest_path() {
		processor.populateGraph("files/GP_test.txt");		
		processor.shortestPathPrecomputation();

		Sexpected = "[cat, hat, heat, wheat]".toUpperCase();
		Listactual = processor.getShortestPath("cat","wheat"); // this line 
		
		String b = Listactual.toString();
		System.out.println(b);
		if (!Sexpected.equals(b))
			fail ("expected:"+ Sexpected+ "actual: "+ b);
	}
	@Test
	public void test04_getShortestDistance_match_with_shortest_path() {
		processor.populateGraph("files/GP_test.txt");
		processor.shortestPathPrecomputation();
		
		expected = 3;
		actual = processor.getShortestDistance("cat","wheat");
		
		if (!expected.equals(actual))
			fail ("expected:"+ expected+ "actual: "+ actual);
	}
	@Test
	public void test05_getShortestPath_get_shortest_path_GP_large() {
		processor.populateGraph("files/GP_test_large.txt");		
		processor.shortestPathPrecomputation();

		Sexpected = "[qheat, qjheat, jheat, jheet, jgeet, geet]".toUpperCase();
		Listactual = processor.getShortestPath("qheat","geet"); 
		
		String b = Listactual.toString();
		
		System.out.println(Listactual);
		if (!Sexpected.equals(b))
			fail ("expected:"+ Sexpected+ "actual: "+ b);
	}
	@Test
	public void test06_getShortestDistance_match_with_shortest_path_GP_large() {
		processor.populateGraph("files/GP_test_large.txt");
		processor.shortestPathPrecomputation();
		
		expected = 4; //[qheat, qjheat, jheat, jheet, jgeet, geet]
		actual = processor.getShortestDistance("qheat","geet");
		System.out.println(actual);
		if (!expected.equals(actual))
			fail ("expected:"+ expected+ "actual: "+ actual);
	}
	
}


