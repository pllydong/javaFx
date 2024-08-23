package sample.utils;

import cn.hutool.core.util.StrUtil;
import sample.model.Branch;
import sample.model.CacheData;
import sample.model.Hotel;
import sample.model.TouristSpot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 初始化数据
 * @author guokun
 * @date 2024/8/17 16:04
 */
public class DataInitUtil {
    public static void initData() {
        initBranches();
    }

    private static void initBranches() {
        clearData();

        Branch rootBranch = newBranch("日本 (Japan)");
        CacheData.setRootBranch(rootBranch);

        // 东京
        initBranchTokyo(rootBranch);

        // 关西
        initBranchGuanxi(rootBranch);

        // 中部
        initCenterBranch(rootBranch);

        // 九州
        initBranchJiuZhou(rootBranch);

        // 北海道
        initBranchBeiHaiDao(rootBranch);

    }

    private static void initBranchBeiHaiDao(Branch rootBranch) {
        Branch branch = newBranch(rootBranch.getId(), "北海道 (北海道)");

        createBranch(branch.getId(), "登别、洞爷湖 (Noboribetsu、Lake Toya)",
                "1.     Noboribetsu Onsen (登別温泉)    \n" +
                        "2.     Noboribetsu Bear Park (登別クマ牧場)    \n" +
                        "3.     Noboribetsu Date Jidaimura (登別伊達時代村)    \n" +
                        "4.     Oyunuma (大湯沼)    \n" +
                        "5.     Noboribetsu Marine Park (登別マリンパーク)    \n" +
                        "6.     Noboribetsu Onsen Dai Hotel (登別温泉大ホテル)     \n" +
                        "7.     Noboribetsu Onsen Jigoku Ramen (登別温泉地獄ラーメン)    \n" +
                        "8.   Noboribetsu Primeval Forest (登別原生林)    \n" +

                        "1.     Lake Toya (洞爺湖)    \n" +
                        "2.     Showa Shinzan (昭和新山)    \n" +
                        "3.     Usuzan (有珠山)    \n" +
                        "4.     Toyako Onsen (洞爺湖温泉)    \n" +
                        "5.     Toyako Volcano Science Museum (洞爺湖火山科学館)    \n" +
                        "6.     Toyako Sightseeing Boat (洞爺湖観光船)     \n" +
                        "7.     Toyako Onsen Street (洞爺湖温泉街)    \n" +
                        "8.     Sobetsu Fruit Village (壮瞥フルーツ村)    \n" +
                        "9.   Toyako Onsen Hotel (洞爺湖温泉ホテル)    ",
                "1. 登别、洞爷湖 - 登别温泉乡村酒店 (Noboribetsu Grand Hotel)\n" +
                        "   - 地址: 154 Noboribetsu Onsen-cho, Noboribetsu, Hokkaido 059-0592, Japan\n" +
                        "   - 电话: +81 143-84-2101\r" +
                        "2. 登别温泉酒店 (Noboribetsu Sekisui-Tei)\n" +
                        "   - 地址: 203-1 Noboribetsu Onsencho, Noboribetsu, Hokkaido 059-0596, Japan\n" +
                        "   - 电话: +81 143-84-2255\r" +
                        "3. 登别皇家酒店 (Noboribetsu Manseikaku)\n" +
                        "   - 地址: 21 Noboribetsu Onsen-cho, Noboribetsu, Hokkaido 059-0551, Japan\n" +
                        "   - 电话: +81 143-84-3501\r" +
                        "4. 洞爷湖万豪酒店 (The Windsor Hotel Toya Resort & Spa)\n" +
                        "   - 地址: 336 Shimizu, Toyako-cho, Abuta-gun, Hokkaido 049-5722, Japan\n" +
                        "   - 电话: +81 142-73-1111\r" +
                        "5. 洞爷湖日航酒店 (The Lake View Toya Nonokaze Resort)\n" +
                        "   - 地址: 29-1 Toyako Onsen, Toyako-cho, Abuta-gun, Hokkaido 049-5721, Japan\n" +
                        "   - 电话: +81 142-75-2600\r" +
                        "6. 洞爷湖温泉酒店 (Toya Sun Palace Resort & Spa)\n" +
                        "   - 地址: 7-1 Toyako Onsen, Toyako-cho, Abuta-gun, Hokkaido 049-5721, Japan\n" +
                        "   - 电话: +81 142-75-1111\r" +
                        "7. 登别酒店 (Noboribetsu Onsen Hotel Mahoroba)\n" +
                        "   - 地址: 65 Noboribetsu Onsen-cho, Noboribetsu, Hokkaido 059-0551, Japan\n" +
                        "   - 电话: +81 143-84-2211\r" +
                        "8. 洞爷湖喜来登酒店 (Sheraton Hokkaido Kiroro Resort)\n" +
                        "   - 地址: 128-1 Tokiwa, Akaigawa-mura, Yoichi-gun, Hokkaido 046-0593, Japan\n" +
                        "   - 电话: +81 135-34-7111\r" +
                        "9. 洞爷湖酒店 (Toya Kohantei)\n" +
                        "   - 地址: 7-8 Toyako Onsen, Toyako-cho, Abuta-gun, Hokkaido 049-5721, Japan\n" +
                        "   - 电话: +81 142-75-2121\r"
        );


        createBranch(branch.getId(), " 富良野 (Furano)",
                "1.     Farm Tomita (ファーム富田)    \n" +
                        "2.     Saika no Sato (彩香の里)    \n" +
                        "3.     Biei Patchwork Road (美瑛のパッチワークの路)    \n" +
                        "4.     Shikisai no Oka (四季彩の丘)    \n" +
                        "5.     Furano Wine Factory (富良野ワイン工場)    \n" +
                        "6.     Furano Ski Resort (富良野スキー場)    \n" +
                        "7.     Furano Cheese Factory (富良野チーズ工房)    \n" +
                        "8.     Furano Wine Vineyard (富良野ワイン葡萄園)    \n" +
                        "9.     Furano Prince Hotel (富良野プリンスホテル)    \n" +
                        "10.   Kumagera (くまげら)     ",
                "1. 富良野温泉酒店 (New Furano Prince Hotel)\n" +
                        "   - 地址: Nakagoryo, Furano, Hokkaido 076-8511, Japan\n" +
                        "   - 电话: +81 167-22-1111\r" +
                        "2. 富良野酒店 (Hotel Naturwald Furano)\n" +
                        "   - 地址: 14-46 Kitanomine-cho, Furano, Hokkaido 076-0034, Japan\n" +
                        "   - 电话: +81 167-22-1211\r" +
                        "3. 富良野乡村酒店 (Furano La Terre)\n" +
                        "   - 地址: 1488-1 Yamabe, Furano, Hokkaido 076-0048, Japan\n" +
                        "   - 电话: +81 167-42-5111\r" +
                        "4. 富良野度假酒店 (Furano Resort Orika)\n" +
                        "   - 地址: 2-3 Furano, Hokkaido 076-0035, Japan\n" +
                        "   - 电话: +81 167-22-2311\r" +
                        "5. 富良野日式酒店 (Furano Hops Hotel)\n" +
                        "   - 地址: 25-10 Naka-Goryo, Furano, Hokkaido 076-0014, Japan\n" +
                        "   - 电话: +81 167-22-1111\r" +
                        "6. 富良野皇家酒店 (Hotel Naturwald Furano)\n" +
                        "   - 地址: 14-46 Kitanomine-cho, Furano, Hokkaido 076-0034, Japan\n" +
                        "   - 电话: +81 167-22-1211\r" +
                        "7. 富良野君悦酒店 (Furano Prince Hotel)\n" +
                        "   - 地址: Nakagoryo, Furano, Hokkaido 076-8511, Japan\n" +
                        "   - 电话: +81 167-22-1111\r" +
                        "8. 富良野花园酒店 (Furano Hotel)\n" +
                        "   - 地址: 15-1 Kitanomine-cho, Furano, Hokkaido 076-0034, Japan\n" +
                        "   - 电话: +81 167-22-1111\r" +
                        "9. 富良野湖景酒店 (Hotel Edel Warme)\n" +
                        "   - 地址: 9-20 Kitanomine-cho, Furano, Hokkaido 076-0034, Japan\n" +
                        "   - 电话: +81 167-22-1161\r" +
                        "10. 富良野精品酒店 (Furano Natulux Hotel)\n" +
                        "    - 地址: 1-35 Asahi-machi, Furano, Hokkaido 076-0026, Japan\n" +
                        "    - 电话: +81 167-22-1777"
        );

        createBranch(branch.getId(), "札幌、小樽 (Sapporo、Otaru)",
                "1.     Sapporo TV Tower (札幌テレビ塔)    \n" +
                        "2.     Odori Park (大通公園)    \n" +
                        "3.     Sapporo Clock Tower (札幌市時計台)    \n" +
                        "4.     Hokkaido University (北海道大学)    \n" +
                        "5.     Shiroi Koibito Park (白い恋人パーク)    \n" +
                        "6.     Hokkaido Shrine (北海道神宮)    \n" +
                        "7.     Susukino (すすきの)    \n" +
                        "8.     Shiroi Koibito Park Restaurant (白い恋人パークレストラン)    \n" +
                        "9.     Jingisukan Daruma (成吉思汗 だるま)    \n" +
                        "10.   Nijo Market (二条市場)    \n" +
                        "" +
                        "1.     Otaru Canal (小樽運河)    \n" +
                        "2.     Otaru Music Box Museum (小樽オルゴール堂)    \n" +
                        "3.     Otaru Glass Studio (小樽ガラス工房)    \n" +
                        "4.     Otaru Sushi Street (小樽寿司屋通り)    \n" +
                        "5.     Tenguyama (天狗山)    \n" +
                        "6.     Kitaichi Glass Shop No. 3 (北一硝子三号館)    \n" +
                        "7.     Masazushi (政寿司)    \n" +
                        "8.     Otaru Canal Warehouse District (小樽運河倉庫群)    \n" +
                        "9.     Otaru Music Box Museum Main Hall (小樽オルゴール堂本館)    \n" +
                        "10.   Otaru Aquarium (小樽水族館)    ",

                        "1. 札幌喜来登酒店 (Sheraton Sapporo Hotel)\n" +
                                "   - 地址: 5-10-1 Minami 2-jo Nishi, Chuo-ku, Sapporo, Hokkaido 060-0062, Japan\n" +
                                "   - 电话: +81 11-512-3355\r" +
                                "2. 札幌大仓酒店 (Hotel Okura Sapporo)\n" +
                                "   - 地址: 9-1 Nishi 5-Chome, Minami 1-Jo, Chuo-Ku, Sapporo, Hokkaido 060-0061, Japan\n" +
                                "   - 电话: +81 11-221-2333\r" +
                                "3. 札幌万豪酒店 (JR Tower Hotel Nikko Sapporo)\n" +
                                "   - 地址: 5 Nishi 2-Chome, Kita 5-Jo, Chuo-Ku, Sapporo, Hokkaido 060-0005, Japan\n" +
                                "   - 电话: +81 11-251-2222\r" +
                                "4. 札幌瑞吉酒店 (The St. Regis Sapporo)\n" +
                                "   - 地址: 1-4 Nishi 1-Chome, Kita 1-Jo, Chuo-Ku, Sapporo, Hokkaido 060-0001, Japan\n" +
                                "   - 电话: +81 11-231-2222\r" +
                                "5. 札幌希尔顿酒店 (Hilton Sapporo Park)\n" +
                                "   - 地址: 7-1 Odori Nishi, Chuo-Ku, Sapporo, Hokkaido 060-0042, Japan\n" +
                                "   - 电话: +81 11-251-2222\r" +
                                "6. 札幌皇家花园酒店 (The Royal Park Hotel Sapporo)\n" +
                                "   - 地址: 1-1-1 Minami 2-Jo Nishi, Chuo-Ku, Sapporo, Hokkaido 060-0062, Japan\n" +
                                "   - 电话: +81 11-251-2222\r" +
                                "7. 札幌皇宫酒店 (Sapporo Grand Hotel)\n" +
                                "   - 地址: 4-6-1 Kita 1-Jo Nishi, Chuo-Ku, Sapporo, Hokkaido 060-0001, Japan\n" +
                                "   - 电话: +81 11-251-2222\r" +
                                "8. 札幌日航酒店 (Sapporo Prince Hotel)\n" +
                                "   - 地址: 1-2-9 Kita 5-Jo Nishi, Chuo-Ku, Sapporo, Hokkaido 060-0005, Japan\n" +
                                "   - 电话: +81 11-251-2222\r" +
                                "9. 札幌豪华酒店 (Jozankei Grand Hotel Zuien)\n" +
                                "   - 地址: 4-30 Jozankei Onsen Higashi, Minami-Ku, Sapporo, Hokkaido 061-2302, Japan\n" +
                                "   - 电话: +81 11-598-2311\r" +
                                "10. 小樽温泉酒店 (Hotel Sonia Otaru)\n" +
                                "    - 地址: 1-4-20 Ironai, Otaru, Hokkaido 047-0031, Japan\n" +
                                "    - 电话: +81 134-21-3151"
                );
    }

