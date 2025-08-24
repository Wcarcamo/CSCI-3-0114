package practice;
import java.util.ArrayList;
import java.util.Arrays;

public class q1 {
    public ArrayList<String> myList;
        
    public void main(String[] args) {
        myList = new ArrayList<String>(
            Arrays.asList("cat", "mouse", "frog", "dog", "dog")
        );
        System.out.println(numWordsOfLength(5));
        System.out.println(numWordsOfLength(3));
        System.out.println(numWordsOfLength(2));

        removeWordsOfLength(5);
        print();
        removeWordsOfLength(3);
        print();
        removeWordsOfLength(2);
        print();
    }

    public int numWordsOfLength(int len) {
        int count = 0;
        for (String item : myList) {
            if (item.length() == len) {
                count += 1;
            }
        }
        return count;
    }

    public void removeWordsOfLength(int len) {
        // int index = 0;
        
        // while (index < myList.size()) {
        //     String item = myList.get(index);

        //     if (item.length() == len) {
        //         myList.remove(index);
        //         index = 0;
        //     } else {
        //         index += 1;
        //     }
        // }
        for(int i = 0; i < myList.size(); ++i) {
            if (myList.get(i).length() == len) {
                myList.remove(i);
                --i;
            }
        }
    }

    public void print() {
        for (String item : myList) {
            System.out.println(item);
        }
    }
}
