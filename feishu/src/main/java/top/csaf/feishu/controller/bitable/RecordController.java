package top.csaf.feishu.controller.bitable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.csaf.feishu.client.bitable.RecordClient;
import top.csaf.feishu.controller.wiki.SpacesController;
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
@RequestMapping("/bitable")
@RestController
public class RecordController {

  @Autowired
  private RecordClient recordClient;

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
  @GetMapping("/apps/{appToken}/tables/{tableId}/records")
  public ApiResponse<BitableListRecordRO> list(@PathVariable String appToken, @PathVariable String tableId, BitableListRecordQO query) {
    return recordClient.list(appToken, tableId, query);
  }

  /**
   * <a href="https://open.feishu.cn/document/server-docs/docs/bitable-v1/app-table-record/create">
   * 新增记录
   * </a>
   * ，用于在数据表中新增一条记录。
   * <p>
   * 如果是知识库中的多维表格，则需要先调用 {@link SpacesController#getNode(String)}，获取响应体中的 obj_token，作为本接口的 appToken 参数
   *
   * @param appToken <a href="https://open.feishu.cn/document/server-docs/docs/bitable-v1/notification#8121eebe">多维表格的唯一标识符</a>
   * @param tableId  <a href="https://open.feishu.cn/document/server-docs/docs/bitable-v1/notification#735fe883">多维表格数据表的唯一标识符</a>
   * @param fields   <a href="https://open.feishu.cn/document/server-docs/docs/bitable-v1/notification#15d8db94">数据表的字段，即数据表的列</a>
   * @return 响应
   */
  @PostMapping("/apps/{appToken}/tables/{tableId}/records")
  public ApiResponse<BitableSaveOrUpdateRecordRO> save(@PathVariable String appToken, @PathVariable String tableId, @RequestBody Map<String, Object> fields) {
    return recordClient.save(appToken, tableId, fields);
  }

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
  @PutMapping("/apps/{appToken}/tables/{tableId}/records/{recordId}")
  public ApiResponse<BitableSaveOrUpdateRecordRO> update(@PathVariable String appToken, @PathVariable String tableId, @PathVariable String recordId, @RequestBody Map<String, Object> fields) {
    return recordClient.update(appToken, tableId, recordId, fields);
  }
}
