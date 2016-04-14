

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cerner.immunizationForecast.operationalClasses.DbOperations;

public class DbOperationsTest {

	private DbOperations dbOps;
	@Before
	public void setUp() throws Exception {
		dbOps=new DbOperations();
	}

	@After
	public void tearDown() throws Exception {
		dbOps=null;
	}
	
	//Checks whether the getVaccineId method returns the right vaccine id when a valid vaccine name is given
	@Test
	public void testGetVaccineIdValidVaccName() {
		assertEquals(100, dbOps.getVaccineId("DTap"));
	}
	
	//Checks whether the getVaccineId method returns the right vaccine id when a invalid vaccine name is given
	@Test
	public void testGetVaccineIdInvalidVaccName() {
		assertEquals(0, dbOps.getVaccineId("ABC"));
	}

	//Checks whether the hasParameter method returns the right result when a valid patient id is given and the patient has parameters set to true
	@Test
	public void testHasParameterTrue() {
		assertTrue(dbOps.hasParameter(10005));
	}
	
	//Checks whether the hasParameter method returns the right result when a invalid patient id is given
	@Test
	public void testHasParameterFalse() {
		assertFalse(dbOps.hasParameter(9000));
	}
	
	//Checks whether the hasParameter method returns the right result when a valid patient id is given and the patient has no parameters set to true
	@Test
	public void testHasParameterEmpty() {
		assertFalse(dbOps.hasParameter(10001));
	}
	
	//Checks whether the getCountOfImmunization method returns the right result when a valid patient id and vaccine id is given
	@Test
	public void testGetImmunizationCountValidPatient() {
		assertEquals(0,dbOps.getCountOfImmunizations(10000, 103));
	}
	
	//Checks whether the getCountOfImmunization method returns the right result when a invalid patient id / vaccine id is given
	@Test
	public void testGetImmunizationCountInValidPatient() {
		assertEquals(0,dbOps.getCountOfImmunizations(9000, 103));
	}
	
	//Checks whether the getAdminDate method returns the right result when a invalid patient id / vaccine id / dose number is given 
	@Test
	public void testGetAdminDateInvalid() {
		assertNull(dbOps.getAdminDate(10000, 103,6));
	}
	
	//Checks whether the getAdminDate method returns the right result when a valid patient id, vaccine id and dose number are given 
	@Test
	public void testGetAdminDateValid() {
		assertNotNull(dbOps.getAdminDate(10000, 100,1));
	}
	
	//Checks whether the getDOB method returns the right result when a invalid patient id is given 
	@Test
	public void testGetDOBInValid() {
		assertNull(dbOps.getDOB(9999));
	}
	
	//Checks whether the getDOB method returns the right result when a valid patient id is given 
	@Test
	public void testGetDOBValid() {
		assertNotNull(dbOps.getDOB(10000));
	}
}
