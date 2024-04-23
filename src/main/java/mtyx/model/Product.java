package mtyx.model;

import cn.hutool.core.annotation.Alias;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author guokun
 * @date 2024/4/20 18:42
 */
@Data
public class Product {
    /**
     * sku编号
     */
    @Alias(value = "sku编号")
    private Long skuId;
    /**
     * 活动编号（唯一确定指定网点下指定产品）
     */
    private String activityId;
    /**
     * 销售价格
     */
    private BigDecimal purchasePrice;
    /**
     * 秒杀价格
     */
    private BigDecimal seckillSettlementPrice;
    /**
     * 底价
     */
    @Alias("底价(单位元)")
    private BigDecimal lowestPrice;
    /**
     * 产品名称
     */
    @Alias("sku名称")
    private String skuName;
    /**
     * 网点ID
     */
    private String regionId;
    /**
     * 网点名称
     */
    private String regionName;
    /**
     * 最大销售库存
     */
    private Integer maxSellQuantity;
    private Integer sellPosition;
    private Integer sellType;
    private String unitName;
}
