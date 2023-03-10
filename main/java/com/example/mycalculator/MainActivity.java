package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView calculation,solution;
    Button btn9, btn8,btn7,btn6,btn5,btn4,btn3,btn2,btn1,btn0,
            btnplus,btnminus,btnmultiply,btndivide,btnequals,btnclear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calculation=findViewById(R.id.input);
        solution=findViewById(R.id.result);
        assign(btn9,R.id.button9);
        assign(btn8,R.id.button8);
        assign(btn7,R.id.button7);
        assign(btn6,R.id.button6);
        assign(btn5,R.id.button5);
        assign(btn4,R.id.button4);
        assign(btn3,R.id.button3);
        assign(btn2,R.id.button2);
        assign(btn1,R.id.button1);
        assign(btn0,R.id.button0);
        assign(btnplus,R.id.plus);
        assign(btnminus,R.id.buttonminus);
        assign(btndivide,R.id.divide);
        assign(btnmultiply,R.id.multiply);
        assign(btnequals,R.id.equals);
        assign(btnclear,R.id.point);
    }
    void assign(Button btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Button button=(Button) view;
        String text= button.getText().toString();
//        calculation.setText(text);
        String data= calculation.getText().toString();
        data=data+text;
        calculation.setText(data);
        if(text.equals("=")){
            calculation.setText((solution.getText()));
        }
        if(text.equals("C")){
            calculation.setText("");
            solution.setText("0");
        }
        String finall=Result(data);
        if(!finall.equals("Galat kiya")) solution.setText(finall);
    }
    String Result(String data){
        try{
            Context context= Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable= context.initStandardObjects();
            String finalRes=context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            return finalRes;
        } catch (Exception e){
            return "Galat kiya";
        }
    }

}