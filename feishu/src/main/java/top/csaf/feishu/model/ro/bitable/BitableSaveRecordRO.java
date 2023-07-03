package top.csaf.feishu.model.ro.bitable;

import lombok.Data;

import java.util.Map;

/**
 * 云文档-多维表格-记录 响应体
 */
@Data
public class BitableSaveRecordRO {

  private String id;
  /**
   * <a href="https://open.feishu.cn/document/uAjLw4CM/ukTMukTMukTM/bitable/notification#15d8db94">
   * 一条记录的唯一标识 ID
   * </a>
   */
  private String record_id;
  /**
   * 该记录的创建人
   */
  private BitableSaveRecordPersonRO created_by;
  /**
   * 该记录的创建时间
   */
  private String created_time;
  /**
   * 该记录最新一次更新的修改人
   */
  private BitableSaveRecordPersonRO last_modified_by;
  /**
   * 该记录最近一次的更新时间
   */
  private int last_modified_time;
  /**
   * 数据表的字段，即数据表的列
   */
  private Map<String, Object> fields;
}
