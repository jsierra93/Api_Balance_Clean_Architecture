package co.com.jsierra.model.commons;

public class RegularExpression {

    public static final String FORMAT_DATE = "(\\d{4})/(\\d{2})/(\\d{2})";
    public static final String FORMAT_DT = "(\\d{4})-(\\d{2})-(\\d{2})";
    public static final String ALPHABETICAL = "[A-Z]*";
    public static final String NUMERIC = "^[0-9]+$";
    public static final String IPV4 = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.)"
            + "{3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
    public static final String UUID = "[0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\"
            + "-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12}";
    public static final String DATE_TIME = "(\\d{2}):(\\d{2}):(\\d{2})";
    public static final String RANGE_DATE_TIME = "(?:[01]\\d|2[0123]):(?:[012345]\\d):(?:[012345]\\d)";
    public static final String RANGE_DATE= "((?:19|20)\\d\\d)/(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])";
    public static final String RANGE_DT = "([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))";
    public static final String NOT_ZERO = "^[1-9][0-9]*$";
    public static final String ALPHANUMERIC = "^[a-zA-Z0-9]*$";

    public RegularExpression(){
        throw new IllegalStateException("Utility Class");
    }
}
