package com.eagle.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eagle.sdwan.R;

public class CustomDialog extends Dialog {

    /**
     * Creates a dialog window that uses the default dialog theme.
     * <p>
     * The supplied {@code context} is used to obtain the window manager and
     * base theme used to present the dialog.
     *
     * @param context the context in which the dialog should run
     *
     */
    private CustomDialog(@NonNull Context context) {
        super(context);
    }

    /**
     * Creates a dialog window that uses a custom dialog style.
     * <p>
     * The supplied {@code context} is used to obtain the window manager and
     * base theme used to present the dialog.
     * <p>
     * The supplied {@code theme} is applied on top of the context's theme. See
     * <a href="{@docRoot}guide/topics/resources/available-resources.html#stylesandthemes">
     * Style and Theme Resources</a> for more information about defining and
     * using styles.
     *
     * @param context    the context in which the dialog should run
     * @param themeResId a style resource describing the theme to use for the
     *                   window, or {@code 0} to use the default dialog theme
     */
    private CustomDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    private CustomDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }


    public  static class Builder{

        protected Context context;
        private String title;
        private String message;
        private String positiveButtonText;
        private int positiveButtonColor;
        private String negativeButtonText;
        private int negativeButtonTextColor;
        private View contentView;

        private DialogInterface.OnClickListener positiveButtonClickListener,
                negativeButtonClickListener;
        private View layout;

        public Builder(Context context) {
            this.context = context;
        }

        /**
         * Set the Dialog message from String
         *
         * @param message msg
         * @return builder
         */
        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        /**
         * Set the Dialog message from resource
         *
         * @param message msg
         * @return b
         */
        public Builder setMessage(int message) {
            this.message = (String) context.getText(message);
            return this;
        }

        /**
         * Set the Dialog title from resource
         *
         * @param title
         * @return
         */
        public Builder setTitle(int title) {
            this.title = (String) context.getText(title);
            return this;
        }

        /**
         * Set the Dialog title from String
         *
         * @param title
         * @return
         */
        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        /**
         * Set a custom content view for the Dialog. If a message is set, the
         * contentView is not added to the Dialog...
         *
         * @param v
         * @return
         */
        public Builder setContentView(View v) {
            this.contentView = v;
            return this;
        }

        /**
         * Set the positive button resource and it's listener
         *
         * @param positiveButtonText
         * @param listener
         * @return
         */
        public Builder setPositiveButton(int positiveButtonText,
                                         DialogInterface.OnClickListener listener) {
            this.positiveButtonText = (String) context
                    .getText(positiveButtonText);
            this.positiveButtonClickListener = listener;
            return this;
        }

        /**
         * Set the positive button text and it's listener
         *
         * @param positiveButtonText
         * @param listener
         * @return
         */
        public Builder setPositiveButton(String positiveButtonText,
                                         DialogInterface.OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }

        public void setPositiveButtonText(String text) {
            if(null == layout){
                return;
            }
            ((Button) layout.findViewById(R.id.positiveButton)).setText(text);
        }

        public Builder setPositiveButtonTextColor(int color) {
            this.positiveButtonColor = color;
            return this;
        }

        public void setPositiveButtonEnable(boolean enable, int color) {
            if(null == layout){
                return;
            }
            ((Button) layout.findViewById(R.id.positiveButton)).setTextColor(color);
            layout.findViewById(R.id.positiveButton).setClickable(enable);
        }

        /**
         * Set the negative button resource and it's listener
         *
         * @param negativeButtonText
         * @param listener
         * @return
         */
        public Builder setNegativeButton(int negativeButtonText,
                                         DialogInterface.OnClickListener listener) {
            this.negativeButtonText = (String) context
                    .getText(negativeButtonText);
            this.negativeButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButtonTextColor(int nColor) {
            this.negativeButtonTextColor = nColor;
            return this;
        }

        /**
         * Set the negative button text and it's listener
         *
         * @param negativeButtonText
         * @param listener
         * @return
         */
        public Builder setNegativeButton(String negativeButtonText,
                                         DialogInterface.OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }

//        public Builder setCancelable(boolean isCancelable){
//            this.
//            return this;
//        }

        /**
         * Create the custom dialog
         */
        public CustomDialog create()
        {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = inflater.inflate(R.layout.diag_layout, null);
            CustomDialog dialog = new CustomDialog(context,/*android.R.style.Theme_DeviceDefault_Dialog*/
                    R.style.dialog);
            dialog.addContentView(layout, new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            return create(layout, dialog);
        }

        private CustomDialog create(View layout, CustomDialog mDialog) {

            // instantiate the dialog with the custom Theme

            // set the dialog title
            final CustomDialog dialog = mDialog;
            if (title != null) {
                ((TextView) layout.findViewById(R.id.title)).setText(title);
            } else {
                layout.findViewById(R.id.title).setVisibility(View.GONE);
//                layout.findViewById(R.id.iv_top).setVisibility(View.GONE);
            }
            // set the confirm button
            if (positiveButtonText != null) {
                ((Button) layout.findViewById(R.id.positiveButton))
                        .setText(positiveButtonText);
                if(positiveButtonColor != 0)
                {
                    ((Button) layout.findViewById(R.id.positiveButton)).setTextColor(positiveButtonColor);
                }
                layout.findViewById(R.id.positiveButton)
                        .setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                if (positiveButtonClickListener != null) {
                                    positiveButtonClickListener.onClick(dialog,
                                            DialogInterface.BUTTON_POSITIVE);
                                }
                                dialog.dismiss();
                            }
                        });
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.positiveButton).setVisibility(
                        View.GONE);
            }
            // set the cancel button
            if (negativeButtonText != null) {
                ((Button) layout.findViewById(R.id.negativeButton))
                        .setText(negativeButtonText);
                if(negativeButtonTextColor != 0)
                {
                    ((Button) layout.findViewById(R.id.negativeButton)).setTextColor(negativeButtonTextColor);
                }
                layout.findViewById(R.id.negativeButton)
                        .setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                if (negativeButtonClickListener != null) {
                                    negativeButtonClickListener.onClick(dialog,
                                            DialogInterface.BUTTON_NEGATIVE);
                                }
                                dialog.dismiss();
                            }
                        });
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.negativeButton).setVisibility(
                        View.GONE);
            }

            if((null == positiveButtonText) || (null == negativeButtonText))
            {
                layout.findViewById(R.id.iv_bottom_center).setVisibility(View.GONE);
            }
            // set the content message
            if (message != null) {
                ((TextView) layout.findViewById(R.id.message)).setText(message);
            } else {
                layout.findViewById(R.id.message).setVisibility(View.GONE);
            }

            if (contentView != null) {
                // if no message set
                // add the contentView to the dialog body
                ((LinearLayout) layout.findViewById(R.id.content))
                        .removeAllViews();
                ((LinearLayout) layout.findViewById(R.id.content)).addView(
                        contentView, new ViewGroup.LayoutParams(
                                ViewGroup.LayoutParams.WRAP_CONTENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT));
            }
            dialog.setContentView(layout);
            return dialog;
        }

    }

    public void onConfigChanged(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        WindowManager.LayoutParams params = this.getWindow().getAttributes();
        params.width = display.getWidth();
        this.getWindow().setAttributes(params);
    }



    }




