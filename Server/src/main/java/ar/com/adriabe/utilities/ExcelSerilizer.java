package ar.com.adriabe.utilities;

import java.util.List;
import java.util.Map;

public abstract class ExcelSerilizer {
	
	public abstract Map<Integer, List<Object>> serialize(Object listing);
}
