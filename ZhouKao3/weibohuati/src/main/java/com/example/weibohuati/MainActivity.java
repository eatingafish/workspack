package com.example.weibohuati;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private com.example.weibohuati.MyEditText ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed = findViewById(R.id.ed);
        ed.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //System.out.println( s.charAt(start));
                if(s.length() == start){

                }else{
                    if("#".toCharArray()[0] == s.charAt(start)){
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        //Dialog dialog = builder.show();
                        builder.setIcon(R.drawable.ic_launcher_background)
                                .setTitle("choose the topic!")
                                .setPositiveButton("ensure", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.dismiss();
                                        Intent intent = new Intent(MainActivity.this,ChooseActivity.class);
                                        startActivityForResult(intent, 1);
                                    }
                                })
                                .create()
                                .show();
                        //System.out.println("equals");
                        //Toast.makeText(MainActivity.this, "you input an a!!", Toast.LENGTH_SHORT).show();
                    }
                }
                //System.out.println(start + " " + before);
                //System.out.println("the word you input this time:" + " " + s.charAt(start));
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                //System.out.println(count);
                //lastInputString = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO 自动生成的方法存根

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1 && resultCode == 0){
            String topic = "#" + data.getExtras().getString("topic") + "#";
            ed.insertTopic(topic);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
