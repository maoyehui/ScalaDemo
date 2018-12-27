import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: SBTTest
 * @Package: PACKAGE_NAME
 * @ClassName: simulateData
 * @Description:
 * @Author: yehui.mao
 * @CreateDate: 2018/9/12 14:30
 * @UpdateUser: yehui.mao
 */
public class simulateData {
    boolean isSuccess = false;

    public static String doPost(String url) {
        try {
            // 定义HttpClient
            HttpClient httpClient = new DefaultHttpClient();

            // 实例化HTTP方法
            HttpPost request = new HttpPost(url);

            List<NameValuePair> nvps = new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return null;
    }
}
