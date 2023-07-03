package top.csaf.feishu.model.properties;

/**
 * 环境变量
 */
public class Env {

  public static final String APP_ID = System.getenv("FEISHU_APP_ID");
  public static final String APP_SECRET = System.getenv("FEISHU_APP_SECRET");
}
