package com.vuihoctienganh.data.source

import com.vuihoctienganh.data.db.entity.WordEntity

/**
 * BNC2014 (British National Corpus) - Academic & formal English
 * Focus on reading comprehension, formal communication, and TOEIC prep.
 */
object BncWords {
    fun getWords(): List<WordEntity> = listOf(
        // === A1 Level — Basic Formal ===
        WordEntity(2001, "appointment", "/əˈpɔɪntmənt/", "cuộc hẹn", "noun", "A1", "work", "bnc", """["I have a doctor's appointment at 3 PM.","Please make an appointment before visiting.","She missed her appointment yesterday."]"""),
        WordEntity(2002, "available", "/əˈveɪləbəl/", "có sẵn, rảnh", "adjective", "A1", "work", "bnc", """["Is this seat available?","The manager is not available right now.","This product is available online."]"""),
        WordEntity(2003, "request", "/rɪˈkwest/", "yêu cầu", "noun", "A2", "work", "bnc", """["I'd like to make a request.","Your request has been approved.","She sent a request for more information."]"""),
        WordEntity(2004, "provide", "/prəˈvaɪd/", "cung cấp", "verb", "A2", "work", "bnc", """["We provide free delivery.","Can you provide more details?","The company provides health insurance."]"""),
        WordEntity(2005, "require", "/rɪˈkwaɪər/", "yêu cầu, đòi hỏi", "verb", "A2", "work", "bnc", """["This job requires experience.","All students are required to attend.","Do you require any assistance?"]"""),

        WordEntity(2006, "schedule", "/ˈʃedjuːl/", "lịch trình", "noun", "A2", "work", "bnc", """["What's your schedule for today?","The schedule has been changed.","Please check the train schedule."]"""),
        WordEntity(2007, "purchase", "/ˈpɜːrtʃəs/", "mua", "verb", "A2", "shopping", "bnc", """["Would you like to purchase this item?","She purchased a new laptop.","Online purchases have increased."]"""),
        WordEntity(2008, "confirm", "/kənˈfɜːrm/", "xác nhận", "verb", "A2", "work", "bnc", """["Please confirm your booking.","I can confirm the meeting is at 2 PM.","She confirmed her attendance."]"""),
        WordEntity(2009, "deliver", "/dɪˈlɪvər/", "giao hàng", "verb", "A2", "shopping", "bnc", """["We deliver within 3 business days.","The package was delivered this morning.","Can you deliver to this address?"]"""),
        WordEntity(2010, "receive", "/rɪˈsiːv/", "nhận", "verb", "A2", "communication", "bnc", """["I received your email.","She received a scholarship.","Did you receive the package?"]"""),

        // === A2-B1 Level — Business & Formal ===
        WordEntity(2011, "conference", "/ˈkɒnfərəns/", "hội nghị", "noun", "B1", "work", "bnc", """["We're attending a conference next week.","The conference room is on the 5th floor.","An international conference on technology."]"""),
        WordEntity(2012, "negotiate", "/nɪˈɡoʊʃieɪt/", "đàm phán", "verb", "B1", "work", "bnc", """["We need to negotiate the terms.","She negotiated a better salary.","The two sides are negotiating a deal."]"""),
        WordEntity(2013, "implement", "/ˈɪmplɪment/", "thực hiện, triển khai", "verb", "B1", "work", "bnc", """["We will implement the new policy next month.","The plan was implemented successfully.","It's time to implement changes."]"""),
        WordEntity(2014, "analysis", "/əˈnæləsɪs/", "phân tích", "noun", "B1", "education", "bnc", """["The analysis shows positive results.","We need a thorough analysis of the data.","Her analysis of the market was accurate."]"""),
        WordEntity(2015, "establish", "/ɪˈstæblɪʃ/", "thành lập, thiết lập", "verb", "B1", "work", "bnc", """["The company was established in 2010.","We need to establish clear guidelines.","She established a good reputation."]"""),

        WordEntity(2016, "participate", "/pɑːrˈtɪsɪpeɪt/", "tham gia", "verb", "B1", "education", "bnc", """["Everyone is encouraged to participate.","She participated in the competition.","Students participate in group discussions."]"""),
        WordEntity(2017, "appropriate", "/əˈproʊpriət/", "phù hợp", "adjective", "B1", "descriptions", "bnc", """["This is not appropriate behavior.","Wear appropriate clothing for the interview.","Choose an appropriate response."]"""),
        WordEntity(2018, "sufficient", "/səˈfɪʃənt/", "đủ", "adjective", "B1", "descriptions", "bnc", """["Is this sufficient for your needs?","We don't have sufficient evidence.","A sufficient amount of sleep is important."]"""),
        WordEntity(2019, "domestic", "/dəˈmestɪk/", "trong nước, nội địa", "adjective", "B1", "society", "bnc", """["Domestic flights are cheaper.","The domestic market is growing.","Domestic products are improving in quality."]"""),
        WordEntity(2020, "relevant", "/ˈreləvənt/", "liên quan", "adjective", "B1", "descriptions", "bnc", """["This is not relevant to our discussion.","Please provide relevant information only.","His experience is relevant to this job."]"""),

        // === B1-B2 Level — Advanced Formal ===
        WordEntity(2021, "allocate", "/ˈæləkeɪt/", "phân bổ", "verb", "B2", "work", "bnc", """["We need to allocate more resources.","The budget was allocated to different departments.","Time should be allocated wisely."]"""),
        WordEntity(2022, "comprehensive", "/ˌkɒmprɪˈhensɪv/", "toàn diện", "adjective", "B2", "descriptions", "bnc", """["We need a comprehensive plan.","The report provides a comprehensive overview.","A comprehensive study of the problem."]"""),
        WordEntity(2023, "preliminary", "/prɪˈlɪmɪneri/", "sơ bộ, ban đầu", "adjective", "B2", "work", "bnc", """["The preliminary results look promising.","We had a preliminary meeting yesterday.","This is just a preliminary draft."]"""),
        WordEntity(2024, "fundamental", "/ˌfʌndəˈmentəl/", "cơ bản, nền tảng", "adjective", "B2", "education", "bnc", """["This is a fundamental principle.","Education is a fundamental right.","There are fundamental differences between them."]"""),
        WordEntity(2025, "substantial", "/səbˈstænʃəl/", "đáng kể, lớn", "adjective", "B2", "descriptions", "bnc", """["There has been a substantial increase in sales.","A substantial amount of money was invested.","The evidence is substantial."]"""),

        WordEntity(2026, "demonstrate", "/ˈdemənstreɪt/", "chứng minh, trình diễn", "verb", "B1", "education", "bnc", """["Let me demonstrate how it works.","The experiment demonstrated the theory.","She demonstrated excellent leadership skills."]"""),
        WordEntity(2027, "evaluate", "/ɪˈvæljueɪt/", "đánh giá", "verb", "B1", "education", "bnc", """["We need to evaluate the results.","The teacher evaluates student progress.","It's important to evaluate risks."]"""),
        WordEntity(2028, "guarantee", "/ˌɡærənˈtiː/", "bảo đảm", "verb", "B1", "work", "bnc", """["We guarantee customer satisfaction.","I can't guarantee it will work.","The product comes with a one-year guarantee."]"""),
        WordEntity(2029, "accommodate", "/əˈkɒmədeɪt/", "cung cấp chỗ ở, đáp ứng", "verb", "B2", "travel", "bnc", """["The hotel can accommodate 200 guests.","We will try to accommodate your request.","The room accommodates up to 10 people."]"""),
        WordEntity(2030, "collaborate", "/kəˈlæbəreɪt/", "hợp tác", "verb", "B2", "work", "bnc", """["We collaborate with international partners.","The teams collaborated on the project.","It's better to collaborate than compete."]"""),

        // === TOEIC-focused ===
        WordEntity(2031, "inventory", "/ˈɪnvəntɔːri/", "hàng tồn kho, kho hàng", "noun", "B1", "work", "bnc", """["We need to check the inventory.","The inventory system needs updating.","Low inventory means we need to reorder."]"""),
        WordEntity(2032, "invoice", "/ˈɪnvɔɪs/", "hóa đơn", "noun", "B1", "work", "bnc", """["Please send the invoice to our office.","The invoice is due by the end of the month.","I received an invoice for the service."]"""),
        WordEntity(2033, "deadline", "/ˈdedlaɪn/", "hạn chót", "noun", "A2", "work", "bnc", """["The deadline is next Friday.","We must meet the deadline.","Can we extend the deadline?"]"""),
        WordEntity(2034, "budget", "/ˈbʌdʒɪt/", "ngân sách", "noun", "B1", "work", "bnc", """["The project is over budget.","We need to reduce the budget.","The annual budget was approved."]"""),
        WordEntity(2035, "revenue", "/ˈrevənjuː/", "doanh thu", "noun", "B2", "work", "bnc", """["Company revenue increased by 20%.","Advertising is our main source of revenue.","Revenue exceeded expectations."]"""),

        WordEntity(2036, "warranty", "/ˈwɒrənti/", "bảo hành", "noun", "B1", "shopping", "bnc", """["The warranty covers repairs for one year.","Is this product still under warranty?","Extended warranty is available."]"""),
        WordEntity(2037, "complimentary", "/ˌkɒmplɪˈmentəri/", "miễn phí, tặng kèm", "adjective", "B1", "travel", "bnc", """["Complimentary breakfast is included.","Guests receive a complimentary drink.","Complimentary Wi-Fi is available."]"""),
        WordEntity(2038, "itinerary", "/aɪˈtɪnəreri/", "lịch trình chuyến đi", "noun", "B1", "travel", "bnc", """["Please review the travel itinerary.","The itinerary includes three cities.","I'll send you the itinerary by email."]"""),
        WordEntity(2039, "reimbursement", "/ˌriːɪmˈbɜːrsmənt/", "hoàn trả chi phí", "noun", "B2", "work", "bnc", """["Submit receipts for reimbursement.","The reimbursement process takes two weeks.","Travel reimbursement is available."]"""),
        WordEntity(2040, "correspondence", "/ˌkɒrɪˈspɒndəns/", "thư từ, trao đổi", "noun", "B2", "communication", "bnc", """["Please direct all correspondence to this address.","I handle business correspondence.","Keep a record of all correspondence."]"""),

        // === More B1-B2 ===
        WordEntity(2041, "efficient", "/ɪˈfɪʃənt/", "hiệu quả", "adjective", "B1", "work", "bnc", """["This method is more efficient.","We need an efficient solution.","She is an efficient worker."]"""),
        WordEntity(2042, "regulations", "/ˌreɡjuˈleɪʃənz/", "quy định", "noun", "B1", "society", "bnc", """["Safety regulations must be followed.","New regulations were introduced.","Government regulations on pollution."]"""),
        WordEntity(2043, "adjacent", "/əˈdʒeɪsənt/", "liền kề, bên cạnh", "adjective", "B2", "descriptions", "bnc", """["The adjacent room is empty.","Our office is adjacent to the bank.","Adjacent buildings were evacuated."]"""),
        WordEntity(2044, "clarify", "/ˈklærɪfaɪ/", "làm rõ", "verb", "B1", "communication", "bnc", """["Could you please clarify this point?","Let me clarify what I meant.","The manager clarified the new policy."]"""),
        WordEntity(2045, "reluctant", "/rɪˈlʌktənt/", "miễn cưỡng, do dự", "adjective", "B2", "emotions", "bnc", """["She was reluctant to accept the offer.","He is reluctant to change his mind.","They were reluctant participants."]"""),

        WordEntity(2046, "approximately", "/əˈprɒksɪmətli/", "khoảng, xấp xỉ", "adverb", "B1", "descriptions", "bnc", """["It takes approximately two hours.","Approximately 500 people attended.","The cost is approximately $100."]"""),
        WordEntity(2047, "consecutive", "/kənˈsekjətɪv/", "liên tiếp", "adjective", "B2", "descriptions", "bnc", """["She won three consecutive games.","It rained for five consecutive days.","Sales increased for three consecutive months."]"""),
        WordEntity(2048, "eligible", "/ˈelɪdʒəbəl/", "đủ điều kiện", "adjective", "B2", "work", "bnc", """["Are you eligible for the scholarship?","Only eligible candidates will be considered.","She is eligible for promotion."]"""),
        WordEntity(2049, "proficiency", "/prəˈfɪʃənsi/", "sự thành thạo", "noun", "B2", "education", "bnc", """["English proficiency is required.","Her proficiency in French is impressive.","Language proficiency test results."]"""),
        WordEntity(2050, "tentative", "/ˈtentətɪv/", "dự kiến, sơ bộ", "adjective", "B2", "work", "bnc", """["The tentative date is March 15.","This is a tentative plan.","She gave a tentative smile."]""")
    )
}
