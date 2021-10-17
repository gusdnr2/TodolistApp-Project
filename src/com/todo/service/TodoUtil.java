package com.todo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc, category, due_date, importance;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("=== �׸� �߰� ===\n"
				+ "���� > ");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.printf("������ �ߺ��˴ϴ�!");
			return;
		}
		System.out.print("ī�װ� > ");
		category = sc.next();
		
		sc.nextLine();
		System.out.print("���� > ");
		desc = sc.nextLine().trim();
		
		System.out.println("���� ���� (���� : 0000/00/00) > ");
		due_date = sc.next();
		
		System.out.print("�߿䵵 �Է� (�߿�, ����, ����) > ");
		importance = sc.next();
		
		TodoItem t = new TodoItem(title, desc, category, due_date, importance);
		if(list.addItem(t)>0)
			System.out.println("���������� �߰��Ͽ����ϴ�.");
	}

	public static void deleteItem(TodoList l) {
		try {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("=== �׸� ���� ===\n"
				+ "���� �� �׸��� ��ȣ > ");
		int index = sc.nextInt();
		if(l.deleteItem(index)>0) {
			System.out.println("�����Ǿ����ϴ�.");
		}
		
		
		} catch (NumberFormatException e) {
			System.out.println("���ڸ� �Է��� �ּ���!");
		}
	}


	public static void updateItem(TodoList l) {
		try {
		Scanner sc = new Scanner(System.in);
		System.out.println("\n"
				+ "=== �׸� ���� ===\n"
				+ "���� �� �׸��� ��ȣ > ");
		int index = sc.nextInt();		
		

		System.out.print("���ο� ������ �Է��� �ּ��� > ");
		String new_title = sc.next().trim();
		System.out.print("���ο� ī�װ��� �Է��� �ּ��� > ");
		String new_cate = sc.next().trim();
		sc.nextLine();
		System.out.print("���ο� ������ �Է��� �ּ��� > ");
		String new_description = sc.nextLine().trim();
		System.out.print("���ο� ���� ���ڸ� �Է��� �ּ��� > ");
		String new_ddat = sc.nextLine().trim();
		System.out.print("�߿䵵 �Է� (�߿�, ����, ����) > ");
		String importance = sc.next();
		

		TodoItem t = new TodoItem(new_title, new_description, new_cate, new_ddat, importance);
        t.setId(index);
		if(l.updateItem(t)>0) 
			System.out.println("�����Ǿ����ϴ�.");
		} catch(NumberFormatException e) {
			System.out.println("���ڸ� �Է��� �ּ���!");
		}
		}

	
	public static void find(TodoList l,String keyword) {
		int counter = 0;
		for (TodoItem item : l.getList(keyword)) {
			System.out.println(item.toString());
			counter++;
		}
			
		if (counter == 0) {
			System.out.println("�˻� ��� �׸��� ã�� ���߽��ϴ�.");
			return;
		} else {
			System.out.println("�� "+counter+"���� �׸��� ã�ҽ��ϴ�.");		
		}
		}
	
	public static void findCate(TodoList l,String keyword) {
		int count = 0;
		for(TodoItem item : l.getListCategory(keyword)) {
			System.out.println(item.toString());
			count++;
		}
		if (count == 0) {
			System.out.println("�׸��� ã�� ���߽��ϴ�.");
			return;
		}
		else 
			System.out.println("�� "+count+"���� �׸��� ã�ҽ��ϴ�.");
		}
	

	public static void listAll(TodoList l) {
		System.out.printf("[��ü ���, �� %d��]\n",l.getCount());
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
	}
	public static void listAll(TodoList l, String orderby, int ordering) {
		System.out.printf("[��ü ���, �� %d��]\n",l.getCount());
		for (TodoItem item : l.getOrderedList(orderby, ordering)) {
			System.out.println(item.toString());
		}
	}
	public static void listCateAll(TodoList l) {
		int count=0;
		for(String item : l.getCategories()) {
			System.out.print(item+" ");
			count++;
		}
		System.out.printf("\n��ü ī�װ��� ������ %d���Դϴ�.\n",count);
	}
	public static void listAll(TodoList l, int num) {
		System.out.println("[��ü ���, �� "+l.getCount()+"��]\n");
		for (TodoItem item : l.getList(num)) {
			System.out.println(item.toString());
		}
	}
	public static void completeItem(TodoList l, int num) {
		if(l.completeItem(num)>0) {
			System.out.println("����� �Ϸ��߽��ϴ�.");
		}
		else
			System.out.println("������ �߻��߽��ϴ�.");
	}
	public static void importanceItem(TodoList l, int num, String imp) {
		if(l.importanceItem(num, imp)>0)
			System.out.println("����� �Ϸ��߽��ϴ�.");
		else
			System.out.println("������ �߻��߽��ϴ�.");
	}
}
	
	/* public static void saveList(TodoList l,String filename) {
		try {
			Writer w  = new FileWriter(filename);
			for (TodoItem item : l.getList()) {
				w.write(item.toSaveString());
			}
			w.close();
			System.out.println("������ �Է� �Ϸ�.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void loadList(TodoList l,String filename) {
		try {
			BufferedReader r = new BufferedReader(new FileReader(filename));
			int count = 0;
			String eachline;
			while ((eachline = r.readLine()) != null) {
				count++;
				StringTokenizer st = new StringTokenizer(eachline,"##");
				String cate = st.nextToken();
				String name = st.nextToken();
				String desc = st.nextToken();
				String ddat = st.nextToken();
				String date = st.nextToken();
				TodoItem t = new TodoItem(name,desc,date,cate,ddat);
				l.addItem(t);
			}
			r.close();
			System.out.println(count+" ���� �׸��� �о����ϴ�.");
		} catch (FileNotFoundException e) {
			System.out.println("TodoList.txt ������ �����ϴ�.");
		} catch (IOException e) {
			System.out.println("������ ���� ���߽��ϴ�.");
		} catch (NoSuchElementException e) {
			System.out.println("������ ���� ���߽��ϴ�.");
		}
}
} */
