


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.control.TextField;

public class ComicStore {
	private FileWriter writer;

	public ArrayList addCustomer(ArrayList list, String a, String b, String c, String d, String e, String f) {
		
		ArrayList<String> newCust = new ArrayList<String>(); 
        newCust.add(a); 
        newCust.add(b);
        newCust.add(c);
        newCust.add(d);
        newCust.add(e);
        newCust.add(f);
        
        list.add(newCust); 
		
		return list;
		
	}
	
	public void fetchOrder(ArrayList list, String choice) {
		
		for (int i = 0; i < list.size(); i++) {
			for(int j = 0; j<1; j++) {	
				String checker = ((ArrayList) list.get(i)).get(j)+" "+ ((ArrayList) list.get(i)).get(j+1);
				
				if (checker.equalsIgnoreCase(choice)) {
					
					Graphics.editDate.setText((String) ( (ArrayList) list.get(i)).get(2));
					Graphics.editComic.setText((String) ((ArrayList) list.get(i)).get(3));
					Graphics.editPublisher.setText((String) ((ArrayList) list.get(i)).get(4));
					Graphics.editCost.setText((String) ((ArrayList) list.get(i)).get(5));
					
				}
				}	
		
		
	}
		
	
		
		
	}

public ArrayList updateOrder(ArrayList custList,String choice, String a, String b, String c, String d) {
	
	for (int i = 0; i < custList.size(); i++) {
		for(int j = 0; j<1; j++) {	
			String checker = ((ArrayList) custList.get(i)).get(j)+" "+ ((ArrayList) custList.get(i)).get(j+1);
			
			if (checker.equalsIgnoreCase(choice)) {
				((ArrayList) custList.get(i)).set(2,a);
				((ArrayList) custList.get(i)).set(3,b);
				((ArrayList) custList.get(i)).set(4,c);
				((ArrayList) custList.get(i)).set(5,d);
	
				
			}
			}	
	
	
}
	
	
	return custList;
		
	}
	


public ArrayList removeCustomer(ArrayList customerList, String choice) {
	
	for (int i = 0; i < customerList.size(); i++) {
		for(int j = 0; j<1; j++) {	
			String checker = ((ArrayList) customerList.get(i)).get(j)+" "+ ((ArrayList) customerList.get(i)).get(j+1);
			
			if (checker.equalsIgnoreCase(choice)) {
				 customerList.remove(i);
				
	
				
			}
			}	
	
	
}
	
	return customerList;
	
}

public void writeToFile(ArrayList list) {
	try {
		writer = new FileWriter(new File("customers.txt"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	for (int i = 0; i < list.size(); i++) {
		
		
		try {
			String line = list.get(i).toString();
			line = line.replace("[", "");
			line = line.replace("]", "");
			line = line.replace(", ", ",");
			line.trim();
			
			writer.write(String.format(line + "\n"));
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		}try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
}
