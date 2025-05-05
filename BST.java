public class BST<K extends Comparable<K>, V> {
    private class Node {
        K key;
        V value;
        Node left, right;
        Node(K key, V value) { this.key = key; this.value = value; }
    }
    private Node root;

    public void insert(K key, V value) {
        root = insertRec(root, key, value);
    }
    private Node insertRec(Node node, K key, V value) {
        if (node == null) return new Node(key, value);
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = insertRec(node.left, key, value);
        else if (cmp > 0) node.right = insertRec(node.right, key, value);
        else node.value = value;
        return node;
    }

    public V search(K key) {
        Node node = searchRec(root, key);
        return node == null ? null : node.value;
    }
    private Node searchRec(Node node, K key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp == 0) return node;
        return cmp < 0 ? searchRec(node.left, key) : searchRec(node.right, key);
    }
}