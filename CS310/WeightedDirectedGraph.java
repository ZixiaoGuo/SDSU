//Author: Zixiao Guo
//cssc0942
//RedID: 822029189
import java.util.*;
import java.io.*;

class WeightedDirectedGraph {
	
	protected Map<String, Map<String, Integer>> outermap = new HashMap<>();
	//String in the outermap is the source
	// string in the innermap is the destination
	protected Map<String, String> inBound = new HashMap<>();
	//map that I use to find vertices with 0 inbound

	
	protected boolean addEdge(String source, String dest, int cost) {
		if (!outermap.containsKey(source) || !outermap.containsKey(dest)) {
			return false;
		}
		else {
			Map<String, Integer> edge = new HashMap<String, Integer>();
			edge = outermap.get(source);
			edge.put(dest, cost);
			
			
			inBound.put(dest, source);
			return true;
		}


	}
	
	protected boolean removeEdges (String source, String destination) {
		if (!outermap.containsKey(source)||!outermap.containsKey(destination))
			return false;
		else {
			outermap.get(source).remove(destination);
			return true;
		}
		
	}
	
	protected void addVertex (String name) {
		if (!outermap.containsKey(name)) {
		outermap.put(name, new HashMap<String, Integer>());
		}
	}
	
	protected boolean removeVertex (String name) {
		if (!outermap.containsKey(name)) {
			return false;
		}
		else {
			outermap.remove(name);
			return true;
		}
		
	}
	
	protected boolean contains (String name) {
		
		return outermap.containsKey(name);
	}
	
	protected boolean contains (String source, String dest) {
		
		return outermap.get(source).containsKey(dest);
	}
	
	protected ArrayList<String> detectOutbound () {
		ArrayList<String> foundVertex = new ArrayList<String>();
		//created this arraylist to collect vertices with 0 outbound val
		for (Map.Entry mapElement : outermap.entrySet()) {
			String key = (String) mapElement.getKey();
			if (outermap.get(key).isEmpty())
				foundVertex.add(key);
		}
		return foundVertex;
		
	}
	
	protected ArrayList<String> detectInbound() {
		ArrayList<String> zeroInbound = new ArrayList<String>();
		//collect vertices with 0 outbound
		for(String element : outermap.keySet()) {
			if (!inBound.containsKey(element)) {
				zeroInbound.add(element);
			}
		}
		
		return zeroInbound;
	}
}
