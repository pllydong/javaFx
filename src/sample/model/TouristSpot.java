package sample.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TouristSpot {
    private String id;
    private String koreanName;
    private String japaneseName;
    private String englishName;

    public TouristSpot(String id, String koreanName, String japaneseName, String englishName) {
        this.id = id;
        this.koreanName = koreanName;
        this.japaneseName = japaneseName;
        this.englishName = englishName;
    }
}