package top.csaf.feishu.model.qo;

import lombok.Data;

/**
 * 云文档-多维表格-记录-列出记录 查询参数
 */
@Data
public class BitableListRecordQO {
  /**
   * 视图的唯一标识符，获取指定视图下的记录 <a href="https://open.feishu.cn/document/server-docs/docs/bitable-v1/notification#aa6eba00">view_id 参数说明</a>
   * <p>
   * 注意：当 filter 参数 或 sort 参数不为空时，请求视为对数据表中的全部数据做条件过滤，指定的 view_id 会被忽略。
   * <p>
   * 示例值："vewqhz51lk"
   */
  private String view_id;
  /**
   * 筛选参数，用于指定本次查询的筛选条件
   * <p>
   * 注意：<br>
   * 1. 不支持对 “人员” 以及 “关联字段” 的属性进行过滤筛选，如人员的 OpenID。<br>
   * 2. 指定筛选条件时，参数长度不超过 2000 个字符。<br>
   * 详细请参考<a href="https://open.feishu.cn/document/server-docs/docs/bitable-v1/app-table-record/filter">筛选条件支持的公式</a>
   * <p>
   * 示例值："AND (CurrentValue.[身高]>180, CurrentValue.[体重]>150)"
   */
  private String filter;
  /**
   * 排序参数，用于指定本次查询返回结果的顺序
   * <p>
   * 注意：<br>
   * 1. 不支持对带 “公式” 和 “关联字段” 的表的使用。<br>
   * 2. 指定排序条件时，参数长度不超过 1000 字符。<br>
   * 3. 当存在多个排序条件时，数据将根据条件顺序逐层排序
   * <p>
   * 示例值："[" 字段 1 DESC"," 字段 2 ASC"]"
   */
  private String sort;
  /**
   * 字段名称，用于指定本次查询返回记录中包含的字段
   * <p>
   * 示例值："[" 字段 1"," 字段 2"]"
   */
  private String field_names;
  /**
   * 控制多行文本字段数据的返回格式，true 表示以数组形式返回。
   * <p>
   * 注意：<br>
   * 1. 多行文本中如果有超链接部分，则会返回链接的 URL。<br>
   * 2. 目前可以返回多行文本中 URL 类型为多维表格链接、飞书 doc、飞书 sheet 的 URL 类型以及 @人员的数据结构。
   * <p>
   * 示例值：true
   */
  private Boolean text_field_as_array;
  /**
   * 用户 ID 类型
   * <p>
   * 示例值："open_id"
   * <p>
   * 可选值有：<br>
   * - open_id：标识一个用户在某个应用中的身份。同一个用户在不同应用中的 Open ID 不同。<a href="https://open.feishu.cn/document/faq/trouble-shooting/how-to-obtain-openid">了解更多：如何获取 Open ID？</a><br>
   * - union_id：标识一个用户在某个应用开发商下的身份。同一用户在同一开发商下的应用中的 Union ID 是相同的，在不同开发商下的应用中的 Union ID 是不同的。通过 Union ID，应用开发商可以把同个用户在多个应用中的身份关联起来。<a href="https://open.feishu.cn/document/faq/trouble-shooting/how-to-obtain-union-id">了解更多：如何获取 Union ID？</a><br>
   * - user_id：标识一个用户在某个租户内的身份。同一个用户在租户 A 和租户 B 内的 User ID 是不同的。在同一个租户内，一个用户的 User ID 在所有应用（包括商店应用）中都保持一致。User ID 主要用于在不同的应用间打通用户数据。<a href="https://open.feishu.cn/document/faq/trouble-shooting/how-to-obtain-user-id">了解更多：如何获取 User ID？</a><br>
   * <p>
   * 默认值：open_id
   * <p>
   * 当值为 user_id，字段权限要求：<br>
   * 获取用户 user ID（仅自建应用）
   */
  private String user_id_type;
  /**
   * 默认值为 false，返回当前字段的默认类型和结果；当该参数的值为 true 时，公式 和 查找引用 类型的字段，将会以 被引用字段 的格式返回
   * <p>
   * 示例值：true
   */
  private Boolean display_formula_ref;
  /**
   * 控制是否返回自动计算的字段，例如 created_by/created_time/last_modified_by/last_modified_time，true 表示返回
   * <p>
   * 示例值：true
   */
  private Boolean automatic_fields;
  /**
   * 分页标记，第一次请求不填，表示从头开始遍历；
   * 分页查询结果还有更多项时会同时返回新的 page_token，
   * 下次遍历可采用该 page_token 获取查询结果
   * <p>
   * 示例值："recn0hoyXL"
   */
  private String page_token;
  /**
   * 分页大小
   * <p>
   * 示例值：10
   * <p>
   * 默认值：20
   * <p>
   * 数据校验规则：<br>
   * - 最大值：500
   */
  private Integer page_size;
}
