package mtyx.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author guokun
 * @date 2024/4/21 13:46
 */
@NoArgsConstructor
@Data
public class ProductV2 {
    private Long scheduleDate;
    private ScheduleSkuDTODTO scheduleSkuDTO;
    private String regionName;
    private String settlementPrice;
    private String reportSellPrice;
    private String sellPrice;
    private Object cutlinePrice;
    private Integer maxSellQuantity;
    private Integer sellPosition;
    private Integer sellType;
    private Integer onlineType;
    private Object scheduleStatusRejectReason;
    private String activityId;
    private Long deliveryStartTime;
    private Long deliveryEndTime;
    private Long sellStartTime;
    private Long sellEndTime;
    private Boolean showConfirm;
    private Integer skuSaleStatus;
    private Object sellPositions;
    private List<String> approvalFieldList;
    private List<Integer> categoryIds;
    private List<?> canDoOperationList;
    private Object scheduleStatusRejectRemark;
    private Object scheduleStatusRejectReasonDesc;
    private Object notifyMessageDTOList;
    private Boolean canEdit;
    private List<String> canEditFieldList;
    private Integer regionId;
    private List<ResourcePositionListDTO> resourcePositionList;
    private Object resourcePositionDisplay;
    private Object canSuspend;
    private Object suspendType;
    private Object suspendDesc;
    private Boolean canCancel;
    private Boolean canRevoke;
    private Object revokeDesc;
    private Object saleStatus;
    private Integer scheduleStatus;
    private Boolean canWithdraw;
    private Object withdrawDesc;



    private boolean firstPro = false;
}
