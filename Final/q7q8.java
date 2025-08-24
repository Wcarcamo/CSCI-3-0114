package Final;
public class q7q8 {
    public static void main(String[] args) {
        int i;
        String str = "hello";
        try {
            i = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            i = -1;
        }
    }

    public static String mystery(String s) {
        char[] temp = new char[1];
        if (s.equals(""))
            return "";
        if ("aeiouAEIOU".indexOf(s.charAt(0)) != -1) {
            temp[0] = s.charAt(0);
            return new String(temp) + mystery(s.substring(1));
        } else
            return "" + mystery(s.substring(1));
    }
}



