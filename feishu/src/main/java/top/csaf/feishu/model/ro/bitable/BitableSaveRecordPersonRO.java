package top.csaf.feishu.model.ro.bitable;

import lombok.Data;

@Data
public class BitableSaveRecordPersonRO {
  /**
   * 用户 id，id 类型等于 user_id_type 所指定的类型。
   */
  private String id;
  /**
   * 用户的中文名称
   */
  private String name;
  /**
   * 用户的英文名称
   */
  private String en_name;
  /**
   * 用户的邮箱
   */
  private String email;
  /**
   * 头像链接
   */
  private String avatar_url;
}
