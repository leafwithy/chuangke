package demo.example.chuangke.util;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * 网络请求的工具类
 */

public class HttpUtil {

    //让构造函数为 private，这样该类就不会被实例化
    private HttpUtil(){}
    //静态内部类:只有通过显式调用 getOkHttpClient 方法时，才会显式装载 ClientHolder 类，从而实例化 OkHttpClient。
    private static  class ClientHolder{
        private static final OkHttpClient sOkHttpClient = new OkHttpClient();
    }

    private static OkHttpClient getOkHttpClient(){
        return ClientHolder.sOkHttpClient;
    }

    public static void sendRequest(String address,RequestBody requestBody,okhttp3.Callback callback){
        Request request = new Request.Builder()
                .url(address)
                .post(requestBody)
                .build();
        HttpUtil.getOkHttpClient().newCall(request).enqueue(callback);//异步发送网络请求
    }

    public static RequestBody loginRequestBody(String account,String password){
        return new FormBody.Builder()
                .add("account", account)
                .add("password", password)
                .build();
    }

    public static RequestBody registerRequestBody(String account,String password){
        return new FormBody.Builder()
                .add("account", account)
                .add("password", password)
                .build();
    }
    public static class  MainRequestBody{
        public static RequestBody getProveItemListRequestBody(String pid){
            return new FormBody.Builder()
                    .add("pid",pid)
                    .build();
        }
        public static RequestBody postProveItemListRequestBody(String title,String type,String readnum,String image){
            return new FormBody.Builder()
                    .add("title",title)
                    .add("type",type)
                    .add("readnum",readnum)
                    .add("image",image)
                    .build();
        }
    }
    public static class  IdeaRequestBody {
        public static RequestBody getrecruitListRequestBody(String uid, String startRid) {
            return new FormBody.Builder()
                    .add("uid", uid)
                    .add("start_rid", startRid)
                    .build();
        }
        public static RequestBody getcommentListRequestBody(String rid) {
            return new FormBody.Builder()
                    .add("rid", rid)
                    .build();
        }
        public static RequestBody getNewListRequestBody(String did){
            return new FormBody.Builder().add("did",did).build();
        }
        public static RequestBody getHotListRequestBody(String hid) {
            return new FormBody.Builder().add("hid", hid).build();
        }
        public static RequestBody postCommentRequestBody(String rid,String uid,String content){
            return new FormBody.Builder()
                    .add("rid", rid)
                    .add("uid", uid)
                    .add("content",content)
                    .build();
        }
        public static RequestBody postRecruitRequestBody(String uid,String intro,String demand){
            return new FormBody.Builder()
                    .add("uid", uid)
                    .add("intro", intro)
                    .add("demand",demand)
                    .build();
        }
        public static RequestBody postNewListRequesrBody(String nid,String content){
            return new FormBody.Builder().add("nid",nid).add("content",content).build();
        }
        public static RequestBody postHotListRequestBody(String hid,String content){
            return new FormBody.Builder().add("hid",hid).add("content",content).build();
        }

        }




    public static RequestBody starRequestBody(int type,String uid,String rid){
        return new FormBody.Builder()
                .add("type",String.valueOf(type))
                .add("uid", uid)
                .add("rid", rid)
                .build();
    }

}
