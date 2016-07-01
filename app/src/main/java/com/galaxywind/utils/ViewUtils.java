package com.galaxywind.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

public final class ViewUtils {
	private static final String TAG = "ViewUtils";
	private static final boolean DEBUG = false;
	/**
	 * 尝试打开软键盘
	 * @param tView 可以是EditText、Button、TextView
	 * @param delay >= 0
	 */
	public static void tryShowSoftInput(final TextView tView, int delay) {
		tView.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				tView.setFocusable(true);
				tView.setFocusableInTouchMode(true);
				tView.requestFocus(0);
				InputMethodManager inputManager = (InputMethodManager)tView.getContext()
														.getSystemService(Context.INPUT_METHOD_SERVICE);
				boolean re = inputManager.showSoftInput(tView, 0);
				
				if (DEBUG)
					android.util.Log.d(TAG, "tryShowSoftInput="+re);
			}
		}, delay);
	}
	
	public static boolean tryHideSoftInput(Context context, View focusView) {
		boolean hideSuccess = false;
		InputMethodManager immManager = 
				(InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
		if (null != focusView && null != focusView.getWindowToken()  
								&& null != immManager) {
			hideSuccess = immManager.hideSoftInputFromWindow(
						focusView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}
		
		return hideSuccess;
	}
	
	
	/**
	 * 
	 * @param strokeColor
	 * @param backgroundColor
	 * @param strokeSize 单位dp
	 * @return GradientDrawable
	 */
	public static GradientDrawable buildShapeStrokeCircleDrawable(int strokeColor, 
										int backgroundColor, float strokeSize) {
		GradientDrawable gradientDrawable = new GradientDrawable();
		gradientDrawable.setShape(GradientDrawable.OVAL);
		gradientDrawable.setColor(backgroundColor);
		
		gradientDrawable.setStroke(
				Math.round(strokeSize * 2), strokeColor);
		
		return gradientDrawable;
	}
	
	/**
	 * 
	 * @param strokeColor
	 * @param backgroundColor
	 * @param strokeSize px
	 * @param corner px
	 * @return
	 */
	public static GradientDrawable buildShapeStrokeDrawable(int strokeColor, 
										int backgroundColor, int strokeSize, int corner) {
		GradientDrawable gradientDrawable = new GradientDrawable();
		gradientDrawable.setShape(GradientDrawable.RECTANGLE);
		gradientDrawable.setCornerRadius(corner);
		gradientDrawable.setStroke(strokeSize, strokeColor);
		gradientDrawable.setColor(backgroundColor);
		return gradientDrawable;
	}
	
	public static ColorStateList buildColorStateListDrawable(int fbdColor, 
							int selectedColor, int pressedColor, int normalColor) {
		int[] colors = new int[] { fbdColor , selectedColor, pressedColor, normalColor};  
        int[][] states = new int[4][];  
        
        states[0] = new int[] { -android.R.attr.state_enabled };  
        states[1] = new int[] { -android.R.attr.state_pressed, android.R.attr.state_selected };  
        states[2] = new int[] { android.R.attr.state_pressed };  
        states[3] = new int[] {};
    	
    	return new ColorStateList(states, colors);
	}
	
	public static StateListDrawable buildStateListDrawable(Drawable fbdDrawable, Drawable selectedDrawable,
								Drawable pressedDrawable, Drawable normalDrawable) {
    	StateListDrawable sdDrawable = new StateListDrawable();
    	
    	if (null != fbdDrawable) {
    		sdDrawable.addState(new int[]{-android.R.attr.state_enabled}, fbdDrawable);
    	}
    	
    	if (null != selectedDrawable) {
    		sdDrawable.addState(new int[]{android.R.attr.state_selected,
					-android.R.attr.state_pressed}, selectedDrawable);
    	}
    	if (null != pressedDrawable) {
    		sdDrawable.addState(new int[]{android.R.attr.state_pressed}, pressedDrawable);
    	}
    	if (null != normalDrawable) {
    		sdDrawable.addState(new int[]{}, normalDrawable);
    	}
    	
    	return sdDrawable;
	}
	
	public static StateListDrawable buildFocuseStateDrawable(Drawable fbdDrawable,
											Drawable focusedDrawable, Drawable normalDrawable) {
		StateListDrawable sdDrawable = new StateListDrawable();
    	
    	if (null != fbdDrawable) {
    		sdDrawable.addState(new int[]{-android.R.attr.state_enabled}, fbdDrawable);
    	}
    	
    	if (null != focusedDrawable) {
    		sdDrawable.addState(new int[]{android.R.attr.state_focused}, focusedDrawable);
    	}
    	if (null != normalDrawable) {
    		sdDrawable.addState(new int[]{}, normalDrawable);
    	}
    	
    	return sdDrawable;
	}
	
	/**
	 * 传0 那么该状态无效
	 * @return 如果都为参数都为0 那么返回null
	 */
	public static StateListDrawable buildColorBackgroundDrawable(int fdbColor, 
								int selectedColor, int pressedColor, int normalColor) {
		return buildStateListDrawable(fdbColor != 0 ? new ColorDrawable(fdbColor) : null,
									selectedColor != 0 ? new ColorDrawable(selectedColor) : null,
									pressedColor != 0 ? new ColorDrawable(pressedColor) : null,
									normalColor != 0 ? new ColorDrawable(normalColor) : null);
	}
}
