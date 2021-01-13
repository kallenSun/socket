/**
 * <Description> <br>
 *
 * @author kallensun <br>
 * @CreateDate 2020/04/11 <br>
 */
public class StringUtils {

    public static Boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static Boolean isNotBlank(final CharSequence cs) {
        return !isBlank(cs);
    }
}
