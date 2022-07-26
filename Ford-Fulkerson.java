import java.util.Deque;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;

public class Ford-Fulkerson{

    public static int solution(int[] entrances, int[] exits, int[][] path) {
    	
    	int total = 0; 
    		
    	// Ford-Fulkerson Algorithm to calculate max flow
    	
    	// Build graph including a dummy node at the beginning (source) and at the end (sink)
    	int[][] graph = buildGraph(entrances, exits, path); 
    	int[][] residualGraph = new int[graph.length][graph.length];  
    	
    	while (true) {
    		
    		Set<Integer> visited = new HashSet<>(); 
    		Deque<Integer> stackPath = new LinkedList<Integer>();
    		
    		int min = dfs (0, graph.length-1, graph, stackPath, visited); 
    		if (min == 0) {
    			break; 
    		}
    		
    		total += min; 
    		
    		int previous  = stackPath.pollLast(); 
    		while(!stackPath.isEmpty()) {
    			int current = stackPath.pollLast(); 
    			
    			graph[previous][current] = Math.max(0, graph[previous][current]-min); 
    			graph[current][previous] += min;
    			residualGraph[previous][current] += min; 
    			
    			previous = current; 
    			
    		}
    			
    	}
    	
    	return total; 

    }
    
    // Depth Search First algorithm to find all the paths
    private static int dfs (int nodeFrom, int nodeExit, int[][] graph, Deque<Integer> stackPath, Set<Integer> visited) {
    	
    	// if end of path
    	if (nodeFrom == nodeExit) {
    		stackPath.push(nodeFrom);
    		return Integer.MAX_VALUE; 
    	}
    	
    	if (visited.contains(nodeFrom)) {
    		return 0; 
    	}
    	
    	visited.add(nodeFrom); 
    	
    	for (int i = 0; i<graph.length; i++) {
    		
    		if (graph[nodeFrom][i] == 0) {
    			continue; 
    		}
    		int min = dfs(i, nodeExit, graph, stackPath, visited); 
    		
    		if (min > 0) {
    			stackPath.add(nodeFrom); 
    			return Math.min(min, graph[nodeFrom][i]); 
    		}
    	}
    	
    	visited.remove(nodeFrom); 
    	
    	return 0; 
    }
    
    // graph including a dummy node at the beginning (source) and at the end (sink)
    private static int[][] buildGraph(int[] entrances, int[] exits, int[][] path){
    	
    	int[][] graph = new int[path.length+2][path.length+2]; 
    	
    	// connections from the source
    	for (int entrance : entrances) {
    		graph[0][entrance+1] = Integer.MAX_VALUE; 
    	}
    	
    	// connections to the sink
    	for (int exit : exits) {
    		graph[exit+1][graph.length-1] = Integer.MAX_VALUE; 
    	}
    	
    	// rest of the graph
    	for (int i = 0; i< path.length; i++) {
    		for (int j = 0; j<path.length; j++) {
    			graph[i+1][j+1] = path[i][j]; 
    		}
    	}
    	
    	return graph;     	
    }
    
    
	   
	   

	public static void main(String[] args) {
		
		System.out.println(foobar2.solution9(new int[]{0, 1}, new int[] {4,5}, new int[][] {{0, 0, 4, 6, 0, 0}, {0, 0, 5, 2, 0, 0}, {0, 0, 0, 0, 4, 4}, {0, 0, 0, 0, 6, 6}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}}));
		System.out.println(foobar2.solution9(new int[]{0}, new int[] {3}, new int[][] {{0, 7, 0, 0}, {0, 0, 6, 0}, {0, 0, 0, 8}, {9, 0, 0, 0}}));

	}


}
