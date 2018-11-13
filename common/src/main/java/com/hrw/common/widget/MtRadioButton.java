package com.hrw.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.List;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/11/13 10:57
 * @desc:
 */
public class MtRadioButton extends RadioGroup {
    boolean isShowButton;
    boolean isOpenAnimat;

    List<String> mRadioLabel;
    Context mContext;

    public MtRadioButton(Context context) {
        this(context, null);
    }

    public MtRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    private void setRadioLabel(List<String> radioLabel) {
        mRadioLabel = radioLabel;
        for (String label : radioLabel) {
            RadioButton button = new RadioButton(mContext);
            LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            button.setLayoutParams(params);
        }
    }

}
