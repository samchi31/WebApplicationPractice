package testpackage;

import java.util.HashMap;
import java.util.Map;

public class ParamMap extends HashMap<String, Object>{
	
	private static Map<String, Object> paramMap;
	
	private ParamMap() {}
	
	public Map<String, Object> init() {
		paramMap = new HashMap<String, Object>();
		return paramMap;
	}
	
	public String getString(String key) {
		return (String) paramMap.get(key);
	}
	
	public Integer getInt(String key) {
		return (Integer) paramMap.get(key);
	}
	
	public <T> T get(String key, T ret) {
		return null;
	}
}
