package com.example.compose.data

import com.example.compose.domain.entities.ListItem
import com.example.compose.domain.entities.ListItem.StudentItem
import com.example.compose.domain.entities.ListItemData
import javax.inject.Inject

interface ItemsSource {
    fun getItems(): ListItemData

    fun addStudent(newStudent: StudentItem)
}

class ItemsSourceImpl @Inject constructor() : ItemsSource {

    private val data: MutableList<ListItem> = mutableListOf(
        StudentItem(
            name = "Иван",
            secondName = "Иванов",
            description = "Только что выпустился из универа, с Android знаком не сильно",
            hasPortfolio = true,
        ),
        ListItem.BannerItem(
            title = "Новая заявка",
            description = "Здравствуйте, меня зовут Глеб, ещё не поздно подать заявку?"
        ),
        StudentItem(
            name = "Пётр",
            secondName = "Петров",
            description = "Сеньор-помидор, 30 лет опыта С++, хочу попробовать себя в новом направлении",
            hasPortfolio = false,
        ),
        StudentItem(
            name = "Семён",
            secondName = "Сёменов",
            description = "Прошёл курсы Skillbox, SkillFactory, SkillShare, но не могу найти работу, помогите мне",
            hasPortfolio = false,
        ),
        StudentItem(
            name = "Андрей",
            secondName = "Андреев",
            description = "Мне не придумали длинного описания",
            hasPortfolio = true,
        ),
        StudentItem(
            name = "Егор",
            secondName = "Егоров",
            description = "Lorem ipsum dolor sit amet ya uchenik mne 19 let",
            hasPortfolio = true,
        ),
    )

    override fun getItems(): ListItemData = ListItemData(data)

    override fun addStudent(newStudent: StudentItem) {
        data.add(newStudent)
    }
}