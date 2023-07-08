package top.csaf.feishu.client.bitable;

import com.dtflys.forest.annotation.*;
import top.csaf.feishu.model.ro.ApiResponse;
import top.csaf.feishu.model.ro.bitable.BitableSaveRecordRO;

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
   * <a href="https://open.feishu.cn/document/server-docs/docs/bitable-v1/app-table-record/create#362449eb">
   * 新增记录
   * </a>
   *
   * @param appToken <a href="https://open.feishu.cn/document/server-docs/docs/bitable-v1/notification#8121eebe">多维表格的唯一标识符</a>
   * @param tableId  <a href="https://open.feishu.cn/document/server-docs/docs/bitable-v1/notification#735fe883">多维表格数据表的唯一标识符</a>
   * @param fields   数据表的字段，即数据表的列
   * @return 新增的记录的内容
   */
  @PostRequest
  ApiResponse<BitableSaveRecordRO> save(@Var("appToken") String appToken, @Var("tableId") String tableId, @Body Map<String, Object> fields);
}
