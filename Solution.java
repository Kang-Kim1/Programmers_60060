import java.util.regex.Pattern;
import java.util.*;
class Solution {
    // Trie Container Class
    class Trie {
		private TrieNode rootNode;

		Trie() {
			rootNode = new TrieNode();
		}
		// 각 노드의 카운트 값을 더하며 비어있는 노드 찾아 입력
		void insert(String word) {
			TrieNode thisNode = this.rootNode;

			for (int i = 0; i < word.length(); i++) {
				thisNode.count++;
				thisNode = thisNode.getChildNodes().computeIfAbsent(word.charAt(i), c -> new TrieNode());
			}
			thisNode.setIsLastChar(true);
		}
		// 검색 keyword의 카운트 값 반환. ?이 포함되어있을 경우 이전 characgter에 대한 카운트 값 반환
		int count(String word) {
			TrieNode thisNode = this.rootNode;
			int count = 0;
			for (int i = 0; i < word.length(); i++) {
				char character = word.charAt(i);
				if (character == '?')
					return thisNode.count;
				TrieNode node = thisNode.getChildNodes().get(character);
				count = node.count;
				thisNode = node;
			}
			return count;
		}

		boolean contains(String word) {
			TrieNode thisNode = this.rootNode;
			for (int i = 0; i < word.length(); i++) {
				char character = word.charAt(i);

				TrieNode node = thisNode.getChildNodes().get(character);

				if (node == null)
					return false;
				thisNode = node;
			}
			return thisNode.isLastChar();
		}

	}
	// Trie 노드 class
	class TrieNode {
		private Map<Character, TrieNode> childNodes = new HashMap<>();
		private boolean isLastChar;
		private int count;
    
		Map<Character, TrieNode> getChildNodes() {
			return this.childNodes;
		}

		boolean isLastChar() {
			return this.isLastChar;
		}

		void setIsLastChar(boolean isLastChar) {
			this.isLastChar = isLastChar;
		}
	}

	public int[] solution(String[] words, String[] queries) {
		int[] answer = new int[queries.length];
		Trie[] trieArr = new Trie[10001];
		Trie[] rTrieArr = new Trie[10001];

		// 각 문자열 & 문자열의 reverse값 array에 입력
		for(String word : words) {
			if(trieArr[word.length()] == null) {
				trieArr[word.length()] = new Trie();
			}
			trieArr[word.length()].insert(word);

			if(rTrieArr[word.length()] == null) {
				rTrieArr[word.length()] = new Trie();
			}
			String reversedStr = (new StringBuffer(word)).reverse().toString();
			rTrieArr[word.length()].insert(reversedStr);
        	}

		// 각 query의 카운트값 확인. ?가앞에 온 query의 경우 reverse 배열에서 확인
		for(int i = 0; i < queries.length; i++) {
			String curr = queries[i];
			int count = 0;
			try {
				if(curr.charAt(0) == '?') {
					curr = (new StringBuffer(curr)).reverse().toString();
					count = rTrieArr[curr.length()].count(curr);
				} else {
					count = trieArr[curr.length()].count(curr);        		
				}
			} catch(NullPointerException exp) { 
				count = 0;
			}
			answer[i] = count;
		}
        	return answer;
	}
}
