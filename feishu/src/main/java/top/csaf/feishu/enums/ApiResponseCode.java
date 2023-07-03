package top.csaf.feishu.enums;

import top.csaf.feishu.model.ro.ApiResponse;

/**
 * 飞书 API 响应码
 * <p>
 * 关联：{@link ApiResponse}
 */
public enum ApiResponseCode {
  SUCCESS(0, "成功"),
  ;

  private Integer value;
  private String desc;

  public Integer getValue() {
    return value;
  }

  public void setValue(Integer value) {
    this.value = value;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  ApiResponseCode(Integer value, String desc) {
    this.value = value;
    this.desc = desc;
  }
}
