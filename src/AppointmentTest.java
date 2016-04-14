import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.cerner.immunizationForecast.beans.ViewAppointment;
import com.cerner.immunizationForecast.operationalClasses.DoctorOperations;


public class AppointmentTest {
	
	private DoctorOperations docOps;
	
	private ArrayList<ViewAppointment> view=new ArrayList<>();
	
	public void setUp()
	{
		docOps=new DoctorOperations();
		
	}
	
	public void tearDown()
	{
		docOps=null;
	}

	//Test to check if any appointments are present for the doctor
	@Test
	public void testIfAppointmentsThere() {
		view=docOps.getDoctorAppointments(10007);
		assertEquals(2,view.size());
		
		
		
	}

}
