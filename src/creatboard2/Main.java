package creatboard2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		System.out.println("==프로그램 시작==");

		// 스캐너 타입의 sc라는 변수를 만들고 스캐너객체를 만들어서 연결한다
		Scanner sc = new Scanner(System.in);

		List<Article> articles = new ArrayList();
		//작성한 대로 공간을 증가하기 위해 ArraytList를 사용해랴한다

		int lastArticleId = 0;
		// while문 밖에 있는 이유는 매번 0이 되면 안되기때문이다

		while (true) {
			// 무한루프
			System.out.println("명령어) ");
			String cmd = sc.nextLine();
			System.out.println("입력된명령어 > " + cmd);

			// 이 안에 있는 명령어가 계속 실행된다

			if (cmd.equals("exit")) {
				// 조건문 cmd라는변수의 값이 exit라는 문자와 같다면
				break;
				// 탈출해라 break를 하지 않으면 무한반복문으로 실행된다
			}
			if (cmd.equals("article write")) {

				int id = lastArticleId + 1;
				lastArticleId = id;
				
				String regDate = Util.getNowDateStr();
				
				System.out.println(regDate);
				
				System.out.println("제목) ");
				String title = sc.nextLine();
				System.out.println("내용) ");
				String body = sc.nextLine();

				Article article = new Article(id, regDate,title, body);
				//제목과 내용을 쓰기 위해 article write 기능의 조건문에 있어야 한다
				articles.add(article);
				//ArrayList의 요소를 추가 하기 위함이디

				System.out.printf("%d번글이 생성되었습니다\n", id);

			}

			else if (cmd.equals("article list")) {
				if (articles.size() == 0) {

					System.out.println("게시글이 없습니다");
					continue;
				} else {
					System.out.println("게시물이 있습니다");
				}
				System.out.println("번호   : 제목");
				for(int i = articles.size()-1; i >= 0; i--) {
					//게시물이 저장되고 보여줄때 1번 부터 보여주게 하기 위함
					Article article = articles.get(i);
					
					System.out.printf("%d   :  %s\n",article.id,article.title);  
				}
				
				
			}else if(cmd.startsWith("article detail ")) {
				//앞에있는 cmd가 뒤에있는 문자열로 시작할경우
				
				String[] cmdBit = cmd.split(" ");
				//split은 안에있는 인자를 기준으로 나눈다
				int id = Integer.parseInt(cmdBit[2]);
				//ex) article detail 1 
				//cmdBit[0] =article
				//cmdBit[1] =detail
				//cmdBit[2] =1
				//Integer.parseInt 문자열을 인트로 형변환 한다
				
//				boolean foundArticle = false;
				//참인지 거짓인지 거짓으로 초기화 게시물이 없을경우를 위해 만든 변수

				Article foundArticle = null;
				
				for(int i=0; i< articles.size(); i++) {
					//게시물이 있냐 없냐 본다 0번부터 끝까지 돌리는 반복문
					Article article = articles.get(i);
					
					if(article.id == id) {
						//위의 id와 같으면 게시물이 존재 하는 경우
//						foundArticle = true;
						foundArticle = article;
						continue;
					}
					
					System.out.println("수정할 제목) ");
					String title = sc.nextLine();
					System.out.println("수정할 내용) ");
					String body = sc.nextLine();
					
					foundArticle.title = title;
					foundArticle.body = title;
					
					System.out.printf("%d번 게시물이 수정되었습니다\n",id);
					
				}
				if(foundArticle == null) {
					//게시물이 없는 경우
					System.out.printf("%d번 게시물이 존재하지 않습니다\n",id);
				}

				articles.remove(foundArticle);
			
				System.out.printf("%d번 게시물이 삭제 되었습니다",id);
			
			}
			
			
			else {
				System.out.printf("%s는(은)존재하지 않는 명령어 입니다\n",cmd);
			}
		}

		sc.close(); // 스캐너기능을 하용하면 반드시 이 실행문을 추가햐야한다

		System.out.println("==프로그램 종료==");

	}

}

class Article {

	int id;
	String title;
	String body;
	String regDate;

	Article(int id, String regDate,String title, String body) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.regDate =regDate;
	}

}