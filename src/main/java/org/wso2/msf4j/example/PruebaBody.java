package org.wso2.msf4j.example;

public class PruebaBody {
	private String cosa;

	
	public PruebaBody(String cosa) {
		super();
		this.cosa = cosa;

	}

	public String getCosa() {
		return cosa;
	}

	public void setCosa(String cosa) {
		this.cosa = cosa;
	}

	
	@Override
	public String toString() {
		return "PruebaBody [cosa="+cosa+"]";
	}


}
