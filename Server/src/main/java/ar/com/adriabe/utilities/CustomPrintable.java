package ar.com.adriabe.utilities;


public abstract class CustomPrintable {
	
	private String title;
	private String filepath;
	
	
	public abstract String getDocuemntType();
	
	public abstract Boolean isDocuementReady();
	
	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * Agrega al final de la palabra tantos espacios como sea necesario para
	 * completar la longitud deseada
	 * 
	 * @param str
	 * @param wantedLength
	 * @return
	 */
	public  String completeWithSpaces(String str, int wantedLength) {
		StringBuffer sb = new StringBuffer(str);
		if (str.length() == wantedLength) {
			return str;
		}
		if (str.length() > wantedLength) {
			return str.substring(0, wantedLength - 1);
		}
		for (int i = 0; i < (wantedLength - str.length()); i++) {
			sb.append(' ');
		}
		return sb.toString();
	}
	
	public  String preAppendWithSpaces(String str, int wantedLength) {
		if (str.length() == wantedLength) {
			return str;
		}
		if (str.length() > wantedLength) {
			return str.substring(0, wantedLength - 1);
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < (wantedLength - str.length()); i++) {
			sb.append(' ');
		}
		sb.append(str);
		return sb.toString();
	}

	
	
}
