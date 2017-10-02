package com.abb.ventyx.saas.objects.pagedefinitions;

public class LeaveRequestPageDefinition {
	
	public static final String LEAVE_REQUEST_TITLE_ID = "//*[@id='saas-3522304']/div/div[2]/div/div[2]/div/div[1]/div[1]/span";
	//"#saas-3522304 > div > div.v-touchkit-navpanel.v-widget.v-has-width.v-has-height > div > div:nth-child(2) > div > div.v-touchkit-navbar.v-widget > div.v-touchkit-navbar-caption > span"
	
	public static final String REQUEST_CODE_TEXT_ID = "bookedLeaveCode";
	public static final String REQUEST_CODE_DESCRIPTION_TEXT_ID = "bookedLeaveDesc";
	public static final String START_DATE_TEXT_ID = "#leaveStartDate > input[type='date']";
	public static final String END_DATE_TEXT_ID = "#leaveEndDate > input[type='date']";
	public static final String DAYS_TEXT_ID = "leaveDays";
	public static final String STATUS_TEXT_ID = "leaveStatusDesc";
	public static final String MESSAGE_TEXT_ID = "#saas-3522304-overlays > div.v-Notification.error.v-Notification-error > div > div > h1";
	
	public static final String NEXT_ICON_ID = "#rowSelectButton > span > span.v-icon.FontAwesome";
//	public static final String DELETE_BUTTON_ID = "#deleteAction > span > span";
	public static final String DELETE_BUTTON_ID = "deleteAction";
	public static final String APPLY_BUTTON_ID = "#modifyAction > span > span";
}
