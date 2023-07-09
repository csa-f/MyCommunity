package top.csaf.feishu.model.ro.bitable;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 云文档-多维表格-记录-列出记录 响应体
 */
@Data
public class BitableListRecordRO {
  /**
   * 是否还有更多项
   */
  private boolean has_more;
  /**
   * 分页标记，当 has_more 为 true 时，会同时返回新的 page_token，否则不返回 page_token
   */
  private String page_token;
  /**
   * 总记录数
   */
  private int total;
  /**
   * 本次请求返回的全部记录列表
   */
  private List<Item> items;

  @Data
  public static class Item {
    /**
     * 一条记录的唯一标识 id <a href="https://open.feishu.cn/document/server-docs/docs/bitable-v1/notification#15d8db94">record_id 参数说明</a>
     */
    private String record_id;
    /**
     * 该记录的创建人
     */
    private Person created_by;
    /**
     * 该记录的创建时间
     */
    private int created_time;
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
     * <p>
     * 当前接口支持的字段类型请参考<a href="https://open.feishu.cn/document/server-docs/docs/bitable-v1/notification">接入指南</a>
     * <p>
     * 不同类型字段的数据结构请参考<a href="https://open.feishu.cn/document/server-docs/docs/bitable-v1/bitable-structure">数据结构概述</a>
     */
    private Map<String, Object> fields;
  }

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
     * <p>
     * 字段权限要求（满足任一）：
     * <p>
     * - 以应用身份读取通讯录
     * - 获取用户基本信息
     */
    private String avatar_url;
  }
}
