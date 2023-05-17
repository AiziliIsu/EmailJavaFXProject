package com.example.emailclientcourse.view;

public enum FontSize {
    Small,
    Medium,
    Big;

    public static String getCssPath(FontSize fontSize){
        switch(fontSize){
            case Medium:
                return "css/fontSmall.css";
            case Big:
                return "css/fontBig.css";
            case Small:
                return "css/fontMedium.css";
            default:
                return null;
        }
    }
}
