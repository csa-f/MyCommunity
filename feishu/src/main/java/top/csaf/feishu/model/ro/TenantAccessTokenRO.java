package top.csaf.feishu.model.ro;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 租户访问凭证 响应体
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TenantAccessTokenRO extends ApiResponse {
  /**
   * 租户访问凭证
   */
  private String tenant_access_token;
  /**
   * tenant_access_token 的过期时间，单位为秒
   */
  private Integer expire;
}
