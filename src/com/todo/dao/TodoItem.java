package com.todo.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.todo.service.DateUtil;

public class TodoItem {
	private int id;
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private String title;
    private String desc;
    private String current_date;
    private String category;
    private String due_date;
    private int is_completed;
    private String importance;
    private String days;


    public  String getImportance() {
		return importance;
	}

	public void setImportance(String importance) {
		this.importance = importance;
	}

	public int getIs_completed() {
		return is_completed;
	}

	public void setIs_completed(int is_completed) {
		this.is_completed = is_completed;
	}

	public TodoItem(String title, String desc, String category, String due_date, String importance){
        this.title=title;
        this.desc=desc;
        this.category=category;
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
        this.current_date= f.format(new Date());
        this.due_date = due_date;
        this.days = DateUtil.getDays(due_date);
        this.importance = importance;
    }
    
    public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public TodoItem(int id, String title, String desc, String date, String category, String due_date, String importance){
    	this.id = id;
        this.title=title;
        this.desc=desc;
        this.current_date=date;
        this.category=category;
        this.due_date=due_date;
        this.days = DateUtil.getDays(due_date);
        this.importance = importance;
    }
    
    public TodoItem(String title, String desc, String date, String category, String due_date,String importance){
        this.title=title;
        this.desc=desc;
        this.current_date=date;
        this.category=category;
        this.due_date=due_date;
        this.days = DateUtil.getDays(due_date);
        this.importance = importance;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDue_date() {
		return due_date;
	}

	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}

	public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(String current_date) {
        this.current_date = current_date;
    }

	@Override
	public String toString() {
		if(is_completed == 0) 
			return id+". [" + category + "] "+" D"+days+" "+ title + " - " + desc + " " + due_date + " " + current_date + " " + importance;
		else if (is_completed == 1)
			return id+". [" + category + "] "+" D"+days+" "+ title +"[V]"+ " - " + desc + " " + due_date + " " + current_date + " " + importance;
		return id+". [" + category + "] "+" D"+days+" "+title + " - " + desc + " " + due_date + " " + current_date + " " + importance;
			
		
	}
	
	public String toSaveString() {
		return category+"##"+title+"##"+desc+"##"+due_date+"##"+current_date+"\n";
	}
    
}
