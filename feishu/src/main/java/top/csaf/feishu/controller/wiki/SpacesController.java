package top.csaf.feishu.controller.wiki;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.csaf.feishu.client.wiki.SpacesClient;
import top.csaf.feishu.model.ro.ApiResponse;
import top.csaf.feishu.model.ro.wiki.SpacesGetNodeRO;

/**
 * <a href="https://open.feishu.cn/document/server-docs/docs/wiki-v2/wiki-overview">
 * 云文档-知识库
 * </a>
 */
@RequestMapping("/wiki/spaces")
@RestController
public class SpacesController {

  @Autowired
  private SpacesClient spacesClient;

  /**
   * <a href="https://open.feishu.cn/document/server-docs/docs/wiki-v2/space-node/get_node">
   * 获取知识空间节点信息
   * </a>
   *
   * @param token 文档的节点 token
   * @return {@link SpacesGetNodeRO}
   */
  @GetMapping("/getNode")
  public ApiResponse<SpacesGetNodeRO> getNode(String token) {
    return spacesClient.getNode(token);
  }
}
