package ready.mumu.interview;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ready.mumu.service.MyAidl;
import ready.mumu.service.myParcelable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_num1 , et_num2 , et_res;
    private Button bt_add , bt_par;

    MyAidl myaidl;

    private ServiceConnection conn = new ServiceConnection() {

        //绑定上服务的时候执行
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            //拿到远程的服务
            Log.v("TAG","绑定成功");
            myaidl = MyAidl.Stub.asInterface(iBinder);
        }

        //当服务断开的时候执行
        @Override
        public void onServiceDisconnected(ComponentName componentName) {

            //回收资源
            myaidl = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        //软件启动就绑定服务
        bindService();
    }

    private void initUI() {
        et_num1 = (EditText) findViewById(R.id.et_num1);
        et_num2 = (EditText) findViewById(R.id.et_num2);
        et_res = (EditText) findViewById(R.id.et_res);
        bt_add = (Button) findViewById(R.id.bt_add);
        bt_par = (Button) findViewById(R.id.bt_par);

        bt_add.setOnClickListener(this);
        bt_par.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if(view == bt_add){
            int num1 = Integer.parseInt(et_num1.getText().toString());
            int num2 = Integer.parseInt(et_num2.getText().toString());

            try {
                //调用远程服务
                int res = myaidl.addnum(num1 , num2);
                et_res.setText(res+"");
            } catch (RemoteException e) {
                e.printStackTrace();
                et_res.setText("报错了");
            }
        }

        if(view == bt_par){

            try {
                String msg = myaidl.readText(new myParcelable("张三",18,"男"));
                et_res.setText(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
                et_res.setText("序列化出错了");
            }

        }

    }

    private void bindService() {
        Log.v("TAG","绑定执行");
        //获取到服务端
        Intent intent = new Intent();
        //5.0之后必须显示intent启动 绑定服务 ， ComponentName两个参数对应是服务包名和服务文件名（文件名必须是包名+文件名）
        intent.setComponent(new ComponentName("ready.mumu.service","ready.mumu.service.Myservice"));
        bindService(intent,conn, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }
}
