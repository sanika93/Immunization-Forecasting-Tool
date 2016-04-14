import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cerner.immunizationForecast.beans.Forecast;
import com.cerner.immunizationForecast.operationalClasses.TDapLogic;


public class TestTdapLogic {
	
	TDapLogic tdap;

	@Before
	public void setUp() throws Exception {
		tdap=new TDapLogic();
	}

	@After
	public void tearDown() throws Exception {
		tdap=null;
	}

	@Test
	public void testFirstDose() {
		
		
		boolean checkFirstDose=tdap.getFirstDose(10005, 101);
		assertEquals(checkFirstDose,true);
		
	}
	
	@Test
	public void testDtapDose()
	{
		assertEquals(false,tdap.getLastDTapDose(10005).isEmpty());
	}
	
	@Test
	public void checkNoOfForecasts()
	{
		ArrayList<Forecast> TDapForecastList=new ArrayList<>();
		TDapForecastList=tdap.createTdapForecast(10006, 101);
		
		assertEquals(TDapForecastList.size(),4,0);
	}
	
	@Test
	public void testDate()
	{
		boolean result=false;
		Date date=tdap.getAdminDate(10006, 101, 1);
		
		if(date==null)
		{
			result=true;
		}
		
		assertEquals(result,true);
	}
	
	@Test
	
	public void testAddDays()
	{
		Date currentDate=tdap.now();
		
		Date newDate=tdap.addDays(currentDate,2);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String finalDate=sdf.format(newDate);
		
		
		String expectedDate="2015-03-22";
		
		assertEquals(finalDate,expectedDate);
		
		
	}
	
	@Test
	public void testCurrentDate()
	{
		Date currentDate=tdap.now();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String finalDate=sdf.format(currentDate);
		
		String expected="2015-03-20";
		
		assertEquals(finalDate,expected);
	}
	
	@Test
	public void testParameter()
	{
		int vaccineId=101;
		String paramName="Pregnancy";
		
		assertEquals(tdap.getParameterId(vaccineId, paramName),200);
	}
	
	@Test
	public void testtyp()
	{
		String result="Overdue";
		int diff=20;
		assertEquals(tdap.getTyp(diff),result);
	}

	
	
}
