package top.csaf.feishu.config.forest;

import com.dtflys.forest.http.ForestRequest;
import com.dtflys.forest.interceptor.Interceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.csaf.feishu.client.AuthClient;
import top.csaf.feishu.model.properties.Env;
import top.csaf.feishu.model.properties.FeishuProperties;
import top.csaf.regex.RegExUtils;

import java.util.regex.Pattern;

/**
 * <a href="https://forest.dtflyx.com/pages/1.5.31/interceptor/">
 * Forest 拦截器
 * </a>
 *
 * @param <T>
 */
@Slf4j
@Component
public class ForestGlobalInterceptor<T> implements Interceptor<T> {

  /**
   * 匹配动态参数
   */
  private static final Pattern URL_DYNAMIC_PARAM_PATTERN = Pattern.compile("%7B(.*?)%7D");

  @Autowired
  private FeishuProperties feishuProperties;
  @Autowired
  private AuthClient authClient;

  /**
   * 该方法在被调用时，并在beforeExecute前被调用
   *
   * @param request Forest请求对象
   */
  @Override
  public boolean beforeExecute(ForestRequest request) {
    String url = request.getUrl();

    // 给需要 tenant_access_token 的接口添加请求头
    for (String needTenantAccessTokenUrl : feishuProperties.getNeedTenantAccessTokenUrls()) {
      if (url.equals(needTenantAccessTokenUrl.replace("{", "%7B").replace("}", "%7D"))) {
        // 获取 tenant_access_token
        request.addHeader("Authorization", "Bearer " + authClient.getTenantAccessToken(Env.APP_ID, Env.APP_SECRET).getTenant_access_token());
        break;
      }
    }

    // 如果包含“{xxx}”，说明是动态参数，需要替换
    if (url.contains("%7B") && url.contains("%7D")) {
      for (String urlParam : RegExUtils.matches(url, URL_DYNAMIC_PARAM_PATTERN, 1)) {
        url = url.replace("%7B" + urlParam + "%7D", request.getVariableValue(urlParam).toString());
      }
    }
    request.setUrl(url);

    return true;
  }
}
