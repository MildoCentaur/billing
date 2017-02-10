package ar.com.adriabe.utilities;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ExcelPrintable extends CustomPrintable {

	private Map<Integer, List<Object>> data = null;
	
	public ExcelPrintable(ExcelSerilizer es, Object listing,
			String title, String filepath) {
		data=es.serialize(listing);
		this.setTitle(title);
		this.setFilepath(filepath);
	}
	
	@Override
	public String getDocuemntType() {
		return "Excel";
	}

	@Override
	public Boolean isDocuementReady() {
		return data==null || data.isEmpty();
	}

	public int size() {
		return data.size();
	}

	public boolean isEmpty() {
		return data.isEmpty();
	}

	public List<Object> getRow(Integer key) {
		return data.get(key);
	}

	public Set<Integer> getRows() {
		return data.keySet();
	}

}
