import java.util.*;

class Node implements Comparable<Node>{
    char ch;
    int frequency;
    Node left, right;

    Node(char ch, int frequency){
        this.ch = ch;
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }

    public int compareTo(Node other){
        return this.frequency - other.frequency;
    }
}

public class huffman_coding {

    static void printHufman(Node root, String code){
        if(root == null) return;

        if(root.left == null && root.right == null){
            System.out.println(root.ch + ": " + code);
            return;
        }

        printHufman(root.left, code + "0");
        printHufman(root.right, code + "1");
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // int n = 6; 
        // char[] charArray = { 'a', 'b', 'c', 'd', 'e', 'f' };
        // int[] charfreq = { 5, 9, 12, 13, 16, 45 };

        System.out.print("Enter the number of characters: ");
        int n = in.nextInt();

        char[] charArray = new char[n];
        int[] charfreq = new int[n];
        for(int i=0; i<n; i++) {
            System.out.print("Enter the character " + (i+1) + ": ");
            charArray[i] = in.next().charAt(0);
            System.out.print("Enter the frequency " + (i+1) + ": ");
            charfreq[i] = in.nextInt();
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int i = 0; i < n; i++) {
            pq.add(new Node(charArray[i], charfreq[i]));
        }

        while(pq.size() > 1){
            Node left = pq.poll();
            Node right = pq.poll();

            Node newNode = new Node('-', left.frequency + right.frequency);
            newNode.left = left;
            newNode.right = right;

            pq.add(newNode);
        }

        Node root = pq.poll();

        System.out.println("Hufman codding");
        printHufman(root, " ");

        in.close();
    }
}