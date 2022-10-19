package za.ac.cput.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

public class Helper {


    public static boolean isEmptyOrNull(String str){
        return StringUtils.isEmpty(str);
    }

    public static String setEmptyIfNull(String str){ //Method to make null values have empty Strings instead.
        if (isEmptyOrNull(str)){
            return StringUtils.EMPTY;
        }
        return str;
    }

    public static void checkStringParam(String paramName, String paramValue){ //Ensure that value cannot have null or empty value.
        if (isEmptyOrNull(paramValue))
            throw new IllegalArgumentException(String.format("Invalid value for: %s", paramName));
    }

    public static void checkEmail(String s){ //Method that ensures that emails are valid.
        EmailValidator emailValidator = EmailValidator.getInstance();
        if (!emailValidator.isValid(s))
            throw new IllegalArgumentException("Email not valid");
    }

    public static void checkIfObjectNull(String objectName, Object object){ //Checks object attributes to ensure they are not null.
        if (object == null){
            throw new IllegalArgumentException(String.format("%s is null", objectName));
        }
    }

}
