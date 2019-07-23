package com.example.mycal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import nguyenvanquan7826.com.Balan;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtNumber;
    private TextView tvResult;

    private int[] idButton = {
            R.id.b0,
            R.id.b1, R.id.b2, R.id.b3,
            R.id.b4, R.id.b5, R.id.b6,
            R.id.b7, R.id.b8, R.id.b9,
            R.id.bphay,
            R.id.bplus, R.id.bminus, R.id.bmultiply, R.id.bdivided,
            R.id.bc, R.id.bequal
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectView();
    }

    private void connectView() {
        edtNumber = (EditText) findViewById(R.id.edt_number);
        tvResult = (TextView) findViewById(R.id.tv_result);

        for (int i = 0; i < idButton.length; i++) {
            findViewById(idButton[i]).setOnClickListener(this);
        }
        init();
    }
    private void init() {
        edtNumber.setText("|");
        tvResult.setText("0");
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();

        // check button number and button operator
        for (int i = 0; i < idButton.length - 2; i++) {
            if (id == idButton[i]) {
                String text = ((Button) findViewById(id)).getText().toString();

                // clear char | on top
                if (edtNumber.getText().toString().trim().equals("|")) {
                    edtNumber.setText("");
                }

                edtNumber.append(text);
                return;
            }
        }

        // Delete Number
        if (id == R.id.bc) {
            BaseInputConnection textFieldInputConnection = new BaseInputConnection(edtNumber, true);
            textFieldInputConnection.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
            return;
        }
        // calculation
        if (id == R.id.bequal) {
            cal();
        }
        if (id == R.id.bac){
            edtNumber.setText("");
        }
    }

    private void cal() {
        String math = edtNumber.getText().toString().trim();
        if (math.length() > 0) {
            Balan balan = new Balan();
            String result = balan.valueMath(math) + "";
            String error = balan.getError();

            // check error
            if (error != null) {
                tvResult.setText(error);
            } else { // show result
                tvResult.setText(result);
            }
        }
    }
}