    private static void initBranchJiuZhou(Branch rootBranch) {
        Branch branch = newBranch(rootBranch.getId(), "九州 (九州)");

        createBranch(branch.getId(), "福岡 (Fukuoka)",
                "1.     Fukuoka Castle (福岡城)    \n" +
                        "2.     Ohori Park (大濠公園)    \n" +
                        "3.     Fukuoka Tower (福岡タワー)    \n" +
                        "4.     Dazaifu Tenmangu Shrine (太宰府天満宮)    \n" +
                        "5.     Marine World Uminonakamichi (マリンワールド海の中道)    \n" +
                        "6.     Hakata Station (博多駅)    \n" +
                        "7.     Nakasu Yatai Street (中洲屋台街)    \n" +
                        "8.     Canal City Hakata (キャナルシティ博多)    \n" +
                        "9.     Tenjin Underground Shopping Mall (天神地下街)    \n" +
                        "10.    Fukuoka Art Museum (福岡市美術館)    \n" +
                        "11.    Fukuoka City Zoological Garden (福岡市動植物園)    \n" +
                        "12.    Hakata Dontaku Festival (博多どんたく港まつり)    \n" +
                        "13.    Kushida Shrine (櫛田神社)    \n" +
                        "14.    Fukuoka Anpanman Children's Museum (福岡アンパンマンこどもミュージアム)    \n" +
                        "15.    Yanagawa River Cruising (柳川川下り)    \n" +
                        "16.    Nokonoshima Island (能古島)    \n" +
                        "17.    Fukuoka City Museum (福岡市博物館)    \n" +
                        "18.    ACROS Fukuoka (アクロス福岡)    \n" +
                        "19.    Momochi Seaside Park (ももち浜ビーチ)    \n" +
                        "20.    Hakata Port Bayside Place (博多港ベイサイドプレイス)    \n" +
                        "1.     Ramen Stadium (ラーメンスタジアム)    \n" +
                        "2.     Fukuoka Parco (福岡パルコ)    \n" +
                        "3.     Hakataza Theater (博多座)    \n" +
                        "4.     Ichiran Main Store (一蘭本社総本店)    \n" +
                        "5.     Fukuoka Asian Art Museum (福岡アジア美術館)    \n" +
                        "6.     Riverwalk Kitakyushu (リバーウォーク北九州)    \n" +
                        "7.     Seaside Momochi (シーサイドももち)    \n" +
                        "8.     Motsunabe Yamaya (もつ鍋やまや)    \n" +
                        "9.     Hakata Ichibangai (博多一番街)    \n" +
                        "10.   Tempura Hirao (天ぷらひらお)  ",
                "1. 福冈 - 福冈日航酒店 (Hotel Nikko Fukuoka)\n" +
                        "   - 地址: 2-18-25 Hakataekimae, Hakata-ku, Fukuoka, 812-0011, Japan\n" +
                        "   - 电话: +81 92-482-1111\r" +
                        "2. 福冈大仓酒店 (Hotel Okura Fukuoka)\n" +
                        "   - 地址: 3-2 Shimokawabata-machi, Hakata-ku, Fukuoka 812-0027, Japan\n" +
                        "   - 电话: +81 92-262-1111\r" +
                        "3. 福冈希尔顿酒店 (Hilton Fukuoka Sea Hawk)\n" +
                        "   - 地址: 2-2-3 Jigyohama, Chuo-ku, Fukuoka 810-8650, Japan\n" +
                        "   - 电话: +81 92-844-8111\r" +
                        "4. 福冈万豪酒店 (The Ritz-Carlton, Fukuoka)\n" +
                        "   - 地址: 2-6-60 Daimyo, Chuo-ku, Fukuoka 810-0041, Japan\n" +
                        "   - 电话: +81 92-715-1111\r" +
                        "5. 福冈哈卡塔皇家酒店 (Grand Hyatt Fukuoka)\n" +
                        "   - 地址: 1-2-82 Sumiyoshi, Hakata-ku, Fukuoka 812-0018, Japan\n" +
                        "   - 电话: +81 92-282-1234\r" +
                        "6. 福冈皇宫酒店 (Hotel New Otani Hakata)\n" +
                        "   - 地址: 1-1-2 Watanabe-dori, Chuo-ku, Fukuoka 810-0004, Japan\n" +
                        "   - 电话: +81 92-714-1111\r" +
                        "7. 福冈君悦酒店 (Grand Hyatt Fukuoka)\n" +
                        "   - 地址: 1-2-82 Sumiyoshi, Hakata-ku, Fukuoka 812-0018, Japan\n" +
                        "   - 电话: +81 92-282-1234\r" +
                        "8. 福冈美丽殿酒店 (Hotel Monterey Fukuoka)\n" +
                        "   - 地址: 3-4-24 Watanabe-dori, Chuo-ku, Fukuoka 810-0004, Japan\n" +
                        "   - 电话: +81 92-737-7111\r" +
                        "9. 福冈皇家花园酒店 (Royal Park Hotel The Fukuoka)\n" +
                        "   - 地址: 2-14-15 Hakataekimae, Hakata-ku, Fukuoka 812-0011, Japan\n" +
                        "   - 电话: +81 92-414-1111");

        createBranch(branch.getId(), " 冲绳 (Okinawa)",
                "1.     Shurijo Castle (首里城)    \n" +
                        "2.     Okinawa Churaumi Aquarium (美ら海水族館)    \n" +
                        "3.     Kokusai Street (国際通り)    \n" +
                        "4.     Okinawa Churaumi Beach (沖縄美ら海ビーチ)    \n" +
                        "5.     Ryukyu Mura (琉球村)    \n" +
                        "6.     Manzamo (万座毛)    \n" +
                        "7.     Sefa-utaki (斎場御嶽)    \n" +
                        "8.     Kouri Bridge (古宇利大橋)    \n" +
                        "9.     Okinawa Peace Memorial Park (平和祈念公園)     \n" +
                        "10.   Katsuren Castle Ruins (勝連城跡)    \n" +
                        "11.   Zakimi Castle Ruins (座喜味城跡)    \n" +
                        "12.   Sesoko Beach (瀬底ビーチ)    \n" +
                        "13.   Fukugi Tree Road (フクギ並木)    \n" +
                        "14.   Hamahiga Island (浜比嘉島)    \n" +
                        "15.   Okinawa World (沖縄ワールド)    \n" +
                        "16.   Nirai Kanai Bridge (ニライカナイ橋)    \n" +
                        "17.   Cape Kyan (喜屋武岬)    \n" +
                        "18.   Blue Cave (青の洞窟)    \n" +
                        "19.   Gyokusendo Cave (玉泉洞)    \n" +
                        "20.   Shuri Kinjo-cho Stone-Paved Path (首里金城町石畳道)    \n" +
                        "1.     A&W Okinawa (A&W沖縄)    \n" +
                        "2.     Hanagasa Shokudo (花笠食堂)    \n" +
                        "3.     Gelateria Mint (ジェラテリア・ミント)    \n" +
                        "4.     Umikaze Yo (海風よ)    \n" +
                        "5.     Okinawa Soba no Mise Inaka (沖縄そばの店 田舎)    \n" +
                        "6.     Blue Seal (ブルーシール)    \n" +
                        "7.     Okashi Goten (御菓子御殿)    \n" +
                        "8.     Goya Shokudo (ゴーヤー食堂)    \n" +
                        "9.     Steak House 88 (ステーキハウス88)    \n" +
                        "10.   Kinchichi Soba (金月そば)    ",
                "1. 冲绳日航酒店 (Hotel Nikko Alivila)\n" +
                        "   - 地址: 600 Gima Yomitan-son, Nakagami-gun, Okinawa 904-0393, Japan\n" +
                        "   - 电话: +81 98-958-5000\r" +
                        "2. 冲绳希尔顿酒店 (Hilton Okinawa Chatan Resort)\n" +
                        "   - 地址: 40-1 Mihama, Chatan-cho, Nakagami-gun, Okinawa 904-0115, Japan\n" +
                        "   - 电话: +81 98-901-1111\r" +
                        "3. 冲绳万豪酒店 (The Ritz-Carlton, Okinawa)\n" +
                        "   - 地址: 1343-1 Kise, Nago, Okinawa 905-0026, Japan\n" +
                        "   - 电话: +81 980-43-5555\r" +
                        "4. 冲绳丽思卡尔顿酒店 (The Ritz-Carlton, Okinawa)\n" +
                        "   - 地址: 1343-1 Kise, Nago, Okinawa 905-0026, Japan\n" +
                        "   - 电话: +81 980-43-5555\r" +
                        "5. 冲绳君悦酒店 (Hyatt Regency Seragaki Island Okinawa)\n" +
                        "   - 地址: 1108 Seragaki, Onna-son, Kunigami-gun, Okinawa 904-0404, Japan\n" +
                        "   - 电话: +81 98-960-4321\r" +
                        "6. 冲绳喜来登酒店 (Sheraton Okinawa Sunmarina Resort)\n" +
                        "   - 地址: 66-1 Fuchaku, Onna-son, Kunigami-gun, Okinawa 904-0494, Japan\n" +
                        "   - 电话: +81 98-965-2222\r" +
                        "7. 冲绳皇冠假日酒店 (ANA InterContinental Manza Beach Resort)\n" +
                        "   - 地址: 2260 Seragaki, Onna-son, Kunigami-gun, Okinawa 904-0493, Japan\n" +
                        "   - 电话: +81 98-966-1211\r" +
                        "8. 冲绳酒店 (Hotel Monterey Okinawa Spa & Resort)\n" +
                        "   - 地址: 1550-1 Fuchaku, Onna-son, Kunigami-gun, Okinawa 904-0413, Japan\n" +
                        "   - 电话: +81 98-993-7111\r" +
                        "9. 冲绳皇家花园酒店 (The Royal Park Hotel Fukuoka)\n" +
                        "   - 地址: 2-14-15 Hakataekimae, Hakata-ku, Fukuoka 812-0011, Japan\n" +
                        "   - 电话: +81 92-414-1111\r" +
                        "10. 冲绳豪华酒店 (Halekulani Okinawa)\n" +
                        "   - 地址: 1967-1 Nakama, Onna-son, Kunigami-gun, Okinawa 904-0401, Japan\n" +
                        "   - 电话: +81 98-953-8600\r"
        );
        createBranch(branch.getId(), " 宮崎 (Miyazaki)",
                "1.     Aoshima Shrine (青島神社)    \n" +
                        "2.     Devil's Washboard (鬼の洗濯板)    \n" +
                        "3.     Udo Shrine (鵜戸神宮)    \n" +
                        "4.     Takachiho Gorge (高千穂峡)    \n" +
                        "5.     Takachiho Shrine (高千穂神社)    \n" +
                        "6.     Nichinan Coast (日南海岸)    \n" +
                        "7.     Toi Cape (都井岬)    \n" +
                        "8.     Obi Castle (飫肥城)    \n" +
                        "9.     Phoenix Zoo (フェニックス自然動物園)    \n" +
                        "10.   Ebino Plateau (えびの高原)    \n" +
                        "11.   Sun Messe Nichinan (サンメッセ日南)    \n" +
                        "12.   Aoshima Beach (青島海水浴場)    \n" +
                        "13.   Miyazaki Prefectural Museum of Nature and History (宮崎県総合博物館)    \n" +
                        "14.   Saitobaru Burial Mounds (西都原古墳群)    \n" +
                        "15.   Miyazaki Science Center (宮崎科学技術館)   \n" +
                        "16.   Michi-no-Eki Phoenix (道の駅フェニックス) 17.   Miyazaki City Phoenix Zoo (宮崎市フェニックス自然動物園)    \n" +
                        "1.     Jidori Sumibiyaki Kuruma (地鶏炭火焼 車) \n" +
                        "2.     Mango Farm (マンゴー農園)  \n" +
                        "3.     Kamaage Udon Togakushi (釜揚げうどん 戸隠)\n" +
                        "4.     Kaisenyasan Umi-Sachi (海鮮居酒屋 海幸)  \n" +
                        "5.     Chicken Nanban Ogura Honten (チキン南蛮のお店 おぐら本店)   \n" +
                        "6.   Saito Winery (西都ワイナリー) ",
                "1. 宫崎温泉酒店 (ANA Holiday Inn Resort Miyazaki)\n" +
                        "   - 地址: 1-16-1 Aoshima, Miyazaki, 889-2162, Japan\n" +
                        "   - 电话: +81 985-65-1555\r" +
                        "2. 宫崎东方酒店 (Hotel Marix Lagoon)\n" +
                        "   - 地址: 3-2-23 Tachibanadorihigashi, Miyazaki, 880-0805, Japan\n" +
                        "   - 电话: +81 985-28-1111\r" +
                        "3. 宫崎太阳酒店 (Sun Messe Miyazaki)\n" +
                        "   - 地址: 2650-1 Miyakoda, Miyazaki, 880-2101, Japan\n" +
                        "   - 电话: +81 985-65-2021\r" +
                        "4.宫崎山荘酒店 (Garden Terrace Miyazaki Hotels & Resorts)\n" +
                        "   - 地址: 4-8-1 Miyazakiekihigashi, Miyazaki, 880-0812, Japan\n" +
                        "   - 电话: +81 985-73-8888\r" +
                        "5. 宫崎万豪酒店 (Sheraton Grande Ocean Resort)\n" +
                        "   - 地址: Hamayama, Yamasaki-cho, Miyazaki, 880-8545, Japan\n" +
                        "   - 电话: +81 985-21-1113\r" +
                        "6. 宫崎温泉乡村酒店 (Aoshima Grand Hotel)\n" +
                        "   - 地址: 1-16-64 Aoshima, Miyazaki, 889-2162, Japan\n" +
                        "   - 电话: +81 985-65-2011\r" +
                        "7. 宫崎海滨酒店 (Seagaia Ocean Resort)\n" +
                        "   - 地址: 3083-1 Hamayama, Yamasaki-cho, Miyazaki, 880-8545, Japan\n" +
                        "   - 电话: +81 985-21-1311\r" +
                        "8. 宫崎豪华酒店 (Garden Terrace Miyazaki Hotels & Resorts)\n" +
                        "    - 地址: 4-8-1 Miyazakiekihigashi, Miyazaki, 880-0812, Japan\n" +
                        "    - 电话: +81 985-73-8888"
        );
        createBranch(branch.getId(), " 鹿児島 (Kagoshima)",
                "1.     Sakurajima (桜島)    \n" +
                        "2.     Kagoshima Castle (鹿児島城)    \n" +
                        "3.     Senganen Garden (仙巌園)    \n" +
                        "4.     Kagoshima Chuo Station (鹿児島中央駅)    \n" +
                        "5.     Shiroyama Observatory (城山展望台)    \n" +
                        "6.     Tenmonkan (天文館)    \n" +
                        "7.     Hirakawa Zoological Park (平川動物園)    \n" +
                        "8.     Ibusuki Onsen (指宿温泉)    \n" +
                        "9.     Chiran Peace Museum (知覧特攻平和会館)  10.    Kirishima Shrine (霧島神宮)    \n" +
                        "11.    Kagoshima City Museum of Art (鹿児島市立美術館)    \n" +
                        "12.    Kagoshima Aquarium (鹿児島水族館)    \n" +
                        "13.    Museum of the Meiji Restoration (維新ふるさと館)    \n" +
                        "14.    Reimeikan, Kagoshima Prefectural Center for Historical Material (鹿児島県歴史資料センター黎明館)    \n" +
                        "15.    Sakurajima Ferry (桜島フェリー)    \n" +
                        "16.    Kagoshima Meiji Restoration Museum (かごしま明治維新館)    \n" +
                        "17.    Satsuma Denshokan (薩摩伝承館)    \n" +
                        "1.     Tenmonkan Mujyaki (天文館むじゃき)    \n" +
                        "2.     Shiroyama Park (城山公園)    \n" +
                        "3.     Kagoshima Ramen Buta-Toro (鹿児島ラーメン豚とろ)    \n" +
                        "4.     Satsuma Kinzangura Shochu Brewery (焼酎蔵 薩摩金山蔵)    \n" +
                        "5.     Cape Nagasakibana (長崎鼻)    \n" +
                        "6.     Senganen Tea House (仙巌園の茶室)    \n" +
                        "7.     Nanshu Shrine (南洲神社)    \n" +
                        "8.     Kirishima Onsen Market (霧島温泉市場)    \n" +
                        "9.     Kagoshima Central Market (鹿児島中央市場)    \n" +
                        "10.   Senganen Garden Restaurant (名勝 仙巌園の料亭)  ",
                "1. 鹿儿岛皇家酒店 (Hotel Shiroyama Kagoshima)\n" +
                        "   - 地址: 41-1 Shinsyouincho, Kagoshima, Kagoshima 890-8586, Japan\n" +
                        "   - 电话: +81 99-224-2211\r" +
                        "2. 鹿儿岛日航酒店 (Hotel Nikko Kagoshima)\n" +
                        "   - 地址: 15-1 Kamoike, Kagoshima, Kagoshima 890-0064, Japan\n" +
                        "   - 电话: +81 99-257-2411\r" +
                        "3. 鹿儿岛万豪酒店 (Sheraton Kagoshima)\n" +
                        "   - 地址: 1-3-1 Furusato-cho, Kagoshima, Kagoshima 890-0045, Japan\n" +
                        "   - 电话: +81 99-214-7766\r" +
                        "4. 鹿儿岛喜来登酒店 (Sheraton Kagoshima)\n" +
                        "   - 地址: 1-3-1 Furusato-cho, Kagoshima, Kagoshima 890-0045, Japan\n" +
                        "   - 电话: +81 99-214-7766\r" +
                        "5. 鹿儿岛皇冠假日酒店 (ANA Crowne Plaza Hotel Kagoshima)\n" +
                        "   - 地址: 8-1-1 Nishihara, Kagoshima, Kagoshima 890-8581, Japan\n" +
                        "   - 电话: +81 99-213-2211\r" +
                        "6. 鹿儿岛皇家花园酒店 (The Royal Park Hotel Fukuoka)\n" +
                        "   - 地址: 2-14-15 Hakataekimae, Hakata-ku, Fukuoka 812-0011, Japan\n" +
                        "   - 电话: +81 92-414-1111\r" +
                        "7. 鹿儿岛豪华酒店 (Kagoshima Tokyu Hotel)\n" +
                        "   - 地址: 22-1 Kamoike, Kagoshima, Kagoshima 890-0063, Japan\n" +
                        "   - 电话: +81 99-257-2411\r" +
                        "8. 鹿儿岛温泉酒店 (Kirishima Kokusai Hotel)\n" +
                        "   - 地址: 3930-12 Makizonocho Takachiho, Kirishima, Kagoshima 899-6603, Japan\n" +
                        "   - 电话: +81 995-78-2525\r"
        );
        createBranch(branch.getId(), " 熊本 (Kumamoto)",
                "1.     Kumamoto Castle (熊本城)    \n" +
                        "2.     Suizenji Jojuen Garden (水前寺成趣園)    \n" +
                        "3.     Mount Aso (阿蘇山)    \n" +
                        "4.     Kusasenri (草千里ヶ浜)    \n" +
                        "5.     Kurokawa Onsen (黒川温泉)    \n" +
                        "6.     Kamitori Arcade (上通りアーケード)    \n" +
                        "7.     Shimotori Arcade (下通りアーケード)    \n" +
                        "8.     Kumamoto City Zoological and Botanical Gardens (熊本市動植物園)    \n" +
                        "9.     Mushagaeshi no Saka (武者返しの坂)    \n" +
                        "10.   Yachiyoza Theatre (八千代座)    \n" +
                        "11.   Greenland Amusement Park (グリーンランド)    \n" +
                        "12.   Kumamoto Prefectural Museum of Art (熊本県立美術館)    \n" +
                        "13.   Tsuruya Department Store (鶴屋百貨店)    \n" +
                        "14.   Mitsui Greenland (三井グリーンランド)    \n" +
                        "15.   Shirakawa Springs (白川水源)    \n" +
                        "16.   Minamata Bay Park (水俣湾親水公園)    \n" +
                        "17.   Nabegataki Waterfall (鍋ヶ滝)    \n" +
                        "18.   Amakusa Pearl Center (天草パールセンター)    \n" +
                        "19.   Shirakawa Onsen (白川温泉)    \n" +
                        "1.     BAZAKURA (馬桜)   \n" +
                        "2.     Ichiran Kumamoto (一蘭 熊本店)   \n" +
                        "3.     Suganoya (菅乃屋)    \n" +
                        "4.     Ramen Akagumi (ラーメン赤組)  \n" +
                        "5.     Kumamoto Tonkotsu Ramen (くまもと豚骨ラーメン)  \n" +
                        "6.     Ikinari Dango Honpo (いきなり団子本舗)  \n" +
                        "7.     Daikokuya (大黒屋)   \n" +
                        "8.     Sakuranobaba Josaien (桜の馬場 城彩苑) \n" +
                        "9.     Basashi Izakaya Sakura (馬刺し 居酒屋桜)  \n" +
                        "10.   Kumamoto Ramen Keika (熊本ラーメン 桂花) ",
                "1.熊本之花酒店(THE BLOSSOM KUMAMOTO)\n" +
                        "-地址：3 Chome-15-26 Kasuga, Nishi Ward, Kumamoto, 860-0047,Japan\n" +
                        "-电话：+81 96-327-8763\r" +
                        "2.三井花园饭店熊本-(Mitsui Garden Hotel Kumamoto)\n" +
                        "-地址：1-20 Koyaimamachi, Chuo Ward, Kumamoto, 860-0012 Japan\n" +
                        "-电话：+81 96-352-1131\r" +
                        "3.船舶酒店集团熊本REF酒店-(REF Kumamoto by VESSEL HOTELS)\n" +
                        "-地址:7-2 Shinshigai, Chuo Ward, Kumamoto, 860-0803,Japan\n" +
                        "-电话:+81 96-328-7777\r" +
                        "4.熊本法夫酒店-(FAV HOTEL KUMAMOTO)\n" +
                        "-地址:9-1 Kajiyamachi, Chuo Ward, Kumamoto, 860-0026 Japan \n" +
                        "-电话:+81 92-292-2431\r" +
                        "5.阿斯科特广场饭店-(Place Hotel Ascot)\n" +
                        "-地址:6-4 Anseimachi, Chuo Ward, Kumamoto, 860-0801 Japan\n" +
                        "-电话:+81 96-328-6000\r" +
                        "6.熊本网格高级酒店-(Grids Premium Hotel Kumamoto)\n" +
                        "-地址:1-7 Koyaimamachi, Chuo Ward, Kumamoto, 860-0012,Japan\n" +
                        "-电话:+81 96-312-2700\r" +
                        "7.多米熊本酒店-(Dormy Inn Kumamoto)\n" +
                        "-地址:3-1 Karashimacho, Chuo Ward, Kumamoto, 860-0804,Japan\n" +
                        "-电话:+81 96-311-5489\r" +
                        "8.熊本新酒店-(THE NEW hotel kumamoto)\n" +
                        "-地址:1 Chome-13-1 Kasuga, Nishi Ward, Kumamoto, 860-0047,Japan\n" +
                        "-电话:+81 96-326-1111\r" +
                        "9.熊本大和魯內酒店-(Daiwa Roynet Hotel Kumamoto)\n" +
                        "-地址:10-22 Hanabatacho, Chuo Ward, Kumamoto, 860-0806,Japan\n" +
                        "-电话:+81 96-319-1050\r" +
                        "10.方舟大熊本酒店-(Ark Hotel Kumamotojomae)\n" +
                        "-地址:5-16 Jotomachi, Chuo Ward, Kumamoto, 860-0846,Japan\n" +
                        "-电话:+81 96-351-2222\r"
        );
    }

