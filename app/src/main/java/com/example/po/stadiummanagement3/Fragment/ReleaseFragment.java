package com.example.po.stadiummanagement3.Fragment;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.po.stadiummanagement3.Activity.ReleaseActivity;
import com.example.po.stadiummanagement3.BuildConfig;
import com.example.po.stadiummanagement3.R;
import com.example.po.stadiummanagement3.WebService.CameraService;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 13701 on 2018/1/10.
 */

public class ReleaseFragment extends Fragment {

    @BindView(R.id.et_main_3)
    EditText mItemName;
    @BindView(R.id.et_desc)
    EditText mItemDesc;
    @BindView(R.id.et_price)
    EditText mItemPirce;
    @BindView(R.id.item_taken_photo)                                //这是拍照的界面
    ImageView mPhotoView;
    @BindView(R.id.item_camera)
    ImageButton mImageButton;

    private String mCurrentPhotoPath;
    private File mPhotoFile;
    private Uri contentUri;
    private static final int REQUEST_PHOTO = 2;
    private static final int RESULT_LOAD_IMAGE = 3;
    private static final int RESULT_CAMERA_IMAGE = 2;
    public static final int TAKE_PHOTO = 111;
    final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_release,container,false);
        ButterKnife.bind(this,v);
        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivityForResult(captureImage,REQUEST_PHOTO);
                //takePhoto();
                showPopueWindow();
                String name = mItemName.getText().toString();
                //mItem.setItemName(name);
                mPhotoFile = CameraService.getPhotoFile(getContext(),name);
                boolean canTakePhoto = mPhotoFile!=null&&
                        captureImage.resolveActivity(getActivity().getPackageManager())!=null;
                mImageButton.setEnabled(canTakePhoto);                                  //判断是否存在相机 从而判断是否能够进行拍照

                if(canTakePhoto){
                    Uri uri = Uri.fromFile(mPhotoFile);
                    captureImage.putExtra(MediaStore.EXTRA_OUTPUT,uri);
                }
            }
        });
        return v;
    }

    private void showPopueWindow(){
        View popView = View.inflate(getContext(),R.layout.popupwindow_camera_need,null);
        Button bt_album = (Button) popView.findViewById(R.id.btn_pop_album);
        Button bt_camera = (Button) popView.findViewById(R.id.btn_pop_camera);
        Button bt_cancle = (Button) popView.findViewById(R.id.btn_pop_cancel);
        //获取屏幕宽高
        int weight = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels*1/3;

        final PopupWindow popupWindow = new PopupWindow(popView,weight,height);
        //popupWindow.setAnimationStyle(R.style.anim_popup_dir);
        popupWindow.setFocusable(true);
        //点击外部popueWindow消失
        popupWindow.setOutsideTouchable(true);

        bt_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
                popupWindow.dismiss();

            }
        });

        bt_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeCamera(RESULT_CAMERA_IMAGE);
                popupWindow.dismiss();

            }
        });

        bt_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();

            }
        });

        //popupWindow消失屏幕变为不透明
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                lp.alpha = 1.0f;
                getActivity().getWindow().setAttributes(lp);
            }
        });

        //popupWindow出现屏幕变为半透明
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.5f;
        getActivity().getWindow().setAttributes(lp);
        popupWindow.showAtLocation(popView, Gravity.BOTTOM,0,50);
        //onActivityResult(RESULT_CAMERA_IMAGE,);
        //startActivityForResult();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK ) {
            if (requestCode == RESULT_LOAD_IMAGE && null != data) {
                final Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getActivity().getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                final String picturePath = cursor.getString(columnIndex);
                try{
                    Bitmap bit3 = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
                    mPhotoView.setImageBitmap(bit3);
                }catch (IOException e){
                    e.printStackTrace();
                }

                //TODO: 2018/1/6 接下来就是把所有的图片转化为Bitmap->Base64位编码->传到服务器
                Bitmap bit3 = BitmapFactory.decodeFile(picturePath);

                Glide.with(getContext()).load(selectedImage).into(mPhotoView);
                //mPhotoView
                //upload(picturePath);
                cursor.close();
            }else if (requestCode == RESULT_CAMERA_IMAGE){

                };
                Bitmap bit2 = BitmapFactory.decodeFile(mCurrentPhotoPath);
                String name = mItemName.getText().toString();
                String desc = mItemDesc.getText().toString();
                int price = Integer.parseInt(mItemPirce.getText().toString());

                //mPhotoView.setImageBitmap(bit2);

            }
        }

    private void takeCamera(int num) {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            photoFile = createImageFile();
            // Continue only if the File was successfully created
            if (photoFile != null) {

                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
                    takePictureIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    //takePictureIntent.addCategory("android.intent.category.DEFAULT");
                    contentUri = FileProvider.getUriForFile(getActivity(), BuildConfig.APPLICATION_ID+".fileProvider",photoFile);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,contentUri);
                    //takePictureIntent.setDataAndType(contentUri,  "image/*");
                }else{
                    takePictureIntent.setDataAndType(Uri.fromFile(photoFile),  "image/*");
                    takePictureIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                }
                //takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                //      Uri.fromFile(photoFile));
            }
        }

        if(takePictureIntent.resolveActivity(getActivity().getPackageManager())!=null){
            startActivityForResult(takePictureIntent, num);//跳转界面传回拍照所得数据
        }
        else{
            Log.d("111", "takeCamera: ");
        }
    }

    private File createImageFile() {
        File storageDir = getActivity().getApplicationContext().getFilesDir() ;
        //File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = null;
        try {

            image = File.createTempFile(
                    generateFileName(),  /* prefix */
                    ".jpg",         /* suffix */
                    storageDir      /* directory */
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    public static String generateFileName() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        return imageFileName;
    }

}
