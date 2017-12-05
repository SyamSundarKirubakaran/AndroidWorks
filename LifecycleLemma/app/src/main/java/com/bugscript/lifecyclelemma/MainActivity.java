package com.bugscript.lifecyclelemma;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText dataGot;
    TextView dataSavedInTextView;
    ImageView status;
    private static final String LIFE_CYCLE_CALLBACK_KEY="callbacks";

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        String lifecycle_transitions=dataSavedInTextView.getText().toString().trim();
        outState.putString(LIFE_CYCLE_CALLBACK_KEY,lifecycle_transitions);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataGot=findViewById(R.id.editText);
        status=findViewById(R.id.imageView);
        dataSavedInTextView=findViewById(R.id.textView);


        if(savedInstanceState!=null){
            if(savedInstanceState.containsKey(LIFE_CYCLE_CALLBACK_KEY)){
                dataSavedInTextView.setText(savedInstanceState
                        .getString(LIFE_CYCLE_CALLBACK_KEY)+"");
            }
        }



        dataGot.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(dataGot.getText().toString().length()>0){
                    status.setImageResource(R.drawable.ic_check_black_24dp);
                    dataSavedInTextView.setText(dataGot.getText().toString().trim()+"");
                }else{
                    status.setImageResource(R.drawable.ic_close_black_24dp);
                    dataSavedInTextView.setText("Nothing to Display!");
                }
            }
        });


    }
}
