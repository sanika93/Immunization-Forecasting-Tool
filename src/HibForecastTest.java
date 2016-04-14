

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cerner.immunizationForecast.operationalClasses.ForeCastLogic;

public class HibForecastTest {

	private ForeCastLogic fLogic;
	@Before
	public void setUp() throws Exception {
		fLogic=new ForeCastLogic();
	}

	@After
	public void tearDown() throws Exception {
		fLogic=null;
	}
	
	//Checks whether the logic is correct when a person has sickle disease and has been immunized once
	@Test
	public void testHasParameterTrue() {
		assertEquals(3,fLogic.createHibForecast(10005,102).size());
	}
	
	//Checks whether the logic is correct when a person has not been immunized even once and ages is > 1 year (Part Of Catch Up)
	@Test
	public void testFinalDose() {
		assertEquals(1,fLogic.createHibForecast(10012,102).size());
	}

	//Checks whether the logic is correct for a new born baby
	@Test
	public void testNewBorn() {
		assertEquals(4,fLogic.createHibForecast(10013,102).size());
	}
	
	//Checks whether the logic is correct for a person who has been immunized once and hasn't had any other immunization even if recommended(Part Of Catch Up)
	@Test
	public void testSingleImm() {
		assertEquals(2,fLogic.createHibForecast(10014,102).size());
	}
	
	//Checks whether the logic is correct for a person who has been immunized once has normal recommendations
	@Test
	public void testSingleImmNormal() {
		assertEquals(2,fLogic.createHibForecast(10015,102).size());
	}
	
}
