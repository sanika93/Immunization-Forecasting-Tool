


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.cerner.immunizationForecast.beans.Login;
import com.cerner.immunizationForecast.operationalClasses.ValidateCredentials;

	@RunWith(value = BlockJUnit4ClassRunner.class)
	public class logInTest 
	{
		String un,pw,addr,dt,longPW,longUN,shortUN,shortPW;
		ValidateCredentials vObj;
		Login validUserCreds= new Login();//contains valid user name and password
		Login invalidUserCreds= new Login();//contains invalid user name and password
		Login invalidUserName= new Login();//contains invalid user name and valid password
		Login invalidUserPassword= new Login();//contains valid user name and invalid password
		Login inactiveUser= new Login();//contains valid user name and password of an inactive user
		
		@Before
		public void setUp() throws Exception 
		{
			//Sets the variables needed for the tests
			vObj= new  ValidateCredentials();			
			validUserCreds.setUsername("Gaurav");
			validUserCreds.setPassword("vikings1");
			invalidUserCreds.setUsername("abc");
			invalidUserCreds.setPassword("xyz");
			invalidUserName.setUsername("Gauraav");
			invalidUserName.setPassword("vikings1");
			invalidUserPassword.setUsername("Gaurav");
			invalidUserPassword.setPassword("vikingsmns1");
			inactiveUser.setUsername("inactive");
			inactiveUser.setPassword("somepw");
		}

		@After
		public void tearDown() throws Exception {
			//Destroys the variables once they are no longer needed
			validUserCreds=null;
			invalidUserCreds=null;
			invalidUserName=null;
			invalidUserPassword=null;
			inactiveUser=null;
		}

		//This test checks whether authentication is successful when valid credentials are given 
		@Test
		public void testValidCred() {
			assertTrue(vObj.validateCred(validUserCreds));
		}
		
		//This test checks whether authentication is unsuccessful when invalid credentials are given 
		@Test
		public void testInvalidCred() {
			assertFalse(vObj.validateCred(invalidUserCreds));
		}
		
		//This test checks whether authentication is unsuccessful when invalid username and valid password are given 
		@Test
		public void testInvalidUserName() {
			assertFalse(vObj.validateCred(invalidUserName));
		}
		
		//This test checks whether authentication is unsuccessful when valid username and invalid password are given 
		@Test
		public void testInvalidPassword() {
			assertFalse(vObj.validateCred(invalidUserPassword));
		}
		
		//This test checks whether authentication is unsuccessful when a users active status is false 
		@Test
		public void testInactiveUser() {
			assertFalse(vObj.validateCred(inactiveUser));
		}
}