    private static void initCenterBranch(Branch rootBranch) {
        Branch branch = newBranch(rootBranch.getId(), "中部 (中部)");

        createBranch(branch.getId(), " 富士山 (Mount Fuji)",
                "1.     Mount Fuji Fifth Station (富士山五合目)    \n" +
                        "2.     Fuji-Q Highland (富士急ハイランド)    \n" +
                        "3.     Oshino Hakkai (忍野八海)    \n" +
                        "4.     Fuji Five Lakes (富士五湖)    \n" +
                        "5.     Lake Kawaguchi (河口湖)    \n" +
                        "6.     Fujisan Hongu Sengen Taisha Shrine (富士山本宮浅間大社)    \n" +
                        "7.     Chureito Pagoda (新倉山浅間公園)    \n" +
                        "8.     Climbing Mount Fuji (富士山登山)    \n" +
                        "9.     Fuji Shibazakura Festival (富士芝桜祭り)    \n" +
                        "10.   Shiraito Falls (白糸の滝)    \n" +
                        "11.   Narusawa Ice Cave (鳴沢氷穴)    \n" +
                        "12.   Mt. Fuji Panoramic Ropeway (富士山パノラマロープウェイ)    \n" +
                        "13.   Fuji Subaru Line (富士山スバルライン)    \n" +
                        "14.   Aokigahara Forest (青木ヶ原樹海)    \n" +
                        "15.   Fuji Speedway (富士スピードウェイ)    \n" +
                        "16.   Fuji Safari Park (富士サファリパーク)    \n" +
                        "17.   Houtou Fudou (ほうとう不動)    \n" +
                        "18.   Yamanakako Hananomiyako Park (山中湖花の都公園)    \n" +
                        "19.   Gotemba Premium Outlets (御殿場プレミアムアウトレット)    \n" +
                        "20.   Mt. Fuji World Heritage Center (富士山世界遺産センター)    ",
                "1. 富士山 - 富士景酒店 (Hotel Mystays Fuji)\n" +
                        "   - 地址: 401-0301 Yamanashi, Fujiyoshida, Arakura 2654, Japan\n" +
                        "   - 电话: +81 555-24-3221\r" +
                        "2. 富士山太阳酒店 (Fuji View Hotel)\n" +
                        "   - 地址: 511 Katsuyama, Fujikawaguchiko, Minamitsuru-gun, Yamanashi 401-0310, Japan\n" +
                        "   - 电话: +81 555-83-2211\r" +
                        "3. 富士河口湖温泉酒店 (Hotel New Century)\n" +
                        "   - 地址: 180-1 Azagawa, Fujikawaguchiko, Minamitsuru-gun, Yamanashi 401-0303, Japan\n" +
                        "   - 电话: +81 555-72-1414\r" +
                        "4. 富士山景酒店 (Highland Resort Hotel & Spa)\n" +
                        "   - 地址: 5-6-1 Shinnishihara, Fujiyoshida, Yamanashi 403-0017, Japan\n" +
                        "   - 电话: +81 555-22-1000\r" +
                        "5. 富士山广场酒店 (Fuji Lake Hotel)\n" +
                        "   - 地址: 1 Funatsu, Fujikawaguchiko, Minamitsuru-gun, Yamanashi 401-0301, Japan\n" +
                        "   - 电话: +81 555-72-2209\r" +
                        "6. 富士山景湖畔酒店 (Kawaguchiko Hotel)\n" +
                        "   - 地址: 200 Azagawa, Fujikawaguchiko, Yamanashi 401-0303, Japan\n" +
                        "   - 电话: +81 555-72-1332\r" +
                        "7. 富士山湖景酒店 (Fujikawaguchiko Onsen Hotel)\n" +
                        "   - 地址: 1184-1 Matsuyama, Fujikawaguchiko, Yamanashi 401-0310, Japan\n" +
                        "   - 电话: +81 555-83-1111\r" +
                        "8. 富士山花园酒店 (Shiki no Yado Fujisan)\n" +
                        "   - 地址: 6713-19 Funatsu, Fujikawaguchiko, Yamanashi 401-0301, Japan\n" +
                        "   - 电话: +81 555-83-3771\r" +
                        "9. 富士山蓝星酒店 (Hotel Konanso)\n" +
                        "   - 地址: 4020-2 Funatsu, Fujikawaguchiko, Minamitsuru-gun, Yamanashi 401-0301, Japan\n" +
                        "   - 电话: +81 555-72-2160\r" +
                        "10. 富士山皇家酒店 (Fuji Royal Hotel)\n" +
                        "   - 地址: 2-3-16 Kamiyoshida, Fujiyoshida, Yamanashi 403-0005, Japan\n" +
                        "   - 电话: +81 555-22-1717\r"
        );
        createBranch(branch.getId(), " 伊豆 (Izu)",
                "1.     Izu Kogen (伊豆高原)    \n" +
                        "2.     Izu Granpal Park (伊豆ぐらんぱる公園)    \n" +
                        "3.     Shuzenji Onsen (修善寺温泉)    \n" +
                        "4.     Jogasaki Coast (城ヶ崎海岸)    \n" +
                        "5.     Atami Onsen (熱海温泉)    \n" +
                        "6.     Izu Animal Kingdom (伊豆アニマルキングダム)    \n" +
                        "7.     Joren Falls (浄蓮の滝)   \n" +
                        "8.     Omuroyama (大室山)    \n" +
                        "9.     Izu Shaboten Zoo (伊豆シャボテン公園)    \n" +
                        "11.    Izu Teddy Bear Museum (伊豆テディベアミュージアム)    \n" +
                        "12.    Izu Ocean Park (伊豆海洋公園)    \n" +
                        "13.    Izu Biopark (伊豆バイオパーク)    \n" +
                        "14.    Atami Castle (熱海城)    \n" +
                        "15.    Izu Nagaoka Onsen (伊豆長岡温泉)    \n" +
                        "16.    Mishima Taisha Shrine (三嶋大社)    \n" +
                        "17.    Izu no Odoriko (伊豆の踊り子)    \n" +
                        "18.    Izu Skyline (伊豆スカイライン)  \n" +
                        "10.    Izu Kyuko Line (伊豆急行)  \n" +
                        "1.     Hakone Yumoto Onsen (箱根湯本温泉)    \n" +
                        "2.     Hakone Glass no Mori Museum (箱根ガラスの森美術館)    \n" +
                        "3.     Hakone Kowakien Yunessun (箱根小涌園ユネッサン)    \n" +
                        "4.     Hakone Open-Air Museum (箱根彫刻の森美術館)    \n" +
                        "5.     Hakone Shrine (箱根神社)    \n" +
                        "6.     Lake Ashi (芦ノ湖)    \n" +
                        "7.     Hakone Ropeway (箱根ロープウェイ)    \n" +
                        "8.     Owakudani (大涌谷)    \n" +
                        "9.     Gotemba Kogen Toki no Sumika (御殿場高原時之栖)    \n" +
                        "10.   Gotemba City Brewery (御殿場市役所前のビール醸造所)    ",
                "1. 伊豆温泉度假酒店 (Izu Marriott Hotel Shuzenji)\n" +
                        "   - 地址: 1529 Odaira, Izu, Shizuoka 410-2415, Japan\n" +
                        "   - 电话: +81 558-72-1311\r" +
                        "2. 伊豆温泉乡村酒店 (Kawana Hotel)\n" +
                        "   - 地址: 1459 Kawana, Ito, Shizuoka 414-0044, Japan\n" +
                        "   - 电话: +81 557-45-1111\r" +
                        "3. 伊豆滨田酒店 (Atami Fufu)\n" +
                        "   - 地址: 11-48 Minaguchicho, Atami, Shizuoka 413-0016, Japan\n" +
                        "   - 电话: +81 557-86-7777\r" +
                        "4. 伊豆美食温泉酒店 (Yagyu no Sho)\n" +
                        "   - 地址: 1116-6 Shuzenji, Izu, Shizuoka 410-2416, Japan\n" +
                        "   - 电话: +81 558-72-0011\r" +
                        "5. 伊豆日式酒店 (Arai Ryokan)\n" +
                        "   - 地址: 970 Shuzenji, Izu, Shizuoka 410-2416, Japan\n" +
                        "   - 电话: +81 558-72-2007\r" +
                        "6. 伊豆高原酒店 (Akazawa Geihinkan)\n" +
                        "   - 地址: 155 Akazawa, Ito, Shizuoka 413-0231, Japan\n" +
                        "   - 电话: +81 557-53-4900\r" +
                        "7. 伊豆酒店 (Hotel New Akao)\n" +
                        "   - 地址: 1993-250 Atami, Shizuoka 413-8555, Japan\n" +
                        "   - 电话: +81 557-82-5151\r"
        );
        createBranch(branch.getId(), " 名古屋 (Nagoya)",
                "1.     Nagoya Castle (名古屋城)    \n" +
                        "2.     Atsuta Shrine (熱田神宮)    \n" +
                        "3.     Nagoya Port Aquarium (名古屋港水族館)    \n" +
                        "4.     Osu Kannon (大須観音)    \n" +
                        "5.     Tokugawa Art Museum (徳川美術館)    \n" +
                        "6.     Nagoya TV Tower (名古屋テレビ塔)    \n" +
                        "7.     Higashiyama Zoo and Botanical Gardens (東山動植物園)    \n" +
                        "8.     Toyota Commemorative Museum of Industry and Technology (トヨタ産業技術記念館)    \n" +
                        "9.     Legoland Japan (レゴランド・ジャパン)    \n" +
                        "10.    Nagoya City Science Museum (名古屋市科学館)    \n" +
                        "11.    Nagoya Noh Theater (名古屋能楽堂)    \n" +
                        "12.    Nagoya/Boston Museum of Fine Arts (名古屋ボストン美術館)    \n" +
                        "13.    Shirotori Garden (白鳥庭園)    \n" +
                        "14.    Oasis 21 (オアシス21)    \n" +
                        "15.    Nagoya Parco (名古屋パルコ)    \n" +
                        "16.    Kinshachi Yokocho (金シャチ横丁)    \n" +
                        "17.    Sakae (栄)    \n" +
                        "18.    Nagoya Port (名古屋港)    \n" +
                        "19.    Hisaya Odori Park (久屋大通公園)    \n" +
                        "20.    Tsuruma Park (鶴舞公園)    \n" +
                        "1.     Nagoya Station (名古屋駅)    \n" +
                        "2.     Osu Shopping Street (大須商店街)    \n" +
                        "3.     Yamamotoya Sohonke (山本屋総本家) \n" +
                        "4.     Yabaton (矢場とん)\n" +
                        "5.     Houraiken (蓬莱軒) \n" +
                        "6.     Cafe de Crie (カフェ・ド・クリエ) \n" +
                        "7.     Nagoya TV Tower Restaurant (名古屋テレビ塔のレストラン) \n" +
                        "8.     Dai Nagoya Building (大名古屋ビルヂング)   \n" +
                        "9.     Midland Square (ミッドランドスクエア)   \n" +
                        "10.     Nagoya Castle Honmaru Palace (名古屋城本丸御殿) \n" +
                        "11.     LACHIC (ラシック) \n" +
                        "12.     Matsuzakaya Nagoya (松坂屋名古屋店) \n" +
                        "13.     Hoshigaoka Terrace (星ヶ丘テラス)   \n" +
                        "14.     Noritake Garden (ノリタケの森)\n" +
                        "15.     Nagoya City Museum (名古屋市博物館) \n" +
                        "16.     Nagoya City Art Museum (名古屋市美術館)  \n" +
                        "17.     Spiral Towers (スパイラルタワーズ)  \n" +
                        "18.     Artopia Hall (アートピアホール) \n" +
                        "19.     Nagoya Mitsukoshi Sakae (名古屋三越栄本店)  \n" +
                        "20.     Marriott Associa Hotel (マリオットアソシアホテル) ",
                "1. 名古屋 - 名古屋万豪酒店 (Nagoya Marriott Associa Hotel)\n" +
                        "   - 地址: 1-1-4 Meieki, Nakamura-ku, Nagoya, Aichi 450-6002, Japan\n" +
                        "   - 电话: +81 52-584-1111\r" +
                        "2. 名古屋皇冠酒店 (ANA Crowne Plaza Hotel Grand Court Nagoya)\n" +
                        "   - 地址: 1-1-1 Kanayama-cho, Naka-ku, Nagoya 460-0023, Japan\n" +
                        "   - 电话: +81 52-683-4111\r" +
                        "3. 名古屋西华酒店 (Hilton Nagoya)\n" +
                        "   - 地址: 1-3-3 Sakae, Naka-ku, Nagoya, Aichi 460-0008, Japan\n" +
                        "   - 电话: +81 52-212-1111\r" +
                        "4. 名古屋新大谷酒店 (Hotel New Otani Nagoya)\n" +
                        "   - 地址: 4-1 Sakae, Naka-ku, Nagoya, Aichi 460-0008, Japan\n" +
                        "   - 电话: +81 52-211-1111\r" +
                        "5. 名古屋荣大酒店 (Nagoya Tokyu Hotel)\n" +
                        "   - 地址: 4-6-8 Sakae, Naka-ku, Nagoya 460-0008, Japan\n" +
                        "   - 电话: +81 52-251-2411\r" +
                        "6. 名古屋东急酒店 (Nagoya Kanko Hotel)\n" +
                        "   - 地址: 1-19-30 Nishiki, Naka-ku, Nagoya 460-8608, Japan\n" +
                        "   - 电话: +81 52-231-7711\r" +
                        "7. 名古屋塔楼酒店 (Nagoya JR Gate Tower Hotel)\n" +
                        "   - 地址: 1-1-3 Meieki, Nakamura-ku, Nagoya, Aichi 450-6660, Japan\n" +
                        "   - 电话: +81 52-566-2111\r" +
                        "8. 名古屋蒙特利酒店 (Hotel Monterey Nagoya)\n" +
                        "   - 地址: 3-23-1 Nishiki, Naka-ku, Nagoya 460-0003, Japan\n" +
                        "   - 电话: +81 52-957-7111\r" +
                        "9. 名古屋中部国际机场酒店 (Centrair Hotel)\n" +
                        "   - 地址: 1-1 Sentorea, Tokoname, Aichi 479-0881, Japan\n" +
                        "   - 电话: +81 569-38-1111\r" +
                        "10. 名古屋皇家花园酒店 (Royal Park Hotel The Nagoya)\n" +
                        "   - 地址: 3-23-13 Nishiki, Naka-ku, Nagoya, Aichi 460-0003, Japan\n" +
                        "   - 电话: +81 52-957-2222\r"
        );
    }

