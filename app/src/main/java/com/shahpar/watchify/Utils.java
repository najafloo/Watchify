package com.shahpar.watchify;

public class Utils {

    final public static String WHATSAPP_BASE_URL = "https://wa.me/";
    final public static String TELEGRAM_BASE_URL = "https://t.me/";

    public static boolean isNUllOrEmpty(String str) {
        return (str == null || str.isEmpty());
    }

    public static String normalizeUserProfileSocialNetworkUrl(String url) {
        url = url.trim();
        String socialId;

        url.replace("//", "/");

        if (!isNUllOrEmpty(url) && url.charAt(url.length() - 1) == '/') {
            url = url.substring(0, url.length() - 1);
        }

        socialId = url.substring(url.lastIndexOf('/') + 1);

        return socialId;
    }

    public static String normalMobileNumberToGlobal(String mobile) {
        String np = mobile.trim();
        mobile = mobile.trim();

        if (mobile.length() == 10 && mobile.charAt(0) == '9') {
            np = "+98" + mobile;
        } else if (mobile.length() == 11 && mobile.charAt(0) == '0') {
            np = "+98" + mobile.substring(1);
        }

        return np;
    }

    public static String makeWhatsAppLink(String mobileNumber) {
        return WHATSAPP_BASE_URL + normalMobileNumberToGlobal(mobileNumber);
    }

    public static String makeTelegramLink(String telegramId) {
        return TELEGRAM_BASE_URL + normalizeUserProfileSocialNetworkUrl(telegramId);
    }
}
