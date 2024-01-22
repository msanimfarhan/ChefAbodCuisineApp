package ca.dal.cs.csci3130.a2;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class JUnitTest {
    CredentialValidator validator;

    @Before
    public void setup() {
        validator = new CredentialValidator();
    }

    @Test
    public void checkIfNetIDIsEmpty() {
        assertTrue(validator.isEmptyNetID(""));
        assertFalse(validator.isEmptyNetID("xyz$56"));
    }

    @Test
    public void checkIfNetIDIsValid() {
        assertTrue(validator.isValidNetID("xy882356"));
    }

    @Test
    public void checkIfNetIDIsInvalid() {
        assertFalse(validator.isValidNetID("B88036452"));
        assertFalse(validator.isValidNetID("abc12345"));
    }


    @Test
    public void checkIfEmailIsValid() {
        assertTrue(validator.isValidEmailAddress("abc123@dal.ca"));
    }


    @Test
    public void checkIfEmailIsInvalid() {
        //buggy test code, write an appropriate test!
        assertFalse(1 + 2 == 3);
    }


    @Test
    public void checkIfEmailIsNotFromDAL() {
        assertTrue(validator.isDALEmailAddress("abc.123@dal.ca"));
        assertFalse(validator.isDALEmailAddress("abc.123@usask.ca"));
    }


    @Test
    public void checkIfRoleIsValid() {
        assertTrue(validator.isValidRole("Buyer"));
        assertTrue(validator.isValidRole("Seller"));
    }


    @Test
    public void checkIfRoleIsInvalid() {
        assertFalse(validator.isValidRole("Select your role"));
    }
}