

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cerner.immunizationForecast.operationalClasses.ForeCastLogic;

public class DateRelatedTests {
	private Date currentDate,newDate,oldDate;
	private ForeCastLogic fLogic;
	private String type1,type2,type3;
	@Before
	public void setUp() throws Exception {
		fLogic=new ForeCastLogic();
		type1="Normal";
		type2="Urgent";
		type3="Overdue";
		currentDate=new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy",Locale.ENGLISH).parse("Wed Mar 19 00:00:00 IST 2015");
		newDate=new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy",Locale.ENGLISH).parse("Wed Mar 29 00:00:00 IST 2015");
		oldDate=new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy",Locale.ENGLISH).parse("Wed Mar 09 00:00:00 IST 2015");
	}

	@After
	public void tearDown() throws Exception {
		currentDate=null;
		newDate=null;
		oldDate=null;
		type1=null;
		type2=null;
		type3=null;
	}
	
	//this test checks whether the current date is same as the date returned by the now() of the forecast logic
	@Test
	public void testCurrentDate() {
		assertEquals(currentDate,fLogic.now());
	}
	
	//this test checks whether the type returned is same as "Normal"
	@Test
	public void testTypeNormal() {
		assertEquals(type1,fLogic.getTyp(-50));
	}
	
	//this test checks whether the type returned is same as "Urgent"
	@Test
	public void testTypeUrgent() {
		assertEquals(type2,fLogic.getTyp(0));
	}
	
	//this test checks whether the type returned is same as "Urgent"
	@Test
	public void testTypeOverdue() {
		assertEquals(type3,fLogic.getTyp(10));
	}
	
	//this test checks for the correctness of adding 10 days to a date
	@Test
	public void testDateAdd10() {
		assertEquals(newDate,fLogic.addDays(currentDate, 10));
	}
	
	//this test checks for the correctness of adding 0 days to a date
	@Test
	public void testDateAdd0() {
		assertEquals(currentDate,fLogic.addDays(currentDate, 0));
	}
	
	//this test checks for the correctness of deducting 0 days to a date
	@Test
	public void testDysDiff() {
		assertEquals(0,fLogic.daysDiff(currentDate));
	}
	
	//this test checks for the correctness of deducting 10 days from current date
	@Test
	public void testDysDiffNegative() {
		assertEquals(-10,fLogic.daysDiff(newDate));
	}
	
	//this test checks for the correctness of deducting 10 days from current date
	@Test
	public void testDysDiffPositive() {
		assertEquals(10,fLogic.daysDiff(oldDate));
	}
}
