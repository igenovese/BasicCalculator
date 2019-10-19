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

    /**
     * Override onCreate method, called when the activity is
     * started.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get every Button by id
        mPlusButton = (Button) findViewById(R.id.button_op_plus);
        mMinusButton= (Button) findViewById(R.id.button_op_minus);
        mMultButton = (Button) findViewById(R.id.button_op_mult);
        mDivButton  = (Button) findViewById(R.id.button_op_div);
        mPctButton  = (Button) findViewById(R.id.button_op_pct);
        mSqrtButton = (Button) findViewById(R.id.button_op_sqrt);

        //Get Operands EditText by id
        mOp1        = (EditText) findViewById(R.id.editText_op1);
        mOp2        = (EditText) findViewById(R.id.editText_op2);

        //Get Result TextView by id
        mResult     = (TextView) findViewById(R.id.textView_result);


        //For every button, create an onClick listener, check operands and if they're
        //correct, calculate and display result; otherwise, display Toast error message
        mPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Plus needs both operands
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
                //Minus needs both operands
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
                //Multiplication needs both operands
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
                //Division needs both operands and Operand 2 shouln't be 0
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
                //Percentage only needs first operand
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
                //Square Root only needs first operand
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

    /**
     *
     * Checks if the required input strings are valid.  The caller passes in parameters
     * to specify which values to check.  The assumption, here, is that the user wil
     * enter two values and press the appropriate button to perform the
     * desired calculation
     *
     * @param NeedOp1		true if the calculation needs a valid operand 1
     * @param NeedOp2		true if the calculation needss a valid operand 2
     *
     * @return			    true if the required inputs are valid, false otherwise
    **/
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
            try {
                if (Double.parseDouble(op2) < 0.0)
                    return false;
                if (NeedOp2Not0) {
                    if (Double.parseDouble(op2) <= 0.0)
                        return false;
                }
            } catch( NumberFormatException e){
                Log.e( TAG, "Exception raised when trying to get Doubles from String operands");
            }
        }

        return true;
    }

    /**
     * Displays a Toast Message indicating that some of the operands were incorrect, clears them
     * and set Focus for entering new operands.
     */
    public void makeErrorToast(){
        mOp1.setText("");
        mOp2.setText("");
        Toast.makeText( MainActivity.this, "Input Error: check operands for the desired operation", Toast.LENGTH_SHORT).show();
        mOp1.requestFocus(); //Set Focus to enter errand operands
    }
}
