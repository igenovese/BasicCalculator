package ece558.rgenovese.basiccalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "BasicCalculator";

    private Button mPlusButton;
    private Button mMinusButton;
    private Button mMultButton;
    private Button mDivButton;
    private Button mPctButton;
    private Button mSqrtButton;

    private EditText mOp1;
    private EditText mOp2;

    private TextView mResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPlusButton = (Button) findViewById(R.id.button_op_plus);
        mMinusButton= (Button) findViewById(R.id.button_op_minus);
        mMultButton = (Button) findViewById(R.id.button_op_mult);
        mDivButton  = (Button) findViewById(R.id.button_op_div);
        mPctButton  = (Button) findViewById(R.id.button_op_pct);
        mSqrtButton = (Button) findViewById(R.id.button_op_sqrt);

        mOp1        = (EditText) findViewById(R.id.editText_op1);
        mOp2        = (EditText) findViewById(R.id.editText_op2);

        mResult     = (TextView) findViewById(R.id.textView_result);


        mPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( !isValidInput( true, true, false )){
                    makeErrorToast();
                }
                else{
                    double result = Double.valueOf( mOp1.getText().toString()) + Double.valueOf( mOp2.getText().toString() );
                    mResult.setText( String.format("%.03f", result));
                }

            }
        });

        mMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( !isValidInput( true, true, false )){
                    makeErrorToast();
                }
                else{
                    double result = Double.valueOf( mOp1.getText().toString()) - Double.valueOf( mOp2.getText().toString() );
                    mResult.setText( String.format("%.03f", result));
                }

            }
        });

        mMultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( !isValidInput( true, true, false )){
                    makeErrorToast();
                }
                else{
                    double result = Double.valueOf( mOp1.getText().toString()) * Double.valueOf( mOp2.getText().toString() );
                    mResult.setText( String.format("%.03f", result));
                }

            }
        });

        mDivButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( !isValidInput( true, true, true )){
                    makeErrorToast();
                }
                else{
                    double result = Double.valueOf( mOp1.getText().toString()) / Double.valueOf( mOp2.getText().toString() );
                    mResult.setText( String.format("%.03f", result));
                }

            }
        });

        mPctButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( !isValidInput( true, false, false )){
                    makeErrorToast();
                }
                else{
                    double result = Double.valueOf( mOp1.getText().toString()) /100.0 ;
                    mResult.setText( String.format("%.03f", result));
                }

            }
        });

        mSqrtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( !isValidInput( true, false, false )){
                    makeErrorToast();
                }
                else{
                    double result = Math.sqrt( Double.valueOf( mOp1.getText().toString())   );
                    mResult.setText( String.format("%.03f", result));
                }

            }
        });

    }

    public boolean isValidInput( boolean NeedOp1, boolean NeedOp2, boolean NeedOp2Not0 ){
        String op1 = mOp1.getText().toString();
        String op2 = mOp2.getText().toString();

        if( NeedOp1 ){
            if ( op1.length() == 0 )
                return false;
            else if( Double.parseDouble( op1 ) < 0.0 )
                return false;
        }

        if( NeedOp2 ){
            if ( op2.length() == 0 )
                return false;
            if( Double.parseDouble( op2 ) < 0.0 )
                return false;
            if( NeedOp2Not0 ) {
                if (Double.parseDouble(op2) <= 0.0)
                    return false;
            }
        }

        return true;
    }

    public void makeErrorToast(){
        Toast.makeText( MainActivity.this, "Input Error: Voltage or Current is incorrect", Toast.LENGTH_SHORT).show();
    }
}
