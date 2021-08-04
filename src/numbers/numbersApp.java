package numbers;

import java.util.*;
import java.io.*;

public class numbersApp {
	public static void main(String[] args) {
		 Scanner sc = new Scanner(System.in);
	      System.out.println("*********************************");
	      System.out.println("*   전화번호 관리 프로그램   *");
	      System.out.println("*********************************");
	      
	      boolean stop = true;
	      String line;
	      PrintWriter pw = null;
			
	      while (stop) {
	         System.out.println("1.리스트 2.등록 3.삭제 4.검색 5.종료");
	         System.out.println("---------------------------------");
	         System.out.print(">메뉴번호:");
	         
	         int num = sc.nextInt();
	         switch(num) {
	         case 1:
	            System.out. println("<1.리스트>\n");
	            error();
	            break;
	            
	         case 2:
	        	 try {
	        		 String[] add = new String[3];
	     			 System.out.println("<2.등록>");
	                 System.out.print(">이름: "); 
	                 add[0] = sc.next();
	                 
	                 System.out.print(">휴대전화: "); 
	                 add[1] = sc.next();
	                 
	                 System.out.print(">집전화: "); 
	                 add[2] = sc.next();
	                
	                 BufferedWriter bw = new BufferedWriter
	                		(new FileWriter("list", true));
	             	
	                bw.write(add[0]+", "+add[1]+", "+add[2]+"\n");
	             	
	                bw.flush();
	             	bw.close();
	             } catch (FileNotFoundException e) {
	            	 e.printStackTrace();
	             } catch(IOException e) {
	            	 e.printStackTrace();
	             } catch (Exception e) {
	            	 e.printStackTrace();
	            	 } 
	             	System.out.println("\n[등록되었습니다.]\n");
	            break;
	            
	         case 3:
	            System.out.println("<3. 삭제>");
	            System.out.print("번호: ");
	            String dummy = "";
	            int deleteline = 0;
	            
	            deleteline = sc.nextInt();
	            
	            try {
	                File f = new File("list");
		            BufferedReader br = new BufferedReader(new FileReader(f));    
	                for (int i = 0; i < deleteline -1; i++) {
	                	// 한줄씩 출력 입력받은 deleteline 전까지
	                	// dummy로 데이터 저장
	                	// readLine의 엔터 단위 구분 & 줄바꿈 \r\n
	                	line = br.readLine();
	                	dummy += (line + "\r\n");
	                }
	                // readLine()호출해 삭제할 열 제외
	                String delData = br.readLine();
	                // 나머지 line읽고 dummy에 전부 더함
	                while ((line = br.readLine()) != null) {
	                	dummy += (line + "\r\n");
	                }
	                // 삭제 열 제외 다시 파일에 적음
	                FileWriter fw = new FileWriter("list");
	                fw.write(dummy);
	                fw.close();
	                br.close();
	            } catch (FileNotFoundException e) {
	           	 e.printStackTrace();
	            } catch(IOException e) {
	           	 e.printStackTrace();
	            } catch (Exception e) {
	           	 e.printStackTrace();
	           	 }
	            System.out.println("\n[삭제되었습니다.]\n");
	            break;
	         case 4:
	        	 String find;
		         String lines = "";
	            System.out.println("<4. 검색>");
	            System.out.print(">이름: ");
	            find = sc.next();
	            try {
	             File f = new File("list");
	             BufferedReader br = new BufferedReader(new FileReader(f)); 
	             while ((line = br.readLine()) != null) {
	            	 if (line.indexOf(find) != -1) {
	            		 lines = line;
	            	 }
	             }
	             System.out.println(lines);
	            } catch (FileNotFoundException e) {
	           	 e.printStackTrace();
	            } catch(IOException e) {
	           	 e.printStackTrace();
	            } catch (Exception e) {
	           	 e.printStackTrace();
	           	 }
	            break;
	         case 5:
	            System.out.println("*********************************");
	            System.out.println("*   감사합니다      *");
	            System.out.println("*********************************");
	            stop = false;
	            break;
	         default:
	            System.out.println("\n[다시입력해주세요]\n");
	            break;
	         }
	      }   
	}
	private static void error() {
		try {
            File f = new File("list");
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null) {
            	System.out.println(line);
            }
            br.close();
         } catch (FileNotFoundException e) {
        	 e.printStackTrace();
         } catch(IOException e) {
        	 e.printStackTrace();
         } catch (Exception e) {
        	 e.printStackTrace();
        	 }
         System.out.println();
	}
	
}