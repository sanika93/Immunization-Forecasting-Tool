

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cerner.immunizationForecast.beans.Passwd;
import com.cerner.immunizationForecast.operationalClasses.ResetPassword;

public class ResetPasswordTest {
	private ResetPassword resetPassword;
	private Passwd pwdValidPasswordsBean;//contains valid credentials and new passwords
	private Passwd pwdInValidOrgPasswordBean;//contains invalid existing password
	private Passwd pwdMismatchBean;//contains new password and confirm password that dont match each other
	private Passwd pwdInValidUserNameBean;//contains invalid username
	@Before
	public void setUp() throws Exception 
	{
		//Sets the variables needed for the tests
		resetPassword= new ResetPassword();
		pwdValidPasswordsBean = new Passwd();
		pwdInValidOrgPasswordBean= new Passwd();
		pwdMismatchBean= new Passwd();
		pwdInValidUserNameBean= new Passwd();
		pwdValidPasswordsBean.setUserName("Gaurav");
		pwdValidPasswordsBean.setuId(10000);
		pwdValidPasswordsBean.setOriginalPw("vikings");
		pwdValidPasswordsBean.setNewPw("vikings1");
		pwdValidPasswordsBean.setConfNewPw("vikings1");
		pwdInValidOrgPasswordBean.setUserName("Gaurav");
		pwdInValidOrgPasswordBean.setuId(10000);
		pwdInValidOrgPasswordBean.setOriginalPw("vikings");
		pwdInValidOrgPasswordBean.setNewPw("vikings");
		pwdInValidOrgPasswordBean.setConfNewPw("vikings");
		pwdMismatchBean.setUserName("Gaurav");
		pwdMismatchBean.setuId(10000);
		pwdMismatchBean.setOriginalPw("vikings");
		pwdMismatchBean.setNewPw("vikingsx");
		pwdMismatchBean.setConfNewPw("vikingsy");
		pwdInValidUserNameBean.setUserName("Gauravfsac");
		pwdInValidUserNameBean.setuId(10000);
		pwdInValidUserNameBean.setOriginalPw("vikings");
		pwdInValidUserNameBean.setNewPw("vikings");
		pwdInValidUserNameBean.setConfNewPw("vikings");
	}

	@After
	public void tearDown() throws Exception {
		//Destroys the variables once they are no longer needed
		resetPassword=null;
		pwdInValidOrgPasswordBean=null;
		pwdMismatchBean= null;
		pwdInValidUserNameBean= null;
	}
	
	//this test returns true if all credentials are correct
	@Test
	public void validPasswords() {
		assertTrue(resetPassword.resetPassword(pwdValidPasswordsBean));
	}

	//this test returns false if original password is incorrect
	@Test
	public void inValidOrgPasswords() {
		assertFalse(resetPassword.resetPassword(pwdInValidOrgPasswordBean));
	}
	
	//this test returns false if the confirm password does not match new password
	@Test
	public void passwordsMismatch() {
		assertFalse(resetPassword.resetPassword(pwdMismatchBean));
	}
	
	//this test returns false if the username is wrong
	@Test
	public void userNameWrong() {
		assertFalse(resetPassword.resetPassword(pwdInValidUserNameBean));
	}
}