    /**
     * 关西
     * @param rootBranch
     */
    private static void initBranchGuanxi(Branch rootBranch) {
        Branch guanxi = newBranch(rootBranch.getId(), "关西 (关西)");

        createBranch(guanxi.getId(), "大阪 (Osaka)",
                "1.     Osaka Castle Keep (大阪城天守閣)    \n" +
                        "2.     Tsutenkaku Tower (通天閣)    \n" +
                        "3.     Universal Studios Japan (日本环球影城)    \n" +
                        "4.     Osaka Aquarium Kaiyukan (大阪水族館海遊館)    \n" +
                        "5.     Shinsaibashi Shopping Street (心斎橋筋商店街)    \n" +
                        "6.     Dotonbori (道頓堀)    \n" +
                        "7.     Tempozan Giant Ferris Wheel (天保山大摩天輪)    \n" +
                        "8.     Abeno Harukas (阿倍野ハルカス)    \n" +
                        "9.     Shitennoji Temple (四天王寺)    \n" +
                        "10.     Hozenji Yokocho (法善寺小路)    \n" +
                        "11.     Nankai Electric Railway Koyasan (南海电铁高野山)    \n" +
                        "12.     Umeda Sky Building (梅田藍天大廈)    \n" +
                        "13.     Namba Grand Kagetsu (なんばグランド花月)    \n" +
                        "14.     Osaka Mint Bureau (大阪造幣局)    \n" +
                        "15.     Kuromon Market (黒門市場)    \n" +
                        "16.     Tenjinbashisuji Shopping Street (天神橋筋商店街)    \n" +
                        "17.     Hankyu Department Store (阪急百貨店)    \n" +
                        "18.     Daimaru Shinsaibashi Store (大丸心斎橋店)    \n" +
                        "19.     Nakanoshima Central Public Hall (中之島中央公会堂)    \n" +
                        "20.     Osaka Expo City (大阪エキスポシティ)    \n" +
                        "21.     Kitashinchi (北新地)    \n" +
                        "22.     Tenjinbashisuji Rokuchome (天神橋筋六丁目)    \n" +
                        "23.     Mitsutera Temple (三津寺)    \n" +
                        "24.     Osaka Port Tempozan Market (大阪港天保山市場)    \n" +
                        "25.     Osaka Nanko ATC (大阪南港ATC)    \n" +
                        "26.     Osaka Castle Park (大阪城公園)    \n" +
                        "27.     Umeda Underground Mall (梅田地下街)    \n" +
                        "28.     America Mura (アメリカ村)    \n" +
                        "29.     Glico Sign (グリコ看板)    \n" +
                        "30.     Nipponbashi Denki-gai (日本橋電器街)    \n" +
                        "31.     Ebisu Bridge (戎橋)    \n" +
                        "32.     Osaka City Central Public Hall (大阪市中央公会堂)    \n" +
                        "33.     Osaka International Convention Center (大阪国際会議場)    \n" +
                        "34.     Osaka Science and Technology Museum (大阪科学技術館)    \n" +
                        "35.     Osaka Nanko Sakishima (大阪南港咲洲)    \n" +
                        "36.     ATC ITM Tower (ATC ITMタワー)    \n" +
                        "37.     HEP Five Ferris Wheel (HEP Five摩天輪)    \n" +
                        "38.     Shinsekai (新世界)    \n" +
                        "39.     Kōzu Shrine (高津宮)    \n" +
                        "40.     Osaka Shochikuza Theatre (大阪松竹座)    \n" +
                        "41.     Kagetsu Theatre (花月劇場)    \n" +
                        "42.     Taiyuji Temple (太融寺)    \n" +
                        "43.     Otsuki Tenmangu (露天神社)    \n" +
                        "44.     Kitano Tenmangu (北野天満宮)    \n" +
                        "45.     Osaka Prefectural Gymnasium (大阪府立体育会館)    \n" +
                        "46.     Ashiharabashi Music Village (芦原橋（音楽村）)    \n" +
                        "47.     Tennoji Zoo (天王寺動物園)    \n" +
                        "48.     Sumiyoshi Taisha (住吉大社)    \n" +
                        "49.     Tenmangu Shrine (天満宮)    \n" +
                        "50.     Sumiyoshi Park (住吉公園)    \n" +
                        "1.     Ichiran Ramen (Dotonbori) (一蘭拉麺（道頓堀店）)    \n" +
                        "2.     Takoyaki Doraku Yamachan (たこ焼道楽 やまちゃん)    \n" +
                        "3.     Kani Doraku (Dotonbori) (かに道楽（道頓堀店）)    \n" +
                        "4.     Okonomiyaki Kiji (大阪名物お好み焼き)    \n" +
                        "5.     Matsusakagyu Yakiniku M (松阪牛焼肉)    \n" +
                        "6.     Kushikatsu Daruma (Dotonbori) (串カツだるま（道頓堀店）)    \n" +
                        "7.     Torikizoku (鳥貴族)    \n" +
                        "8.     Kinryu Ramen (金龍拉麺)    \n" +
                        "9.     Shabu-shabu Onyasa (しゃぶしゃぶ温野菜)    \n" +
                        "10.     Fugetsu (お好み焼)    \n" +
                        "11.     Hanamikouji Saryo (花見小路茶寮)    \n" +
                        "12.     Honmiyake (焼牛)    \n" +
                        "13.     Osaka Kani Doraku (大阪かに道楽)    \n" +
                        "14.     Ganso Kushikatsu Daruma (Shinsekai) (元祖串カツ だるま（新世界総店）)    \n" +
                        "15.     Nanba Kyuhorou (中華料理)    ",

                "1. 大阪 - 帝国酒店大阪 (Imperial Hotel Osaka)\n" +
                        "   - 地址: 1-8-50 Temmabashi, Kita-ku, Osaka, 530-0042, Japan\n" +
                        "   - 电话: +81 6-6881-1111\r" +
                        "2. 大阪瑞吉酒店 (The St. Regis Osaka)\n" +
                        "   - 地址: 3-6-12 Honmachi, Chuo-ku, Osaka, 541-0053, Japan\n" +
                        "   - 电话: +81 6-6258-3333\r" +
                        "3. 大阪日航酒店 (Hotel Nikko Osaka)\n" +
                        "   - 地址: 1-3-3 Nishi-shinsaibashi, Chuo-ku, Osaka 542-0086, Japan\n" +
                        "   - 电话: +81 6-6244-1111\r" +
                        "4. 大阪海港区日航酒店 (Hotel Universal Port)\n" +
                        "   - 地址: 1-1-111 Sakurajima, Konohana-ku, Osaka 554-0031, Japan\n" +
                        "   - 电话: +81 6-6463-5000\r" +
                        "5. 大阪丽思卡尔顿酒店 (The Ritz-Carlton, Osaka)\n" +
                        "   - 地址: 2-5-25 Umeda, Kita-ku, Osaka 530-0001, Japan\n" +
                        "   - 电话: +81 6-6343-7000\r" +
                        "6. 大阪希尔顿酒店 (Hilton Osaka)\n" +
                        "   - 地址: 8-8, Umeda 1-chome, Kita-ku, Osaka 530-0001, Japan\n" +
                        "   - 电话: +81 6-6347-7111\r" +
                        "7. 大阪阪急国际酒店 (Osaka Hankyu International Hotel)\n" +
                        "   - 地址: 19-19 Chayamachi, Kita-ku, Osaka 530-0013, Japan\n" +
                        "   - 电话: +81 6-6377-2100\r" +
                        "8. 大阪万豪酒店 (Osaka Marriott Miyako Hotel)\n" +
                        "   - 地址: 1-1-43 Abenosuji, Abeno-ku, Osaka 545-0052, Japan\n" +
                        "   - 电话: +81 6-6628-6111\r" +
                        "9. 大阪新世界日航酒店 (Hotel Keihan Universal Tower)\n" +
                        "   - 地址: 6-2-45 Shimaya, Konohana-ku, Osaka 554-0024, Japan\n" +
                        "   - 电话: +81 6-6465-1001\r" +
                        "10. 大阪中心皇冠假日酒店 (Crowne Plaza ANA Osaka)\n" +
                        "   - 地址: 1-3-1 Dojimahama, Kita-ku, Osaka 530-0004, Japan\n" +
                        "   - 电话: +81 6-6347-1112\r"
                );

        createBranch(guanxi.getId(), "京都 (Kyoto)",
                "1.     Kinkakuji Temple (金閣寺)    \\n\" +\n" +
                        "                        \"2.     Kiyomizudera Temple (清水寺)    \\n\" +\n" +
                        "                        \"3.     Fushimi Inari Shrine (伏見稲荷大社)    \\n\" +\n" +
                        "                        \"4.     Ginkakuji Temple (銀閣寺)    \\n\" +\n" +
                        "                        \"5.     Arashiyama (嵐山)    \\n\" +\n" +
                        "                        \"6.     Nijo Castle (二条城)    \\n\" +\n" +
                        "                        \"7.     Gion (祇園)    \\n\" +\n" +
                        "                        \"8.     Tenryuji Temple (天龍寺)    \\n\" +\n" +
                        "                        \"9.     Kyoto Imperial Palace (京都御所)    \\n\" +\n" +
                        "                        \"10.    Toji Temple (東寺)    \\n\" +\n" +
                        "                        \"11.    Sanjusangendo Temple (三十三間堂)    \\n\" +\n" +
                        "                        \"12.    Nanzenji Temple (南禅寺)    \\n\" +\n" +
                        "                        \"13.    Ryoanji Temple (龍安寺)    \\n\" +\n" +
                        "                        \"14.    Shimogamo Shrine (下鴨神社)    \\n\" +\n" +
                        "                        \"15.    Heian Shrine (平安神宮)    \\n\" +\n" +
                        "                        \"16.    Kyoto Tower (京都タワー)    \\n\" +\n" +
                        "                        \"17.    Philosopher’s Walk (哲学の道)    \\n\" +\n" +
                        "                        \"18.    Yasaka Shrine (八坂神社)    \\n\" +\n" +
                        "                        \"19.    Daitokuji Temple (大徳寺)    \\n\" +\n" +
                        "                        \"20.    Kuramadera Temple (鞍馬寺)    \\n\" +\n" +
                        "                        \"1.     Sagano Scenic Railway (嵯峨野トロッコ列車)    \\n\" +\n" +
                        "                        \"2.     Kyoto International Manga Museum (京都国際マンガミュージアム)    \\n\" +\n" +
                        "                        \"3.     Kyoto Aquarium (京都水族館)    \\n\" +\n" +
                        "                        \"4.     Kyoto Railway Museum (京都鉄道博物館)    \\n\" +\n" +
                        "                        \"5.     Sagano (嵯峨野)    \\n\" +\n" +
                        "                        \"6.     Kamigamo Shrine (上賀茂神社)    \\n\" +\n" +
                        "                        \"7.     Nishiki Market (錦市場)    \\n\" +\n" +
                        "                        \"8.     Pontocho (先斗町)    \\n\" +\n" +
                        "                        \"9.     Isetan Kyoto (伊勢丹京都店)    \\n\" +\n" +
                        "                        \"10.   Kyoto Station Building (京都駅ビル)    ",
                "1. 京都丽思卡尔顿酒店 (The Ritz-Carlton, Kyoto)\n" +
                        "   - 地址: Kamogawa Nijo-Ohashi Hotori, Nakagyo-ku, Kyoto, 604-0902, Japan\n" +
                        "   - 电话: +81 75-746-5555\r" +
                        "2. 京都四季酒店 (Four Seasons Hotel Kyoto)\n" +
                        "   - 地址: 445-3 Myohoin Maekawa-cho, Higashiyama-ku, Kyoto 605-0932, Japan\n" +
                        "   - 电话: +81 75-541-8288\r" +
                        "3. 京都皇家酒店 (The Royal Park Hotel Kyoto)\n" +
                        "   - 地址: 745 Sanjocho, Sanjo-kudaru, Kawaramachi-dori, Nakagyo-ku, Kyoto 604-8004, Japan\n" +
                        "   - 电话: +81 75-241-1111\r" +
                        "4. 京都宾馆 (Kyoto Hotel Okura)\n" +
                        "   - 地址: 604-8558 Kyoto, Nakagyo-ku, Kawaramachi-Oike, Japan\n" +
                        "   - 电话: +81 75-211-5111\r" +
                        "5. 京都世纪酒店 (Kyoto Century Hotel)\n" +
                        "   - 地址: 680 Higashishiokoji-cho, Shimogyo-ku, Kyoto 600-8216, Japan\n" +
                        "   - 电话: +81 75-351-0111\r" +
                        "6. 京都格兰德酒店 (Hyatt Regency Kyoto)\n" +
                        "   - 地址: 644-2 Sanjusangendo-mawari, Higashiyama-ku, Kyoto 605-0941, Japan\n" +
                        "   - 电话: +81 75-541-1234\r" +
                        "7. 京都丽思酒店 (Rihga Royal Hotel Kyoto)\n" +
                        "   - 地址: 1 Taimatsu-cho, Shiokoji-sagaru, Higashi Horikawa-dori, Shimogyo-ku, Kyoto 600-8237, Japan\n" +
                        "   - 电话: +81 75-341-1121\r" +
                        "8. 京都高台寺优美娜酒店 (Hotel Gracery Kyoto Sanjo)\n" +
                        "   - 地址: 406 Sakuranocho, Shinkyogoku, Higashiiru, Shijo-dori, Nakagyo-ku, Kyoto 604-8035, Japan\n" +
                        "   - 电话: +81 75-222-1111\r" +
                        "9. 京都樱花酒店 (Hotel Sakura Terrace)\n" +
                        "   - 地址: 1-1 Higashikujo Karasumacho, Minami-ku, Kyoto 601-8016, Japan\n" +
                        "   - 电话: +81 75-692-1112\r" +
                        "10. 京都瑞光酒店 (Kyoto Brighton Hotel)\n" +
                        "   - 地址: 602-8071 Kyoto, Kamigyo-ku Shinmachi-Dori Nakadachiuri, Japan\n" +
                        "   - 电话: +81 75-441-4411\r"
                );
        createBranch(guanxi.getId(), "神户 (Kobe)",
                "1.     Kobe Port Tower (神戸ポートタワー)    \n" +
                        "2.     Meriken Park (メリケンパーク)    \n" +
                        "3.     Mount Rokko (六甲山)    \n" +
                        "4.     Nankinmachi (南京町)    \n" +
                        "5.     Kitano Ijinkan-gai (北野異人館街)    \n" +
                        "6.     Arima Onsen (有馬温泉)    \n" +
                        "7.     Kobe Animal Kingdom (神戸どうぶつ王国)    \n" +
                        "8.     Ikuta Shrine (生田神社)    \n" +
                        "9.     Mount Maya (摩耶山)    \n" +
                        "10.    Kobe Harborland (神戸ハーバーランド)    \n" +
                        "11.    Suma Aqualife Park (須磨海浜水族園)    \n" +
                        "12.    Kobe Anpanman Children's Museum & Mall (神戸アンパンマンこどもミュージアム＆モール)    \n" +
                        "13.    Hyogo Prefectural Museum of Art (兵庫県立美術館)    \n" +
                        "14.    Sake Brewery Tours (酒蔵めぐり)    \n" +
                        "15.    Sumadera Temple (須磨寺)    \n" +
                        "16.    Higashi Yuenchi Park (東遊園地)    \n" +
                        "17.    Kobe Municipal Arboretum (神戸市立森林植物園)    \n" +
                        "18.    Maiko Park (舞子公園)    \n" +
                        "19.    Daimaru Kobe (大丸神戸店)    \n" +
                        "1.     Suma Rikyu Park (神戸市立須磨離宮公園)    \n" +
                        "2.     Oji Zoo (王子動物園)    \n" +
                        "3.     KOBE KITANO HOTEL    \n" +
                        "4.     Kobe Nunobiki Herb Garden (神戸布引ハーブ園)    \n" +
                        "5.     Fruits Flower Park (フルーツ・フラワーパーク)    \n" +
                        "6.     KOBE Lamp Museum (KOBEらんぷミュージアム)    \n" +
                        "7.     Mitsui Outlet Park Marine Pia Kobe (三井アウトレットパーク マリンピア神戸)    \n" +
                        "8.     Kobe Harborland umie (神戸ハーバーランドumie)    \n" +
                        "9.     Kobe Motomachi Shopping Street (神戸元町商店街)    \n" +
                        "10.   Sannomiya Center Street (三宮センター街)    ",
                "1. 神户海港酒店 (Kobe Portopia Hotel)\n" +
                        "   - 地址: 10-1 Minatojima Nakamachi, Chuo-ku, Kobe, Hyogo 650-0046, Japan\n" +
                        "   - 电话: +81 78-302-1111\r" +
                        "2. 神户东方酒店 (Hotel Okura Kobe)\n" +
                        "   - 地址: 2-1 Hatobacho, Chuo-ku, Kobe, Hyogo 650-8560, Japan\n" +
                        "   - 电话: +81 78-333-0111\r" +
                        "3. 神户皇冠广场酒店 (Crowne Plaza ANA Kobe)\n" +
                        "   - 地址: 1-1 Kitano-cho, Chuo-ku, Kobe 650-0002, Japan\n" +
                        "   - 电话: +81 78-291-1121\r" +
                        "4. 神户梅尔帕洛斯酒店 (Hotel Monterey Kobe)\n" +
                        "   - 地址: 2-11-13 Shimoyamate-dori, Chuo-ku, Kobe, Hyogo 650-0021, Japan\n" +
                        "   - 电话: +81 78-392-7111\r" +
                        "5. 神户东方酒店 (Oriental Hotel Kobe)\n" +
                        "   - 地址: 25 Kyomachi, Chuo-ku, Kobe 650-0034, Japan\n" +
                        "   - 电话: +81 78-326-1500\r" +
                        "6. 神户格兰德酒店 (Kobe Meriken Park Oriental Hotel)\n" +
                        "   - 地址: 5-6 Hatoba-cho, Chuo-ku, Kobe, Hyogo 650-0042, Japan\n" +
                        "   - 电话: +81 78-325-8111\r" +
                        "7. 神户日航酒店 (Kobe Bay Sheraton Hotel & Towers)\n" +
                        "   - 地址: 2-13 Koyocho-naka, Higashinada-ku, Kobe 658-0032, Japan\n" +
                        "   - 电话: +81 78-857-7000\r" +
                        "8. 神户阪急酒店 (Hotel Sunroute Sopra Kobe)\n" +
                        "   - 地址: 1-1-22 Isobe-dori, Chuo-ku, Kobe 651-0084, Japan\n" +
                        "   - 电话: +81 78-222-7500\r" +
                        "9. 神户湾花园酒店 (Kobe Bay Sheraton Hotel & Towers)\n" +
                        "   - 地址: 2-13 Koyocho-naka, Higashinada-ku, Kobe 658-0032, Japan\n" +
                        "   - 电话: +81 78-857-7000\r" +
                        "10. 神户北野酒店 (Kobe Kitano Hotel)\n" +
                        "   - 地址: 3-3-20 Yamamoto-dori, Chuo-ku, Kobe 650-0003, Japan\n" +
                        "   - 电话: +81 78-271-3711\r"
        );
    }

