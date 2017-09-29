package com.abb.ventyx.saas.objects.pagedefinitions;

public class LeaveRequestsPageDefinition {
	
	public static final String LEAVE_REQUEST_TITLE_ID = "//*[@id='saas-3522304']/div/div[2]/div/div[2]/div/div[1]/div[1]/span";
	
	public static final String REQUEST_CODE_TEXT_ID = "bookedLeaveCode";
	public static final String REQUEST_CODE_DESCRIPTION_TEXT_ID = "bookedLeaveDesc";
	public static final String START_DATE_TEXT_ID = "#leaveStartDate > input[type='date']";
	public static final String END_DATE_TEXT_ID = "#leaveEndDate > input[type='date']";
	public static final String DAYS_TEXT_ID = "leaveDays";
	public static final String STATUS_TEXT_ID = "leaveStatusDesc";
	public static final String MESSAGE_TEXT_ID = "#saas-3522304-overlays > div.v-Notification.error.v-Notification-error > div > div > h1";
}
