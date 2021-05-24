# Programmers_60060
## 프로그래머스 - 가사 검색(https://programmers.co.kr/learn/courses/30/lessons/60060)  
완전탐색으로 풀었다가 효율성 테스트에서 좌절,,  
풀이를 찾다가 Trie라는 자료구조에 대해 처음 접할 수 있었다.  
Trie는 아래와 같이 Tree 형태로 String을 Character로 쪼개어 각 노드에 입력한다 :  
<img src="https://user-images.githubusercontent.com/20007119/119345941-82e3fc00-bcd4-11eb-9da4-2eda7505b3ec.png" width="60%" height="60%">  
새로운 문자열을 추가할 때마다 문자열에 포함된 Charcter 노드의 카운트수를 더해준다.  
각 Query에서 ?가 위치한 바로 앞 Character 노드의 Count를 반환하여 답을 구할 수 있었다.  

실행 순서는 다음과 같다 :  
1. Trie와 Node 클레스 및 입력, Count 메소드 생성
2. 입력 문자열 & Reverse 문자열을 Trie, Reverse Trie에 입력
3. Trie의 Count 메소드로 각 쿼리에 대한 count 값 answer 배열에 입력. ?가 앞에 온 쿼리는 Reverse처리 후, reverse Trie를 통해 확인  
4. answer 배열 반환
