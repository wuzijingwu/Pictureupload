package text.bwei.com.pictureupload.touxiang;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
    private static volatile RetrofitUtils instance;
    private Retrofit retrofit;
    private ApiService apiService;


    private RetrofitUtils(){
        OkHttpClient client = new OkHttpClient();
        retrofit = new Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://120.27.23.105/file/")
                .build();
        apiService = retrofit.create(ApiService.class);
    }
    public static RetrofitUtils getInstance(){
        if (instance==null){
            synchronized (RetrofitUtils.class){
                if (null==instance){
                    instance = new RetrofitUtils();

                }
            }
        }
        return instance;

    }
    public ApiService getService(){
        return apiService;
    }

}