package com.example.po.stadiummanagement3.WebService;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.util.UUID;

/**
 * Created by 13701 on 2018/1/10.
 */

public class CameraService {
    public static File getPhotoFile(Context mContext, String name){
        File externalFileDir = mContext
                .getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if(externalFileDir==null)
            return null;

        if(name==""){
            return new File(externalFileDir,"IMG"+ UUID.randomUUID().toString()+".jpg");
        }else{
            return new File(externalFileDir,name);
        }

    }
}
