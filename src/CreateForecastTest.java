

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cerner.immunizationForecast.beans.Forecast;
import com.cerner.immunizationForecast.operationalClasses.ForeCastLogic;

public class CreateForecastTest {
	private int pId;
	private int vId;
	private int newPId;
	private ForeCastLogic fLogic;
	private ArrayList<Forecast> dTapForecastList;
	@Before
	public void setUp() throws Exception 
	{
		//Sets the variables needed for the tests
		pId=10000;//Patients id
		vId=100;//Vaccine id
		newPId=10025;//New users Patient Id
		fLogic = new ForeCastLogic();
		ArrayList<Forecast> dTapForecastList = new ArrayList<Forecast>();
		dTapForecastList.clear();
	}

	@After
	public void tearDown() throws Exception {
		//Destroys the variables once they are no longer needed
		pId=0;
		vId=0;
		newPId=0;
		fLogic=null;
		dTapForecastList=null;		
	}
	//checks whether the create forecast logic return a list with 2 records as the patient has been administered 3 doses out of 5
	@Test
	public void testForecastedListSize() {
		dTapForecastList=fLogic.createDtapForecast(pId, vId);//this method returns the forecast list
		assertEquals(2, dTapForecastList.size());
		dTapForecastList.clear();
	}
	
	//checks whether the create forecast logic return a list with 5 records as the patient has not been administered any doses out of 5
	@Test
	public void testForecastedListSizeNewUser() {
		dTapForecastList=fLogic.createDtapForecast(newPId,vId);//this method returns the forecast list
		assertEquals(5, dTapForecastList.size());
		dTapForecastList.clear();
	}
}
