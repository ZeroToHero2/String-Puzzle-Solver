import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class TesterClass {
//-----------------------------------------------------
// Title: Class TesterClass
// Author: Abdusselam koç,Bahadır ünal
// ID: 4931214741,1000776827
// Section: 2,3
// Assignment: 5
// Description: This class tests the all functionalities of the program with different inputs.
//-----------------------------------------------------

    public void test(int input2) throws FileNotFoundException {
        //--------------------------------------------------------
        // Summary: test function to test functionalities of the class with constant inputs.
        // name is given.
        // it takes one parameter which tells the loop to execute required function.
        // Precondition: The program is not tested with different inputs.
        // Postcondition: The program is tested with different inputs
        //--------------------------------------------------------

        Scanner scanner = new Scanner(System.in);
        // Scanner fileScanner = new Scanner(new File("C:\\Users\\BAHADIR\\IdeaProjects\\Work\\Homework5\\src\\input1.txt"));
        String path = "C:\\Users\\m_722\\Desktop\\cmpe 3rd class\\cmpe 343\\hmw5\\Work\\Homework5\\src\\input2.txt"; //File Path
        Scanner fileScanner = new Scanner(new File(path));
        Scanner fileScanner1 = new Scanner(new File(path));
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
        int input = input2;
        switch (input) {
            case 1:     //Search
                String searchInput = "recognition";
                if (Trie.Search(searchInput)) System.out.println("True"); // Search Algorithm Test
                else System.out.println("False");
                break;
            case 2:     //AutoComplete
                String inputWord = "be";
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
                    System.out.println("No word");
                }
                System.out.println(s); // AutoComplete Algorithm Test
                break;
            case 3:     //ReverseAutoComplete /
                Util ut = new Util();
                String userInput = "e";
                ut.ReverseSearch(userInput, trieST);
                break;
            case 4: //FullComplete

                String inp = "ap l ";
                int staticInpLength = inp.length();
                for (int i = 0; i < staticInpLength; i++) { //This for converting the th ee to th.ree which can compatible with our fullComplete method.
                    if (inp.charAt(i) == ' ') {
                        inp = inp.substring(0, i) + "." + inp.substring(i + 1, inp.length());
                    }
                }
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
                    System.out.println("No word");
                }
                System.out.println(sb); // FullComplete Algorithm Test*/

                break;
            case 5: //findTopK
                int top = 3;
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
            case 6: //SolvePuzzle
                String puzzlePath = "C:\\Users\\m_722\\Desktop\\cmpe 3rd class\\cmpe 343\\hmw5\\Work\\Homework5\\src\\puzlle2.txt";
                Util ut1 = new Util();
                ut1.solvePuzzle(trieST, puzzlePath);
                break;
        }


    }


    public static void main(String[] args) throws FileNotFoundException {
        TesterClass test = new TesterClass(); // tester class object.
        System.out.print("Search:  ");
        test.test(1); // testing for input 1.

        System.out.println();
        System.out.print("Auto Complete:   ");
        test.test(2); // testing for input 2.

        System.out.println();
        System.out.print(" Reverse Auto Complete:   ");
        test.test(3); // testing for input 3.

        System.out.println();
        System.out.print("Full Complete:   ");
        test.test(4); // testing for input 4.

        System.out.println();
        System.out.print("Find Top K:   ");
        test.test(5); // testing for input 5.

        System.out.println();
        System.out.print("Solve Puzzle:  ");
        test.test(6); // testing for input 6.
    }


}
