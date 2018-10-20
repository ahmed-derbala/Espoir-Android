package com.ahmedderbala.espoir.app;

public class AppConfig {
    //ip
    //public static String URL_IP = "http://derbalaahmed531992.000webhostapp.com/";
    public static String URL_IP = "http://192.168.43.17/";
    //public static String URL_IP = "http://172.16.186.74/";


    //server
    public static String URL_SERVER = URL_IP + "espoir_php/";


    // Server user login url
    public static String URL_LOGIN = URL_SERVER + "login.php";

    // Server user register url
    public static String URL_REGISTER = URL_SERVER + "register.php";
    //CASES
    public static String URL_CASES = "cases/";
    public static String URL_LIST_CASES = URL_SERVER + URL_CASES + "listCases.php";
    public static String URL_ADD_CASE = URL_SERVER + URL_CASES + "addCase.php";

    public static String URL_CASE_THUMBNAIL = URL_SERVER + URL_CASES + "images/";


}
