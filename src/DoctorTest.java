import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.cerner.immunizationForecast.beans.Doctor;
import com.cerner.immunizationForecast.beans.NewUser;
import com.cerner.immunizationForecast.operationalClasses.DoctorOperations;
import com.cerner.immunizationForecast.operationalClasses.LogDetails;



public class DoctorTest {
	
	private DoctorOperations docOps;
	private ArrayList<Doctor> doc;
	private NewUser newUser;
	private LogDetails login;

	
	
	public void setUp()
	{
		 docOps=new DoctorOperations();
		 doc=new ArrayList<Doctor>();
		 newUser=new NewUser();
		 newUser.setUsername("Abhijeet");
		 newUser.setPassword("Abhijeet");
		 newUser.setType(0);
		 login=new LogDetails();
		
	}
	
	public void tearDown()
	{
		docOps=null;
	}
	
	
	
	
	public void checkIfDetailsInserted() {
		
			boolean addUser=login.storeLoginDetails(newUser);
			if(addUser==true)
			{
			int userId=login.getLoginDetails("Abhijeet");
		    String active="t";
			assertTrue(docOps.insertPatientDoctorDetails(userId,10008,active));
			}
			
		}
	@Test
	public void checkIfDoctorNamesAreReturned()
	{
		boolean result=false;
		doc=docOps.getDoctorNames();
		if(doc!=null)
		{
			result=true;
		}
		assertEquals(true,result);
	}
	
	

}
