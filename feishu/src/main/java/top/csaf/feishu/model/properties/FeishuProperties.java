package top.csaf.feishu.model.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 飞书配置
 */
@ConfigurationProperties(prefix = "feishu")
@Configuration
@Data
public class FeishuProperties {
  /**
   * 需要 tenant_access_token 的接口配置
   */
  private List<String> needTenantAccessTokenUrls;
}
