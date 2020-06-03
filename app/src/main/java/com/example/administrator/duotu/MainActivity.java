package com.example.administrator.duotu;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.duotu.okhttp.CallBackUtil;
import com.example.administrator.duotu.okhttp.OkhttpUtil;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.gyf.barlibrary.ImmersionBar;
import com.hjm.bottomtabbar.BottomTabBar;
import com.wildma.pictureselector.PictureSelector;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import okhttp3.Call;

public class MainActivity extends AppCompatActivity {

    private ImageView image;
    int overPosition;
    private float mPosX, mPosY, mCurPosX, mCurPosY;
    private BottomTabBar bottomtabbar;
    String image1 = "0";
    private Button tijiao;
    private String url;
    private Button jiema;
    private String s;
    private ImageView image123;
    String tupianbe="base64,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";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImmersionBar.with(this).init();

        //String url="http://api.tianapi.com/bulletin/index?key=860e6cc9efdf69b8e27b42618850c78c";
        //String url="http://localhost:8080/ZTZJ-ZHGYLDSJGKFXPT/mobile/qyxx/getCpbh";
        //String url="http://a42778c0.ngrok.io/ZTZJ-ZHGYLDSJGKFXPT/mobile/qyxx/getCpbh";
        url = "http://10.10.1.144:8080/ZTZJ-ZHGYLDSJGKFXPT/mobile/qyxx/addQyxx";
        Button button1 = findViewById(R.id.button1);
        image = findViewById(R.id.image);
        bottomtabbar = findViewById(R.id.bottomtabbar);
        tijiao = findViewById(R.id.tijiao);
        jiema = findViewById(R.id.jiema);
        image123 = findViewById(R.id.image123);
        jiema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap = stringToBitmap("base64,"+s);
                image123.setImageBitmap(bitmap);
            }
        });
        bottomtabbar.init(getSupportFragmentManager())

                .setImgSize(35, 35)
                .setFontSize(10)
                .setChangeColor(Color.RED, Color.GRAY)
                .addTabItem("页1", R.mipmap.ic_launcher, R.mipmap.ic_launcher_round, Fragment1.class)
                .addTabItem("页2", R.mipmap.ic_launcher, R.mipmap.ic_launcher_round, Fragment2.class)
                .addTabItem("页3", R.mipmap.ic_launcher, R.mipmap.ic_launcher_round, Fragment3.class)
                .isShowDivider(false);

        requsetPermission();
        tijiao.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {
                //图片
                 // s = MainActivity.imageToBase64(image1);
                s = getBitmapStrBase64(BitmapFactory.decodeFile(image1));

                HashMap<String, String> stringStringHashMap = new HashMap<>();
                stringStringHashMap.put("qymc", "123");
                stringStringHashMap.put("sjh", "13453976792");
                stringStringHashMap.put("dlmm", "123213");

                stringStringHashMap.put("qyzz", "base64,"+s);
                stringStringHashMap.put("jdxx", "base64,"+s);
                // File file = new File(image1);
 /*               HashMap<String, File> stringFileHashMap = new HashMap<>();


                stringFileHashMap.put("qyzz",file);
                stringFileHashMap.put("jdxx",file);
                stringFileHashMap.put("qyzz",file);*/

                Log.i("mainacokhttp", "onClick:图片的beas64 " + s);
               /* OkhttpUtil.okHttpPost(url, stringStringHashMap, new CallBackUtil.CallBackString() {
                    @Override
                    public void onFailure(Call call, Exception e) {
                        Log.i("mainacokhttp", "onFailure: 失败"+e);
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.i("mainacokhttp", "onFailure: 成功"+response);
                    }
                });
*/


           /*     OkhttpUtil.okHttpUploadMapFile(url, stringFileHashMap, "image", stringStringHashMap, new CallBackUtil.CallBackString() {
                    @Override
                    public void onFailure(Call call, Exception e) {
                        Log.i("mainacokhttp", "onFailure: 失败"+e);
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.i("mainacokhttp", "onFailure: 成功"+response);
                    }
                });*/

            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*new IntentIntegrator(MainActivity.this)
                        .setOrientationLocked(false)//解锁屏幕方向锁定

                        .setCameraId(0)//前置或后置摄像头
                        .initiateScan();*/
                PictureSelector
                        .create(MainActivity.this, PictureSelector.SELECT_REQUEST_CODE)
                        .selectPicture(false, 200, 200, 1, 1);

            }
        });


       /* OkhttpUtil.okHttpGet(url, new CallBackUtil.CallBackString() {
                    @Override
                    public void onFailure(Call call, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        bean bean = gson.fromJson(response, bean.class);
                        int code = bean.getCode();
                        if (code == 200) {
                            Toast.makeText(MainActivity.this, bean.getNewslist().size() + "", Toast.LENGTH_SHORT).show();
                        }
                    }
                });*/



        /*OkhttpUtil.okHttpPost(url,stringStringHashMap, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                Log.i("mainac111","22222222"+e);
            }

            @Override
            public void onResponse(String response) {
                Log.i("mainac111","22222222"+response);
                Gson gson = new Gson();
                ZhuceAcBean bean = gson.fromJson(response, ZhuceAcBean.class);
                int code = bean.getCode();
                String result = bean.getResult();
                Log.i("mainac111",result+"");
            }
        });*/



               /* OkHttp.getAsync(url, new OkHttp.DataCallBack() {
                    @Override
                    public void requestFailure(Request request, IOException e) {

                        Toast.makeText(MainActivity.this, "数据获取失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void requestSuccess(String result) throws Exception {
                        Gson gson = new Gson();
                        bean bean = gson.fromJson(result, bean.class);
                        int code = bean.getCode();
                        if (code == 200) {
                            Toast.makeText(MainActivity.this, bean.getNewslist().size() + "", Toast.LENGTH_SHORT).show();

                        }
                    }
                });*/

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //扫描成功
            if (result.getContents() == null) {
                //结束扫码
                Toast.makeText(this, "取消", Toast.LENGTH_SHORT).show();
            } else {
                //扫码出结果
                String result1 = result.getContents();
                Log.d("code", result1);
                Toast.makeText(this, result1, Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
        super.onActivityResult(requestCode, resultCode, data);
        /*结果回调*/
        if (requestCode == PictureSelector.SELECT_REQUEST_CODE) {
            if (data != null) {
                String picturePath = data.getStringExtra(PictureSelector.PICTURE_PATH);
                image.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                image1 = picturePath;
                Log.i("mainacokhttp", "onFailure: 图片路径" + image1);
                /*如果使用 Glide 加载图片，则需要禁止 Glide 从缓存中加载，因为裁剪后保存的图片地址是相同的*/
                /*RequestOptions requestOptions = RequestOptions
                        .circleCropTransform()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true);
                Glide.with(this).load(picturePath).apply(requestOptions).into(mIvImage);*/
            }
        }
    }

    //权限
    private void requsetPermission() {
        if (Build.VERSION.SDK_INT > 22) {
            if (ContextCompat.checkSelfPermission(MainActivity.this,
                    android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                //先判断有没有权限 ，没有就在这里进行权限的申请
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{android.Manifest.permission.CAMERA}, 1);

            } else {

            }
        } else {

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //这里已经获取到了摄像头的权限，想干嘛干嘛了可以

                } else {
                    //这里是拒绝给APP摄像头权限，给个提示什么的说明一下都可以。
                    Toast.makeText(MainActivity.this, "请手动打开相机权限", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }

    }
   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        *//*结果回调*//*
        if (requestCode == PictureSelector.SELECT_REQUEST_CODE) {
            if (data != null) {
                String picturePath = data.getStringExtra(PictureSelector.PICTURE_PATH);
                image.setImageBitmap(BitmapFactory.decodeFile(picturePath));

                *//*如果使用 Glide 加载图片，则需要禁止 Glide 从缓存中加载，因为裁剪后保存的图片地址是相同的*//*
     *//*RequestOptions requestOptions = RequestOptions
                        .circleCropTransform()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)

                        .skipMemoryCache(true);
                Glide.with(this).load(picturePath).apply(requestOptions).into(mIvImage);*//*
            }
        }
    }*/

    /**
     * 将图片转换成Base64编码的字符串
     */
    public static String imageToBase64(String path) {
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        InputStream is = null;
        byte[] data = null;
        String result = null;
        try {
            is = new FileInputStream(path);
            //创建一个字符流大小的数组。
            data = new byte[is.available()];
            //写入数组
            is.read(data);
            //用默认的编码格式进行编码
            result = Base64.encodeToString(data, Base64.NO_WRAP);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return result;
    }

    private String getBitmapStrBase64(Bitmap bitmap) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        byte[] bytes = bos.toByteArray();
        String string = Base64.encodeToString(bytes, Base64.DEFAULT);
        return string;
    }
    /*public void encodeImage(Bitmap bitmap){
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    //读取图片到ByteArrayOutputStream
    bitmap.compress(Bitmap.CompressFormat.PNG, 40, baos); //参数如果为100那么就不压缩
    byte[] bytes = baos.toByteArray();
    String strbm = Base64.encodeToString(bytes,Base64.DEFAULT);
  }*/

    public Bitmap stringToBitmap(String string) {
        Bitmap bitmap = null;
        try {
            byte[] bitmapArray = Base64.decode(string.split(",")[1], Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
