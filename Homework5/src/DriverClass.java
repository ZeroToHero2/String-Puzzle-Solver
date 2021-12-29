import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DriverClass {
    public static void solvePuzzle(TrieST<Integer> Try, File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        String arr[][] = new String[5][5];
        while (sc.hasNext()) {
            int counter = 0;
            int counter1 = 0;
            if (counter == 4) {
                counter1++;
                counter = 0;
            }
            arr[counter1][counter] = sc.next();
            counter++;

        }
        print(arr);




    }

    private static void print(String[][] arr) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.println(arr[i][j]);

            }

        }


    }


    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        Scanner fileScanner = new Scanner(new File("C:\\Users\\m_722\\IdeaProjects\\Work\\Homework5\\src\\input1.txt"));
        Scanner fileScanner1 = new Scanner(new File("C:\\Users\\m_722\\IdeaProjects\\Work\\Homework5\\src\\input1.txt"));
        /**
         *  Start Scanning Initial State From File
         */

        TST<Integer> Trie = new TST<Integer>();
        TrieST<Integer> trieST = new TrieST<Integer>();
        while (fileScanner.hasNextLine()) {
            String[] inputArray;
            inputArray = fileScanner.nextLine().split(" ");
            for (int i = 0; i < inputArray.length; i++) {
                Trie.put(inputArray[i], ((int) Math.random() * 100) + 1);//Add a value to end of the string from 1 to 100
                trieST.put(inputArray[i], 1);//Add a value to end of the string from 1 to 100
            }
        }


        /**
         *  Start Scanning Initial State From File
         */

        /**
         * Start Search
         */
        System.out.println(Trie.Search("I")); // Search Algorithm Test
        /**
         * End Search
         */

        /**
         *  Start AutoComplete
         */
        Queue<String> autoComplete = new Queue<String>();
        autoComplete = (Queue<String>) Trie.AutoComplete("a");
        StringBuilder s = new StringBuilder();
        for (String item : autoComplete) {
            s.append(item);
            s.append(", ");
        }
        if (!autoComplete.isEmpty()) { //s.isEmpty() VPL error for this expression
            s.deleteCharAt(s.length() - 2);
        } else {
            System.out.println("No words");
        }
        System.out.println(s); // AutoComplete Algorithm Test
        /**
         *  End AutoComplete
         */

        /**
         * Start ReverseAutoComplete
         */


        /**
         *  End ReverseAutoComplete
         */

        /**
         * Start FullComplete
         */
        Queue<String> fullComplete = new Queue<String>();
        fullComplete = (Queue<String>) Trie.FullComplete("th.ee");
        // !!!!README: NORMALDE GELEN INPUT: th ee => kullanıcıdan alırken boşluklar "." ile değişecek.if(str.charAt(i)==' ') then change it with '.'
        // !----Suffix olarak kontrol eksik ---!
        StringBuilder sb = new StringBuilder();
        for (String item : fullComplete) {
            sb.append(item);
            sb.append(", ");
        }
        if (!fullComplete.isEmpty()) { //s.isEmpty() VPL error for this expression
            sb.deleteCharAt(sb.length() - 2);
        } else {
            System.out.println("No words");
        }
        System.out.println(sb); // FullComplete Algorithm Test

        /**
         *  End FullComplete
         */

        /**
         * Start FindTopK  Benmi kod en fazla 3 ü hesaplıyor.kullanıcıdan alınan değerle değiştirilebilir.
         */
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        while (fileScanner1.hasNextLine()) {
            String[] inputArray;
            inputArray = fileScanner1.nextLine().split(" ");
            for (int i = 0; i < inputArray.length; i++) {
                String dequeuedString = inputArray[i];
                if (!map.containsKey(dequeuedString)) {
                    map.put(dequeuedString, 1); //Initially every string occurences is one
                } else {
                    // map.put(dequeuedString, map.get(dequeuedString) + 1);
                    if (!map.isEmpty()) map.put(dequeuedString, map.get(dequeuedString) + 1);
                    else map.put(dequeuedString, 1);
                }
            }
        }
       /* HashMap<String, Integer> map = new HashMap<String, Integer>();
        Queue<String> temp = (Queue<String>) Trie.keys();
        System.out.println(Trie.keys() );
        for (int i = 0; i < temp.size(); i++) {
            String dequeuedString = temp.dequeue();
            if (!map.containsKey(dequeuedString)) {
                map.put(dequeuedString, 1); //Initially every string occurences is one
            } else {
                map.put(dequeuedString, map.get(dequeuedString) + 1);
               /* if (!map.isEmpty()) map.put(dequeuedString, map.get(dequeuedString) + 1);
                else map.put(dequeuedString, 1);*/
        //   }
        //   }
      /*  System.out.println(map.toString());
        Queue<String> temp2 = (Queue<String>) Trie.keys();
        int[] sortArray = new int[temp2.size()];
        for (int i = 0; i < temp2.size(); i++) {
            sortArray[i] = map.get(temp2.dequeue());
        }
        Arrays.sort(sortArray);
        // Assume that K is 3 This will change according to the input that getting from user
        for (int i = 0; i < 3; i++) {
            System.out.println(sortArray[i]);
        }*/
        Queue<String> temp = (Queue<String>) trieST.keys();
        TopK[] sortArray = new TopK[temp.size()];
        int staticQueueSize = temp.size();
        for (int i = 0; i < staticQueueSize; i++) {
            String dequeuedString1 = temp.dequeue();
            TopK text = new TopK(dequeuedString1, map.get(dequeuedString1)); //paralel object in order to print TopK String
            sortArray[i] = text;
        }
        Arrays.sort(sortArray);

        // Assume that K is 3 This will change according to the input that getting from user
        for (int i = sortArray.length - 1; sortArray.length - 3 < i; i--) { // 3 yerine kullanıcıdan gelen değeri koy.
            if (i == sortArray.length - 2) {
                System.out.print(sortArray[i].text);
            } else {
                System.out.print(sortArray[i].text + ", ");
            }
        }
        /**
         *  End FindTopK
         */
    }
}
