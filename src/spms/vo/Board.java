package spms.vo;

import java.sql.Time;
import java.util.Date;

public class Board {

	protected int no;
	protected String email1;
	protected String password;
	protected String content;
	protected Date date;
	protected Time time;
	protected Date udate;
	protected Time utime;	
	
	
	public int getVno(){
		return no;
	}
	
	public Board setVno(int no){
		this.no = no;
		return this;
	}
	
	public String getEmail(){
		return email1;
	}
	
	public Board setEmail(String email){
		this.email1 = email;
		return this;
	}

	public String getPassword(){
		return password;
	}
	
	public Board setPassword(String password){
		this.password = password;
		return this;
	}
	
	public String getContent(){
		return content;
	}
	
	public Board setContent(String content){
		this.content = content;
		return this;
	}
	
	public Date getDate(){
		return date;
	}
	
	public Board setDate(Date date){
		this.date = date;
		return this;
	}
	
	public Time getTime(){
		return time;
	}
	
	public Board setTime(Time time){
		this.time = time;
		return this;
	}
	
	public Date getUdate(){
		return udate;
	}
	
	public Board setUdate(Date update_date){
		this.udate = update_date;
		return this;
	}	
	public Time getUtime(){
		return utime;
	}
	
	public Board setUtime(Time update_time){
		this.utime = update_time;
		return this;
	}	
}
