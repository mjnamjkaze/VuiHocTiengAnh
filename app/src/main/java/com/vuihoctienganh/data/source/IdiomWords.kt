package com.vuihoctienganh.data.source

import com.vuihoctienganh.data.db.entity.WordEntity

/**
 * PIE (Phrases, Idioms & Expressions) dataset
 * Common English idioms and phrasal expressions.
 */
object IdiomWords {
    fun getWords(): List<WordEntity> = listOf(
        WordEntity(4001, "break the ice", "/breɪk ðə aɪs/", "phá vỡ sự im lặng", "idiom", "A2", "communication", "idiom", """["Tell a joke to break the ice.","She broke the ice by introducing herself.","Games can help break the ice at parties."]"""),
        WordEntity(4002, "piece of cake", "/piːs əv keɪk/", "dễ như ăn bánh", "idiom", "A2", "daily", "idiom", """["The test was a piece of cake.","Don't worry, it's a piece of cake.","For her, math is a piece of cake."]"""),
        WordEntity(4003, "hit the nail on the head", "/hɪt ðə neɪl ɒn ðə hed/", "nói đúng vấn đề", "idiom", "B1", "communication", "idiom", """["You hit the nail on the head with that answer.","She always hits the nail on the head.","His analysis hit the nail on the head."]"""),
        WordEntity(4004, "under the weather", "/ˈʌndər ðə ˈweðər/", "không khỏe", "idiom", "A2", "health", "idiom", """["I'm feeling under the weather today.","She's been under the weather all week.","Call in sick if you're under the weather."]"""),
        WordEntity(4005, "cost an arm and a leg", "/kɒst ən ɑːrm ænd ə leɡ/", "rất đắt đỏ", "idiom", "B1", "shopping", "idiom", """["That car costs an arm and a leg.","The repair cost us an arm and a leg.","Living in the city costs an arm and a leg."]"""),

        WordEntity(4006, "once in a blue moon", "/wʌns ɪn ə bluː muːn/", "hiếm khi, lâu lâu mới có", "idiom", "B1", "time", "idiom", """["I only eat fast food once in a blue moon.","He calls once in a blue moon.","We visit that place once in a blue moon."]"""),
        WordEntity(4007, "let the cat out of the bag", "/let ðə kæt aʊt əv ðə bæɡ/", "lỡ miệng tiết lộ bí mật", "idiom", "B1", "communication", "idiom", """["She let the cat out of the bag about the surprise party.","Don't let the cat out of the bag!","Oops, I let the cat out of the bag."]"""),
        WordEntity(4008, "kill two birds with one stone", "/kɪl tuː bɜːrdz wɪð wʌn stoʊn/", "một công đôi việc", "idiom", "B1", "work", "idiom", """["By studying abroad, I killed two birds with one stone.","Let's kill two birds with one stone and combine the meetings.","Shopping there kills two birds with one stone."]"""),
        WordEntity(4009, "speak of the devil", "/spiːk əv ðə ˈdevəl/", "nói Tào Tháo, Tào Tháo tới", "idiom", "A2", "social", "idiom", """["Speak of the devil! We were just talking about you.","Speak of the devil, here she comes.","Oh, speak of the devil!"]"""),
        WordEntity(4010, "bite off more than you can chew", "/baɪt ɒf mɔːr ðæn juː kæn tʃuː/", "ôm đồm quá nhiều", "idiom", "B1", "work", "idiom", """["I think I bit off more than I can chew with this project.","Don't bite off more than you can chew.","She bit off more than she could chew at work."]"""),

        WordEntity(4011, "the ball is in your court", "/ðə bɔːl ɪz ɪn jɔːr kɔːrt/", "đến lượt bạn quyết định", "idiom", "B1", "work", "idiom", """["I've done my part, the ball is in your court.","The ball is in your court now.","We made the offer, the ball is in their court."]"""),
        WordEntity(4012, "burn the midnight oil", "/bɜːrn ðə ˈmɪdnaɪt ɔɪl/", "thức khuya làm việc", "idiom", "B1", "work", "idiom", """["Students burn the midnight oil before exams.","She's been burning the midnight oil all week.","I had to burn the midnight oil to finish the report."]"""),
        WordEntity(4013, "get out of hand", "/ɡet aʊt əv hænd/", "mất kiểm soát", "idiom", "B1", "daily", "idiom", """["The situation got out of hand quickly.","Don't let things get out of hand.","The party got out of hand."]"""),
        WordEntity(4014, "hang in there", "/hæŋ ɪn ðer/", "cố lên, đừng bỏ cuộc", "idiom", "A2", "emotions", "idiom", """["Hang in there, things will get better.","She told me to hang in there.","Just hang in there, you're almost done."]"""),
        WordEntity(4015, "hit the books", "/hɪt ðə bʊks/", "học bài chăm chỉ", "idiom", "A2", "education", "idiom", """["I need to hit the books tonight.","She hits the books every evening.","Time to stop playing and hit the books."]"""),

        WordEntity(4016, "on the same page", "/ɒn ðə seɪm peɪdʒ/", "cùng quan điểm, đồng ý", "idiom", "B1", "work", "idiom", """["Let's make sure we're all on the same page.","Are we on the same page about this?","The team is finally on the same page."]"""),
        WordEntity(4017, "pull someone's leg", "/pʊl ˈsʌmwʌnz leɡ/", "trêu đùa ai đó", "idiom", "A2", "social", "idiom", """["Are you pulling my leg?","I was just pulling your leg, don't worry.","He loves pulling people's legs."]"""),
        WordEntity(4018, "the last straw", "/ðə lɑːst strɔː/", "giọt nước tràn ly", "idiom", "B1", "emotions", "idiom", """["Being late again was the last straw.","That comment was the last straw for me.","It was the last straw and she quit."]"""),
        WordEntity(4019, "a blessing in disguise", "/ə ˈblesɪŋ ɪn dɪsˈɡaɪz/", "trong cái rủi có cái may", "idiom", "B1", "daily", "idiom", """["Losing that job was a blessing in disguise.","The delay turned out to be a blessing in disguise.","What seemed bad was actually a blessing in disguise."]"""),
        WordEntity(4020, "easier said than done", "/ˈiːziər sed ðæn dʌn/", "nói dễ hơn làm", "idiom", "A2", "daily", "idiom", """["Saving money is easier said than done.","That's easier said than done.","Learning a new language is easier said than done."]"""),

        WordEntity(4021, "actions speak louder than words", "/ˈækʃənz spiːk ˈlaʊdər ðæn wɜːrdz/", "hành động quan trọng hơn lời nói", "idiom", "B1", "daily", "idiom", """["Don't just promise, actions speak louder than words.","She believes actions speak louder than words.","Remember that actions speak louder than words."]"""),
        WordEntity(4022, "back to square one", "/bæk tə skwer wʌn/", "quay lại từ đầu", "idiom", "B1", "work", "idiom", """["The plan failed, we're back to square one.","If this doesn't work, it's back to square one.","Every mistake puts us back to square one."]"""),
        WordEntity(4023, "call it a day", "/kɔːl ɪt ə deɪ/", "kết thúc công việc hôm nay", "idiom", "A2", "work", "idiom", """["I'm tired, let's call it a day.","We should call it a day and rest.","After 10 hours, they called it a day."]"""),
        WordEntity(4024, "go the extra mile", "/ɡoʊ ðiː ˈekstrə maɪl/", "nỗ lực hơn bình thường", "idiom", "B1", "work", "idiom", """["She always goes the extra mile for customers.","If you want to succeed, go the extra mile.","Good employees go the extra mile."]"""),
        WordEntity(4025, "it takes two to tango", "/ɪt teɪks tuː tə ˈtæŋɡoʊ/", "cần cả hai bên", "idiom", "B1", "social", "idiom", """["Don't just blame him, it takes two to tango.","Remember, it takes two to tango.","In any argument, it takes two to tango."]"""),

        WordEntity(4026, "miss the boat", "/mɪs ðə boʊt/", "lỡ cơ hội", "idiom", "B1", "daily", "idiom", """["Apply now or you'll miss the boat.","I missed the boat on that investment.","Don't miss the boat on this deal."]"""),
        WordEntity(4027, "stay on top of", "/steɪ ɒn tɒp əv/", "kiểm soát tốt, theo sát", "idiom", "B1", "work", "idiom", """["Stay on top of your emails.","She stays on top of all the deadlines.","It's hard to stay on top of everything."]"""),
        WordEntity(4028, "think outside the box", "/θɪŋk ˌaʊtˈsaɪd ðə bɒks/", "suy nghĩ sáng tạo", "idiom", "B1", "work", "idiom", """["We need to think outside the box.","She always thinks outside the box.","Innovation requires thinking outside the box."]"""),
        WordEntity(4029, "time flies", "/taɪm flaɪz/", "thời gian trôi nhanh", "idiom", "A2", "time", "idiom", """["Time flies when you're having fun.","I can't believe it's December already, time flies!","Time really flies when you're busy."]"""),
        WordEntity(4030, "wrap up", "/ræp ʌp/", "kết thúc, tổng kết", "idiom", "A2", "work", "idiom", """["Let's wrap up the meeting.","We need to wrap up this project by Friday.","She wrapped up her presentation in style."]""")
    )
}
