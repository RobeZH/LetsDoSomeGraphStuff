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
	/**
	 * When eccountered any error, or accidentally returned -1 
	 * while this method populateGraph is actually working properly
	 */
	public void test01_populateGraph_encounter_error() { 
		expected = -1; 
		actual = processor.populateGraph("D:\\Eclipse files\\X-team project\\LetsDoSomeGraphStuff\\files"); ;
		if ( expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual + "\n error encountered!");
	}
	@Test
	/**
	 *  See if the populateGraph returns the correct number of 
	 *  vertice(words) added 
	 */
	public void test02_populateGraph_return_correct_number() {
		expected = 6; // TODO: MAY MODIFY this number
		actual = processor.populateGraph("files/GP_test.txt");

		if (! expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
	}
	@Test
	/**
	 *  get the shortest path between two words for GP_test
	 */
	public void test03_getShortestPath_get_shortest_path() {
		processor.populateGraph("files/GP_test.txt");		
		processor.shortestPathPrecomputation();

		Sexpected = "[cat, hat, heat, wheat]".toUpperCase();
		Listactual = processor.getShortestPath("cat","wheat"); 
		
		String b = Listactual.toString();

		if (!Sexpected.equals(b))
			fail ("expected:"+ Sexpected+ "actual: "+ b);
	}
	@Test
	/**
	 *  get the shortest distance between two words for GP_test
	 */
	public void test04_getShortestDistance_match_with_shortest_path() {
		processor.populateGraph("files/GP_test.txt");
		processor.shortestPathPrecomputation();
		
		expected = 3;
		actual = processor.getShortestDistance("cat","wheat");
		
		if (!expected.equals(actual))
			fail ("expected:"+ expected+ "actual: "+ actual);
	}
	@Test
	/**
	 *  get the shortest path between two words for GP_test_large(more words)
	 */
	public void test05_getShortestPath_get_shortest_path_GP_large() { 
		// test for GP_large
		processor.populateGraph("files/GP_test_large.txt");		
		processor.shortestPathPrecomputation();

		Sexpected = "[qheat, jheat, jheet, jgeet, geet]".toUpperCase();
		Listactual = processor.getShortestPath("qheat","geet"); 
		
		String b = Listactual.toString();

		if (!Sexpected.equals(b))
			fail ("expected:"+ Sexpected+ "actual: "+ b);
	}
	@Test
	/**
	 *   get the shortest path between two words for GP_test_large(more words)
	 */
	public void test06_getShortestDistance_match_with_shortest_path_GP_large() {
		// test for GP_large
		processor.populateGraph("files/GP_test_large.txt");
		processor.shortestPathPrecomputation();
		
		expected = 4; //[qheat, jheat, jheet, jgeet, geet]
		actual = processor.getShortestDistance("qheat","geet");

		if (!expected.equals(actual))
			fail ("expected:"+ expected+ "actual: "+ actual);
	}
	
}


