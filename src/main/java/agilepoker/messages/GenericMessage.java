package agilepoker.messages;

import java.util.HashMap;
import java.util.Map;

public class GenericMessage {
	private String[] names;
	private Object[] values;
	
	public void setNames(String[] names) {
		this.names = names;
	}
	
	public String[] getNames() {
		return names;
	}
	
	public void setValues(Object[] values) {
		this.values = values;
	}
	
	public Object[] getValues() {
		return values;
	}
	
	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < names.length; i++) {
			map.put(names[i], values[i]);
		}
		return map;
	}
}
