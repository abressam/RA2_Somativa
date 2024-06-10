package com.example.ra2somativa.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ra2somativa.ui.theme.button

@Composable
fun InstructionScreen(
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Instruções do Jogo",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "1º - Informe um apelido para liberar o botão 'Começar a Jogar';",
            fontSize = 14.sp,
            textAlign = TextAlign.Justify
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "2º - Ao iniciar o jogo, você terá 10 segundos (representado pela barra que troca de cor) para selecionar uma das 4 opções de resposta;",
            fontSize = 14.sp,
            textAlign = TextAlign.Justify
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "3º - Você pode fazer até 1000 pontos em cada pergunta caso acertar a resposta certa, os pontos são proporcionais ao tempo, então a cada segundo são descontados -100 pontos;",
            fontSize = 14.sp,
            textAlign = TextAlign.Justify
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "4º - Se responder errado ou não selecionar uma opção, automaticamente recebe 0 pontos;",
            fontSize = 14.sp,
            textAlign = TextAlign.Justify
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "5º - Uma tela de feedback é mostrada ao final de cada pergunta, sendo de sucesso ou erro, com a sua pontuação e um timer de 3 segundos para a próxima pergunta;",
            fontSize = 14.sp,
            textAlign = TextAlign.Justify
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "6º - O jogo tem um total de 10 perguntas referentes a pontos turísticos pelo mundo;",
            fontSize = 14.sp,
            textAlign = TextAlign.Justify
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "7º - Cada pergunta tem direito a uma única dica aleatória que pode ser acessada pelo botão no canto superior direito;",
            fontSize = 14.sp,
            textAlign = TextAlign.Justify
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "8º - Ao final do jogo é mostrada a sua posição na tela de Ranking, o botão 'Voltar' retorna a tela inicial;",
            fontSize = 14.sp,
            textAlign = TextAlign.Justify
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "9º - Boa sorte!",
            fontSize = 14.sp,
            textAlign = TextAlign.Left
        )
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = {
                navController.navigate("start_screen")
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = button),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Voltar", fontSize = 18.sp)
        }
    }
}
