package com.sensiple.qa.android;

public interface OR {

	public final String GUERRILLA_MAIL_DROPDOWN="//select[@id='gm-host-select']";
	public final String GUERRILLA_MAIL_TEXTBOX="//span//input[@type='text']";
	public final String GUERRILLA_MAIL_SET_BUTTON="//button[contains(text(),'Set')]";
	public final String GUERRILLA_MAIL_EDIT_BUTTON="//span[@id='inbox-id']";
	public final String GUERRILLA_MAIL_RESET_YOUR_PASSWORD_LINK="//tbody[@id='email_list']/tr[@class='mail_row  email_unread pointer click-set'][1]//td/a[contains(text(),'DEMO: Reset')]";
	public final String GUERRILLA_MAIL_CLICK_HERE_TO_RESET_YOUR_PASSWORD_LINK="//a[text()='Click here to reset your password']";
	public final String GUERRILLA_MAIL_MAIL_CHECKBOX_TO_DELETE="//tbody[@id='email_list']/tr[1]/td/input[@type='checkbox']";
	public final String GUERRILLA_MAIL_MAIL_DELETE_BUTTON="//input[@id='del_button']";
	public final String ANDROID_RA_MEMBER_LOGIN_EMAIL_TEXTBOX="//android.widget.EditText[@resource-id='com.realappeal.app:id/accountname']";
	public final String ANDROID_RA_MEMBER_LOGIN_PASSWORD_TEXTBOX="//android.widget.EditText[@resource-id='com.realappeal.app:id/password']";
	public final String ANDROID_RA_MEMBER_LOGIN_SIGNIN_BUTTON="//android.widget.Button[@resource-id='com.realappeal.app:id/singin_btn']";
	public final String ANDROID_RA_MEMBER_MENU_BUTTON="//android.webkit.WebView[@content-desc='Dashboard | Real Appeal']//android.view.View[7]//android.view.View";
	public final String ANDROID_RA_MEMBER_LOGOUT_BUTTON="//android.view.View[contains(@content-desc,'LOG OUT')]";
    
	public final String ANDROID_RA_MEMBER_VERSION_NAME="//android.widget.TextView[@resource-id='com.realappeal.app:id/lblVersionName']";
	public final String ANDROID_RA_MEMBER_KULFI_ENVIRONMENT_NAME="//android.widget.TextView[@text='kulfi']/following::android.widget.CheckBox[contains(@resource-id,'com.realappeal.app:id/checkBox1')]";
	public final String ANDROID_RA_MEMBER_TEST_ENVIRONMENT_NAME="//android.widget.TextView[@text='test']/following::android.widget.CheckBox[contains(@resource-id,'com.realappeal.app:id/checkBox1')]";
	public final String ANDROID_RA_MEMBER_ENTER_WEIGHT_TEXTBOX="//android.widget.EditText[contains(@text,'Enter Weight')]";
	public final String ANDROID_RA_MEMBER_SAVE_AND_CONTINUE_BUTTON="//android.widget.Button[contains(@content-desc,'SAVE & CONTINUE')]"; 
	public final String ANDROID_RA_MEMBER_BEFORE_JOINING_YOUR_SESSION_HEADER="//android.view.View[contains(@content-desc,'Before joining your session')]"; 
	public final String COACH_SESSION_MIC_ON="//div[@id='mic-status-icon'and@class='device-status-icon']";
	public final String COACH_SESSION_VIDEO_ON="//div[@id='video-status-icon'and@class='device-status-icon']";
	public final String COACH_SESSION_MIC_OFF="//div[@id='mic-status-icon'and@class='device-status-icon highlight']";
	public final String COACH_SESSION_VIDEO_OFF="//div[@id='video-status-icon'and@class='device-status-icon highlight']";

	public static String COACHES_LOGIN_PAGE_LOGO_REF ="//div[@id='orgLoginBoxLogo']";
	public static String COACHES_USERNAME_1 ="//input[@type='email']";
	public static String COACHES_PASSWORD ="//input[@type='password']";
	public static String COACHES_LOGIN_BUTTON ="//button[@id='login_button']";
	public final String MEMBER_SESSION_MIC_ON="//android.widget.ToggleButton[@resource-id='com.realappeal.app:id/toggleBtnMike' and @checked='true']";
	public final String MEMBER_SESSION_MIC_OFF="//android.widget.ToggleButton[@resource-id='com.realappeal.app:id/toggleBtnMike' and @checked='false']";
	public final String MEMBER_SESSION_VIDEO_ON="//android.widget.ToggleButton[@resource-id='com.realappeal.app:id/toggleBtnCamera' and @checked='true']";
	public final String MEMBER_SESSION_VIDEO_OFF="//android.widget.ToggleButton[@resource-id='com.realappeal.app:id/toggleBtnCamera' and @checked='false']";	
	public final String LIVESESSION_1ON1_SESSION_MEMBER_CONNECTIONS_ANDROID_DEVICE_ICON="//span[@class='users-browser-icon sprite-android']";
	public final String COACH_SESSION_MIC_DISABLED="//div[@class='toolbar']//div[2]//span[@id='memberMic0'and@class='clickable fa fa-microphone-slash red']";
	public final String COACH_SESSION_MIC_ENABLED="//div[@class='toolbar']//div[2]//span[@id='memberMic0'and@class='clickable fa fa-microphone green']";
	public final String COACH_SESSION_CAMERA_ENABLED="//div[@class='toolbar']//div[2]//span[@id='memberCamera0'and@class='clickable fa fa-video-camera green']";
	public final String COACH_SESSION_CAMERA_DISABLED="//div[@class='toolbar']//div[2]//span[@id='memberCamera0'and@class='clickable fa fa-eye-slash red']";
    public final String COACH_VERIFY_MEMBER_CHAT_TEXT="//div[@class='username ng-binding']";
   
}
