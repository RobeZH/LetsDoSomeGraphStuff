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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.util.Pair;

/**
 * This class adds additional functionality to the graph as a whole.
 * 
 * Contains an instance variable, {@link #graph}, which stores information for all the vertices and edges.
 * @see #populateGraph(String)
 *  - loads a dictionary of words as vertices in the graph.
 *  - finds possible edges between all pairs of vertices and adds these edges in the graph.
 *  - returns number of vertices added as Integer.
 *  - every call to this method will add to the existing graph.
 *  - this method needs to be invoked first for other methods on shortest path computation to work.
 * @see #shortestPathPrecomputation()
 *  - applies a shortest path algorithm to precompute data structures (that store shortest path data)
 *  - the shortest path data structures are used later to 
 *    to quickly find the shortest path and distance between two vertices.
 *  - this method is called after any call to populateGraph.
 *  - It is not called again unless new graph information is added via populateGraph().
 * @see #getShortestPath(String, String)
 *  - returns a list of vertices that constitute the shortest path between two given vertices, 
 *    computed using the precomputed data structures computed as part of {@link #shortestPathPrecomputation()}.
 *  - {@link #shortestPathPrecomputation()} must have been invoked once before invoking this method.
 * @see #getShortestDistance(String, String)
 *  - returns distance (number of edges) as an Integer for the shortest path between two given vertices
 *  - this is computed using the precomputed data structures computed as part of {@link #shortestPathPrecomputation()}.
 *  - {@link #shortestPathPrecomputation()} must have been invoked once before invoking this method.
 *  
 * @author sapan (sapan@cs.wisc.edu)
 * 
 */
public class GraphProcessor {

    /**
     * Graph which stores the dictionary words and their associated connections
     */
    private GraphADT<String> graph;
    
    /**
     * HashTable which stores the distance between different Strings.
     */
    private Hashtable<Pair<String, String>, Integer> distanceTable;
    
    /**
     * HashTable which stores the predecessor from one String to another String
     */
    private Hashtable<Pair<String, String>, String> predecessorMap;
    
    /**
     * Constructor for this class. Initializes instances variables to set the starting state of the object
     */
    public GraphProcessor() {
        this.graph = new Graph<>();
    }
        
    /**
     * Builds a graph from the words in a file. Populate an internal graph, by adding words from the dictionary as vertices
     * and finding and adding the corresponding connections (edges) between 
     * existing words.
     * 
     * Reads a word from the file and adds it as a vertex to a graph.
     * Repeat for all words.
     * 
     * For all possible pairs of vertices, finds if the pair of vertices is adjacent {@link WordProcessor#isAdjacent(String, String)}
     * If a pair is adjacent, adds an undirected and unweighted edge between the pair of vertices in the graph.
     * 
     * @param filepath file path to the dictionary
     * @return Integer the number of vertices (words) added
     */
    public Integer populateGraph(String filepath) {
        Stream<String> stream;
        try {
            stream = WordProcessor.getWordStream(filepath);
        } catch (IOException e) {
            return 0;
        }
        List<String> wordList = stream.collect(Collectors.toList());
        
        for(String string : wordList)
            graph.addVertex(string);
        
        for(int i = 0; i < wordList.size(); i++) {
            for(int j = i+1; j < wordList.size(); j++) {
                String str1 = wordList.get(i), str2 = wordList.get(j);
                //if adjacent, add edge between the two
                if(WordProcessor.isAdjacent(str1, str2)) graph.addEdge(str1, str2);
            }
        }
        return wordList.size();
    }

    
    /**
     * Gets the list of words that create the shortest path between word1 and word2
     * 
     * Example: Given a dictionary,
     *             cat
     *             rat
     *             hat
     *             neat
     *             wheat
     *             kit
     *  shortest path between cat and wheat is the following list of words:
     *     [cat, hat, heat, wheat]
     * 
     * @param word1 first word
     * @param word2 second word
     * @return List<String> list of the words
     */
    public List<String> getShortestPath(String word1, String word2) {
        word1 = word1.toUpperCase(); word2 = word2.toUpperCase();
        ArrayList<String> resList = new ArrayList<>();
        
        //des is the destination, start is the starting point
        String des = word2, start = word1;
        resList.add(des);
        
        //If the shotestDitance is -1, there is no shortest path
        if(getShortestDistance(word1, word2) == -1) return null;
        //Recursively find the predecessor from start to destination
        while(!des.equals(start)) {
            des = predecessorMap.get(new Pair<String, String>(start, des));
            resList.add(des);
        }
        Collections.reverse(resList);
        return resList;
    }
    
    /**
     * Gets the distance of the shortest path between word1 and word2
     * 
     * Example: Given a dictionary,
     *             cat
     *             rat
     *             hat
     *             neat
     *             wheat
     *             kit
     *  distance of the shortest path between cat and wheat, [cat, hat, neat, wheat]
     *   = 3 (the number of edges in the shortest path)
     * 
     * @param word1 first word
     * @param word2 second word
     * @return Integer distance
     */
    public Integer getShortestDistance(String word1, String word2) {
        word1 = word1.toUpperCase(); word2 = word2.toUpperCase();
        Integer dis = distanceTable.get(new Pair<String, String>(word1, word2));
        return dis == null ? -1 : dis;
    }
    
    /**
     * Computes shortest paths and distances between all possible pairs of vertices.
     * This method is called after every set of updates in the graph to recompute the path information.
     * Any shortest path algorithm can be used (Djikstra's or Floyd-Warshall recommended).
     */
    public void shortestPathPrecomputation() {
        Iterable<String> verticesList = graph.getAllVertices();
        distanceTable = new Hashtable<>();
        predecessorMap = new Hashtable<>();
        //Use BFS to calclulate the shortest path
        for(String string : verticesList) {
            Hashtable<String, Integer> visited = new Hashtable<>();
            
            //The BFS queue (using LinkedList)
            LinkedList<Pair<String, Integer>> queue = new LinkedList<>();
            
            //Put the starting point in the queue
            distanceTable.put(new Pair<String, String>(string, string), 0);
            queue.addLast(new Pair<String, Integer>(string, 0));
            visited.put(string, 1);
            
            while(!queue.isEmpty()) {
                Pair<String, Integer> pair = queue.removeFirst();
                String currentString = pair.getKey();
                int distance = pair.getValue();
                for(String nextString : graph.getNeighbors(currentString)) {
                    if(!visited.containsKey(nextString)) {
                        //Updating the next position and push it to the BFS queue
                        visited.put(nextString, 1);
                        distanceTable.put(new Pair<String, String>(string, nextString), distance + 1);
                        predecessorMap.put(new Pair<String, String>(string, nextString), currentString);
                        queue.addLast(new Pair<String, Integer>(nextString, distance + 1));
                    }
                }
            }
        }
        
    }
}
