package sample.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
public class TouristSpot {
    private int id;
    private String koreanName;
    private String japaneseName;
    private String englishName;

    public TouristSpot(int id, String koreanName, String japaneseName, String englishName) {
        this.id = id;
        this.koreanName = koreanName;
        this.japaneseName = japaneseName;
        this.englishName = englishName;
    }

    public static Map<Integer, TouristSpot> TOURIST_SPOT_MAP;

    public static String TXT = "도쿄 역 & 긴자 & 오다이바\n" +
            "とうきょうえき & ぎんざ & おだいば\n" +
            "Tokyo Station & Ginza & Odaiba\n" +
            "\t" +
            "시부야 & 하라주쿠 & 오모테산도\n" +
            "しぶや  & はらじゅく & おもてさんどう\n" +
            "Shibuya & Harajuku & Omotesando\n" +
            "\t" +
            "에비스 & 다이칸야마 & 롯폰기\n" +
            "えびす & だいかんやま & ろっぽんぎ \n" +
            "Ebisu & Daikanyama & Roppongi\n" +
            "\t" +
            "신주쿠\n" +
            "しんじゅく\n" +
            "Shinjuku\n" +
            "\t" +
            "우에노 & 아키하바라\n" +
            "うえの  & あきはばら\n" +
            " Ueno & Akihabara\n" +
            "\t" +
            "아사쿠사 & 도쿄스카이트리\n" +
            "あさくさ  & とうきょうスカイツリー \n" +
            "Asakusa & Tokyo Skytree\n" +
            "\t" +
            "요코하마\n" +
            "よこはま \n" +
            "Yokohama\n" +
            "\t" +
            "가마쿠라\n" +
            "かまくら \n" +
            "Kamakura\n" +
            "\t" +
            "하코네\n" +
            "はこね\n" +
            "Hakone\n" +
            "\t" +
            "그랑스타 마루노우치\n" +
            "グランスタ丸の内\n" +
            "Gransta Marunouchi\n" +
            "\t" +
            "도쿄역일번가\n" +
            "東京駅一番街\n" +
            "Tokyo Station First Avenue\n" +
            "\t" +
            "고쿄\n" +
            "ごきょう\n" +
            "고쿄\n" +
            "\t" +
            "고쿄 히가시교엔\n" +
            "皇居東御苑\n" +
            "The East Gardens of the Imperial Palace \n" +
            "\t" +
            "마루노우치 나카도리\n" +
            "丸の内仲通り\n" +
            "Marunouchi Naka Street\n" +
            "\t" +
            "미쓰비시 1호관 미술관\n" +
            "三菱一号館美術館\n" +
            "Mitsubishi Ichigokan Museum\n" +
            "\t" +
            "JP타워 깃테\n" +
            "JPタワー·ギッテ\n" +
            "JP Tower Gitte\n" +
            "\t" +
            "긴자 미츠코시\n" +
            "座三越\n" +
            "ginja micheukosi\n" +
            "\t" +
            "마츠야 긴자\n" +
            "松屋銀座\n" +
            "Matsuya Ginza\n" +
            "\t" +
            "긴자 와코\n" +
            "銀座和子\n" +
            "Ginza Kazuko\n" +
            "\t" +
            "키라리토 긴자\n" +
            "きらりと銀座\n" +
            "Kirarito Ginza\n" +
            "\t" +
            "이토야\n" +
            "いとうや\n" +
            "Itoya\n" +
            "\t" +
            "긴자 로프트\n" +
            "銀座ロフト\n" +
            "Ginza Loft\n" +
            "\t" +
            "마로니에 게이트 긴자\n" +
            "マロニエゲート銀座\n" +
            "Marronnier Gate Ginza\n" +
            "\t" +
            "도큐플라자 긴자\n" +
            "東急プラザ銀座\n" +
            "\t" +
            "애플스토어 긴자\n" +
            "アップルストア銀座\n" +
            "\t" +
            "시세이도 더 긴자\n" +
            "生堂 もっと 銀座\n" +
            "\t" +
            "긴자플레이스\n" +
            "銀座プレイス\n" +
            "Tokyu Plaza Ginza\n" +
            "\t" +
            "긴자 식스\n" +
            "銀座シックス\n" +
            "Ginza Six\n" +
            "\t" +
            "분카요코초\n" +
            "文化横丁\n" +
            "Bunkayokocho\n" +
            "\t" +
            "한큐 멘즈 도쿄\n" +
            "阪急 メンズ 東京\n" +
            "Hankyu Men's Tokyo\n" +
            "\t" +
            "긴자 키쿠스이\n" +
            "銀座菊水\n" +
            "Ginza Kikusui\n" +
            "\t" +
            "가부키자\n" +
            "かぶきざ\n" +
            "Kabukiza\n" +
            "\t" +
            "후지테레비\n" +
            "フジテレビ\n" +
            "Fuji TV\n" +
            "\t" +
            "오다이바 해변공원\n" +
            "お台場海浜公園\n" +
            "Odaiba Seaside Park\n" +
            "\t" +
            "다이바시티 도쿄 프라자\n" +
            "台場シティ東京プラザ\n" +
            "Diver City Tokyo Plaza\n" +
            "\t" +
            "아쿠아시티 오다이바\n" +
            "アクアシティお台場\n" +
            "Aqua City Odaiba\n" +
            "\t" +
            "덱스 도쿄 비치\n" +
            "デックス 東京ビーチ\n" +
            "Decks Tokyo Beach\n" +
            "\t" +
            "시부야 109\n" +
            "渋谷109\n" +
            "Shibuya 109\n" +
            "\t" +
            "시부야 센터가이\n" +
            "渋谷センターガイ\n" +
            "Shibuya Center Gai\n" +
            "\t" +
            "스페인자카\n" +
            "スペイン坂\n" +
            "Spanishzaka\n" +
            "\t" +
            "시부야 히카리에\n" +
            "渋谷ヒカリエ\n" +
            "Shibuya Hikarie\n" +
            "\t" +
            "메이지신궁\n" +
            "めいじじんぐう\n" +
            "Meiji Jingu Shrine\n" +
            "\t" +
            "다케시타도리\n" +
            "たけしたどおり\n" +
            "Takeshita Street\n" +
            "\t" +
            "오모테산도힐즈\n" +
            "表参道ヒルズ\n" +
            "Omotesando Hills\n" +
            "\t" +
            "에비스 가든 플레이스\n" +
            "恵比寿ガーデンプレイス\n" +
            "Yebisu Garden Place\n" +
            "\t" +
            "에비스 맥주 기념관\n" +
            "めぐみひじゅばくさけきねんかん\n" +
            "Yebisu Beer Memorial Hall\n" +
            "\t" +
            "도쿄도사진미술관\n" +
            "とうきょうとしゃしんびじゅつかん\n" +
            "Tokyo Photographic Art Museum\n" +
            "\t" +
            "에비스요코쵸\n" +
            "恵比寿横丁\n" +
            "Ebisu Yokocho\n" +
            "\t" +
            "구 아사쿠라케 주택\n" +
            "旧朝倉家住宅\n" +
            "Former Asakurak Residence\n" +
            "\t" +
            "힐사이드테라스\n" +
            "ヒルサイドテラス\n" +
            "Hillside Terrace\n" +
            "\t" +
            "다이칸야마 티사이트\n" +
            "代官山ティサイト\n" +
            "Daikanyama Tea Site\n" +
            "\t" +
            "로그로드 다이칸야마\n" +
            "ログロード代官山\n" +
            "Log Road Daikanyama\n" +
            "\t" +
            "모리미술관\n" +
            "もりびじゅつかん\n" +
            "Mori Art Museum\n" +
            "\t" +
            "전망대 도쿄시티뷰\n" +
            "展望台 東京シティビュー\n" +
            "Tokyo City View Observation Deck \n" +
            "\t" +
            "웨스트워크\n" +
            "ウェストワーク\n" +
            "West Walk\n" +
            "\t" +
            "롯폰기힐즈 아레나\n" +
            "六本木ヒルズアリーナ\n" +
            "Roppongi Hills Arena\n" +
            "\t" +
            "모리정원\n" +
            "森庭園\n" +
            "Mori Garden\n" +
            "\t" +
            "테레비 아사히\n" +
            "テレビあさひ\n" +
            "TV Asahi\n" +
            "\t" +
            "케야키자카\n" +
            "けやきざか\n" +
            "Keyakizaka\n" +
            "\t" +
            "국립신미술관\n" +
            "こくりゅうしんびじゅつかん\n" +
            "National Museum of Fine Arts\n" +
            "\t" +
            "도쿄 미드타운\n" +
            "とうきょうミッドタウン \n" +
            "Tokyo Midtown\n" +
            "\t" +
            "21_21 디자인사이트\n" +
            "21_21 デザインサイト\n" +
            "21_21 Design Site\n" +
            "\t" +
            "산토리 미술관\n" +
            "サントリーびじゅつかん\n" +
            "Suntory Art Museum\n" +
            "\t" +
            "스누피 뮤지엄\n" +
            "スヌーピーミュージアム\n" +
            "Snoopy Museum\n" +
            "\t" +
            "도쿄타워\n" +
            "とうきょうタワー\n" +
            "Tokyo Tower\n" +
            "\t" +
            "시바 공원\n" +
            "芝公園\n" +
            "shiva park\n" +
            "\t" +
            "죠죠지\n" +
            "定常寺\n" +
            "Jojoji\n" +
            "\t" +
            "스타벅스커피 시바다이몬점\n" +
            "スターバックスコーヒー芝大門店\n" +
            "Starbucks Coffee Shiba Daimon Branch\n" +
            "\t" +
            "신주쿠 교엔\n" +
            "しんじゅくぎょえん\n" +
            "Shinjuku Gyoen\n" +
            "\t" +
            "\t" +
            "다카시마야 타임즈스퀘어\n" +
            "高島屋タイムズスクエア\n" +
            "Takashimaya Times Square\n" +
            "\t" +
            "뉴우먼\n" +
            "ニューウーマン\n" +
            "new woman\n" +
            "\t" +
            "도쿄 도청\n" +
            "東京都庁\n" +
            "Tokyo Metropolitan Government Building\n" +
            "\t" +
            "이세탄\n" +
            "いせたん\n" +
            "Isetan\n" +
            "\t" +
            "키노쿠니야\n" +
            "紀伊國屋\n" +
            "Kinokuniya\n" +
            "\t" +
            "가부키초\n" +
            "かぶきちょう\n" +
            "Kabukicho\n" +
            "\t" +
            "신주쿠 골든가이\n" +
            "新宿ゴールデンガイ\n" +
            "Shinjuku Golden Gai\n" +
            "\t" +
            "신오쿠보 코리아타운\n" +
            "新大久保コリアタウン\n" +
            "Shin-Okubo Koreatown\n" +
            "\t" +
            "하모니카요코초\n" +
            "ハーモニカ横丁\n" +
            "Harmonica Yokocho\n" +
            "\t" +
            "미타카의 숲 지브리 미술관\n" +
            "鷹の森ジブリ美術館\n" +
            "Mitaka Forest Ghibli Museum\n" +
            "\t" +
            "우에노 공원\n" +
            "うえのこうえん\n" +
            "Ueno Park\n" +
            "\t" +
            "우에노 동물원\n" +
            "上野動物園\n" +
            "Ueno Zoo\n" +
            "\t" +
            "도쿄도미술관\n" +
            "とうきょうとびじゅつかん\n" +
            "Tokyo Metropolitan Art Museum\n" +
            "\t" +
            "도쿄예술대학 붉은 벽돌 1 · 2호관\n" +
            "東京芸術大学赤レンガ1·2号館\n" +
            "Tokyo University of the Arts Red Brick Buildings 1 and 2\n" +
            "\t" +
            "국제어린이도서관\n" +
            "こくさいこどもとしょかん \n" +
            "International Children's Library\n" +
            "\t" +
            "도쿄국립박물관\n" +
            "とうきょうくにりつはくぶつかん\n" +
            "Tokyo National Museum\n" +
            "\t" +
            "국립서양미술관\n" +
            "こくりつせいようびじゅつかん\n" +
            "National Museum of Western Art\n" +
            "\t" +
            "국립과학박물관\n" +
            "こくりつかがくはくぶつかん\n" +
            "National Science Museum\n" +
            "\t" +
            "아메야요코초\n" +
            "アメヤよこちょう\n" +
            "Ameyayokocho\n" +
            "\t" +
            "니케고욘마르 아키오카아르티잔\n" +
            "ニケゴヨンマル秋岡アルチザン\n" +
            "Nike Goyonmar Akioka Artisan\n" +
            "\t" +
            "챠바라 아키오카 마르쉐\n" +
            "チャバラ 秋岡 マルシェ\n" +
            "Chabara Akioka Marche\n" +
            "\t" +
            "아키하바라 라디오회관\n" +
            "秋葉原ラジオ会館\n" +
            "Akihabara Radio Hall\n" +
            "\t" +
            "요도바시아키바\n" +
            "淀橋アキバ\n" +
            "Yodobashi Kiba\n" +
            "\t" +
            "아키하바라 UDX\n" +
            "秋葉原UDX\n" +
            "Akihabara UDX\n" +
            "\t" +
            "마치 에큐트 간다 만세이바시\n" +
            "まるでエキュート神田万世橋\n" +
            "Maach ecute Kanda Manseibashi\n" +
            "\t" +
            "센소지\n" +
            "あさくさじ \n" +
            "Sensoji Temple\n" +
            "\t" +
            "나카미세도리\n" +
            "なかみせどおり\n" +
            "Nakamise Dori\n" +
            "\t" +
            "마루고토닛폰\n" +
            "マルゴトニッポン\n" +
            "Marugoto Nippon\n" +
            "\t" +
            "아사쿠사홋피도리\n" +
            "浅草ホッピー通り\n" +
            "Asakusa Hoppi Dori\n" +
            "\t" +
            "갓파바시도구거리\n" +
            "かっぱ橋道具通り\n" +
            "Kappabashi Tool Street\n" +
            "\t" +
            "도쿄 스카이트리타운\n" +
            "東京スカイツリータウン\n" +
            "Tokyo Skytree Town\n" +
            "\t" +
            "바다가 보이는 언덕 공원\n" +
            "海の見える丘公園\n" +
            "Harbor View Park\n" +
            "\t" +
            "모토마치 스트리트\n" +
            "元町ストリート\n" +
            "Motomachi Street\n" +
            "\t" +
            "모토마치 나카도리\n" +
            "元町中通り\n" +
            "Motomachi Nakadori\n" +
            "\t" +
            "요코하마 주카가이\n" +
            "横浜仲街\n" +
            "Yokohama Chukagai\n" +
            "\t" +
            "야마시타 공원\n" +
            "山下公園\n" +
            "Yamashita Park\n" +
            "\t" +
            "아카렌가소고\n" +
            "赤レンガそごう\n" +
            "Akarenga Sogo\n" +
            "\t" +
            "미나토미라이21\n" +
            "みなとみらい２１\n" +
            "Minato Mirai 21\n" +
            "\t" +
            "엔가쿠지\n" +
            "円覚寺\n" +
            "Engakuji\n" +
            "\t" +
            "요우쇼메이미술관\n" +
            "ようしょうめい美術館\n" +
            "Youshomei Art Museum\n" +
            "\t" +
            "유이가하마\n" +
            "ゆいがはま\n" +
            "Yuigahama\n" +
            "\t" +
            "가마쿠라 대불\n" +
            "鎌倉大仏\n" +
            "Kamakura Great Buddha\n" +
            "\t" +
            "고쿠라쿠지\n" +
            "ごくらくじ\n" +
            "Gokurakuji\n" +
            "\t" +
            "시치리가하마\n" +
            "しちりがはま \n" +
            "Shichirigahama\n" +
            "\t" +
            "에노시마\n" +
            "えのしま\n" +
            "Enoshima\n" +
            "\t" +
            "하코네유모토\n" +
            "はこねゆもと\n" +
            "Hakone Yumoto\n" +
            "\t" +
            "조각의 숲 미술관\n" +
            "はこね ちょうこくのもりびじゅつかん\n" +
            "Hakone Open-Air Museum \n" +
            "\t" +
            "고라공원\n" +
            "強羅公園\n" +
            "Gora Park\n" +
            "\t" +
            "오와쿠다니\n" +
            "だいわくや\n" +
            "Owakudani\n" +
            "\t" +
            "아시노코\n" +
            "あしのこう\n" +
            "Ashinoko\n" +
            "\t" +
            "모토하코네\n" +
            "もとはこね \n" +
            "Motohakone\n" +
            "\t" +
            "\t";

    static {
        TOURIST_SPOT_MAP = new HashMap<>();
        for (String txt : TXT.split("\t")) {
            String[] names = txt.split("\n");
            if (names.length > 0) {
                TouristSpot t = new TouristSpot();
                t.setId(TOURIST_SPOT_MAP.size() + 1);
                t.setKoreanName(names[0]);
                if (names.length > 1) {
                    t.setJapaneseName(names[1]);
                }
                if (names.length > 2) {
                    t.setEnglishName(names[2]);
                }
                System.out.println(t);
                TOURIST_SPOT_MAP.put(t.getId(), t);
            }
        }
    }
}