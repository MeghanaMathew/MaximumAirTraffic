package foa_HW4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class compute_MaxFlow {

	public static void main(String[] args) {
		List<Flight> lax = new ArrayList<>();
		List<Flight> sfo = new ArrayList<>();
		List<Flight> bos = new ArrayList<>();
		List<Flight> iad = new ArrayList<>();
		List<Flight> phx = new ArrayList<>();
		List<Flight> sea = new ArrayList<>();
		List<Flight> den = new ArrayList<>();
		List<Flight> atl = new ArrayList<>();
		List<Flight> ord = new ArrayList<>();
		List<Flight> allFlights = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader("flights_new.csv"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				Flight node = new Flight(values[0],values[1],values[2],values[3],values[4],values[5]);
				if(values[0].equals("LAX"))
					lax.add(node);	
				else if(values[0].equalsIgnoreCase("SFO"))
					sfo.add(node);
				else if(values[0].equalsIgnoreCase("ATL"))
					atl.add(node);
				else if(values[0].equalsIgnoreCase("ORD"))
					ord.add(node);
				else if(values[0].equalsIgnoreCase("BOS"))
					bos.add(node);
				else if(values[0].equalsIgnoreCase("PHX"))
					phx.add(node);
				else if(values[0].equalsIgnoreCase("SEA"))
					sea.add(node);
				else if(values[0].equalsIgnoreCase("DEN"))
					den.add(node);
				else if(values[0].equalsIgnoreCase("IAD"))
					iad.add(node); 

				allFlights.add(node);
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
		Flight source = new Flight();
		Flight sink = new Flight();
		
		Map<Flight,Integer> map = new HashMap<>();
		map.put(source,0);
		int count =1;
		for(int i=0;i<allFlights.size();i++) {
			map.put(allFlights.get(i),count);
			count++;
		}
		map.put(sink,count);

		List<List<Integer>> adj = new ArrayList<>();
		for(int i=0;i<allFlights.size()+2;i++) {
			List<Integer> list = new ArrayList<>();
			for(int j=0;j<allFlights.size()+2;j++)
				list.add(0);
			adj.add(list);
		}
		
		for(int i=0;i<lax.size();i++) {
			adj.get(0).set(map.get(lax.get(i)), lax.get(i).max_capa);
		}
		
		int i=1;
		for(Flight node: allFlights) {
			if(node.to.equals("JFK")) {
				adj.get(i).set(adj.size()-1, node.max_capa);

			}
			if(node.to.equals("SFO")) {
				for(int j=0;j<sfo.size();j++) {
					if(timeComparison(node.arrival,sfo.get(j).departure)) {
						adj.get(i).set(map.get(sfo.get(j)), node.max_capa);
					}
				}
			}else if(node.to.equals("PHX")) {
				for(int j=0;j<phx.size();j++) {
					if(timeComparison(node.arrival,phx.get(j).departure)) {
						adj.get(i).set(map.get(phx.get(j)), node.max_capa);
					}
				}
			}else if(node.to.equals("SEA")) {
				for(int j=0;j<sea.size();j++) {
					if(timeComparison(node.arrival,sea.get(j).departure)) {
						adj.get(i).set(map.get(sea.get(j)), node.max_capa);
					}
				}
			}else if(node.to.equals("DEN")) {
				for(int j=0;j<den.size();j++) {
					if(timeComparison(node.arrival,den.get(j).departure)) {
						adj.get(i).set(map.get(den.get(j)), node.max_capa);
					}
				}
			}else if(node.to.equals("ATL")) {
				for(int j=0;j<atl.size();j++) {
					if(timeComparison(node.arrival,atl.get(j).departure)) {
						adj.get(i).set(map.get(atl.get(j)), node.max_capa);
					}
				}
			}else if(node.to.equals("ORD")) {
				for(int j=0;j<ord.size();j++) {
					if(timeComparison(node.arrival,ord.get(j).departure)) {
						adj.get(i).set(map.get(ord.get(j)), node.max_capa);
					}
				}
			}else if(node.to.equals("BOS")) {
				for(int j=0;j<bos.size();j++) {
					if(timeComparison(node.arrival,bos.get(j).departure)) {
						adj.get(i).set(map.get(bos.get(j)), node.max_capa);
					}
				}
			}else if(node.to.equals("IAD")) {
				for(int j=0;j<iad.size();j++) {
					if(timeComparison(node.arrival,iad.get(j).departure)) {
						adj.get(i).set(map.get(iad.get(j)), node.max_capa);
					}
				}
			}
			i++;
		}
		
		//code for ford fulkerson's
		Max_via_Fulkerson m = new Max_via_Fulkerson(); 
		m.V = adj.size();
		System.out.println("The maximum possible flow is " + 
				m.fordFulkerson(adj, 0, adj.size()-1)); 



	}

	public static boolean timeComparison(String arrival,String dept) {
		String[] arrivalArr = arrival.split(":");
		String[] deptArr = dept.split(":");
		//chk hour
		if(Integer.valueOf(arrivalArr[0])>Integer.valueOf(deptArr[0]))
			return false;
		return true;
	}
}
