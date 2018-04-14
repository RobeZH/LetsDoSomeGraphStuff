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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Undirected and unweighted graph implementation
 * 
 * @param <E> type of a vertex
 * 
 * @author sapan (sapan@cs.wisc.edu)
 * 
 */
public class Graph<E> implements GraphADT<E> {
    
    /**
     * Graph is represented by adjacencyList, E is vertex, Set<E> is 
     * its adjacent vertex.
     */
    private HashMap<E, Set<E>> adjacencyList;
    
    public Graph() {
    	this.adjacencyList = new HashMap<>();
    }
    
    
    /**
     * {@inheritDoc}
     */
    @Override
    public E addVertex(E vertex) {
    	/**
    	 * if vertex is null or vertex already exists.
    	 */
        if (vertex == null) {
            return null;
        }
        
        if (this.adjacencyList.containsKey(vertex)) {
            return null;
        }
        this.adjacencyList.put(vertex, new HashSet<E>());
        return vertex;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E removeVertex(E vertex) {
    	/**
    	 * if vertex is null or vertex does not exists.
    	 */
        if (vertex == null) {
            return null;
        }
        
        if (!this.adjacencyList.containsKey(vertex)) {
            return null;
        }
        
        this.adjacencyList.remove(vertex);
        /**
         * remove all edges containing the vertex
         */
        for (E i: this.getAllVertices()) {
            this.adjacencyList.get(i).remove(vertex);
        }
        return vertex;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addEdge(E vertex1, E vertex2) {
    	/**
    	 * if any vertex is null or does not exist, or two vertices are equal
    	 */
        if (vertex1 == null || vertex2 == null) {
            return false;
        }
        if (!this.adjacencyList.containsKey(vertex1) || !this.adjacencyList.containsKey(vertex2)) {
            return false;
        }
        if (vertex1.equals(vertex2)) {
            return false;
        }
        
        this.adjacencyList.get(vertex1).add(vertex2);
        this.adjacencyList.get(vertex2).add(vertex1);
        return true;
    }    

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeEdge(E vertex1, E vertex2) {
    	/**
    	 * if any vertex is null or does not exist, or two vertices are equal
    	 */
        if (vertex1 == null || vertex2 == null) {
            return false;
        }
        if (!this.adjacencyList.containsKey(vertex1) || !this.adjacencyList.containsKey(vertex2)) {
            return false;
        }
        if (vertex1.equals(vertex2)) {
            return false;
        }
        
        this.adjacencyList.get(vertex1).remove(vertex2);
        this.adjacencyList.get(vertex2).remove(vertex1);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAdjacent(E vertex1, E vertex2) {
        if (vertex1 == null || vertex2 == null) {
            return false;
        }
        if (!this.adjacencyList.containsKey(vertex1) || !this.adjacencyList.containsKey(vertex2)) {
            return false;
        }
        if (vertex1.equals(vertex2)) {
            return false;
        }
        
        return this.adjacencyList.get(vertex1).contains(vertex2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<E> getNeighbors(E vertex) {
        if (vertex == null) {
            return null;
        }
        
        if (!this.adjacencyList.containsKey(vertex)) {
            return null;
        }
        
        return this.adjacencyList.get(vertex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<E> getAllVertices() {
        return this.adjacencyList.keySet();
    }
    

}
