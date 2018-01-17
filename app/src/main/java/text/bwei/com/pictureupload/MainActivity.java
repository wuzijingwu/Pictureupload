package text.bwei.com.pictureupload;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import text.bwei.com.pictureupload.mine.bean.view.LoginActivity;
import text.bwei.com.pictureupload.touxiang.ImageUtils;
import text.bwei.com.pictureupload.touxiang.MessageBean;
import text.bwei.com.pictureupload.touxiang.NewsPresenter;
import text.bwei.com.pictureupload.touxiang.NewsView;

public class MainActivity extends AppCompatActivity implements NewsView {

    @BindView(R.id.tv_name_back)
    TextView tvNameBack;
    @BindView(R.id.rbspsc)
    RadioButton rbspsc;
    @BindView(R.id.rbdpsc)
    RadioButton rbdpsc;
    @BindView(R.id.rbwdzj)
    RadioButton rbwdzj;
    @BindView(R.id.rbwfk)
    RadioButton rbwfk;
    @BindView(R.id.rbdfh)
    RadioButton rbdfh;
    @BindView(R.id.rbdsh)
    RadioButton rbdsh;
    @BindView(R.id.rbdpj)
    RadioButton rbdpj;
    @BindView(R.id.rbtk)
    RadioButton rbtk;
    @BindView(R.id.rbyfk)
    RadioButton rbyfk;
    @BindView(R.id.rbczk)
    RadioButton rbczk;
    @BindView(R.id.rbdjq)
    RadioButton rbdjq;
    @BindView(R.id.rbhb)
    RadioButton rbhb;
    @BindView(R.id.rbjf)
    RadioButton rbjf;
    @BindView(R.id.shouhuodizhi)
    RadioButton shouhuodizhi;
    @BindView(R.id.image_iii)
    ImageView imageIii;
    private String name;

    private File tempFile;
    private static final String PHOTO_FILE_NAME = "temp_photo.jpg";
    private static final int PHOTO_REQUEST_CAREMA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果
    private NewsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new NewsPresenter();
        presenter.attachView(this);

    }

    @Override
    public void onStart() {
        super.onStart();
        SharedPreferences user = getSharedPreferences("USER", Context.MODE_PRIVATE);
        name = user.getString("name", "000");
        if (!name.equals("000")) {
            tvNameBack.setText(name);
        } else {
            tvNameBack.setText("点击登录");
        }
    }


    @OnClick({R.id.image_iii, R.id.tv_name_back, R.id.rbspsc, R.id.rbdpsc, R.id.rbwdzj, R.id.rbwfk, R.id.rbdfh, R.id.rbdsh, R.id.rbdpj, R.id.rbtk, R.id.rbyfk, R.id.rbczk, R.id.rbdjq, R.id.rbhb, R.id.rbjf, R.id.shouhuodizhi})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.image_iii:
//                Intent in1 = new Intent(this, LoginActivity.class);
//                startActivity(in1);
                Intent intent1 = new Intent(Intent.ACTION_PICK);
                intent1.setType("image/*");
                // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_GALLERY
                startActivityForResult(intent1, PHOTO_REQUEST_GALLERY);



                break;
            case R.id.tv_name_back:

                Intent in = new Intent(this, LoginActivity.class);
                startActivity(in);

                break;
            case R.id.rbspsc:
                break;
            case R.id.rbdpsc:
                break;
            case R.id.rbwdzj:
                break;
            case R.id.rbwfk:
                break;
            case R.id.rbdfh:
                break;
            case R.id.rbdsh:
                break;
            case R.id.rbdpj:
                break;
            case R.id.rbtk:
                break;
            case R.id.rbyfk:
                break;
            case R.id.rbczk:
                break;
            case R.id.rbdjq:
                break;
            case R.id.rbhb:
                break;
            case R.id.rbjf:
                break;
            case R.id.shouhuodizhi:
                break;
        }
    }



    /*
* 判断sdcard是否被挂载
*/
    private boolean hasSdcard() {
        //判断ＳＤ卡手否是安装好的　　　media_mounted
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }
    /*
    * 剪切图片
    */
    private void crop(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);

        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PHOTO_REQUEST_GALLERY) {
            // 从相册返回的数据
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                crop(uri);
            }
        } else if (requestCode == PHOTO_REQUEST_CAREMA) {
            // 从相机返回的数据
            if (hasSdcard()) {
                crop(Uri.fromFile(tempFile));
            } else {
                Toast.makeText(MainActivity.this, "未找到存储卡，无法存储照片！", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == PHOTO_REQUEST_CUT) {
            // 从剪切图片返回的数据
            if (data != null) {
                Bitmap bitmap = data.getParcelableExtra("data");
                /**
                 * 获得图片
                 */
                imageIii.setImageBitmap(bitmap);
                setImgByStr( bitmap);
            }
            try {
                // 将临时文件删除
                tempFile.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    /**
     * 上传头像
     */
    public  void setImgByStr(Bitmap bitmap) {
        if(bitmap != null){
            // 拿着imagePath上传了
            // ...
        }
        String imagePath = ImageUtils.savePhoto(bitmap, Environment
                .getExternalStorageDirectory().getAbsolutePath(), String
                .valueOf(System.currentTimeMillis()));
        Log.d("zxz","imagePath:"+imagePath);
        if(imagePath!=null){
            File file=new File(imagePath);//将要保存图片的路径

            try {
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                bos.flush();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            RequestBody photoRequestBody = RequestBody.create(MediaType.parse("image/png"), file);
            MultipartBody.Part photo = MultipartBody.Part.createFormData("file", file.getName(), photoRequestBody);
            presenter.getData("71",photo);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onSuccess(MessageBean messageBean) {
        String msg = messageBean.getMsg();
        Log.e("zxz",msg);
    }















}
