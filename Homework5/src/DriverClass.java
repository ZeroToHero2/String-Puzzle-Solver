import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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
        R = arr.length;
        C = arr.length;

        ArrayList<String> list2 = new ArrayList<>();

        for (Object e : trieST.keys()) {
            boolean check = patternSearch(arr, (String) e);
            if (check) list2.add((String) e);

        }
        for (int i = 0; i < list2.size() - 1; i++) {
            System.out.print(list2.get(i) + ", ");
        }
        System.out.println(list2.get(list2.size() - 1));


    }


    public void solvePuzzle(TrieST<Integer> Try, String path) throws FileNotFoundException {
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
        StringBuilder sb = new StringBuilder(str);
        sb.reverse();
        return sb.toString();
    }

    public void ReverseSearch(String S, TrieST trieST) {
        ArrayList<String> list2 = new ArrayList<>();

        TrieST<Integer> ntry = new TrieST();

        for (Object e : trieST.keys()) {
            ntry.put(reverseString((String) e), ((int) Math.random() * 100) + 1);
        }


        TrieST<Integer> ntry2 = new TrieST();
        for (String s : ntry.keysWithPrefix(S)) {
            ntry2.put(reverseString(s), ((int) Math.random() * 100) + 1);
        }
        for (String s : ntry2.keys()) {
            list2.add(s);
        }

        if (!list2.isEmpty()) {
            for (int i = 0; i < list2.size() - 1; i++) {
                System.out.print(list2.get(i) + ", ");
            }
            System.out.print(list2.get(list2.size() - 1));
        } else {
            System.out.println("No word");
        }


    }

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

    public void reader() throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        Scanner fileScanner = new Scanner(new File("C:\\Users\\BAHADIR\\IdeaProjects\\Work\\Homework5\\src\\input1.txt"));
        Scanner fileScanner1 = new Scanner(new File("C:\\Users\\BAHADIR\\IdeaProjects\\Work\\Homework5\\src\\input1.txt"));
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


    }
}

public class DriverClass {


    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        // Scanner fileScanner = new Scanner(new File("C:\\Users\\BAHADIR\\IdeaProjects\\Work\\Homework5\\src\\input1.txt"));
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
                    System.out.println("No word");
                }
                System.out.println(s); // AutoComplete Algorithm Test
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
                if (!fullComplete.isEmpty()) { //s.isEmpty() VPL error for this expression
                    sb.deleteCharAt(sb.length() - 2);
                } else {
                    System.out.println("No word");
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
                String puzzlePath = scanner.next();
                Util ut1 = new Util();
                ut1.solvePuzzle(trieST, puzzlePath);
                break;
        }


    }
}



