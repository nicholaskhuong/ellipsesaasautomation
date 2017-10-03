package com.abb.ventyx.saas.objects.pagedefinitions;

public class LeaveRequestsPageDefinition {
	
	//request leave appl
	public static final String LEAVE_REQUEST_TITLE_ID = "//*[@id='saas-3522304']/div/div[2]/div/div[2]/div/div[1]/div[1]/span";
	
	public static final String REQUEST_CODE_TEXT_ID = "bookedLeaveCode";
	public static final String REQUEST_CODE_DESCRIPTION_TEXT_ID = "bookedLeaveDesc";
	public static final String START_DATE_TEXT_ID = "#leaveStartDate > input[type='date']";
	public static final String END_DATE_TEXT_ID = "#leaveEndDate > input[type='date']";
	public static final String DAYS_TEXT_ID = "leaveDays";
	public static final String STATUS_DESC_TEXT_ID = "leaveStatusDesc";
	public static final String MESSAGE_TEXT_ID = "#saas-3522304-overlays > div.v-Notification.error.v-Notification-error > div > div > h1";
	
	public static final String NEXT_ICON_ID = "//*[@id='leaveRequestList']/div/div/div/div/div/div[2]/div/div/div/div/div[2]";
	
	//Approve leave app
	
	public static final String APPROVE_LEAVE_REQUESTS_TITLE_ID="//*[@id='saas-3522304']/div/div[2]/div/div[1]/div/div[1]/div[1]/span";
	public static final String EMPLOYEE_TEXTFIELD_ID = "employee";
	public static final String APPROVE_BUTTON_ID = "approveAction";
	public static final String CONFIRM_BUTTON_ID = "confirmAction";
	public static final String REJECT_BUTTON_ID = "rejectAction";
}
