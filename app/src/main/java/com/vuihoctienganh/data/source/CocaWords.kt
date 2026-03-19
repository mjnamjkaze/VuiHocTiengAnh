package com.vuihoctienganh.data.source

import com.vuihoctienganh.data.db.entity.WordEntity

/**
 * COCA (Corpus of Contemporary American English) - Top spoken words
 * Curated list of most frequently used American English words.
 */
object CocaWords {
    fun getWords(): List<WordEntity> = listOf(
        // === A1 Level — Daily Life ===
        WordEntity(1001, "hello", "/həˈloʊ/", "xin chào", "interjection", "A1", "greetings", "coca", """["Hello, how are you today?","She said hello to everyone at the party.","Hello! Is anyone home?"]"""),
        WordEntity(1002, "thank", "/θæŋk/", "cảm ơn", "verb", "A1", "greetings", "coca", """["Thank you for your help.","I want to thank my teacher.","Thanks for coming to my birthday."]"""),
        WordEntity(1003, "please", "/pliːz/", "làm ơn", "adverb", "A1", "greetings", "coca", """["Please sit down.","Can I have some water, please?","Please don't be late."]"""),
        WordEntity(1004, "sorry", "/ˈsɑːri/", "xin lỗi", "adjective", "A1", "greetings", "coca", """["I'm sorry I'm late.","Sorry, I didn't hear you.","She felt sorry for the lost puppy."]"""),
        WordEntity(1005, "goodbye", "/ɡʊdˈbaɪ/", "tạm biệt", "interjection", "A1", "greetings", "coca", """["Goodbye! See you tomorrow.","She waved goodbye to her friends.","It's hard to say goodbye."]"""),

        WordEntity(1006, "water", "/ˈwɔːtər/", "nước", "noun", "A1", "food", "coca", """["Can I have a glass of water?","The water in the lake is very clean.","Plants need water to grow."]"""),
        WordEntity(1007, "food", "/fuːd/", "thức ăn", "noun", "A1", "food", "coca", """["This food is delicious.","We need to buy food for dinner.","My favorite food is pizza."]"""),
        WordEntity(1008, "eat", "/iːt/", "ăn", "verb", "A1", "food", "coca", """["I eat breakfast every morning.","What do you want to eat?","Don't eat too much candy."]"""),
        WordEntity(1009, "drink", "/drɪŋk/", "uống", "verb", "A1", "food", "coca", """["I drink coffee every morning.","Would you like something to drink?","You should drink more water."]"""),
        WordEntity(1010, "rice", "/raɪs/", "cơm, gạo", "noun", "A1", "food", "coca", """["We eat rice every day.","Can you cook rice?","Rice is a staple food in Asia."]"""),

        WordEntity(1011, "happy", "/ˈhæpi/", "vui vẻ, hạnh phúc", "adjective", "A1", "emotions", "coca", """["I'm very happy today.","She looks happy in the photo.","Happy birthday to you!"]"""),
        WordEntity(1012, "sad", "/sæd/", "buồn", "adjective", "A1", "emotions", "coca", """["Why are you sad?","The movie was very sad.","Don't be sad, everything will be okay."]"""),
        WordEntity(1013, "angry", "/ˈæŋɡri/", "tức giận", "adjective", "A1", "emotions", "coca", """["He was angry about the mistake.","Don't be angry with me.","The angry dog barked loudly."]"""),
        WordEntity(1014, "tired", "/taɪərd/", "mệt mỏi", "adjective", "A1", "emotions", "coca", """["I'm very tired after work.","She looks tired today.","The tired children fell asleep quickly."]"""),
        WordEntity(1015, "afraid", "/əˈfreɪd/", "sợ hãi", "adjective", "A1", "emotions", "coca", """["Don't be afraid of the dark.","I'm afraid of spiders.","She was afraid to speak in public."]"""),

        WordEntity(1016, "house", "/haʊs/", "nhà", "noun", "A1", "home", "coca", """["We live in a big house.","Come to my house for dinner.","The house has three bedrooms."]"""),
        WordEntity(1017, "room", "/ruːm/", "phòng", "noun", "A1", "home", "coca", """["My room is very clean.","This room is too small.","There are four rooms in the house."]"""),
        WordEntity(1018, "door", "/dɔːr/", "cửa", "noun", "A1", "home", "coca", """["Please close the door.","Someone is knocking at the door.","The door is locked."]"""),
        WordEntity(1019, "window", "/ˈwɪndoʊ/", "cửa sổ", "noun", "A1", "home", "coca", """["Open the window, please.","I can see the garden from my window.","The window is broken."]"""),
        WordEntity(1020, "table", "/ˈteɪbəl/", "bàn", "noun", "A1", "home", "coca", """["Put the book on the table.","We sit at the table for dinner.","The table is made of wood."]"""),

        WordEntity(1021, "school", "/skuːl/", "trường học", "noun", "A1", "education", "coca", """["I go to school every day.","School starts at 8 o'clock.","My school is near my house."]"""),
        WordEntity(1022, "teacher", "/ˈtiːtʃər/", "giáo viên", "noun", "A1", "education", "coca", """["My teacher is very kind.","The teacher explains the lesson well.","She wants to be a teacher."]"""),
        WordEntity(1023, "student", "/ˈstuːdənt/", "học sinh, sinh viên", "noun", "A1", "education", "coca", """["He is a good student.","There are 30 students in my class.","The student studies very hard."]"""),
        WordEntity(1024, "book", "/bʊk/", "sách", "noun", "A1", "education", "coca", """["I love reading books.","This book is very interesting.","Please open your book to page 10."]"""),
        WordEntity(1025, "write", "/raɪt/", "viết", "verb", "A1", "education", "coca", """["Please write your name here.","She writes in her diary every night.","I can write in English."]"""),

        WordEntity(1026, "morning", "/ˈmɔːrnɪŋ/", "buổi sáng", "noun", "A1", "time", "coca", """["Good morning, everyone!","I wake up early in the morning.","She exercises every morning."]"""),
        WordEntity(1027, "night", "/naɪt/", "đêm", "noun", "A1", "time", "coca", """["Good night! Sleep well.","The stars are beautiful at night.","I study at night."]"""),
        WordEntity(1028, "today", "/təˈdeɪ/", "hôm nay", "adverb", "A1", "time", "coca", """["What day is today?","I have a meeting today.","Today is a beautiful day."]"""),
        WordEntity(1029, "tomorrow", "/təˈmɒroʊ/", "ngày mai", "adverb", "A1", "time", "coca", """["See you tomorrow!","Tomorrow is Saturday.","I'll do it tomorrow."]"""),
        WordEntity(1030, "yesterday", "/ˈjestərdeɪ/", "hôm qua", "adverb", "A1", "time", "coca", """["I was sick yesterday.","Yesterday was Monday.","What did you do yesterday?"]"""),

        // === A1 Level — People & Family ===
        WordEntity(1031, "mother", "/ˈmʌðər/", "mẹ", "noun", "A1", "family", "coca", """["My mother cooks very well.","I love my mother very much.","Mother's Day is in May."]"""),
        WordEntity(1032, "father", "/ˈfɑːðər/", "bố", "noun", "A1", "family", "coca", """["My father works in an office.","Father takes me to school.","He looks like his father."]"""),
        WordEntity(1033, "friend", "/frend/", "bạn bè", "noun", "A1", "family", "coca", """["She is my best friend.","I have many friends at school.","A true friend is hard to find."]"""),
        WordEntity(1034, "child", "/tʃaɪld/", "trẻ em", "noun", "A1", "family", "coca", """["The child is playing in the park.","Every child deserves education.","She has two children."]"""),
        WordEntity(1035, "name", "/neɪm/", "tên", "noun", "A1", "personal", "coca", """["What is your name?","My name is Minh.","She forgot his name."]"""),

        // === A1 Level — Actions ===
        WordEntity(1036, "go", "/ɡoʊ/", "đi", "verb", "A1", "actions", "coca", """["I go to work every day.","Let's go to the park.","She goes to bed at 10 PM."]"""),
        WordEntity(1037, "come", "/kʌm/", "đến", "verb", "A1", "actions", "coca", """["Come here, please.","She comes from Vietnam.","The bus is coming."]"""),
        WordEntity(1038, "see", "/siː/", "nhìn thấy", "verb", "A1", "actions", "coca", """["I can see the mountains.","Nice to see you again.","Did you see the movie?"]"""),
        WordEntity(1039, "know", "/noʊ/", "biết", "verb", "A1", "actions", "coca", """["I know the answer.","Do you know this person?","She knows how to swim."]"""),
        WordEntity(1040, "want", "/wɒnt/", "muốn", "verb", "A1", "actions", "coca", """["I want to go home.","What do you want for dinner?","She wants a new phone."]"""),

        WordEntity(1041, "like", "/laɪk/", "thích", "verb", "A1", "actions", "coca", """["I like chocolate ice cream.","Do you like music?","She likes to read books."]"""),
        WordEntity(1042, "love", "/lʌv/", "yêu", "verb", "A1", "emotions", "coca", """["I love my family.","She loves playing guitar.","Love is the most important thing."]"""),
        WordEntity(1043, "help", "/help/", "giúp đỡ", "verb", "A1", "actions", "coca", """["Can you help me?","I want to help my friends.","Help! I need somebody."]"""),
        WordEntity(1044, "work", "/wɜːrk/", "làm việc", "verb", "A1", "work", "coca", """["I work from Monday to Friday.","She works in a hospital.","Hard work pays off."]"""),
        WordEntity(1045, "play", "/pleɪ/", "chơi", "verb", "A1", "actions", "coca", """["The children play in the garden.","Do you play soccer?","Let's play a game."]"""),

        // === A1 Level — Things ===
        WordEntity(1046, "phone", "/foʊn/", "điện thoại", "noun", "A1", "technology", "coca", """["Where is my phone?","She's talking on the phone.","I bought a new phone."]"""),
        WordEntity(1047, "money", "/ˈmʌni/", "tiền", "noun", "A1", "daily", "coca", """["I don't have much money.","Money can't buy happiness.","How much money do you need?"]"""),
        WordEntity(1048, "car", "/kɑːr/", "xe hơi", "noun", "A1", "transport", "coca", """["He drives a red car.","I go to work by car.","The car is very fast."]"""),
        WordEntity(1049, "clothes", "/kloʊðz/", "quần áo", "noun", "A1", "daily", "coca", """["I need new clothes.","She wears beautiful clothes.","Put on warm clothes."]"""),
        WordEntity(1050, "weather", "/ˈweðər/", "thời tiết", "noun", "A1", "nature", "coca", """["The weather is nice today.","What's the weather like?","Bad weather is coming."]"""),

        // === A2 Level — Communication ===
        WordEntity(1051, "understand", "/ˌʌndərˈstænd/", "hiểu", "verb", "A2", "communication", "coca", """["I understand the lesson now.","Do you understand English?","It's hard to understand this problem."]"""),
        WordEntity(1052, "explain", "/ɪkˈspleɪn/", "giải thích", "verb", "A2", "communication", "coca", """["Can you explain this to me?","The teacher explained the rule clearly.","Let me explain my idea."]"""),
        WordEntity(1053, "agree", "/əˈɡriː/", "đồng ý", "verb", "A2", "communication", "coca", """["I agree with you.","We all agreed on the plan.","Do you agree or disagree?"]"""),
        WordEntity(1054, "believe", "/bɪˈliːv/", "tin tưởng", "verb", "A2", "communication", "coca", """["I believe in you.","Do you believe this story?","She believes she can succeed."]"""),
        WordEntity(1055, "suggest", "/səˈdʒest/", "đề nghị, gợi ý", "verb", "A2", "communication", "coca", """["I suggest we take a break.","Can you suggest a good restaurant?","She suggested a different approach."]"""),

        WordEntity(1056, "decision", "/dɪˈsɪʒən/", "quyết định", "noun", "A2", "work", "coca", """["It was a difficult decision.","Have you made a decision yet?","The decision changed everything."]"""),
        WordEntity(1057, "problem", "/ˈprɒbləm/", "vấn đề", "noun", "A2", "work", "coca", """["What's the problem?","We need to solve this problem.","No problem! I can help."]"""),
        WordEntity(1058, "answer", "/ˈænsər/", "câu trả lời", "noun", "A2", "education", "coca", """["What's the answer to question 5?","I don't know the answer.","She answered all questions correctly."]"""),
        WordEntity(1059, "question", "/ˈkwestʃən/", "câu hỏi", "noun", "A2", "education", "coca", """["Can I ask a question?","That's a good question.","She asked me a difficult question."]"""),
        WordEntity(1060, "reason", "/ˈriːzən/", "lý do", "noun", "A2", "communication", "coca", """["What's the reason for being late?","There must be a reason.","Give me one good reason."]"""),

        // === A2 Level — Daily Activities ===
        WordEntity(1061, "remember", "/rɪˈmembər/", "nhớ", "verb", "A2", "actions", "coca", """["I can't remember his name.","Remember to lock the door.","She remembers everything from childhood."]"""),
        WordEntity(1062, "forget", "/fərˈɡet/", "quên", "verb", "A2", "actions", "coca", """["Don't forget your keys.","I always forget her birthday.","He forgot to call me."]"""),
        WordEntity(1063, "choose", "/tʃuːz/", "chọn", "verb", "A2", "actions", "coca", """["You can choose any color.","I chose the red dress.","It's hard to choose between them."]"""),
        WordEntity(1064, "spend", "/spend/", "dành, tiêu", "verb", "A2", "daily", "coca", """["I spend time with my family.","Don't spend too much money.","She spends hours reading."]"""),
        WordEntity(1065, "travel", "/ˈtrævəl/", "du lịch", "verb", "A2", "travel", "coca", """["I love to travel.","She travels around the world.","We traveled to Japan last year."]"""),

        WordEntity(1066, "practice", "/ˈpræktɪs/", "luyện tập", "verb", "A2", "education", "coca", """["Practice makes perfect.","I practice English every day.","She practices piano for two hours."]"""),
        WordEntity(1067, "improve", "/ɪmˈpruːv/", "cải thiện", "verb", "A2", "education", "coca", """["I want to improve my English.","The situation has improved.","Practice will improve your skills."]"""),
        WordEntity(1068, "prepare", "/prɪˈper/", "chuẩn bị", "verb", "A2", "actions", "coca", """["She prepared dinner for the family.","Are you prepared for the test?","Prepare yourself for a surprise."]"""),
        WordEntity(1069, "compare", "/kəmˈper/", "so sánh", "verb", "A2", "education", "coca", """["Don't compare yourself to others.","Compare these two products.","Compared to last year, this is better."]"""),
        WordEntity(1070, "develop", "/dɪˈveləp/", "phát triển", "verb", "A2", "work", "coca", """["The city is developing quickly.","She developed a new skill.","We need to develop a plan."]"""),

        // === A2 Level — Descriptions ===
        WordEntity(1071, "important", "/ɪmˈpɔːrtənt/", "quan trọng", "adjective", "A2", "descriptions", "coca", """["This is very important.","Education is important for everyone.","The most important thing is health."]"""),
        WordEntity(1072, "different", "/ˈdɪfərənt/", "khác nhau", "adjective", "A2", "descriptions", "coca", """["We have different opinions.","This is different from what I expected.","Every person is different."]"""),
        WordEntity(1073, "possible", "/ˈpɒsəbəl/", "có thể", "adjective", "A2", "descriptions", "coca", """["Is it possible to change the date?","Anything is possible.","Please reply as soon as possible."]"""),
        WordEntity(1074, "popular", "/ˈpɒpjələr/", "phổ biến", "adjective", "A2", "descriptions", "coca", """["This song is very popular.","He is popular at school.","Coffee is a popular drink."]"""),
        WordEntity(1075, "necessary", "/ˈnesəseri/", "cần thiết", "adjective", "A2", "descriptions", "coca", """["Sleep is necessary for health.","Is it really necessary?","Take the necessary steps."]"""),

        // === B1 Level — Work & Business ===
        WordEntity(1076, "achieve", "/əˈtʃiːv/", "đạt được", "verb", "B1", "work", "coca", """["She achieved her dream.","What do you want to achieve this year?","Hard work helps you achieve success."]"""),
        WordEntity(1077, "opportunity", "/ˌɒpərˈtuːnəti/", "cơ hội", "noun", "B1", "work", "coca", """["This is a great opportunity.","Don't miss this opportunity.","Equal opportunities for everyone."]"""),
        WordEntity(1078, "experience", "/ɪkˈspɪriəns/", "kinh nghiệm", "noun", "B1", "work", "coca", """["She has 5 years of experience.","It was an amazing experience.","Experience is the best teacher."]"""),
        WordEntity(1079, "responsible", "/rɪˈspɒnsəbəl/", "chịu trách nhiệm", "adjective", "B1", "work", "coca", """["Who is responsible for this?","She is a responsible person.","Be responsible for your actions."]"""),
        WordEntity(1080, "professional", "/prəˈfeʃənəl/", "chuyên nghiệp", "adjective", "B1", "work", "coca", """["He is very professional.","Professional development is important.","She is a professional singer."]"""),

        WordEntity(1081, "communicate", "/kəˈmjuːnɪkeɪt/", "giao tiếp", "verb", "B1", "communication", "coca", """["We need to communicate better.","She communicates clearly.","It's important to communicate your feelings."]"""),
        WordEntity(1082, "consider", "/kənˈsɪdər/", "cân nhắc", "verb", "B1", "communication", "coca", """["Please consider my proposal.","I'm considering changing jobs.","Consider the consequences before acting."]"""),
        WordEntity(1083, "encourage", "/ɪnˈkɜːrɪdʒ/", "khuyến khích", "verb", "B1", "communication", "coca", """["Teachers encourage students to ask questions.","She encouraged me to try again.","This news is very encouraging."]"""),
        WordEntity(1084, "benefit", "/ˈbenɪfɪt/", "lợi ích", "noun", "B1", "work", "coca", """["Exercise has many health benefits.","What are the benefits of this plan?","Everyone will benefit from the change."]"""),
        WordEntity(1085, "challenge", "/ˈtʃælɪndʒ/", "thách thức", "noun", "B1", "work", "coca", """["This is a big challenge.","She loves a good challenge.","The biggest challenge is time management."]"""),

        // === B1 Level — Society & Relationships ===
        WordEntity(1086, "environment", "/ɪnˈvaɪrənmənt/", "môi trường", "noun", "B1", "nature", "coca", """["Protect the environment.","The work environment is friendly.","Pollution damages the environment."]"""),
        WordEntity(1087, "community", "/kəˈmjuːnəti/", "cộng đồng", "noun", "B1", "society", "coca", """["Our community is very supportive.","Community service is important.","She is active in the community."]"""),
        WordEntity(1088, "situation", "/ˌsɪtʃuˈeɪʃən/", "tình huống", "noun", "B1", "daily", "coca", """["This is a difficult situation.","What would you do in this situation?","The situation is under control."]"""),
        WordEntity(1089, "relationship", "/rɪˈleɪʃənʃɪp/", "mối quan hệ", "noun", "B1", "society", "coca", """["They have a good relationship.","Building strong relationships takes time.","The relationship between the two countries improved."]"""),
        WordEntity(1090, "government", "/ˈɡʌvərnmənt/", "chính phủ", "noun", "B1", "society", "coca", """["The government made a new policy.","Government spending has increased.","Local government provides many services."]"""),

        // === B1 Level — Abstract & Academic ===
        WordEntity(1091, "research", "/rɪˈsɜːrtʃ/", "nghiên cứu", "noun", "B1", "education", "coca", """["Scientific research is important.","She did research on climate change.","The research results are promising."]"""),
        WordEntity(1092, "technology", "/tekˈnɒlədʒi/", "công nghệ", "noun", "B1", "technology", "coca", """["Technology changes our lives.","New technology makes work easier.","Information technology is growing fast."]"""),
        WordEntity(1093, "quality", "/ˈkwɒləti/", "chất lượng", "noun", "B1", "descriptions", "coca", """["Quality is more important than quantity.","The quality of this product is excellent.","We focus on quality education."]"""),
        WordEntity(1094, "attention", "/əˈtenʃən/", "sự chú ý", "noun", "B1", "education", "coca", """["Pay attention to the teacher.","This issue needs our attention.","She wants attention from her parents."]"""),
        WordEntity(1095, "advantage", "/ədˈvæntɪdʒ/", "lợi thế", "noun", "B1", "work", "coca", """["What are the advantages of this method?","Take advantage of this opportunity.","He has an advantage over other candidates."]"""),

        // === B2 Level — Advanced ===
        WordEntity(1096, "acknowledge", "/əkˈnɒlɪdʒ/", "thừa nhận", "verb", "B2", "communication", "coca", """["She acknowledged her mistake.","We must acknowledge the problem.","He acknowledged the applause with a smile."]"""),
        WordEntity(1097, "consequence", "/ˈkɒnsɪkwəns/", "hậu quả", "noun", "B2", "society", "coca", """["Every action has consequences.","Think about the consequences first.","The consequences were severe."]"""),
        WordEntity(1098, "influence", "/ˈɪnfluəns/", "ảnh hưởng", "noun", "B2", "society", "coca", """["Social media has a big influence.","She has a positive influence on others.","His work influenced many people."]"""),
        WordEntity(1099, "perspective", "/pərˈspektɪv/", "quan điểm", "noun", "B2", "communication", "coca", """["Try to see things from a different perspective.","Everyone has their own perspective.","From my perspective, this is the best option."]"""),
        WordEntity(1100, "significant", "/sɪɡˈnɪfɪkənt/", "đáng kể, quan trọng", "adjective", "B2", "descriptions", "coca", """["There was a significant improvement.","This is a significant discovery.","The difference is not significant."]""")
    )
}
