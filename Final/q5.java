package Final;
public class q5 {
    public static void main(String[] args) {
        Integer[] a = {1, 1, 2};
        Integer[] b = {1, 2, 3};
        Integer[] e = {3, 2, 1};
        
        String[] c = {"apple", "pear", "carrot"};
        String[] d = {"pineapple", "apple", "pineapple"};
        
        System.out.println("containsDuplicates for a = " + isSorted(a));
        System.out.println("containsDuplicates for b = " + isSorted(b));
        System.out.println("containsDuplicates for c = " + isSorted(c));
        System.out.println("containsDuplicates for d = " + isSorted(d));
        System.out.println("containsDuplicates for d = " + isSorted(e));
    }
    
    public static int isSorted(Comparable[] list) {
        boolean ascending = true;
        boolean descending = true;
        
        for (int i = 0; i < list.length - 1; i++) {
            int comparison = list[i].compareTo(list[i + 1]);
            if (comparison > 0) {
                ascending = false;
            } else if (comparison < 0) {
                descending = false;
            }
        }
        
        if (ascending) {
            return 1;
        } else if (descending) {
            return 2;
        } else {
            return 0;
        }
    }
}
