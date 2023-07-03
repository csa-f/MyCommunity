package top.csaf.feishu.client.wiki;

import com.dtflys.forest.annotation.BaseRequest;
import com.dtflys.forest.annotation.GetRequest;
import com.dtflys.forest.annotation.Query;
import top.csaf.feishu.model.ro.ApiResponse;
import top.csaf.feishu.model.ro.wiki.SpacesGetNodeRO;

/**
 * <a href="https://open.feishu.cn/document/server-docs/docs/wiki-v2/wiki-overview">
 * 云文档-知识库
 * </a>
 */
@BaseRequest(
  baseURL = "${feishu.wiki.spaces.baseUrl}",
  contentType = "application/json",
  charset = "utf-8"
)
public interface SpacesClient {

  /**
   * <a href="https://open.feishu.cn/document/server-docs/docs/wiki-v2/space-node/get_node">
   * 获取知识空间节点信息
   * </a>
   *
   * @param token 文档的节点 token
   * @return
   */
  @GetRequest(value = "${feishu.wiki.spaces.getNode}")
  ApiResponse<SpacesGetNodeRO> getNode(@Query("token") String token);
}
