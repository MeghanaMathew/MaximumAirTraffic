package foa_HW4;

import java.util.*;

class Flight{
	String airline_name;
	String departure;
	String arrival;
	String from;
	String to;
	String flight_no;
	int max_capa;
	private static Map<String,Integer> capa_Map;
	
	Flight(String from, String to, String departure, String arrival,String flight_no,String name){
		this.airline_name = name;
		this.departure = departure;
		this.arrival = arrival;
		this.from = from;
		this.to = to;
		this.flight_no = flight_no;
		get_Max_Capa(flight_no);
	}
	Flight(){}
	
    static{
     capa_Map= new HashMap<>();
     capa_Map.put("a319",128);
     capa_Map.put("a220",105);
     capa_Map.put("a319",128);
     capa_Map.put("a320", 150);
     capa_Map.put("a321", 185);
     capa_Map.put("a321neo",196);
     capa_Map.put("a330", 230);
     capa_Map.put("a330-200", 230);
     capa_Map.put("a330-300",290);
     capa_Map.put("a330-900neo",280);
     capa_Map.put("a350",300);
     capa_Map.put("a350-900",300);
     capa_Map.put("717",110);
     capa_Map.put("717-200",110);
     capa_Map.put("737",126);
     capa_Map.put("737-700",126);
     capa_Map.put("737-800",165);
     capa_Map.put("737-900",180);
     capa_Map.put("737-900er",180);
     capa_Map.put("737-max 9",180);
     capa_Map.put("757",180);
     capa_Map.put("757-200",180);
     capa_Map.put("757-300",230);
     capa_Map.put("767",200);
     capa_Map.put("767-300",200);
     capa_Map.put("767-300er",225);
     capa_Map.put("767-400er",240);
     capa_Map.put("777",270);
     capa_Map.put("777-200",270);
     capa_Map.put("777-200er",270);
     capa_Map.put("777-200lr",280);
     capa_Map.put("777-300",300);
     capa_Map.put("787",235);
     capa_Map.put("787-8",235);
     capa_Map.put("787-9",280);
     capa_Map.put("embraer 170",72);
     capa_Map.put("embraer 175",78);
     capa_Map.put("embraer 190",100);
     capa_Map.put("mcdonnell douglas md - 88",150);
     capa_Map.put("mcdonnell douglas md -88",150);
     capa_Map.put("mcdonnell douglas md - 90",150);
     capa_Map.put("mcdonnell douglas md -90",150);
     capa_Map.put("mcdonnell douglas md - 90-30",150);
     capa_Map.put("crj",75);
     capa_Map.put("crj",75);
             }

	public void get_Max_Capa(String type){
	      String rtype=null;
	     type=type.toLowerCase();  
	         if(type.contains("douglas")||type.contains("md"))
	             rtype="mcdonnell douglas md ".concat(type.substring(type.indexOf("-")));
	         else if(type.startsWith("airbus"))
	             rtype=type.substring(7);
	         else if(type.startsWith("canadair regional jet"))
	             rtype="crj";
	         else if(type.startsWith("boeing"))
	             rtype=type.substring(7);
	         else
	             rtype=type;
	         this.max_capa=capa_Map.getOrDefault(rtype,180);
	}
}