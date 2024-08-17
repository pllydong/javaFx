package sample.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Hotel {
    private String enName;
    private String address;
    private String phone;
    private String zhName;
    private String hotelId;
    public Hotel(String enName, String address, String phone, String hotelId) {
        this.enName = enName;
        this.address = address;
        this.phone = phone;
        this.hotelId = hotelId;
    }

}