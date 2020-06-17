package com.javaex.phone;

import java.util.List;
import java.util.Scanner;

public class PhoneApp {

	public static void main(String[] args) {

		PhoneDao phoneDao = new PhoneDao();
	
		Scanner sc = new Scanner(System.in);
		
		System.out.println("******************************************************");
		System.out.println("*               전화번호 관리 프로그램               *");
		System.out.println("******************************************************");

		boolean run = true;
		while(run) {
			
			String name, hp, company;
			int personId, count;
			
			System.out.println();
			System.out.println("1.리스트   2.등록   3.수정   4.삭제   5.검색   6.종료");
			System.out.println("------------------------------------------------------");
			System.out.print(">메뉴번호: ");
			int menu = sc.nextInt();
			
			switch(menu) {
			case 1:
				System.out.println("<1.리스트>");
				
				List<PersonVo> pList = phoneDao.select();
				
				for(PersonVo vo : pList) {
					System.out.println(vo.getPersonId()+".\t"+vo.getName()+"\t"+vo.getHp()+"\t"+vo.getCompany());
				}
				
				break;
			
			case 2:
			    System.out.println("<2.등록>");
			    
			    System.out.print("이름 > ");
				name = sc.next();
				
				System.out.print("휴대전화 > ");
				hp = sc.next();
				
			    System.out.print("회사번호 > ");
				company = sc.next();
				
				count = phoneDao.insert(name, hp, company);
				
				System.out.println("["+count+"건이 등록되었습니다.]");
				break;
				
			case 3:
				System.out.println("<3.수정>");
				
				System.out.print("번호 > ");
				personId = sc.nextInt();
				sc.nextLine();
				
				System.out.println();
				System.out.println("[수정할 내용을 입력해 주세요]");
				
				System.out.print("이름 > ");
				name = sc.nextLine();
				
				System.out.print("휴대전화 > ");
				hp = sc.nextLine();
				
				System.out.print("회사번호 > ");
				company = sc.nextLine();

				count = phoneDao.update(personId, name, hp, company);
				System.out.println("["+count+"건이 수정되었습니다.]");
				break;
				
			case 4:
				System.out.println("<4.삭제>");
				
				System.out.print("번호 > ");
				personId = sc.nextInt();
				
				count = phoneDao.delete(personId);
				System.out.println("["+count+"건이 삭제되었습니다.]");
				break;
				
			case 5:
				System.out.println("<5.검색>");
				System.out.println();
				
				System.out.print("검색어 > ");
		    	String keyword = sc.next();
		    	
		    	List<PersonVo> serchList = phoneDao.select();
		    	
		    	String result = null;
		    	boolean printOK = false;
		      	
		    	for(PersonVo vo: serchList) {
		    		result = vo.getName()+"\t"+vo.getHp()+"\t"+vo.getCompany();
		    		if(result.contains(keyword)) {
		    			printOK = true;
						System.out.println(vo.getPersonId()+".\t"+result);
		    		}
		    	}//for
		    	
		    	if(printOK==false) {
		    		System.out.println("[검색 결과가 없습니다.]");
		    	}
		    	
				break;
				
			case 6:
				System.out.println("******************************************************");
		    	System.out.println("*                     감사합니다.                    *");
				System.out.println("******************************************************");
		    	run = false;
				break;
				
			default:
				System.out.println("[다시 입력해 주세요.]");
				break;
			}//switch
		
		}//while
		
		sc.close();
		
	}
}
