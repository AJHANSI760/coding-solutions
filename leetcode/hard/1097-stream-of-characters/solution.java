
class StreamChecker {
    private static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isWord;
    }

    private final TrieNode root;
    private final StringBuilder stream;
    private final int maxLength;

    public StreamChecker(String[] words) {
        root = new TrieNode();
        stream = new StringBuilder();

        int max = 0;

        for (String word : words) {
            max = Math.max(max, word.length());

            TrieNode node = root;

            for (int i = word.length() - 1; i >= 0; i--) {
                int index = word.charAt(i) - 'a';

                if (node.children[index] == null) {
                    node.children[index] = new TrieNode();
                }

                node = node.children[index];
            }

            node.isWord = true;
        }

        maxLength = max;
    }

    public boolean query(char letter) {
        stream.append(letter);

        if (stream.length() > maxLength) {
            stream.deleteCharAt(0);
        }

        TrieNode node = root;

        for (int i = stream.length() - 1; i >= 0; i--) {
            int index = stream.charAt(i) - 'a';

            if (node.children[index] == null) {
                return false;
            }

            node = node.children[index];

            if (node.isWord) {
                return true;
            }
        }

        return false;
    }
}
