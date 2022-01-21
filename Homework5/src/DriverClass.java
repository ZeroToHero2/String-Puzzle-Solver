import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

//-----------------------------------------------------
// Title: Util Class
// Author: Abdusselam koç,Bahadır ünal
// Description: This class creates the matrix and and finds word according to search and finds reverseAutocomplete.
//-----------------------------------------------------


class Util {

    // Rows and columns in the given grid
    int R, C;
    // For searching in all 8 direction
    int[] x = {-1, -1, -1, 0, 0, 1, 1, 1};
    int[] y = {-1, 0, 1, -1, 1, -1, 0, 1};
    // This function searches in all
    // 8-direction from point
    // (row, col) in grid[][]
    boolean search2D(char[][] grid, int row, int col, String word) {
        //--------------------------------------------------------
        // Summary: A helper method for searching the puzzle. It  searches the puzzle horizontally, vertically and diagonally.
        // name is given.
        // Precondition: Whether the word is in the puzzle or not is unknown.
        // Postcondition: Whether the word is in the puzzle or not is known.
        //--------------------------------------------------------

        // If first character of word
        // doesn't match with
        // given starting point in grid.
        if (grid[row][col] != word.charAt(0)) {
            return false;
        }
        int len = word.length();

        // Search word in all 8 directions
        // starting from (row, col)
        for (int dir = 0; dir < 8; dir++) {
            // Initialize starting point
            // for current direction
            int k, rd = row + x[dir], cd = col + y[dir];
            // First character is already checked,
            // match remaining characters
            for (k = 1; k < len; k++) {
                // If out of bound break
                if (rd >= R || rd < 0 || cd >= C || cd < 0) break;
                // If not matched, break
                if (grid[rd][cd] != word.charAt(k)) break;
                // Moving in particular direction
                rd += x[dir];
                cd += y[dir];
            }
            // If all character matched,
            // then value of must
            // be equal to length of word
            if (k == len) return true;
        }
        return false;
    }

    // Searches given word in a given
    // matrix in all 8 directions
    boolean patternSearch(char[][] grid, String word) {
        //--------------------------------------------------------
        // Summary: searches a words in the puzzle.
        // name is given.
        // Precondition: Whether the word is in the puzzle or not is unknown.
        // Postcondition: Whether the word is in the puzzle or not is known.
        //--------------------------------------------------------

        // Consider every point as starting
        // point and search given word
        boolean check = false;
        for (int row = 0; row < R; row++) {
            for (int col = 0; col < C; col++) {
                if (search2D(grid, row, col, word)) check = true;
            }
        }
        return check;
    }


    public void createArray(TrieST trieST, char[][] arr) {
        //--------------------------------------------------------
        // Summary: Searches for the every word in the trie from puzzle.
        // name is given.
        // Precondition: Whether the word is in the puzzle or not is unknown.
        // Postcondition: Whether the word is in the puzzle or not is known.
        //--------------------------------------------------------
        R = arr.length;
        C = arr.length;

        ArrayList<String> list2 = new ArrayList<>();
        // loop to search for every word in the given trie.
        for (Object e : trieST.keys()) {
            boolean check = patternSearch(arr, (String) e);
            if (check) list2.add((String) e);

        }
        // print loop to print all words
        for (int i = 0; i < list2.size() - 1; i++) {
            System.out.print(list2.get(i) + ", ");
        }
        System.out.println(list2.get(list2.size() - 1));


    }

    public void solvePuzzle(TrieST<Integer> Try, String path) throws FileNotFoundException {
        //--------------------------------------------------------
        // Summary: Creates a 2d char array for the given puzzle input.
        // name is given.
        // Precondition: The puzzle is not created.
        // Postcondition: The puzzle is created.
        //--------------------------------------------------------

        Scanner sc = new Scanner(new File(path));
        ArrayList<String> list = new ArrayList<>();
        char[][] arr;
        int counter2 = 0;
        String next = sc.nextLine();
        list.add(next);
        while (sc.hasNext()) {
            next = sc.nextLine();
            list.add(next);
        }
        counter2 = 0;
        arr = new char[list.size()][list.size()];
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).length(); j = j + 2) {
                arr[i][counter2] = list.get(i).charAt(j);
                counter2++;
            }
            counter2 = 0;
        }
        createArray(Try, arr);
    }

    private String reverseString(String str) {
        //--------------------------------------------------------
        // Summary: A helper method to reverse Strings
        // name is given.
        // Precondition: The String is not reversed.
        // Postcondition: The string is reversed and returned.
        //--------------------------------------------------------

        StringBuilder sb = new StringBuilder(str);
        sb.reverse();
        return sb.toString();
    }

    public void ReverseSearch(String S, TrieST trieST) {
        //--------------------------------------------------------
        // Summary: finds the reverse auto complete for given inputs according to the given suffix.
        // name is given.
        // Precondition: all string with the given suffix is not found.
        // Postcondition:  all string with the given suffix is not found and printed.
        //--------------------------------------------------------

        //creating trie.
        ArrayList<String> list2 = new ArrayList<>();
        TrieST<Integer> ntry = new TrieST();
        for (Object e : trieST.keys()) {
            ntry.put(reverseString((String) e), ((int) Math.random() * 100) + 1);
        }
        //creating trie.
        TrieST<Integer> ntry2 = new TrieST();
        for (String s : ntry.keysWithPrefix(S)) {
            ntry2.put(reverseString(s), ((int) Math.random() * 100) + 1);
        }
        for (String s : ntry2.keys()) {
            list2.add(s);
        }
        //printing values to the screen.
        if (!list2.isEmpty()) {
            for (int i = 0; i < list2.size() - 1; i++) {
                System.out.print(list2.get(i) + ", ");
            }
            System.out.print(list2.get(list2.size() - 1));
        } else {
            System.out.println("No word");
        }
    }
}

public class DriverClass {
//-----------------------------------------------------
// Title: Class DriverClass
// Author:Bahadır Ünal, Abdusselam Koç,
// Description: This class creates search, autocomplete, fullAutocomplete, finding top k methods and runs the main loop.
//-----------------------------------------------------


    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine().trim(); //File Path
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
        int input = scanner.nextInt();
        switch (input) {
            case 1:     //Search
                String searchInput = scanner.next().toLowerCase();
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
                if (!autoComplete.isEmpty()) { 
                    s.deleteCharAt(s.length() - 2);
                } else {
                    System.out.println("No word");
                }
                break;
            case 3:     //ReverseAutoComplete /
                Util ut = new Util();
                String userInput = scanner.next();
                ut.ReverseSearch(userInput, trieST);
                break;
            case 4: //FullComplete
                scanner.nextLine();// Consume newline left-over
                String inp = scanner.nextLine();
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
                if (!fullComplete.isEmpty()) { 
                    sb.deleteCharAt(sb.length() - 2);
                } else {
                    System.out.println("No word");
                }
                break;
            case 5: //findTopK
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
            case 6: //SolvePuzzle
                String puzzlePath = scanner.next();
                Util ut1 = new Util();
                ut1.solvePuzzle(trieST, puzzlePath);
                break;
        }
        
    }
}



