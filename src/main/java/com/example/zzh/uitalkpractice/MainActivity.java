package com.example.zzh.uitalkpractice;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private ListView msgListView;

    private EditText inputText;

    private Button send;

    private MsgAdapter adapter;

    private List<Msg> msgList = new ArrayList<Msg>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMsg();
        adapter = new MsgAdapter(MainActivity.this, R.layout.msg_item, msgList);
        inputText = (EditText) findViewById(R.id.input_text);
        send = (Button) findViewById(R.id.send);
        msgListView = (ListView) findViewById(R.id.msg_list_view);
        msgListView.setAdapter(adapter);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = inputText.getText().toString();
                if (!"".equals(content)) {
                    Msg msg = new Msg(Msg.TYPE_SEND, content);
                    msgList.add(msg);
                    //当有新消息，刷新ListView的显示
                    adapter.notifyDataSetChanged();
                    //将listview定位到最后一项
                    msgListView.setSelection(msgList.size());
                    inputText.setText("");
                }
            }
        });
    }

    private void initMsg() {
        Msg msg1 = new Msg(Msg.TYPE_RECEIVED, "Hello guy");
        msgList.add(msg1);
        Msg msg2 = new Msg(Msg.TYPE_SEND, "Hello. Who is that?");
        msgList.add(msg2);
        Msg msg3 = new Msg(Msg.TYPE_RECEIVED, "This is Tom. Nice talking to you.");
        msgList.add(msg3);
    }

}
