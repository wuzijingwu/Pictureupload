package text.bwei.com.pictureupload.touxiang;

import okhttp3.MultipartBody;

public interface BasePresenter{
    void getData(String uid, MultipartBody.Part file);
}