package ca.dal.cs.csci3130.a2;

import androidx.core.util.PatternsCompat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CredentialValidator {
    protected boolean isEmptyNetID(String netID) {
        return netID.isEmpty();
    }

    protected boolean isValidNetID(String netID) {
        if (netID != null && netID.length() == 8) {
            if (Character.isLetter(netID.charAt(0)) && Character.isLetter(netID.charAt(1))) {
                for (int i = 2; i < 8; i++) {
                    if (!Character.isDigit(netID.charAt(i))) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    protected boolean isValidEmailAddress(String emailAddress) {
        if(emailAddress == null){
            return false;
        }

        String regexEmail = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        Pattern pattern = Pattern.compile(regexEmail);
        Matcher matcher = pattern.matcher(emailAddress);
        return matcher.matches();
    }

    protected boolean isDALEmailAddress(String emailAddress) {
        return emailAddress.endsWith("@dal.ca");
    }

    protected boolean isValidRole(String role) {
        //buggy method, fix it!
        return false;
    }
}