    private static void initBranchTokyo(Branch rootBranch) {
        createBranch(rootBranch.getId(),
                "东京 (Tokyo)",

                "1.   Tokyo Station (東京駅)  \n" +
                        "2.   Ginza (銀座)  \n" +
                        "3.   Odaiba (お台場)  \n" +
                        "4.   Shibuya (渋谷)  \n" +
                        "5.   Harajuku (原宿)  \n" +
                        "6.   Omotesando (表参道)  \n" +
                        "7.   Ebisu (恵比寿)  \n" +
                        "8.   Daikanyama (代官山)  \n" +
                        "9.   Roppongi (六本木)  \n" +
                        "10.   Shinjuku (新宿)  \n" +
                        "11.   Ueno (上野)  \n" +
                        "12.   Akihabara (秋葉原)  \n" +
                        "13.   Asakusa (浅草)  \n" +
                        "14.   Tokyo Skytree (東京スカイツリー)  \n" +
                        "15.   Yokohama (横浜)  \n" +
                        "16.   Kamakura (鎌倉)  \n" +
                        "17.   Hakone (箱根)  \n" +
                        "18.   Gransta Marunouchi (グランスタ丸の内)  \n" +
                        "19.   Tokyo Station First Avenue (東京駅一番街)  \n" +
                        "20.   Tokyo Imperial Palace (皇居)  \n" +
                        "21.   The East Gardens of the Imperial Palace (皇居東御苑)  \n" +
                        "22.   Marunouchi Naka Street (丸の内仲通り)  \n" +
                        "23.   Mitsubishi Ichigokan Museum (三菱一号館美術館)  \n" +
                        "24.   JP Tower Gitte (JPタワー·ギッテ)  \n" +
                        "25.   Ginza Mitsukoshi (銀座三越)  \n" +
                        "26.   Matsuya Ginza (松屋銀座)  \n" +
                        "27.   Ginza Wako (銀座和光)  \n" +
                        "28.   Kirarito Ginza (きらりと銀座)  \n" +
                        "29.   Itoya (伊東屋)  \n" +
                        "30.   Ginza Loft (銀座ロフト)  \n" +
                        "31.   Marronnier Gate Ginza (マロニエゲート銀座)  \n" +
                        "32.   Apple Store Ginza (アップルストア銀座)  \n" +
                        "33.   Shiseido Ginza (資生堂銀座)  \n" +
                        "34.   Ginza Place (銀座プレイス)  \n" +
                        "35.   Ginza Six (銀座シックス)  \n" +
                        "36.   Bunkayokocho (文化横丁)  \n" +
                        "37.   Hankyu Men's Tokyo (阪急メンズ東京)  \n" +
                        "38.   Ginza Kikusui (銀座菊水)  \n" +
                        "39.   Kabukiza (歌舞伎座)  \n" +
                        "40.   Fuji TV (フジテレビ)  \n" +
                        "41.   Odaiba Marine Park (お台場海浜公園)  \n" +
                        "42.   Diver City Tokyo Plaza (ダイバーシティ東京プラザ)  \n" +
                        "43.   Aqua City Odaiba (アクアシティお台場)  \n" +
                        "44.   Decks Tokyo Beach (デックス東京ビーチ)  \n" +
                        "45.   Shibuya 109 (渋谷109)  \n" +
                        "46.   Shibuya Center Gai (渋谷センター街)  \n" +
                        "47.   SPAIN ZAKA (スペイン坂)  \n" +
                        "48.   Shibuya Hikarie (渋谷ヒカリエ)  \n" +
                        "49.   Meiji Shrine (明治神宮)  \n" +
                        "50.   Takeshita Street (竹下通り)  \n" +
                        "51.   Omotesando Hills (表参道ヒルズ)  \n" +
                        "52.   Yebisu Garden Place (恵比寿ガーデンプレイス)  \n" +
                        "53.   Yebisu Beer Memorial Hall (エビスビール記念館)  \n" +
                        "54.   Tokyo Photographic Art Museum (東京都写真美術館)  \n" +
                        "55.   Ebisu Yokocho (恵比寿横丁)  \n" +
                        "56.   Kyū-Asakurake House (旧朝倉家住宅)  \n" +
                        "57.   Hillside Terrace (ヒルサイドテラス)  \n" +
                        "58.   Daikanyama Tea Site (代官山ティサイト)  \n" +
                        "59.   Log Road Daikanyama (ログロード代官山)  \n" +
                        "60.   Mori Art Museum (森美術館)  \n" +
                        "61.   Tokyo City View Observation Deck (東京シティビュー)  \n" +
                        "62.   West Walk (ウエストウォーク)  \n" +
                        "63.   Roppongi Hills Arena (六本木ヒルズアリーナ)  \n" +
                        "64.   Mori Garden (森庭園)  \n" +
                        "65.   TV Asahi (テレビ朝日)  \n" +
                        "66.   Keyakizaka (けやき坂)  \n" +
                        "67.   The National Art Center Tokyo (国立新美術館)  \n" +
                        "68.   Tokyo Midtown (東京ミッドタウン)  \n" +
                        "69.   21_21 DESIGN SIGHT (21_21 デザインサイト)  \n" +
                        "70.   Suntory Art Museum (サントリー美術館)  \n" +
                        "71.   Snoopy Museum (スヌーピーミュージアム)  \n" +
                        "72.   Tokyo Tower (東京タワー)  \n" +
                        "73.   Shibakoen (芝公園)  \n" +
                        "74.   Starbucks Coffee Shiba Daimon Branch (スターバックスコーヒー芝大門店)  \n" +
                        "75.   Shinjuku Gyoen (新宿御苑)  \n" +
                        "76.   Takashimaya Times Square (高島屋タイムズスクエア)  \n" +
                        "77.   Newoman (ニュウマン)  \n" +
                        "78.   Tokyo Metropolitan Government Building (東京都庁)  \n" +
                        "79.   Isetan (伊勢丹)  \n" +
                        "80.   Kinokuniya (紀伊國屋)  \n" +
                        "81.   Kabukicho (歌舞伎町)  \n" +
                        "82.   Shinjuku Golden Gai (新宿ゴールデン街)  \n" +
                        "83.   Shin-Okubo Koreatown (新大久保コリアタウン)  \n" +
                        "84.   Harmonica Yokocho (ハーモニカ横丁)  \n" +
                        "85.   Mitaka Forest Ghibli Museum (三鷹の森ジブリ美術館)  \n" +
                        "86.   Ueno Park (上野公園)  \n" +
                        "87.   Ueno Zoo (上野動物園)  \n" +
                        "88.   Tokyo Metropolitan Art Museum (東京都美術館)  \n" +
                        "89.   Tokyo University of the Arts Red Brick Buildings 1 and 2 (東京芸術大学赤レンガ1・2号館)  \n" +
                        "90.   International Children's Library (国際こども図書館)  \n" +
                        "91.   Tokyo National Museum (東京国立博物館)  \n" +
                        "92.   National Museum of Western Art (国立西洋美術館)  \n" +
                        "93.   National Science Museum (国立科学博物館)  \n" +
                        "94.   Ameyayokocho (アメ横)  \n" +
                        "95.   Nike Goyonmar Akioka Artisan (ニケゴヨンマル秋岡アルチザン)  \n" +
                        "96.   Chabara Akioka Marche (チャバラ秋葉原マルシェ)  \n" +
                        "97.   Akihabara Radio Hall (秋葉原ラジオ会館)  \n" +
                        "98.   Yodobashi Akiba (淀橋アキバ)  \n" +
                        "99.   Akihabara UDX (秋葉原UDX)  \n" +
                        "100.   Maach ecute Kanda Manseibashi (マーチエキュート神田万世橋)  \n" +
                        "101.   Sensoji Temple (浅草寺)  \n" +
                        "102.   Nakamise Dori (仲見世通り)  \n" +
                        "103.   Marugoto Nippon (まるごと日本)  \n" +
                        "104.   Asakusa Hoppy Street (浅草ホッピー通り)  \n" +
                        "105.   Kappabashi Dougu Street (かっぱ橋道具通り)  \n" +
                        "106.   Tokyo Skytree Town (東京スカイツリータウン)  \n" +
                        "107.   Harbor View Park (港の見える丘公園)  \n" +
                        "108.   Motomachi Street (元町ストリート)  \n" +
                        "109.   Motomachi Nakadori (元町中通り)  \n" +
                        "110.   Yokohama Chukagai (横浜中華街)  \n" +
                        "111.   Yamashita Park (山下公園)  \n" +
                        "112.   Akarenga Soko (赤レンガ倉庫)  \n" +
                        "113.   Minato Mirai 21 (みなとみらい21)  \n" +
                        "114.   Engakuji (円覚寺)  \n" +
                        "115.   Youshomei Art Museum (養松院美術館)  \n" +
                        "116.   Yuigahama (由比ヶ浜)  \n" +
                        "117.   Kamakura Great Buddha (鎌倉大仏)  \n" +
                        "118.   Gokurakuji (極楽寺)  \n" +
                        "119.   Shichirigahama (七里ヶ浜)  \n" +
                        "120.   Enoshima (江の島)  \n" +
                        "121.   Hakone Yumoto (箱根湯本)  \n" +
                        "122.   Hakone Open-Air Museum (箱根彫刻の森美術館)  \n" +
                        "123.   Gora Park (強羅公園)  \n" +
                        "124.   Owakudani (大涌谷)  \n" +
                        "125.   Ashinoko (芦ノ湖)  \n" +
                        "126.   Motohakone (元箱根)  \n",

                "1. 帝国酒店东京 (Imperial Hotel Tokyo)\n" +
                        "   - 地址: 1-1-1 Uchisaiwai-cho, Chiyoda-ku, Tokyo, 100-8558, Japan\n" +
                        "   - 电话: +81 3-3504-1111\r" +
                        "2. 旭日光之塔大酒店 (The Gate Hotel Asakusa Kaminarimon by Hulic)\n" +
                        "   - 地址: 2-16-11 Kaminarimon, Taito, Tokyo 111-0034, Japan\n" +
                        "   - 电话: +81 3-5826-3877\r" +
                        "3. 东京大仓酒店 (The Okura Tokyo)\n" +
                        "   - 地址: 2-10-4 Toranomon, Minato City, Tokyo 105-0001, Japan\n" +
                        "   - 电话: +81 3-3582-0111\r" +
                        "4. 丸之内大酒店 (Palace Hotel Tokyo)\n" +
                        "   - 地址: 1-1-1 Marunouchi, Chiyoda-ku, Tokyo 100-0005, Japan\n" +
                        "   - 电话: +81 3-3211-5211\r" +
                        "5. 东京皇家花园酒店 (The Royal Park Hotel Tokyo Shiodome)\n" +
                        "   - 地址: 1-6-3 Higashi-Shimbashi, Minato-ku, Tokyo 105-8333, Japan\n" +
                        "   - 电话: +81 3-6253-1111\r" +
                        "6. 东京湾洲际酒店 (InterContinental Tokyo Bay)\n" +
                        "   - 地址: 1-16-2 Kaigan, Minato-ku, Tokyo 105-8576, Japan\n" +
                        "   - 电话: +81 3-5404-2222\r" +
                        "7. 东京湾日航酒店 (Hilton Tokyo Odaiba)\n" +
                        "   - 地址: 1-9-1 Daiba, Minato-ku, Tokyo 135-8625, Japan\n" +
                        "   - 电话: +81 3-5500-5500\r" +
                        "8. 东京车站酒店 (The Tokyo Station Hotel)\n" +
                        "   - 地址: 1-9-1 Marunouchi, Chiyoda-ku, Tokyo 100-0005, Japan\n" +
                        "   - 电话: +81 3-5220-1111\r" +
                        "9. 东京安达大酒店 (Hotel New Otani Tokyo Garden Tower)\n" +
                        "   - 地址: 4-1 Kioi-cho, Chiyoda-ku, Tokyo 102-8578, Japan\n" +
                        "   - 电话: +81 3-3265-1111\r" +
                        "10. 丸之内酒店 (Marunouchi Hotel)\n" +
                        "   - 地址: 1-6-3 Marunouchi, Chiyoda-ku, Tokyo 100-0005, Japan\n" +
                        "   - 电话: +81 3-3217-1111\r"
        );
    }

