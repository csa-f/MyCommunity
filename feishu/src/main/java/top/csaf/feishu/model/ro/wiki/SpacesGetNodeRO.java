package top.csaf.feishu.model.ro.wiki;

import lombok.Data;

/**
 * 云文档-知识库-获取知识空间节点信息 响应体
 */
@Data
public class SpacesGetNodeRO {

  /**
   * 节点信息
   */
  private SpacesNode node;

  @Data
  public static class SpacesNode {
    /**
     * <a href="https://open.feishu.cn/document/server-docs/docs/wiki-v2/wiki-overview">
     * 知识空间 ID
     * </a>
     */
    private String space_id;
    /**
     * 节点 token
     */
    private String node_token;
    /**
     * 对应文档类型的 token，可根据 objType 判断属于哪种文档类型。
     */
    private String obj_token;
    /**
     * 文档类型，对于快捷方式，该字段是对应的实体的 objType。
     * <p>
     * doc：旧版文档
     * sheet：表格
     * mindnote：思维导图
     * bitable：多维表格
     * file：文件
     * docx：新版文档
     */
    private String obj_type;
    /**
     * 父节点 token。若当前节点为一级节点，父节点 token 为空。
     */
    private String parent_node_token;
    /**
     * 节点类型
     * <p>
     * origin：实体
     * shortcut：快捷方式
     */
    private String node_type;
    /**
     * 快捷方式对应的实体 node_token，当节点为快捷方式时，该值不为空。
     */
    private String origin_node_token;
    /**
     * 快捷方式对应的实体所在的 space id
     */
    private String origin_space_id;
    /**
     * 是否有子节点
     */
    private Boolean has_child;
    /**
     * 文档标题
     */
    private String title;
    /**
     * 文档创建时间
     */
    private String obj_create_time;
    /**
     * 文档最近编辑时间
     */
    private String obj_edit_time;
    /**
     * 节点创建时间
     */
    private String node_create_time;
    /**
     * 节点创建者
     */
    private String creator;
    /**
     * 节点所有者
     */
    private String owner;
  }
}
