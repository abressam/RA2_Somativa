package com.example.ra2somativa.feature.data.model

class QuestionData {
    fun loadQuestions(): List<Question> = listOf(
        Question(
            questionText = "Em qual país está localizado o Monte Fuji?",
            options = listOf("Coréia do Sul", "China", "Mongólia", "Japão"),
            correctAnswerIndex = 3,
            imageUrl = "https://images.pexels.com/photos/18882602/pexels-photo-18882602/free-photo-of-lake-and-fuji-mountain-behind.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            tips = listOf("Tóquio", "Anime", "Terremotos")
        ),
        Question(
            questionText = "Em qual país está localizado o Templo Borobudur?",
            options = listOf("Indonésia", "China", "Índia", "Mongólia"),
            correctAnswerIndex = 0,
            imageUrl = "https://images.pexels.com/photos/6109262/pexels-photo-6109262.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            tips = listOf("Clima tropical", "Maior arquipélogo do mundo", "Fronteira com a Malásia")
        ),
        Question(
            questionText = "Em qual país está localizado o Palácio de Potala?",
            options = listOf("Coréia do Norte", "Turquia", "Rússia", "China"),
            correctAnswerIndex = 3,
            imageUrl = "https://images.pexels.com/photos/8604524/pexels-photo-8604524.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            tips = listOf("Muralha", "Maior população do mundo", "Dinastia Qin")
        ),
        Question(
            questionText = "Em qual país está localizado o Templo de Karnak?",
            options = listOf("Angola", "Egito", "Etiópia", "Marrocos"),
            correctAnswerIndex = 1,
            imageUrl = "https://images.pexels.com/photos/18991528/pexels-photo-18991528/free-photo-of-view-of-the-karnak-temple-luxor-egypt.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            tips = listOf("Pirâmides", "Rio Nilo", "Cleópatra")
        ),
        Question(
            questionText = "Em qual país está localizado a Capadócia?",
            options = listOf("Geórgia", "Romênia", "Turquia", "Armênia"),
            correctAnswerIndex = 2,
            imageUrl = "https://images.pexels.com/photos/3889724/pexels-photo-3889724.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            tips = listOf("Império Otomano", "Cidade de Tróia", "Kebabs")
        ),
        Question(
            questionText = "Em qual país está localizado o Palácio de Versalhes?",
            options = listOf("França", "Alemanha", "Suiça", "Itália"),
            correctAnswerIndex = 0,
            imageUrl = "https://images.pexels.com/photos/19778687/pexels-photo-19778687/free-photo-of-aerial-view-of-the-gardens-of-palace-of-versailles-paris-france.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            tips = listOf("Croissant", "Museu do Louvre", "Torre Eiffel")
        ),
        Question(
            questionText = "Em qual país está localizado a Catedral de São Basílio?",
            options = listOf("Polônia", "Cazaquistão", "Rússia", "Ucrânia"),
            correctAnswerIndex = 2,
            imageUrl = "https://images.pexels.com/photos/753339/pexels-photo-753339.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            tips = listOf("Czar", "Vodka", "Stroganoff")
        ),
        Question(
            questionText = "Em qual país está localizado a Ilha de Páscoa?",
            options = listOf("Peru", "Colombia", "Filipinas", "Chile"),
            correctAnswerIndex = 3,
            imageUrl = "https://images.pexels.com/photos/14545823/pexels-photo-14545823.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            tips = listOf("Deserto Atacama", "Vinho", "Maior observatório astronômico")
        ),
        Question(
            questionText = "Em qual país está localizado a Ópera de Sydney?",
            options = listOf("Emirados Árabes Unidos", "Austrália", "Austria", "Marrocos"),
            correctAnswerIndex = 1,
            imageUrl = "https://images.pexels.com/photos/1878293/pexels-photo-1878293.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            tips = listOf("Cangoroo", "Coala", "Banda Men at Work")
        ),
        Question(
            questionText = "Em qual país está localizado o Teotihuacán?",
            options = listOf("México", "Cuba", "Costa Rica", "El Salvador"),
            correctAnswerIndex = 0,
            imageUrl = "https://images.pexels.com/photos/4092171/pexels-photo-4092171.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            tips = listOf("Taco", "Dia de Los Muertos", "El Chavo del Ocho")
        ),
    )
}