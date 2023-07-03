package top.csaf.feishu.model.ro;

import lombok.Data;

/**
 * 飞书 API 响应体
 *
 * @param <T> 响应体数据（data）字段类型
 */
@Data
public class ApiResponse<T> {

  /**
   * 错误码，非 0 取值表示失败
   */
  private Integer code;
  /**
   * 错误描述
   */
  private String msg;
  private T data;
}
