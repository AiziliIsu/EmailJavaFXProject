package com.example.emailclientcourse.view;

public enum ColorTheme {
    Light,
    Default,
    Dark;

    public static String getCssPath(ColorTheme colorTheme){
        switch(colorTheme){
            case Light:
                return "css/themeLight.css";
            case Default:
                return "css/themeDefault.css";
            case Dark:
                return "css/themeDark.css";
            default:
                return null;
        }
    }
}
