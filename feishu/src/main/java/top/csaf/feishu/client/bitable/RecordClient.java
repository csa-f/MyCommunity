package top.csaf.feishu.client.bitable;

import com.dtflys.forest.annotation.*;
import top.csaf.feishu.model.qo.BitableListRecordQO;
import top.csaf.feishu.model.ro.ApiResponse;
import top.csaf.feishu.model.ro.bitable.BitableListRecordRO;
import top.csaf.feishu.model.ro.bitable.BitableSaveOrUpdateRecordRO;

import java.util.Map;

/**
 * <a href="https://open.feishu.cn/document/server-docs/docs/bitable-v1/bitable-overview">
 * 云文档-多维表格-记录
 * </a>
 */
@BaseRequest(
  baseURL = "${feishu.bitable.record.baseUrl}",
  contentType = "application/json",
  charset = "utf-8"
)
public interface RecordClient {

  /**
   * <a href="https://open.feishu.cn/document/server-docs/docs/bitable-v1/app-table-record/list">
   * 列出记录
   * </a>
   * ，用于列出数据表中的现有记录，单次最多列出 500 行记录，支持分页获取。
   *
   * @param appToken <a href="https://open.feishu.cn/document/server-docs/docs/bitable-v1/notification#8121eebe">多维表格的唯一标识符</a>
   * @param tableId  <a href="https://open.feishu.cn/document/server-docs/docs/bitable-v1/notification#735fe883">多维表格数据表的唯一标识符</a>
   * @param query    查询参数
   * @return 响应
   */
  @GetRequest
  ApiResponse<BitableListRecordRO> list(@Var("appToken") String appToken, @Var("tableId") String tableId, @Query BitableListRecordQO query);

  /**
   * <a href="https://open.feishu.cn/document/server-docs/docs/bitable-v1/app-table-record/create#362449eb">
   * 新增记录
   * </a>
   * ，用于在数据表中新增一条记录。
   *
   * @param appToken <a href="https://open.feishu.cn/document/server-docs/docs/bitable-v1/notification#8121eebe">多维表格的唯一标识符</a>
   * @param tableId  <a href="https://open.feishu.cn/document/server-docs/docs/bitable-v1/notification#735fe883">多维表格数据表的唯一标识符</a>
   * @param fields   数据表的字段，即数据表的列
   * @return 响应
   */
  @PostRequest
  ApiResponse<BitableSaveOrUpdateRecordRO> save(@Var("appToken") String appToken, @Var("tableId") String tableId, @Body Map<String, Object> fields);

  /**
   * <a href="https://open.feishu.cn/document/server-docs/docs/bitable-v1/app-table-record/update">
   * 更新记录
   * </a>
   *
   * @param appToken <a href="https://open.feishu.cn/document/server-docs/docs/bitable-v1/notification#8121eebe">多维表格的唯一标识符</a>
   * @param tableId  <a href="https://open.feishu.cn/document/server-docs/docs/bitable-v1/notification#735fe883">多维表格数据表的唯一标识符</a>
   * @param recordId <a href="https://open.feishu.cn/document/server-docs/docs/bitable-v1/notification#15d8db94">一条记录的唯一标识 id</a>
   * @param fields   数据表的字段，即数据表的列
   * @return 响应
   */
  @PutRequest(url = "${feishu.bitable.record.updateUrl}")
  ApiResponse<BitableSaveOrUpdateRecordRO> update(@Var("appToken") String appToken, @Var("tableId") String tableId, @Var("recordId") String recordId, @Body Map<String, Object> fields);
}
