import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class HuffmanCoding{
    private static HuffmanNode root;
    private static StringBuilder code;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the message to encode. ");
        String message = s.nextLine();
        HashMap<Character, Integer> frequencyMap = new HashMap<>();
        for(char c:message.toCharArray()){
            frequencyMap.put(c, frequencyMap.getOrDefault(c,0)+1);
        }

        PriorityQueue<HuffmanNode> huffmanTree = new PriorityQueue<>((a,b)->a.frequency - b.frequency);

        for(char c:frequencyMap.keySet()){
            huffmanTree.add(new HuffmanNode(c, frequencyMap.get(c)));
        }

        while(huffmanTree.size()>1){
            HuffmanNode left = huffmanTree.poll();
            HuffmanNode right = huffmanTree.poll();
            HuffmanNode newNode = new HuffmanNode('$', left.frequency + right.frequency);
            newNode.left = left;
            newNode.right = right;
            huffmanTree.add(newNode);
        }

        HuffmanNode root = huffmanTree.poll();

        System.out.println("Huffman codes: ");
        printCodes(root, new StringBuilder());
    }

    public static void printCodes(HuffmanNode root, StringBuilder code){
        HuffmanCoding.root = root;
        HuffmanCoding.code = code;
        if(root == null) return;

        if(root.data != '$') System.out.println(root.data + " = " + code);

        if(root.left != null){
            printCodes(root.left, code.append('0'));
            code.deleteCharAt(code.length()-1);
        }

        if(root.right != null){
            printCodes(root.right, code.appendCodePoint('1'));
            code.deleteCharAt(code.length() - 1);
        }
    }
}

class HuffmanNode{
    char data;
    int frequency;
    HuffmanNode left, right;

    HuffmanNode(char data, int frequency){
        this.data = data;
        this.frequency = frequency;
    }
}