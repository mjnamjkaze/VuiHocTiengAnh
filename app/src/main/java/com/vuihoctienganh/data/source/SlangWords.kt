package com.vuihoctienganh.data.source

import com.vuihoctienganh.data.db.entity.WordEntity

/**
 * SlangTrack - Modern slang & informal expressions
 * Focus on casual conversation, social media, and pop culture.
 */
object SlangWords {
    fun getWords(): List<WordEntity> = listOf(
        WordEntity(3001, "lit", "/lɪt/", "tuyệt vời, sôi động", "adjective", "A2", "social", "slang", """["The party last night was lit!","This song is so lit.","The concert was absolutely lit."]"""),
        WordEntity(3002, "vibe", "/vaɪb/", "cảm giác, không khí", "noun", "A2", "social", "slang", """["I love the vibe of this café.","Good vibes only.","She has a really chill vibe."]"""),
        WordEntity(3003, "slay", "/sleɪ/", "làm xuất sắc, đỉnh", "verb", "A2", "social", "slang", """["She slayed that presentation.","You always slay in that outfit.","Slay, queen!"]"""),
        WordEntity(3004, "ghost", "/ɡoʊst/", "biến mất không liên lạc", "verb", "A2", "social", "slang", """["He ghosted me after the first date.","Don't ghost your friends.","She got ghosted and felt terrible."]"""),
        WordEntity(3005, "flex", "/fleks/", "khoe, thể hiện", "verb", "A2", "social", "slang", """["He's always flexing his new car.","Stop flexing on social media.","Nice flex, but nobody asked."]"""),

        WordEntity(3006, "lowkey", "/ˌloʊˈkiː/", "hơi, kín đáo", "adverb", "A2", "social", "slang", """["I lowkey love that song.","She's lowkey the best player on the team.","I'm lowkey stressed about the exam."]"""),
        WordEntity(3007, "highkey", "/ˌhaɪˈkiː/", "rõ ràng, công khai", "adverb", "A2", "social", "slang", """["I highkey need a vacation.","That movie was highkey amazing.","I highkey want to try that restaurant."]"""),
        WordEntity(3008, "savage", "/ˈsævɪdʒ/", "bá đạo, thẳng thắn", "adjective", "A2", "social", "slang", """["That comeback was savage.","She's a savage on the basketball court.","His comment was so savage."]"""),
        WordEntity(3009, "salty", "/ˈsɔːlti/", "bực bội, khó chịu", "adjective", "A2", "emotions", "slang", """["Why are you so salty today?","He got salty after losing the game.","Don't be salty about it."]"""),
        WordEntity(3010, "shade", "/ʃeɪd/", "nói xéo, chê bai", "noun", "A2", "social", "slang", """["She threw shade at her rival.","No shade, but that outfit is ugly.","Stop throwing shade at people."]"""),

        WordEntity(3011, "tea", "/tiː/", "tin đồn, chuyện nóng", "noun", "A2", "social", "slang", """["Spill the tea! What happened?","She always has the tea on everyone.","I heard some tea about the company."]"""),
        WordEntity(3012, "cap", "/kæp/", "nói dối", "noun", "A2", "social", "slang", """["No cap, this is the best pizza ever.","That's cap, I don't believe you.","She's capping about her test score."]"""),
        WordEntity(3013, "bussin", "/ˈbʌsɪn/", "ngon tuyệt, đỉnh", "adjective", "A2", "food", "slang", """["This food is bussin!","The party was bussin last night.","Her cooking is always bussin."]"""),
        WordEntity(3014, "bet", "/bet/", "OK, đồng ý, chắc chắn", "interjection", "A1", "social", "slang", """["Wanna go to the movies? Bet!","I'll be there at 8. Bet.","You can do it? Bet, let's see."]"""),
        WordEntity(3015, "simp", "/sɪmp/", "người quá si mê", "noun", "A2", "social", "slang", """["He buys her everything, what a simp.","Don't simp for someone who doesn't care.","She called him a simp."]"""),

        WordEntity(3016, "clout", "/klaʊt/", "tầm ảnh hưởng, nổi tiếng", "noun", "B1", "social", "slang", """["He's just doing it for clout.","Chasing clout on social media.","She has a lot of clout in the industry."]"""),
        WordEntity(3017, "fam", "/fæm/", "gia đình, bạn thân", "noun", "A1", "social", "slang", """["What's up, fam?","I'm hanging out with my fam tonight.","You're like fam to me."]"""),
        WordEntity(3018, "finesse", "/fɪˈnes/", "khéo léo xử lý", "verb", "B1", "social", "slang", """["She finessed her way through the interview.","I'm gonna finesse this situation.","He finessed a free upgrade."]"""),
        WordEntity(3019, "sus", "/sʌs/", "đáng ngờ", "adjective", "A1", "social", "slang", """["That guy is acting real sus.","Something about this deal seems sus.","Don't be sus, just tell the truth."]"""),
        WordEntity(3020, "goat", "/ɡoʊt/", "người giỏi nhất (Greatest Of All Time)", "noun", "A2", "social", "slang", """["Messi is the GOAT of soccer.","She's the GOAT at cooking.","You're the GOAT, thank you so much!"]"""),

        WordEntity(3021, "mood", "/muːd/", "tâm trạng, đồng cảm", "noun", "A1", "emotions", "slang", """["This rainy weather is such a mood.","Monday morning is a mood.","That video is a whole mood."]"""),
        WordEntity(3022, "stan", "/stæn/", "fan cuồng, cuồng theo", "verb", "A2", "social", "slang", """["I stan this band so hard.","We stan a responsible leader.","She stans Korean pop music."]"""),
        WordEntity(3023, "extra", "/ˈekstrə/", "quá lố, drama", "adjective", "A2", "social", "slang", """["She's so extra with her outfits.","Don't be so extra about it.","His reaction was way too extra."]"""),
        WordEntity(3024, "woke", "/woʊk/", "ý thức xã hội cao", "adjective", "B1", "society", "slang", """["She's very woke about environmental issues.","Stay woke about what's happening.","The company tried to appear woke."]"""),
        WordEntity(3025, "glow-up", "/ˈɡloʊ.ʌp/", "lột xác, thay đổi ngoạn mục", "noun", "A2", "social", "slang", """["She had the biggest glow-up!","What a glow-up since high school.","His confidence glow-up is impressive."]"""),

        WordEntity(3026, "chill", "/tʃɪl/", "thư giãn, thoải mái", "verb", "A1", "daily", "slang", """["Let's just chill at home tonight.","He's a very chill person.","I need to chill after this exam."]"""),
        WordEntity(3027, "fire", "/faɪər/", "cực hay, đỉnh", "adjective", "A2", "social", "slang", """["That new album is fire!","Your outfit is fire today.","This restaurant's food is straight fire."]"""),
        WordEntity(3028, "cringe", "/krɪndʒ/", "gây xấu hổ, ngại", "adjective", "A2", "emotions", "slang", """["That video was so cringe.","I cringed watching his speech.","Don't be cringe at the interview."]"""),
        WordEntity(3029, "drip", "/drɪp/", "phong cách, outfit đẹp", "noun", "A2", "social", "slang", """["Check out his drip today.","She always has the best drip.","That jacket is drip."]"""),
        WordEntity(3030, "bruh", "/brʌ/", "bạn ơi (ngạc nhiên/bực)", "interjection", "A1", "social", "slang", """["Bruh, did you just see that?","Bruh, that's not funny.","Bruh moment right there."]""")
    )
}
