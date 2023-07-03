package top.csaf.feishu.client;

import com.dtflys.forest.annotation.BaseRequest;
import com.dtflys.forest.annotation.Body;
import com.dtflys.forest.annotation.PostRequest;
import top.csaf.feishu.model.ro.TenantAccessTokenRO;

/**
 * <a href="https://open.feishu.cn/document/home/introduction-to-scope-and-authorization/access-credentials">
 * 访问凭证
 * </a>
 */
@BaseRequest(
  baseURL = "${feishu.auth.baseUrl}",
  contentType = "application/json",
  charset = "utf-8"
)
public interface AuthClient {

  /**
   * <a href="https://open.feishu.cn/document/server-docs/authentication-management/access-token/tenant_access_token_internal">
   * 自建应用获取 tenant_access_token
   * </a>
   *
   * @param appId     <a href="https://open.feishu.cn/document/server-docs/api-call-guide/terminology#b047be0c">应用唯一标识（app_id）</a>
   * @param appSecret <a href="https://open.feishu.cn/document/server-docs/api-call-guide/terminology#1b5fb6cd">应用秘钥（app_secret）</a>
   * @return 租户访问凭证、过期时间（秒）
   */
  @PostRequest("${feishu.auth.tenantAccessTokenInternal}")
  TenantAccessTokenRO getTenantAccessToken(@Body("app_id") String appId, @Body("app_secret") String appSecret);
}
