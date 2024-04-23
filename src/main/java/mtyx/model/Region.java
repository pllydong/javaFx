package mtyx.model;

import lombok.Data;

/**
 * @author guokun
 * @date 2024/4/21 13:21
 */
@Data
public class Region {
    /**
     * 网点ID
     */
    private String regionId;
    /**
     * 网点名称
     */
    private String regionName;
    /**
     * 分区ID
     */
    private String districtId;
    /**
     * 分区名称
     */
    private String districtName;
}
