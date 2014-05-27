package main.java.com.pest.demo.observer;

public interface Observer {

	public void update();
	
	public void setSubject(Subject sub);
}
