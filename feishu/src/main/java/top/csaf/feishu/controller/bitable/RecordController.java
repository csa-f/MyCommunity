package top.csaf.feishu.controller.bitable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.csaf.feishu.client.bitable.RecordClient;
import top.csaf.feishu.controller.wiki.SpacesController;
import top.csaf.feishu.model.ro.ApiResponse;
import top.csaf.feishu.model.ro.bitable.BitableSaveRecordRO;

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
   * <a href="https://open.feishu.cn/document/uAjLw4CM/ukTMukTMukTM/reference/bitable-v1/app-table-record/create">
   * 新增记录
   * </a>
   * <p>
   * 如果是知识库中的多维表格，则需要先调用 {@link SpacesController#getNode(String)}，获取响应体中的 obj_token，作为本接口的 appToken 参数
   *
   * @param appToken <a href="https://open.feishu.cn/document/uAjLw4CM/ukTMukTMukTM/bitable/notification#8121eebe">多维表格的唯一标识符</a>
   * @param tableId  <a href="https://open.feishu.cn/document/uAjLw4CM/ukTMukTMukTM/bitable/notification#735fe883">多维表格数据表的唯一标识符</a>
   * @param fields   数据表的字段，即数据表的列
   * @return
   */
  @PostMapping("/apps/{appToken}/tables/{tableId}/records")
  public ApiResponse<BitableSaveRecordRO> save(@PathVariable String appToken, @PathVariable String tableId, @RequestBody Map<String, Object> fields) {
    return recordClient.save(appToken, tableId, fields);
  }
}
