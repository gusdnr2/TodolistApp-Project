package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		boolean quit = false;
		int number,checker,id;
		String imp;
		//l.importData(filename);
		Menu.displaymenu();
		do {
			Menu.Prompt();
			String choice1 = "";
			String choice2 = " ";
			String choice = sc.nextLine();
			
			if (choice.contains(" ")) {
				choice1 = choice.substring(0,choice.indexOf(" "));
				choice2 = choice.substring(choice.indexOf(" ")+1);
				choice = choice1;
			}
			
			switch (choice) {

			case "add":
				TodoUtil.createItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;

			case "ls_name_asc":
				System.out.println("=== 이름 순서 정렬 ===");
				TodoUtil.listAll(l, "title", 1);
				break;

			case "ls_name_desc":
				System.out.println("=== 이름 역순 정렬 ===");
				TodoUtil.listAll(l, "title", 0);
				break;
				
			case "ls_date":
				System.out.println("=== 날짜 순서 정렬 ===");
				TodoUtil.listAll(l, "due_date", 1);
				break;
				
			case "ls_date_desc":
				System.out.println("=== 날짜 역순 정렬 ===");
				TodoUtil.listAll(l, "due_date", 0);
				break;
				
			case "ls_cate":
				TodoUtil.listCateAll(l);
				break;
				
			case "find_cate":
				TodoUtil.findCate(l, choice2);
				break;

			case "exit":
				quit = true;
				break;
				
			case "reset":
				l.DeleteData();
				l.ResetSequence();
				
			case "help":
				Menu.displaymenu();
				break;
			
			case "find":
				TodoUtil.find(l, choice2);
				break;
				
			case "comp":
				TodoUtil.completeItem(l, Integer.parseInt(choice2));
				break;
				
			case "ls_comp":
				TodoUtil.listAll(l,1);
				break;
			case "add_multi":
				System.out.print("추가를 원하는 항목의 개수 입력 > ");
				number = sc.nextInt();
				checker = 0;
				for (int i=0; i<number; i++) {
					TodoUtil.createItem(l);
					System.out.print("계속 하시겠습니까? (멈추기 위해 0 입력) > ");
					checker = sc.nextInt();
					if (checker == 0)
						break;
				}
				break;
			case "edit_multi":
				System.out.print("수정을 원하는 항목의 개수 입력 > ");
				number = sc.nextInt();
				checker = 0;
				for (int i=0; i<number; i++) {
					TodoUtil.updateItem(l);
					System.out.print("계속 하시겠습니까? (멈추기 위해 0 입력) > ");
					checker = sc.nextInt();
					if (checker == 0)
						break;
				}
				break;
			case "impo":
				id = sc.nextInt();
				imp = sc.next();
				TodoUtil.importanceItem(l,id,imp);
				
			default:
				System.out.println("정확한 명령어를 입력해 주세요. \n명령어 목록이 궁금하시면 help 명령어를 사용해 주세요.");
				break;
			}
			
		} while (!quit);
		// TodoUtil.saveList(l, filename);
	}
}
