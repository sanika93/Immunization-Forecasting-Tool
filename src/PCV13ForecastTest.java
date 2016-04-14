

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cerner.immunizationForecast.operationalClasses.ForeCastLogic;

public class PCV13ForecastTest {
	
	private ForeCastLogic fLogic; 
	@Before
	public void setUp() throws Exception {
		fLogic=new ForeCastLogic();
	}

	@After
	public void tearDown() throws Exception {
		fLogic=null;
	}

	//Tests the correctness of forecast for a person who has never been immunized
	@Test
	public void testForecastUnimmunizedPerson() {
		assertEquals(5,fLogic.createPCV13Forecast(10000, 103).size());
	}
	
	//Tests the correctness of forecast for a person who has been immunized one
	@Test
	public void testForecastImmunizedOnce() {
		assertEquals(3,fLogic.createPCV13Forecast(10001, 103).size());
	}
	
	
	//Tests the correctness of forecast for a person who has been immunized twice
	@Test
	public void testForecastImmunizedTwice() {
		assertEquals(2,fLogic.createPCV13Forecast(10002, 103).size());
	}
	
	//Tests the correctness of forecast for a person who has been immunized thrice
	@Test
	public void testForecastImmunizedThrice() {
		assertEquals(1,fLogic.createPCV13Forecast(10014, 103).size());
	}
	
	
	//Tests the correctness of forecast for a person who has been immunized four times
	@Test
	public void testForecastImmunizedFour() {
		assertEquals(0,fLogic.createPCV13Forecast(10016, 103).size());
	}
}
