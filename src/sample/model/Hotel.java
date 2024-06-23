package sample.model;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private String name;
    private String address;
    private String phone;
    private int hotelId;
    public Hotel(String name, String address, String phone, int hotelId) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.hotelId = hotelId;
    }
    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public String getPhone() {
        return phone;
    }

    public static List<Hotel> HOTEL_LIST = new ArrayList<>();

    static {
        // 创建酒店对象列表
        List<Hotel> hotels = new ArrayList<>();
        // 添加酒店对象到列表
        hotels.add(new Hotel("ONE@Tokyo", "1 Chome-19-3 Oshiage, Sumida City, Tokyo 131-0045 日本", "+81 3-5630-1193", 1));
        hotels.add(new Hotel("Hotel Metropolitan Tokyo Ikebukuro", "1 Chome-6-1 Nishiikebukuro, Toshima City, Tokyo 171-8505 日本", "+81 3-3980-1111", 2));
        hotels.add(new Hotel("Aman Tokyo", "The Otemachi Tower, 1 Chome-5-6 Ōtemachi, Chiyoda City, Tokyo 100-0004 日本", "+81 3-5224-3333", 3));
        hotels.add(new Hotel("Tokyo Bay Shiomi Prince Hotel", "2 Chome-8-16 Shiomi, Koto City, Tokyo 135-0052 日本", "+81 3-6660-3222", 4));
        hotels.add(new Hotel("FOCUS KURAMAE", "4 Chome-21-2 Kuramae, Taito City, Tokyo 111-0051 日本", "+81 3-5829-9980", 5));
        hotels.add(new Hotel("TOKYO-W-inn Asakusa", "4 Chome-6-1 Kotobuki, Taito City, Tokyo 111-0042 日本", "+81 3-6802-8985", 6));
        hotels.add(new Hotel("Hotel Wing International Korakuen", "1 Chome-25-11 Hongo, Bunkyo City, Tokyo 113-0033 日本", "+81 3-5804-1011", 7));
        hotels.add(new Hotel("Hostel DEN", "日本〒103-0023 Tokyo, Chuo City, Nihonbashihoncho, 4 Chome−13−8 Hostel DEN", "+81 3-6661-2067", 8));
        hotels.add(new Hotel("The Gate Hotel Ryogoku by HULIC", "1 Chome-2-13 Yokoami, Sumida City, Tokyo 130-0015 日本", "+81 3-5637-7041", 9));
        hotels.add(new Hotel("The Royal Park Canvas Ginza 8", "日本〒104-0061 Tokyo, Chuo City, Ginza, 8 Chome−9−4 ザ ロイヤルパーク キャンバス 銀座8", "+81 3-6205-8010", 10));
        hotels.add(new Hotel("Hotel Cen ホテルセン", "1 Chome-5-19 Hyakunincho, Shinjuku City, Tokyo 169-0073 日本", "+81 3-6278-9901", 11));
        hotels.add(new Hotel("HOTEL GRAPHY NEZU", "4 Chome-5-12 Ikenohata, Taito City, Tokyo 110-0008 日本", "+81 50-3138-2628", 12));
        hotels.add(new Hotel("Rhodes Kagurazaka Hotel", "1-17 Saikumachi, Shinjuku City, Tokyo 162-0838 日本", "+81 80-1316-2286", 13));
        hotels.add(new Hotel("Asakusa Tobu Hotel", "1 Chome-1-15 Asakusa, Taito City, Tokyo 111-0032 日本", "+81 3-3843-0111", 14));
        hotels.add(new Hotel("Cocts Akihabara", "5 Chome-2-7 Asakusabashi, Taito City, Tokyo 111-0053 日本", "+81 3-5809-2147", 15));
        hotels.add(new Hotel("The Blossom Hibiya", "1 Chome-1-13 Shinbashi, Minato City, Tokyo 105-0004 日本", "+81 3-3591-8702", 16));
        hotels.add(new Hotel("Imano Tokyo Ginza Hostel", "1 Chome-5-10 Shintomi, Chuo City, Tokyo 104-0041 日本", "+81 3-5117-2131", 17));
        hotels.add(new Hotel("Karaksa Hotel TOKYO STATION", "1 Chome-5-3 Yaesu, Chuo City, Tokyo 103-0028 日本", "+81 3-3243-6602", 18));
        hotels.add(new Hotel("Bulgari Hotel Tokyo", "2 Chome-2-1 Yaesu, Chuo City, Tokyo 104-0028 日本", "+81 3-6262-3333", 19));
        hotels.add(new Hotel("AC Hotel Tokyo Ginza", "6 Chome-14-7 Ginza, Chuo City, Tokyo 104-0061 日本", "+81 3-5550-0102", 20));
        hotels.add(new Hotel("Rhodes Kagurazaka Hotel", "1-17 Saikumachi, Shinjuku City, Tokyo 162-0838 日本", "+81 80-1316-2286", 21));
        hotels.add(new Hotel("The Blossom Hibiya", "1 Chome-1-13 Shinbashi, Minato City, Tokyo 105-0004 日本", "+81 3-3591-8702", 22));
        hotels.add(new Hotel("Via Inn Prime Akasaka", "2 Chome-6-17 Akasaka, Minato City, Tokyo 107-0052 日本", "+81 3-3505-5489", 23));
        hotels.add(new Hotel("MIMARU TOKYO ASAKUSA STATION", "2 Chome-20-4 Kaminarimon, Taito City, Tokyo 111-0034 日本", "+81 3-6231-7183", 24));
        hotels.add(new Hotel("Hotel Ryumeikan Tokyo", "1 Chome-3-22 Yaesu, Chuo City, Tokyo 103-0028 日本", "+81 3-3271-0971", 25));
        hotels.add(new Hotel("MIMARU TOKYO IKEBUKURO", "2 Chome-61-1 Ikebukuro, Toshima City, Tokyo 171-0014 日本", "+81 3-5927-8877", 26));
        hotels.add(new Hotel("Petit Grande Miyabi", "1 Chome-2-10 Ishiwara, Sumida City, Tokyo 130-0011 日本", "+81 3-6240-4444", 27));
        hotels.add(new Hotel("BELLUSTAR TOKYO, A Pan Pacific Hotel", "1 Chome-29-1 Kabukicho, Shinjuku City, Tokyo 160-0021 日本", "+81 3-6233-8800", 28));
        hotels.add(new Hotel("Four Seasons Hotel Tokyo At Marunouchi", "1 Chome-11-1 Marunouchi, Chiyoda City, Tokyo 100-6277 日本", "+81 3-5222-7222", 29));
        hotels.add(new Hotel("HOTEL TOKYO TRIP", "5 Chome-18-14 Nishinippori, Arakawa City, Tokyo 116-0013 日本", "+81 3-5615-5727", 30));
        hotels.add(new Hotel("&AND HOSTEL MINAMISENJU", "3 Chome-2-9 Minamisenju, Arakawa City, Tokyo 116-0003 日本", "+81 3-6806-7663", 31));
        hotels.add(new Hotel("The Okura Tokyo", "2 Chome-10-4 Toranomon, Minato City, Tokyo 105-0001 日本", "+81 3-3582-0111", 32));
        hotels.add(new Hotel("ICI Hotel Ueno Shin Okachimachi", "4 Chome-23-11 Taito, Taito City, Tokyo 110-0016 日本", "+81 3-3837-7575", 33));
        hotels.add(new Hotel("CANDEO HOTELS Tokyo Shimbashi", "3 Chome-6-8 Shinbashi, Minato City, Tokyo 105-0004 日本", "+81 3-5510-3400", 34));
        hotels.add(new Hotel("Hotel Artia Dinosaur", "7-chome-28-28 Tsuruma, Machida, Tokyo 194-0004 日本", "+81 42-796-6717", 35));
        hotels.add(new Hotel("Tosei Hotel Cocone Ueno Okachimachi", "3 Chome-23-9 Ueno, Taito City, Tokyo 110-0005 日本", "+81 3-3837-5541", 36));
        hotels.add(new Hotel("Pearl Hotel Shinjuku Akebono-bashi", "9-5 Sumiyoshicho, Shinjuku City, Tokyo 162-0065 日本", "+81 3-3359-3900", 37));
        hotels.add(new Hotel("remm Roppongi", "7 Chome-14-4 Roppongi, Minato City, Tokyo 106-0032 日本", "+81 3-6863-0606", 38));
        hotels.add(new Hotel("Ueno Station Hostel Oriental I", "6 Chome-9-9 Ueno, Taito City, Tokyo 110-0005 日本", "+81 3-3833-1501", 39));
        hotels.add(new Hotel("HOTEL GROOVE SHINJUKU, A PARKROYAL Hotel", "1 Chome-29-1 Kabukicho, Shinjuku City, Tokyo 160-0021 日本", "+81 3-6233-8888", 40));
        hotels.add(new Hotel("MUSTARD HOTEL SHIMOKITAZAWA", "3 Chome-9-19 Kitazawa, Setagaya City, Tokyo 155-0031 日本", "+81 3-6407-9077", 41));
        hotels.add(new Hotel("Sakura Cross Hotel Ueno Okachimachi", "2 Chome-10-12 Kojima, Taito City, Tokyo 111-0056 日本", "+81 70-5264-1544", 42));
        hotels.add(new Hotel("Mesm Tokyo, Autograph Collection", "1 Chome-10-30 Kaigan, Minato City, Tokyo 105-0022 日本", "+81 3-5777-1111", 43));
        hotels.add(new Hotel("Tmark City Hotel Tokyo Omori", "1 Chome-2-10 Omorihoncho, Ota City, Tokyo 143-0011 日本", "+81 3-5764-1810", 44));
        hotels.add(new Hotel("Hotel Villa-Fontaine Tokyo-Kayabacho", "1 Chome-8-2 Shinkawa, Chuo City, Tokyo 104-0033 日本", "+81 3-3553-2220", 45));
        hotels.add(new Hotel("dots. tokyo", "2 Chome-1-17 Kitaueno, Taito City, Tokyo 110-0014 日本", "+81 3-6371-7917", 46));
        hotels.add(new Hotel("Hamacho Hotel", "3 Chome-20-2 Nihonbashihamacho, Chuo City, Tokyo 103-0007 日本", "+81 3-5643-1811", 47));
        hotels.add(new Hotel("Hotel + Hostel Tokyo Asakusa 2", "1 Chome-7-10 Hanakawado, Taito City, Tokyo 111-0033 日本", "+81 50-1808-6043", 48));
        hotels.add(new Hotel("KAIKA Tokyo by The Share Hotels", "2 Chome-16-5 Honjo, Sumida City, Tokyo 130-0004 日本", "+81 3-3625-2165", 49));
        hotels.add(new Hotel("THE skm TOKYO HOTEL&DINING", "4 Chome-25-5 Kotobashi, Sumida City, Tokyo 130-0022 日本", "+81 3-5600-7666", 50));

        HOTEL_LIST = hotels;
    }
}