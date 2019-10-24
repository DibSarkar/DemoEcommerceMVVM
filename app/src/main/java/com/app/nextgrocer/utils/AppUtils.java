package com.app.nextgrocer.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.DisplayMetrics;

/**
 * Created by web112 on 24/demo_ten/17.
 */

public class AppUtils {

    public static float getDeviceDensityScale(Context context) {
        float densityScale = context.getResources().getDisplayMetrics().density;
        return densityScale;
    }

    /*public static String getMimeTypeFromFile(File file) {
        Uri uri = Uri.fromFile(file);
        String fileExtension = MimeTypeMap.getFileExtensionFromUrl(uri.toString());
        String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtension);
        return mimeType;
    }*/

    public final static boolean isValidEmail(CharSequence email) {
        if (email == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }

    public static void dialPhoneNumber(Context context, String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }

    public static void composeSmsMessage(Context context, String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:" + phoneNumber));  // This ensures only SMS apps respond
        //intent.putExtra("sms_body", message);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }


        public static int calculateNoOfColumns(Context context, float columnWidthDp) { // For example columnWidthdp=180
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            float screenWidthDp = displayMetrics.widthPixels / displayMetrics.density;
            int noOfColumns = (int) (screenWidthDp / columnWidthDp + 0.5); // +0.5 for correct rounding to int.
            return noOfColumns;

    }

}
