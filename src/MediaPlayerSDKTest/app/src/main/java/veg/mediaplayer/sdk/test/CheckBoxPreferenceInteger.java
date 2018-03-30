/*
 *
  * Copyright (c) 2011-2017 VXG Inc.
 *
 */


package veg.mediaplayer.sdk.test;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.CheckBoxPreference;
import android.util.AttributeSet;
import android.util.Log;

public class CheckBoxPreferenceInteger extends CheckBoxPreference 
{
	public static final String TAG = "CheckBoxPreferenceInteger";
	
    public CheckBoxPreferenceInteger(Context context) 
    {
        super(context);
    }

    public CheckBoxPreferenceInteger(Context context, AttributeSet attrs) 
    {
        super(context, attrs);
    }

    public CheckBoxPreferenceInteger(Context context, AttributeSet attrs, int defStyle) 
    {
        super(context, attrs, defStyle);
    }

    @Override
    protected boolean getPersistedBoolean(boolean defaultReturnValue) 
    {
    	int defaultValueInt = (defaultReturnValue ? 1 : 0);
    	
    	if (!shouldPersist()) {
    	     return defaultReturnValue;
    	}
    	
        int returnValue = getSharedPreferences().getInt(getKey(), defaultValueInt);
        Log.v(TAG, "getPersistedBoolean: " + getKey() + returnValue);
        
    	return (getSharedPreferences().getInt(getKey(), defaultValueInt) == 1);
    }
 
    @Override
    protected boolean persistBoolean(boolean value) 
    {
    	int valueInt = (value ? 1 : 0);
    	SharedPreferences.Editor editor = getSharedPreferences().edit();    	
    	editor.putInt(getKey(), valueInt);
    	return editor.commit();
    }

    @Override
    protected String getPersistedString(String defaultReturnValue) 
    {
        return String.valueOf(getPersistedInt(-1));
    }

    @Override
    protected boolean persistString(String value) 
    {
        return persistInt(Integer.valueOf(value));
    }
}