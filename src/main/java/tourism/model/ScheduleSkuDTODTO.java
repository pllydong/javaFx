package tourism.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author guokun
 * @date 2024/4/21 13:48
 */
@NoArgsConstructor
@Data
public class ScheduleSkuDTODTO {
    private Long productId;
    private Long skuId;
    private String skuName;
    private List<String> barCodeList;
    private Long productProductionDate;
    private List<Integer> skuTagList;
    private String skuImg;
}
