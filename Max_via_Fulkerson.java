package foa_HW4;

import java.util.*; 

public class Max_via_Fulkerson {

	static int V; 
	boolean breadth_First_Search(int[][] rGraph, int s, int t, int parent[]) 
	{ 
		
		boolean visited[] = new boolean[V]; 
		for(int i=0; i<V; ++i) 
			visited[i]=false; 

		LinkedList<Integer> queue = new LinkedList<Integer>(); 
		queue.add(s); 
		visited[s] = true; 
		parent[s]=-1; 

		while (queue.size()!=0) 
		{ 
			int u = queue.poll(); 

			for (int v=0; v<V; v++) 
			{ 
				if (visited[v]==false && rGraph[u][v] > 0) 
				{ 
					queue.add(v); 
					parent[v] = u; 
					visited[v] = true; 
				} 
			} 
		} 

		return (visited[t] == true); 
	} 


	int fordFulkerson(List<List<Integer>> graph, int s, int t) 
	{ 
		int i, j; 
		int residual[][] = new int[V][V]; 
		
		for (i = 0; i < V; i++) 
		{	for (j = 0; j < V; j++) 
			{	residual[i][j] = graph.get(i).get(j); 
			//System.out.print(residual[i][j]+" ");
			}
		//System.out.println();
		}

		int max_flow = 0;
		int parent[] = new int[V]; 
		while (breadth_First_Search(residual, s, t, parent)) 
		{ 

			int path_flow = Integer.MAX_VALUE; 
			for (j=t; j!=s; j=parent[j]) 
			{ 
				i = parent[j]; 
				path_flow = Math.min(path_flow, residual[i][j]); 
			} 

			for (j=t; j != s; j=parent[j]) 
			{ 
				i = parent[j]; 
				residual[i][j] -= path_flow; 
				residual[j][i] += path_flow; 
			} 

			max_flow += path_flow; 
		} 
		return max_flow; 
	} 
} 