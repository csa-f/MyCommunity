package top.csaf.feishu.model.ro.bitable;

import lombok.Data;

import java.util.Map;

/**
 * 云文档-多维表格-记录-新增记录 响应体
 */
@Data
public class BitableSaveOrUpdateRecordRO {

  private String id;
  /**
   * <a href="https://open.feishu.cn/document/server-docs/docs/bitable-v1/notification#15d8db94">
   * 一条记录的唯一标识 ID
   * </a>
   */
  private String record_id;
  /**
   * 该记录的创建人
   */
  private Person created_by;
  /**
   * 该记录的创建时间
   */
  private String created_time;
  /**
   * 该记录最新一次更新的修改人
   */
  private Person last_modified_by;
  /**
   * 该记录最近一次的更新时间
   */
  private int last_modified_time;
  /**
   * 数据表的字段，即数据表的列
   */
  private Map<String, Object> fields;

  @Data
  public static class Person {
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
}