    private static void clearData() {
        CacheData.getHotelMap().clear();
        CacheData.getBranchMap().clear();
        CacheData.getTouristSpotMap().clear();
    }

    /**
     * 解析景点列表
     * @param sportData
     * @return
     */
    public static Set<String> newTouristSpotSet(String sportData) {
        List<String> sportStrList = Arrays.stream(sportData.split("\n")).map(StrUtil::trim).filter(StrUtil::isNotBlank).collect(Collectors.toList());
        List<TouristSpot> list = new ArrayList<>();

        // 定义正则表达式模式
        String pattern = "(\\d+)\\.\\s+(.*) \\((.*)\\)";
        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);

        for (String sportStr : sportStrList) {
            // 创建 Matcher 对象
            Matcher m = r.matcher(sportStr);
            if (m.find()) {
                String number = String.valueOf(CacheData.getTouristSpotMap().size());
                String englishName = m.group(2).trim();
                String japaneseName = m.group(3).trim();
                System.out.println("景点编号: " + number);
                System.out.println("景点英文名: " + englishName);
                System.out.println("景点日文名: " + japaneseName);
                TouristSpot sport = new TouristSpot();
                sport.setEnglishName(englishName);
                sport.setJapaneseName(japaneseName);
                sport.setKoreanName(StrUtil.EMPTY);
                sport.setId(number);
                list.add(sport);
                CacheData.getTouristSpotMap().put(number, sport);
            }
        }
        return list.stream().map(TouristSpot::getId).collect(Collectors.toSet());
    }

    /**
     * 解析 "中文名 (英文名)"
     * @param data
     * @return
     */
    public static Branch newBranch(String data) {
        Branch branch = new Branch();
        // 定义正则表达式模式
        String pattern = "(.*) \\((.*)\\)";
        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);
        // 创建 Matcher 对象
        Matcher m = r.matcher(data);
        if (m.find()) {
            String chineseName = m.group(1).trim();
            String englishName = m.group(2).trim();
            System.out.println("机构中文名: " + chineseName);
            System.out.println("机构英文名: " + englishName);
            branch.setZhName(chineseName);
            branch.setEnName(englishName);
        }
        branch.setId(String.valueOf(CacheData.getBranchMap().size()));
        CacheData.getBranchMap().put(branch.getId(), branch);
        return branch;
    }
    
    public static Branch newBranch(String pid, String data) {
        Branch branch = newBranch(data);
        branch.setParentId(pid);
        CacheData.getBranchMap().get(pid).getChildren().add(branch.getId());
        return branch;
    }

    /**
     *
     * @param branchData
     * @param sportData
     * @param hotelData
     */
    private static void createBranch(String parentId, String branchData, String sportData, String hotelData) {
        Branch branch = newBranch(branchData);
        branch.setTouristSpots(newTouristSpotSet(sportData));
        branch.setHotels(newHotelSet(hotelData));
        branch.setParentId(parentId);
        CacheData.getBranchMap().get(parentId).getChildren().add(branch.getId());
    }

    /**
     * 解析酒店列表数据
     * @param hotelData
     * @return
     */
    private static Set<String> newHotelSet(String hotelData) {
        List<String> splitList = Arrays.stream(hotelData.split("\r")).map(String::trim).filter(StrUtil::isNotEmpty).collect(Collectors.toList());
        List<Hotel> list = new ArrayList<>();

        // 定义正则表达式模式
        String pattern = "(\\d+)\\.\\s+(.*) \\((.*)\\)\n.* - 地址: (.*)\n.* - 电话: (.*)";
        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);

        for (String s : splitList) {

            // 创建 Matcher 对象
            Matcher m = r.matcher(s);
            if (m.find()) {
                String number = String.valueOf(CacheData.getHotelMap().size());
                String chineseName = m.group(2).trim();
                String englishName = m.group(3).trim();
                String address = m.group(4).trim().replaceAll("\\n|\\(|\\)", "");
                String phone = m.group(5).trim().replaceAll("\\n|\\(|\\)", "");
                System.out.println("酒店编号: " + number);
                System.out.println("酒店名: " + chineseName);
                System.out.println("酒店名: " + englishName);
                System.out.println("酒店地址: " + address);
                System.out.println("酒店电话: " + phone);
                Hotel hotel = new Hotel();
                hotel.setHotelId(number);
                hotel.setAddress(address);
                hotel.setEnName(englishName);
                hotel.setZhName(chineseName);
                hotel.setPhone(phone);
                list.add(hotel);
                CacheData.getHotelMap().put(number, hotel);
            }

        }

        return list.stream().map(Hotel::getHotelId).collect(Collectors.toSet());
    }
}
