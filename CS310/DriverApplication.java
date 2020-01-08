//Author: Zixiao Guo
//cssc0942
//RedID: 822029189

import java.io.*;
import java.util.ArrayList;

public class DriverApplication {
	public static void main(String[] args) throws IOException {

		String input;	//read lines from input
		String[] name;	//store separated part of input
		String start = null;	//start vertex
		String destination = null;	//destination vertex
		int cost = 0;	//cost
		int heaviestCost = 0;	//store heaviest cost found
		String hasHeaviestCost = null;// Store the edge with heaviest cost
		ArrayList<String> selfBound = new ArrayList<String>();
		//to collect vertex with self bound
		ArrayList<String> zeroOutbound = new ArrayList<String>();
		//to collect vertex with zero outbound
		ArrayList<String> zeroInbound = new ArrayList<String>();
		//to collect the vertex with zero inbound
				
		WeightedDirectedGraph graph = new WeightedDirectedGraph();
		
		try {
			BufferedReader reader=new BufferedReader(new FileReader(args[0]));
			
			while ((input = reader.readLine()) != null) {
				name = input.split(",");
				start = name[0];
				if (name.length == 3) {
					destination = name[1];
					cost = Integer.parseInt(name[2]);
					
					//find the vertex with heaviest cost
					if (cost > heaviestCost) {
						heaviestCost = cost;
						hasHeaviestCost = start + " to " + destination;
						 
					}
					//find vertices with self bound
					if (start.equals(destination)) {
						selfBound.add(start);
					}

				}
				
				//add the vertex and edge to the map
				if (name.length == 1) {
					graph.addVertex(start);
				}
				else if (name.length == 3) {
					graph.addVertex(start);
					graph.addVertex(destination);
					graph.addEdge(start, destination, cost);
				}	
			}
			zeroOutbound = graph.detectOutbound();
			zeroInbound = graph.detectInbound();

			System.out.print("Total number of verticies in the graph: ");
			System.out.println(graph.outermap.size());
			System.out.println("Vertices with zero inbound: " + zeroInbound);
			System.out.println("Vertices with self edges: " + selfBound);
			System.out.println("Vertices with zero outbound: " + zeroOutbound);
			System.out.print("List the edge with heaviest weight: ");
			System.out.println(hasHeaviestCost);
			//trimmed the print part to not exceed the 80 characters wide limit
			
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		
		
		
	}
}
