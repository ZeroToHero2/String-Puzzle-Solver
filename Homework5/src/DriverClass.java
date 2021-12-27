import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DriverClass {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine().trim(); //File Path
        // Scanner fileScanner = new Scanner(new File("C:\\Users\\BAHADIR\\IdeaProjects\\Work\\Homework5\\src\\input1.txt")); static address
        Scanner fileScanner = new Scanner(new File(path));
        Scanner fileScanner1 = new Scanner(new File(path));
        /**
         *  Scanning Initial State From File
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

        int input = scanner.nextInt();
        switch (input) {
            case 1:     //Search
                String searchInput = scanner.next();
                if (Trie.Search(searchInput)) System.out.println("True"); // Search Algorithm Test
                else System.out.println("False");
                break;
            case 2:     //AutoComplete
                String inputWord = scanner.next();
                Queue<String> autoComplete = new Queue<String>();
                autoComplete = (Queue<String>) Trie.AutoComplete(inputWord);
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
                break;
            case 3:     //ReverseAutoComplete //Boş

                //Fill Here

                break;
            case 4: //FullComplete //Eksik
                // !!!!README: NORMALDE GELEN INPUT: th ee => kullanıcıdan alırken boşluklar "." ile değişecek.if(str.charAt(i)==' ') then change it with '.'  --DONE--
                // !----Suffix olarak kontrol eksik ---! <3
                scanner.nextLine();// Consume newline left-over
                String inp = scanner.nextLine();
                int staticInpLength = inp.length();
                for (int i = 0; i < staticInpLength; i++) { //This for converting the th ee to th.ree which can compatible with our fullComplete method.
                    if (inp.charAt(i) == ' ') {
                        inp = inp.substring(0, i) + "." + inp.substring(i + 1, inp.length());
                    }
                }
                System.out.println(inp);//Output Controk
                Queue<String> fullComplete = new Queue<String>();
                fullComplete = (Queue<String>) Trie.FullComplete(inp); //"th.ee"

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
                System.out.println(sb); // FullComplete Algorithm Test*/

                break;
            case 5: //findTopK      -----------!!!!!!!!!!!!  cevap "we" yerine "We" olmalı   !!!!!!!!!!!!-------------------
                int top = scanner.nextInt();
                HashMap<String, Integer> map = new HashMap<String, Integer>();
                while (fileScanner1.hasNextLine()) { //Read File in a different way that specify the number of each "string occurences" into the Hashmap.
                    String[] inputArray;
                    inputArray = fileScanner1.nextLine().split(" ");
                    for (int i = 0; i < inputArray.length; i++) {
                        String dequeuedString = inputArray[i];
                        if (!map.containsKey(dequeuedString)) {
                            map.put(dequeuedString, 1); //Initially every string occurences is one
                        } else {
                            if (!map.isEmpty())
                                map.put(dequeuedString, map.get(dequeuedString) + 1); //If it is already occured increment it's frequency by 1.
                            else map.put(dequeuedString, 1);
                        }
                    }
                }
                Queue<String> temp = (Queue<String>) trieST.keys();
                TopK[] sortArray = new TopK[temp.size()];
                int staticQueueSize = temp.size();
                for (int i = 0; i < staticQueueSize; i++) {
                    String dequeuedString1 = temp.dequeue();
                    TopK text = new TopK(dequeuedString1, map.get(dequeuedString1)); //paralel object in order to print TopK String
                    sortArray[i] = text;
                }
                Arrays.sort(sortArray); //Sort array with Object Reference.

                for (int i = sortArray.length - 1; sortArray.length - (top + 1) < i; i--) {
                    if (i == sortArray.length - top) {
                        System.out.print(sortArray[i].text);
                    } else {
                        System.out.print(sortArray[i].text + ", ");
                    }
                }

                break;
            case 6: //SolvePuzzle //Boş

                //Fill here

                break;
        }


    }
}
//-------------------Trial---------------------------
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
