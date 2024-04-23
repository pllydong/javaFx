package mtyx.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author guokun
 * @date 2024/4/21 13:48
 */
@NoArgsConstructor
@Data
public class ResourcePositionListDTO {
    private Integer type;
    private Integer stock;
    private String purchasePrice;
    private Object reportSellingPrice;
}
