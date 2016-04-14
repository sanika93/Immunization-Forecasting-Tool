/* Author:Srividya Shastry*/
/* Owner : Cerner		  */
/* Team: Vikings		  */

package com.cerner.immunizationForecast.operationalClasses;

import java.util.ArrayList;

import com.cerner.immunizationForecast.beans.ParameterandValue;
import com.cerner.immunizationForecast.beans.Patient_Parameter;

public class ParameterOperations 
{
	//Function that returns a list of  health conditions taking into consideration the gender of the patient.
	//For eg,some health conditions like pregnancy cannot correspond to a male user.
	
	public ArrayList<ParameterandValue> getListBasedOnGenderForView(char gender,ArrayList<String> allParameters,ArrayList<Patient_Parameter> patientParamList)
	{
		ParameterandValue paramValueBean =null;
		String parameterName = null;
		char valueOfParameter = '\0';
		int indexOfPatientParamList=0;
		
		ArrayList<ParameterandValue> parametersAndValueforUserDisplay= new ArrayList<ParameterandValue>();		//List that holds the health condition along with the value(Yes or No) as selected by the particular patient
		
				
        for(int indexOfAllParameters=0;indexOfAllParameters<allParameters.size();indexOfAllParameters++)
        {
        		paramValueBean = new ParameterandValue();
        	        		
        		parameterName = allParameters.get(indexOfAllParameters);													//Get a health condition from the list of all health conditions
        		
        		//Add all health conditions to the bean, and add "Pregnancy" only if user is female
           		if((parameterName.equals("Pregnancy") && (gender == 'f')) || !(parameterName.equals("Pregnancy")))
        		{
           			valueOfParameter = patientParamList.get(indexOfPatientParamList).getValue();
           			paramValueBean.setParameterName(allParameters.get(indexOfAllParameters)); 
           			indexOfPatientParamList++;
        		}	
        		else
        				continue;
           		
           		paramValueBean.setValue("No");																			//If  the user had selected it, store it as a true.
        		if(valueOfParameter == 't')																	
        		{
        			paramValueBean.setValue("Yes");
        		}
        		
        		parametersAndValueforUserDisplay.add(paramValueBean);
         }
        
        return parametersAndValueforUserDisplay;
	}
	
	//Function that returns the list of health conditions to be asked based on gender of person. This method is used when the user enters his details
	
	public ArrayList<String> getListBasedOnGenderForAdd(char gender,ArrayList<String> allParameters)
	{
		ArrayList<String> listToBeDisplayedBasedOnGender  = new ArrayList<String>();		//Final list of health conditions to be asked to the user
		
		String parameter = null;
		
		if(gender == 'f')																	//Return all parameters if user is female		
		{
			return allParameters;
		}
		else
		{
			for(int i=0;i<allParameters.size();i++)					//since the user is male,add all parameters except "Pregnancy" to another arraylist
			{
				parameter = allParameters.get(i);
				
				if(!(parameter.equals("Pregnancy")))
				{
					listToBeDisplayedBasedOnGender.add(allParameters.get(i));
				}
			}	
		}
		
		return listToBeDisplayedBasedOnGender;
	}
	
	
	public int storeResultBasedOnGender(int patientID,char gender,ArrayList<String> allParameters,String[] selectedParameters)
	{
		int parameterID= 0;
		String parameterName = null;
		int numberOfSuccessfulInserts = 0;
		int index=0;
		
		Patient_Parameter patientParameterBean  = new Patient_Parameter();
		PatientHandler objOfPatientHandler = new PatientHandler();
		DbOperations objOfDbOperations = new DbOperations();
				
    	for (int i = 0; i < allParameters.size(); i++)						//Setting the bean
		{           		
    		parameterName = allParameters.get(i);
    		parameterID = objOfDbOperations.getParameterID(parameterName);
   			patientParameterBean.setParameterID(parameterID);
    		patientParameterBean.setPatientID(patientID);
    		patientParameterBean.setValue('f');
    		
    		if(index<selectedParameters.length)
    		{
    			if(selectedParameters[index].equals(parameterName))
    			{
    				patientParameterBean.setValue('t');
    				index++;
    			}
    		}
  	
    		if(parameterName.equals("Pregnancy"))
    		{
    			if(gender == 'f')																//Add "Pregnancy" to the bean only if user is female
    			{
    				if(objOfPatientHandler.storePatientParameter(patientParameterBean))
    	    		{
    					numberOfSuccessfulInserts++;
    	    		}
    			}
    			else
    				continue;
    		}
    		else
    		{
    			if(objOfPatientHandler.storePatientParameter(patientParameterBean))
    				
	    		{
    				numberOfSuccessfulInserts++;
	    		}
    		}
    	}
    	return numberOfSuccessfulInserts;
	}
	
	public boolean toCheckIfArrayHasTheValue(String[] arr, String targetValue)
	{
		for(String s: arr){
			if(s.equals(targetValue))
				return true;
		}
		return false;
	}
	
	public int storeAllParametersFalse(ArrayList<String> parametersToStore,int patientID)

    {

            int parameterID= 0;

            String parameterName = null;

            int numberOfSuccessfulInserts = 0;

            int index=0;

            

            Patient_Parameter patientParameterBean  = new Patient_Parameter();

            PatientHandler objOfPatientHandler = new PatientHandler();

            DbOperations objOfDbOperations = new DbOperations();

            

            for (index = 0; index < parametersToStore.size(); index++)                                              //Setting the bean

            {                       

            parameterName = parametersToStore.get(index);

            parameterID = objOfDbOperations.getParameterID(parameterName);

                    patientParameterBean.setParameterID(parameterID);

            patientParameterBean.setPatientID(patientID);

            patientParameterBean.setValue('f');     

            

            if(objOfPatientHandler.storePatientParameter(patientParameterBean))

            {

                            numberOfSuccessfulInserts++;

            }

            

    }

    return numberOfSuccessfulInserts;

    }


}
