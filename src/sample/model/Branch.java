package sample.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author guokun
 * @date 2024/8/17 15:46
 * 机构地点
 */
@Data
public class Branch {
    private String id;
    /**
     * 机构名称1 - 英文名
     */
    private String enName;
    /**
     * 简称
     */
    private String shortName;
    /**
     * 中文名
     */
    private String zhName;
    /**
     * 日文名
     */
    private String jaName;
    /**
     * 韩文名
     */
    private String koName;

    /**
     * 父机构ID
     */
    private String parentId;

    /**
     * 父ID列表
     */
    private Set<String> children = new HashSet<>();

    /**
     * 景点ID列表
     */
    private Set<String> touristSpots = new HashSet<>();

    /**
     * 酒店ID列表
     */
    private Set<String> hotels = new HashSet<>();

    /**
     * 航班列表
     *
     */
    private Set<String> flights = new HashSet<>();


    public Set<String> getAllHotels() {
        Set<String> set = new HashSet<>(hotels);
        for (String childId : children) {
            hotels.addAll(CacheData.getBranchMap().get(childId).getAllHotels());
        }
        return set;
    }

    public Set<String> getAllSports() {
        Set<String> set = new HashSet<>(touristSpots);
        for (String childId : children) {
            hotels.addAll(CacheData.getBranchMap().get(childId).getAllSports());
        }
        return set;
    }
}
