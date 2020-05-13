package com.sumit.sbnriassignment;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.regex.Pattern;

/**
 * This class have basic utility methods.
 */
public class Utils {

    public static Dialog dialog;
    public static String EMAIL_REGEX = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    public static String COMMON_PREF = "common_pref";
    public static final int DB_CALL = 1;
    public static final int SERVICE_CALL = 2;

    public static void displayErrorMessage(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
    }

    public static boolean isEmailValid(String email) {
        return Pattern.compile(EMAIL_REGEX).matcher(email).matches();
    }

    public static void showProgressDialog(Context context) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.row_loading);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

    }

    public static void dismissDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static void showInternetMessage(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void updateLoginStatus(Context context, boolean status) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(COMMON_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLoggedIn", status);
        editor.commit();

    }

    public static boolean isUserLoggedInOrNot(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(COMMON_PREF, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("isLoggedIn", false);


    }

    public static void updateDataStoredInDb(Context context, boolean status) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(COMMON_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isDataStoredInDB", status);
        editor.commit();

    }

    public static boolean isDataStoredInDb(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(COMMON_PREF, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("isDataStoredInDB", false);


    }


}
