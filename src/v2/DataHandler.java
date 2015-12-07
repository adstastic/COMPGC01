package v2;

public interface DataHandler {
	
	void add(Object o);
	
	void remove(Object o);
	
	Object getAtIndex(Integer index);
	
	void read();
		
	void write();
	
}