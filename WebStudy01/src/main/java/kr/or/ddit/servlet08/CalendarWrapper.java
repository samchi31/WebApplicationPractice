package kr.or.ddit.servlet08;

import java.util.Calendar;
import java.util.Locale;

import static java.util.Calendar.*;

import java.text.DateFormatSymbols;

public class CalendarWrapper {
	
	private Calendar adaptee;
	private Locale locale;
	
	private int offset;
	
	private int dayOfWeekFirst;
	private int lastDate;
	
	private String[] weekDays;
	private String[] months;
	
	private int beforeYear;
	private int beforeMonth;
	private int nextYear;
	private int nextMonth;
	
	private int currentMonth;
	private int currentYear;
	

	public CalendarWrapper(Calendar adaptee, Locale locale) {
		super();
		this.adaptee = adaptee;
		this.locale = locale;
		
		adaptee.set(DAY_OF_MONTH, 1);	// 현재달 1일로
		dayOfWeekFirst = adaptee.get(DAY_OF_WEEK);	//시작하는 요일	
		offset = dayOfWeekFirst - SUNDAY;
		lastDate = adaptee.getActualMaximum(DAY_OF_MONTH);
		
		currentYear = adaptee.get(YEAR);
		currentMonth = adaptee.get(MONTH);
		
		DateFormatSymbols dfs = DateFormatSymbols.getInstance(locale);
		weekDays = dfs.getWeekdays();// 요일
		months = dfs.getMonths();
		
		adaptee.add(MONTH, -1);	//한달을 뺀다
		beforeMonth = adaptee.get(MONTH);
		beforeYear = adaptee.get(YEAR);
		 
		adaptee.add(MONTH, 2);		
		nextMonth = adaptee.get(MONTH);
		nextYear = adaptee.get(YEAR);
		
		adaptee.add(MONTH, -1);
	}

	public Calendar getAdaptee() {
		return adaptee;
	}

	public void setAdaptee(Calendar adaptee) {
		this.adaptee = adaptee;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getDayOfWeekFirst() {
		return dayOfWeekFirst;
	}

	public void setDayOfWeekFirst(int dayOfWeekFirst) {
		this.dayOfWeekFirst = dayOfWeekFirst;
	}

	public int getLastDate() {
		return lastDate;
	}

	public void setLastDate(int lastDate) {
		this.lastDate = lastDate;
	}

	public String[] getWeekDays() {
		return weekDays;
	}

	public void setWeekDays(String[] weekDays) {
		this.weekDays = weekDays;
	}
	
	public String[] getMonths() {
		return months;
	}

	public void setMonths(String[] months) {
		this.months = months;
	}

	public int getBeforeYear() {
		return beforeYear;
	}

	public void setBeforeYear(int beforeYear) {
		this.beforeYear = beforeYear;
	}

	public int getBeforeMonth() {
		return beforeMonth;
	}

	public void setBeforeMonth(int beforeMonth) {
		this.beforeMonth = beforeMonth;
	}

	public int getNextYear() {
		return nextYear;
	}

	public void setNextYear(int nextYear) {
		this.nextYear = nextYear;
	}

	public int getNextMonth() {
		return nextMonth;
	}

	public void setNextMonth(int nextMonth) {
		this.nextMonth = nextMonth;
	}
	

	public int getCurrentMonth() {
		return currentMonth;
	}

	public void setCurrentMonth(int currentMonth) {
		this.currentMonth = currentMonth;
	}

	public int getCurrentYear() {
		return currentYear;
	}

	public void setCurrentYear(int currentYear) {
		this.currentYear = currentYear;
	}
	

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	@Override
	public String toString() {
		return String.format(locale,"%1$tY, %1$tB", adaptee);
	}
	
	
	
}
