package text.bwei.com.pictureupload.touxiang;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiService {
//    https://www.zhaoapi.cn/file/upload
    @Multipart
    @POST("upload")
    Flowable<MessageBean> getMusicList(@Query("uid") String uid, @Part MultipartBody.Part file);

}