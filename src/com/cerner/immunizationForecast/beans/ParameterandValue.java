/* Author:Srividya Shastry*/
/* Owner : Cerner		  */
/* Team: Vikings		  */

package com.cerner.immunizationForecast.beans;

public class ParameterandValue {
	private String parameterName  =null;
	private String value = null;
	
	public String getParameterName() {
		return parameterName;
	}
	
	public void setParameterName(String paramterName) {
		this.parameterName = paramterName;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}

}
