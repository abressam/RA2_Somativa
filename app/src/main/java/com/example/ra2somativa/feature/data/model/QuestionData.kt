package com.example.ra2somativa.feature.data.model

class QuestionData {
    fun loadQuestions(): List<Question> = listOf(
        Question(
            questionText = "Em qual país está localizado o Monte Fuji?",
            options = listOf("Coréia do Sul", "China", "Mongólia", "Japão"),
            correctAnswerIndex = 3,
            imageUrl = "https://images.pexels.com/photos/18882602/pexels-photo-18882602/free-photo-of-lake-and-fuji-mountain-behind.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
        ),
//        Question(
//            questionText = "Em qual país está localizado o Templo Borobudur?",
//            options = listOf("Indonésia", "China", "Índia", "Mongólia"),
//            correctAnswerIndex = 0,
//            imageUrl = "https://images.pexels.com/photos/6109262/pexels-photo-6109262.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
//        ),
//        Question(
//            questionText = "Em qual país está localizado o Palácio de Potala?",
//            options = listOf("Coréia do Norte", "Turquia", "Rússia", "China"),
//            correctAnswerIndex = 3,
//            imageUrl = "https://images.pexels.com/photos/8604524/pexels-photo-8604524.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
//        ),
//        Question(
//            questionText = "Em qual país está localizado o Templo de Karnak?",
//            options = listOf("Angola", "Egito", "Etiópia", "Marrocos"),
//            correctAnswerIndex = 1,
//            imageUrl = "https://images.pexels.com/photos/18991528/pexels-photo-18991528/free-photo-of-view-of-the-karnak-temple-luxor-egypt.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
//        ),
//        Question(
//            questionText = "Em qual país está localizado a Capadócia?",
//            options = listOf("Geórgia", "Romênia", "Turquia", "Armênia"),
//            correctAnswerIndex = 2,
//            imageUrl = "https://images.pexels.com/photos/3889724/pexels-photo-3889724.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
//        ),
    )
}