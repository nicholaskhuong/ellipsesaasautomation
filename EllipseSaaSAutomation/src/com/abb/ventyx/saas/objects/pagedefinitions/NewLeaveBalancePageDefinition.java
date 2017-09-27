package com.abb.ventyx.saas.objects.pagedefinitions;

public class NewLeaveBalancePageDefinition {
	public static final String NEW_LEAVE_REQUEST_TEXT_ID = "//*[@id='saas-3522304']/div/div[2]/div/div[2]/div/div[1]/div[1]/span";
	
	public static final String TYPE_DROPDOWN_ID = "#leaveTypeCode > select[class='v-select-select']";
	public static final String REQUEST_CODE_DROPDOWN_ID = "#bookedLeaveCode > select";
	public static final String PART_DFAY_LEAVE_CHECKBOX_ID = "#gwt-uid-15";
	public static final String START_DATE_DATEPICKER_ID = "#leaveStartDate > input[type='date']";
	public static final String END_DATE_DATEPICKER_ID = "#leaveEndDate > input[type='date']";
	public static final String DAYS_TEXT_ID = "#gwt-uid-16 > div";
	public static final String DAYS_VALUE_ID = "#leaveDays";
	public static final String HOURS_TEXT_ID = "#gwt-uid-18 > div";
	public static final String HOURS_VALUE_ID = "#leaveHours";
	public static final String FORECAST_TEXT_ID = "#gwt-uid-20 > div";
	public static final String FORECAST_VALUE_ID = "#forecastDays";
	public static final String FORECAST_HOURS_TEXT_ID = "#gwt-uid-22 > div";
	public static final String FORECAST_HOURS_VALUE_ID = "##forecastHours";
	public static final String STATUS_TEX_ID = "//*[@id='fgLeaveRequestDetails']/div[10]/div[1]/div";
	public static final String STATUS_VALUE_ID = "#leaveStatusDesc";
	public static final String NOTES_TEXT_ID = "//*[@id='fgLeaveRequestDetails']/div[11]/div[1]/div";
	public static final String NOTES_TEXTAREA_ID = "#stdText";
	
	public static final String APPLY_BUTTON_ID = "createLeaveRequest3";
//	public static final String APPLY_BUTTON_ID = "#createLeaveRequest3 > span > span";
	
	public static final String Next_ICON_ID = "#rowSelectButton > span > span.v-icon.FontAwesome";
}

